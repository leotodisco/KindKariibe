package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import beans.CategoriaBean;
import beans.CorriereBean;

public class CorriereDAO implements ModelInterface<CorriereBean> {
	private static final String TABLE_NAME = "corriere";

	@Override
	public void doSave(CorriereBean bean) throws SQLException {
	String sql = "INSERT INTO " + TABLE_NAME + "('idCorriere','azienda',nTelefono) VALUES (?,?,?))";
		
		try(Connection con = DriverManagerConnectionPool.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){	
				ps.setString(1, String.valueOf(bean.getId()));
				ps.setString(2, bean.getAzienda());
				ps.setString(3, bean.getnTelefono());
				ps.execute();
			}
		}
		
	}

	@Override
	public boolean doDelete(String arg) throws SQLException {
		String sql = "DELETE FROM "+ TABLE_NAME +" WHERE `idCorriere`= ? ";

		try(Connection con = DriverManagerConnectionPool.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, arg);
				return ps.execute();
			}
		}
	}

	@Override
	public CorriereBean doRetrieveByKey(String id) throws Exception {
		String sql = "SELECT * FROM " + TABLE_NAME + "WHERE idCorriere = ?";
		CorriereBean bean = new CorriereBean();
		
		try(Connection conn = DriverManagerConnectionPool.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, id);
				
				ResultSet rs = statement.executeQuery();

				if(rs.next()) {
					bean.setId(Integer. valueOf(rs.getString("idCorriere")));
					bean.setAzienda(rs.getString("azienda"));
					bean.setnTelefono(rs.getString("nTelefono"));
				}
			}	
		}
		
		return bean;
	}

	@Override
	public Collection<CorriereBean> doRetrieveAll(String order) throws Exception {
		List<CorriereBean> corrieri = new ArrayList<>();
		CorriereBean buffer = new CorriereBean();
		order = order.isEmpty() ? "nome" : order;
		String sql = "SELECT * FROM " + TABLE_NAME + "Order by" + order;

		try(Connection conn = DriverManagerConnectionPool.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				ResultSet rs = statement.executeQuery();

				while(rs.next()) {
					buffer.setId(Integer. valueOf(rs.getString("idCorriere")));
					buffer.setAzienda(rs.getString("azienda"));
					buffer.setnTelefono(rs.getString("nTelefono"));
					corrieri.add(buffer);
				}
			}	
		}

		return corrieri;
	}

	@Override
	public void doUpdate(CorriereBean bean) throws SQLException {
		String sql = "UPDATE "+TABLE_NAME+"SET 'azienda' = ?,'nTelefono' = ? WHERE idCorriere = ?";
		
		try(Connection conn = DriverManagerConnectionPool.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, bean.getAzienda());
				statement.setString(2, bean.getnTelefono());
				statement.setString(3, String.valueOf(bean.getId()));
				statement.executeUpdate();
			}	
		}
		
	}

}
