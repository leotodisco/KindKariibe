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
  				
  				for(ProdottoBean prodotto : ListaProdotti)
  				{
  				%>
  					<tr>
  					<td><a href="CreaCatalogo?action=details&id=<%=prodotto.getNome()%>"><img src = "<%= prodotto.getPathImage().get(0) %>"></a></td>
  					<td><%= prodotto.getNome() %></td>
  					<td><%= prodotto.getPrezzo() %></td>
  					<td><%= prodotto.getDescrizione() %></td>
  					</tr>
  					
  					
  				<%}%>
  			
  		
  			
  			
</table> 


</body>
</html>