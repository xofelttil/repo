<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>小辰出行后台管理</title>
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/demo/demo.css">
	<script type="text/javascript" src="jquery-easyui-1.7.0/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
	
</head>
<body>
	<div style="margin:20px 0;"></div>
	
	<table class="easyui-datagrid" title="Basic DataGrid" style="width:950px;height:150px"
			data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'itemid',width:80">Item ID</th>
				<th data-options="field:'productid',width:100">value</th>
				
			</tr>
			<tr>
			<th data-options="field:'itemid',width:80">PV</th>
				<th data-options="field:'productid',width:100" id="pv">value</th>
			</tr>
			
			<tr>
				<th data-options="field:'itemid',width:80">uv</th>
				<th data-options="field:'productid',width:100" id="uv">value</th>
				
			</tr>
		</thead>
	</table> 
	<input type="button" value="更新数据" onclick="update()"> &nbsp;&nbsp;&nbsp;&nbsp; 更新时间<div id="updateTime"></div>
  
  
  <table id="dg" title="My Users" class="easyui-datagrid" style="width:950px;height:700px"
           toolbar="#toolbar"
           rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="a" width="50">USERAccessLog</th>
                
            </tr>
            
            
           
        </thead>
        
        	
    </table>
</body>
		<script type="text/javascript">
		
		window.onload= function () {
			var uv =document.getElementById("uv");
			var pv =document.getElementById("pv");
			$.ajax({
				url : "back.do?op=onLoaddata",
				dataType : "JSON",
				
				success : function(data) {
					$("#uv").text(data.uv);
					$("#pv").text(data.pv);
					
					
			
				}
			});
			
			
			
			$.ajax({
				url : "back.do?op=getAccess",
				dataType : "JSON",
				success : function(data) {
					var tdArr = document.getElementById('dg').firstElementChild;
					for (var i = 0; i < data.length; i++) {
						var obj =data[i];
					
					    $('#dg').datagrid('appendRow',{
					        a: obj
					    }
					        
					    );
						
			            
			        
			          
			            
						

			        }
					
				}
			});
			
		}
		
		
		 function update(){
			 $.ajax({
					url : "back.do?op=onLoaddata",
					dataType : "JSON",
					
					success : function(data) {
						$("#uv").text(data.uv);
						$("#pv").text(data.pv);
						 $('#updateTime').text(data.date);
						
						
						
						
					}
				});
			 
			
		 }
		
		</script>
</html>