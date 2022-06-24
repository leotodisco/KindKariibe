package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import model.UserDAO;

/**
 * Servlet implementation class ModificaUtente
 */
@WebServlet("/ModificaUtente")
public class ModificaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String VecchiaPassword = request.getParameter("VecchiaP");
		String valore = request.getParameter("valore");
		String attributo = request.getParameter("attributo");
		String utente = request.getParameter("utente");
		
		UserBean utenteS = (UserBean) request.getSession().getAttribute("utente");
		UserDAO uDAO = new UserDAO();
		
		if(VecchiaPassword.equals(utenteS.getPassword())) {
			
			try {
				UserDAO.SingoloUpdate(attributo, valore, utente);
				
				utenteS = uDAO.doRetrieveByKey(utenteS.getCodiceFiscale());
				request.setAttribute("utente", utenteS);
				
				response.sendRedirect("Catalogo.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
	}

	
}
