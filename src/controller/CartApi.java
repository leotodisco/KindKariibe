package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class CartApi
 */
@WebServlet("/CartApi")
public class CartApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String contentType = "application/json; charset=UTF-8";
	private String action;
	private Gson gson = new Gson();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
    public CartApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		if(action.equals("incrementa")) {
			System.out.println("ciao");
			System.out.println("prova nella servlet, quantita = " + request.getParameter("Quantita"));
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
