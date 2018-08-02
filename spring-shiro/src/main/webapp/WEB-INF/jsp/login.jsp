<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
</head>
<body>
<h1>登陆</h1>
<h3>${msg}</h3>
<form action="/spring-shiro/login.action" method="post">
	用户名：<input name="username" type="text"><br>
	密码：<input name="password" type="password"><br>
	<input type="submit" value="登录"> 
</form>
</body>
</html>