
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <head>
    	
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/demo/demo.css">
	<script type="text/javascript" src="jquery-easyui-1.7.0/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
	
	 <script>
    function goPage(pageno){
    	
    	if(pageno!=null&& pageno!=""){
    		
    		document.getElementById("pageno").value=pageno;
    	}
    	document.myform.submit();
    	
    }
    
    
    </script>
	
    </head>
    <body >
    
    
 <form name="myform" action="doFind.jsp" method="post">
 
<input type="text" name="单车号" value="${keyword}"/> <input type="submit" value="搜索">

<hr/>
<table id ="dataList" name="dataList" width="80%">


</table>
<a href="javascript:void" onclick="goPage(1)">第一页</a>
<a href="javascript:void" onclick="goPage(${pagebean.prepage})">上一页</a>
<a href="javascript:void" onclick="goPage(${pagebean.nextpage})">下一页</a>
<a href="javascript:void" onclick="goPage(${pagebean.totalpage})">最后一页</a>
第${pagebean.pageno }页/共${pagebean.totalpage }页 每页${pagebean.pagesize }条/共${pagebean.total}条
到第<input id="pageno" type="text" name="pageno"/>页
</form>


    </body>
    
    	
    		
    	
    	
 


    </html>

