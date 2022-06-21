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
import beans.GustoBean;

public class GustoDAO implements ModelInterface<GustoBean>{
	private static DataSource ds;
	private static final String TABLE_NAME = "gusto";


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
	public void doSave(GustoBean bean) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " (nome,colore,descrizione,quantitaResidua) VALUES (?,?,?,?)";

		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){	
				ps.setString(1, String.valueOf(bean.getNome()));
				ps.setString(2, bean.getColore());
				ps.setString(3, bean.getDescrizione());
				ps.setString(4, String.valueOf(bean.getquantitaInMagazzino()));
				ps.execute();
			}
		}

	}

	@Override
	public boolean doDelete(String arg) throws SQLException {
		String sql = "DELETE FROM "+ TABLE_NAME +" WHERE `nome`= ? ";

		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, arg);
				return ps.execute();
			}
		}
	}

	@Override
	public GustoBean doRetrieveByKey(String id) throws Exception {
		String sql = "SELECT * FROM " + "kindkaribe.gusto" + " WHERE nome = ?";
		GustoBean bean = new GustoBean();

		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, id);

				ResultSet rs = statement.executeQuery();

				if(rs.next()) {
					bean.setNome(rs.getString("nome"));
					bean.setColore(rs.getString("colore"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setquantitaInMagazzino(Double.valueOf(rs.getString("quantitaResidua")));
				}
			}	
		}

		return bean;
	}

	@Override
	public Collection<GustoBean> doRetrieveAll(String order) throws Exception {
		List<GustoBean> gusti = new ArrayList<>();
		String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ? ";
		order = order.isEmpty() ? "nome" : order;

		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, order);
				ResultSet rs = statement.executeQuery();

				while(rs.next()) {
					GustoBean buffer = new GustoBean();
					buffer.setNome(rs.getString("nome"));
					buffer.setColore(rs.getString("colore"));
					buffer.setDescrizione(rs.getString("descrizione"));
					buffer.setquantitaInMagazzino(Double.valueOf(rs.getString("quantitaResidua")));
					gusti.add(buffer);
				}
			}	
		} 
		return gusti;
	}

	@Override
	public void doUpdate(GustoBean bean) throws SQLException {
		String sql = "UPDATE "+TABLE_NAME+"SET 'colore' = ?,'descrizione' = ?,'quantitaResidua'= ?  WHERE nome = ?";

		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, bean.getColore());
				statement.setString(2, bean.getDescrizione());
				statement.setString(3, String.valueOf(bean.getquantitaInMagazzino()));
				statement.setString(4, bean.getNome());
				statement.executeUpdate();
			}	
		}

	}



}