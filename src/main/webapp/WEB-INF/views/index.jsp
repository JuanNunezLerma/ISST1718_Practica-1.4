<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<p>Por favor, introduzca sus datos de acceso:</p>
		<form action="Acceso" method="post">
		Usuario:<br><input type="text" name="usuario" required></br>
		Clave:<br><input type="password" name="clave" required></br>
		<br><input type="submit" value="Inicio Sesión"></br>
		</form>

<P>  La hora actual en el servidor es: ${serverTime}. </P>
</body>
</html>
