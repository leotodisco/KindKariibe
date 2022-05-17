<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="loginCss.css">
<link href="https://www.dafontfree.net/embed/dmxhZGltaXItc2NyaXB0LXJlZ3VsYXImZGF0YS8xMy92LzY1NTU2L1ZMQURJTUlSLlRURg" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Benvenuto!</title>
</head>
<body>  




    <br>
    <div class="login" id="loginDiv">
        <div>
            <h2>Accedi</h2>
        </div>
        <br>
        <form id="login" method="post" action="LoginServlet">

            <label for="email"></label>
            <input type="text" name="email" class="uname" placeholder="Email">
            <br><br>
            <label for="password"></label>
            <input class="pass" type="password" name="password" placeholder="Password">
            <br><br>
            <input type="submit" value="Login" class="bottone-Schermata-Login" />
            <br><br>

        </form>
            <span>
                <button id="log" class="bottone-Schermata-Login">Crea Account</button>
            </span>
    </div>

 <div class="registrazione" id="registrationDiv">
        <div>
            <h2>Crea Account</h2>
        </div>
        <br>
        <form id="reg" method="post" action="RegistrazioneServlet">
            <div id="classe1">
                <label for="Nome"></label>
                <input type="text" id="input1" name="nome" placeholder="Nome" class="uname" required><br><br>
                <label for="Cognome"></label>
                <input type="text" id="input1" name="cognome" placeholder="Cognome" class="uname" required><br><br>
                <label for="Codice Fiscale"></label>
                <input type="text" id="input1" placeholder="Codice Fiscale" name="CodiceFiscale" class="uname" required><br><br>
                <button id="avanti" class="bottone-Schermata-Login">Avanti</button>
            </div>

            <div id="classe">
                <label for="Data di nascita"></label>
                
                <input id="input2" placeholder="Data di nascita" type="text" onfocus="(this.type='date')" class="uname"
                    required name="data"><br><br>
               
                <div class="indirizzo">
                    <span class="via">
                        <label for="Via"></label>
                        <input type="text" id="input2" placeholder="Via" name="via" class="uname2" required><br><br>
                        <label for="Numero Civico"></label>
                        <input type="text" id="input2" placeholder="Numero" class="uname3" name="nCivico"  required><br><br>
                        <label for="Città"></label>
                        <input type="text" id="input2" placeholder="Città" class="uname2" name="citta" required><br><br>
                    </span>
                    <span class="via">
                        <label for="Provincia"></label>
                        <input type="text" id="input2" placeholder=" Provincia" class="uname4" name="provincia" required><br><br>
                        <label for="CAP"></label>
                        <input type="text" id="input2" placeholder=" CAP" class="uname4" name="CAP"  required><br><br>
                        <label for="Sesso"></label>
                    </span>
                </div>
                <fieldset class="sex">
                    <legend style="font-family: 'archia-regular';">Sesso: </legend>
                    <div>
                        <label for="Uomo" style="font-family: 'archia-regular';">M</label>
                        <input type="radio" id="Sesso" name="sesso" value="M">
                    </div>
                    <div>
                        <label for="Donna" style="font-family: 'archia-regular'; padding: 0; margin: 0">F</label>
                        <input type="radio" id="Sesso" name="sesso" value="F">
                    </div>
                </fieldset>
                <button id="avanti2" class="bottone-Schermata-Login">Avanti</button>
            </div>

            <div id="classe3">
                <label for="Numero Telefono"></label>
                <input class="pass" type="text" name="nTelefono" placeholder="Numero Telefono" required><br><br>
                <label for="email"></label>
                <input type="text" name="email" class="uname" placeholder="Email" required>
                <br><br>
                <label for="password"></label>
                <input class="pass" type="password" name="password" placeholder="Password" required>
                <span>
                    <br><br>
                    <input type="submit" value="Registrati" class="bottone-Schermata-Login" id="last" />
                </span>
            </div>
        </form>
    </div>



    <script>
   
        $(document).ready(function () {
            $(log).click(function () {
                $(registrationDiv).show();
                $(registrationDiv).addClass('animate');
                $(loginDiv).hide();
                $(classe).hide();
                $(avanti2).hide();
                $(classe3).hide();
                $(last).hide();
            });

            $(avanti).click(function fun() {
                $(classe1).hide();
                $(last).hide();
                $(classe3).hide();
                $(classe).show();
                $(avanti2).show();
            });

            $(avanti2).click(function funct() {
                $(classe3).show();
                $(avanti2).hide();
                $(classe).hide();
                $(last).show();
            });

        });
    </script>
</body>



</html>