<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import = "beans.*" %>
<!DOCTYPE html>
<html>
<head>
<link href = "paginaDettaglio.css" rel = "stylesheet">
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<% ProdottoBean bean = (ProdottoBean) request.getAttribute("prodotto"); %>

<title>
<%= bean.getNome()%></title>
</head>
<body>

<jsp:include page="header.jsp" />

<p class = "product-title-nascosto"><%= bean.getNome() %></p>
         <p class = "categoria-nascosto"><%= bean.getTipo()%></p>
	      <div class = "container">
	      
            <div class ="item">
             <img class="image" src="./immagini/<%= bean.getPathImage().get(0)  %>">
            </div>
            <div class="item-descrizione">
                <p class = "product-title"><%= bean.getNome() %></p>
                <p class = "categoria"><%= bean.getTipo()%></p>
                <p class = "descrizione"><%= bean.getDescrizione()%></p>
                <p class = "product-title">&euro; <%= String.format("%.02f", bean.getPrezzo()) %></p>
                <p class = "container-bottone">
                <button class="aggiungi"><a href="./GestioneCarrello?action=aggiungi&id=<%=bean.getNome()%>">Aggiungi al carrello</a></button>
                </p>
            </div>
        </div>

        <div class="consigliati">
            <div class = "item-consigliato">
                <img src="" alt = "elemento consigliato" class ="immagine-item-consigliato">
                <p>testo</p>
            </div>
            
            <div class = "item-consigliato">
                <img src="" alt = "elemento consigliato" class ="immagine-item-consigliato">
                <p>testo</p>
            </div>

            <div class = "item-consigliato">
                <img src="" alt = "elemento consigliato" class ="immagine-item-consigliato">
                <p>testo</p>
            </div>

            <div class = "item-consigliato">
                <img src="" alt = "elemento consigliato" class ="immagine-item-consigliato">
                <p>testo</p>
            </div>

            <div class = "item-consigliato">
                <img src="" alt = "elemento consigliato" class ="immagine-item-consigliato">
                <p>testo</p>
            </div>
        </div>

</body>
</html>