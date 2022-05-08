<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Succesfull</title>
</head>
<body>
<% UserBean utente = (UserBean) request.getAttribute("utente");
pageContext.setAttribute("user",utente.getNome(),PageContext.SESSION_SCOPE);
%>

	<jsp:forward page="/CreaCatalogo" />

</body>
</html>