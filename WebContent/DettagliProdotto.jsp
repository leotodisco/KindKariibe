<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import = "beans.*" import = "java.util.*" import="java.nio.*" import="java.nio.charset.StandardCharsets" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<link href = "paginaDettaglio.css" rel = "stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<% ProdottoBean bean = (ProdottoBean) request.getAttribute("prodotto"); 
	List<ProdottoBean> elenco = (List<ProdottoBean>) request.getAttribute("prodottiConsigliati");
	
	if(elenco == null)
	{
		response.sendRedirect("./CreaCatalogo?azioni=details");	
		return;
	}
	
	List<RecensioneBean> elencoRecensioni = (List<RecensioneBean>) request.getAttribute("recensioni");
	if(elencoRecensioni == null)
	{
		response.sendRedirect("./CreaCatalogo?azioni=details");	
		return;
	}
	
	UserBean utente = (UserBean) request.getSession(true).getAttribute("utente"); 
	
%>

<title><%= bean.getNome()%></title>
</head>
<body>
<jsp:include page="header.jsp" />

<p class = "product-title-nascosto"><%= bean.getNome() %></p>
         <p class = "categoria-nascosto"><%= bean.getTipo()%></p>
	      <div class = "container">
	      
            <div class ="item">
             <img class="image" src="./immagini/<%= bean.getPathImage().get(0)  %>">
            </div>
            <div class="item-descrizione">
                <p class = "product-title"><%= bean.getNome() %></p>
                <p class = "categoria"><%= bean.getTipo()%></p>
                <p class = "votoMedio" id="rating"><%= request.getAttribute("votoMedio")%></p>
                <p class = "descrizione"><%= bean.getDescrizione()%></p>
                <p class = "product-title">&euro; <%= String.format("%.02f", bean.getPrezzo()) %></p>
                <p class = "container-bottone">
                <button class="aggiungi"><a href="./GestioneCarrello?action=aggiungi&id=<%=bean.getId()%>">Aggiungi al carrello</a></button>
                </p>
            </div>
        </div>
		
        <br><br>
		<div class="intestazione">
        	<span class="prodotti-consigliati-header">Prodotti consigliati</span>
        </div>
        <div class="consigliati">
        <%
        	for(ProdottoBean p : elenco){
        		 %>
            <div class = "item-consigliato">
                <a class="cons" href="CreaCatalogo?action=details&id=<%= p.getId()%>" style="text-decoration:none"><img src="./immagini/<%= p.getPathImage().get(0)  %>" alt = "elemento consigliato" class ="immagine-item-consigliato"></a>
                <span style="text-align:center"><a class="cons" href="CreaCatalogo?action=details&id=<%= p.getId()%>" style="text-decoration:none"><%= p.getNome() %></a></span>
            </div>
        <%
        	}
            %>
            
             </div>
        <br><br>
        <div class="intestazione">
        	<span class="prodotti-consigliati-header">Lascia una recensione</span>
        </div>
        
        <div class="recensioni" id="parte-recensione">
        	<form action="RecensioneServlet" method="get" class="form-recensione">
        		<input type="hidden" name="prodotto" value="<%=bean.getId()%>">
         
        		<!-- DIV VOTO -->
        		<div class="rate">
        			<input type="radio" id="star5" name="voto" value="5" required />
        			<label for="star5" title="text">5 stars</label>
        			<input type="radio" id="star4" name="voto" value="4" required/>
        			<label for="star4" title="text">4 stars</label>
        			<input type="radio" id="star3" name="voto" value="3" required/>
        			<label for="star3" title="text">3 stars</label>
        			<input type="radio" id="star2" name="voto" value="2" required/>
        			<label for="star2" title="text">2 stars</label>
        			<input type="radio" id="star1" name="voto" value="1" required/>
        			<label for="star1" title="text">1 star</label>
    			</div>
        		<!-- DOV -->
        		<br><br>
        		<textarea rows="5" cols="60" name="testo" placeholder=" inserisci una recensione..." id="rex"></textarea>
        		<input type="hidden" name="action" value="recensisci">
        		<br><br>
        		<div style="text-align:center">
        			<input type="submit" value="Recensisci" id="tasto-recensione" style="display:none">
        		</div>
        	</form>
        </div>
        
        
		<div class="intestazione">        
        	<span class="prodotti-consigliati-header">Recensioni</span>
        	<br>
        </div>
        
        <section id="testimonials">
<%for(RecensioneBean r : elencoRecensioni) {%>
    <!--testimonials-box-container------>
    <div class="testimonial-box-container">
        <!--BOX-1-------------->
        <div class="testimonial-box">
            <!--top------------------------->
            <div class="box-top">
                <!--profile----->
                <div class="profile">
                    <!--img---->
                    <div class="profile-img">
                    
                        <img src="https://cdn3.iconfinder.com/data/icons/avatars-15/64/_Ninja-2-512.png" />
                    </div>
                    <!--name-and-username-->
                    <div class="name-user">
                        <span class="nomeUtente" style="color: #2f2f2f; font-size: 1.2rem;"><%= r.getUtente().getNome() %></span>
                    </div>
                </div>
                <!--reviews------>
                <div class="reviews">
                    <span class="votoMedio"><%= r.getVoto() %></span>
                    <!--Empty star-->
                </div>
            </div>
            <!--Comments---------------------------------------->
            <div class="client-comment">
                <p><%= r.getTesto() %></p>
            </div>
        </div>
    </div>
    	<%}%>
</section>
        
        
        
       
        
        	
  
            <script>
            $(document).ready(function () {
            	//fare ajax che controlla se è stato acquistato il prodotto
            	//se è stato acquistato mostra il pulsante recensisci
            	//se l'user non ha acquistato non mostra il pulsante
            	function ControllaRecensione(prodotto) {
					return $.ajax({
						url : "RecensioneServlet",
						type : 'GET',
						async : false,
						cache : false,
						timeout : 30000,
						dataType : "json",
						data : {
							action : "check",
							prodotto : prodotto
						},
						success : function(data) {
							return data
						},
						fail : function(msg) {
							return true;
						}
					});
				}
            	  var n = $(".votoMedio").text();
			        $(".votoMedio").empty();

			        for (let i = 0; i < n-1; i++) {
			            $(".votoMedio").append("<ion-icon name=star></ion-icon>");
			        }
			        
			        var res = ControllaRecensione(<%=bean.getId()%>);
				if (res.responseJSON.message == "acquistato") {
					$("#tasto-recensione").show();
				} else{	
					$('#rex').attr("placeholder", "  PUOI RECENSIRE SOLO SE HAI ACQUISTATO IL PRODOTTO")
				}
				
				

            });//chiusura JQUERY
            
            
            </script>
       <jsp:include page="footer.jsp"/>
</body>
</html>