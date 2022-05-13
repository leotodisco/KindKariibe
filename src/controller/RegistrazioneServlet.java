package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import model.UserDAO;

/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneServlet() {
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
		// TODO Auto-generated method stub
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String CodiceFiscale = request.getParameter("CodiceFiscale");
		String email = request.getParameter("email");
		String nTelefono = request.getParameter("nTelefono");
		String Password = request.getParameter("password");
		String sesso = request.getParameter("sesso");
		String dataS = request.getParameter("data");
		
		UserDAO dao = new UserDAO();
		
		
		
		
		
		try {
			if(dao.doRetriveByEmail(email) != null)
			{
				response.sendRedirect("RegistrazioneFallita.jsp");
			}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserBean utente = new UserBean();
		try {
			Date data = sdf.parse(dataS);
			java.sql.Date datasql = new java.sql.Date(data.getTime());
			utente.setDataNascita(datasql);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setCodiceFiscale(CodiceFiscale);
		utente.setEmail(email);
		utente.setnTelefono(nTelefono);
		utente.setPassword(Password);
		utente.setSesso(sesso);
		utente.setAdmin(false);
		
		try {
			dao.doSave(utente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			request.getSession().setAttribute("utente", utente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
