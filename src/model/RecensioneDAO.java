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

import beans.ProdottoBean;
import beans.RecensioneBean;

public class RecensioneDAO implements ModelInterface<RecensioneBean> {
	private static final String TABLE_NAME = "recensione";
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
	public void doSave(RecensioneBean bean) throws SQLException {
		String controllaAcquisto = "SELECT * FROM utente U JOIN (SELECT * FROM ordine O JOIN composizione C ON O.idOrdine=C.ordine WHERE C.prodotto = ?) J "+
									"ON U.codiceFiscale= J.utente WHERE U.codiceFiscale=?";
		
		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(controllaAcquisto)){	
				ps.setInt(1, bean.getProdotto().getId());
				ps.setString(2, bean.getUtente().getCodiceFiscale());

				ps.executeQuery();
			}
		}
		

		String sqlInsert = "INSERT INTO " + TABLE_NAME + " (prodotto, utente, voto, testo) VALUES (?,?,?,?)";
		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sqlInsert)){	
				ps.setInt(1, bean.getProdotto().getId());
				ps.setString(2, bean.getUtente().getCodiceFiscale());
				ps.setInt(3, bean.getVoto());
				ps.setString(4, bean.getTesto());

				ps.execute();
			}
		}
		
	}

	@Override
	public boolean doDelete(String arg) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RecensioneBean doRetrieveByKey(String arg) throws Exception {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE idRecensione = ? ";
		RecensioneBean result = new RecensioneBean();

		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, arg);
				ResultSet rs = statement.executeQuery();
				ProdottoDAO prodDAO = new ProdottoDAO();
				UserDAO daoUser = new UserDAO();

				if(rs.next()) {
					result.setIdRecensione(rs.getInt("idRecensione"));
					result.setVoto(rs.getInt("voto"));
					result.setTesto(rs.getString("testo"));
					result.setProdotto(prodDAO.doRetrieveByKey(rs.getString("prodotto")));
					result.setUtente(daoUser.doRetrieveByKey(rs.getString("utente")));
				}
			}
		}
		return result;
	}

	@Override
	public Collection<RecensioneBean> doRetrieveAll(String order) throws Exception {
		order = order.isEmpty() ? "idRecensione" : order;
		String sql = "SELECT * FROM " + TABLE_NAME + "ORDER BY ?";
		List<RecensioneBean> lista = new ArrayList<>();
		RecensioneBean result = new RecensioneBean();
		
		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, order);
				ResultSet rs = statement.executeQuery();
				ProdottoDAO prodDAO = new ProdottoDAO();
				UserDAO daoUser = new UserDAO();

				while(rs.next()) {
					result.setIdRecensione(rs.getInt("idRecensione"));
					result.setVoto(rs.getInt("voto"));
					result.setTesto(rs.getString("testo"));
					result.setProdotto(prodDAO.doRetrieveByKey(rs.getString("prodotto")));
					result.setUtente(daoUser.doRetrieveByKey(rs.getString("utente")));
					
					lista.add(result);
				}
			}
		}
		
		return null;
	}
	
	public ArrayList<RecensioneBean> getRecensioniByProduct(ProdottoBean prod) throws Exception{
		String sql = "SELECT * FROM "+ TABLE_NAME + " WHERE prodotto = ?";
		ArrayList<RecensioneBean> lista = new ArrayList<>();
		RecensioneBean result = new RecensioneBean();
		
		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setInt(1, prod.getId());
				ResultSet rs = statement.executeQuery();
				ProdottoDAO prodDAO = new ProdottoDAO();
				UserDAO daoUser = new UserDAO();
				
				while(rs.next()) {
					result.setIdRecensione(rs.getInt("idRecensione"));
					result.setVoto(rs.getInt("voto"));
					result.setTesto(rs.getString("testo"));
					try {
						result.setProdotto(prodDAO.doRetrieveByKey(rs.getString("prodotto")));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						result.setUtente(daoUser.doRetrieveByKey(rs.getString("utente")));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					lista.add(result);
				}
			}
		}
		
		
		return lista;
	}

	@Override
	public void doUpdate(RecensioneBean bean) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public static Double getVotoMedio(ProdottoBean prodotto) throws Exception {
		String sql = "SELECT AVG(voto) AS media FROM recensione WHERE prodotto=?";
		double result = 0;
		
		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setInt(1, prodotto.getId());
				ResultSet rs = statement.executeQuery();
				
				if(rs.next()) {
					result = rs.getDouble("media");
				}
			}
		}
		
		return result;
	}
	


}
