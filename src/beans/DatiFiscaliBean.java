package beans;

public class DatiFiscaliBean {
	
	private Integer idDatiFiscali;
	private MetodoPagamentoBean metodoPagamento;
	private Integer idIndirizzoFatturazione;
	
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

	public Integer getIdIndirizzoFatturazione() {
		return idIndirizzoFatturazione;
	}


	public void setIdIndirizzoFatturazione(Integer idIndirizzoFatturazione) {
		this.idIndirizzoFatturazione = idIndirizzoFatturazione;
	}
	
	
	
}
