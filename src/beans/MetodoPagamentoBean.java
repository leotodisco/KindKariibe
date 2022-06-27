package beans;

import java.io.Serializable;

public class MetodoPagamentoBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1834298588744476937L;
	private Integer idMetodoPagamento;
	private String tipo;
	private String nomeIntestatario;
	private String numeroCarta;
	private int meseScadenza;
	private int annoScadenza;
	private String iban;
	private String causale;
	private String circuito;
	private Integer CVV;


	public String getCircuito() {
		return circuito;
	}

	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}

	public Integer getCVV() {
		return CVV;
	}

	public void setCVV(Integer cVV) {
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

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numero) {
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