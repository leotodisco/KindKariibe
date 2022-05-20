<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.*" import = "beans.*" import = "model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Completa Acquisto</title>
</head>
<body>
<jsp:include page="header.jsp" />

<h1>Riepilogo acquisto</h1>

<%HttpSession sessione = request.getSession();
  UserBean utente= (UserBean)sessione.getAttribute("Utente"); 
  if(utente==null){%>
  <h1> non sei registrato</h1>
  	
  <%}
  else{
	Carrello cart= (Carrello) sessione.getAttribute("Carrello");
	if(cart == null){ %>
	
		<p>Carrello vuoto </p>
		
  <%}
	else{
		for(ProdottoBean prodotto : cart.getProducts().keySet())
			{%>
		
			<h4><%= prodotto.getNome() %>    X <%= cart.getProducts().get(prodotto) %></h4>
			
		<%}%>
	<%}%>
	<h1>Seleziona il tuo metodo di pagamento</h1>
<%} %>

</body>
</html>