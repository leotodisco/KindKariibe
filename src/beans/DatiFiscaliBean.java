package beans;

public class DatiFiscaliBean {
	
	@Override
	public String toString() {
		return "DatiFiscaliBean [idDatiFiscali=" + idDatiFiscali + ", metodoPagamento=" + idMetodoPagamento
				+ ", idIndirizzoFatturazione=" + idIndirizzoFatturazione + "]";
	}


	private Integer idDatiFiscali;
	private Integer idMetodoPagamento;
	private Integer idIndirizzoFatturazione;
	
	public DatiFiscaliBean() {
	}


	public Integer getIdDatiFiscali() {
		return idDatiFiscali;
	}


	public void setIdDatiFiscali(Integer idDatiFiscali) {
		this.idDatiFiscali = idDatiFiscali;
	}

	public Integer getIdMetodoPagamento() {
		return idMetodoPagamento;
	}


	public void setIdMetodoPagamento(Integer idMetodoPagamento) {
		this.idMetodoPagamento = idMetodoPagamento;
	}

	

	public Integer getIdIndirizzoFatturazione() {
		return idIndirizzoFatturazione;
	}


	public void setIdIndirizzoFatturazione(Integer idIndirizzoFatturazione) {
		this.idIndirizzoFatturazione = idIndirizzoFatturazione;
	}
	
	
	
}
