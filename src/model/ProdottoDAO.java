package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.CategoriaBean;
import beans.CorriereBean;
import beans.ProdottoBean;

public class ProdottoDAO implements ModelInterface<ProdottoBean> {
	private static final String TABLE_NAME = "prodotto";
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
	public void doSave(ProdottoBean bean) throws SQLException {
		String sql = new String();

		if(bean.getTipo().toLowerCase().equals("pasticceria")) {
			sql = "INSERT INTO" + TABLE_NAME 
					+ "('nome','tipo','descrizione','quantitaResidua','prezzo','IVA','peso','categoria')"
					+ "VALUES (?,?,?,?,?,?,?,?)";	

			try(Connection con = ds.getConnection()){
				try(PreparedStatement ps = con.prepareStatement(sql)){
					ps.setString(1, bean.getNome());
					ps.setString(2, bean.getTipo());
					ps.setString(3, bean.getDescrizione());
					ps.setDouble(4, bean.getQuantitaResidua());
					ps.setDouble(5, bean.getPrezzo());
					ps.setDouble(6, bean.getIVA());
					ps.setDouble(7, bean.getPeso());
					ps.setString(8, bean.getCategoria().getNome());
					//TO DO immagini 

					ps.executeUpdate();
				}
			}
		}

		else {
			sql = "INSERT INTO" + TABLE_NAME 
					+ "('nome','tipo','descrizione','quantitaResidua','prezzo','IVA','peso')"
					+ "VALUES (?,?,?,?,?,?,?)";

			try(Connection con = ds.getConnection()){
				try(PreparedStatement ps = con.prepareStatement(sql)){
					ps.setString(1, bean.getNome());
					ps.setString(2, bean.getTipo());
					ps.setString(3, bean.getDescrizione());
					ps.setDouble(4, bean.getQuantitaResidua());
					ps.setDouble(5, bean.getPrezzo());
					ps.setDouble(6, bean.getIVA());
					ps.setDouble(7, bean.getPeso());
					//ps.setString(8, bean.getGusti()); //gusti deve andare in "costituzione"
					//TO DO immagini 

					ps.execute();
				}
			}
		}
	}

	@Override
	public boolean doDelete(String arg) throws SQLException {
		String sql = "DELETE FROM "+ TABLE_NAME +" WHERE `nome`= ?";
		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, arg);
				return ps.execute();
			}
		}
	}

	@Override
	public ProdottoBean doRetrieveByKey(String nome) throws Exception {
		String sql = "SELECT *,C.descrizione AS Cdesc, C.nome as Cnome FROM prodotto FULL JOIN categoria C ON categoria = C.nome"
				+ "WHERE nome = ?;";
		ProdottoBean bean = new ProdottoBean();
		CategoriaBean buffer = new CategoriaBean();

		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, nome);

				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					bean.setNome(rs.getString("nome"));
					buffer.setNome(rs.getString("Cnome"));
					buffer.setDescrizione(rs.getString("Cdesc"));
					Optional<CategoriaBean> optional = Optional.of(buffer); //categoria può essere null
					bean.setCategoria(optional);
					bean.setPrezzo(rs.getDouble("prezzo"));
					bean.setIVA(rs.getDouble("IVA"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setTipo(rs.getString("tipo"));
					bean.setQuantitaResidua(rs.getDouble("quantitaDisponibili"));
					ArrayList<String> elencoPathImmagini = new ArrayList<>();
					String sqlImmagini = "SELECT url FROM possessoImmagine PI JOIN immagine"
							+ " ON PI.prodotto = ?;";

					//ottieni url immagini
					try(PreparedStatement stmt = conn.prepareStatement(sqlImmagini)){
						stmt.setString(1, nome);
						ResultSet images = stmt.executeQuery();
						while(images.next()) {
							elencoPathImmagini.add(images.getString("url"));
						}
						bean.setPathImage(elencoPathImmagini);
					}

					//ottieni peso
					String sqlPeso = "SELECT peso FROM estensione WHERE prodotto = ?;";
					try(PreparedStatement statement2 = conn.prepareStatement(sqlPeso)){
						statement.setString(1, nome);
						ResultSet weight = statement2.executeQuery();
						bean.setPeso(weight.getDouble("peso"));
					}

					//ottieni gusti
					String sqlGusti = "SELECT gusto FROM costituzione WHERE prodotto = ?;";
					try(PreparedStatement statement3 = conn.prepareStatement(sqlGusti)){
						statement3.setString(1, nome);
						ArrayList<String> elencoGusti = new ArrayList<>();
						ResultSet gusti = statement3.executeQuery();

						while(gusti.next()) {
							elencoGusti.add(gusti.getString("gusto"));
						}
						bean.setGusti(Optional.of(elencoGusti));
					}
				}
			}	
		}
		return bean;
	}

	@Override
	public Collection<ProdottoBean> doRetrieveAll(String order) throws Exception {
		String sql = "SELECT *,C.descrizione AS Cdesc, C.nome as Cnome FROM prodotto FULL JOIN categoria C "
				+ "ON categoria = C.nome ";
		order = order.isEmpty() ? "C.nome" : order;
		ProdottoBean bean = new ProdottoBean();
		CategoriaBean buffer = new CategoriaBean();
		ArrayList<ProdottoBean> result = new ArrayList<>();

		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				//statement.setString(1, order);

				ResultSet rs = statement.executeQuery();
				while(rs.next()) {
					bean.setNome(rs.getString("nome"));
					buffer.setNome(rs.getString("Cnome"));
					buffer.setDescrizione(rs.getString("Cdesc"));
					Optional<CategoriaBean> optional = Optional.of(buffer); //categoria può essere null
					bean.setCategoria(optional);
					bean.setPrezzo(rs.getDouble("prezzo"));
					bean.setIVA(rs.getDouble("IVA"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setTipo(rs.getString("tipo"));
					bean.setQuantitaResidua(rs.getDouble("quantitaDisponibili"));
					ArrayList<String> elencoPathImmagini = new ArrayList<>();
					String sqlImmagini = "SELECT url FROM possessoImmagine PI JOIN immagine"
							+ " ON PI.prodotto = ?;";
					//ottieni url immagini
					try(PreparedStatement stmt = conn.prepareStatement(sqlImmagini)){
						stmt.setString(1, bean.getNome());
						ResultSet images = stmt.executeQuery();
						while(images.next()) {
							elencoPathImmagini.add(images.getString("url"));
						}
						bean.setPathImage(elencoPathImmagini);
					}
					//ottieni peso
					String sqlPeso = "SELECT peso FROM estensione WHERE prodotto = ?;";
					try(PreparedStatement statement2 = conn.prepareStatement(sqlPeso)){
						statement2.setString(1, bean.getNome());
						ResultSet weight = statement2.executeQuery();
						if(weight.next())
						{
							bean.setPeso(weight.getDouble("peso"));						
						}
					}

					//ottieni gusti
					String sqlGusti = "SELECT gusto FROM costituzione WHERE prodotto = ?;";
					try(PreparedStatement statement3 = conn.prepareStatement(sqlGusti)){
						statement3.setString(1, bean.getNome());
						ArrayList<String> elencoGusti = new ArrayList<>();
						ResultSet gusti = statement3.executeQuery();

						while(gusti.next()) {
							elencoGusti.add(gusti.getString("gusto"));
						}
						bean.setGusti(Optional.of(elencoGusti));
					}

					result.add(bean);
				}
			}	
		}
		return result;
	}

	@Override
	public void doUpdate(ProdottoBean bean) throws SQLException {
		String sql = new String();

		if(bean.getTipo().toLowerCase().equals("pasticceria")) {
			sql = "INSERT INTO" + TABLE_NAME 
					+ "('nome','tipo','descrizione','quantitaResidua','prezzo','IVA','peso','categoria')"
					+ "VALUES (?,?,?,?,?,?,?,?)";	

			try(Connection con = ds.getConnection()){
				try(PreparedStatement ps = con.prepareStatement(sql)){
					ps.setString(1, bean.getNome());
					ps.setString(2, bean.getTipo());
					ps.setString(3, bean.getDescrizione());
					ps.setDouble(4, bean.getQuantitaResidua());
					ps.setDouble(5, bean.getPrezzo());
					ps.setDouble(6, bean.getIVA());
					ps.setDouble(7, bean.getPeso());
					ps.setString(8, bean.getCategoria().getNome());
					//TO DO immagini 

					ps.executeUpdate();
				}
			}
		}

		else {
			sql = "INSERT INTO" + TABLE_NAME 
					+ "('nome','tipo','descrizione','quantitaResidua','prezzo','IVA','peso')"
					+ "VALUES (?,?,?,?,?,?,?)";

			try(Connection con = ds.getConnection()){
				try(PreparedStatement ps = con.prepareStatement(sql)){
					ps.setString(1, bean.getNome());
					ps.setString(2, bean.getTipo());
					ps.setString(3, bean.getDescrizione());
					ps.setDouble(4, bean.getQuantitaResidua());
					ps.setDouble(5, bean.getPrezzo());
					ps.setDouble(6, bean.getIVA());
					ps.setDouble(7, bean.getPeso());
					//ps.setString(8, bean.getGusti()); //gusti deve andare in "costituzione"
					//TO DO immagini 

					ps.executeUpdate();
				}
			}
		}
		
	}
}
