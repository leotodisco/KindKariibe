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
import beans.IndirizzoBean;

public class IndirizzoDao implements ModelInterface<IndirizzoBean> {
	private static final String TABLE_NAME = "Indirizzo";
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
	public void doSave(IndirizzoBean bean) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " ('via', 'citta', 'CAP', 'numCivico', 'provincia') "
				+ "VALUES (?,?,?,?,?)";
		
		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){	
				ps.setString(1, bean.getVia());
				ps.setString(2, bean.getCitta());
				ps.setString(3, bean.getCAP());
				ps.setString(4, bean.getnCivico());
				ps.setString(5, bean.getProvincia());

				ps.execute();
			}
		}
		
	}

	@Override
	public boolean doDelete(String arg) throws SQLException {
		String sql = "DELETE FROM "+ TABLE_NAME +" WHERE `id`= ? ";

		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, arg);
				return ps.execute();
			}
		}
	}

	@Override
	public IndirizzoBean doRetrieveByKey(String arg) throws Exception {
		
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE `id`= ? ";
		IndirizzoBean result = new IndirizzoBean();

		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, arg);
				ResultSet rs = statement.executeQuery();

				if(rs.next()) {
					result.setId(rs.getInt("id"));
					result.setVia(rs.getString("via"));
					result.setCitta(rs.getString("citta"));
					result.setCAP(rs.getString("CAP"));
					result.setnCivico(rs.getString("numCivico"));
					result.setProvincia(rs.getString("provincia"));
				}
			}
		}
		return result;

	}

	@Override
	public Collection<IndirizzoBean> doRetrieveAll(String order) throws Exception {
		ArrayList<IndirizzoBean> categorie = new ArrayList<>();
		IndirizzoBean result = new IndirizzoBean();
		String sql = "SELECT * FROM " + TABLE_NAME;

		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				ResultSet rs = statement.executeQuery();

				while(rs.next()) {
					result.setId(rs.getInt("id"));
					result.setVia(rs.getString("via"));
					result.setCitta(rs.getString("citta"));
					result.setCAP(rs.getString("CAP"));
					result.setnCivico(rs.getString("numCivico"));
					result.setProvincia(rs.getString("provincia"));
					categorie.add(result);
				}
			}	
		}

		return categorie;
		
	}

	@Override
	public void doUpdate(IndirizzoBean bean) throws SQLException {

		
	}

}
