<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*" import="beans.*"
	import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="catalogo.css" rel="stylesheet" type="text/css">
<title>Catalogo prodotti</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<jsp:include page="header.jsp" />
	<%  UserBean utente = (UserBean) request.getSession(true).getAttribute("utente");
	
		if(utente != null && utente.getAdmin())
		{
		%>
		<jsp:forward page="adminPage.jsp"/>
	<% } %>


	<div class="pasticceria">
		<span class="titolo">La Pasticceria</span>
	</div>

	<% ArrayList<ProdottoBean> ListaProdotti = (ArrayList<ProdottoBean>) request.getAttribute("prodotti");
	if(ListaProdotti == null) {
		response.sendRedirect("./CreaCatalogo");	
		return;
	}
	%>
	<div class="container">
  	<%
  				for(ProdottoBean prodotto : ListaProdotti)
  				{
  					%>
		<div class="card">
			<a href="CreaCatalogo?action=details&id=<%=prodotto.getNome()%>"><img class="image" src="./immagini/<%= prodotto.getPathImage().get(0)  %>"></a>
			<div class="dettagli-card">
				<div class="nome"><%= prodotto.getNome() %></div>
				<p>&euro; <%= String.format("%.02f", prodotto.getPrezzo())%></p>
				<a class="scritte" href="GestioneCarrello?action=aggiungi&id=<%=prodotto.getNome()%>">Aggiungi al carrello</a>
			</div>
		</div>
	<%}%>
	</div>


	<form action="LogoutServlet" method="get">
		<input type="submi value="Logout" />


		<a href = "login-form.jsp">Login</a>



</body>
</html>