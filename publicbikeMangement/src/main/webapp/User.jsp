<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/demo/demo.css">
	<script type="text/javascript" src="jquery-easyui-1.7.0/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
    <table id="dg" title="My Users" class="easyui-datagrid" style="width:950px;height:700px"
           toolbar="#toolbar"
           rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="a" width="50">USER ID</th>
                <th field="b" width="50">Status</th>
                <th field="c" width="50">openId</th>
                <th field="d" width="50">Class</th>
            </tr>
            
            
           
        </thead>
        
        	
    </table>
    <div id="toolbar">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="reload()">Reload User</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
    </div>
    
    
    
	
</body>

<script type="text/javascript">

window.onload= function () {
	
	$.ajax({
		url : "back.do?op=findAllUsers",
		dataType : "JSON",
		success : function(data) {
			var tdArr = document.getElementById('dg').firstElementChild;
			for (var i = 0; i < data.length; i++) {
				var obj =data[i];
			
			    $('#dg').datagrid('appendRow',{
			        a: obj._id,
			        b: obj.status,
			        c: obj.openId,
			        d: obj._class
			    }
			        
			    );
				
	            
	        
	          
	            
				

	        }
			
		}
	});
}

function reload(){
	
	 location.reload();
}

function destroyUser(){
    var row = $('#dg').datagrid('getSelected');
    if (row){
    	console.log(row);
        $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
            if (r){
            	
            	$.ajax({
            		url : "back.do?op=deleteUsers&id="+row.a,
            		dataType : "JSON",
            		success : function(data) {
            			 
            			 location.reload();
            			
            		}
            	});
            }
        });
    }
	
}

</script>

</html>