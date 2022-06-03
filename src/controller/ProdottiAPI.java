package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.ProdottoBean;
import beans.ResponseStatusMessage;
import model.ProdottoDAO;

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
		if(request.getParameter("prodotto").isEmpty() || request.getParameter("prodotto")==null ) {
			response.setStatus(200);
			response.getWriter().print(gson.toJson(null));
		}
		
		if(action.equals("search")) {
			ProdottoDAO dao = new ProdottoDAO();
			try {
				List<ProdottoBean> elencoProdotti = new ArrayList<>();
				//prendo tutti i prodotti il cui nome contiene il nome passato via ajax
				elencoProdotti = dao.doRetrieveAll("C.nome").stream().filter(s->s.getNome().toLowerCase().contains(request.getParameter("prodotto").toLowerCase()))
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
