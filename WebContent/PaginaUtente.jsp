<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="beans.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Utente</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="PaginaUtente.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	</head>

	<body>
		<% 
			UserBean utente = (UserBean) request.getSession(true).getAttribute("utente");
			
		%>
		<% if (utente == null) { %>
			<jsp:forward page="login-form.jsp" />
		<%}
			ArrayList<MetodoPagamentoBean> elencoMetodi = utente.getElencoMetodiPagamento();
			ArrayList<IndirizzoBean> elencoIndirizzi = utente.getIndirizziSpedizione();
			HashMap<OrdineBean, ArrayList<String>> ordini=(HashMap<OrdineBean, ArrayList<String>>)request.getAttribute("ordini");
   			
			response.setHeader( "Cache-Control", "no-store, no-cache, must-revalidate");  //HTTP 1.1
			response.setHeader("Pragma","no-cache"); //HTTP 1.0
			response.setDateHeader ("Expires", -1); //prevents caching at the proxy server
                	
        	if(ordini==null){
           		ordini = new HashMap<OrdineBean, ArrayList<String>>(); //nel caso riporta a arrayList
        	}
    	%>

    <script>
        $(document).ready(function () {
            $(".carte-credito").first().hide();
            $(".carte-credito").eq(1).show();
            $("#ordini").addClass("attivo");


            $(".opzione").click(function () {
                $(".opzione").removeClass("attivo");
                $(this).addClass("attivo");
                $(".titolo").empty();
                $(".titolo").text($(this).text());
            })

            $("#carte").click(function () {
                $(".carte-credito").first().show();
                $(".carte-credito").eq(1).hide();
                $(".carte-credito").eq(2).hide();
            });

            $("#dati").click(function () {
                $(".carte-credito").first().hide();
                $(".carte-credito").hide();
                $(".carte-credito").eq(2).show();
                $("#mostraIndirizzi").show();
            });

            $("#ordini").click(function () {
                $(".carte-credito").first().hide();
                $(".carte-credito").eq(2).hide();
                $(".carte-credito").eq(1).show();
            });
            
            $("#flag").click(function () {
                $(".carte-credito").first().hide();
                $(".carte-credito").eq(1).hide();
                $("#ind").show();
                $("#flag").hide();
                $("#mostraIndirizzi").hide();
                $(".titolo").empty();
                $(".titolo").text("Inserisci Indirizzo");
            });
            
            $("#flag2").click(function () {
                $(".carte-credito").first().hide();
                $(".carte-credito").eq(1).hide();
                $("#container-form-carte").show();
                $("#flag").hide();
                $("#mostraIndirizzi").hide();
                $(".titolo").empty();
                $(".titolo").text("Inserisci nuova carta");
            });
            

            $("#chiudi-indirizzo").click(function () {
                $(".carte-credito").first().hide();
                $(".carte-credito").eq(1).hide();
                $(".carte-credito").eq(2).show();
                $("#ind").hide();
                $("#flag").show();
                $("#mostraIndirizzi").show();
                $("#chiudi-indirizzo").hide();
                $(".titolo").empty();
                $(".titolo").text("Dati Personali");
            });
            
            $("#chiudi-form").click(function () {
            	$("#mostraIndirizzi").hide();
            	$(".carte-credito").eq(2).hide();
                $(".carte-credito").first().show();
                $("#chiudi-form").hide();
                $("#ind").hide();
                $("#flag").hide();
                $("#flag2").show();                
                $("#container-form-carte").hide();
                $(".titolo").empty();
                $(".titolo").text("Dati Fiscali");
            });

        });//chiusura jquery
    </script>
    
    	<jsp:include page="header.jsp" />
    	
        <div class="intestazione">
        	<span class="titolo">Ordini Recenti</span>
    	</div>
       <!--quando vai su eclipse si sminchia perchè hai due ul li-->
    <div class="contenitore">

        <!--MENU LATERALE-->
        <div class="selezione">
            <ul class="opzioni">
                <li class="opzione" id="ordini">
                    <ion-icon name="cart" class="icona"></ion-icon>Ordini Recenti
                </li>
                <li class="opzione" id="carte">
                    <ion-icon name="card" class="icona"></ion-icon>Dati Fiscali
                </li>
                <li class="opzione" id="dati">
                    <ion-icon name="person" class="icona"></ion-icon>Dati Personali
                </li>
                <a href="LogoutServlet">
                <li class="opzione">
						<ion-icon name="log-out-outline" class="icona"></ion-icon>Logout
				</li>
				</a>
            </ul>
        </div>


        <!--SEZIONE CARTE DI CREDITO-->
        <div class="carte-credito elencoCarte" style="display: none" id="carte-utente">
        
        <% for(MetodoPagamentoBean metodo : elencoMetodi){ %>
            <span class="method-1">
                <span>
                    <ion-icon name="card"></ion-icon>
                </span>
                <span><b>Numero:</b> <%= "XXXX XXXX XXXX " + metodo.getNumeroCarta().substring(metodo.getNumeroCarta().length()-4) %></span>
                <span><b>Intestatario:</b> <%= metodo.getNomeIntestatario()  %></span>
                <span><b>Circuito:</b> <%= metodo.getCircuito() %></span>
            </span>
		<% } %>

        <div class="intestazione">
            	<span id="flag2" class="bottone-submit"><ion-icon name="add-circle-outline" class="icona"></ion-icon>Inserisci Carta</span>
            </div>

        </div> <!-- FINE SEZIONE CARTE DI CREDITO -->
        
        
        <div style="display: none" id="container-form-carte">
        	
            
            <!-- FORM CARTE DI CREDITO -->
            <div>
            <form action="AggiungiMetodoPagamento" method="get" id="form-carta">
            	<br><br>
            	<ion-icon name="close-circle-outline" style="float:right; font-size: 2.5rem; cursor: pointer;" id="chiudi-form"></ion-icon>
            	<input type="hidden" name="tipo" value="carta"> 
              	<div>
              	<br><br>
                	<label for="cardholder-name">Nome Proprietario</label><br>
                	<input type="text" name="cardholder-name" class="uname" placeholder="Mario Rossi" required>
              	</div>
              	<br>
              	<div>
                	<label for="card-number" class="label-default">Numero Di Carta</label><br>
                	<input type="number" name="card-number" id="card-number" class="uname" placeholder="1111 1111 1111 1111" required>
              	</div>
              	<br>
              	<div>
                	<div>
                		<div style="display:flex; gap:10px; align-items:center">
                  			<label for="expire-date">Data Scadenza</label><br>
                  			<label for="cvv">CVV</label><br>
                  		</div>
                  		<div style="display:flex; gap:27px;" >
                  		<span>
                    	<input type="number" name="month" id="expire-date" placeholder="11" min="1" max="12" class="uname3" required>
                    		/
                    	<input type="number" name="year" id="expire-date" placeholder="2023" min="2022" class="uname3" required>
                  		</span>
                  		<input type="number" name="cvv" class="uname3" placeholder="XXX" required>
                  		</div>
                	</div>
              </div>
              <br>
              <br><br>
              
              <input type="submit" value="Inserisci Carta" class="bottone-submit-form-carte">
    
            </form>
		</div>
        </div>
        

        <!--SEZIONE ORDINI-->
        <div class="carte-credito" style="display: none">

            <%for(OrdineBean ord : ordini.keySet()){ %>
            <div class="ordine">
                <span>
                    <ion-icon name="cart"></ion-icon>
                </span>
                
                <div class="container-ordine">
                    <div class="cols">
                        <span><b>Ordine del:</b> <%=ord.getDataEvasioneAsString() %></span>
                        <span><b>Stato:</b> <%=ord.getDataArrivo()==null ? "In transito" : "Consegnato" %></span>
                        <span><b>IVA:</b> &euro;3,80</span>
                        <span><b>Totale:</b> &euro;45,50</span>
                    </div>
                    <div class="row">
                    	<%for(ProdottoBean pro : ord.getProducts().keySet()){ %>          
                        	<img src="./immagini/<%=pro.getPathImage().get(0) %>" class="immagine-ordine-anteprima">
                        <%} %>
                    </div>
                </div> <!-- fine container ordine -->
                <div style="display:flex; justify-content: center;">
                	
                	<form action="FatturaServlet" method="get">
                    	<input type="hidden" name="ordine" value="<%=ord.getIdOrdine()%>">
                    	<input type="submit" value="Fattura">
            		</form>
            		
            	</div>
            </div><!-- fine ordine totale -->

            <!-- input type hidden -->
            <%} %>
        </div>

		<!-- SEZIONE DATI UTENTE -->
        <div class="carte-credito" style="display: none" >
        <div id="mostraIndirizzi">
        <!--SEZIONE INDIRIZZI-->
        <%for(IndirizzoBean i : elencoIndirizzi){%>
            <div class="ordine">
                <span>
                    <ion-icon name="home"></ion-icon>
                </span>
                <div class="container-ordine">
                    <div class="cols">
                        <span><b>Via:</b> <%=i.getVia()+" "+i.getnCivico()%></span>
                        <span><b>Citta: </b><%=i.getCitta()+" "+"("+i.getProvincia()+")"%></span>
                        <span><b>CAP:</b> <%=i.getCAP()%></span>
                    </div>
                </div>
            </div>
            <%}%>
            </div>

			
            <!--form inserimento indirizzo-->
            <div class="intestazione">
            	<span id="flag" class="bottone-submit"><ion-icon name="add-circle-outline" class="icona"></ion-icon>Inserisci indirizzo</span>
            </div>
            
            <div id="ind" style="display:none">
		    <form action="AggiungiIndirizzo" method="post" id="form-indirizzo">
		    <ion-icon name="close-circle-outline" style="float:right; font-size: 2.5rem; cursor: pointer;" id="chiudi-indirizzo"></ion-icon>
    			<div class="indirizzo">
                    <span class="via">
                        <label for="Via"></label>
                        <input type="text" placeholder="Via" name="via" class="uname2" required><br><br>
                        <label for="Numero Civico"></label>
                        <input type="text" placeholder="Numero" class="uname3" name="nCivico"  required><br><br>
                        <label for="Città"></label>
                        <input type="text" placeholder="Città" class="uname2" name="citta" required><br><br>
                    </span>
                    <br><br>
                    <span class="via">
                        <label for="Provincia"></label>
                        <input type="text" placeholder=" Provincia" class="uname4" name="provincia" required><br><br>
                        <label for="CAP"></label>
                        <input type="text" placeholder=" CAP" class="uname4" name="CAP"  required><br><br>
                    </span>
                </div>
                <div class="intestazione">
                	<input type="submit" value="Inserisci" class="bottone-submit">
                </div>
            </form>
            </div> <!-- fine div id=  "ind" -->
                  
	

        </div> <!-- fine parte utente -->

    </div>
    


		

	</body>
</html>

