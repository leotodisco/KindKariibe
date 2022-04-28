<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.util.*" import = "beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
  				
  				for(ProdottoBean prodotto : ListaProdotti)
  				{
  				%>
  					<tr>
  					<td><img src = "<%= prodotto.getPathImage().get(0) %>"usemap = #dettagli></td>
  					<td><%= prodotto.getNome() %></td>
  					<td><%= prodotto.getPrezzo() %></td>
  					<td><%= prodotto.getDescrizione() %></td>
  					</tr>
  					
  					
  				<%}%>
  			
  		
  			
  			
</table> 

	<map name="dettagli">
	<area shape="default" alt="dettagli"  href="DettagliProdotto.jsp">

	</map>	

</body>
</html>