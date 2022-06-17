<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*" import="beans.*"
	import="model.*"%>
<!DOCTYPE html>
<html>

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta charset="UTF-8">
  <title>Carrello</title>
  <link rel="stylesheet" href="CarrelloStile.css">
</head>

<body>
  <% HttpSession sessione=request.getSession(true); UserBean utente=(UserBean) sessione.getAttribute("utente");
  		if(utente==null){ %>
  		<jsp:forward page="login-form.jsp"/>
  		
  	<%		
  		}
  %>
 <jsp:include page = "header.jsp"/>

<!-- CONTROLLARE QUESTA COSA DOMANI -->
		<%Carrello cart=(Carrello) sessione.getAttribute("Carrello"); 
			if(cart == null){
				cart = new Carrello();
		}
			%>
 
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
 
    <main class="container">
    <div class="parte-alta">
      <h1 class="heading">
        <ion-icon name="cart-outline"></ion-icon> Carrello
      </h1>
      </div>
      
      <div class="item-flex">
        <section class="checkout">
          <h2 class="section-heading" >Indirizzo di Spedizione</h2>
          <form action="AcquistaServlet" method="get" >
          <input type="hidden" name="action" value="conferma">
          <div class="container-indirizzo">
          
              <div class="payment-method" >
            <% for(IndirizzoBean u : utente.getIndirizziSpedizione()){ %>
                <!--<ion-icon class="checkmark " name="checkmark-circle"></ion-icon>-->
                <span class="method indirizzo-">
                <input type="radio" name="idIndirizzo" value="<%=u.getId()%>" style="display:none;">
                  <!--INDICA UN INDIRIZZO-->
                  <ion-icon name="home"></ion-icon>
                  <%= "Via: "+u.getVia() +" "+u.getnCivico()+" "+"Città: "+u.getCitta()+" "+u.getCAP()+" "+u.getProvincia()%>
                </span>
              <%} %>
              </div>

          </div>

          <h2 class="section-heading">Dettagli di pagamento</h2>
                  <div class="payment-form">
                  
            <div class="payment-method">
              <span class="method" id="bottone-carta-credito">
                <ion-icon name="card"></ion-icon>
                <span>Carta di Credito</span>
                <ion-icon class="checkmark" id="checkmark-carta" name="checkmark-circle"></ion-icon>
              </span>

              <span class="method" id="bottone-bonifico">
              <!-- QUI INSERIRE UN CHECKBOX PER DIRE CHE E UN BONIFICO  -->
                <ion-icon name="logo-euro"></ion-icon>
                <span>Pagamento in Contrassegno</span>
                <input type="hidden" name="tipo" value="bonifico" class="identifier"> 
                <ion-icon class="checkmark" id="checkmark-bonifico" name="checkmark-circle"></ion-icon>
              </span>

            </div>
            
         <% 
              	ArrayList<MetodoPagamentoBean> elenco = new ArrayList<>(utente.getElencoMetodiPagamento());
				ArrayList<MetodoPagamentoBean> result = new ArrayList<>();
				for(MetodoPagamentoBean metodo : elenco){
    				if(metodo.getTipo().equals("Carta")){
        			result.add(metodo);
    				}
				} 
		%>
	
	<div id="container-metodi-pagamento" style="display:none;">
    	  <div class="payment-method">
	<%
	for(MetodoPagamentoBean m : result){	
	%>	
                <!--<ion-icon class="checkmark " name="checkmark-circle"></ion-icon>-->
                 <!-- capire per quale motivo mi passa sempre solo il primo elemento che compare -->
                <span class="method-1" >
                 <input type="radio" name="idMetodo" value="<%=m.getidMetodoPagamento()%>" class="identifier" style="display:none;">
                    <span><ion-icon name="card"></ion-icon><ion-icon class="checkmark" name="checkmark-circle" style="display:none;"></ion-icon></span>
                  	<span><b>Numero:</b> <%= "XXXX XXXX XXXX " + m.getNumeroCarta().substring(m.getNumeroCarta().length()-4) %></span>
                  	<span><b>Intestatario:</b> <%= m.getNomeIntestatario()%></span>
                </span>
              
     <%} %>
              </div>  
    </div>
    					
                            <input type="submit" value="Conferma Pagamento" class="bottone-inserisci" id="tasto-conferma-pagamento">
                        
              </form>
 
            <!--CI VUOLE METODO POST (?)-->
            
          </div>
          
          <button class="btn btn-primary" style="display: none;" id="bottone-conferma">
            <b>Conferma</b> € <span id="payAmount"><%=cart.getCostoTotale() %></span>
          </button>

			<span style="display:flex">
            	<button class="method" id="inserimento-carta" style="margin-bottom:25px; display:none;">
					<h2 id="scritta-per-inserire-metodo">Inserisci una nuova carta</h2>
				</button>
				<h5 id="chiudi" style="margin-left: 10%; margin-top:6.7px; cursor:pointer; display:none;">&#10006;</h5>
			</span>
				
            <form action="servlet per aggiungere roba" method="get" id="form-carta">
              <div class="cardholder-name">
                <label for="cardholder-name" class="label-default">Nome Proprietario</label>
                <input type="text" name="cardholder-name" id="cardholder-name" class="input-default" placeholder="  Mario Rossi" required>
              </div>
              <div class="card-number">
                <label for="card-number" class="label-default">Numero Di Carta</label>
                <input type="number" name="card-number" id="card-number" class="input-default" placeholder="  1111 1111 1111 1111" required>
              </div>
              <div class="input-flex">
                <div class="expire-date">
                  <label for="expire-date" class="label-default">Data Scadenza</label>
                  <div class="input-flex">
                    <input type="number" name="day" id="expire-date" placeholder="31" min="1" max="31"
                      class="input-default" required>
                    /
                    <input type="number" name="month" id="expire-date" placeholder="12" min="1" max="12"
                      class="input-default" required>
                  </div>
                </div>
              </div>
              <br>
              <div class="cvv">
                  <label for="cvv" class="label-default">CVV</label>
                  <input type="number" name="cvv" id="cvv" class="input-default" placeholder="  XXX" required>
                </div>
              <br><br>
              
              <input type="submit" value="Inserisci Carta" class="bottone-inserisci">
    
            </form>
</section>

        <section class="cart">
              <div style=" text-align:center; margin-top:20px; padding: 0; margin-bottom:0;">
<h2 class="section-heading">Prodotti</h2>
</div>
          <%  for(ProdottoBean prodotto : cart.getProducts().keySet()) {%>
             
              <div class="cart-item-box">
                <div class="product-card">
                  <div class="card">
                    <div class="img-box">
                      <img class="product-img" width="80px" src="./immagini/<%= prodotto.getPathImage().get(0)%>">
                    </div>
                    <div class="detail">
                      <h4 class="product-name">
                        <%=prodotto.getNome()%>
                      </h4>
                      <h4 class="product-id" style="display:none">
                        <%=prodotto.getId()%>
                      </h4>
                      <div class="wrapper">

                        <div class="product-qty">
                          <!--parte in cui in java si fa incrementare e decrementare-->
                          <button class="diminuisci"><ion-icon name="remove-outline"></ion-icon>
                          </button>
                          <span id="quantity" class="quantita"><%=cart.getProducts().get(prodotto)%></span>

                          <button id="increment" class="aumenta">
                          <ion-icon name="add-outline"></ion-icon>
                          </button>
                          
                        </div> <!-- div quantita -->

                        <div class="price">
                          € <span id="price">
                            <!--variabile java a cui si somma il prezzo per fare il totale-->
                            <%=prodotto.getPrezzo()%>
                          </span>
                        </div>
                      </div>
                    </div>
                    <button class="product-close-btn">
                      &#10006;
                    </button>
                  </div>
                </div>
           
              </div>
              
              <%}  %>
                <div class="wrapper">
                  <div class="discount-token">
                    <label for="discount-token" class="label-default">Codice Sconto</label>
                    <div class="wrapper-flex">
                      <input type="text" name="discount-token" id="discount-token" class="input-default">
                      <button class="btn btn-outline">Applica</button>
                    </div>
                  </div>

                  <div class="amount">
                    <div class="tax">
                      <span>IVA</span> <span>€ <span id="tax-span"><%= String.format("%,.2f", cart.getTax()) %></span></span>
                    </div>
                    <div class="shipping">
                      <span>Spedizione</span> <span>€ <span id="shipping">0.00</span></span>
                    </div>
                    <div class="total">
                      <span>Totale</span> <span>€ <span id="total"><%=String.format("%,.2f",cart.getCostoTotale() + cart.getTax()) %></span></span>
                    </div>
                  </div>
                </div>
        </section>
      </div>
    </main>

<script>
$(document).ready(function () {
	
    $('#bottone-carta-credito').click(function () {
    	$("#bottone-conferma").hide();//tasto conferma
    	$("#bottone-carta-credito").css("border", "2px solid green");
    	$("#bottone-bonifico").css("border", "1px solid #2f2f2f");
    	$("#checkmark-carta").show();
    	$("#checkmark-bonifico").hide();
    	$("#container-metodi-pagamento").show();
    	$("#inserimento-carta").show();
    	 	$('#inserimento-carta').click(function () {
    			 $("#form-carta").show();
    			 $("#chiudi").show();
    			 
    			 $('#chiudi').click(function () {
    				 $("#chiudi").hide();
    				 $("#form-carta").hide();
    			 })
    			 
    	 	})
    	 })
    
    $('#bottone-bonifico').click(function () {
    	$("#bottone-bonifico").css("border", "2px solid green");
    	$("#bottone-carta-credito").css("border", "1px solid #2f2f2f");
    	$("#checkmark-bonifico").show();
    	$("#checkmark-carta").hide();
    	$("#bonific").show();
    	$("#form-carta").hide();
    	$("#inserimento-carta").hide();
    	$("#container-metodi-pagamento").hide();
    })
    
    $(".method-1").click(function(){
    	$(".method-1").css("border", "1px solid #2f2f2f")
    	$(this).css("border", "2px solid green")    	
    })
    
        	
    $("input[type=radio][name=idMetodo]").parent().on("click", function() {
    	   $(this).find("input[type=radio][name=idMetodo]").prop("checked", true);
    });
    
    $("input[type=radio][name=tipo]").parent().on("click", function() {
 	   $(this).find("input[type=radio][name=tipo]").prop("checked", true);
 	});
    
    $("input[type=radio][name=idIndirizzo]").parent().on("click", function() {
 	   $(this).find("input[type=radio][name=idIndirizzo]").prop("checked", true);
 	});
    
    //indirizzo
     $(".indirizzo-").click(function(){
    	$(".indirizzo-").css("border", "1px solid #2f2f2f")
    	$(this).css("border", "2px solid green")
    })
    
    $("#scritta-per-inserire-metodo").click(function (){
    	$("#scritta-per-inserire-metodo").css("color", "green")
    	 $('#chiudi').click(function () {
    		 $("#scritta-per-inserire-metodo").css("color", "#2f2f2f")
    			 })
    })
    
    //ajax per incremento e decremento
    function incrementa(nome){
    	return $.ajax({
			url : "ProdottiAPI",
			type : 'GET',
			async : false,
			cache : false,
			timeout : 3000,
			dataType : "json",
			data : {
				action: "incrementa",
				prodotto: nome
			},
			success : function(data) {
				return data;

			},
			fail : function(msg) {
				
				return false;
			}
		});
    }
    
     function ottieniTasse(){
     	return $.ajax({
 			url : "ProdottiAPI",
 			type : 'GET',
 			async : false,
 			cache : false,
 			timeout : 3000,
 			dataType : "json",
 			data : {
 				action: "tasse"
 			},
 			success : function(data) {
 				return data;

 			},
 			fail : function(msg) {
 				return false;
 			}
 		});
     }
    
    $(".aumenta").click(function (){
    	var qt = $(this).parent().children(".quantita").text()
    	qt++
    	var id = $(this).parent().parent().parent().children(".product-id").text()
    	var result = incrementa(id)
		
    	
    	
		var tasse = ottieniTasse();
		$("#tax-span").empty();
		$("#tax-span").append(parseFloat(tasse.responseJSON).toFixed(2))

		$("#total").empty()
		$("#total").append(parseFloat(parseFloat(result.responseJSON) + parseFloat(tasse.responseJSON)).toFixed(2))
		
		$(this).parent().children(".quantita").empty();
		$(this).parent().children(".quantita").append(qt);
    })
    
    //fine incremento
    
    //parte del decremento
    function decrementa(nome){
     	return $.ajax({
 			url : "ProdottiAPI",
 			type : 'GET',
 			async : false,
 			cache : false,
 			timeout : 3000,
 			dataType : "json",
 			data : {
 				action: "decrementa",
 				prodotto: nome
 			},
 			success : function(data) {
 				return data;

 			},
 			fail : function(msg) {
 				
 				return false;
 			}
 		});
     }
    
    $(".diminuisci").click(function (){
     	var qt = $(this).parent().children(".quantita").text()
    	qt--
    	
    	var id = $(this).parent().parent().parent().children(".product-id").text()
    	
       	if(qt == 0){
    		$(this).parent().parent().parent().parent().hide();
    	}
     	decrementa(id);

		var tasse = ottieniTasse();

		$("#tax-span").empty();
		$("#tax-span").append(parseFloat(tasse.responseJSON).toFixed(2))

		$("#total").empty()
		$("#total").append(parseFloat(parseFloat(result.responseJSON) + parseFloat(tasse.responseJSON)).toFixed(2))
		
		$(this).parent().children(".quantita").empty();
		$(this).parent().children(".quantita").append(qt);
    })
    
    
    //elimina totalmente un prodotto dal catalogo
    function elimina(nome){
     	return $.ajax({
 			url : "ProdottiAPI",
 			type : 'GET',
 			async : false,
 			cache : false,
 			timeout : 3000,
 			dataType : "json",
 			data : {
 				action: "elimina",
 				prodotto: nome
 			},
 			success : function(data) {
 				return data;

 			},
 			fail : function(msg) {
 				
 				return false;
 			}
 		});
     }
   
     $(".product-close-btn").click(function (){
    	var id = $(this).parent().parent().parent().find(".product-id").text()
    	var result = elimina(id)
        $(this).parent().parent().parent().hide();

		var tasse = ottieniTasse();
		$("#tax-span").empty();
		$("#tax-span").append(parseFloat(tasse.responseJSON).toFixed(2))

		$("#total").empty()
		$("#total").append(parseFloat(parseFloat(result.responseJSON) + parseFloat(tasse.responseJSON)).toFixed(2))
		
		$(this).parent().children(".quantita").empty();
		$(this).parent().children(".quantita").append(qt);

    })
})//chiusura jquery totale
</script>
	            	
</body>

</html>