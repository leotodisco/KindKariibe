<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
  <head>
    <meta charset="UTF-8">
   <!-- da modificare i font e devi anche modificare la mail sotto nel form-->
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link rel="stylesheet" href="contatti.css">
   </head>
<body>

	<jsp:include page="header.jsp" />
	
	<div style="text-align: center">
	<span class="titolo">Contattaci</span>
	</div>
<div class="centra">

  <div class="container">
    <div class="content">
      <div class="left-side">
        <div class="address details">
            <ion-icon name="ice-cream" ></ion-icon>
          <div class="topic">Indirizzo</div>
          <div class="text-one">Via Francesco Prata, 6</div>
          <div class="text-two">Castel Morrone (CE)</div>
        </div>
        <div class="phone details">
            <ion-icon name="call"></ion-icon>
          <div class="topic">Cellulare</div>
          <div class="text-one">+39 3332224433</div>
        </div>
        <div class="email details">
         <ion-icon name="mail"></ion-icon>
          <div class="topic">Email</div>
          <div class="text-one">kindkaribe@gmail.com</div>
        </div>
      </div> <!-- fine div sinistro -->
      <div class="right-side">
        
        <form action="https://formsubmit.co/leopoldo.todiscozte@gmail.com" method="POST" >
        <div class="input-box">
          <input type="text" placeholder="Inserisci il tuo nome">
        </div>
        <div class="input-box" id="email">
          <input type="text" placeholder="Inserisci la tua email">
        </div>
        <div class="input-box message-box">
            <input type="text" placeholder="Messaggio">
          
        </div>
        <div class="button">
          <input type="submit" class="button" value="Invia" >
          <input type="hidden" name="_next" value="ringraziamento.html"> <!--da vedere questo next-->
        </div>
      </form>
    </div>
    </div>
  </div>
  </div>
  
  <div style="text-align: center; margin-top: 30px;">
	<span class="testo" >Se hai un qualsiasi tipo di richiesta non esitare. Ti risponderemo nel minor tempo possibile!</span>
	</div>
  <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
  <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
  <jsp:include page="footer.jsp" />
</body>
</html>
