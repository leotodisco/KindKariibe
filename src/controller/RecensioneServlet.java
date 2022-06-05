package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.ProdottoBean;
import beans.RecensioneBean;
import beans.ResponseStatusMessage;
import beans.UserBean;
import model.ProdottoDAO;
import model.RecensioneDAO;
import model.UserDAO;

/**
 * Servlet implementation class RecensioneServlet
 */
@WebServlet("/RecensioneServlet")
public class RecensioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecensioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String azioni = request.getParameter("action");
		HttpSession sessione = request.getSession();
		UserBean utente = (UserBean) sessione.getAttribute("utente");
		
		if(utente==null)
		{
			RequestDispatcher view = request.getRequestDispatcher("login-form.jsp");
			view.include(request, response);
			
		}	
		

		if(azioni.equals("recensisci")) {
			RecensioneBean recensione = new RecensioneBean();
			RecensioneDAO recensioneDAO = new RecensioneDAO();
			ProdottoBean prodotto;
			ProdottoDAO productDao = new ProdottoDAO();
			try {
				prodotto = productDao.doRetrieveByKey(request.getParameter("prodotto"));
				System.out.println(prodotto.toString());
				recensione.setProdotto(prodotto);
				recensione.setUtente(utente);
				recensione.setTesto(request.getParameter("testo"));
				recensione.setVoto(Integer.valueOf(request.getParameter("voto")));
				
				recensioneDAO.doSave(recensione);
				
				RequestDispatcher view = request.getRequestDispatcher("home.jsp");
				view.forward(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(azioni.equals("check")) {
			ProdottoBean prodotto;
			ProdottoDAO productDao = new ProdottoDAO();
			HttpSession sess = request.getSession();
			UserBean usr = (UserBean) sess.getAttribute("utente");
			
			try {
				String messaggio = new String();
				prodotto = productDao.doRetrieveByKey(request.getParameter("prodotto"));
				boolean result = false;
				
				if(usr!=null) {
					result = ProdottoDAO.isAcquired(prodotto, usr);
				}
				else {
					messaggio ="non acquistato";
				}
				
				if(result == true){
					messaggio = "acquistato";
				}
				
				else if(result !=true || utente==null) {
					messaggio = "non acquistato";
				}
				
					response.setStatus(200);
					response.getWriter().print(gson.toJson(new ResponseStatusMessage(200, messaggio)));
					response.getWriter().flush();
				}	
			 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
