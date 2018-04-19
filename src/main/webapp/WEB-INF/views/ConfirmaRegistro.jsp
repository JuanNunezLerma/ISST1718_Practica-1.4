<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmacion de Registro</title>
</head>
<body>
Registro confirmado. Pulse OK.

<form action="ConfirmaRegistro" method="POST">
	<br></br>
	Nombre:<br><input type="text" name="nombre" id="nombre" value="${nombre}"></br>
	Apellidos:<br><input type="text" name="apellidos" id="apellidos" value="${apellidos}"></br>
	Email:<br><input type="email" name="email" id="email" value="${email}"></br>
	<input type="submit" value="OK">
</form>
</body>
</html>