<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
</head>
<body>
	<h2>Products List</h2>
	<p><a href='<c:url value="create" />'>Create new</a></p>
	<table>
	<tr><th>Name</th><th>Price</th><th></th></tr>
	
	<c:forEach var="bet" items="${bets}">
		 <tr>
			<td>${bet.name}</td>
		    <td>${bet.price}</td>
		</tr>	
	</c:forEach>
	</table>
</body>
</html>