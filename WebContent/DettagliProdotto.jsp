<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import = "beans.*" %>
<!DOCTYPE html>
<html>
<head>
<link href = "paginaDettaglio.css" rel = "stylesheet">
<meta charset="utf-8">
<% ProdottoBean bean = (ProdottoBean) request.getAttribute("prodotto"); %>

<title>
<%= bean.getNome()%></title>
</head>
<body>

<jsp:include page="header.jsp" />

	      <div class = "container">
            <div class ="item">
             <img src = "./immagini/<%=bean.getPathImage().get(0)%>" alt = "immagine" class ="image">
            </div>
            <div class="item-descrizione">
                <p class = "product-title"><b><%= bean.getNome() %></b></p>
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