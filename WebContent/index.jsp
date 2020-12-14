<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Form</title>
</head>
<body>
	<form action="LoginServlet" method="POST">
		<h1>Login</h1>
		<br><br>
		
	    Login: <input name="login" />
	    <br><br>
	    Password: <input name="password" type="password"/>
	    <br><br>    
	    <input type="submit" value="Login" />
	</form>
	<br><br> 
	 <a href='<c:url value="/Register.jsp" />'>Register</a>
</body>
</html>