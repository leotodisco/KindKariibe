<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*" import="beans.*"
	import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carrello</title>
</head>
<body>

</form>
	<h1>Carrello</h1>
	<p>
		<a href="GestioneCarrello?action=svuota">svuota</a>
	</p>
	<p>
		<a href="AcquistaServlet">acquista</a>
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

</body>
</html>