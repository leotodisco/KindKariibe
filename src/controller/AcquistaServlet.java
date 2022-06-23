package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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
			else if(azione.equals("prova")) {
				IndirizzoDao daoAddress = new IndirizzoDao();
				IndirizzoBean beanIndirizzo;
				
				MetodoPagamentoDAO metodDAO = new MetodoPagamentoDAO();
				try {
					MetodoPagamentoBean beanz = metodDAO.doRetrieveByKey(request.getParameter("idMetodo"));
					beanIndirizzo = daoAddress.doRetrieveByKey(request.getParameter("idIndirizzo"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if(azione.equals("conferma")) {
				//creo l'oggetto datiFiscali
				
				DatiFiscaliBean datiFiscali= new DatiFiscaliBean();
				String idIndirizzo= (String)request.getParameter("idIndirizzo");
				
				
				datiFiscali.setIdIndirizzoFatturazione(Integer.valueOf(idIndirizzo));
				datiFiscali.setIdMetodoPagamento(Integer.parseInt((String)request.getParameter("idMetodo")));
				
				DatiFiscaliDAO datiFiscaliDao= new DatiFiscaliDAO();
				try {
					datiFiscaliDao.doSave(datiFiscali);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					datiFiscali= datiFiscaliDao.theLastInsert();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				OrdineBean ordine= new OrdineBean();

				ordine.setDatiFiscali(datiFiscali);
				ordine.setUtente(utente);
				Carrello cart=(Carrello)sessione.getAttribute("Carrello");
				

				//devo salvare prodotto - [quantita - iva - prezzo] 
				//[quantita - iva - prezzo] è una lista di interi
				//posizione 0 = quantita, pos 1=iva pos2 = prezzo
				
				HashMap<ProdottoBean,Integer> contenutoCarrello = cart.getProducts();			                  //setto i prodotti dell'ordine
				for(ProdottoBean prod : contenutoCarrello.keySet()) {				
					ArrayList<Double> quantitaIvaPrezzo = new ArrayList<>();
					quantitaIvaPrezzo.add(0, contenutoCarrello.get(prod).doubleValue());
					quantitaIvaPrezzo.add(1, prod.getIVA());
					quantitaIvaPrezzo.add(2, prod.getPrezzo());
					
					
					ordine.addProduct(prod, quantitaIvaPrezzo);

				}
				
						//setto i dati per la spedizione
				IndirizzoDao daoIndirizzo= new IndirizzoDao();
				
				try {
					ordine.setIndirizzoSpedizione(daoIndirizzo.doRetrieveByKey(idIndirizzo));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				CorriereDAO corriereDao= new CorriereDAO();
				
				try {
					ArrayList<CorriereBean> corrierelista =  (ArrayList<CorriereBean>) corriereDao.doRetrieveAll("idCorriere");
					CorriereBean corrierel = corrierelista.get((int)Math.floor(((Double) (Math.random() * 2))));
					ordine.setCorriere(corrierel);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ordine.setCostoTotale(cart.getCostoTotale());
				
				ordine.setCodiceSconto("");
				
				long millis = System.currentTimeMillis();  
			        
				java.sql.Date date = new java.sql.Date(millis);
				ordine.setDataEvasione(date);
				ordine.setUrlPdf("placeolder");
				
				OrdineDAO ordineDao= new OrdineDAO();
				try {
					ordineDao.doSave(ordine);
					cart = new Carrello();
					sessione.setAttribute("Carrello", cart);
					RequestDispatcher succesfull= request.getRequestDispatcher("home.jsp"); //leopoldo ha modificato
					succesfull.forward(request, response);
				} catch (SQLException e) {
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
