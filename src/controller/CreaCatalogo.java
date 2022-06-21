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

import beans.CategoriaBean;
import beans.GustoBean;
import beans.OrdineBean;
import beans.ProdottoBean;
import beans.RecensioneBean;
import beans.UserBean;
import model.CategoriaDAO;
import model.GustoDAO;
import model.OrdineDAO;
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
		GustoDAO gDAO = new GustoDAO();
		OrdineDAO Odao = new OrdineDAO();
		CategoriaDAO CDAO = new CategoriaDAO();
		
		try {
			ArrayList<GustoBean> gusti = (ArrayList<GustoBean>) gDAO.doRetrieveAll("nome");
			request.getSession().setAttribute("gusti", gusti);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if(azioni == null)
		{
			
			if( tipo == null || tipo.equals("Pasticceria"))
				{
				ProdottoDAO Dao = new ProdottoDAO();
				List<ProdottoBean> ListaPasticceria = null;
				try {
					ListaPasticceria = (ArrayList<ProdottoBean>) Dao.doRetrieveAll("C.nome");
					List<OrdineBean> ordini = (List<OrdineBean>) Odao.doRetrieveAll("idOrdine");
					request.getSession().setAttribute("ordini", ordini);
					List<CategoriaBean> Categorie= (List<CategoriaBean>) CDAO.doRetrieveAll("idOrdine");
					request.getSession().setAttribute("Categorie", Categorie);
					
				if(tipo != null && tipo.equals("Pasticceria"))
				{
					ListaPasticceria = ListaPasticceria.stream().filter(t -> t.getTipo().equals("Pasticceria")).collect(Collectors.toList());
				}
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
			RecensioneDAO daoRecensioni = new RecensioneDAO();
			ArrayList<RecensioneBean> elencoRecensioni = new ArrayList<>();
			ProdottoDAO Dao = new ProdottoDAO();
			CategoriaDAO Cdao = new CategoriaDAO();
			GustoDAO Gdao = new GustoDAO();


			ProdottoBean prodotto;
			try {
				prodotto = Dao.doRetrieveByKey(request.getParameter("id"));
				List<ProdottoBean> prodottiConsigliati = Dao.doRetrieveAll("C.nome").stream().filter(s->!s.getId().equals(request.getParameter("id"))).limit(4).collect(Collectors.toList());
				elencoRecensioni = (ArrayList<RecensioneBean>) daoRecensioni.getRecensioniByProduct(prodotto).stream().limit(5).collect(Collectors.toList());
				
				request.setAttribute("prodottiConsigliati", prodottiConsigliati);
				request.setAttribute("prodotto", prodotto);
				request.setAttribute("votoMedio", RecensioneDAO.getVotoMedio(prodotto));
				request.setAttribute("recensioni", elencoRecensioni);
				
				RequestDispatcher view = null;
				
				if(utente != null && utente.getAdmin() == true)
				{
					List<CategoriaBean> categorie = (List<CategoriaBean>) Cdao.doRetrieveAll("nome");
					request.setAttribute("categorie", categorie);
					List<GustoBean> gusti = (List<GustoBean>) Gdao.doRetrieveAll("nome");
					request.setAttribute("gusti", gusti);
					view = request.getRequestDispatcher("DettagliProdottoAdmin.jsp");
				}
				else
				{
					view = request.getRequestDispatcher("DettagliProdotto.jsp");
				}
				
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
