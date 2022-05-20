package controller;

import java.io.IOException;
import beans.*;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AcquistaServlet
 */
@WebServlet("/AcquistaServlet")
public class AcquistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcquistaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione= request.getSession();
		UserBean utente= (UserBean)sessione.getAttribute("utente");
		
		if(utente==null) {
			RequestDispatcher fail= request.getRequestDispatcher("login-form.jsp");
			fail.forward(request, response);
		}
		else {
			Carrello cart=(Carrello)sessione.getAttribute("Carrello");
			if(cart==null) {
				RequestDispatcher fail= request.getRequestDispatcher("carrello.jsp");
				fail.forward(request, response);
			}
			else {
				RequestDispatcher succesfull= request.getRequestDispatcher("completaAcquisto.jsp");
				succesfull.forward(request, response);
			}
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
