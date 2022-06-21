package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IndirizzoBean;
import beans.UserBean;
import model.IndirizzoDao;
import model.UserDAO;
import model.possessoIndirizzoDAO;

/**
 * Servlet implementation class AggiungiIndirizzo
 */
@WebServlet("/AggiungiIndirizzo")
public class AggiungiIndirizzo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiIndirizzo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String via = request.getParameter("via");
		String citta = request.getParameter("citta");
		String CAP = request.getParameter("CAP");
		String nCivico = request.getParameter("nCivico");
		String provincia = request.getParameter("provincia");
		
		IndirizzoBean bean = new IndirizzoBean();
		
		bean.setCAP(CAP);
		bean.setCitta(citta);
		bean.setnCivico(nCivico);
		bean.setProvincia(provincia);
		bean.setVia(via);
		
		UserBean utente = (UserBean) request.getSession().getAttribute("utente");
		
		IndirizzoDao Idao = new IndirizzoDao();
		possessoIndirizzoDAO Pdao = new possessoIndirizzoDAO();
		UserDAO uDAO = new UserDAO();
		
		
		
		
		try {
			int ID = Idao.doSaveI(bean);
			bean.setId(ID);
			Pdao.doSave(utente, bean);
			utente = uDAO.doRetrieveByKey(utente.getCodiceFiscale());
			request.getSession().setAttribute("utente", utente);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String vecchioURL=request.getHeader("referer");
		response.sendRedirect(vecchioURL);
		
		
	}

}
