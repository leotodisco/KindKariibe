package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.MetodoPagamentoBean;
import beans.UserBean;

public class UserDAO implements ModelInterface<UserBean> {
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/kindkaribe");
		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	@Override
	public void doSave(UserBean bean) throws SQLException {
		String insertSQL = "INSERT INTO utente" 
				+ " (codiceFiscale, nome, cognome, email, nTelefono, password, genere, dataNascita ) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?,?)";

		try (Connection con = ds.getConnection()){
			try(PreparedStatement preparedStatement = con.prepareStatement(insertSQL)){
				preparedStatement.setString(1, bean.getCodiceFiscale());
				preparedStatement.setString(2, bean.getNome());
				preparedStatement.setString(3, bean.getCognome());
				preparedStatement.setString(4, bean.getEmail());
				preparedStatement.setString(5, bean.getnTelefono());
				preparedStatement.setString(6, bean.getPassword());
				preparedStatement.setString(7, bean.getSesso());
				preparedStatement.setDate(8, (java.sql.Date) bean.getDataNascita());

				preparedStatement.executeUpdate();
			}
		}
		
		}
	

	@Override
	public boolean doDelete(String codiceFiscale) throws SQLException {
		String sql = "DELETE FROM utente WHERE codiceFiscale = ?";

		try(Connection connection = ds.getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.setString(1, codiceFiscale);
				return preparedStatement.execute();	
			}
		}
	}



	@Override
	public UserBean doRetrieveByKey(String codiceFiscale) throws Exception {
		UserBean bean = new UserBean();
		String selectSQL = "SELECT * FROM utente WHERE codiceFiscale = ?";
		System.out.println("\n\n\n\n 1234\n\n\n\n\n");

		try(Connection connection = ds.getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){
				preparedStatement.setString(1, codiceFiscale);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					bean.setCodiceFiscale(rs.getString("CodiceFiscale"));
					bean.setNome(rs.getString("nome"));
					bean.setCognome(rs.getString("cognome"));
					bean.setEmail(rs.getString("nTelefono"));
					bean.setPassword(rs.getString("password"));
					bean.setDataNascita(rs.getDate("dataNascita"));
					bean.setSesso(rs.getString("genere"));	
					ArrayList<MetodoPagamentoBean> MetodiPagamento = new ArrayList<>();
					MetodoPagamentoDAO metodiDAO = new MetodoPagamentoDAO();

					String sqlPagamenti = "SELECT metodo FROM datiPagamento WHERE utente = '" + codiceFiscale + "'";

					try(PreparedStatement ps = connection.prepareStatement(sqlPagamenti)){
						ResultSet metodi = ps.executeQuery();
						while(metodi.next()) {
							MetodiPagamento.add(metodiDAO.doRetrieveByKey(metodi.getString("idMetodoPagamento")));
						}
					}
					bean.setElencoMetodiPagamento(MetodiPagamento);
				}
			}
		}
		return bean;
	}

	@Override
	public Collection<UserBean> doRetrieveAll(String order) throws Exception {
		String sql = "SELECT * FROM utente ORDER BY ?";
		order = order.isEmpty() ? "cognome" : order;
		List<UserBean> listaUtenti = new ArrayList<>();

		try(Connection connection = ds.getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.setString(1, order);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					UserBean bean = new UserBean();
					bean.setCodiceFiscale(rs.getString("codiceFiscale"));
					bean.setNome(rs.getString("nome"));
					bean.setCognome(rs.getString("cognome"));
					bean.setEmail(rs.getString("nTelefono"));
					bean.setPassword(rs.getString("password"));
					bean.setDataNascita(rs.getDate("dataNascita"));
					bean.setSesso(rs.getString("genere"));	
					bean.setnTelefono(rs.getString("nTelefono"));
					ArrayList<MetodoPagamentoBean> MetodiPagamento = new ArrayList<>();
					MetodoPagamentoDAO metodiDAO = new MetodoPagamentoDAO();

					String sqlPagamenti = "SELECT metodo FROM datiPagamento WHERE utente = '" + bean.getCodiceFiscale() + "'";

					try(PreparedStatement ps = connection.prepareStatement(sqlPagamenti)){
						ResultSet metodi = ps.executeQuery();
						while(metodi.next()) {
							MetodiPagamento.add(metodiDAO.doRetrieveByKey(metodi.getString("idMetodoPagamento")));
						}
					}
					
					bean.setElencoMetodiPagamento(MetodiPagamento);
					listaUtenti.add(bean);
				}
			}
		}

		return listaUtenti;
	}

	@Override
	public void doUpdate(UserBean bean) throws SQLException {
		String sql = "UPDATE utente SET nome = ?, cognome = ?, genere = ? " +
				" dataNascita = ?, email = ?, password =?, "+
				" nTelefono = ? WHERE codiceFiscale = ? ";

		try(Connection connection = ds.getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){	
				preparedStatement.setString(1, bean.getNome());
				preparedStatement.setString(2, bean.getCognome());
				preparedStatement.setString(3, bean.getSesso());
				preparedStatement.setDate(4, (java.sql.Date) bean.getDataNascita());
				preparedStatement.setString(5, bean.getEmail());
				preparedStatement.setString(6, bean.getPassword());
				preparedStatement.setString(7, bean.getnTelefono());
				preparedStatement.setString(8, bean.getCodiceFiscale());

				preparedStatement.execute();
			}
		}
	}

	public UserBean doRetriveByEmail(String email) throws NumberFormatException, Exception{
		UserBean bean = new UserBean();
		String selectSQL = "SELECT * FROM utente WHERE email = ?";

		try (Connection connection = ds.getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){
				preparedStatement.setString(1, email);

				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					bean.setCodiceFiscale(rs.getString("CodiceFiscale"));
					bean.setNome(rs.getString("nome"));
					bean.setCognome(rs.getString("cognome"));
					bean.setEmail(rs.getString("nTelefono"));
					bean.setPassword(rs.getString("password"));
					bean.setDataNascita(rs.getDate("dataNascita"));
					bean.setSesso(rs.getString("genere"));	
					bean.setnTelefono(rs.getString("nTelefono"));
					bean.setAdmin(rs.getBoolean("admin"));
					
				}
				
				MetodoPagamentoDAO daoPagamenti= new MetodoPagamentoDAO();
				bean.setElencoMetodiPagamento(daoPagamenti.doRetriveByUtente(bean.getCodiceFiscale()));
				
				IndirizzoDao daoIndirizzi= new IndirizzoDao();
				bean.setIndirizziSpedizione(daoIndirizzi.doRetriveByUtente(bean.getCodiceFiscale()));
				
				
			}
		}
		return bean;
	}
	

}
