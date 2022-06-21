package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MetodoPagamentoBean;
import beans.UserBean;
import model.DatiPagamentoDAO;
import model.MetodoPagamentoDAO;
import model.UserDAO;

/**
 * Servlet implementation class AggiungiMetodoPagamento
 */
@WebServlet("/AggiungiMetodoPagamento")
public class AggiungiMetodoPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiMetodoPagamento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String tipoMetodo = request.getParameter("tipo");
		UserBean utente = (UserBean) request.getSession().getAttribute("utente");
		
		
		if(tipoMetodo.equals("carta"))
		{
			
			String Proprietario = request.getParameter("cardholder-name");
			String NumeroCarta = request.getParameter("card-number");
			String AnnoScadenza = request.getParameter("year");
			String MeseScadenza = request.getParameter("month");
			String CVV = request.getParameter("cvv");
			
			MetodoPagamentoBean bean = new MetodoPagamentoBean();
			bean.setAnnoScadenza(Integer.valueOf(AnnoScadenza));
			bean.setMeseScadenza(Integer.valueOf(MeseScadenza));
			bean.setCVV(Integer.parseInt(CVV));
			bean.setNomeIntestatario(Proprietario);
			bean.setNumeroCarta(NumeroCarta);
			bean.setTipo("Carta");
			bean.setCircuito("Mastercard");
			
			MetodoPagamentoDAO mDao = new MetodoPagamentoDAO();
			DatiPagamentoDAO dDAO = new DatiPagamentoDAO();
			UserDAO uDAO = new UserDAO();
			try {
				int ID = mDao.doSaveI(bean);
				bean.setidMetodoPagamento(ID);
				dDAO.doSave(utente, bean);
				utente = uDAO.doRetrieveByKey(utente.getCodiceFiscale());
				request.getSession().setAttribute("utente", utente);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
		
		
		
		
		
		
		
	}

}
