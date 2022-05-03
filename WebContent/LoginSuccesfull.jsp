<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Succesfull</title>
</head>
<body>
<% UserBean utente = (UserBean) request.getAttribute("utente"); %>

<h1>hello <%= utente.getNome()%> <%= utente.getCognome() %></h1>

	<jsp:forward page="/CreaCatalogo" />

</body>
</html>