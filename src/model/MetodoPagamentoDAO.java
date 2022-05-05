package model;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import beans.CorriereBean;
import beans.MetodoPagamentoBean;


public class MetodoPagamentoDAO implements ModelInterface<MetodoPagamentoBean>{
	private static DataSource ds;
	private static final String TABLE_NAME = "metodoPagamento";

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
	public void doSave(MetodoPagamentoBean bean) throws SQLException {
		String sql = new String();
		
		if(bean.getTipo().equals("Carta")) {
			sql = "INSERT INTO " + TABLE_NAME + "('idMetodoPagamento','tipo','nomeIntestatario','numeroCarta','meseScadenza', "
					+ " 'annoScadenza','IBAN',', "
					+ " 'circuito','CVV') "
					+ " VALUES (?,?,?,?,?,?,?,?,?)";

			try(Connection con = ds.getConnection()){
				try(PreparedStatement ps = con.prepareStatement(sql)){	
					ps.setInt(1, bean.getidMetodoPagamento());
					ps.setString(2, bean.getTipo());
					ps.setString(3, bean.getNomeIntestatario());
					ps.setString(4, bean.getNumeroCarta());
					ps.setInt(5, bean.getMeseScadenza());
					ps.setInt(6, bean.getAnnoScadenza());
					ps.setString(7, bean.getIban());
					ps.setString(8, bean.getCircuito());
					ps.setInt(9, bean.getCVV());

					ps.execute();
				}
			}
		}
		
		else { //se il metodo è bonifico
			sql = "INSERT INTO " + TABLE_NAME + " ('idMetodoPagamento','tipo','nomeIntestatario', "
					+ "'IBAN','causale',) "
					+ " VALUES (?,?,?,?,?) ";

			try(Connection con = ds.getConnection()){
				try(PreparedStatement ps = con.prepareStatement(sql)){	
					ps.setInt(1, bean.getidMetodoPagamento());
					ps.setString(2, bean.getTipo());
					ps.setString(3, bean.getNomeIntestatario());
					ps.setString(4, bean.getIban());
					ps.setString(5, bean.getCausale());

					ps.execute();
				}
			}
			
		}
	}

	@Override
	public boolean doDelete(String arg) throws SQLException {
		String sql = "DELETE FROM "+ TABLE_NAME +" WHERE `idMetodoPagamento`= ? ";

		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, arg);
				return ps.execute();
			} 
		}
	}


	@Override
	public MetodoPagamentoBean doRetrieveByKey(String id) throws Exception {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE idMetodoPagamento = ?";
		MetodoPagamentoBean bean = new MetodoPagamentoBean();

		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, id);

				ResultSet rs = statement.executeQuery();

				if(rs.next()) {
					bean.setidMetodoPagamento(Integer. valueOf(rs.getString("idMetodoPagamento")));
					bean.setTipo(rs.getString("tipo"));
					bean.setNomeIntestatario(rs.getString("nomeIntestatario"));
					bean.setNumeroCarta(rs.getString("numeroCarta"));
					bean.setMeseScadenza(rs.getInt("meseScadenza"));
					bean.setAnnoScadenza(rs.getInt("annoScadenza"));
					bean.setIban(rs.getString("IBAN"));
					bean.setCausale(rs.getString("causale"));
					bean.setCircuito(rs.getString("circuito"));
					bean.setCVV(rs.getInt("CVV"));
				}
			}	
		}
		return bean;
	}

	@Override
	public Collection<MetodoPagamentoBean> doRetrieveAll(String order) throws Exception {
		List<MetodoPagamentoBean> metodi = new ArrayList<>();
		String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY ?";
		order = order.isEmpty() ? "nome" : order;

		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, order);
				ResultSet rs = statement.executeQuery();

				while(rs.next())
				{
					MetodoPagamentoBean bean = new MetodoPagamentoBean();
					bean.setidMetodoPagamento(Integer. valueOf(rs.getString("idMetodoPagamento")));
					bean.setTipo(rs.getString("tipo"));
					bean.setNomeIntestatario(rs.getString("nomeIntestatario"));
					bean.setNumeroCarta(rs.getString("numeroCarta"));
					bean.setMeseScadenza(rs.getInt("meseScadenza"));
					bean.setAnnoScadenza(rs.getInt("annoScadenza"));
					bean.setIban(rs.getString("IBAN"));
					bean.setCausale(rs.getString("causale"));
					bean.setCircuito(rs.getString("circuito"));
					bean.setCVV(rs.getInt("CVV"));

					metodi.add(bean);
				}
			}
		}
		return metodi;
	}

	@Override
	public void doUpdate( MetodoPagamentoBean bean) throws SQLException {
		String sql = new String();
		
		sql = bean.getTipo().equals("Carta") 
				? "UPDATE " +TABLE_NAME+ "SET 'tipo' = ?,'nomeIntestatario' = ?, 'numeroCarta' = ?, 'meseScadenza' = ?, 'annoScadenza' = ?,"
				+ " 'iban' =?, 'causale' = ?, 'circuito' = ?, 'CVV' = ? "
				+ " WHERE idMetodoPagamento = ?"
				
				: "UPDATE " +TABLE_NAME+ "SET 'tipo' = ?,'nomeIntestatario' = ?, "
				+ " 'IBAN' = ?, 'causale' = ?;";

		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, bean.getTipo());
				statement.setString(2, bean.getNomeIntestatario());
				statement.setString(3, bean.getNumeroCarta());
				statement.setInt(4, bean.getMeseScadenza());
				statement.setInt(5, bean.getAnnoScadenza());
				statement.setString(6, bean.getIban());
				statement.setString(7,bean.getCausale());
				statement.setString(8, bean.getCircuito());
				statement.setInt(9, bean.getCVV());
				statement.setInt(10, bean.getidMetodoPagamento());

				statement.executeUpdate();
			}	
		}

	}
}