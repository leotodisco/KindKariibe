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
			<div class= "prova">
			<a  id ="panino" href="CreaCatalogo?action=details&id=<%= prodotto.getId()%>"><img class="image" id= "speriamo" src="./immagini/<%= prodotto.getPathImage().get(0)  %>"></a>
    <span class="text">
    <a href="GestioneCarrello?action=aggiungi&id=<%=prodotto.getId()%>">
    <ion-icon name="cart-outline" id="iconaCarrello" style="font-size: 1.75rem; color: #2f2f2f;  background-color: #fcefd4;">
    </ion-icon></a></span>
  </div>
				
			<div class="dettagli-card">
				<div class="nome"><%= prodotto.getNome() %></div>
				<p>&euro; <%= String.format("%.02f", prodotto.getPrezzo())%></p>
			</div>
		</div>
	<%}%>
	</div>
	<div style="text-align:center; margin-top: 50px;">
	<span class="uname"><a href="Vaschetta.jsp">Crea una vaschetta!</a></span>
	</div>
	<script>
	 $(document).ready(function () {
		 
        $(".prova").mouseover(function(){
         $(this).find(".text").show()
         var vari = $(this).children("#panino");
       		vari.children("#speriamo").css("opacity","0.5").css("transition", ".3s ease")
       		.css("border","3px solid #acd872");
        	
        });
        
        $(".prova").mouseout(function(){
        	$(this).find(".text").hide()
        	var vari = $(this).children("#panino");
       		vari.children("#speriamo").css("opacity","1").css("transition", ".3s ease")
       		.css("border","1px solid black");
        	
        });
		 
        
     }) 
	</script>

<jsp:include page="footer.jsp"/>
</body>
</html>