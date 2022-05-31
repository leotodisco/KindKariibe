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


import beans.ImmagineBeans;
import beans.ProdottoBean;

public class PossessoImmagineDAO {
	
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

	
	public void doSave(ImmagineBeans immagine ,ProdottoBean prodotto) throws SQLException{
	
		String insertSQL = "insert into kindkaribe.possessoimmagine (prodotto,immagine) \r\n" + 
				"VALUES (?,?)";
		
		
		try (Connection con = ds.getConnection()){
			try(PreparedStatement preparedStatement = con.prepareStatement(insertSQL)){
				preparedStatement.setLong(1, prodotto.getId());
				preparedStatement.setInt(2, immagine.getIdImmagine());
				
				preparedStatement.execute();
			}
		}
	}
		
	public boolean doDelete(String Idimmagine, String prodotto) throws SQLException {
			String sql = "DELETE FROM possessoimmagine WHERE mmagine = ? and prodotto = ?";

			try(Connection connection = ds.getConnection()){
				try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
					preparedStatement.setString(1, Idimmagine);
					preparedStatement.setString(2, prodotto);
					return preparedStatement.execute();	
				}
			}
		}
		
	public ArrayList<String> retrieveImmagine(String nomeP) throws SQLException
	{
		String sql = "SELECT immagine from possessoimmagine where prodotto = ?";
		
		ArrayList<String> immagini = new ArrayList<>();
		
		try(Connection connection = ds.getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.setString(1, nomeP);
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					immagini.add(rs.getString("immagine"));
				}
				
			}
		}
		
		
		return immagini;
		
	}
		
		
		
		
	}
	

