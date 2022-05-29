package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

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
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(action.equals("checkEmail")) {
			System.out.println(request.getParameter("email"));
			UserDAO dao = new UserDAO();
			try {
				UserBean u = dao.doRetriveByEmail(request.getParameter("email"));
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

		UserBean user = (UserBean) request.getSession().getAttribute("user");
		if(user == null) {
			response.setStatus(401);
			//send error pagina 404
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
