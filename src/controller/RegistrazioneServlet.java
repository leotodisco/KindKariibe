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
		String via = request.getParameter("via");
		String citta = request.getParameter("citta");
		String CAP = request.getParameter("CAP");
		String provincia = request.getParameter("provincia");
		String sesso = request.getParameter("sesso");
		String nCivico = request.getParameter("nCivico"); 
		String dataS = request.getParameter("data");
		
	
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserBean utente = new UserBean();
		try {
			Date data = sdf.parse(dataS);
			utente.setDataNascita(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserDAO dao = new UserDAO();
		
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setCodiceFiscale(CodiceFiscale);
		utente.setEmail(email);
		utente.setnTelefono(nTelefono);
		utente.setPassword(Password);
		utente.setVia(via);
		utente.setCitta(citta);
		utente.setCAP(CAP);
		utente.setProvincia(provincia);
		utente.setSesso(sesso);
		try {
			utente.setnCivico(nCivico);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dao.doSave(utente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
