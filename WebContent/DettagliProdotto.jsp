<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><% ProdottoBean bean = (ProdottoBean) request.getAttribute("prodotto"); %>
<%= bean.getNome() %></title>
</head>
<body>

</body>
</html>