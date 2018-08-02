<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>成功登陆</title>
</head>
<body>
<h1>成功登陆</h1>
<shiro:hasPermission name="user:add">  
进入添加页面：<a href="add.action">添加</a><br>
</shiro:hasPermission>
<shiro:hasPermission name="user:update">  
进入更新页面：<a href="update.action">更新</a><br>
</shiro:hasPermission>
</body>
</html>