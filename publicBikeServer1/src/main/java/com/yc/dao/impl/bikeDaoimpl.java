package com.yc.dao.impl;

import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import com.yc.bean.bike;
import com.yc.dao.bikeDao;
@Repository
public class bikeDaoimpl implements bikeDao{
		private JdbcTemplate jdbcTemp;
		
		@Autowired
		public void setDataSource(DataSource datasource){
			this.jdbcTemp=new JdbcTemplate(datasource);
		}
	@Override
	public bike addBike(bike bk) {
		/*
		 * 添加自行车操作
		 * GeneratedKeyHolder获取自增列
		 */
		// TODO Auto-generated method stub
		String sql="insert into bike( latitude,longitude,status,qrcode ) values(0.0,0.0,0,'')";
		KeyHolder keyholder =new GeneratedKeyHolder();
		this.jdbcTemp.update(connection ->{
			java.sql.PreparedStatement ps= connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			return ps;
		},keyholder
		
				);
		bk.setBid(keyholder.getKey()+"");
		
		return bk;
		
	}

	
	/*
	 * 更新自行车状态
	 */
	@Autowired
	private MongoTemplate mongoTemplate;
	public void updateBike(bike bk) {
		Query q=new Query();
		q.addCriteria(    Criteria.where("id").is(bk.getBid()));
		Update u=new Update();
		u.set("status", bk.getStatus() );
		u.set("latitude", bk.getLatitude());
		u.set("longitude", bk.getLongitude());
		u.set("qrcode", bk.getQrcode());
		this.mongoTemplate.updateFirst(q, u, bike.class,"bike");
		
	}
/*
 * 查找车辆
 */
	@Override
	public bike findBike(String bid) {
		bike b=mongoTemplate.findById( bid, bike.class,"bike");
		return b;
		
	}

}
