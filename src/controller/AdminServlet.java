package controller;

import com.oreilly.servlet.MultipartRequest;

import java.io.File;
import java.io.IOException;

import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CategoriaBean;
import beans.ProdottoBean;
import model.CategoriaDAO;
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		
			
			
			CategoriaDAO buffer = new CategoriaDAO();
			Optional<CategoriaBean> cat;
			ProdottoBean bean = new ProdottoBean();
			try {
				cat = Optional.of(buffer.doRetrieveByKey(catNome));
				bean.setNome(name);
				bean.setDescrizione(description);
				bean.setPrezzo(price);
				bean.setQuantitaResidua(quantity);
				bean.setCategoria(cat);
				bean.setIVA(iva);
				bean.setPeso(peso);
				bean.setTipo(tipo);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				prod.doSave(bean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//rimuove totalmente il prodotto dal database
		if(azioni.equals("rimuovi")) {
			String name = request.getParameter("nome");
			//TO DO metodo x diminuire la quantita nel database di quanto vale quantita

			try {
				prod.doDelete(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(azioni.equals("aggiorna")) {
			String name = request.getParameter("nome");
			String description = request.getParameter("descrizione");
			Double price = Double.parseDouble(request.getParameter("prezzo"));
			Double quantity = Double.parseDouble(request.getParameter("quantita"));
			String catNome = request.getParameter("categoria");
			Double iva = Double.parseDouble(request.getParameter("IVA"));
			Double peso = Double.parseDouble(request.getParameter("peso"));
			String immagine = request.getParameter("immagine");
			String tipo = request.getParameter("tipo");
			
			CategoriaDAO buffer = new CategoriaDAO();
			Optional<CategoriaBean> cat;
			ProdottoDAO dao = new ProdottoDAO();
			ProdottoBean bean = new ProdottoBean();
			try {
				bean = dao.doRetrieveByKey(name);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				cat = Optional.of(buffer.doRetrieveByKey(catNome));
				bean.setNome(name);
				bean.setDescrizione(description);
				bean.setPrezzo(price);
				bean.setQuantitaResidua(quantity);
				bean.setCategoria(cat);
				bean.setIVA(iva);
				bean.addImmaginePrimaPosizione(immagine); //in prima posizione della lista c'è l'immagine del catalogo
				bean.setPeso(peso);
				bean.setTipo(tipo);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

			try {
				prod.doUpdate(bean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		RequestDispatcher view = getServletContext().getRequestDispatcher("/Catalogo.jsp");
		view.forward(request, response);
		
	}


}
