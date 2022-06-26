package beans;

import java.io.Serializable;

public class IndirizzoBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4480183454695488625L;
	private int id;
	private String via;
	private String citta;
	private String CAP;
	private String nCivico;
	private String provincia;
	
	public IndirizzoBean()
	{
		
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getCAP() {
		return CAP;
	}
	public void setCAP(String cAP) {
		CAP = cAP;
	}
	public String getnCivico() {
		return nCivico;
	}
	public void setnCivico(String nCivico) {
		this.nCivico = nCivico;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}



	@Override
	public String toString() {
		return "IndirizzoBean [id=" + id + ", via=" + via + ", citta=" + citta + ", CAP=" + CAP + ", nCivico=" + nCivico
				+ ", provincia=" + provincia + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CAP == null) ? 0 : CAP.hashCode());
		result = prime * result + ((citta == null) ? 0 : citta.hashCode());
		result = prime * result + id;
		result = prime * result + ((nCivico == null) ? 0 : nCivico.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((via == null) ? 0 : via.hashCode());
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
		IndirizzoBean other = (IndirizzoBean) obj;
		if (CAP == null) {
			if (other.CAP != null)
				return false;
		} else if (!CAP.equals(other.CAP))
			return false;
		if (citta == null) {
			if (other.citta != null)
				return false;
		} else if (!citta.equals(other.citta))
			return false;
		if (id != other.id)
			return false;
		if (nCivico == null) {
			if (other.nCivico != null)
				return false;
		} else if (!nCivico.equals(other.nCivico))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (via == null) {
			if (other.via != null)
				return false;
		} else if (!via.equals(other.via))
			return false;
		return true;
	}
	
	
	

}
