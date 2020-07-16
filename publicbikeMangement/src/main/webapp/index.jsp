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
	<style>
		.westname
		{ 
		text-decoration:none;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$('.westname').click(function(){
				var name = this.innerHTML;
				var url = this.href;
				//通过获取的名字创建选项卡
				crateTabs(name,url);
				//让超链接不跳转
				return false;
			});
			
		});
		
		//创建tabs的函数
		function crateTabs(name,url){
			if($('#tt').tabs('exists',name)){
				$('#tt').tabs('select',name);  
			}else{
				$('#tt').tabs('add',{    
				    title:name,    
				    content:createUrl(url),    
				    closable:true    
				});  
			}
		}
		//使用iframe转化里面的数据使之返回的是url里面的数据
		function createUrl(url){
			return "<iframe src='"+url+"' style='border:0px;width:100%;height:95%;'></iframe>";
		}
		
	</script>
</head>
<body>
		<div id="cc" class="easyui-layout" data-options="fit:true">   
    	<div data-options="region:'north',title:'小辰出行后台管理',split:true" style="height:100px;"></div> 
    	  
	    <div data-options="region:'west',title:'系统菜单',split:true" style="width:100px;">
			<div id="aa" class="easyui-accordion"  data-options="fit:true">
				<div title="单车管理" data-options="iconCls:'icon-reload',selected:true" style="overflow: auto; padding: 10px;">
					<a href="ibike.jsp" class="westname" >单车管理</a>
				</div>
				<div title="用户管理" data-options="iconCls:'icon-reload'" style="padding: 10px;">
					<a href="User.jsp" class="westname">用户管理</a>
				</div>
				<div title="数据管理" data-options="iconCls:'icon-reload'" style="padding: 10px;">
					<a href="datapage.jsp" class="westname">数据管理</a>
				</div>
			
			<!--  
			<div title="订单管理" data-options="iconCls:'icon-reload'" style="padding: 10px;">
					<a href="#" class="westname">订单管理</a>
				</div>
				<div title="地址管理" data-options="iconCls:'icon-reload'" style="padding: 10px;">
					<a href="#" class="westname">地址管理</a>
				</div>
				
			
				
				-->
			</div>
		</div>   
		
	    <div data-options="region:'center',title:''" style="padding:5px;background:#eee;">
			<div id="tt" class="easyui-tabs" data-options="fit:true">
				<div title="数据资料" data-options="closable:true" style="overflow: auto; padding: 20px; display: none;">这是数据资料</div>
			</div>
		</div>    
</div>  
</body>
</html>
