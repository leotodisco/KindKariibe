package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.IndirizzoBean;
import beans.UserBean;
import model.IndirizzoDao;
import model.UserDAO;

/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
       
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String CodiceFiscale = request.getParameter("CodiceFiscale");
		String email = request.getParameter("emailr");
		String nTelefono = request.getParameter("nTelefono");
		String Password = request.getParameter("password");
		String via = request.getParameter("via");
		String citta = request.getParameter("citta");
		String CAP = request.getParameter("CAP");
		String provincia = request.getParameter("provincia");
		String sesso = request.getParameter("sesso");
		String nCivico = request.getParameter("nCivico"); 
		String dataS = request.getParameter("data");
		//fare bean indirizzo
		IndirizzoBean indirizzo = new IndirizzoBean();
		indirizzo.setCAP(CAP);
		indirizzo.setCitta(citta);
		indirizzo.setProvincia(provincia);
		indirizzo.setVia(via);
		indirizzo.setnCivico(nCivico);

		ArrayList<IndirizzoBean> elenco = new ArrayList<>();
		IndirizzoDao addressDao = new IndirizzoDao();
		try {
			addressDao.doSave(indirizzo);
			indirizzo.setId(addressDao.ottieniKeyAutoIncrement());

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		elenco.add(indirizzo);
		
		
		
		java.util.Date dataNascita = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		UserBean utente = new UserBean();
		try {
			Date data = sdf.parse(dataS);
			java.sql.Date dataSQL = new java.sql.Date(data.getTime());

			utente.setDataNascita(dataSQL);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserDAO dao = new UserDAO();
		utente.setIndirizziSpedizione(elenco);
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setCodiceFiscale(CodiceFiscale);
		utente.setEmail(email);
		utente.setnTelefono(nTelefono);
		utente.setPassword(Password);
		utente.setSesso(sesso);

		try {
			dao.doSave(utente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher view = getServletContext().getRequestDispatcher("/Catalogo.jsp");
		view.forward(request, response);
		
		
	}

}
