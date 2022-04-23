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

public class UserDAO implements ModelInterface<UserBean> {
	
	public UserDAO()
	{
		
	}

	@Override
	public void doSave(UserBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO utente" 
				+ " (codiceFiscale, nome, cognome, email, nTelefono, password, via, citta, CAP, privincia ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getCodiceFiscale());
			preparedStatement.setString(2, bean.getNome());
			preparedStatement.setString(3, bean.getCognome());
			preparedStatement.setString(4, bean.getEmail());
			preparedStatement.setString(5, bean.getnTelefono());
			preparedStatement.setString(6, bean.getPassword());
			preparedStatement.setString(7, bean.getVia());
			preparedStatement.setString(8, bean.getCitta());
			preparedStatement.setString(9, bean.getCAP());
			preparedStatement.setString(10, bean.getProvincia());

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
	}

	@Override
	public boolean doDelete(String codiceFiscale) throws SQLException {
		String sql = "DELETE FROM utente WHERE codiceFiscale = ?";
		
		try(Connection connection = DriverManagerConnectionPool.getConnection()){
			PreparedStatement preparedStatement = null;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, codiceFiscale);
			return preparedStatement.execute();	
		}
	}

	

	@Override
	public UserBean doRetrieveByKey(String codiceFiscale) throws Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UserBean bean = new UserBean();

		String selectSQL = "SELECT * FROM utente WHERE codiceFiscale = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codiceFiscale);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscale(rs.getString("CodiceFiscale"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("nTelefono"));
				bean.setPassword(rs.getString("password"));
				bean.setVia(rs.getString("via"));
				bean.setCitta(rs.getString("citta"));
				bean.setCAP(rs.getString("CAP"));
				bean.setProvincia(rs.getString("provincia"));
				bean.setnCivico(rs.getString("numCivico"));
				bean.setDataNascita(rs.getDate("dataNascita"));
				bean.setSesso(rs.getString("genere"));	
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}

	@Override
	public Collection<UserBean> doRetrieveAll(String order) throws Exception {
		
		String sql = "SELECT * FROM utente ORDER BY ?";
		order = order.isEmpty() ? "cognome" : order;
		List<UserBean> listaUtenti = new ArrayList();
		UserBean bean = new UserBean();
	
		
		try(Connection connection = DriverManagerConnectionPool.getConnection()){
			PreparedStatement preparedStatement = null;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, order);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				bean.setCodiceFiscale(rs.getString("codiceFiscale"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("nTelefono"));
				bean.setPassword(rs.getString("password"));
				bean.setVia(rs.getString("via"));
				bean.setCitta(rs.getString("citta"));
				bean.setCAP(rs.getString("CAP"));
				bean.setProvincia(rs.getString("provincia"));
				bean.setnCivico(rs.getString("numCivico"));
				bean.setDataNascita(rs.getDate("dataNascita"));
				bean.setSesso(rs.getString("genere"));				
				listaUtenti.add(bean);
			}
		}

		return listaUtenti;
	}

	@Override
	public void doUpdate(UserBean bean) throws SQLException {
		String sql = "UPDATE utente SET codiceFiscale = ?,nome = ?,cognome = ?,genere = ?,via = ?," +
					 "numCivico = ?,CAP = ?, citta = ?,provincia = ?, dataNascita = ?, email = ?, password =?,"+
					 "nTelefono = ?";
		
		try(Connection connection = DriverManagerConnectionPool.getConnection()){
			PreparedStatement preparedStatement = null;
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, bean.getCodiceFiscale());
			preparedStatement.setString(2, bean.getNome());
			preparedStatement.setString(3, bean.getCognome());
			preparedStatement.setString(4, bean.getSesso());
			preparedStatement.setString(5, bean.getVia());
			preparedStatement.setString(6, bean.getnCivico());
			preparedStatement.setString(7, bean.getCAP());
			preparedStatement.setString(8, bean.getCitta());
			preparedStatement.setString(9, bean.getProvincia());
			preparedStatement.setDate(10, (java.sql.Date) bean.getDataNascita());
			preparedStatement.setString(11, bean.getEmail());
			preparedStatement.setString(12, bean.getPassword());
			preparedStatement.setString(13, bean.getnTelefono());
			
			preparedStatement.execute();
		}
	}
	
	public UserBean doRetriveByEmail(String email) throws NumberFormatException, Exception{
		UserBean bean = new UserBean();
		String selectSQL = "SELECT * FROM utente WHERE email = ?";

		try (Connection connection = DriverManagerConnectionPool.getConnection()){
			PreparedStatement preparedStatement = null;
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCodiceFiscale(rs.getString("CodiceFiscale"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("nTelefono"));
				bean.setPassword(rs.getString("password"));
				bean.setVia(rs.getString("via"));
				bean.setCitta(rs.getString("citta"));
				bean.setCAP(rs.getString("CAP"));
				bean.setProvincia(rs.getString("provincia"));
				bean.setnCivico(rs.getString("numCivico"));
				bean.setDataNascita(rs.getDate("dataNascita"));
				bean.setSesso(rs.getString("genere"));	
				
			}
		}
		return bean;
	}

}
