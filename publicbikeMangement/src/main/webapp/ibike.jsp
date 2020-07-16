
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <head>
    	
    	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.7.0/demo/demo.css">
	<script type="text/javascript" src="jquery-easyui-1.7.0/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    </head>
    <body >
    
    
   请输入单车号查询: <input type="text" id="search" >
   
   <input type="button" onclick="findByid()" value="查询"><br>
  
    
   
    
    
     单车号:<input type="text" id="id"> &nbsp;&nbsp;&nbsp;&nbsp;单车状态:<input type ="text" id="status"><br>
    二维码:<input type="text" id= "qrcode">  &nbsp;&nbsp;&nbsp;&nbsp;单车位置:<input type ="text" id="loc"><br>
   <input type="radio" name="ud" value="true" checked>update
<br>
	<input type="radio" name="ud" value="false">insert
	
	<input type="submit" value="提交" onclick="update()">
    
    
   
    
    
    
    	<table  border="2" id="tablebox">
    <tr>
        <td class="active">单车id</td>
        <td class="warning">单车位置</td>
        <td class="bikesta">单车状态</td>
        <td class="bikeqr">二维码</td>
         <td class="bikecon">操作</td>
        
    </tr>
		</table>

    </body>
    
    <script type="text/javascript">
    		var toolbar = [{
    			text:'Add',
    			iconCls:'icon-add',
    			handler:function(){alert('add')}
    		},{
    			text:'Cut',
    			iconCls:'icon-cut',
    			handler:function(){alert('cut')}
    		},'-',{
    			text:'Save',
    			iconCls:'icon-save',
    			handler:function(){alert('save')}
    		}];
    		
   			window.onload= function () {
    			
    			$.ajax({
					url : "back.do?op=findAll",
					dataType : "JSON",
					success : function(data) {
						
						var tdArr = document.getElementById('tablebox').firstElementChild;
						for (var i = 0; i < data.length; i++) {
							var obj =data[i];
							var id=obj.id;
							console.log(id);
							var tr = document.createElement("tr");
				            tr.innerHTML = '<td class="active">' + obj.id + '</td><td class="warning">'+obj.location+'</td>'
				            +'<td class="bikesta">'+obj.status1+'</td>'+'<td class="bikeqr">'+obj.qrcode+'</td>'
				            +'<td class="bikecon"> <a href="javascript:void(0)" onclick="deleteFunction( \''+ id+ '\'  )">delete</a></td>'
				            ;
				            
				            
				        
				            tdArr.appendChild(tr)
			
				        }
				
					}
				});
    		}
    		
    		
    		 
    		function deleteFunction(id){

    			$.ajax({
					url : "back.do?op=delete&id="+id,
					dataType : "JSON",
					success : function(data) {
						location.reload();
			
				        
				
					}
				});
    			
    		}
    		
    		
    		function findByid(){
    			var id =document.getElementById('search').value;
    			
    			
    			$.ajax({
					url : "back.do?op=findByid&id="+id,
					dataType : "JSON",
					success : function(data) {
						
						var tdArr = document.getElementById('tablebox').firstElementChild;
						 $("#tablebox tr").remove(""); 
						for (var i = 0; i < data.length; i++) {
							var obj =data[i];
							var id=obj.id;
							console.log(id);
							var tr = document.createElement("tr");
				            tr.innerHTML = '<td class="active">' + obj.id + '</td><td class="warning">'+obj.location+'</td>'
				            +'<td class="bikesta">'+obj.status1+'</td>'+'<td class="bikeqr">'+obj.qrcode+'</td>'
				            +'<td class="bikecon"> <a href="javascript:void(0)" onclick="deleteFunction( \''+ id+ '\'  )">delete</a></td>'
				            ;
				           
				            tdArr.appendChild(tr);
				           
			
				        }
				
					}
				});
    			
    		}
    		
    		function update(){
    			var id =document.getElementById('id').value;
    			var status =document.getElementById('status').value;
    			var qrcode =document.getElementById('qrcode').value;
    			var loc =document.getElementById('loc').value;
    			
    			var flag =$('input:radio:checked').val();
    			
    			
    			$.ajax({
					url : "back.do?op=update&id="+id+"&status="+status+"&loc="+loc+"&qrcode="+qrcode+"&flag="+flag,
					dataType : "JSON",
					success : function(data) {
						
						var tdArr = document.getElementById('tablebox').firstElementChild;
						 $("#tablebox tr").remove(""); 
						for (var i = 0; i < data.length; i++) {
							var obj =data[i];
							var id=obj.id;
							console.log(id);
							var tr = document.createElement("tr");
				            tr.innerHTML = '<td class="active">' + obj.id + '</td><td class="warning">'+obj.location+'</td>'
				            +'<td class="bikesta">'+obj.status1+'</td>'+'<td class="bikeqr">'+obj.qrcode+'</td>'
				            +'<td class="bikecon"> <a href="javascript:void(0)" onclick="deleteFunction( \''+ id+ '\'  )">delete</a></td>'
				            ;
				           
				            tdArr.appendChild(tr);
				            location.reload();
				            
			
				        }
				
					}
				});
    		}
    		
    	
    	</script>
 


    </html>

