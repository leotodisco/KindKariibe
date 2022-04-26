package beans;

public class MetodoPagamentoBean {
	private int idMetodoPagamento;
	private String tipo;
	private String nomeIntestatario;
	private int numeroCarta;
	private int meseScadenza;
	private int annoScadenza;
	private String iban;
	private String causale;
	private String circuito;
	private java.util.Date dataPagamento;
	private String CVV;
	
	
	
	public int getIdMetodoPagamento() {
		return idMetodoPagamento;
	}

	public void setIdMetodoPagamento(int idMetodoPagamento) {
		this.idMetodoPagamento = idMetodoPagamento;
	}

	public String getCircuito() {
		return circuito;
	}

	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}

	public java.util.Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(java.util.Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getCVV() {
		return CVV;
	}

	public void setCVV(String cVV) {
		CVV = cVV;
	}

	public MetodoPagamentoBean() {
		
	}
	
	public int getidMetodoPagamento() {
		return idMetodoPagamento;
	}
	
	public void setidMetodoPagamento(int id) {
		this.idMetodoPagamento = id;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getNomeIntestatario() {
		return nomeIntestatario;
	}
	
	public void setNomeIntestatario(String nome) {
		this.nomeIntestatario = nome;
	}
	
	public int getNumeroCarta() {
		return numeroCarta;
	}
	
	public void setNumeroCarta(int numero) {
		this.numeroCarta = numero;
	}
	
	public int getMeseScadenza() {
		return meseScadenza;
	}
	
	public void setMeseScadenza(int mese) {
		this.meseScadenza = mese;
	}
	
	public int getAnnoScadenza() {
		return annoScadenza;
	}
	
	public int setAnnoScadenza(int anno) {
		return this.annoScadenza = anno;
	}
	
	public String getIban() {
		return iban;
	}
	
	public String setIban(String iban) {
		return this.iban = iban;
	}
	
	public String getCausale() {
		return causale;
	}
	
	public String setCausale(String causale) {
		return this.causale = causale;
	}
	
	@Override
	public String toString() {
		return "MetodoPagamento [id=" + idMetodoPagamento + ", tipo=" + tipo + ", nomeIntestatario=" + nomeIntestatario + ", numeroCarta="
				+ numeroCarta + ", meseScadenza=" + meseScadenza + ", annoScadenza=" + annoScadenza + ", iban=" + iban + ", causale=" + causale+  "]";
	}
	
}