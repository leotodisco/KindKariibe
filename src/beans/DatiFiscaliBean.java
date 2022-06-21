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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDatiFiscali == null) ? 0 : idDatiFiscali.hashCode());
		result = prime * result + ((idIndirizzoFatturazione == null) ? 0 : idIndirizzoFatturazione.hashCode());
		result = prime * result + ((idMetodoPagamento == null) ? 0 : idMetodoPagamento.hashCode());
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
		DatiFiscaliBean other = (DatiFiscaliBean) obj;
		if (idDatiFiscali == null) {
			if (other.idDatiFiscali != null)
				return false;
		} else if (!idDatiFiscali.equals(other.idDatiFiscali))
			return false;
		if (idIndirizzoFatturazione == null) {
			if (other.idIndirizzoFatturazione != null)
				return false;
		} else if (!idIndirizzoFatturazione.equals(other.idIndirizzoFatturazione))
			return false;
		if (idMetodoPagamento == null) {
			if (other.idMetodoPagamento != null)
				return false;
		} else if (!idMetodoPagamento.equals(other.idMetodoPagamento))
			return false;
		return true;
	}
	
	
	
}
