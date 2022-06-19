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
	List<CategoriaBean> elencoC = (List<CategoriaBean>) request.getAttribute("categorie");
	
	
	if(elenco == null || elencoC == null)
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
                <p><%= bean.getNome() %></p><button id= "Nome">modifica</button>
                <form action="AdminServlet" method = "post" class = "nomeF" enctype="multipart/form-data" style = "display:none">
                	<label for="valore">Nome:</label><br>
                    <textarea name="valore" maxlength="100" rows="3" required
                        placeholder="inserire nome..."></textarea><br>
                        <input type="hidden" name="Attributo" value="nome">
                        <input type="hidden" name="prodotto" value="<%=bean.getId() %>">
                        <input type="hidden" name="operazione" value="aggiorna">
                        <input type="submit" value="conferma modifica">
                </form>
                <p class = "categoria"><%= bean.getTipo() %></p>
                <p class = "votoMedio" id="rating"><%= request.getAttribute("votoMedio")%></p>
                <p class = "descrizione"><%= bean.getDescrizione()%></p><button id= "descrizione">modifica</button>
                <form action="AdminServlet" method = "post" class = "descrizioneF" enctype="multipart/form-data" style = "display:none">
                	<label for="valore">Descrizione:</label><br>
                    <textarea name="valore" maxlength="100" rows="3" required
                        placeholder="inserire descrizione..."></textarea><br>
                        <input type="hidden" name="Attributo" value="descrizione">
                        <input type="hidden" name="prodotto" value="<%=bean.getId() %>">
                        <input type="hidden" name="operazione" value="aggiorna">
                        <input type="submit" value="conferma modifica">
                </form>
                <p class = "product-title">&euro; <%= String.format("%.02f", bean.getPrezzo()) %></p><button id= "prezzo">modifica</button>
                <form action="AdminServlet" method = "post" class = "prezzoF" enctype="multipart/form-data" style = "display:none">
                	<label for="valore">Prezzo:</label><br>
                    <input type = "text" name = "valore" required class = "number"><br>
                        <input type="hidden" name="Attributo" value="prezzo">
                        <input type="hidden" name="prodotto" value="<%=bean.getId() %>">
                        <input type="hidden" name="operazione" value="aggiorna">
                        <input type="submit" value="conferma modifica">
                </form>
                <p class = "categoria"><%= bean.getCategoria().toString() %></p><button id= "categoria">modifica</button>
                <form action="AdminServlet" method = "post" class = "categoriaF" enctype="multipart/form-data" style = "display:none">
                	<label for="valore">Categoria:</label><br>
                    <select name="valore">
                 
				<% 	
				for(CategoriaBean C : elencoC)
				{ 
				%>
					 <option value="<%= C.getNome() %>"><%= C.getNome() %></option>
					
				<%}%>
				
                    </select>
                        <input type="hidden" name="Attributo" value="categoria">
                        <input type="hidden" name="prodotto" value="<%=bean.getId() %>">
                        <input type="hidden" name="operazione" value="aggiorna">
                        <input type="submit" value="conferma modifica">
                </form>
                
                <p class = "product-title"> IVA :<%= String.format("%.02f", bean.getIVA()) %> &percnt;</p><button id= "IVA">modifica</button>
                <form action="AdminServlet" method = "post" class = "IVAF" enctype="multipart/form-data" style = "display:none">
                	<label for="valore">IVA:</label><br>
                    <input type = "text" name = "valore" required class = "number"><br>
                        <input type="hidden" name="Attributo" value="IVA">
                        <input type="hidden" name="prodotto" value="<%=bean.getId() %>">
                        <input type="hidden" name="operazione" value="aggiorna">
                        <input type="submit" value="conferma modifica">
                </form>
                
                <p class = "product-title"> quantita residua :<%= bean.getQuantitaResidua() %></p><button id= "quantita">modifica</button>
                <form action="AdminServlet" method = "post" class = "quantitaF" enctype="multipart/form-data" style = "display:none">
                	<label for="valore">quantita residua:</label><br>
                    <input type = "text" name = "valore" required class = "number"><br>
                        <input type="hidden" name="Attributo" value="quantitaDisponibili">
                        <input type="hidden" name="prodotto" value="<%=bean.getId() %>">
                        <input type="hidden" name="operazione" value="aggiorna">
                        <input type="submit" value="conferma modifica">
                </form>
                
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

			        for (let i = 0; i < n; i++) {
			            $(".votoMedio").append("<ion-icon name=star></ion-icon>");
			        }
			        
			        var res = ControllaRecensione(<%=bean.getId()%>);
				if (res.responseJSON.message == "acquistato") {
					$("#tasto-recensione").show();
				} else{	
					$('#rex').attr("placeholder", "  PUOI RECENSIRE SOLO SE HAI ACQUISTATO IL PRODOTTO")
				}
				
				

            });//chiusura JQUERY
            
            
            $("#Nome").click(function(){
            	
            	var form = $(".nomeF").css("display")
            	
            	if(form == "none"){
            	
            		$(".nomeF").show();
            		
            	}
            	else
				{
            		$(".nomeF").hide();
				}            		
 					
            })
            
                        
            
            $("#descrizione").click(function(){
            	
            	var form = $(".descrizioneF").css("display")
            	
            	if(form == "none"){
            	
            		$(".descrizioneF").show();
            		
            	}
            	else
				{
            		$(".descrizioneF").hide();
				}            		
 					
            })
            
                $("#prezzo").click(function(){
            	
            	var form = $(".prezzoF").css("display")
            	
            	if(form == "none"){
            	
            		$(".prezzoF").show();
            		
            	}
            	else
				{
            		$(".prezzoF").hide();
				}            		
 					
            })
            
               $("#categoria").click(function(){
            	
            	var form = $(".categoriaF").css("display")
            	
            	if(form == "none"){
            	
            		$(".categoriaF").show();
            		
            	}
            	else
				{
            		$(".categoriaF").hide();
				}            		
 					
            })
            
                $("#IVA").click(function(){
            	
            	var form = $(".IVAF").css("display")
            	
            	if(form == "none"){
            	
            		$(".IVAF").show();
            		
            	}
            	else
				{
            		$(".IVAF").hide();
				}            		
 					
            })
            
                $("#quantita").click(function(){
            	
            	var form = $(".quantitaF").css("display")
            	
            	if(form == "none"){
            	
            		$(".quantitaF").show();
            		
            	}
            	else
				{
            		$(".quantitaF").hide();
				}            		
 					
            })
            
            $("input.number").on("keydown", function (e) {
    // allow function keys and decimal separators
    if (
        // backspace, delete, tab, escape, enter, comma and .
        $.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 188, 190]) !== -1 ||
        // Ctrl/cmd+A, Ctrl/cmd+C, Ctrl/cmd+X
        ($.inArray(e.keyCode, [65, 67, 88]) !== -1 && (e.ctrlKey === true || e.metaKey === true)) ||
        // home, end, left, right
        (e.keyCode >= 35 && e.keyCode <= 39)) {
 
        
        // optional: replace commas with dots in real-time (for en-US locals)
        if (e.keyCode === 188) {
            e.preventDefault();
            $(this).val($(this).val() + ".");
        }
    	/*
        // optional: replace decimal points (num pad) and dots with commas in real-time (for EU locals)
        if (e.keyCode === 110 || e.keyCode === 190) {
            e.preventDefault();
            $(this).val($(this).val() + ",");
        }
        */
 
        return;
    }
    // block any non-number
    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
        e.preventDefault();
    }
});
            
            
            </script>
       
</body>
</html>