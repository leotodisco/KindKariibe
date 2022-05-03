<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="loginCss.css">
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
        <input type="text" name="email" id="Uname" placeholder="Email">    
        <br><br>    
        <label for="password"></label>
             <input id="Pass" type="password" name="password" placeholder="enter password">             
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
