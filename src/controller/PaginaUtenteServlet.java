package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.*;
import model.*;

/**
 * Servlet implementation class PaginaUtenteServlet
 */
@WebServlet("/PaginaUtenteServlet")
public class PaginaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginaUtenteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione= request.getSession(true);
		
		HashMap<OrdineBean, ArrayList<String>> elenco = new HashMap<>(); 
		
		UserBean utente= (UserBean)sessione.getAttribute("utente");
		if(utente==null) {
			RequestDispatcher fail= request.getRequestDispatcher("login-form.jsp");
			fail.forward(request, response);
		}
		else {
			OrdineDAO ordineDao= new OrdineDAO();
	
			try {
				for(OrdineBean order : ordineDao.doRetriveByUtente(utente)) {
					ArrayList<String> elencoImmagini = new ArrayList<>();
					ArrayList<ProdottoBean> bufferProdotti = new ArrayList<>();
					
					
					for(ProdottoBean product : order.getProducts().keySet()) {
						bufferProdotti.add(product);
						
						for(String image : product.getPathImage()) {
							elencoImmagini.add(image);
						}
						
						elenco.put(order, elencoImmagini);
					}
				}
				
				request.setAttribute("ordini", elenco);
				//request.setAttribute("ordini", ordineDao.doRetriveByUtente(utente)); SE VOGLIO TORNARE A PRIMA BASTA
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		RequestDispatcher success= request.getRequestDispatcher("PaginaUtente.jsp");
		success.forward(request, response);
	}

}
