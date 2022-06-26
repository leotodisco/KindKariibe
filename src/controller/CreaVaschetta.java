package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
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
		
		if(azioni.equals("inserire")) {
			String name = request.getParameter("nome");
			name = name + ("\n");
			String description = request.getParameter("descrizione");
			Double price = Double.valueOf(request.getParameter("prezzo"));
			Double quantity = Double.valueOf(request.getParameter("quantita"));
			String catNome = request.getParameter("categoria");
			Double iva = Double.valueOf(request.getParameter("IVA"));
			Double peso = Double.valueOf(request.getParameter("peso"));
			String tipo = request.getParameter("tipo");
			
			String Gusto1 = request.getParameter("gusto1");
			if(Gusto1 == null)
			{
				Gusto1 = "Cioccolato";
			}
			String Gusto2 = new String();
			
			if(request.getParameter("gusto2") != null) {
				Gusto2 = request.getParameter("gusto2");
			}
			else {
				Gusto2 = null;
			}
			
			String Gusto3 = new String();
			if(request.getParameter("gusto3") != null) {
				Gusto3 = request.getParameter("gusto3");
			}
			else {
				Gusto3=null;
			}
		
			
			
			name = name + ("\n");
			
			
			
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
				immagine.setUrl("Vaschetta.jpeg");
				immagine.setTestoAlt("immagine mancante");
				cat = Optional.of(buffer.doRetrieveByKey(catNome));
				bean.setNome(name);
				bean.setDescrizione(description);
				bean.setPrezzo(price);
				bean.setQuantitaResidua(quantity);
				bean.setCategoria(cat);
				bean.setIVA(iva);
				bean.setTipo(tipo);

				ArrayList<String> elencoImmagini = new ArrayList<>();
				elencoImmagini.add(0, immagine.getUrl());
				bean.setPathImage(elencoImmagini);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				if(tipo.equals("Vaschetta"))
				{
					
					try {
						bean.setPeso(peso);
						bean.getGusti().add(gDAO.doRetrieveByKey(Gusto1));
						
						if(Gusto2 != null)
						{
							if(!Gusto2.equals(Gusto1))
							{
								System.out.println("boh1");
								bean.getGusti().add(gDAO.doRetrieveByKey(Gusto2));
							}
							
						}
						
						if(Gusto3 != null)
						{
							if(!Gusto3.equals(Gusto2) && !Gusto3.equals(Gusto1))
							{
								bean.getGusti().add(gDAO.doRetrieveByKey(Gusto3));		
							}
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
				
				//per far sì che la vaschetta non sia visibile nel catalogo
				prod.doDelete(bean.getId().toString());
				
				for(GustoBean G : bean.getGusti())
				{
					System.out.println(G);
					cDAO.doSave(G, bean);
				}
				
				Carrello cart = (Carrello) request.getSession().getAttribute("Carrello");
				
				if(cart == null)
				{
					cart = new Carrello();
				}
				cart.addProduct(bean);
				request.getSession().setAttribute("Carrello", cart);	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.sendRedirect("Catalogo-Gelateria.jsp");
			
			
		}

		else if(azioni.equals("mostraGusti")) {
			GustoDAO gDAO=new GustoDAO();
			ArrayList<GustoBean> gusti = new ArrayList<>();
			try {
				gusti = (ArrayList<GustoBean>) gDAO.doRetrieveAll("nome");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Comparator<GustoBean> gustiComparator = (o1, o2) -> {
    			int r = o1.getNome().compareTo(o2.getNome());
    				return r;
    		};
    		
    		//i gusti saranno presentati in ordine alfabetico
    		gusti.sort(gustiComparator);
			request.setAttribute("gusti", gusti);
			
			RequestDispatcher view = request.getRequestDispatcher("Vaschetta.jsp");
			view.forward(request, response);
		}	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		doGet(request,response);
		
	}

}
