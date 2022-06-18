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
  <div class="container">
    <div class="content">
      <div class="left-side">
        <div class="address details">
            <ion-icon name="ice-cream" ></ion-icon>
          <div class="topic">Indirizzo</div>
          <div class="text-one">Via Dei Piselloni 32</div>
          <div class="text-two">Caserta</div>
        </div>
        <div class="phone details">
            <ion-icon name="call"></ion-icon>
          <div class="topic">Cellulare</div>
          <div class="text-one">+39 3332224433</div>
          <div class="text-two">+39 0816789800</div>
        </div>
        <div class="email details">
         <ion-icon name="mail"></ion-icon>
          <div class="topic">Email</div>
          <div class="text-one">kindkaribe@gmail.com</div>
          <div class="text-two">info.kindkaribe@gmail.com</div>
        </div>
      </div>
      <div class="right-side">
        <div class="topic-text">Mandaci un messaggio </div>
        <p>Se hai un qualsiasi tipo di richiesta non esitare. Ti risponderemo nel minor tempo possibile.</p>
        <form action="https://formsubmit.co/pinuccioiltrapanatore28@gmail.com" method="POST" >
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
  <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
  <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>
