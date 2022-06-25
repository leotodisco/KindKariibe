<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.*" import = "beans.*" import = "model.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin Ordini</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="admin.css">
    <link rel="stylesheet" href="PaginaUtente.css">
</head>

<body>
<script>

	function mostraElemento(data){
		if(data.length > 0) {
			data.forEach(e => { //se dà errore è perchè eclipse non conosce javascript
				let stato;
			
				if(e.dataArrivo == null)
					stato = "in transito";
				else
					stato = "consegnato";
				
				var myvar = '<div class="ordine">'+
		                			'<span>'+
	               						 '<ion-icon name="cart"></ion-icon>'+
	            					'</span>'+    
						            '<div class="container-ordine">'+
						                '<div class="cols">'+
						                	'<span><b>Codice Ordine:</b>'+ e.idOrdine+'</span>'+
						                    '<span><b>Ordine del:</b>'+ e.dataEvasione+'</span>'+
						                     '<span><b>Stato:</b>'+stato+'</span>'+
						                    '<span><b>Totale:</b> &euro;'+e.costoTotale+'</span>'+
						                	'</div>'+
						                	'<div class="row">'+
						                	//gestire i prodotti e le immagini
						                	'</div>'+
						            '</div>' +
						        '</div>';
				
				$("#prova").append(myvar);
			})
		}
	}

	function ricercaAjax(codFiscale) {
		return $.ajax({
			url : "ProdottiAPI",
			type : 'GET',
			async : true,
			cache : false,
			timeout : 30000,
			dataType : "json",
			data : {
				action : "getOrdini",
				utente : codFiscale
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
	
	function RICERCA(){
		$("#prova").empty(); //se non svuotassi all'inizio finirei per avere un elenco di prodotti subito
		var res = ricercaAjax($("#valoreRicerca").val());
	
	}

</script>

	<%UserBean utente = (UserBean) request.getSession(true).getAttribute("utente");
		
			if(utente == null || !utente.getAdmin())
			{
			%>
			<jsp:forward page="login-form.jsp"/>
		    <%}%>
	
	<input type="hidden" name="operazione" value="Mostra Ordini">
	<%
	 	    ArrayList<OrdineBean> ListaOrdini = (ArrayList<OrdineBean>) request.getSession().getAttribute("ordini");
			if(ListaOrdini == null) {
				response.sendRedirect("./CreaCatalogo");	
				return;	
			}
	%>
	
	<div class="centra-testo">
		<h1 class="intestazione">Gestione Ordini</h1>
	</div>
		
	<button class="uname4">
		<a href="adminPage.jsp" style="text-decoration:none; color: #2f2f2f">Home</a>
	</button>
	 <br><br>
	
		<input type="text" id="valoreRicerca">
		<button onclick="RICERCA();">Cerca</button>
	
		<div id="prova" > <!-- è la div in cui saranno mostrati gli ordini -->
		
		</div>
		
	
	

	
	

	

</body>
</html>