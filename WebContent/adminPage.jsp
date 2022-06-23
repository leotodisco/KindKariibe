<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import = "java.util.*" import = "beans.*" import = "model.*" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="admin.css">
    <meta charset="UTF-8">
    <title>Admin</title>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<% ArrayList<ProdottoBean> ListaProdotti = (ArrayList<ProdottoBean>) request.getAttribute("prodotti");
	if(ListaProdotti == null) {
		response.sendRedirect("./CreaCatalogo");	
		return;
	}
	
	ArrayList<GustoBean> ListaGusti = (ArrayList<GustoBean>) request.getSession().getAttribute("gusti");
	if(ListaGusti == null) {
		response.sendRedirect("./CreaCatalogo");	
		return;
	
	
	}

	ArrayList<OrdineBean> ListaOrdini = (ArrayList<OrdineBean>) request.getSession().getAttribute("ordini");
	if(ListaOrdini == null) {
		response.sendRedirect("./CreaCatalogo");	
		return;
	
	
	}
	
	ArrayList<CategoriaBean> ListaCategoria = (ArrayList<CategoriaBean>) request.getSession().getAttribute("Categorie");
	if(ListaCategoria == null) {
		response.sendRedirect("./CreaCatalogo");	
		return;
	
	
	}

	%>
	
<div id=title>
        <h2 id="titolo">Prodotti in vendita</h2>
    </div>
    <table>
        <tr>
        <th>Immagine</th>
        <th>Prodotto</th>
        <th>Categoria</th>
        <th>Tipo</th>
        <th>Prezzo</th>
        <th>Quantita Residua</th>
        <th>IVA</th>
        <th>Elimina</th>
        </tr>

  <%
  				for(ProdottoBean prodotto : ListaProdotti)
  				{
  				%>
  				
  					<tr>
  					<td><a href="CreaCatalogo?action=details&id=<%=prodotto.getId()%>">
  					<img class="immagine" src  = "./immagini/<%=prodotto.getPathImage().get(0)%>"  alt = "immagine"> </a> </td>
  					<td><%= prodotto.getNome() %></td>
  					<td><%= prodotto.getCategoria().toString()%></td>
  					<td><%= prodotto.getTipo() %></td>
  					<td>&euro; <%= String.format("%.02f", prodotto.getPrezzo())%></td>
  					<td><%=  prodotto.getQuantitaResidua().intValue() %></td>
  					<td><%= prodotto.getIVA() %>&percnt;</td>
  					<td>
  						<form action="AdminServlet" method="post" enctype="multipart/form-data">
							<input type="hidden" name="operazione" value="rimuovi">
							<input name="nome" type="hidden" value="<%= prodotto.getId() %>">
							<input type="submit" value="&times;" class="close-">
						</form>	
  					</td>
  					</tr>
  				<%}%>
 			
 			
 			
</table> 

 			            <div class="logout">
                	<form action="LogoutServlet" method="get">
						<input type="submit" value="Logout" />
					</form>
            </div>
 			

<div style="display: flex">
    <div>
        <li>Inserimento</li>
        <button id="myBtn">Inserisci un prodotto</button>
        <div id="myModal" class="modal">
            <div class="modal-content">
                <span class="close">&times;</span>

                <form action="AdminServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="operazione" value="inserire">
                    <label for="nome">Nome:</label><br> <input name="nome" type="text" maxlength="20" required
                        placeholder="inserire nome..."><br>

                    <label for="categoria">Categoria:</label><br>
                    <select name="categoria">
                        <!--CATEGORIA BEAN NEL FOR CHE MI STAMPA TUTTE LE CATEGORIE-->
                        <option value="Monoporzione">Monoporzione</option>
                        <option value="Gelato">Gelato</option>
						<option value="Babà">Babà</option>
						<option value="Piccola pasticceria">Piccola pasticceria</option>
						<option value="Torta">Torta</option>
                    </select>
                    <br>

                    <label for="tipo">Tipo:</label><br>
                    <select name="tipo">
                        <option value="Pasticceria" id = "Pasticceria">Pasticceria</option>
                        <option value="Vaschetta" id = "Vaschetta">Vaschetta</option>
                    </select>
                    <br>

					
                    <label for="descrizione">Descrizione:</label><br>
                    <textarea name="descrizione" maxlength="100" rows="3" required
                        placeholder="inserire descrizione..."></textarea><br>

                    <label for="immagine">Immagine:</label><br>
                    <input type="file" name="image" size="35"><br>

                    <br> <label for="prezzo">Prezzo:</label><br> <input name="prezzo" type="number" min="0" value="0"
                        required><br>

                    <br> <label for="IVA">IVA:</label><br> <input name="IVA" type="number" min="0" value="10"
                        required><br>
                        
                    <label for="peso" class = "vaschetta" style = "display:none">Peso:</label><br>
                    <select name="peso" class = "vaschetta" style = "display:none">
                    
                        <option value="500">500</option>
                        <option value="750">750</option>
						<option value="1000">1000</option>

					</select>

                    <label for="quantita">Quantità:</label><br> <input name="quantita" type="number" min="1" value="1"
                        required><br>

                    <label for="gusto1" class = "vaschetta" style = "display:none">Gusto 1:</label><br>
                    <select name="gusto1" class = "vaschetta" style = "display:none">
                    
				<% 	for(GustoBean g : ListaGusti)
				{ 
				%>
					 <option value="<%= g.getNome()%>"><%= g.getNome() %></option>
					
				<%}%>
				
                    </select>
                    <br>
                    
                    <label for="gusto2" class = "vaschetta" style = "display:none">Gusto 2:</label><br>
                    <select name="gusto2" class = "vaschetta" style = "display:none">
                    
				<% 	for(GustoBean g : ListaGusti)
				{ 
				%>
					 <option value="<%= g.getNome()%>"><%= g.getNome() %></option>
					
				<%}%>
				
                    </select>
                    <br>
                    
                    
                    <label for="gusto3" class = "vaschetta" style = "display:none">Gusto 3:</label><br>
                    <select name="gusto3" class = "vaschetta" style = "display:none">
                    
				<% 	for(GustoBean g : ListaGusti)
				{ 
				%>
					 <option value="<%= g.getNome()%>"><%= g.getNome() %></option>
					
				<%}%>
				
                    </select>
                    <br>


                    <input type="submit" value="Aggiungi"><input type="reset" value="Reset">

                </form>

            </div>
        </div>

	                    <br>
	<div>
	
	<br>
	<button id= "ordiniB">Mostra Ordini</button><br>
	<h2 class = "ordini" style = "display:none">Lista Ordini</h2>
				<% 	for(OrdineBean O : ListaOrdini)
				{ 
				%>
					<h5 class = "ordini" style = "display:none">Ordine numero <%= O.getIdOrdine() %></h5>	
				<%}%>	
	
	</div><br>
	<button id= "CategorieB">Mostra Categorie</button>
	<div id = "divCategorie" style = "display:none">
	<h2>Lista Categorie</h2>
	<% 	for(CategoriaBean C : ListaCategoria) { %>
			<div class="singola-categoria">
					<h5 class = "CategorieD"><%=C.getNome() %></h5>
  					<form action="AdminServlet" method="post" enctype="multipart/form-data" class = "CategorieD">
						<input type="hidden" name="operazione" value="rimuoviC">
						<input name="nome" type="hidden" value="<%= C.getNome()%>">
						<input type="submit" value="&times;" class="close-">
					</form>	
						
					<button class = "nomeCategoria" class = "CategorieD">modifica nome</button>
				 	<form action="AdminServlet" method = "post" class = "formModificaN" style = "display:none" enctype="multipart/form-data" class = "CategorieD"> 
					    <label for="valore">nome : </label><br> 
						<input name="valore" type="text" maxlength="20" required placeholder="inserire nome..."><br>
                       	<input type = "hidden" value="ModificaCategoria" name = "operazione">
	                    <input type = "hidden" value="nome" name = "attributo">
	                    <input type = "hidden" value="<%=C.getNome() %>" name = "nome">
	                    <input type="submit" value="modifica">
				 	</form>
					 	
					<h5 class = "CategorieD"><%=C.getDescrizione() %></h5><button class = "descrizioneCategoria" class = "CategorieD">modifica descrizione</button>
				 	<form action="AdminServlet" method = "post" class = "formModificaD" style = "display:none" enctype="multipart/form-data" > 
					    <label for="valore">Descrizione : </label><br> 
			    			<input name="valore" type="text" maxlength="20" required placeholder="inserire desrizione..."><br>
	                        <input type = "hidden" value="ModificaCategoria" name = "operazione">
	                        <input type = "hidden" value="descrizione" name = "attributo">
	                        <input type = "hidden" value="<%=C.getNome() %>" name = "nome">
	                        <input type="submit" value="modifica">
					 </form>
					 </div>
					 <br>
		<%}%>
	</div>
		
	<div>
		<br>
		<button id = "bottoneCategoria">Aggiungi Categoria</button>
		<form action="AdminServlet" method = "post" enctype="multipart/form-data" id = "categoriaForm" style = "display:none">
			<label for="Categoria">Categoria: </label><br>
			<label for="nome">Nome: </label><br> 
			<input name="nome" type="text" maxlength="20" required placeholder="inserire nome..."><br>                    
		   	<label for="descrizione">Descrizione: </label><br> 
			<input name="descrizione" type="text" maxlength="20" required placeholder="inserire desrizione..."><br>
		    <input type="submit" value="Aggiungi">
		    <input type="hidden" name="operazione" value="aggiungiCategoria">
		</form>
		</div>
		<br>
		<button id= "GustiB">Mostra Gusti</button>
	<div id = "divGusti" style = "display:none">
	<h2>Lista Gusti</h2>
				<% 	for(GustoBean G : ListaGusti)
				{ 
				%>
				<div class="singolo-gusto">
					<div class="nomeGusto">
						<h5 class = "GustiD"><%=G.getNome() %></h5>
  						<form action="AdminServlet" method="post" enctype="multipart/form-data" class = "GustiD">
							<input type="hidden" name="operazione" value="rimuoviG">
							<input name="nome" type="hidden" value="<%= G.getNome()%>">
							<input type="submit" value="&times;" class="close-">
						</form>	
					</div>
						
						
					<button id="bottone-modificaNome" class = "nomeGusto GustiD">modifica nome</button>
					<br><br>
					 	<form action="AdminServlet" id="nomeGusto1" method = "post" class = "formModificaNG" style = "display:none" enctype="multipart/form-data" class = "GustoD"> 
						    <label for="valore">nome : </label><br> 
							<input name="valore" type="text" maxlength="20" required placeholder="inserire nome..."><br>
	                       	<input type = "hidden" value="ModificaGusto" name = "operazione">
	                        <input type = "hidden" value="nome" name = "attributo">
	                        <input type = "hidden" value="<%=G.getNome() %>" name = "nome">
	                        <input type="submit" value="modifica">
						 
					 	</form>
					 	
					 	
					 	<h5 class = "GustiD"><%=G.getDescrizione() %></h5><button class = "descrizioneGusto" class = "GustoD">modifica descrizione</button>
					 	<form action="AdminServlet" method = "post" class = "formModificaDG" style = "display:none" enctype="multipart/form-data" > 
					    <label for="valore">Descrizione : </label><br> 
							<input name="valore" type="text" maxlength="20" required placeholder="inserire desrizione..."><br>
                        	<input type = "hidden" value="ModificaGusto" name = "operazione">
                        	<input type = "hidden" value="descrizione" name = "attributo">
                        	<input type = "hidden" value="<%=G.getNome() %>" name = "nome">
                        	<input type="submit" value="modifica">
					 	</form>
					 	
					 	<h5 class = "GustiD"><%=G.getColore() %></h5><button class = "ColoreGusto" class = "GustoD">modifica Colore</button>
					 	<form action="AdminServlet" method = "post" class = "formModificaCG" style = "display:none" enctype="multipart/form-data" > 
					    	<label for="valore">Colore : </label><br> 
							<input name="valore" type="text" maxlength="20" required placeholder="inserire desrizione..."><br>
                        	<input type = "hidden" value="ModificaGusto" name = "operazione">
                        	<input type = "hidden" value="Colore" name = "attributo">
                        	<input type = "hidden" value="<%=G.getNome() %>" name = "nome">
                        	<input type="submit" value="modifica">
					 
					 	</form>
					 
					 	<h5 class = "GustiD"><%=G.getquantitaInMagazzino() %></h5><button class = "QuantitaGusto" class = "GustoD">modifica quantita</button>
					 	<form action="AdminServlet" method = "post" class = "formModificaQG" style = "display:none" enctype="multipart/form-data" > 
						    <label for="valore">Quantita: </label><br> 
							<input name="valore" type="number" required><br>
	                        <input type = "hidden" value="ModificaGusto" name = "operazione">
	                        <input type = "hidden" value="Quantita" name = "attributo">
	                        <input type = "hidden" value="<%=G.getNome() %>" name = "nome">
	                        <input type="submit" value="modifica">
					 
					 	</form> 
					 <hr>
					</div><!-- fine div gusto -->
					<br><br>
				<%}%>
		</div>
	
	
	<br>
	<br>
	
	<button id = "bottoneGusto">Aggiungi Gusto</button>
	
	<div id = "divAggiungiGusto" style = "display:none">
	
	<form action="AdminServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="operazione" value="aggiungiGusto">
                    <label for="nome">Nome:</label><br> <input name="nome" type="text" maxlength="30" required
                        placeholder="inserire nome...">
                        <br>

                    <label for="descrizione">Descrizione:</label><br>
                    <textarea name="descrizione" maxlength="100" rows="3" required
                        placeholder="inserire descrizione..."></textarea><br>

                    <br> <label for="quantita">quantita:</label><br> <input name="quantita" type="number" min="0"
                        required><br>
                        
                    <label for="colore">Colore:</label><br>
                    <textarea name="colore" maxlength="100" rows="3" required
                        placeholder="inserire colore..."></textarea><br>

                    <input type="submit" value="Aggiungi Gusto"><input type="reset" value="Reset">
                    <br>
                </form>
	
	</div>
	</div>
	

    <script>
    
    
    	$("#bottoneCategoria").click(function(){
    		
        	var form = $("#categoriaForm").css("display")
        	
        	if(form == "none"){
        	
        		$("#categoriaForm").show();
        		
        	}
        	else
			{
        		$("#categoriaForm").hide();
			}  
    		
    		
    	})
    	
    	$(".descrizioneCategoria").click(function(){
        	$(this).parent().children($(".formModificaD")).show();
    	})
    	
    	$(".nomeCategoria").click(function(){
        	$(this).parent().children($(".formModificaN")).show();
    	})
    	
    	
    	$("#ordiniB").click(function(){
    	   		$(".ordini").toggle();
    	})
    	
    	$("#CategorieB").click(function(){
    	   		$("#divCategorie").toggle();
    	})
    	
    	
    	//PARTE DEI GUSTI
    	
    	$("#bottoneGusto").click(function(){
    	   		$("#divAggiungiGusto").toggle();
    	})
    	
    	$("#GustiB").click(function(){
    	   		$("#divGusti").toggle();
    	})
    	
    	$(".GustiD").click(function(){
    	   	$(this).parent().children($(".formModificaNG")).show();
    	})    	

    	$(".descrizioneGusto").click(function(){
    		$(this).parent().children($(".formModificaDG")).show();
    	})
    	
    	$(".ColoreGusto").click(function(){
    		$(this).parent().children($(".formModificaCG")).show();
    	})
    	
    	$(".QuantitaGusto").click(function(){
    		$(this).parent().children($(".formModificaQG")).show();
    	})
    
        var modal = document.getElementById("myModal");
        var btn = document.getElementById("myBtn");
        var span = document.getElementsByClassName("close")[0];
        
		$("#Vaschetta").click(function(){
		
			$(".vaschetta").show();
		
		})
		
		$("#Pasticceria").click(function(){
			$(".vaschetta").hide();
		})
		
        
        btn.onclick = function () {
            modal.style.display = "block";
        }
		
        span.onclick = function () {
            modal.style.display = "none";
        }
        
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
        
        var modl = document.getElementById("myModal1");
        var btn1 = document.getElementById("myBtn1");
        var span1 = document.getElementsByClassName("close")[1];

        btn1.onclick = function () {
            modl.style.display = "block";
        }
        
        span1.onclick = function () {
            modl.style.display = "none";
        }
        window.onclick = function (event) {
            if (event.target == modl) {
                modl.style.display = "none";
            }
        }
       
    </script>
</body>
</html>