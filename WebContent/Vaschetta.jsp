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
  
 <jsp:include page="header.jsp"/>
    
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
  		<form action="CreaVaschetta" method="get" id="primo">
         	
         	
         	<% ArrayList<GustoBean> ListaGusti = (ArrayList<GustoBean>) request.getAttribute("gusti");
			if(ListaGusti==null){
				response.sendRedirect("./CreaVaschetta?operazione=mostraGusti");
				return;
			}
	%>
         <select id="gusto1" class="minimal">
     
     	  	<%
     	  	
     	  	for(GustoBean gusto : ListaGusti)
  				{
  				%>

    <option value="<%= gusto.getNome()%>"><%= gusto.getNome()%></option>
    
    <%}%>
  </select>
  </form>
      
    
    <form action="CreaVaschetta" method="get" id="secondo" style="display:none;">
  	<% ArrayList<GustoBean> elencoGusti = (ArrayList<GustoBean>) request.getAttribute("gusti");
			if(elencoGusti==null){
				response.sendRedirect("./CreaVaschetta?operazione=mostraGusti");
				return;
			}
	%>
         <select id="gusto2" class="minimal">
     
     	  	<%
     	  	
     	  	for(GustoBean gusto : elencoGusti)
  				{
  				%>
     
    <option value="<%= gusto.getNome()%>"><%= gusto.getNome()%></option>
    
    <%}%>
  </select>
  
  <select id="gusto2" class="minimal">
     
     	  	<%
     	  	
     	  	for(GustoBean gusto : elencoGusti)
  				{
  				%>
      
    <option value="<%= gusto.getNome()%>"><%= gusto.getNome()%></option>
    
    <%}%>
  </select>
    </form>
    
        <form action="CreaVaschetta" method="get" id="terzo" style="display:none;")>
  	<% ArrayList<GustoBean> Gusti = (ArrayList<GustoBean>) request.getAttribute("gusti");
			if(Gusti==null){
				response.sendRedirect("./CreaVaschetta?operazione=mostraGusti");
				return;
			}
	%>
         <select id="gusto2" class="minimal">
     
     	  	<%
     	  	
     	  	for(GustoBean gusto : Gusti)
  				{
  				%>
    <option value="<%= gusto.getNome()%>"><%= gusto.getNome()%></option>
   
    <%}%>
  </select>
  
  <select id="gusto2" class="minimal">
     
     	  	<%
     	  	
     	  	for(GustoBean gusto : Gusti)
  				{
  				%>
     
    <option value="<%= gusto.getNome()%>"><%= gusto.getNome()%></option>
      
    
    <%}%>
  </select>
  
  <select id="gusto2" class="minimal">
     
     	  	<%
     	  	
     	  	for(GustoBean gusto : Gusti)
  				{
  				%>
      
    <option value="<%= gusto.getNome()%>"><%= gusto.getNome()%></option>
    <%}%>
  </select>
  </form>
    
    
    
    <input type="hidden" value="inserire" name="operazione">
    <input type="submit" value="Crea">
    
    <!-- Script -->
    <script>
    
$("#selezione1").click(function(){
            $(".scoop1").hide();
            $(".scoop2").hide();
            $("#primo").show();
            $("#secondo").hide();
            $("#terzo").hide();
            });

        $("#selezione2").click(function(){
            $(".scoop1").hide();
            var primo = $(".scoop2");
            primo.show();
            $("#primo").hide();
            $("#secondo").show();
            $("#terzo").hide();
            
        });
        $("#selezione3").click(function(){
            
        	$(".scoop1").show();
            $(".scoop2").show();
            $(".scoop3").show();
            $("#primo").hide();
            $("#secondo").hide();
            $("#terzo").show();
            
        });
        

</script>
    
  </body>
</html>