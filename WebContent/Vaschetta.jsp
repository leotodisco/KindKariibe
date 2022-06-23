<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*" import="beans.*"
	import="model.*"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Vaschetta</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- Google Fonts -->
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap"
      rel="stylesheet"
    />
    <!-- Stylesheet -->
    <link rel="stylesheet" href="Vaschette.css" />
  </head>
  <body>
  <%  
		UserBean utente = (UserBean) request.getSession(true).getAttribute("utente");
	 %>
    <div class="wrapper">
      <div class="container">
        <div class="scoop1"></div>
        <div class="scoop2"></div>
        <div class="scoop3"></div>
        <div class="bowl">
          <div class="base"></div>
        </div>
      </div>

      <div class="btns">
        <button id="selezione1">500g</button>
        <button id="selezione2">750g</button>
        <button id="selezione3">1000g</button>
      </div>
      <p>seleziona i tuoi gusti</p>
      	<% ArrayList<GustoBean> elencoGusti = (ArrayList<GustoBean>)request.getAttribute("gusto");
	%>
     	  	<%
     	  	for(GustoBean gusto : elencoGusti)
  				{
  					%>
      <div class="btns">
        <button id="btn-scoop1">Cioccolato</button>
      </div>
    </div>
    <%}%>
    <!-- Script -->


    
  </body>
</html>