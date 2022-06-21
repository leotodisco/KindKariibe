package beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class OrdineBean {
	
	private Integer idOrdine;
	private DatiFiscaliBean datiFiscali;
	private CorriereBean corriere;
	private UserBean utente;
	private Double costoTotale;
	private String codiceSconto;
	private Date dataPartenza,dataEvasione,dataArrivo;
	private String urlPdf;
	private IndirizzoBean indirizzoSpedizione;
	private ConcurrentHashMap<ProdottoBean,ArrayList<Integer>> products; //ci serve una lista di interi per indicare anche l'iva
													  //in posizione 0 abbiamo quanita, in posizione 1 abbiamo IVA
													  //in posizione 2 si salva il prezzo.
	
	public OrdineBean() {
		super();
		this.products = new ConcurrentHashMap<>();
	}


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
	
	public String getDataEvasioneAsString() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String strDate= formatter.format(this.dataEvasione);  
		return strDate;
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
	
	//in overload si hanno due metodi uno che prende anche i dati in input e uno che non li prende.
	//perche?
	//quando sto inserendo per la prima volta qualcosa mi va bene prendere i dati che avevo già
	//se sto facendo un addProduct dopo mesi dall'ordine i prezzi potrebbero essere cambiati
	//dunque li devo prendere da "composizione"
	public synchronized void addProduct(ProdottoBean product, ArrayList<Integer> datiComposizione) {
		if(!products.keySet().isEmpty()) {
		for(ProdottoBean prodotto : products.keySet())
			{
			
				if(prodotto.getNome().equals(product.getNome()))
				{	
					ArrayList<Integer> datiProdotto = new ArrayList<>(products.get(prodotto));
					Integer quantitaAggiornata = datiComposizione.get(0) + 1;
					
					datiComposizione.set(0, quantitaAggiornata);
					this.products.replace(prodotto, datiComposizione);
				}
				
				else {
					this.products.put(product, datiComposizione);
					
				}
			}
			}
			
		else
			{
				this.products.put(product, datiComposizione);
			}
		
	}
	
	
	public synchronized void addProduct(ProdottoBean product) {
		if(!products.keySet().isEmpty()) {
		for(ProdottoBean prodotto : products.keySet())
			{
			
				if(prodotto.getNome().equals(product.getNome()))
				{	
					ArrayList<Integer> datiProdotto = new ArrayList<>(products.get(prodotto));
					Integer quantitaAggiornata = datiProdotto.get(0) + 1;
					datiProdotto.set(0, quantitaAggiornata);
					this.products.replace(prodotto, datiProdotto);
				}
				
				else {
					ArrayList<Integer> datiProdotto = new ArrayList<>();
					datiProdotto.add(0, 1); //se è la prima volta che aggiungo, la quantità vale 1
					datiProdotto.add(1, product.getIVA().intValue());
					datiProdotto.add(2, product.getPrezzo().intValue());
					this.products.put(product, datiProdotto);
					
				}
			}
			}
			
		else
			{
				ArrayList<Integer> datiProdotto = new ArrayList<>();
				datiProdotto.add(0, 1); //se è la prima volta che aggiungo, la quantità vale 1
				datiProdotto.add(1, product.getIVA().intValue());
				datiProdotto.add(2, product.getPrezzo().intValue());
				this.products.put(product, datiProdotto);
			}
		
	}
	

	public ConcurrentHashMap<ProdottoBean, ArrayList<Integer>> getProducts() {
		return products;
	}


	public void setProducts(ConcurrentHashMap<ProdottoBean, ArrayList<Integer>> products) {
		this.products = products;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceSconto == null) ? 0 : codiceSconto.hashCode());
		result = prime * result + ((corriere == null) ? 0 : corriere.hashCode());
		result = prime * result + ((costoTotale == null) ? 0 : costoTotale.hashCode());
		result = prime * result + ((dataArrivo == null) ? 0 : dataArrivo.hashCode());
		result = prime * result + ((dataEvasione == null) ? 0 : dataEvasione.hashCode());
		result = prime * result + ((dataPartenza == null) ? 0 : dataPartenza.hashCode());
		result = prime * result + ((datiFiscali == null) ? 0 : datiFiscali.hashCode());
		result = prime * result + ((idOrdine == null) ? 0 : idOrdine.hashCode());
		result = prime * result + ((indirizzoSpedizione == null) ? 0 : indirizzoSpedizione.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((urlPdf == null) ? 0 : urlPdf.hashCode());
		result = prime * result + ((utente == null) ? 0 : utente.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdineBean other = (OrdineBean) obj;
		if (codiceSconto == null) {
			if (other.codiceSconto != null)
				return false;
		} else if (!codiceSconto.equals(other.codiceSconto))
			return false;
		if (corriere == null) {
			if (other.corriere != null)
				return false;
		} else if (!corriere.equals(other.corriere))
			return false;
		if (costoTotale == null) {
			if (other.costoTotale != null)
				return false;
		} else if (!costoTotale.equals(other.costoTotale))
			return false;
		if (dataArrivo == null) {
			if (other.dataArrivo != null)
				return false;
		} else if (!dataArrivo.equals(other.dataArrivo))
			return false;
		if (dataEvasione == null) {
			if (other.dataEvasione != null)
				return false;
		} else if (!dataEvasione.equals(other.dataEvasione))
			return false;
		if (dataPartenza == null) {
			if (other.dataPartenza != null)
				return false;
		} else if (!dataPartenza.equals(other.dataPartenza))
			return false;
		if (datiFiscali == null) {
			if (other.datiFiscali != null)
				return false;
		} else if (!datiFiscali.equals(other.datiFiscali))
			return false;
		if (idOrdine == null) {
			if (other.idOrdine != null)
				return false;
		} else if (!idOrdine.equals(other.idOrdine))
			return false;
		if (indirizzoSpedizione == null) {
			if (other.indirizzoSpedizione != null)
				return false;
		} else if (!indirizzoSpedizione.equals(other.indirizzoSpedizione))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (urlPdf == null) {
			if (other.urlPdf != null)
				return false;
		} else if (!urlPdf.equals(other.urlPdf))
			return false;
		if (utente == null) {
			if (other.utente != null)
				return false;
		} else if (!utente.equals(other.utente))
			return false;
		return true;
	}
	
	
	
	
	
}
 