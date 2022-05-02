<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.util.*" import = "beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="ProductStyle.css" rel="stylesheet" type="text/css">
<title>Catalogo prodotti</title>
</head>
<body>
	
	<h1 align = "center">Prodotti in vendita</h1>
	
 <table>
  <tr>
    <th>Prodotto</th>
    <th>Nome</th>
    <th>Prezzo</th>
    <th>Descrizione</th>
  </tr>
  <% ArrayList<ProdottoBean> ListaProdotti = (ArrayList<ProdottoBean>) request.getAttribute("prodotti");
	if(ListaProdotti == null) {
		response.sendRedirect("./CreaCatalogo");	
		return;
	}
  				for(ProdottoBean prodotto : ListaProdotti)
  				{
  				%>
  					<tr>
  					<td><a href="CreaCatalogo?action=details&id=<%=prodotto.getNome()%>"><img src = "./images/<%= prodotto.getPathImage().get(0) %>"></a></td>
  					<td><%= prodotto.getNome() %></td>
  					<td><%= prodotto.getPrezzo() %></td>
  					<td><%= prodotto.getDescrizione() %></td>
  					</tr>
  					
  					
  				<%}%>
  			
  		
  			
  			
</table> 
	
	
	<%  UserBean utente = (UserBean) request.getAttribute("utente"); 
	
		if(utente == null)
		{
			utente = (UserBean) getServletContext().getAttribute("utente");
		}
	
		if(utente.getAdmin())
		{
			ServletContext c = getServletContext();
			c.setAttribute("utente", utente);
		%>
		
			<h2>Inserisci</h2>
	<form action="AdminServlet" method="post">
		<input type="hidden" name="operazione" value="inserire">
		 <label	for="nome">Nome:</label><br> <input name="nome" type="text"
			maxlength="20" required placeholder="inserire nome..."><br>
			
			<label for="categoria">Categoria:</label><br>
		<textarea name="categoria" maxlength="100" rows="3" 
			placeholder="inserire categoria..."></textarea>
			
				<label for="tipo">Tipo:</label><br>
		<textarea name="tipo" maxlength="100" rows="3" 
			placeholder="inserire tipo..."></textarea>

		<label for="descrizione">Descrizione:</label><br>
		<textarea name="descrizione" maxlength="100" rows="3" required
			placeholder="inserire descrizione..."></textarea>
			
				<label for="immagine">Immagine:</label><br>
		<textarea name="immagine" maxlength="100" rows="3" required
			placeholder="inserire nome immagine..."></textarea>
			
		<br> <label for="prezzo">Prezzo:</label><br> <input
			name="prezzo" type="number" min="0" value="0" required><br>

		<br> <label for="IVA">IVA:</label><br> <input
			name="IVA" type="number" min="0" value="10" required><br>
		
			<br> <label for="peso">Peso:</label><br> <input
			name="peso" type="number" min="0" value="10" required><br>
		
		<label for="quantita">Quantit�:</label><br> <input
			name="quantita" type="number" min="1" value="1" required><br>

		<input type="submit" value="Aggiungi"><input type="reset"
			value="Reset">

	</form>

	<h2>Rimuovi Elemento</h2>
	<form action="AdminServlet" method="post">
		<input type="hidden" name="operazione" value="rimuovi"> <label
			for="nome">Nome:</label><br> <input name="nome" type="text"
			maxlength="20" required placeholder="inserire nome..."><br>

		<input type="submit" value="Rimuovi"><input type="reset"
			value="Reset">

	</form>

	<h2>Aggiorna prodotto</h2>
	<form action="AdminServlet" method="post">
		<input type="hidden" name="operazione" value="aggiorna"> 
		
				 <label	for="nome">Nome:</label><br> <input name="nome" type="text"
			maxlength="20" required placeholder="inserire nome..."><br>
			
			<label for="categoria">Categoria:</label><br>
		<textarea name="categoria" maxlength="100" rows="3" 
			placeholder="inserire categoria..."></textarea>
			
				<label for="tipo">Tipo:</label><br>
		<textarea name="tipo" maxlength="100" rows="3" 
			placeholder="inserire tipo..."></textarea>

		<label for="descrizione">Descrizione:</label><br>
		<textarea name="descrizione" maxlength="100" rows="3" 
			placeholder="inserire descrizione..."></textarea>
			
				<label for="immagine">Immagine:</label><br>
		<textarea name="immagine" maxlength="100" rows="3" 
			placeholder="inserire nome immagine..."></textarea>
			
		<br> <label for="prezzo">Prezzo:</label><br> <input
			name="prezzo" type="number" min="0" value="0"><br>

		<br> <label for="IVA">IVA:</label><br> <input
			name="IVA" type="number" min="0" value="10"><br>
		
			<br> <label for="peso">Peso:</label><br> <input
			name="peso" type="number" min="0" value="10" ><br>
		
		<label for="quantita">Quantit�:</label><br> <input
			name="quantita" type="number" min="1" value="1" ><br>
			
			<input type="submit" value="Aggiorna"><input type="reset"
			value="Reset">
	</form>
		
		
		
		<% } %>
	
<form action="LogoutServlet" method="get" > 
     <input type="submit" value="Logout"/>
</form> 


</body>
</html>