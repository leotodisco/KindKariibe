package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.CategoriaBean;
import beans.GustoBean;
import beans.ImmagineBeans;
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
		//non usare
			
	}

	@Override
	public boolean doDelete(String arg) throws SQLException {
		String sql = "DELETE FROM "+ TABLE_NAME +" WHERE `id`= ?";
		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, arg);
				return ps.execute();
			}
		}
	}

	@Override
	public ProdottoBean doRetrieveByKey(String nome) throws Exception {
		String sql = "SELECT prodotto.*, categoria.nome as Cnome, categoria.descrizione as Cdesc "
				+ "FROM prodotto JOIN categoria "
				+"ON categoria.nome = prodotto.categoria "
				+ "where prodotto.id = ?";
		ProdottoBean bean = new ProdottoBean();
		CategoriaBean buffer = new CategoriaBean();

		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, nome);

				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					bean.setNome(rs.getString("nome"));
					bean.setId(Integer.parseInt(rs.getString("id")));
					buffer.setNome(rs.getString("Cnome"));
					buffer.setDescrizione(rs.getString("Cdesc"));
					Optional<CategoriaBean> optional = Optional.of(buffer); //categoria può essere null
					bean.setCategoria(optional);
					bean.setPrezzo(rs.getDouble("prezzo"));
					bean.setIVA(rs.getDouble("IVA"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setTipo(rs.getString("tipo"));
					bean.setQuantitaResidua(rs.getDouble("quantitaDisponibili"));
					bean.setPeso(rs.getDouble("peso"));
					ArrayList<String> elencoPathImmagini = new ArrayList<>();
					
					ImmagineDAO imDAO = new ImmagineDAO();
					ImmagineBeans imBean = new ImmagineBeans();
					PossessoImmagineDAO possDAO = new PossessoImmagineDAO();
					CostituzioneDAO CostDAO = new CostituzioneDAO();
					GustoDAO GDAO = new GustoDAO();
					GustoBean GustoB = new GustoBean();
					
					
					for(String immagine : possDAO.retrieveImmagine(nome))
					{
						imBean = imDAO.doRetrieveByKey(immagine);
						elencoPathImmagini.add(imBean.getUrl());
					}
					
					bean.setPathImage(elencoPathImmagini);


					for(String gusti : CostDAO.retrieveGusti(rs.getString("id")))
					{
						GustoB = GDAO.doRetrieveByKey(gusti);
						bean.getGusti().add(GustoB);
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
		CategoriaBean buffer = new CategoriaBean();
		ArrayList<ProdottoBean> result = new ArrayList<>();

		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				//statement.setString(1, order);

				ResultSet rs = statement.executeQuery();
				while(rs.next()) {
					ProdottoBean bean = new ProdottoBean();
					bean.setNome(rs.getString("nome"));
					bean.setId(Integer.parseInt(rs.getString("id")));
					buffer.setNome(rs.getString("Cnome"));
					buffer.setDescrizione(rs.getString("Cdesc"));
					Optional<CategoriaBean> optional = Optional.of(buffer); //categoria può essere null
					bean.setCategoria(optional);
					bean.setPrezzo(rs.getDouble("prezzo"));
					bean.setIVA(rs.getDouble("IVA"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setTipo(rs.getString("tipo"));
					bean.setQuantitaResidua(rs.getDouble("quantitaDisponibili"));
					bean.setPeso(rs.getDouble("peso"));
					
					ArrayList<String> elencoPathImmagini = new ArrayList<>();
					
					ImmagineDAO imDAO = new ImmagineDAO();
					ImmagineBeans imBean = new ImmagineBeans();
					PossessoImmagineDAO possDAO = new PossessoImmagineDAO();
					CostituzioneDAO CostDAO = new CostituzioneDAO();
					GustoDAO GDAO = new GustoDAO();
					GustoBean GustoB = new GustoBean();
					
					for(String immagine : possDAO.retrieveImmagine(rs.getString("id")))
					{
						imBean = imDAO.doRetrieveByKey(immagine);
						elencoPathImmagini.add(imBean.getUrl());
					}

					bean.setPathImage(elencoPathImmagini);

					
					for(String gusti : CostDAO.retrieveGusti(rs.getString("id")))
					{
						GustoB = GDAO.doRetrieveByKey(gusti);
						bean.getGusti().add(GustoB);
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
			sql = "UPDATE " + TABLE_NAME + " SET "
					+ " tipo = ?, descrizione = ?, quantitaDisponibili = ?, prezzo = ?, IVA = ?, categoria = ?"
					+ " WHERE id = ? ";
			


			try(Connection con = ds.getConnection()){
				try(PreparedStatement preparedStatement2 = con.prepareStatement(sql)){
					
					
					
					preparedStatement2.setString(1, bean.getTipo());
					preparedStatement2.setString(2, bean.getDescrizione());
					preparedStatement2.setDouble(3, bean.getQuantitaResidua());
					preparedStatement2.setDouble(4, bean.getPrezzo());
					preparedStatement2.setDouble(5, bean.getIVA());
					preparedStatement2.setString(6, bean.getCategoria().getNome());
					preparedStatement2.setLong(7, bean.getId());
					preparedStatement2.executeUpdate();
					
					
						
					
				}
			}
		}

		else {
			sql = "UPDATE " + TABLE_NAME + " SET "
					+ " tipo = ?, descrizione = ?, quantitaDisponibili = ?, prezzo = ?, IVA = ?, peso = ?, categoria = ? "
					+ " WHERE id = ? ";

			try(Connection con = ds.getConnection()){
				try(PreparedStatement ps = con.prepareStatement(sql)){
					ps.setString(1, bean.getTipo());
					ps.setString(2, bean.getDescrizione());
					ps.setDouble(3, bean.getQuantitaResidua());
					ps.setDouble(4, bean.getPrezzo());
					ps.setDouble(5, bean.getIVA());
					ps.setDouble(6, bean.getPeso());
					ps.setString(7, bean.getCategoria().getNome());
					ps.setLong(8, bean.getId());
					ps.executeUpdate();	
					//ps.setString(8, bean.getGusti()); //gusti deve andare in "costituzione"
					//TO DO PESO

					
				}
			}
		}

	}
	
	
	public int doSaveI(ProdottoBean bean) throws SQLException {
		String sql = new String();
		
		String IDSQL = "SELECT id FROM kindkaribe.prodotto ORDER BY id DESC LIMIT 1";

		if(bean.getTipo().toLowerCase().equals("pasticceria")) {
			sql =  "INSERT INTO `kindkaribe`.`prodotto` (`nome`, `categoria`, `tipo`, `prezzo`, `descrizione`, `quantitaDisponibili`, `IVA`,`id`) "
					+ "VALUES (?,?,?,?,?,?,?,?)";	
			
			

			try(Connection con = ds.getConnection()){
				try(PreparedStatement ps = con.prepareStatement(IDSQL)){
					
					ResultSet id = ps.executeQuery();
					
					id.next();
					int ID = id.getInt("id") + 1;
					
					PreparedStatement ps2 = con.prepareStatement(sql);
					
					ps2.setString(1, bean.getNome());
					ps2.setString(2, bean.getCategoria().getNome());
					ps2.setString(3, bean.getTipo());
					ps2.setDouble(4, bean.getPrezzo());
					ps2.setString(5, bean.getDescrizione());
					ps2.setDouble(6, bean.getQuantitaResidua());
					ps2.setDouble(7, bean.getIVA());
					ps2.setInt(8, ID);
					
					ps2.executeUpdate();
					
					return ID;

				}

			}
		}
		else {
			sql = "INSERT INTO " + TABLE_NAME 
					+ " (nome, tipo, descrizione, quantitaDisponibili, prezzo, IVA,id,categoria,peso) "
					+ " VALUES (?,?,?,?,?,?,?,?,?)";

			try(Connection con = ds.getConnection()){
				try(PreparedStatement ps = con.prepareStatement(IDSQL)){
					
					
					ResultSet id = ps.executeQuery();
					
					id.next();
					int ID = id.getInt("id") + 1;
					
					PreparedStatement ps2 = con.prepareStatement(sql);
					
					ps2.setString(1, bean.getNome());
					ps2.setString(2, bean.getTipo());
					ps2.setString(3, bean.getDescrizione());
					ps2.setDouble(4, bean.getQuantitaResidua());
					ps2.setDouble(5, bean.getPrezzo());
					ps2.setDouble(6, bean.getIVA());
					ps2.setInt(7, ID);
					ps2.setString(8, bean.getCategoria().getNome());
					ps2.setDouble(9, bean.getPeso());	
					
					ps2.execute();
						return ID;
					
					
				
				}
			}
		}
	}
	
	public ProdottoBean doRetrieveByNome(String nome) throws Exception {
		String sql = "SELECT prodotto.*, categoria.nome as Cnome, categoria.descrizione as Cdesc "
				+ "FROM prodotto JOIN categoria "
				+"ON categoria.nome = prodotto.categoria "
				+ "where prodotto.id = ?";
		ProdottoBean bean = new ProdottoBean();
		CategoriaBean buffer = new CategoriaBean();

		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, nome);

				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					bean.setNome(rs.getString("nome"));
					bean.setId(Integer.parseInt(rs.getString("id")));
					buffer.setNome(rs.getString("Cnome"));
					buffer.setDescrizione(rs.getString("Cdesc"));
					Optional<CategoriaBean> optional = Optional.of(buffer); //categoria può essere null
					bean.setCategoria(optional);
					bean.setPrezzo(rs.getDouble("prezzo"));
					bean.setIVA(rs.getDouble("IVA"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setTipo(rs.getString("tipo"));
					bean.setQuantitaResidua(rs.getDouble("quantitaDisponibili"));
					bean.setPeso(rs.getDouble("peso"));
					ArrayList<String> elencoPathImmagini = new ArrayList<>();
					
					ImmagineDAO imDAO = new ImmagineDAO();
					ImmagineBeans imBean = new ImmagineBeans();
					PossessoImmagineDAO possDAO = new PossessoImmagineDAO();
					CostituzioneDAO CostDAO = new CostituzioneDAO();
					GustoDAO GDAO = new GustoDAO();
					GustoBean GustoB = new GustoBean();
					
					
					for(String immagine : possDAO.retrieveImmagine(nome))
					{
						imBean = imDAO.doRetrieveByKey(immagine);
						elencoPathImmagini.add(imBean.getUrl());
					}
					
					bean.setPathImage(elencoPathImmagini);


					for(String gusti : CostDAO.retrieveGusti(rs.getString("id")))
					{
						GustoB = GDAO.doRetrieveByKey(gusti);
						bean.getGusti().add(GustoB);
					}
					
					
				}
			}	
		}
		return bean;
	}
	
	
}
