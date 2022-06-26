package beans;

import java.io.Serializable;

public class CorriereBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -545434051788429727L;
	private Integer id;
	private String nTelefono;
	private String azienda;
	
	public CorriereBean() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getnTelefono() {
		return nTelefono;
	}
	public void setnTelefono(String nTelefono) {
		this.nTelefono = nTelefono;
	}
	public String getAzienda() {
		return azienda;
	}
	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}
	
	@Override
	public String toString() {
		return "CorriereBean [id=" + id + ", nTelefono=" + nTelefono + ", azienda=" + azienda + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((azienda == null) ? 0 : azienda.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nTelefono == null) ? 0 : nTelefono.hashCode());
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
		CorriereBean other = (CorriereBean) obj;
		if (azienda == null) {
			if (other.azienda != null)
				return false;
		} else if (!azienda.equals(other.azienda))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nTelefono == null) {
			if (other.nTelefono != null)
				return false;
		} else if (!nTelefono.equals(other.nTelefono))
			return false;
		return true;
	}
	
	
	
	
	
}
