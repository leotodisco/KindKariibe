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

import beans.GustoBean;
import beans.ProdottoBean;

public class CostituzioneDAO {

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

	
	public void doSave(GustoBean gusto ,ProdottoBean prodotto) throws SQLException{
	
		String insertSQL = "insert into kindkaribe.costituzione (prodotto,gusto) " + 
				"VALUES (?,?)";
		
		
		try (Connection con = ds.getConnection()){
			try(PreparedStatement preparedStatement = con.prepareStatement(insertSQL)){
				preparedStatement.setLong(1, prodotto.getId());
				preparedStatement.setString(2, gusto.getNome());
				
				preparedStatement.execute();
			}
		}
	}
	
	//questo metodo salva tutti i gusti presenti nel bean
	public void doSaveALL(ProdottoBean prodotto) throws SQLException{
		
		String insertSQL = "insert into kindkaribe.costituzione (prodotto,gusto) " + 
				"VALUES (?,?)";
		
		
		try (Connection con = ds.getConnection()){
			try(PreparedStatement preparedStatement = con.prepareStatement(insertSQL)){
				for(GustoBean gusto : prodotto.getGusti()) {
					preparedStatement.setInt(1, prodotto.getId());
					preparedStatement.setString(2, gusto.getNome());

					preparedStatement.executeUpdate();
				}
			}
		}
	}
		
	public boolean doDelete(String gusto, String prodotto) throws SQLException {
			String sql = "DELETE FROM costituzione WHERE gusto = ? and prodotto = ?";

			try(Connection connection = ds.getConnection()){
				try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
					preparedStatement.setString(1, gusto);
					preparedStatement.setString(2, prodotto);
					return preparedStatement.execute();	
				}
			}
		}
		
	public ArrayList<String> retrieveGusti(String nomeP) throws SQLException
	{
		String sql = "SELECT gusto from costituzione where prodotto = ?";
		
		ArrayList<String> gusti = new ArrayList<>();
		
		try(Connection connection = ds.getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.setString(1, nomeP);
				ResultSet rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					gusti.add(rs.getString("gusto"));
				}
				
			}
		}
		
		
		return gusti;
		
	}
		
	public static boolean cambiaGusto(String Idprodotto, String valore, String gusto) throws SQLException
	{
		String sql = "UPDATE  costituzione SET gusto = ?" 
				 + " WHERE prodotto = ? and gusto = ?";
		
		try(Connection con = ds.getConnection()){
			try(PreparedStatement preparedStatement2 = con.prepareStatement(sql)){
				
				preparedStatement2.setString(1, valore);
				preparedStatement2.setString(2, Idprodotto);
				preparedStatement2.setString(3, gusto);
			
				preparedStatement2.executeUpdate();
				return true;
		
			}
		
		

	
}
		
	}
}
