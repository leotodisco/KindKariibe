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
	
	public MetodoPagamentoBean() {
		
	}
	
	public int getidMetodoPagamento() {
		return idMetodoPagamento;
	}
	
	public void setidMetodoPagamento(int id) {
		this.idMetodoPagamento = metodo;
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
	
	public void getAnnoScadenza() {
		return annoScadenza;
	}
	
	public int setAnnoScadenza(int anno) {
		this.annoScadenza = anno;
	}
	
	public void getIban() {
		return iban;
	}
	
	public String setIban(String iban) {
		this.iban = iban;
	}
	
	public void getCausale() {
		return causale;
	}
	
	public String setCausale(String causale) {
		this.causale = causale;
	}
	
	@Override
	public String toString() {
		return "MetodoPagamento [id=" + idMetodoPagamento + ", tipo=" + tipo + ", nomeIntestatario=" + nomeIntestatario + ", numeroCarta="
				+ numeroCarta + ", meseScadenza=" + meseScadenza + ", annoScadenza=" + annoScadenza + ", iban=" + iban + ", causale=" + causale+  "]";
	}
	
}