<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*" import="beans.*"
	import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="catalogo.css" rel="stylesheet" type="text/css">
<title>Catalogo prodotti</title>
</head>
<body>

	<%  UserBean utente = (UserBean) request.getSession(true).getAttribute("utente");
	
		if(utente != null && utente.getAdmin())
		{
		%>
		<jsp:forward page="adminPage.jsp"/>
	<% } %>
	<jsp:include page="header.jsp" />

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
				<a href="GestioneCarrello?action=aggiungi&id=<%=prodotto.getNome()%>">Aggiungi al carrello</a>
			</div>
		</div>
		
		

	<%}%>
	</div>





	<form action="LogoutServlet" method="get">
		<input type="submi value="Logout" />
	</form>
	<h1>Carrello</h1>
	<p>
		<a href="GestioneCarrello?action=svuota">svuota</a>
	</p>
	<p>
		<a href="GestioneCarrello?action=acquista">acquista</a>
	</p>
	<% HttpSession sessione = request.getSession();
	
		Carrello cart = (Carrello) sessione.getAttribute("Carrello");
		
		if(cart == null)
		{%>
	<p>Carrello vuoto</p>

	<%}
		else
		{
		
			for(ProdottoBean prodotto : cart.getProducts().keySet())
			{%>

	<h1><%= prodotto.getNome() %>
		X
		<%= cart.getProducts().get(prodotto) %>
		<a href="GestioneCarrello?action=rimuovi&id=<%=prodotto.getNome()%>">
			rimuovi</a>
	</h1>

	<%}
		%>


	<%}
			%>


		<a href = "login-form.jsp">Login</a>



</body>
</html>