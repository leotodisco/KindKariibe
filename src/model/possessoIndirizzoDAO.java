package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.IndirizzoBean;
import beans.UserBean;

//dao per linkare un utente ad un indirizzo
public class possessoIndirizzoDAO{
	
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

	public void doSave(UserBean utente, IndirizzoBean indirizzo) throws SQLException {
		String sql="INSERT INTO kindkaribe.possessoIndirizzo (utente, indirizzo) "
				+ "VALUES (?,?)";
		
		try(Connection con= ds.getConnection()){
			try(PreparedStatement ps= con.prepareStatement(sql)){
				ps.setString(1,utente.getCodiceFiscale());
				ps.setInt(2, indirizzo.getId());
				
				ps.execute();
			}
		}
		
	}

	public boolean doDelete(String codiceFiscale, int idIndirizzo) throws SQLException {
		String sql = "DELETE FROM possessoIndirizzo WHERE utente = ? and metodo = ?";

		try(Connection connection = ds.getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.setString(1, codiceFiscale);
				preparedStatement.setLong(2, idIndirizzo);
				return preparedStatement.execute();	
			}
		}
	}


	public ArrayList<String> retrieveMetodo(String utente) throws SQLException
	{
		String sql = "SELECT immagine from possessoIndirizzo where utente = ?";
		
		ArrayList<String> metodi = new ArrayList<>();
		
		try(Connection connection = ds.getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.setString(1, utente);
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					metodi.add(rs.getString("indirizzo"));
				}
				
			}
		}
		
		
		return metodi;
		
	}
	
}
