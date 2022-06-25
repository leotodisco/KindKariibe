package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.OrdineBean;
import beans.ProdottoBean;
import beans.ResponseStatusMessage;
import beans.UserBean;
import model.Carrello;
import model.OrdineDAO;
import model.ProdottoDAO;
import model.UserDAO;

/**
 * Servlet implementation class ProdottiAPI
 */
@WebServlet("/ProdottiAPI")
public class ProdottiAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String contentType = "application/json; charset=UTF-8";
	private String action;
	private Gson gson = new Gson();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Se è una richiesta di tipo ajax
		if(request.getHeader("x-requested-with") == null) {
			response.sendError(500);
			return;
		} 
		this.action = request.getParameter("action");
		response.setContentType(contentType);	
		super.service(request, response);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdottiAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		if(action.equals("search")) {
			ProdottoDAO dao = new ProdottoDAO();
			try {
				List<ProdottoBean> elencoProdotti = new ArrayList<>();
				//prendo tutti i prodotti il cui nome contiene il nome passato via ajax
				elencoProdotti = dao.doRetrieveAllVisibili("C.nome").stream().filter(s->s.getNome().toLowerCase().contains(request.getParameter("prodotto").toLowerCase()))
														.collect(Collectors.toList());
				response.setStatus(200);
				response.getWriter().print(gson.toJson(elencoProdotti));
				response.getWriter().flush();
				return;
			} catch (SQLException e) {
				e.printStackTrace();
				response.setStatus(500);
				response.getWriter().print(gson.toJson(new ResponseStatusMessage(500, "error")));
				response.getWriter().flush();
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//incrementa quantita
		if(action.equals("incrementa")) {
			System.out.println("ha capito che voglio?\n");
			if((String) request.getParameter("prodotto") == null)
				return;
			
			HttpSession sessione = request.getSession(true);
			//chiedi ad alessandro come minchia puoi passare qualcosa della sessione via ajax
			Carrello cart = (Carrello) sessione.getAttribute("Carrello");
			ProdottoDAO prod = new ProdottoDAO();
			ProdottoBean p;
			double result = 0;
			double totaleIVA = 0;
			try {
				p = prod.doRetrieveByNome((String) request.getParameter("prodotto"));
				cart.addProduct(p); // questo metodo controlla se un prodotto è gia presente, in tal caso incrementa solo
				result = cart.getCostoTotale();
				totaleIVA = cart.getTax();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setStatus(200);
			response.getWriter().print(gson.toJson(result));
			response.getWriter().flush();
			return;
		}
		
		if(action.equals("decrementa")) {
			System.out.println("funzione di decrementa");
			if((String) request.getParameter("prodotto") == null)
				return;
			
			HttpSession sessione = request.getSession(true);
			//chiedi ad alessandro come minchia puoi passare qualcosa della sessione via ajax
			Carrello cart = (Carrello) sessione.getAttribute("Carrello");
			
			
			ProdottoDAO prod = new ProdottoDAO();
			ProdottoBean p;
			double result = 0;
			double totaleIVA = 0;
			
			try {
				p = prod.doRetrieveByNome((String) request.getParameter("prodotto"));
				cart.deleteProduct(p); // questo metodo controlla se un prodotto è gia presente, in tal caso incrementa solo
				result = cart.getCostoTotale();
				totaleIVA = cart.getTax();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setStatus(200);
			response.getWriter().print(gson.toJson(result));
			response.getWriter().flush();
			return;
		}
		
		
		if(action.equals("tasse")) {		
			HttpSession sessione = request.getSession(true);
			//chiedi ad alessandro come minchia puoi passare qualcosa della sessione via ajax
			Carrello cart = (Carrello) sessione.getAttribute("Carrello");
			double totaleIVA = 0;
			
			try {
				totaleIVA = cart.getTax();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			response.setStatus(200);
			response.getWriter().print(gson.toJson(totaleIVA));
			response.getWriter().flush();
			return;
		}
		
		if(action.equals("elimina")) {
			System.out.println("elimina\n");
			if((String) request.getParameter("prodotto") == null)
				return;
			
			HttpSession sessione = request.getSession(true);
			
			Carrello cart = (Carrello) sessione.getAttribute("Carrello");
			ProdottoDAO prod = new ProdottoDAO();
			ProdottoBean p;
			double result = 0;
			double totaleIVA = 0;
			try {
				p = prod.doRetrieveByNome((String) request.getParameter("prodotto"));
				cart.eliminaProdotto(p); // questo metodo controlla se un prodotto è gia presente, in tal caso incrementa solo
				
				result = cart.getCostoTotale();
				totaleIVA = cart.getTax();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setStatus(200);
			response.getWriter().print(gson.toJson(result));
			response.getWriter().flush();
			return;
		}
		
		else if(action.equals("svuota"))
		{
			HttpSession sessione = request.getSession(true);
			Carrello cart = new Carrello();
			sessione.setAttribute("Carrello", cart);
			response.setStatus(200);
			response.getWriter().print(gson.toJson(cart.getCostoTotale()));
			response.getWriter().flush();
		}
		
		else if(action.equals("getOrdini")) {
			OrdineDAO daoOrdine = new OrdineDAO();
			ArrayList<OrdineBean> elenco = new ArrayList<>();
			UserBean usr = new UserBean();
			UserDAO daoUSR = new UserDAO();
			
			try {
				usr = daoUSR.doRetrieveByKey((String) request.getParameter("utente"));
				elenco = (ArrayList<OrdineBean>) daoOrdine.doRetriveByUtente(usr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setStatus(200);
			response.getWriter().print(gson.toJson(elenco));
			response.getWriter().flush();
			
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
