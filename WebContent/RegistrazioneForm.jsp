<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form id="login" method="post" action="RegistrazioneServlet"> 

		<label for="nome"></label> 
        <input type="text" name="nome" class = "uname" placeholder="nome" required>    
        <br><br>   
		<label for="cognome"></label> 
        <input type="text" name="cognome" class = "uname" placeholder="cognome" required>    
        <br><br>
        <label for="CodiceFiscale"></label> 
        <input type="text" name="CodiceFiscale" class = "uname" placeholder="CodiceFiscale" required>    
        <br><br>    
        <label for="email"></label>
        <input class = "pass" type="email" name="email" placeholder="email" required>             
        <br><br>
        <label for="nTelefono"></label>
        <input class = "pass" type="number" name="nTelefono" placeholder="nTelefono" required>             
        <br><br>
        <label for="password"></label>
        <input class = "pass" type="password" name="password" placeholder="password" required>             
        <br><br>
        <label for="sesso"></label>
        <input class = "pass" id = "sesso" type="radio" name="sesso" value = "M">
        <label for="Maschio">Maschio</label><br>
  		<input class = "pass" type="radio" id="sesso" name="sesso" value="F">
  		<label for="Femmina">Femmina</label><br>            
        <br><br>
        <input type = "date" class = "pass" id = "data" name ="data">
        <input type="submit" value="Login" id="log"/>       
        <br><br>
        
        </form>



</body>
</html>