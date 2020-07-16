package com.yc.util;


import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbHelper {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// ��������
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// ��ȡ���ݿ������
	public Connection getConn() throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jiepai", "root", "a");
		return conn;
	}

	// �ر���Դ ���ȿ����ĺ�رգ������ģ�
	public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		// �ر���Դ
		if (rs != null && !rs.isClosed()) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (pstmt != null && !pstmt.isClosed()) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null && !conn.isClosed()) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ���²��� ��ɾ��
	 * 
	 * @param sql
	 *            �������
	 * @param params
	 *            ����Ĳ��� �������Ķ������� ����Ĳ���˳���룿˳��һ��
	 * @throws SQLException
	 * 
	 */
	public int update(String sql, Object... params) throws SQLException {
		int result = 0;
		try {
			conn = getConn();// ��ȡ���Ӷ���
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			setParamsObject(pstmt, params);
			// ִ��
			result = pstmt.executeUpdate();
		} finally {
			closeAll(conn, pstmt, null);
		}
		return result;
	}

	// ���������� ���ò��� ����Ĳ���˳�������ʺŵ�˳��һ��
	public void setParamsObject(PreparedStatement pstmt, Object... params) throws SQLException {
		if (params == null || params.length <= 0)
			return;
		for (int i = 0; i < params.length; i++) {
			pstmt.setObject(i + 1, params[i]); // �������еĵ�i��Ԫ�ص�ֵ����Ϊ��i + 1 ���ʺ�
		}
	}
	
	public double getCount(String sql, Object... params) throws SQLException {
		double result = 0;
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			setParamsObject(pstmt, params);
			rs = pstmt.executeQuery();
			if (rs.next())
				result = rs.getDouble(1);
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return result;
	}
	
	/**
	 * ���ض�����¼��ѯ���� select * from table_name
	 * 
	 * @throws IOException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectMutil(String sql, Object... params) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			// ���ò���
			setParamsObject(pstmt, params);
			// ��ȡ�����
			rs = pstmt.executeQuery();
			// ���ݽ���������ȡ�����н��������������
			List<String> columnNames = getAllColumnNames(rs);
			while (rs.next()) {
				map = new HashMap<String, Object>();
				Object obj = null; // ��ȡ��ֵ
				// ѭ�����е�����
				for (String name : columnNames) {
					obj = rs.getObject(name);
					
					map.put(name, obj);
					
					list.add(map);
				}
			}
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * ��ѯ�����¼
	 * 
	 * @param <T>
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findMutil(String sql, Class<T> cls ,Object... params) throws Exception {
		List<T> list = new ArrayList<T>();
		T t = null;
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			setParamsObject(pstmt, params);
			rs = pstmt.executeQuery();
			Method[] methods = cls.getDeclaredMethods();
			List<String> columnNames = getAllColumnNames(rs);
			while (rs.next()) {
				t = cls.newInstance();
				for (String name : columnNames) {
					for (Method m : methods) {
						if (("set" + name).equalsIgnoreCase(m.getName())) {
							String typeName = m.getParameterTypes()[0].getName();
							
							if ("java.lang.Integer".equals(typeName)  ||"int".equals(typeName)) {
								m.invoke(t, rs.getInt(name));
							} else if ("java.lang.Double".equals(typeName) ||"double".equals(typeName)) {
								m.invoke(t, rs.getDouble(name));
							} else if ("java.lang.Float".equals(typeName)|| "float".equals(typeName)) {
								m.invoke(t, rs.getFloat(name));
							} else if ("java.lang.Long".equals(typeName)|| "long".equals(typeName)) {
								m.invoke(t, rs.getLong(name));
							} else {
								m.invoke(t, rs.getString(name));
							}
						}
					}
				}
				list.add(t);
			}
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return list;
	}

	

	/**
	 * ��ȡ��ѯ����ֶ���
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public List<String> getAllColumnNames(ResultSet rs) throws SQLException {
		List<String> list = new ArrayList<String>();
		ResultSetMetaData data = rs.getMetaData();
		int count = data.getColumnCount();
		for (int i = 1; i <= count; i++) {
			String str = data.getColumnName(i); // ��ȡָ���е�����
			// ��������� List ������
			list.add(str);
		}
		return list;
	}



}
