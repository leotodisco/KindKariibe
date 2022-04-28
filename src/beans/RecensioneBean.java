package beans;

public class RecensioneBean {
	
	private Integer idRecensione,voto;
	private UserBean utente;
	private ProdottoBean prodotto;
	private String testo;
	
	public RecensioneBean() {
	}

	public Integer getIdRecensione() {
		return idRecensione;
	}

	public void setIdRecensione(Integer idRecensione) {
		this.idRecensione = idRecensione;
	}

	public Integer getVoto() {
		return voto;
	}

	public void setVoto(Integer voto) {
		this.voto = voto;
	}

	public UserBean getUtente() {
		return utente;
	}

	public void setUtente(UserBean utente) {
		this.utente = utente;
	}

	public ProdottoBean getProdotto() {
		return prodotto;
	}

	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}
	
	
	
	
	

}
