<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*" import="beans.*"
	import="model.*"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Vaschetta</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <link rel="stylesheet" href="Vaschette.css" />
  </head>
  <body>
  
 <jsp:include page="header.jsp"/>
 <% ArrayList<GustoBean> ListaGusti = (ArrayList<GustoBean>) request.getAttribute("gusti");	
			if(ListaGusti==null){
				response.sendRedirect("./CreaVaschetta?operazione=mostraGusti");
				return;
 }%>
 
 	<div class="intestazione">
        	<span class="titolo">Composizione Vaschetta</span>
    </div>
 
 
    <div style="display:flex; justify-content:center;">
	    <div class="wrapper"> <!-- è il rettangolo -->
	      <div class="container"> <!-- parte del rettangolo -->
	        <div class="scoop1"></div> <!-- gli scoop sono palline -->
	        <div class="scoop2"></div>
	        <div class="scoop3"></div>
	        <div class="bowl"> 
	          <div class="base"></div> <!-- è il collo della bowl -->
	        </div>
	      </div> <!-- chiusura container  -->
	     </div> <!-- chiusura wrapper -->
     </div>
      
	<div >
	      <div class="btns">
	        <button id="selezione1">500g</button>
	        <button id="selezione2">750g</button>
	        <button id="selezione3">1000g</button>
		</div>
	</div>

	<div class="container-form">
		<div class="intestazione"> 
      		<span class="testo">Seleziona Gusti</span>
      	</div>
      
      <div style="display: flex; justify-content:center;">
  		 <form action="CreaVaschetta" method="get" id="primo">
	         	<select id="gusto1" class="minimal" name="gusto1">
	     	  		<%for(GustoBean gusto : ListaGusti){%>
	    				<option value="<%= gusto.getNome()%>"><%= gusto.getNome()%></option>
	   				<%}%>
	  		 	</select>
	  			<input type="hidden" value="inserire" name="operazione">
	  			<input type="hidden" value="1" name="quantita">
	  			<input type="hidden" value="5.00" name="prezzo">
	  			<input type="hidden" value="5.00" name="IVA">
	  			<input type="hidden" value="Vaschetta custom" name="nome">
	  			<input type="hidden" value="Vaschetta dal peso di 500g, composta da un solo gusto." name="descrizione">
	  			<input type="hidden" value="Gelato" name="categoria">
	  			<input type="hidden" value="Vaschetta" name="tipo">
	  			<input type="hidden" value="500" name="peso">
	  			
	  			<br><br>
		  		<div style="text-align:center;">
		  			<input type="submit" value="Crea">
		  		</div>
	  	    </form> <!-- FINE FORM PER UN SOLO GUSTO -->
	      
	    
		    <!-- FORM PER DUE GUSTI -->
		    <form action="CreaVaschetta" method="get" id="secondo" style="display:none;">
		    	 <input type="hidden" value="inserire" name="operazione">
		         <select id="gusto2" class="minimal" name="gusto1">
		     	  	<%for(GustoBean gusto : ListaGusti){%>
		    	 		<option value="<%= gusto.getNome()%>"><%= gusto.getNome()%></option>
					<%}%>
		  		</select>
		   
		 		<select id="gusto2" name="gusto2">
		 		<%for(GustoBean gusto : ListaGusti){%>
		    		<option value="<%= gusto.getNome()%>"><%= gusto.getNome()%></option> 
		    	<%}%>
		  		</select>
		  		
		  		
	  			<input type="hidden" value="1" name="quantita">
	  			<input type="hidden" value="10.00" name="prezzo">
	  			<input type="hidden" value="5.00" name="IVA">
	  			<input type="hidden" value="Vaschetta custom" name="nome">
	  			<input type="hidden" value="Vaschetta dal peso di 500g, composta da un solo gusto." name="descrizione">
	  			<input type="hidden" value="Gelato" name="categoria">
	  			<input type="hidden" value="Vaschetta" name="tipo">
	  			<input type="hidden" value="500" name="peso">
	  			<br><br>
	  			
		  		<div style="text-align:center">
		  			<input type="submit" value="Crea">
		  		</div>
		    </form><!-- FINE FORM PER DUE GUSTI -->
		    
		    
		    <!-- FORM PER TRE GUSTI -->
		    <form action="CreaVaschetta" method="get" id="terzo" style="display:none;">
		      	<input type="hidden" value="inserire" name="operazione">
	  			<input type="hidden" value="1" name="quantita">
	  			<input type="hidden" value="15.00" name="prezzo">
	  			<input type="hidden" value="5.00" name="IVA">
	  			<input type="hidden" value="Vaschetta custom" name="nome">
	  			<input type="hidden" value="Vaschetta dal peso di 500g, composta da un solo gusto." name="descrizione">
	  			<input type="hidden" value="Gelato" name="categoria">
	  			<input type="hidden" value="Vaschetta" name="tipo">
	  			<input type="hidden" value="500" name="peso">
	  			
		  		 
		         <select id="gusto2" class="minimal" name="gusto1">
		     	 <%for(GustoBean gusto : ListaGusti){%>
		    		<option value="<%= gusto.getNome()%>"><%= gusto.getNome()%></option>
				 <%}%>
		  		</select>
		  		
		  		<select id="gusto2" class="minimal" name="gusto2">
		     	  	<%for(GustoBean gusto : ListaGusti){%>
		    			<option value="<%= gusto.getNome()%>"><%= gusto.getNome()%></option>
		    		<%}%>
		  		</select>
		  
		  		<select id="gusto2" class="minimal" name="gusto3">
		     	  	<%for(GustoBean gusto : ListaGusti){%>
		    			<option value="<%= gusto.getNome()%>"><%= gusto.getNome()%></option>
		    		<%}%>
		  		</select>
		  		<br><br>
		  		<div style="text-align:center">
		  			<input type="submit" value="Crea">
		  		</div>
		  	</form>
	  	</div>
    </div>
    
    
    
    
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
    <jsp:include page="footer.jsp"/>
  </body>
</html>