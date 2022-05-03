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

	<h1 align = "center"><%= bean.getNome() %></h1>
	
	 <table>
  <tr>
    <th>Prodotto</th>
    <th>Nome</th>
    <th>Prezzo</th>
    <th>IVA</th>
    <th>Descrizione</th>
    <th>QuantitàDisponibile</th>
    <th>Gusti</th>
  </tr>
    					<tr>
  					<td><img src = "<%=bean.getPathImage().get(0)%>"></td>
  					<td><%= bean.getNome() %></td>
  					<td><%= bean.getPrezzo() %></td>
  					<td><%= bean.getIVA().intValue() %> %</td>
  					<td><%= bean.getDescrizione() %></td>
  					<td><%= bean.getQuantitaResidua().intValue() %></td>
  					<td><%for(String gusto : bean.getGusti())
  							{%>
  								<%= gusto %> <br>
  						
  						
  							<%}%> </td>
  					</tr>
  </table>
	


</body>
</html>