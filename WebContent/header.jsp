<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "beans.*"   %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="stili.css">
    
    <script>
        function animazione() {
            var element = document.getElementById("hamburger");
            element.classList.toggle("open");
          }
    </script>
</head>

<body>     
<% 	HttpSession sessione= request.getSession();
	
	UserBean utente = (UserBean) sessione.getAttribute("utente");%>
<% if (utente == null) {
					utente = new UserBean();
					utente.setNome("ospite");
				}
				%>
        <nav class="nav-container">
            <div class="nav-container-item-sx">
                <img src="" alt="logo"> 
            </div>

            <ul class="elenco nav-container-item">
                <li>Gelateria</li>
                <li>Pasticceria</li>
                <li>Storia</li>
                <li>Contatti</li>
            </ul>

            <div class="nav-container-item-dx">
                <span class="text-user">Ciao,  <%= utente.getNome() %> </span>
            <img src="" alt="cart" class="cart">
          </div>
        </nav>

</body>



</html>