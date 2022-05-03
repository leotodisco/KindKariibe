<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login form</title>
</head>
<body>
<form action="LoginServlet" method="post"> 
<fieldset>
     <legend>Login</legend>
     <label for="email">email</label>
     <input id="email" type="text" name="email" placeholder="enter email"> 
     <br>   
     <label for="password">Password</label>
     <input id="password" type="password" name="password" placeholder="enter password"> 
     <br>
     <input type="submit" value="Login"/>
     <input type="reset" value="Reset"/>
</fieldset>
</form> 



</body>
<jsp:include page="footer.jsp" />
</html>
