<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "beans.*"   %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="./stili.css">
    
    <script>
        function animazione() {
            var element = document.getElementById("hamburger");
            element.classList.toggle("open");
          }
    </script>
</head>

<body>     
<% UserBean utente = (UserBean) request.getAttribute("utente");%>
<% String nome = (String) pageContext.getAttribute("user",PageContext.SESSION_SCOPE);  %>
   <header class = "mediaQuery">
    <div id="hamburger" class="hamburger" onclick="animazione()">
        <span class="hamburger__top-bun"></span>
        <span class="hamburger__bottom-bun"></span>
    </div>

    <nav class="menu-item2">
        <a href="" class="testo-menu">Gelateria</a>
        <a href="" class="testo-menu">Pasticceria</a>
        <a href="" class="testo-menu">Storia</a>
        <a href="" class="testo-menu">Contatti</a>
    </nav>
    </header>


        <header class = "header-container">
            <div>
            	<img src ="logo.png" alt="logo" class = "logo-header">
            </div>
            <nav class="menu-item">
                <a href="" class="testo-menu">Gelateria</a>
                <a href="" class="testo-menu">Pasticceria</a>
                <a href="" class="testo-menu">Storia</a>
                <a href="" class="testo-menu">Contatti</a>
            </nav>

				<% if (utente == null) {
					utente = new UserBean();
					utente.setNome("ospite");
				}
				%>
            <div class = "parte-destra-header">
			<span class = "item-dx">Ciao, <%= nome %>  </span>
                <img src = "carrello.png" alt="cart" class = "item-dx">
		</div>
        </header>
</body>

</html>