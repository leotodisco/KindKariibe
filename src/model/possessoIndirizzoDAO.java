package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//dao per linkare un utente ad un indirizzo
public class possessoIndirizzoDAO{
	
	private static final String TABLE_NAME = "possessoIndirizzo";
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

	public void doSave(String codiceFiscale, int idIndirizzo) throws SQLException {
		String sql="INSERT INTO kindkaribe.possessoIndirizzo (utente, indirizzo) VALUES ('"+codiceFiscale+"', '"+idIndirizzo+"')";
		
		try(Connection con= ds.getConnection()){
			try(PreparedStatement ps= con.prepareStatement(sql)){
				ps.execute();
			}
		}
		
	}

	public boolean doDelete(String codiceFiscale, int idIndirizzo) throws SQLException {
		String sql= "DELETE FROM `kindkaribe`.`possessoIndirizzo` WHERE (`utente` = '"+codiceFiscale+"') and (`indirizzo` = '"+idIndirizzo+"')";
		try(Connection con= ds.getConnection()){
			try(PreparedStatement ps= con.prepareStatement(sql)){
				return ps.execute();
			}
		}
	}


	public void doUpdate(Object bean) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
}
