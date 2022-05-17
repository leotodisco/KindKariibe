<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import = "java.util.*" import = "beans.*" import = "model.*" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="admin.css">
    <meta charset="UTF-8">
    <title>Admin</title>
</head>
<body>
	<% ArrayList<ProdottoBean> ListaProdotti = (ArrayList<ProdottoBean>) request.getAttribute("prodotti");
	if(ListaProdotti == null) {
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
        </tr>

  <%
  				for(ProdottoBean prodotto : ListaProdotti)
  				{
  				%>
  					<tr>
  					<td><a href="CreaCatalogo?action=details&id=<%=prodotto.getNome()%>">
  					<img class="immagine" src  = "./immagini/<%=prodotto.getPathImage().get(0)%>"  alt = "immagine"> </a> </td>
  					<td><%= prodotto.getNome() %></td>
  					<td><%= prodotto.getCategoria().getNome() %></td>
  					<td><%= prodotto.getTipo() %></td>
  					<td>&euro; <%= String.format("%.02f", prodotto.getPrezzo())%></td>
  					<td><%=  prodotto.getQuantitaResidua().intValue() %></td>
  					<td><%= prodotto.getDescrizione() %></td>
  					<td><%= prodotto.getIVA() %>&percnt;</td>
  					</tr>
  					
  				<%}%>
 			
</table> 

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
                        <option value="volvo">Volvo</option>
                    </select>
                    <br>

                    <label for="tipo">Tipo:</label><br>
                    <select name="tipo">
                        <option value="volvo">Pasticceria</option>
                        <option value="volvo">Vaschetta</option>
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

                    <input type="submit" value="Aggiungi"><input type="reset" value="Reset">

                </form>

            </div>
        </div>

        <li>Aggiornamento</li>
        <button id="myBtn1">Aggiorna</button>
        <div id="myModal1" class="modal">
            <div class="modal-content">
                <span class="close">&times;</span>

                <form action="AdminServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="operazione" value="aggiorna">
                    <label for="nome">Nome:</label><br> <input name="nome" type="text" maxlength="20" required
                        placeholder="inserire nome..."><br>

                    <label for="categoria">Categoria:</label><br>
                    <select name="categoria">
                        <!--CATEGORIA BEAN NEL FOR CHE MI STAMPA TUTTE LE CATEGORIE-->
                        <option value="volvo">Volvo</option>
                    </select>
                    <br>

                    <label for="tipo">Tipo:</label><br>
                    <select name="tipo">
                        <option value="volvo">Pasticceria</option>
                        <option value="volvo">Vaschetta</option>
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

        var modal = document.getElementById("myModal1");
        var btn = document.getElementById("myBtn1");
        var span = document.getElementsByClassName("close")[0];

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
    </script>
</body>
</html>