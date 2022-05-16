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

import beans.ImmagineBeans;

public class ImmagineDAO implements ModelInterface<ImmagineBeans> {
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

	public int doSaveI(ImmagineBeans bean) throws SQLException {
		String insertSQL = "insert into kindkaribe.immagine (idImmagine,altezza,lunghezza,URL,nome,testoALT) \r\n" + 
				"VALUES (?,?,?,?,?,?)";
		
		String IDSQL = "SELECT idImmagine FROM kindkaribe.immagine ORDER BY idImmagine DESC LIMIT 1";
		
		try (Connection con = ds.getConnection()){
			try(PreparedStatement preparedStatement = con.prepareStatement(IDSQL)){
				
				ResultSet id = preparedStatement.executeQuery();
				
				id.next();
				int ID = id.getInt("idImmagine") + 1;
				
				PreparedStatement preparedStatement2 = con.prepareStatement(insertSQL);
				
				preparedStatement2.setInt(1, ID);
				preparedStatement2.setInt(2, bean.getAltezza());
				preparedStatement2.setInt(3, bean.getLunghezza());
				preparedStatement2.setString(4, bean.getUrl());
				preparedStatement2.setString(5, bean.getNome().substring(0, bean.getNome().length() - 4));
				preparedStatement2.setString(6, bean.getTestoAlt());
				
				preparedStatement2.executeUpdate();
				return ID;
			}
		}
		
		
	}

	@Override
	public boolean doDelete(String Idimmagine) throws SQLException {
		String sql = "DELETE FROM immagine WHERE idImmagine = ?";

		try(Connection connection = ds.getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
				preparedStatement.setString(1, Idimmagine);
				return preparedStatement.execute();	
			}
		}
	}

	@Override
	public ImmagineBeans doRetrieveByKey(String idImmagine) throws Exception {
		ImmagineBeans bean = new ImmagineBeans();
		String selectSQL = "SELECT * FROM immagine WHERE idImmagine = ?";

		try(Connection connection = ds.getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){
				preparedStatement.setString(1, idImmagine);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					bean.setAltezza(rs.getInt("altezza"));
					bean.setNome(rs.getString("nome"));
					bean.setLunghezza(rs.getInt("lunghezza"));
					bean.setUrl(rs.getString("URL"));
					bean.setTestoAlt(rs.getString("testoALT"));
					bean.setIdImmagine(rs.getInt("idImmagine"));
				}
			}
		}
		return bean;
	}

	@Override
	public Collection<ImmagineBeans> doRetrieveAll(String order) throws Exception {

		String selectSQL = "SELECT * FROM immagine ORDER BY ?";
		order = order.isEmpty() ? "nome" : order;
		List<ImmagineBeans> listaUtenti = new ArrayList<>();

		try(Connection connection = ds.getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)){
				preparedStatement.setString(1, order);				
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					ImmagineBeans bean = new ImmagineBeans();
					bean.setAltezza(rs.getInt("altezza"));
					bean.setNome(rs.getString("nome"));
					bean.setLunghezza(rs.getInt("lunghezza"));
					bean.setUrl(rs.getString("URL"));
					bean.setTestoAlt(rs.getString("testoALT"));
					bean.setIdImmagine(rs.getInt("idImmagine"));
					
					listaUtenti.add(bean);
				}
				
			}

		}
		return listaUtenti;
		
		
	}

	@Override
	public void doUpdate(ImmagineBeans bean) throws SQLException {
		String sql = "UPDATE SET idImmagine = ?,altezza = ?"
				+ "lunghezza = ?, URL = ?, nome = ?,testoALT = ?";
		
		try (Connection con = ds.getConnection()){
			try(PreparedStatement preparedStatement = con.prepareStatement(sql)){
				preparedStatement.setInt(1, bean.getAltezza());
				preparedStatement.setInt(2, bean.getLunghezza());
				preparedStatement.setString(3, bean.getUrl());
				preparedStatement.setString(4, bean.getNome());
				preparedStatement.setString(5, bean.getTestoAlt());
				
				preparedStatement.executeUpdate();
			}
		}
		
	}

	@Override
	//non usare non va bene, usare doSaveI
	public void doSave(ImmagineBeans bean) throws SQLException {
		// TODO Auto-generated method stub
		
	}



}
