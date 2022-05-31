<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "beans.*"   %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="stili.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
       <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
  <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

</head>

<body>     
<% 
UserBean utente = (UserBean) request.getSession(true).getAttribute("utente");
%>
<% if (utente == null) {
					utente = new UserBean();
					utente.setNome("ospite");
				}
				%>
				
	<script>
        $(document).ready(function () {
            $('.sidebarBtn').click(function () {
                $('.sidebar').toggleClass('active')
                $('.sidebarBtn').toggleClass('sposta')
         
            })
        })
    </script>

    <div class="sidebar" id="ciao">
        <ul>
        	<li><a href="home.jsp" class="testoResponsive">Home</a></li>
            <li><a href="Catalogo.jsp" class="testoResponsive">Pasticceria</a></li>
            <li><a href="Catalogo-Gelateria.jsp" class="testoResponsive">Gelateria</a></li>
            <li><a href="#" class="testoResponsive">Storia</a></li>
            <li><a href="#" class="testoResponsive">Contatti</a></li>
        </ul>
    </div>
    
   
       	<button class="sidebarBtn">
            <span></span>
        </button>		
				
				<!-- QUI INIZIA LA PARTE NORMALE -->
        <nav class="nav-container">
            <div class="nav-container-item-sx">
            <div class="dot-logo">
                <span style="z-index:100;" class="testoLogo-header"><a href="home.jsp">K</a></span>
                
                <div class="dotArancione-logo" style="z-index: -100;">
                    <span class="testoLogo-header"><a href="home.jsp">K</a></span>
                </div>
            </div>
        </div>
        

            <ul class="elenco nav-container-item">
                <li><a href="Catalogo-Gelateria.jsp">Gelateria</a></li>
                <li><a href="Catalogo.jsp">Pasticceria</a></li>
                <li>Storia</li>
                <li>Contatti</li>
            </ul>

            <div class="nav-container-item-dx">
                <span class="text-user">Ciao,  <%= utente.getNome() %> </span>
             <ion-icon name="cart-outline" style="font-size: 1.75rem; color= "#2f2f2f""></ion-icon> 
          </div>
        </nav>

</body>



</html>