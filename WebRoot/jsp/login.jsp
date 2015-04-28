<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'login.jsp' starting page</title>
<script type="text/javascript"
	src="js/jquery-easyui-1.3.6/jquery-2.1.0.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js" charset="UTF-8"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.3.6/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.3.6/themes/icon.css">
<script type="text/javascript">
	var loginAndRegDialog;
	$(function() {
		loginAndRegDialog = $("#loginAndRegDialog").dialog({
			closable : false,
			modal : true,
			buttons : [ {
				text : '注册',
				handler : function() {
					$("#loginAndRegDialog").dialog('close');
					$("#register").dialog('open');
				}
			}, {
				text : '登录',
				handler : function() {
					//模拟访问Spring MVC的Controller
					$.ajax({
						url : "login/login.html",
						data : $("#loginInputForm").serialize(),
						type:"post",
						cache : false,
						dataType : 'text',
						success : function(r) {
							var json = eval("(" + r + ")");
							if ( json.success) {
								loginAndRegDialog.dialog('close');
								window.location.href = "jsp/main.jsp";
							} else {
								$.messager.alert('标题', json.msg);
							}
						}
					});
				}
			} ]
		});
	
	$("#register").dialog({
		title:"注册",
		closed: true,
		width:350,
		modal : true,
		closable:true,
		  onClose:function(){
  	  	 			$("#loginAndRegDialog").dialog('open');
  		  	},
		buttons:[	{
					text:"注册",
					handler:function(){
						var password=$("#password").val();
						var dpassword=$("#dpassword").val();
						var email=$("#email").val();
						var username=$("#username").val();
						if(username == ""){
							alert("用户名不能为空");
						}else if(password == ""){
							alert("密码不能为空");
						}else if(dpassword == ""){
							alert("重复密码不能为空");
						}else if(email == ""){
							alert("邮箱不能为空");
						}else{
							$.ajax({
								url:'login/register.html',
								data:{"username":username,"password":password,"email":email},
								success:function (r){
									window.location.href = "jsp/main.jsp";
								}
							});
						}
					}
					},{
						text:"取消",
						handler:function(){
							$("#register").dialog('close');
						}
					}
				]
	});
	$("#dpassword").blur(function(){
			var password=$("#password").val();
			var dpassword=$(this).val();
			if(password != dpassword){
				alert("2次密码不一致");
				$("#dpassword").val("");
				$("#dpassword").focus();
			}
		
	
	});
	
	$("#email").blur(function(){
		var email=$(this).val();
		if(!isEmail(email)){
				alert("邮箱格式不正确");
				$("#email").val("");
				$("#email").focus();
		}
	
	});
	
	$("#username").blur(function(){
		var username=$(this).val();
		$.ajax({
			url:'login/checkusername.html',
			data:{"username":username},
			success:function(r){
				if(r != "success"){
					alert("用户名已经存在");
					$("#username").val("");
					$("#username").focus();
				}
			}
		});
	
	});
	
	function isEmail(str){
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		return reg.test(str);
	} 
		
	
});
</script>
</head>
<body style="width:100%;height: 100%;">
	<div id="loginAndRegDialog" title="用户登录"
		style="width:400px;height:200px;">
		<form action="loginInputForm" method="post" id="loginInputForm">
			<table>
				<tr>
					<th align="right">用户名：</th>
					<td><input name="username" class="easyui-validatebox"
						required="true"></td>
				</tr>
				<tr>
					<th align="right">密&nbsp;&nbsp;码：</th>
					<td><input name="password" type="password"
						class="easyui-validatebox" required="required"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="register">
	<form id="registerform">
	<table>
		<tr>
			<td>用户名&nbsp;:</td>
			<td><input type="text" name="username" required="true" class="easyui-validatebox" id="username" size="10"></td>
		</tr>
		<tr>
		<td>密&nbsp;&nbsp;码:</td>
		<td><input type="password" name="password" required="true" class="easyui-validatebox" id="password" size="10"></td>
		</tr>
		<tr>
		<td>重复密码:</td>
		<td><input type="password" name="dpassword" required="true" class="easyui-validatebox" id="dpassword" size="10"></td>
		</tr>
		<tr>
		<td>邮&nbsp;&nbsp;箱:</td>
		<td><input type="text" name="email" id="email" required="true" class="easyui-validatebox" size="10"></td>
		</tr>
	</table>
	</form>
	</div>
</body>
</html>
