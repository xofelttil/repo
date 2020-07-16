<%@page import="com.yc.bean.pagebean"%>
<%@page import="com.yc.bean.bike"%>
<%@page import="com.yc.biz.backBiz"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%

request.setCharacterEncoding("utf-8");
int pageno=1;
if(request.getParameter("pageno")!=null && request.getParameter("pageno").equals("")==false){
	
	 pageno=Integer.parseInt(request.getParameter("pageno"));
}


int pagesize=10;
if(request.getParameter("pagesize")!=null && request.getParameter("pagesize").equals("")==false){
	
	pagesize=Integer.parseInt(request.getParameter("pagesize"));
}


String keyword="";
System.out.println("request.getParameter"+request.getParameter("keyword"));
if(request.getParameter("keyword")!=null){
	System.out.println(keyword);
	keyword=request.getParameter("keyword");
	session.setAttribute("keyword", keyword);
}

backBiz jb=new backBiz();
pagebean pb=jb.find(pageno, pagesize, keyword);

request.setAttribute("pagebean", pb);

request.getRequestDispatcher("list.jsp").forward(request, response);

%>