package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import model.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		
		UserDAO model = new UserDAO();
		UserBean User;

		try {
			User = model.doRetriveByEmail(username); //prendo il cliente con l'email
			
			if(User.getEmail() == null)
			{
				RequestDispatcher fail = request.getRequestDispatcher("LoginFailed.jsp");
				fail.include(request, response);
			}
			else
			{
				
				if(password.equals(User.getPassword()))
				{
					request.getSession().setAttribute("utente", User);
					RequestDispatcher succesfull = request.getRequestDispatcher("LoginSuccesfull.jsp");

					succesfull.forward(request, response);
				}
				else
				{
					RequestDispatcher wrong = request.getRequestDispatcher("wrongPassword.jsp");
					wrong.forward(request, response);
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
	}


}
