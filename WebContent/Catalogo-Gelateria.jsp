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
	<%  UserBean utente = (UserBean) request.getSession().getAttribute("utente");
	
		if(utente != null && utente.getAdmin())
		{
		%>
		<jsp:forward page="adminPage.jsp"/>
	<% } %>


	<div class="pasticceria">
		<span class="titolo">La Gelateria</span>
	</div>

	<% ArrayList<ProdottoBean> ListaProdotti = (ArrayList<ProdottoBean>) request.getAttribute("prodotti");
	if(ListaProdotti == null) {
		response.sendRedirect("./CreaCatalogo?tipo=Gelateria");	
		return;
	}
	%>
	<div class="container">
  	<%
  				for(ProdottoBean prodotto : ListaProdotti)
  				{
  					%>
		<div class="card">
			<a href="CreaCatalogo?action=details&id=<%=prodotto.getId()%>"><img class="image" src="./immagini/<%= prodotto.getPathImage().get(0)  %>"></a>
			<div class="dettagli-card">
				<div class="nome"><%= prodotto.getNome() %></div>
				<p>&euro; <%= String.format("%.02f", prodotto.getPrezzo())%></p>
				<a href="GestioneCarrello?action=aggiungi&id=<%=prodotto.getId()%>">Aggiungi al carrello</a>
			</div>
		</div>
		
		

	<%}%>
	</div>

</body>
</html>