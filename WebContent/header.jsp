<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "beans.*"   %>
<!DOCTYPE html>
<html>

<head>
	
	
    <link rel="stylesheet" type="text/css" href="stili.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
       <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
       
       <link rel="shortcut icon" href="./lib/favicon.ico"/>
  <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

</head>

<body>     
<% 
UserBean utente = (UserBean) request.getSession(true).getAttribute("utente");
%>
<% if (utente == null) {
					utente = new UserBean();
				}
				%>
				
<%
	
    response.setHeader( "Cache-Control", "no-store, no-cache, must-revalidate");  //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
				
	<script>
        $(document).ready(function () {
            $('.sidebarBtn').click(function () {
                $('.sidebar').toggleClass('active')
                $('.sidebarBtn').toggleClass('sposta')
            })//menu hamburger
            
            function mostraElemento(data){
            	if(data.length > 0) {
            		$("#prova").empty(); //svuoto la div perchè in questo modo si toglie quello che avevo cercato prima
            		$("#prova").show();
            		
            		data.forEach(e => {
						var myvar = '<a href="CreaCatalogo?action=details&id='+e.id+'"><div style=" width: 200px;  border-bottom: 1px solid black; right: 50px;"><h2 class="titolo-ricerca">'+e.nome+'</h2></a> ';
						var myvar2 = '<a href="CreaCatalogo?action=details&id='+e.id+'"><img class="immagine-ricerca" src="./immagini/'+e.pathImage[0]+'">'+'</a> </div><br><br>';
						
						$("#prova").append(myvar);
						$("#prova").append(myvar2);
					})
				}
            }
            
            function ricercaAjax(nomeProdotto) {
				return $.ajax({
					url : "ProdottiAPI",
					type : 'GET',
					async : false,
					cache : false,
					timeout : 30000,
					dataType : "json",
					data : {
						action : "search",
						prodotto : nomeProdotto
					},
					success : function(data) {
						mostraElemento(data);
						return data
					},
					fail : function(msg) {
						alert("negativo")
						return true;
					}
				});
			}//funzione ricercaAjax
			
			$("#valoreRicerca").keyup( function(){
            	$("#prova").empty(); //se non svuotassi all'inizio finirei per avere un elenco di prodotti subito
            	
            	var res = ricercaAjax($("#valoreRicerca").val());
   
			})
            
			$("#cerca").click(function(){
				//preferire classe css .active e quando clicchi fai toggleClass
				//valoreRicerca è il form
			
				$("#valoreRicerca").toggleClass("nascondi");
				
				$("#cerca").click(function(){
					$("#prova").empty();
					$("#prova").toggleClass("nascondi");
				})

			})
			

			
			
        })//chiusura jquery totale
        
    </script>

    <div class="sidebar" id="ciao">
        <ul>
        	<li><a href="home.jsp" class="testoResponsive">Home</a></li>
            <li><a href="Catalogo.jsp" class="testoResponsive">Pasticceria</a></li>
            <li><a href="Catalogo-Gelateria.jsp" class="testoResponsive">Gelateria</a></li>
            <li><a href="#" class="testoResponsive">Storia</a></li>
            <li><a href="#" class="testoResponsive">Contatti</a></li>
            <!-- mettere disegno omino, posso pensare di mettere sia accedi che carrello nello stesso li -->
            <li><a href="PaginaUtente.jsp" class="testoResponsive">Profilo <ion-icon name="person" style="font-size: 1.55rem; color: #2f2f2f;"></ion-icon></a></li>
            <li><a href="carrello.jsp">Carrello <ion-icon name="cart-outline" style=" align-items: center; font-size: 1.75rem; color: #2f2f2f;"></ion-icon></a></li>
            <!-- per la ricerca fare nuovo li -->
        </ul>
    </div>
    
   
       	<button class="sidebarBtn">
            <span></span>
        </button>		
				
				<!-- QUI INIZIA LA PARTE NORMALE -->
        <nav class="nav-container">
            <div class="nav-container-item-sx">
            <div class="dot-logo">
                <span style="z-index:100;" class="testoLogo-header"><a href="home.jsp">K</a></span>
                
                <div class="dotArancione-logo" style="z-index: -100;">
                    <span class="testoLogo-header"><a href="home.jsp">K</a></span>
                </div>
            </div>       
        </div>
       
            <ul class="elenco nav-container-item">
                <li><a href="Catalogo-Gelateria.jsp">Gelateria</a></li>
                <li><a href="Catalogo.jsp">Pasticceria</a></li>
                <li>Storia</li>
                <li>Contatti</li>
            </ul>
           

            <div class="nav-container-item-dx">
                            	<ion-icon name="search" id="cerca" style="font-size: 1.7rem; cursor: pointer; margin-right:5px;"></ion-icon>
                            	
                            	<!-- AGGIUSTARE -->
					<div class="container-ricerca" >
                  <input type="text" id="valoreRicerca" class="form-prodotti-ricerca nascondi" >
					<div id="prova" class="nascondi divProdottiRicerca">
						</div>
					</div>    
                
                <span class="text-user"><a href="PaginaUtenteServlet"> <%= utente.getNome()==null ? "Accedi" : "Ciao, " + utente.getNome() %> </a> </span>
	  	    		
             	<a href="carrello.jsp"><ion-icon name="cart-outline" style="font-size: 1.75rem; color: #2f2f2f;"></ion-icon></a> 
             
				
          </div>
        </nav>

</body>



</html>