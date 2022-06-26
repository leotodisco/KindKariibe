<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="beans.*"%>
<!DOCTYPE html>

<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="home.css">
    <link rel="icon" type="image/x-icon" href="./lib/favicon.ico" />
    
</head>

<body>
<jsp:include page="header.jsp"/>

	<%  UserBean utente = (UserBean) request.getSession().getAttribute("utente");
	
		if(utente != null && utente.getAdmin())
		{
		%>
		<jsp:forward page="adminPage.jsp"/>
	<% } %>

    <div class="center">
        <div class="dot">
            <div class="dotArancione">
            </div>
        </div>
        <span class="spanHome" id="testo">KindKaribe</span>
        <hr class="wave-sx" >
        <h4>dal 1980</h4>
    </div>
    
    <div style="display:flex; flex-direction: row">
	    <div class="parteSinistraHome">
	    	<div style="text-align:center">
		    	<span class="testo-home-pagina2">Fresco, Nutriente</span>
		    	<br>
		    	<span class="testo-home-pagina2">Kilometro 0</span>
		    	
			    <p class="paragrafo">
				    Una storia che ha origine prima della Seconda Guerra Mondiale; una storia raccontata da ogni 
					singola prelibatezza della pasticceria KindKaribe.
					Nella nascente Caserta degli anni '30, i nonni Gentile avevano un importante e 
					rinomato panificio in cui vigeva una regola fondamentale: l'utilizzo del lievito madre. 
					Sessant'anni e due generazioni dopo, la stessa regola vive ancora nei lieviti della pasticceria KindKaribe, 
					oggi capitanata da Marco con l'ausilio della moglie Ester e il figlio Arturo.
				</p>
			</div>
		</div> <!-- fine parte sx gome -->
		
		<div class="parteDestraHome">
            <div class="dotVerde"><span class="scritta-pallina">Solo Prodotti Italiani</span></div>            
        	<div class="dotRosa"><span class="scritta-pallina">100% <br>Latte Fresco</span></div>
        </div>
	</div>
	
	<br><br><br><br><br><br><br><br>
	
	<div class="parteFinaleHome">
		<button class="uname"><a href="Catalogo.jsp">Scopri</a></button>
	</div>
	
	<jsp:include page="footer.jsp"/>
</body>

</html>