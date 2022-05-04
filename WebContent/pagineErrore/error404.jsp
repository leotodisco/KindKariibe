<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.*" import = "beans.*"%>
<%@page isErrorPage="true" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Errore 404</title>
		<link rel="stylesheet" type="text/css" href="./errors.css">	
	</head>
	
<body>
<% UserBean utente = (UserBean) request.getAttribute("utente"); %>

	<div>	
		<h1 class="error-code">404</h1>
		<p class="error">Pagina non trovata</p>	
		
			<form method="get" action="../CreaCatalogo">
		 <input type="submit" value="Home"/>
	</form>
		
	</div>
	
		

</body>
</html>