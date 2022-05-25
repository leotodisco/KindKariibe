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

<%HttpSession sessione = request.getSession(true);
  UserBean utente= (UserBean)sessione.getAttribute("utente"); 
  if(utente==null){%>
  <jsp:forward page="login-form.jsp"></jsp:forward>
  	
  <%}
  else{
	Carrello cart= (Carrello) sessione.getAttribute("Carrello");
	if(cart == null){ %>
	
	<jsp:forward page="carrello.jsp"></jsp:forward>
		
  <%}
	else{		//vado ad elencare tutti i prodotti all'interno del carrello
		for(ProdottoBean prodotto : cart.getProducts().keySet()){%>
		
			<h4><%= prodotto.getNome() %>    X <%= cart.getProducts().get(prodotto) %></h4>
			
		<%}%>
	<%}%>
	<h1>Seleziona il tuo metodo di pagamento</h1>
	
	<form action="AcquistaServlet?action=completa">
	
	<%ArrayList<MetodoPagamentoBean> metodiPagamento= utente.getElencoMetodiPagamento();
	if(metodiPagamento.isEmpty()){%>
		<h2>non hai nessuno metodo di pagamento</h2>
	<%}
	else{%>
			<%Iterator<MetodoPagamentoBean> it= metodiPagamento.iterator();
			  while(it.hasNext()){ 
			  	MetodoPagamentoBean pagamento=it.next();%>
				  <input type="radio"  value="<%=pagamento.getIdMetodoPagamento()%>" name="idMetodo"required>
				  					<%=pagamento.getCircuito()+"  "+pagamento.getNumeroCarta().subSequence(13,15)%>
			  <%}
			  %>
			  <h1>Scegli il tuo indirizzo di spedizione</h1>
			  
	<%} 
	ArrayList<IndirizzoBean> indirizzi= utente.getIndirizziSpedizione();
	if(indirizzi.isEmpty()){%>
		<h2>Non hai nessun indirizzo di spedizione</h2>
	<%	
	}else{%>
		<%Iterator<IndirizzoBean> it= indirizzi.iterator();   
	  	while(it.hasNext()){ 
	  		IndirizzoBean indirizzo=it.next();%>
		  	<input type="radio" value="<%=indirizzo.getId()%>" name="idIndirizzo" required>
		  					<%= indirizzo.getVia()+"  "+ indirizzo.getCitta()%>
	 	 <%}	
	}
	
	CorriereDAO corriereDao= new CorriereDAO();
	Collection<CorriereBean> corrieri= corriereDao.doRetrieveAll("disc");
	if(corrieri.isEmpty()){%>
		<h1>Non ci sono corrieri</h1>
	<% 
	}else{
		Iterator<CorriereBean> it= corrieri.iterator();
		while(it.hasNext()){
			CorriereBean corriere= it.next();%>
			<input type="radio" value="<%= corriere.getId() %>" name="corriere" required>
			
			<%
		}
	}
	%>
	<br>
	<input type="submit">
	</form>
<%} %>

</body>
</html>