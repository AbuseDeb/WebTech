<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="../RegisterServlet" method="POST">
		<h1>Register</h1>
		<br><br>
		<p>${err}</p>
	    Login: <input name="login" />
	    <br><br>
	    Password: <input name="password" type="password"/>
	    <br><br>    
	    <input type="submit" value="Register" />
	</form>
	<br><br> 
	Back to <a href='<c:url value="/index.jsp" />'>login</a>
</body>
</html>