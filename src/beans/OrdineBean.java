package beans;

import java.util.Date;
import java.util.HashMap;

public class OrdineBean {
	
	private Integer idOrdine;
	private DatiFiscaliBean datiFiscali;
	private CorriereBean corriere;
	private UserBean utente;
	private Double costoTotale;
	private String codiceSconto;
	private Date dataPartenza,dataEvasione,dataArrivo;
	private String urlPdf;
	private HashMap<ProdottoBean,Integer> products;
	private IndirizzoBean indirizzoSpedizione;
	
	
	public IndirizzoBean getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}


	@Override
	public String toString() {
		return "OrdineBean [idOrdine=" + idOrdine + ", datiFiscali=" + datiFiscali + ", corriere=" + corriere
				+ ", utente=" + utente + ", costoTotale=" + costoTotale + ", codiceSconto=" + codiceSconto
				+ ", dataPartenza=" + dataPartenza + ", dataEvasione=" + dataEvasione + ", dataArrivo=" + dataArrivo
				+ ", urlPdf=" + urlPdf + ", products=" + products + ", indirizzoSpedizione=" + indirizzoSpedizione
				+ "]";
	}


	public void setIndirizzoSpedizione(IndirizzoBean indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
	}


	public OrdineBean() {
		
	}


	public Integer getIdOrdine() {
		return idOrdine;
	}


	public void setIdOrdine(Integer idOrdine) {
		this.idOrdine = idOrdine;
	}


	public DatiFiscaliBean getDatiFiscali() {
		return datiFiscali;
	}


	public void setDatiFiscali(DatiFiscaliBean datiFiscali) {
		this.datiFiscali = datiFiscali;
	}


	public CorriereBean getCorriere() {
		return corriere;
	}


	public void setCorriere(CorriereBean corriere) {
		this.corriere = corriere;
	}


	public UserBean getUtente() {
		return utente;
	}


	public void setUtente(UserBean utente) {
		this.utente = utente;
	}


	public Double getCostoTotale() {
		return costoTotale;
	}


	public void setCostoTotale(Double costoTotale) {
		this.costoTotale = costoTotale;
	}


	public String getCodiceSconto() {
		return codiceSconto;
	}


	public void setCodiceSconto(String codiceSconto) {
		this.codiceSconto = codiceSconto;
	}


	public Date getDataPartenza() {
		return dataPartenza;
	}


	public void setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
	}


	public Date getDataEvasione() {
		return dataEvasione;
	}


	public void setDataEvasione(Date dataEvasione) {
		this.dataEvasione = dataEvasione;
	}


	public Date getDataArrivo() {
		return dataArrivo;
	}


	public void setDataArrivo(Date dataArrivo) {
		this.dataArrivo = dataArrivo;
	}


	public String getUrlPdf() {
		return urlPdf;
	}


	public void setUrlPdf(String urlPdf) {
		this.urlPdf = urlPdf;
	}
	
	public void addProduct(ProdottoBean product) {
		
		boolean flag = false;
		
			for(ProdottoBean prodotto : products.keySet())
			{
				if(prodotto.getNome().equals(product.getNome()))
				{
					flag = true;
					products.replace(prodotto, products.get(prodotto) + 1);
				}
			
			}
			
			if(!flag)
			{
				products.put(product, 1);
			}
			
		
	}
	
	public void deleteProduct(ProdottoBean product) {
		
		for(ProdottoBean prodotto : products.keySet())
		{
			if(prodotto.getNome().equals(product.getNome()))
			{
				if(products.get(prodotto) == 1)
				{
					products.remove(prodotto);
				}
				else
				{
					products.replace(prodotto, products.get(prodotto) - 1);
				}
				
				
			}
		}
		
	}


	public HashMap<ProdottoBean, Integer> getProducts() {
		return products;
	}


	public void setProducts(HashMap<ProdottoBean, Integer> products) {
		this.products = products;
	}
	
	
	
}
 