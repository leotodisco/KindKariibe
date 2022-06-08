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
	
	public ArrayList<MetodoPagamentoBean> doRetriveByUtente(String codiceFiscale) throws SQLException{
		String sql="SELECT * FROM metodopagamento INNER JOIN datipagamento ON idMetodoPagamento = metodo WHERE utente= '"+codiceFiscale+"'";
		
		try(Connection con= ds.getConnection()){
			ArrayList<MetodoPagamentoBean> metodi= new ArrayList<>();
			
			try(PreparedStatement ps=con.prepareStatement(sql)){
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					MetodoPagamentoBean metodo= new MetodoPagamentoBean();
					
					metodo.setidMetodoPagamento(rs.getInt("idMetodoPagamento"));
					metodo.setTipo(rs.getString("tipo"));
					metodo.setNomeIntestatario(rs.getString("nomeIntestatario"));
					metodo.setNumeroCarta(rs.getString("numeroCarta"));
					metodo.setMeseScadenza(rs.getInt("meseScadenza"));
					metodo.setAnnoScadenza(rs.getInt("annoScadenza"));
					metodo.setIban(rs.getString("IBAN"));
					metodo.setCausale(rs.getString("causale"));
					metodo.setCircuito(rs.getString("circuito"));
					metodo.setCVV(rs.getInt("CVV"));
					
					metodi.add(metodo);
				}
				
				return metodi;
			}
			
		}
	}

	@Override
	public void doSave(MetodoPagamentoBean bean) throws SQLException {
	//non usare
	
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
	
	
	public int doSaveI(MetodoPagamentoBean bean) throws SQLException {
		String sql = new String();
		
		String IDSQL = "SELECT idMetodoPagamento FROM kindkaribe.metodopagamento ORDER BY idImmagine DESC LIMIT 1";
		
		if(bean.getTipo().equals("Carta")) {
			sql = "INSERT INTO " + TABLE_NAME + "('idMetodoPagamento','tipo','nomeIntestatario','numeroCarta','meseScadenza', "
					+ " 'annoScadenza','IBAN',', "
					+ " 'circuito','CVV','idMetodoPagamento') "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?)";

			try(Connection con = ds.getConnection()){
				try(PreparedStatement ps = con.prepareStatement(IDSQL)){	
					
					ResultSet id = ps.executeQuery();
					
					id.next();
					int ID = id.getInt("idMetodoPagamento") + 1;
					
					PreparedStatement ps2 = con.prepareStatement(sql);
					
					ps2.setInt(1, bean.getidMetodoPagamento());
					ps2.setString(2, bean.getTipo());
					ps2.setString(3, bean.getNomeIntestatario());
					ps2.setString(4, bean.getNumeroCarta());
					ps2.setInt(5, bean.getMeseScadenza());
					ps2.setInt(6, bean.getAnnoScadenza());
					ps2.setString(7, bean.getIban());
					ps2.setString(8, bean.getCircuito());
					ps2.setInt(9, bean.getCVV());
					ps2.setInt(10, ID);

					ps2.execute();
					return ID;
				}
			}
		}
		
		else { //se il metodo è bonifico
			sql = "INSERT INTO " + TABLE_NAME + " ('idMetodoPagamento','tipo','nomeIntestatario', "
					+ "'IBAN','causale','idMetodoPagamento') "
					+ " VALUES (?,?,?,?,?,?) ";

			try(Connection con = ds.getConnection()){
				try(PreparedStatement ps = con.prepareStatement(IDSQL)){
					
					ResultSet id = ps.executeQuery();
					
					id.next();
					int ID = id.getInt("idMetodoPagamento") + 1;
					
					PreparedStatement ps2 = con.prepareStatement(sql);
					
					ps2.setInt(1, bean.getidMetodoPagamento());
					ps2.setString(2, bean.getTipo());
					ps2.setString(3, bean.getNomeIntestatario());
					ps2.setString(4, bean.getIban());
					ps2.setString(5, bean.getCausale());
					ps2.setLong(6, ID);

					ps2.execute();
					return ID;
				}
			}
			
		}
	}
	
	
	
	
	
}