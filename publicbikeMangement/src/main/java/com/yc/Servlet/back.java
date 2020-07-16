package com.yc.Servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.yc.bean.bike;
import com.yc.bean.userBean;
import com.yc.biz.backBiz;
import com.yc.dao.backDao;
import com.yc.util.MongoDBUtil;

/**
 * Servlet implementation class back
 */
@WebServlet("/back.do")
public class back extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	        
	         
	         
	        
		
		String op =request.getParameter("op");
		if("findAll".equals(op)){
			findAll(request,response);
			
		}else if("delete".equals(op)){
			delete(request,response);
		}else if("update".equals(op)){
			update(request,response);
		}else if("findByid".equals(op)){
			findByid(request,response);
			
		}else if("login".equals(op)){
			login(request,response);
		}else if("onLoaddata".equals(op)){
			onLoaddata(request,response);
		}else if("findAllUsers".equals(op)){
			findAllUsers(request,response);
		}else if("deleteUsers".equals(op)){
			deleteUsers(request,response);
		}else if("getAccess".equals(op)){
			getAccess(request,response);
		}
		
		
	}




	private void getAccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		backBiz bz =new backBiz();
		   List<String> access = bz.getAccess();
		
		
		super.writeJson(response,access );
	}




	private void deleteUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id =request.getParameter("id");
		backBiz bz =new backBiz();
		List<userBean> findAllUsers = bz.deleteUsers(id);
		System.out.println(findAllUsers+"=========================");
		if(findAllUsers.equals("[]")){
			userBean user1 =new userBean();
			user1.set_id("null");
			findAllUsers.add(user1);
		}
		super.writeJson(response,findAllUsers );
	}




	private void findAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		backBiz bz =new backBiz();
		List<userBean> findAllUsers = bz.findAllUsers();
		super.writeJson(response,findAllUsers );
		
	}




	private void onLoaddata(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		backBiz bz =new backBiz();
		Map<String, Object> onLoaddata = bz.onLoaddata();
		System.out.println("onLoaddata");
		super.writeJson(response, onLoaddata);
	}




	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name =request.getParameter("username");
		
		String pass =request.getParameter("password");
		
		System.out.println(name + pass);
		
		if("admin".equals(name) && "a".equals(pass)){
			System.out.println("待验证。。。");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			System.out.println("执行跳转成功");
		}
		
	}




	private void findByid(HttpServletRequest request, HttpServletResponse response) throws IOException {
			
			String id =request.getParameter("id");
		
			System.out.println("执行查找获取id成功"+id);
		
			backBiz bz =new backBiz();
		
			List<bike> delete = bz.findByid(id);
		
			super.writeJson(response, delete);
		
			System.out.println("执行删除后返回结果集合");
	}




	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id =request.getParameter("id");
		
		String status =request.getParameter("status");
		String qrcode =request.getParameter("qrcode");
		String loc =request.getParameter("loc");
		String flag =request.getParameter("flag");
		
		System.out.println("Update的参数"+status+qrcode+loc+flag+id);
		backBiz bz =new backBiz();
		List<bike> bike =null;
		if("true".equals(flag)){
			bike = bz.update(id,status,loc,qrcode);
		}else if("false".equals(flag)){
			bike=bz.insert(id,status,loc,qrcode);
		}
	
		super.writeJson(response, bike);
		
	}




	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id =request.getParameter("id");
		
		System.out.println("获取id成功"+id);
		
		backBiz bz =new backBiz();
		
		List<bike> delete = bz.delete(id);
		
		super.writeJson(response, delete);
		
		System.out.println("执行删除后返回结果集合");
		
		
	}




	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
					
		backBiz bz =new backBiz();
		  List<bike> bike = bz.findAllBike();
		
		super.writeJson(response, bike);
		
		
		
	}

}
