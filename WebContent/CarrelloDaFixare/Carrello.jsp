<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*" import="beans.*"
	import="model.*"%>
<!DOCTYPE html>


<head>
  <meta charset="UTF-8">
  <title>Carrello</title>

  <!--
    - custom css link
  -->
  <link rel="stylesheet" href="style.css">

  <!--
    - google font link
  -->
  <link
    href="https://fonts.googleapis.com/css?family=Source+Sans+3:200,300,regular,500,600,700,800,900,200italic,300italic,italic,500italic,600italic,700italic,800italic,900italic"
    rel="stylesheet" />
</head>

<body>
      <% HttpSession sessione = request.getSession();
	
		Carrello cart = (Carrello) sessione.getAttribute("Carrello");
		
		if(cart == null)
		{%>
		<p>Carrello vuoto</p>

	<%}
	%>

  <!--
    - main container
  -->

  <main class="container">

    <h1 class="heading">
      <ion-icon name="cart-outline"></ion-icon> Carrello
    </h1>

    <div class="item-flex">

      <!--
       - checkout section
      -->
      <section class="checkout">

        <h2 class="section-heading">Dettagli di pagamento</h2>

        <div class="payment-form">

          <div class="payment-method">

            <button class="method" onclick="myFunction2()">
              <ion-icon name="card"></ion-icon>

              <span>Carta di Credito</span>

              <ion-icon class="checkmark " name="checkmark-circle"></ion-icon>
            </button>

            <button class="method"  onclick="myFunction()">
              <ion-icon name="logo-euro"></ion-icon>

              <span>Bonifico</span>

              <ion-icon class="checkmark " name="checkmark-circle"></ion-icon>
            </button>

          </div>

          <form action="#" id="prova">

            <div class="cardholder-name">
              <label for="cardholder-name" class="label-default">Nome Proprietario</label>
              <input type="text" name="cardholder-name" id="cardholder-name" class="input-default">
            </div>

            <div class="card-number">
              <label for="card-number" class="label-default">Numero Di Carta</label>
              <input type="number" name="card-number" id="card-number" class="input-default">
            </div>

            <div class="input-flex">

              <div class="expire-date">
                <label for="expire-date" class="label-default">Data Scadenza</label>

                <div class="input-flex">

                  <input type="number" name="day" id="expire-date" placeholder="31" min="1" max="31"
                    class="input-default">
                  /
                  <input type="number" name="month" id="expire-date" placeholder="12" min="1" max="12"
                    class="input-default">

                </div>
              </div>

              <div class="cvv">
                <label for="cvv" class="label-default">CVV</label>
                <input type="number" name="cvv" id="cvv" class="input-default">
              </div>

            </div>


          </form>
          <form action="#" id="bonific">

            <div class="cardholder-name">
              <label for="cardholder-name" class="label-default">Rapporto di addebito </label>
              <input type="text" name="cardholder-name" id="cardholder-name" class="input-default">
            </div>

            <div class="cardholder-name">
              <label for="cardholder-name" class="label-default">Eseguito da</label>
              <input type="text" name="card-number" id="card-number" class="input-default">
            </div>

            <div class="cardholder-name">
              <label for="cardholder-name" class="label-default">IBAN</label>
              <input type="text" name="card-number" id="card-number" class="input-default">
            </div>


            <div class="cvv">
              <label for="cvv" class="label-default">Importo</label>
              <input type="number" name="cvv" id="cvv" class="input-default">
            </div>

            <div class="cardholder-name">
              <label for="cardholder-name" class="label-default">Causale</label>
              <input type="text" name="card-number" id="card-number" class="input-default">
            </div>

            <div class="input-flex">

              <div class="expire-date">
                <label for="expire-date" class="label-default">Data di esecuzione</label>

                <div class="input-flex">

                  <input type="number" name="day" id="expire-date" placeholder="31" min="1" max="31"
                    class="input-default">
                  /
                  <input type="number" name="month" id="expire-date" placeholder="12" min="1" max="12"
                    class="input-default">

                </div>
              </div>

              <div class="cvv">
                <label for="cvv" class="label-default">CVV</label>
                <input type="number" name="cvv" id="cvv" class="input-default">
              </div>

            </div>

          </form>

        </div>

        

        <button class="btn btn-primary">
          <b>Sgancia</b> € <span id="payAmount">2.15</span>
        </button>

      </section>


      <!--
        - cart section
      -->

      <%
		
		
			for(ProdottoBean prodotto : cart.getProducts().keySet())
			{%>
      <section class="cart">

        <div class="cart-item-box">

          <h2 class="section-heading">I Tuoi Ordini</h2>

          <div class="product-card">

            <div class="card">

              <div class="img-box">
              	<img class="product-img" width="80px" src="./immagini/<%= prodotto.getPathImage().get(0)%>">
              </div>

              <div class="detail">

                <h4 class="product-name"><%=prodotto.getNome()%></h4>

                <div class="wrapper">

                  <div class="product-qty">
                    <button id="decrement">
                      <ion-icon name="remove-outline"></ion-icon>
                    </button>

                    <span id="quantity">1</span>

                    <button id="increment">
                      <ion-icon name="add-outline"></ion-icon>
                    </button>
                  </div>

                  <div class="price">
                    € <span id="price"><%=prodotto.getPrezzo()%></span>
                  </div>

                </div>

              </div>

              <button class="product-close-btn">
                <ion-icon name="close-outline"></ion-icon>
                <a href="GestioneCarrello?action=rimuovi&id=<%=prodotto.getNome()%>">Rimuovi</a>
              </button>

            </div>

          </div>


        <div class="wrapper">

          <div class="discount-token">

            <label for="discount-token" class="label-default">Codice Sconto</label>

            <div class="wrapper-flex">

              <input type="text" name="discount-token" id="discount-token" class="input-default">

              <button class="btn btn-outline">Applica</button>

            </div>

          </div>
          
	<%
		}
	%>

          <div class="amount">

            <div class="subtotal">
              <span>Subtotale</span> <span>€ <span id="subtotal">2.05</span></span>
            </div>

            <div class="tax">
              <span>IVA</span> <span>€ <span id="tax">0.10</span></span>
            </div>

            <div class="shipping">
              <span>Spedizione</span> <span>€ <span id="shipping">0.00</span></span>
            </div>

            <div class="total">
              <span>Totale</span> <span>€ <span id="total">2.15</span></span>
            </div>

          </div>

        </div>

      </section>

    </div>

  </main>






  <!--
    - custom js link
  -->
  <script src="./script.js"></script>

  <!--
    - ionicon link
  -->
  <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
  <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

</body>

</html>