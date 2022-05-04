package beans;

public class DatiFiscaliBean {
	
	private Integer idDatiFiscali;
	private MetodoPagamentoBean metodoPagamento;
	private String via;
	private Integer nCivico;
	private String provincia;
	private Integer CAP;
	private String citta;
	
	
	public DatiFiscaliBean() {
	}


	public Integer getIdDatiFiscali() {
		return idDatiFiscali;
	}


	public void setIdDatiFiscali(Integer idDatiFiscali) {
		this.idDatiFiscali = idDatiFiscali;
	}


	public MetodoPagamentoBean getMetodoPagamento() {
		return metodoPagamento;
	}


	public void setMetodoPagamento(MetodoPagamentoBean metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}


	public String getVia() {
		return via;
	}


	public void setVia(String via) {
		this.via = via;
	}


	public Integer getnCivico() {
		return nCivico;
	}


	public void setnCivico(Integer nCivico) {
		this.nCivico = nCivico;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public Integer getCAP() {
		return CAP;
	}


	public void setCAP(Integer cAP) {
		CAP = cAP;
	}


	public String getCitta() {
		return citta;
	}


	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	
	
}
