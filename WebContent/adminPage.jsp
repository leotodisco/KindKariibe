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
        <th>Quantità Residua</th>
        <th>Descrizione</th>
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
  					<td><%= prodotto.getDescrizione() %></td>
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

        <li>Aggiornamento</li>
        <button id="myBtn1">Aggiorna</button>
        <div id="myModal1" class="modal">
            <div class="modal-content">
            <!-- QUI AGGIUNGERE ID -->
                <span class="close">&times;</span>

                <form action="AdminServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="operazione" value="aggiorna">
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
                        <option value="Pasticceria">Pasticceria</option>
                        <option value="Gelateria">Vaschetta</option>
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

                    <br> <label for="peso">Peso:</label><br> <input name="peso" type="number" min="0" value="10"
                        required><br>

                    <label for="quantita">Quantità:</label><br> <input name="quantita" type="number" min="1" value="1"
                        required><br>

                    <input type="submit" value="Aggiorna"><input type="reset" value="Reset">
                </form>

            </div>
        </div>
    </div>

    <script>
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