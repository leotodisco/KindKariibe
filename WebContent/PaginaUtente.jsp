<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "beans.*" import = "java.util.*"   %>
<!DOCTYPE html>
<html>
    <head>
        <title>prova</title>
        <link rel="stylesheet" href="stilelogin.css">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body style="background-color: #fcefd4;">
    <% 
UserBean utente = (UserBean) request.getSession(true).getAttribute("utente");
%>
<% if (utente == null) { %>
	<jsp:forward page="login-form.jsp"/>

<%
				}
				%>
     <jsp:include page = "header.jsp"/>
     
      	<%
    response.setHeader( "Cache-Control", "no-store, no-cache, must-revalidate");  //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", -1); //prevents caching at the proxy server
%>

    <div class="container">

        <div class="card">
            
            <div class="top-container">
               <div class ="info">
                    <div class="testi">
                        <p class="name"><%=utente.getNome()%></p>
                        <p class="mail"><%=utente.getEmail()%></p>
                    </div>
                </div>
            </div>
            
            <div class="ordini-recenti">
                <span class="text">Ordini recenti</span>
                <table>
                	<%Collection<OrdineBean> ordini=(Collection<OrdineBean>)request.getAttribute("ordini");
                	
                		if(ordini==null){
                			System.out.println("Non hai nessun ordine");
                		}
                		else{
                			for(OrdineBean ordine: ordini){
                				System.out.println(ordine.getIdOrdine().toString());
                			}
                		}
                		%>
                </table>
            </div>
            <div class="le-tue-informazioni">
                <span class="text">Informazioni personali</span>
            </div>
            <div class="logout">
                	<form action="LogoutServlet" method="get">
						<input type="submit" value="Logout" />
					</form>
            </div>
            
        </div>
        
    </div>
</body>
    </html>

