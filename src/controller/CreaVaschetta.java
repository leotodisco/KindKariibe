package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CategoriaBean;
import beans.GustoBean;
import beans.ImmagineBeans;
import beans.ProdottoBean;
import model.Carrello;
import model.CategoriaDAO;
import model.CostituzioneDAO;
import model.GustoDAO;
import model.ImmagineDAO;
import model.PossessoImmagineDAO;
import model.ProdottoDAO;

/**
 * Servlet implementation class CreaVaschetta
 */
@WebServlet("/CreaVaschetta")
public class CreaVaschetta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaVaschetta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProdottoDAO prod = new ProdottoDAO();
		String azioni = request.getParameter("operazione");
		
		System.out.println("ciao");
		if(azioni.equals("inserire")) {
			
			System.out.println("cazziduri");
			String name = request.getParameter("nome");
			String description = request.getParameter("descrizione");
			Double price = Double.parseDouble(request.getParameter("prezzo"));
			Double quantity = Double.parseDouble(request.getParameter("quantita"));
			String catNome = request.getParameter("categoria");
			Double iva = Double.parseDouble(request.getParameter("IVA"));
			Double peso = Double.parseDouble(request.getParameter("peso"));
			String tipo = request.getParameter("tipo");
			String Gusto1 = request.getParameter("gusto1");
			String Gusto2 = request.getParameter("gusto2");
			String Gusto3 = request.getParameter("gusto3");
			System.out.println("provaprova");
			
			
			ImmagineBeans immagine = new ImmagineBeans();
			ImmagineDAO imDAO = new ImmagineDAO();
			PossessoImmagineDAO posDAO = new PossessoImmagineDAO();
			GustoDAO gDAO = new GustoDAO();
			CostituzioneDAO cDAO = new CostituzioneDAO();
			
			CategoriaDAO buffer = new CategoriaDAO();
			Optional<CategoriaBean> cat;
			ProdottoBean bean = new ProdottoBean();
			try {
				immagine.setNome("Vaschetta");
				immagine.setUrl("Vaschetta.png");
				immagine.setTestoAlt("immagine mancante");
				cat = Optional.of(buffer.doRetrieveByKey(catNome));
				bean.setNome(name);
				bean.setDescrizione(description);
				bean.setPrezzo(price);
				bean.setQuantitaResidua(quantity);
				bean.setCategoria(cat);
				bean.setIVA(iva);
				bean.setTipo(tipo);
				
				Collection<GustoBean> g = gDAO.doRetrieveAll(name);
				request.setAttribute("gusti",g);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				if(tipo.equals("Vaschetta"))
				{
					
					try {
						bean.setPeso(peso);
						System.out.println(Gusto1);
						bean.getGusti().add(gDAO.doRetrieveByKey(Gusto1));
						
						if(Gusto2 != null)
						{
							bean.getGusti().add(gDAO.doRetrieveByKey(Gusto2));
						}
						
						if(Gusto3 != null)
						{
							bean.getGusti().add(gDAO.doRetrieveByKey(Gusto3));
						}
						 
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				int ID2 = prod.doSaveI(bean);
				bean.setId(ID2);
				int ID = imDAO.doSaveI(immagine);
				immagine.setIdImmagine(ID);
				posDAO.doSave(immagine, bean);
				
				Carrello cart = (Carrello) request.getSession().getAttribute("Carrello");
				
				if(cart == null)
				{
					cart = new Carrello();
				}
				cart.addProduct(bean);
				
				request.getSession().setAttribute("Carrello", cart);
				
				for(GustoBean G : bean.getGusti())
				{
					cDAO.doSave(G, bean);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(azioni.equals("mostraGusti")) {
			GustoDAO gDAO=new GustoDAO();
			ArrayList<GustoBean> g = new ArrayList<>();
			try {
				g = (ArrayList<GustoBean>) gDAO.doRetrieveAll("nome");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("gusti",g);
			RequestDispatcher view = request.getRequestDispatcher("Vaschetta.jsp");
			view.forward(request, response);
		}


		
		
	}



	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		ProdottoDAO prod = new ProdottoDAO();
		String azioni = request.getParameter("operazione");
		
		
		if(azioni.equals("inserire")) {
			System.out.println("cazziduri");
			String name = request.getParameter("nome");
			String description = request.getParameter("descrizione");
			Double price = Double.parseDouble(request.getParameter("prezzo"));
			Double quantity = Double.parseDouble(request.getParameter("quantita"));
			String catNome = request.getParameter("categoria");
			Double iva = Double.parseDouble(request.getParameter("IVA"));
			Double peso = Double.parseDouble(request.getParameter("peso"));
			String tipo = request.getParameter("tipo");
			String Gusto1 = request.getParameter("gusto1");
			String Gusto2 = request.getParameter("gusto2");
			String Gusto3 = request.getParameter("gusto3");

			
			
			ImmagineBeans immagine = new ImmagineBeans();
			ImmagineDAO imDAO = new ImmagineDAO();
			PossessoImmagineDAO posDAO = new PossessoImmagineDAO();
			GustoDAO gDAO = new GustoDAO();
			CostituzioneDAO cDAO = new CostituzioneDAO();
			
			CategoriaDAO buffer = new CategoriaDAO();
			Optional<CategoriaBean> cat;
			ProdottoBean bean = new ProdottoBean();
			try {
				immagine.setNome("Vaschetta");
				immagine.setUrl("Vaschetta.png");
				immagine.setTestoAlt("immagine mancante");
				cat = Optional.of(buffer.doRetrieveByKey(catNome));
				bean.setNome(name);
				bean.setDescrizione(description);
				bean.setPrezzo(price);
				bean.setQuantitaResidua(quantity);
				bean.setCategoria(cat);
				bean.setIVA(iva);
				bean.setTipo(tipo);
				
				Collection<GustoBean> g = gDAO.doRetrieveAll(name);
				request.setAttribute("gusti",g);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				if(tipo.equals("Vaschetta"))
				{
					
					try {
						bean.setPeso(peso);
						System.out.println(Gusto1);
						bean.getGusti().add(gDAO.doRetrieveByKey(Gusto1));
						
						if(Gusto2 != null)
						{
							bean.getGusti().add(gDAO.doRetrieveByKey(Gusto2));
						}
						
						if(Gusto3 != null)
						{
							bean.getGusti().add(gDAO.doRetrieveByKey(Gusto3));
						}
						 
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				int ID2 = prod.doSaveI(bean);
				bean.setId(ID2);
				int ID = imDAO.doSaveI(immagine);
				immagine.setIdImmagine(ID);
				posDAO.doSave(immagine, bean);
				
				Carrello cart = (Carrello) request.getSession().getAttribute("Carrello");
				
				if(cart == null)
				{
					cart = new Carrello();
				}
				cart.addProduct(bean);
				
				request.getSession().setAttribute("Carrello", cart);
				
				for(GustoBean G : bean.getGusti())
				{
					cDAO.doSave(G, bean);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(azioni.equals("mostraGusti")) {
			GustoDAO gDAO=null;
			Collection<GustoBean> g = null;
			try {
				g = gDAO.doRetrieveAll(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("gusti",g);
		}


		
		
	}

}
