<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="loginCss.css">
<link href="https://www.dafontfree.net/embed/dmxhZGltaXItc2NyaXB0LXJlZ3VsYXImZGF0YS8xMy92LzY1NTU2L1ZMQURJTUlSLlRURg" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login form</title>
</head>
<body>  

    <br>    
    <div class="login">  
        <div>
            <h2>Accedi</h2>
        </div>  
        <br>
    <form id="login" method="post" action="LoginServlet"> 

        <label for="email"></label> 
        <input type="text" name="email" class = "uname" placeholder="Email">    
        <br><br>    
        <label for="password"></label>
             <input class = "pass" type="password" name="password" placeholder="Password">             
        <br><br>   
        <input type="submit" value="Login" id="log"/>       
        <br><br> 
        <span>
            <a href="#">Password dimenticata</a>
        </span>        
            
    </form>     
</div>    

</body>
</html>
