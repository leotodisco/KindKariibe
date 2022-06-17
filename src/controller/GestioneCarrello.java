package controller;

import java.io.IOException;
import java.util.Objects;

import javax.naming.Context;
import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import com.google.gson.Gson;

import beans.OrdineBean;
import beans.ProdottoBean;
import beans.ResponseStatusMessage;
import beans.UserBean;
import model.Carrello;
import model.ProdottoDAO;

/**
 * Servlet implementation class GestioneCarrello
 */
@WebServlet("/GestioneCarrello")
public class GestioneCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestioneCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		String azione = request.getParameter("action");
		HttpSession sessione = request.getSession(true);
		Carrello cart = (Carrello) sessione.getAttribute("Carrello"); 
		UserBean utente = (UserBean) request.getSession(true).getAttribute("utente");
		
		if(utente == null)
		{
			RequestDispatcher login = request.getRequestDispatcher("login-form.jsp");
			login.forward(request, response);
		}
		
		
		
		if(azione.equals("aggiungi"))
		{
			ProdottoDAO dao = new ProdottoDAO();
			try {	
				String id = request.getParameter("id");
				ProdottoBean prodotto = dao.doRetrieveByKey(id);
				if(cart == null)
				{
					cart = new Carrello();
				}
			
				cart.addProduct(prodotto);
				sessione.setAttribute("Carrello", cart);
				
				//request.getHeader("referer") ci dice da quale url proviene la richiesta, in questo modo l'utente può
				//aggiungere più prodotti al carrello senza passare per la pagina del carrello.
				String vecchioURL=request.getHeader("referer");
				response.sendRedirect(vecchioURL);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(azione.equals("svuota"))
		{
			cart = new Carrello();
			sessione.setAttribute("Carrello", cart);
			RequestDispatcher view = request.getRequestDispatcher("Catalogo.jsp");
			view.forward(request, response);
		}
		
		else if(azione.equals("rimuovi"))
		{
			String id = request.getParameter("id");
			ProdottoDAO dao = new ProdottoDAO();
			ProdottoBean prodotto;
			
			try {
				prodotto = dao.doRetrieveByKey(id);
				cart.deleteProduct(prodotto);
				
				sessione.setAttribute("Carrello", cart);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RequestDispatcher view = request.getRequestDispatcher("carrello.jsp");
			view.forward(request, response);
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
