package controller;

import com.oreilly.servlet.MultipartRequest;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CategoriaBean;
import beans.GustoBean;
import beans.ImmagineBeans;
import beans.ProdottoBean;
import model.CategoriaDAO;
import model.CostituzioneDAO;
import model.GustoDAO;
import model.ImmagineDAO;
import model.PossessoImmagineDAO;
import model.ProdottoDAO;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoDAO prod = new ProdottoDAO();
		MultipartRequest multi;
		String path = getServletContext().getRealPath("/")+"immagini";
		multi = new MultipartRequest(request,path,20971520);
		String azioni = multi.getParameter("operazione");

		if(azioni.equals("inserire")) {
			String name = multi.getParameter("nome");
			String description = multi.getParameter("descrizione");
			Double price = Double.parseDouble(multi.getParameter("prezzo"));
			Double quantity = Double.parseDouble(multi.getParameter("quantita"));
			String catNome = multi.getParameter("categoria");
			Double iva = Double.parseDouble(multi.getParameter("IVA"));
			Double peso = Double.parseDouble(multi.getParameter("peso"));
			String tipo = multi.getParameter("tipo");
			String Gusto1 = multi.getParameter("gusto1");
			String Gusto2 = multi.getParameter("gusto2");
			String Gusto3 = multi.getParameter("gusto3");

			
			
			ImmagineBeans immagine = new ImmagineBeans();
			ImmagineDAO imDAO = new ImmagineDAO();
			PossessoImmagineDAO posDAO = new PossessoImmagineDAO();
			GustoDAO gDAO = new GustoDAO();
			CostituzioneDAO cDAO = new CostituzioneDAO();
			
			CategoriaDAO buffer = new CategoriaDAO();
			Optional<CategoriaBean> cat;
			ProdottoBean bean = new ProdottoBean();
			try {
				immagine.setNome(multi.getOriginalFileName("image"));
				immagine.setUrl(multi.getOriginalFileName("image"));
				immagine.setTestoAlt("immagine mancante");
				cat = Optional.of(buffer.doRetrieveByKey(catNome));
				bean.setNome(name);
				bean.setDescrizione(description);
				bean.setPrezzo(price);
				bean.setQuantitaResidua(quantity);
				bean.setCategoria(cat);
				bean.setIVA(iva);
				bean.setTipo(tipo);
				
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
				
				for(GustoBean G : bean.getGusti())
				{
					cDAO.doSave(G, bean);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//rimuove totalmente il prodotto dal database
		if(azioni.equals("rimuovi")) {
			String name = multi.getParameter("nome");
			//TO DO metodo x diminuire la quantita nel database di quanto vale quantita

			try {
				prod.doDelete(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("AdminPage.jsp");
		}

		
		if(azioni.equals("aggiorna")) {
			
			String Attributo = multi.getParameter("Attributo");
			String valore = multi.getParameter("valore");
			String Idprodotto = multi.getParameter("prodotto");
			
			
			if(Attributo.equals("gusto"))
			{
				String gusto = multi.getParameter("gusto");
				try {
					CostituzioneDAO.cambiaGusto(Idprodotto, valore,gusto);
					response.sendRedirect("DettagliProdottoAdmin.jsp");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
			
			try {
				ProdottoDAO.SingoloUpdate(Attributo, valore, Idprodotto);
				response.sendRedirect("DettagliProdottoAdmin.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			}
			
			}
		
		
		if(azioni.equals("aggiungiCategoria"))
		{
			
			String nome = multi.getParameter("nome");
			String descrizione = multi.getParameter("descrizione");
			
			CategoriaDAO cDAO  = new CategoriaDAO();
			CategoriaBean bean = new CategoriaBean();
			
			bean.setNome(nome);
			bean.setDescrizione(descrizione);
			
			try {
				cDAO.doSave(bean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.sendRedirect("DettagliProdottoAdmin.jsp");
			
		}
		
		if(azioni.equals("ModificaCategoria"))
		{
			String attributo = multi.getParameter("attributo");
			String valore = multi.getParameter("valore");
			String nome = multi.getParameter("nome");
			
			try {
				CategoriaDAO.SingoloUpdate(attributo, valore, nome);
				response.sendRedirect("DettagliProdottoAdmin.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		if(azioni.equals("rimuoviC")) {
			String name = multi.getParameter("nome");

			try {
				CategoriaDAO cDAO  = new CategoriaDAO();
				
				
			
					cDAO.doDelete(name);
					response.sendRedirect("DettagliProdottoAdmin.jsp");
				prod.doDelete(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(azioni.equals("aggiungiI")) {
			
			ImmagineBeans immagine = new ImmagineBeans();
			ImmagineDAO imDAO = new ImmagineDAO();
			PossessoImmagineDAO posDAO = new PossessoImmagineDAO();
			ProdottoBean bean = new ProdottoBean();
			String IDP = multi.getParameter("prodotto");
			System.out.println(IDP);
			
			try {
				bean = prod.doRetrieveByKey(IDP);
				System.out.println(bean.getNome());
				immagine.setNome(multi.getOriginalFileName("image"));
				immagine.setUrl(multi.getOriginalFileName("image"));
				immagine.setTestoAlt("immagine mancante");
				int ID = imDAO.doSaveI(immagine);
				immagine.setIdImmagine(ID);
				posDAO.doSave(immagine, bean);
				
				response.sendRedirect("DettagliProdottoAdmin.jsp");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
		
		if(azioni.equals("aggiungiGusto")){
			
			String nome = multi.getParameter("nome");
			String descrizione = multi.getParameter("descrizione");
			String quantita = multi.getParameter("quantita");
			String colore = multi.getParameter("colore");
			
			GustoDAO GDAO = new GustoDAO();
			GustoBean Gbean = new GustoBean();
			
			Gbean.setNome(nome);
			Gbean.setColore(colore);
			Gbean.setDescrizione(descrizione);
			Gbean.setquantitaInMagazzino(Double.parseDouble(quantita));
			
			try {
				GDAO.doSave(Gbean);
				response.sendRedirect("DettagliProdottoAdmin.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
				
		if(azioni.equals("ModificaGusto")) {
			
			String attributo = multi.getParameter("attributo");
			String valore = multi.getParameter("valore");
			String nome = multi.getParameter("nome");
			
			
			
			try {
				GustoDAO.SingoloUpdate(attributo, valore, nome);
				response.sendRedirect("DettagliProdottoAdmin.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
