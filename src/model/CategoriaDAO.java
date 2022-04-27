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

import beans.CategoriaBean;

public class CategoriaDAO implements ModelInterface<CategoriaBean> {
	private static final String TABLE_NAME = "categoria";
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
	public void doSave(CategoriaBean bean) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + "('nome','descrizione') VALUES (?,?)";
		
		try(Connection con = DriverManagerConnectionPool.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){	
				ps.setString(1, bean.getNome());
				ps.setString(2, bean.getDescrizione());

				ps.execute();
			}
		}

	}

	@Override
	public boolean doDelete(String nome) throws SQLException {
		String sql = "DELETE FROM "+ TABLE_NAME +" WHERE `nome`= ? ";

		try(Connection con = DriverManagerConnectionPool.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, nome);
				return ps.execute();
			}
		}
	}

	@Override
	public CategoriaBean doRetrieveByKey(String nome) throws Exception {
		String sql = "SELECT * FROM " + TABLE_NAME + "WHERE nome = " + nome;
		CategoriaBean result = new CategoriaBean();

		try(Connection conn = DriverManagerConnectionPool.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				ResultSet rs = statement.executeQuery();

				if(rs.next()) {
					result.setNome(rs.getString("nome"));
					result.setDescrizione(rs.getString("descrizione"));
				}
			}	
		}

		return result;
	}

	@Override
	public Collection<CategoriaBean> doRetrieveAll(String order) throws Exception {
		List<CategoriaBean> categorie = new ArrayList<>();
		CategoriaBean buffer = new CategoriaBean();
		String sql = "SELECT * FROM " + TABLE_NAME;

		try(Connection conn = DriverManagerConnectionPool.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				ResultSet rs = statement.executeQuery();

				while(rs.next()) {
					buffer.setNome(rs.getString("nome"));
					buffer.setDescrizione(rs.getString("descrizione"));
					categorie.add(buffer);
				}
			}	
		}

		return categorie;
	}

	@Override
	public void doUpdate(CategoriaBean bean) throws SQLException {
		String sql = "UPDATE "+TABLE_NAME+"SET 'descrizione' = ? WHERE nome = "+bean.getNome();

		try(Connection conn = DriverManagerConnectionPool.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, bean.getDescrizione());
				statement.executeUpdate();
			}	
		}

	}

}
