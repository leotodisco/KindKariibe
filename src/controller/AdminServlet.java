package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ProdottoBean;
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
		String azioni = request.getParameter("operazione");

		if(azioni.equals("inserire")) {
			String name = request.getParameter("nome");
			String description = request.getParameter("descrizione");
			Double price = Double.parseDouble(request.getParameter("prezzo"));
			Double quantity = Double.parseDouble(request.getParameter("quantita"));

			ProdottoBean bean = new ProdottoBean();
			bean.setNome(name);
			bean.setDescrizione(description);
			bean.setPrezzo(price);
			bean.setQuantitaResidua(quantity);

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

			ProdottoBean bean = new ProdottoBean();
			bean.setNome(name);
			bean.setDescrizione(description);
			bean.setPrezzo(price);
			bean.setQuantitaResidua(quantity);

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
