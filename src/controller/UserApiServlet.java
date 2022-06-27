package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.ResponseStatusMessage;
import beans.UserBean;
import model.UserDAO;


/**
 * Servlet implementation class UserApiServlet
 */
@WebServlet("/Api/User")
public class UserApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String contentType = "application/json; charset=UTF-8";
	private String action;
	private Gson gson = new Gson();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getHeader("x-requested-with") == null) {
			response.sendError(500);
			return;
		} 
		this.action = request.getParameter("action");
		response.setContentType(contentType);		
		super.service(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		if(action.equals("checkEmail")) {
			UserDAO dao = new UserDAO();
		            try {
		                var u = dao.doRetriveByEmail(request.getParameter("email"));
		                response.setStatus(200);
		                String risposta = (u == null ? "free" : "taken");
		                response.getWriter().print(gson.toJson(new ResponseStatusMessage(200, risposta)));
		                response.getWriter().flush();
		                return;
		            } catch (SQLException e) {
		                response.setStatus(500);
		                response.getWriter().print(gson.toJson(new ResponseStatusMessage(500, "error")));
		                response.getWriter().flush();
		                return;
		            } catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		
		if(action.equals("checkCodFiscale")) {
			UserDAO dao = new UserDAO();
			try {
				UserBean u = dao.doRetrieveByKey(request.getParameter("CodiceFiscale"));
				response.setStatus(200);
				String messaggio;
				if(u.getEmail() == null) {
					messaggio = "free";

				}

				else
					messaggio = "taken";
				
				response.getWriter().print(gson.toJson(new ResponseStatusMessage(200, messaggio)));
				response.getWriter().flush();
				return;
			} catch (Exception e) {
				response.setStatus(500);

				response.getWriter().flush();
				return;
			}
		}
		
		//controlla il login 
		if(action.equals("checkPassword")) {
			UserDAO dao = new UserDAO();
			String messaggio = new String();
			try {
				UserBean usr = dao.doRetriveByEmail(request.getParameter("email"));
				ArrayList<String> emails = dao.doRetrieveEmails();
				
				if(!emails.contains(request.getParameter("email"))){
					messaggio = "free";
				}
				
				else if(usr.getPassword().equals(request.getParameter("password"))){
						messaggio = "taken";
				}

				else {
						messaggio = "free";
				}
			
				
				response.setStatus(200);
				response.getWriter().print(gson.toJson(new ResponseStatusMessage(200, messaggio)));
				response.getWriter().flush();

			}
			 catch (Exception e) {
				response.setStatus(500);

				response.getWriter().flush();

			}
		}
		
		
		if(action.equals("checkNumero")) {
			UserDAO dao = new UserDAO();
            try {
                var u = dao.doRetriveByNumero(request.getParameter("nTelefono"));
                
                String risposta = (u.getCodiceFiscale()  == null ? "free" : "taken");

                response.setStatus(200);
                response.getWriter().print(gson.toJson(new ResponseStatusMessage(200, risposta)));
                response.getWriter().flush();
                return;
            } catch (SQLException e) {
                response.setStatus(500);
                response.getWriter().print(gson.toJson(new ResponseStatusMessage(500, "error")));
                response.getWriter().flush();
                return;
            } catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		UserBean user = (UserBean) request.getSession().getAttribute("user");
		if(user == null) {
			response.setStatus(401);

			response.getWriter().flush();
			return;
		} else if ( action == null ) {
			response.setStatus(500);

			response.getWriter().flush();
			return;
		}

		if(action.equals("getAddresses")) {
			response.setStatus(200);
			response.getWriter().print(gson.toJson(user.getIndirizziSpedizione()));
			response.getWriter().flush();
			return;
		}


	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}