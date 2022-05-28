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

import beans.DatiFiscaliBean;
import beans.MetodoPagamentoBean;

public class DatiFiscaliDAO implements ModelInterface<DatiFiscaliBean>{

	private static final String TABLE_NAME = "datiFiscali";
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
	public void doSave(DatiFiscaliBean bean) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " (metodoPagamento, indirizzoFatturazione) VALUES (?,?)";

		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, bean.getIdMetodoPagamento());
				ps.setInt(2, bean.getIdIndirizzoFatturazione());
				ps.execute();

			}
		}
		
	}
	
	public DatiFiscaliBean theLastInsert() throws SQLException {
		String sql= "SELECT * FROM "+ TABLE_NAME + " ORDER BY idDatiFiscali DESC LIMIT 1 ";
		DatiFiscaliBean bean= new DatiFiscaliBean();
		
		try(Connection con= ds.getConnection()){
			try(PreparedStatement ps= con.prepareStatement(sql)){
				ResultSet rs= ps.executeQuery();
				
				if(rs.next()) {
					bean.setIdDatiFiscali(rs.getInt("idDatiFiscali"));
					bean.setIdIndirizzoFatturazione(rs.getInt("indirizzoFatturazione"));
					bean.setIdMetodoPagamento(rs.getInt("metodoPagamento"));
				}

			}
		}
		return bean;
	}

	@Override
	public boolean doDelete(String arg) throws SQLException {
		String sql ="DELETE FROM " +TABLE_NAME+ " WHERE idDatiFiscali = ?";

		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, Integer.valueOf(arg));
				return ps.execute();
			}
		}
	}

	@Override
	public DatiFiscaliBean doRetrieveByKey(String arg) throws Exception {
		String sql = "SELECT * FROM " +TABLE_NAME+ " WHERE idDatiFiscali = ?";
		DatiFiscaliBean dt = new DatiFiscaliBean();

		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, Integer.valueOf(arg));
				ResultSet rs = ps.executeQuery();

				if(rs.next()) {
					dt.setIdDatiFiscali(rs.getInt("idDatiFiscali"));
					dt.setIdIndirizzoFatturazione(rs.getInt("indirizzo"));
					dt.setIdMetodoPagamento(rs.getInt("idMetodoPagamento"));
				}


			}
		}

		return dt;
	}

	@Override
	public Collection<DatiFiscaliBean> doRetrieveAll(String order) throws Exception {
		List<DatiFiscaliBean> dati = new ArrayList<>();
		
		
		String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY idDatiFiscali ?";
		order = order.isEmpty() ? "nome" : order;
		
		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, Integer.valueOf(order));
				ResultSet rs = ps.executeQuery();


				
				while(rs.next()) {
					DatiFiscaliBean dt = new DatiFiscaliBean();
					dt.setIdDatiFiscali(rs.getInt("idDatiFiscali"));
					dt.setIdIndirizzoFatturazione(rs.getInt("indirizzo"));
					dt.setIdMetodoPagamento(rs.getInt("idMetodoPagamento"));
					
					dati.add(dt);
				}
				
			}
		}
		
		return dati;
	}

	@Override
	public void doUpdate(DatiFiscaliBean bean) throws SQLException {
		String sql = "UPDATE " +TABLE_NAME+ " SET metodoPagamento = ?, indirizzo = ?"
				+ " WHERE idDatiFiscali = ?";

		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, bean.getIdMetodoPagamento());
				ps.setInt(2, bean.getIdIndirizzoFatturazione());
				ps.setInt(3, bean.getIdDatiFiscali());

				ps.executeUpdate();
			}
		}

	}

}
