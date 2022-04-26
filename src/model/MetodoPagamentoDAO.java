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

import beans.CorriereBean;
import beans.MetodoPagamentoBean;


public class MetodoPagamentoDAO implements ModelInterface<MetodoPagamentoBean>{
	
	private static final String TABLE_NAME = "metodoPagamento";
	
	@Override
	public void doSave(MetodoPagamentoBean bean) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + "('idMetodoPagamento','tipo','nomeIntestatario','numeroCarta','meseScadenza',"
				+ "'annoScadenza','iban','causale',"
				+ "'circuito','dataPagamento','CVV') "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		try(Connection con = DriverManagerConnectionPool.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){	
				ps.setString(1, String.valueOf(bean.getidMetodoPagamento()));
				ps.setString(2, bean.getTipo());
				ps.setString(3, bean.getNomeIntestatario());
				ps.setString(4,String.valueOf(bean.getNumeroCarta()));
				ps.setString(5, String.valueOf(bean.getMeseScadenza()));
				ps.setString(6, String.valueOf(bean.getAnnoScadenza()));
				ps.setString(7, bean.getIban());
				ps.setString(8,bean.getCausale());
				ps.setString(8, bean.getCircuito());
				ps.setDate(10, (java.sql.Date) bean.getDataPagamento());
				ps.setInt(11, Integer.parseInt(bean.getCVV()));
				
				ps.execute();
			}
		}
	}
	
	@Override
	public boolean doDelete(String arg) throws SQLException {
		String sql = "DELETE FROM "+ TABLE_NAME +" WHERE `idMetodoPagamento`= ? ";

		try(Connection con = DriverManagerConnectionPool.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, arg);
				return ps.execute();
			} 
		}
	}
	
	
	@Override
	public MetodoPagamentoBean doRetrieveByKey(String id) throws Exception {
		String sql = "SELECT * FROM " + TABLE_NAME + "WHERE idMetodoPagamento = ?";
		MetodoPagamentoBean bean = new MetodoPagamentoBean();
		
		try(Connection conn = DriverManagerConnectionPool.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, id);
				
				ResultSet rs = statement.executeQuery();

				if(rs.next()) {
					bean.setidMetodoPagamento(Integer. valueOf(rs.getString("idMetodoPagamento")));
					bean.setTipo(rs.getString("tipo"));
					bean.setNomeIntestatario(rs.getString("nomeIntestatario"));
					bean.setNumeroCarta(Integer. valueOf(rs.getString("numeroCarta")));
					bean.setMeseScadenza(Integer. valueOf(rs.getString("meseScadenza")));
					bean.setAnnoScadenza(Integer. valueOf(rs.getString("annoScadenza")));
					bean.setIban(rs.getString("iban"));
					bean.setCausale(rs.getString("causale"));
					bean.setCircuito(rs.getString("circuito"));
					bean.setDataPagamento(rs.getDate("dataPagamento"));
					bean.setCVV(rs.getString("CVV"));
				}
			}	
		}
		
		return bean;
	}
	
	@Override
	public Collection<MetodoPagamentoBean> doRetrieveAll(String order) throws Exception {
		List<MetodoPagamentoBean> metodi = new ArrayList<>();
		MetodoPagamentoBean bean = new MetodoPagamentoBean();
		String sql = "SELECT * FROM " + TABLE_NAME + "ORDER BY ?";
		order = order.isEmpty() ? "nome" : order;

		try(Connection conn = DriverManagerConnectionPool.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, order);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next())
				{
					bean.setidMetodoPagamento(Integer. valueOf(rs.getString("idMetodoPagamento")));
					bean.setTipo(rs.getString("tipo"));
					bean.setNomeIntestatario(rs.getString("nomeIntestatario"));
					bean.setNumeroCarta(Integer. valueOf(rs.getString("numeroCarta")));
					bean.setMeseScadenza(Integer. valueOf(rs.getString("meseScadenza")));
					bean.setAnnoScadenza(Integer. valueOf(rs.getString("annoScadenza")));
					bean.setIban(rs.getString("iban"));
					bean.setCausale(rs.getString("causale"));
					bean.setCircuito(rs.getString("circuito"));
					bean.setDataPagamento(rs.getDate("dataPagamento"));
					bean.setCVV(rs.getString("CVV"));
					
					metodi.add(bean);
				}
			}
		}
		return metodi;
	}
	
	@Override
	public void doUpdate( MetodoPagamentoBean bean) throws SQLException {
		String sql = "UPDATE "+TABLE_NAME+"SET 'tipo' = ?,'nomeIntestatario' = ?, 'numeroCarta' = ?, 'meseScadenza' = ?, 'annoScadenza' = ?, 'iban' =?, 'causale' = ?"
				+ " WHERE idMetodoPagamento = ?";
		
		try(Connection conn = DriverManagerConnectionPool.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, bean.getTipo());
				statement.setString(2, bean.getNomeIntestatario());
				statement.setString(3,String.valueOf(bean.getNumeroCarta()));
				statement.setString(4, String.valueOf(bean.getMeseScadenza()));
				statement.setString(5, String.valueOf(bean.getAnnoScadenza()));
				statement.setString(6, bean.getIban());
				statement.setString(7,bean.getCausale());
				statement.setString(8, bean.getCircuito());
				statement.setDate(9, (java.sql.Date) bean.getDataPagamento());
				statement.setInt(10, Integer.parseInt(bean.getCVV()));
				statement.setString(11, String.valueOf(bean.getidMetodoPagamento()));
				
				statement.executeUpdate();
			}	
		}
		
	}
}