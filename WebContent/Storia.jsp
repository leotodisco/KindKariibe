<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        
        <title>Chi siamo</title>
       
        <link rel="stylesheet" href="Storia.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </head>
    <body>
    	<jsp:include page="header.jsp" />
	<%  UserBean utente = (UserBean) request.getSession().getAttribute("utente");
	
		if(utente != null && utente.getAdmin())
		{
		%>
		<jsp:forward page="adminPage.jsp"/>
	<% } %>
    	
        <section>
            <div class = "image">
               <img src="./immagini/castelmorrone.jpg" id="citta" class="image" style="margin-left:10px;">
               <img src="./immagini/pasticceria.jpeg" id="foto" class="image" style="display:none;">
               <img src="./immagini/coni.jpeg" id="gelateria" class="image" style="display:none;">
            </div>
         
         
            <div class = "content">
                <h2 class="intestazione">Chi siamo</h2>
                <span><!-- line here --></span>
                <p id="prima">Una storia che ha origine prima della Seconda Guerra Mondiale una storia raccontata da ogni 
                    singola prelibatezza della pasticceria KindKaribe.
                    Nella nascente Caserta degli anni '30, i nonni Gentile avevano un importante e 
                    rinomato panificio in cui vigeva una regola fondamentale: l'utilizzo del lievito madre. 
                    Sessant'anni e due generazioni dopo, la stessa regola vive ancora nei lieviti della pasticceria KindKaribe, 
                    oggi capitanata da Marco con l'ausilio della moglie Ester e il figlio Arturo.</p>
                <p id="seconda" style="display:none;"> La pasticceria KindKaribe offre ogni giorno una vastissima selezione di prodotti tipici napoletani, fatti con passione e 
                    sapienza. Delizie in cui perdersi tra la morbidezza di una pastiera, la croccantezza di una calda sfogliatella, 
                    fino ad inebriarsi del profumo del rum di un babà appena bagnato. 
                    La pasticceria KindKaribe prepara torte dal design innovativo pronte a regalare un'emozione sia visiva che gustativa.
                </p>
                <p id="terza" style="display:none;">  KindKaribe non è solo pasticceria. Gustosi gelati, assolutamente artigianali, 
                    sono preparati con cura utilizzando solo ingredienti freschi e naturali. 
                    Una caffetteria di alta professionalità accompagna ogni piccolo peccato di gola e 
                    piacevoli momenti da concedersi da soli o in compagnia.</p>
                    
                <ul class = "links">
                    <li id="work">Storia</li>
                    <div class = "vertical-line"></div>
                    <li id="service">Pasticceria</li>
                    <div class = "vertical-line"></div>
                    <li id="contact">Gelateria</li>
                </ul>
            </div>
        </section><br><br>
  <script>   
  
     $('#work').click(function(){
		   $('#prima').show();
		   $("#seconda").hide();
		   $("#terza").hide();
		   $("#foto").hide();
		   $("#citta").show();
		   $("#gelateria").hide();
		   $(this).css("border-color","green");
		   $(this).css("color","green");
		   $('#service').css("border-color","#2f2f2f");
		   $('#service').css("color","#2f2f2f");
		   $('#contact').css("border-color","#2f2f2f");
		   $('#contact').css("color","#2f2f2f");
		   $('.content').children(".intestazione").empty();
		   $('.content').children(".intestazione").append($(this).text());
	});
     
	$('#service').click(function(){
		   $('#seconda').show();
		   $("#prima").hide();
		   $("#terza").hide();
		   $("#foto").show();
		   $("#citta").hide();
		   $("#gelateria").hide();
		   $(this).css("border-color","green");
		   $(this).css("color","green");
		   $('#work').css("border-color","#2f2f2f");
		   $('#work').css("color","#2f2f2f");
		   $('#contact').css("border-color","#2f2f2f");
		   $('#contact').css("color","#2f2f2f");
		   $('.content').children(".intestazione").empty();
		   $('.content').children(".intestazione").append($(this).text());
		   
	});    

	$('#contact').click(function(){
	   $('#terza').show();
	   $("#prima").hide();
	   $("#seconda").hide();
	   $(this).css("border-color","green");
	   $(this).css("color","green");
	   $('#work').css("border-color","#2f2f2f");
	   $('#work').css("color","#2f2f2f");
	   $('#service').css("border-color","#2f2f2f");
	   $('#service').css("color","#2f2f2f");
	   $('.content').children(".intestazione").empty();
	   $('.content').children(".intestazione").append($(this).text());
	   $("#foto").hide();
	   $("#citta").hide();
	   $("#gelateria").show();
	});
	
  </script>
  
<jsp:include page="footer.jsp" />
</body>
</html>
    