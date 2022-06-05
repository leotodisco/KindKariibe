package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
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

import beans.ProdottoBean;
import beans.UserBean;
import model.ProdottoDAO;
import model.RecensioneDAO;

/**
 * Servlet implementation class CreaCatalogo
 */
@WebServlet("/CreaCatalogo")
public class CreaCatalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreaCatalogo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String azioni = request.getParameter("action");
		HttpSession sessione = request.getSession();
		UserBean utente = (UserBean) sessione.getAttribute("utente");
		String tipo = request.getParameter("tipo");

		if(azioni == null)
		{
			
			if( tipo == null || tipo.equals("Pasticceria"))
				{
				ProdottoDAO Dao = new ProdottoDAO();
				ArrayList<ProdottoBean> ListaProdotti = null;
				try {
					ListaProdotti = (ArrayList<ProdottoBean>) Dao.doRetrieveAll("C.nome");
					List<ProdottoBean> ListaPasticceria = ListaProdotti.stream().filter(t -> t.getTipo().equals("Pasticceria")).collect(Collectors.toList());
					request.setAttribute("prodotti", ListaPasticceria);

					RequestDispatcher view = request.getRequestDispatcher("Catalogo.jsp");
					view.include(request, response);


				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
			else if(tipo.equals("Gelateria"))
			{
				ProdottoDAO Dao = new ProdottoDAO();
				ArrayList<ProdottoBean> ListaProdotti = null;
				try {
					ListaProdotti = (ArrayList<ProdottoBean>) Dao.doRetrieveAll("C.nome");
					List<ProdottoBean> ListaPasticceria = ListaProdotti.stream().filter(t -> t.getTipo().equals("Vaschetta")).collect(Collectors.toList());
					request.setAttribute("prodotti", ListaPasticceria);

					RequestDispatcher view = request.getRequestDispatcher("Catalogo-Gelateria.jsp");
					view.include(request, response);


				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		else if(azioni.equals("details"))
		{

			ProdottoDAO Dao = new ProdottoDAO();	

			ProdottoBean prodotto;
			try {
				prodotto = Dao.doRetrieveByKey(request.getParameter("id"));
				List<ProdottoBean> prodottiConsigliati = Dao.doRetrieveAll("C.nome").stream().filter(s->!s.getId().equals(request.getParameter("id"))).limit(4).collect(Collectors.toList());
				
				request.setAttribute("prodottiConsigliati", prodottiConsigliati);
				request.setAttribute("prodotto", prodotto);
				request.setAttribute("votoMedio", RecensioneDAO.getVotoMedio(prodotto));
				RequestDispatcher view = request.getRequestDispatcher("DettagliProdotto.jsp");
				view.include(request, response);

			} catch (Exception e) {
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
