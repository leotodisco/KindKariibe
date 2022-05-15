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
		String sql = new String();

		if(bean.getTipo().toLowerCase().equals("pasticceria")) {
			sql =  "INSERT INTO `kindkaribe`.`prodotto` (`nome`, `categoria`, `tipo`, `prezzo`, `descrizione`, `quantitaDisponibili`, `IVA`)"
					+ "VALUES (?,?,?,?,?,?,?)";	

			try(Connection con = ds.getConnection()){
				try(PreparedStatement ps = con.prepareStatement(sql)){
					ps.setString(1, bean.getNome());
					ps.setString(2, bean.getCategoria().getNome());
					ps.setString(3, bean.getTipo());
					ps.setDouble(4, bean.getPrezzo());
					ps.setString(5, bean.getDescrizione());
					ps.setDouble(6, bean.getQuantitaResidua());
					ps.setDouble(7, bean.getIVA());
					
					ps.executeUpdate();

				}

			}
		}
		else {
			sql = "INSERT INTO " + TABLE_NAME 
					+ " (nome, tipo, descrizione, quantitaDisponibili, prezzo, IVA) "
					+ " VALUES (?,?,?,?,?,?)";

			try(Connection con = ds.getConnection()){
				try(PreparedStatement ps = con.prepareStatement(sql)){
					ps.setString(1, bean.getNome());
					ps.setString(2, bean.getTipo());
					ps.setString(3, bean.getDescrizione());
					ps.setDouble(4, bean.getQuantitaResidua());
					ps.setDouble(5, bean.getPrezzo());
					ps.setDouble(6, bean.getIVA());

					ps.execute();

					String insertPeso ="INSERT INTO `kindkaribe`.`estensione` (`prodotto`, `peso`) VALUES (?,?)";
					try(PreparedStatement ps2 = con.prepareStatement(insertPeso)){
						ps2.setString(1, bean.getNome());
						ps2.setDouble(2, bean.getPeso());

						ps2.execute();
					}
					
				
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
		String sql = "SELECT prodotto.*, categoria.nome as Cnome, categoria.descrizione as Cdesc "
				+ "FROM prodotto JOIN categoria "
				+"ON categoria.nome = prodotto.categoria "
				+ "where prodotto.nome = ?";
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
					
					ImmagineDAO imDAO = new ImmagineDAO();
					ImmagineBeans imBean = new ImmagineBeans();
					PossessoImmagineDAO possDAO = new PossessoImmagineDAO();
					
					
					for(String immagine : possDAO.retrieveImmagine(nome))
					{
						imBean = imDAO.doRetrieveByKey(immagine);
						elencoPathImmagini.add(imBean.getUrl());
					}
					
					bean.setPathImage(elencoPathImmagini);

					//ottieni peso
					String sqlPeso = "SELECT peso FROM estensione WHERE prodotto = ?;";
					try(PreparedStatement statement2 = conn.prepareStatement(sqlPeso)){
						statement2.setString(1, nome);
						ResultSet weight = statement2.executeQuery();
						while(weight.next())
						{
							bean.setPeso(weight.getDouble("peso"));						
						}
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
		CategoriaBean buffer = new CategoriaBean();
		ArrayList<ProdottoBean> result = new ArrayList<>();

		try(Connection conn = ds.getConnection()){
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				//statement.setString(1, order);

				ResultSet rs = statement.executeQuery();
				while(rs.next()) {
					ProdottoBean bean = new ProdottoBean();
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
					
					ImmagineDAO imDAO = new ImmagineDAO();
					ImmagineBeans imBean = new ImmagineBeans();
					PossessoImmagineDAO possDAO = new PossessoImmagineDAO();
					
					for(String immagine : possDAO.retrieveImmagine(rs.getString("nome")))
					{
						imBean = imDAO.doRetrieveByKey(immagine);
						elencoPathImmagini.add(imBean.getUrl());
					}

					bean.setPathImage(elencoPathImmagini);
					
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
			sql = "UPDATE " + TABLE_NAME + " SET "
					+ " tipo = ?, descrizione = ?, quantitaDisponibili = ?, prezzo = ?, IVA = ?, categoria = ? "
					+ " WHERE nome = ? ";

			try(Connection con = ds.getConnection()){
				try(PreparedStatement ps = con.prepareStatement(sql)){
					
					ps.setString(1, bean.getTipo());
					ps.setString(2, bean.getDescrizione());
					ps.setDouble(3, bean.getQuantitaResidua());
					ps.setDouble(4, bean.getPrezzo());
					ps.setDouble(5, bean.getIVA());
					ps.setString(6, bean.getCategoria().getNome());
					ps.setString(7, bean.getNome());
					ps.executeUpdate();
					
					
						
					
				}
			}
		}

		else {
			sql = "UPDATE " + TABLE_NAME + " SET "
					+ " tipo = ?, descrizione = ?, quantitaDisponibili = ?, prezzo = ?, IVA = ? "
					+ " WHERE nome = ? ";

			try(Connection con = ds.getConnection()){
				try(PreparedStatement ps = con.prepareStatement(sql)){
					ps.setString(1, bean.getTipo());
					ps.setString(2, bean.getDescrizione());
					ps.setDouble(3, bean.getQuantitaResidua());
					ps.setDouble(4, bean.getPrezzo());
					ps.setDouble(5, bean.getIVA());
					ps.setString(6, bean.getNome());
					ps.executeUpdate();	
					//ps.setString(8, bean.getGusti()); //gusti deve andare in "costituzione"
					//TO DO PESO

					
				}
			}
		}

	}
}
