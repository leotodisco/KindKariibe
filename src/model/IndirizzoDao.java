package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.IndirizzoBean;

public class IndirizzoDao implements ModelInterface<IndirizzoBean> {
	private static final String TABLE_NAME = "indirizzo";
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
		String sql = "INSERT INTO " + TABLE_NAME + " (via, citta, CAP, numCivico, provincia) "
				+ " VALUES (?,?,?,?,?) ";
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


	public ArrayList<IndirizzoBean> doRetriveByUtente(String codiceFiscale) throws SQLException{
		String sql= "SELECT * FROM indirizzo INNER JOIN possessoIndirizzo ON indirizzo.id=possessoIndirizzo.indirizzo WHERE utente= '"+ codiceFiscale+"'";

		try(Connection con= ds.getConnection()){
			try(PreparedStatement ps= con.prepareStatement(sql)){
				ResultSet rs= ps.executeQuery();
				ArrayList<IndirizzoBean> indirizzi= new ArrayList<>();

				while(rs.next()) {
					IndirizzoBean indirizzo=new IndirizzoBean();

					indirizzo.setId(rs.getInt("id"));
					indirizzo.setVia(rs.getString("via"));
					indirizzo.setCitta(rs.getString("citta"));
					indirizzo.setProvincia(rs.getString("provincia"));
					indirizzo.setnCivico(rs.getString("numCivico"));
					indirizzo.setCAP(rs.getString("CAP"));

					indirizzi.add(indirizzo);
				}
				return indirizzi;
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
		String sql = "UPDATE utente SET via = ?,citta = ?, provincia = ?, CAP = ?, " +
				" numCivico = ? WHERE id = ?" ;

		try(Connection connection = ds.getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){	
				preparedStatement.setString(1, bean.getVia());
				preparedStatement.setString(2, bean.getCitta());
				preparedStatement.setString(3, bean.getProvincia());
				preparedStatement.setString(4, bean.getCAP());
				preparedStatement.setString(5, bean.getnCivico());
				preparedStatement.setInt(6, bean.getId());

				preparedStatement.execute();
			}
		}
	}
	
	public int ottieniKeyAutoIncrement() throws SQLException {
		String sql2 = "SELECT MAX(id) as id from indirizzo";
		try(Connection connect = ds.getConnection()){
			try(PreparedStatement statement = connect.prepareStatement(sql2)){	
				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					return rs.getInt("id");
				}
			}
		}
		return 0;
		
	}

}
