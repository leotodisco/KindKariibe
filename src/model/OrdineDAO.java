package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.CorriereBean;
import beans.DatiFiscaliBean;
import beans.OrdineBean;
import beans.ProdottoBean;
import beans.UserBean;

public class OrdineDAO implements ModelInterface<OrdineBean>{

	private static final String TABLE_NAME = "ordine";
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


	public Collection<OrdineBean> doRetriveByUtente(UserBean utente) throws Exception {
		String sql= "SELECT * FROM "+ TABLE_NAME+" WHERE utente = ? ORDER BY dataEvasione DESC" ;

		List<OrdineBean> ordini = new ArrayList<>();

		try(Connection con= ds.getConnection()){
			try(PreparedStatement ps= con.prepareStatement(sql)){
				ps.setString(1, utente.getCodiceFiscale());
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					OrdineBean ordine= new OrdineBean();
					ordine.setIdOrdine(rs.getInt("idOrdine"));
					ordine.setCodiceSconto(rs.getString("codiceSconto"));
					ordine.setCostoTotale(rs.getDouble("costoTotale"));
					ordine.setUrlPdf(rs.getString("urlPdf"));
					ordine.setDataEvasione(rs.getDate("dataEvasione"));
					ordine.setDataPartenza(rs.getDate("dataPartenza"));
					ordine.setDataArrivo(rs.getDate("dataArrivo"));

					//FAI LA PARTE CHE PRENDE I PRODOTTI DA COMPOSIZIONE


					ordine.setUtente(utente);

					CorriereBean r = new CorriereBean();
					CorriereDAO crrDao = new CorriereDAO();
					r = crrDao.doRetrieveByKey(rs.getString("corriere"));
					ordine.setCorriere(r);

					DatiFiscaliBean df = new DatiFiscaliBean();
					DatiFiscaliDAO datiFis = new DatiFiscaliDAO();
					df = datiFis.doRetrieveByKey(String.valueOf(rs.getInt("datiFiscali")));
					ordine.setDatiFiscali(df);

					IndirizzoDao ind= new IndirizzoDao();
					ordine.setIndirizzoSpedizione(ind.doRetrieveByKey(rs.getString("indirizzoSpedizione")));

					//DEVO PRENDERE TUTTI I PRODOTTI DI UN DATO ORDINE E STANNO IN COMPOSIZONE
					String sqlProdotti = "SELECT * FROM composizione WHERE ordine = ?";
					ProdottoDAO products = new ProdottoDAO();
					ConcurrentHashMap<ProdottoBean,ArrayList<Integer>> elencoProdotti = new ConcurrentHashMap<>(); 
					//NEL BEAN SI SALVA PRODOTTO-QUANTITA-PREZZO-IVA che stanno in composizione
					//[QUANTITA-PREZZO-IVA] è una lista di interi/double
					try(PreparedStatement statementDatiOrdine = con.prepareStatement(sqlProdotti)){
						statementDatiOrdine.setInt(1, ordine.getIdOrdine());
						ResultSet prodottiSET = statementDatiOrdine.executeQuery();
						ArrayList<Integer> datiComposizione = new ArrayList<>();
						
						while(prodottiSET.next()) {
							datiComposizione.add(0, Double.valueOf(prodottiSET.getString("quantita")).intValue());
							datiComposizione.add(1, Double.valueOf(prodottiSET.getString("prezzo")).intValue());
							datiComposizione.add(2, Double.valueOf(prodottiSET.getString("IVA")).intValue());
							//devo inserire nella mappa tutto cio dopo
							ProdottoBean prod = products.doRetrieveByKey(prodottiSET.getString("prodotto"));
							ordine.addProduct(prod, datiComposizione);
						}

						ordini.add(ordine);
					}
				}
			}
		}
		
		//ordinare in base alla data
		return ordini;
	}

	@Override
	public void doSave(OrdineBean bean) throws SQLException {
		String sql = " INSERT INTO " + TABLE_NAME + " (datiFiscali,corriere,utente, "
				+ " costoTotale, codiceSconto, dataEvasione, urlPdf, indirizzoSpedizione ) VALUES (?,?,?,?,?,?,?,?) ";



		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, bean.getDatiFiscali().getIdDatiFiscali());
				ps.setInt(2, bean.getCorriere().getId());
				ps.setString(3, bean.getUtente().getCodiceFiscale());
				ps.setDouble(4, bean.getCostoTotale());
				ps.setString(5, bean.getCodiceSconto());
				ps.setDate(6, (Date) bean.getDataEvasione());
				ps.setString(7, bean.getUrlPdf());
				ps.setInt(8, bean.getIndirizzoSpedizione().getId());

				ps.execute();
			}

			String sql2 = "INSERT INTO " + "composizione" + " (prodotto,ordine,IVA,prezzo,quantita) "
					+ "VALUES(?,?,?,?,?)";

			try(PreparedStatement ps = con.prepareStatement("select idOrdine from ordine order by idOrdine DESC"))
			{
				ResultSet set = ps.executeQuery();

				set.next();

				int idOrdine = set.getInt(1);

				for(ProdottoBean products : bean.getProducts().keySet())
				{
					try(PreparedStatement ps1 = con.prepareStatement(sql2))
					{
						ps1.setInt(1, products.getId());
						ps1.setInt(2, idOrdine);
						ps1.setDouble(3, products.getIVA());
						ps1.setDouble(4, products.getPrezzo());
						ps1.setInt(5, bean.getProducts().get(products).get(0)); //in posizione 0 della lista c'è sempre quantita

						ps1.executeUpdate();
					}
				}

			}


		}

	}

	@Override
	public boolean doDelete(String arg) throws SQLException {
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE idOrdine = ? ";

		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, Integer.valueOf(arg));
				return ps.execute();
			}
		}

	}

	@Override
	public OrdineBean doRetrieveByKey(String arg) throws Exception {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE idOrdine = ? ";
		OrdineBean ordine = new OrdineBean();

		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, Integer.valueOf(arg));
				ResultSet rs = ps.executeQuery();

				if(rs.next()) {
					ordine.setIdOrdine(rs.getInt("idOrdine"));
					ordine.setCodiceSconto(rs.getString("codiceSconto"));
					ordine.setCostoTotale(rs.getDouble("costoTotale"));
					ordine.setUrlPdf(rs.getString("urlPdf"));
					ordine.setDataEvasione(rs.getDate("dataEvasione"));
					ordine.setDataPartenza(rs.getDate("dataPartenza"));
					ordine.setDataArrivo(rs.getDate("dataArrivo"));

				}

				UserBean usr = new UserBean();
				UserDAO usrDAO = new UserDAO();
				usr = usrDAO.doRetrieveByKey(rs.getString("utente"));
				ordine.setUtente(usr);

				CorriereBean r = new CorriereBean();
				CorriereDAO crrDao = new CorriereDAO();
				r = crrDao.doRetrieveByKey(rs.getString("corriere"));
				ordine.setCorriere(r);

				DatiFiscaliBean df = new DatiFiscaliBean();
				DatiFiscaliDAO datiFis = new DatiFiscaliDAO();
				df = datiFis.doRetrieveByKey(String.valueOf(rs.getInt("datiFiscali")));
				ordine.setDatiFiscali(df);

				IndirizzoDao ind= new IndirizzoDao();
				ordine.setIndirizzoSpedizione(ind.doRetrieveByKey(rs.getString("indirizzoSpedizione")));
				
				//DEVO PRENDERE TUTTI I PRODOTTI DI UN DATO ORDINE E STANNO IN COMPOSIZONE
				String sqlProdotti = "SELECT * FROM composizione WHERE ordine = ?";
				ProdottoDAO products = new ProdottoDAO();
				ConcurrentHashMap<ProdottoBean,ArrayList<Integer>> elencoProdotti = new ConcurrentHashMap<>(); 
				//NEL BEAN SI SALVA PRODOTTO-QUANTITA-PREZZO-IVA che stanno in composizione
				//[QUANTITA-PREZZO-IVA] è una lista di interi/double
				try(PreparedStatement statementDatiOrdine = con.prepareStatement(sqlProdotti)){
					statementDatiOrdine.setInt(1, ordine.getIdOrdine());
					ResultSet prodottiSET = statementDatiOrdine.executeQuery();
					ArrayList<Integer> datiComposizione = new ArrayList<>();
					
					while(prodottiSET.next()) {
						datiComposizione.add(0, Double.valueOf(prodottiSET.getString("quantita")).intValue());
						datiComposizione.add(1, Double.valueOf(prodottiSET.getString("prezzo")).intValue());
						datiComposizione.add(2, Double.valueOf(prodottiSET.getString("IVA")).intValue());
						//devo inserire nella mappa tutto cio dopo
						ProdottoBean prod = products.doRetrieveByKey(prodottiSET.getString("prodotto"));
						ordine.addProduct(prod, datiComposizione);
					}
				}
			
			}
		}
		return ordine;
	}

	@Override
	public Collection<OrdineBean> doRetrieveAll(String order) throws Exception {
		List<OrdineBean> ordini = new ArrayList<>();

		String sql = "SELECT * FROM " + TABLE_NAME + "ORDER BY ?";
		order = order.isEmpty() ? "nome" : order;

		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, Integer.valueOf(order));
				ResultSet rs = ps.executeQuery();

				while(rs.next()) {
					OrdineBean ordine = new OrdineBean();
					ordine.setIdOrdine(rs.getInt("idOrdine"));
					ordine.setCodiceSconto(rs.getString("codiceSconto"));
					ordine.setCostoTotale(rs.getDouble("costoTotale"));
					ordine.setUrlPdf(rs.getString("urlPdf"));
					ordine.setDataEvasione(rs.getDate("dataEvasione"));
					ordine.setDataPartenza(rs.getDate("dataPartenza"));
					ordine.setDataArrivo(rs.getDate("dataArrivo"));

					UserBean usr = new UserBean();
					UserDAO usrDAO = new UserDAO();
					usr = usrDAO.doRetrieveByKey(rs.getString("utente"));
					ordine.setUtente(usr);

					CorriereBean r = new CorriereBean();
					CorriereDAO crrDao = new CorriereDAO();
					r = crrDao.doRetrieveByKey(rs.getString("corriere"));
					ordine.setCorriere(r);

					DatiFiscaliBean df = new DatiFiscaliBean();
					DatiFiscaliDAO datiFis = new DatiFiscaliDAO();
					df = datiFis.doRetrieveByKey(String.valueOf(rs.getInt("datiFiscali")));
					ordine.setDatiFiscali(df);

					IndirizzoDao ind= new IndirizzoDao();
					ordine.setIndirizzoSpedizione(ind.doRetrieveByKey(rs.getString("indirizzoSpedizione")));

					ordini.add(ordine);

				}

			}
		}

		return ordini;
	}

	@Override
	public void doUpdate(OrdineBean bean) throws SQLException {
		String sql = "UPDATE " +TABLE_NAME+ " WHERE idOrdine = ?";

		try(Connection con = ds.getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, bean.getIdOrdine());
				ps.setInt(2, bean.getDatiFiscali().getIdDatiFiscali());
				ps.setInt(3, bean.getCorriere().getId());
				ps.setString(4, bean.getUtente().getCodiceFiscale());
				ps.setDouble(5, bean.getCostoTotale());
				ps.setString(6, bean.getCodiceSconto());
				ps.setDate(7, (Date) bean.getDataEvasione());
				ps.setDate(8, (Date) bean.getDataPartenza());
				ps.setDate(9, (Date) bean.getDataArrivo());
				ps.setString(10, bean.getUrlPdf());

				ps.executeUpdate();
			}
		}

	}

}