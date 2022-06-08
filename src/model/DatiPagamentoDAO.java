package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.MetodoPagamentoBean;
import beans.UserBean;

public class DatiPagamentoDAO {
	
	
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

	
	public void doSave(UserBean utente ,MetodoPagamentoBean metodo) throws SQLException{
	
		String insertSQL = "insert into kindkaribe.datipagamento (utente,metodo) \r\n" + 
				"VALUES (?,?)";
		
		
		try (Connection con = ds.getConnection()){
			try(PreparedStatement preparedStatement = con.prepareStatement(insertSQL)){
				preparedStatement.setString(1, utente.getCodiceFiscale());
				preparedStatement.setInt(2, metodo.getidMetodoPagamento());
				
				preparedStatement.execute();
			}
		}
	}
		
	public boolean doDelete(String Idmetodo, String utente) throws SQLException {
			String sql = "DELETE FROM datipagamento WHERE utente = ? and metodo = ?";

			try(Connection connection = ds.getConnection()){
				try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
					preparedStatement.setString(1, utente);
					preparedStatement.setString(2, Idmetodo);
					return preparedStatement.execute();	
				}
			}
		}
		
	public ArrayList<String> retrieveMetodo(String utente) throws SQLException
	{
		String sql = "SELECT immagine from datipagamento where utente = ?";
		
		ArrayList<String> metodi = new ArrayList<>();
		
		try(Connection connection = ds.getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.setString(1, utente);
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					metodi.add(rs.getString("metodo"));
				}
				
			}
		}
		
		
		return metodi;
		
	}
		
		


}
