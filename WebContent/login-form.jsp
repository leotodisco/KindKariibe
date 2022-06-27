<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="loginCss.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Benvenuto!</title>
</head>
<body>  
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires", 0);
%>

    <br>
    <div class="login" id="loginDiv">
        <div>
            <h2>Accedi</h2>
        </div>
        <br>
        <form id="login" method="post" action="LoginServlet" onsubmit="funzioneConvalida(this);">

            <label for="email"></label>
            <input type="text" id="email-login" name="email" class="uname" placeholder="Email">
            <br><br>
            <label for="password"></label>
            <input class="pass" id="password-login" type="password" name="password" placeholder="Password">
            <br><br>
        		<input type="submit" value="Login" class="bottone-Schermata-Login" id="login-submitt"/>
        	<br>

        </form>
            <div class="container-bottoni">
	
                <button id="log" class="bottone-Schermata-Login">Crea Account</button>
            </div>
    </div>

 <div class="registrazione" id="registrationDiv">
        <div>
            <h2>Crea Account</h2>
        </div>
        <br>
        <form id="reg" method="post" action="RegistrazioneServlet" >
            <div id="classe1">
                <label for="Nome"></label>
                <input type="text" id="nome" name="nome" placeholder="Nome" class="uname" required><br><br>
                <label for="Cognome"></label>
                <input type="text" id="cognome" name="cognome" placeholder="Cognome" class="uname" required><br><br>
                <label for="Codice Fiscale"></label>
                <input type="text" id="codFiscale" placeholder="Codice Fiscale" name="CodiceFiscale" class="uname" required><br><br>
                <span class="messaggio-errore" id="codFiscaleAjaxError">Codice fiscale già presente nel database...</span>
                <span class="messaggio-errore" id="codFiscaleErrato">Inserire un codice corretto...</span>
                <span class="container-bottoni">
                	<button id="avanti" class="bottone-Schermata-Login">Avanti</button> <!-- id = avanti è il primo vottone -->
                </span>
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
                        <input type="text" id="input2" placeholder="Provincia (XX)" class="uname4" name="provincia" required maxlength="2"><br><br>
                        <label for="CAP"></label>
                        <input type="text" id="input2" placeholder=" CAP" class="uname4" name="CAP"  required maxlength="5"><br><br>
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
                <br>
                <span class="container-bottoni">
                	<button id="indietro1" class="bottone-Schermata-Login">Indietro</button>
                	<button id="avanti2" class="bottone-Schermata-Login">Avanti</button>
                </span>
            </div>

            <div id="classe3">
                <label for="Numero Telefono"></label>
                <input class="pass" id="nTelefono" type="text" name="nTelefono" placeholder="Numero Telefono" required maxlength="10"><br><br>
                <label for="email"></label>
                <input type="text" name="emailr" class="uname" placeholder="Email" id="email" required>
                <br><br>
                <label for="password"></label>
                <input class="pass" type="password" name="password" placeholder="Password" required>
                <span>
                    <br><br>
                    <span class="container-bottoni">
                	<!-- FARE JAVASCRIPT PER BOTTONE INDIETRO1 -->
	                	<button id="indietro2" class="bottone-Schermata-Login">Indietro</button>
    	                <button value="Registrati" class="bottone-Schermata-Login" id="last">Registrati</button>
       		        </span>
                </span>
            </div>
        </form>
    </div>

	

    <script>
    $(document).ready(function () {
        function checkLogin(email, pass) {
            return $.ajax({
                url: "Api/User",
                type: 'GET',
                async: false,
                cache: false,
                timeout: 30000,
                dataType: "json",
                data: {
                    action: "checkPassword",
                    password: pass,
                    email: email
                },
                success: function (data) {

                    return data
                },
                fail: function (msg) {
                    return true;
                }
            });
        }
        $("#login-submitt").click(function funzioneConvalida(obj) {
            var email = $("#email-login").val();
            var password = $("#password-login").val()
            var res = checkLogin(email, password);

            if (res.responseJSON.message == "taken") {
                obj.submit()
            }

            else {
                event.preventDefault();
                $("#email-login").addClass(".error");
                $("#password-login").addClass(".error");
            }
        });


        $("#log").click(function () {
            $("#registrationDiv").show();
            $("#registrationDiv").addClass('animate');
            $("#loginDiv").hide();
            $("#classe").hide();
            $("#avanti2").hide();
            $("#classe3").hide();
            $("#last").hide();
        });
        
        $("#indietro1").click(function fun() {
            $("#registrationDiv").show();
            $("#classe1").show();
            $("#classe").hide();
        });
        
        $("#indietro2").click(function fun() {
            $("#registrationDiv").show();
            $("#classe").show();
            $("#classe3").hide();
            $("#avanti2").show();
        });

        //inizia la parte del crea account
        $("#avanti").click(function fun() {
            //espressione regolare in JQUERY non richiede che ci siano '/'
            //e si usa il metodo test anzichè match
            var flag = true;
            var regExpNomeCognome = new RegExp("^[A-Za-z]+$");
            var regExpCodFiscale = new RegExp("^[A-Z]{6}[A-Z0-9]{2}[A-Z][A-Z0-9]{2}[A-Z][A-Z0-9]{3}[A-Z]$", "i"); //la i serve per essere case sensitive
            if (!regExpNomeCognome.test($('#nome').val())) {
                if (!regExpNomeCognome.test($('#cognome').val())) {
                    $('#cognome').addClass('.error');
                }
                $('#nome').addClass('.error');
                if (!regExpCodFiscale.test($('#codFiscale').val())) {
                    $('#codFiscale').toggleClass(".error");
                    $('#codFiscaleErrato').show();
                }
            } //test nome
            else if (!regExpNomeCognome.test($('#cognome').val())) {
                $('#cognome').addClass('.error');
                //far mostrare "inserire cognome corretto"
                if (!regExpCodFiscale.test($('#codFiscale').val())) {
                    $('#codFiscale').toggleClass('.error');
                    $('#codFiscaleErrato').show();
                }
            }//test cognome
            if (!regExpCodFiscale.test($("#codFiscale").val())) {
                $('#codFiscale').toggleClass('.error');
                $("#codFiscaleErrato").show();
            }
            else {
                $("#classe1").hide();
                $("#last").hide();
                $("#classe3").hide();
                $("#classe").show();
                $("#avanti2").show();
            }
            //cod fiscale check
            function checkIfCodiceFiscaleExists(codFiscale) {
                return $.ajax({
                    url: "Api/User",
                    type: 'GET',
                    async: false,
                    cache: false,
                    timeout: 30000,
                    dataType: "json",
                    data: {
                        action: "checkCodFiscale",
                        CodiceFiscale: codFiscale
                    },
                    success: function (data) {
                        return data
                    },
                    fail: function (msg) {
                        return true;
                    }
                });
            }
            var res = checkIfCodiceFiscaleExists($("#codFiscale").val());
            if (res.responseJSON.message == "taken") {
                flag = false;
                $('#codFiscale').addClass(".error");
                $('#codFiscaleAjaxError').show();
            }
            else {
                $("#codFiscale").toggleClass(".error");
                $("#codFiscaleAjaxError").hide();
            }
            if (flag == false) {
                $("#registrationDiv").show();
                $("#classe1").show();
                $("#classe").hide();
            }
        });

        $("#avanti2").click(function funct() {
            $("#classe3").show();
            $("#avanti2").hide();
            $("#classe").hide();
            $("#last").show();
        });


        function checkEmail(inputtxt) {
            var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
            
            if (inputtxt.match(mailformat)){
                return true;
            }
            
            else {
                return false;
            }
        }
        
        function checkPhonenumber(inputtxt) {

        	
        	 var phoneno = /^([0-9]{10})$/
            
            if (inputtxt.match(phoneno)){
                return true;
	           	
            }
            
            else
            	return false;
        }

        function checkIfEmailExists(email) {
            return $.ajax({
                url: "Api/User",
                type: 'GET',
                async: false,
                cache: false,
                timeout: 30000,
                dataType: "json",
                data: {
                    action: "checkEmail",
                    email: email
                },
                success: function (data) {
                    return data
                },
                fail: function (msg) {
                    return true;
                }
            });
        }

        //nTelefono
        function controllaNTelefono(numero) {
            return $.ajax({
                url: "Api/User",
                type: 'GET',
                async: false,
                cache: false,
                timeout: 30000,
                dataType: "json",
                data: {
                    action: "checkNumero",
                    nTelefono: numero
                },
                success: function (data) {
                    return data;
                },
                fail: function (msg) {
                    return true;
                }
            });
        }
        
        $("#last").click(function () {

            var valid = true;
        	
            var email = $("#email").val();
            if (checkEmail($("#email").val())==false) {
            	$("#email").addClass("error");
                valid = false;
            } 
            
  
		 
            var res1 = checkIfEmailExists(email);
	
 
            if (res1.responseJSON.message == "taken") {
            	$("#email").addClass("error");
                valid = false;
            }

     
         /*
            if (checkPhonenumber($("#nTelefono").val()) == false) {
                valid = false;
                $("#nTelefono").addClass("error");
            }
*/

			var numero = $("#nTelefono").val()

			var variabile = controllaNTelefono(numero);

            if (variabile.responseJSON.message == "taken") {
            	$("#nTelefono").addClass("error");
                valid = false;
            }

            alert(valid);
            
            if(valid == true){            	
                $("#reg").submit();
            }
            else{
            	event.preventDefault();
            }

        });
 
        
    });//fine ajax
    


</script>


</body>



</html>