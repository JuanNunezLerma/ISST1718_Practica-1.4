<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Usuarios</title>
</head>
<body>
	<table style="width:100%">
	  <tr>
	    <th>Nombre</th>
	    <th>Apellidos</th> 
	    <th>Email</th>
	  </tr>
	  <c:forEach items="${LUsuarios}" var="usu">
	  <tr>
	  	<td><c:out value="${usu.nombre}"/></td>
	  	<td><c:out value="${usu.apellidos}"/></td>
		<td><c:out value="${usu.email}"/></td>
	  </c:forEach>
	  </tr>
	</table>

</body>
</html>