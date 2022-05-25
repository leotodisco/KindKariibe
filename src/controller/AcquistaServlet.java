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
		String azione= request.getParameter("action");
		
		HttpSession sessione= request.getSession(true);
		UserBean utente= (UserBean)sessione.getAttribute("utente");
		if(utente==null) {
			RequestDispatcher fail= request.getRequestDispatcher("login-form.jsp");
			fail.forward(request, response);
		}
		else {
			if(azione== null) {
	
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
			else if(azione.equals("conferma")) {
				OrdineBean ordine= new OrdineBean();
			
				Carrello cart=(Carrello)sessione.getAttribute("Carrello");
				
				ordine.setProducts(cart.getProducts());			//setto i prodotti dell'ordine
				DatiFiscaliDAO daoFiscali= new DatiFiscaliDAO();		//setto i dati fiscali
				try {
					ordine.setDatiFiscali(daoFiscali.doRetrieveByKey((String)request.getAttribute("idMetodo")));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						//setto i dati per la spedizione
				IndirizzoDao daoIndirizzo= new IndirizzoDao();
				
				try {
					ordine.setIndirizzoSpedizione(daoIndirizzo.doRetrieveByKey((String)request.getAttribute("idIndirizzo")));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				CorriereDAO corriereDao= new CorriereDAO();
				
				try {
					ordine.setCorriere(corriereDao.doRetrieveByKey((String)request.getAttribute("corriere")));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
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
