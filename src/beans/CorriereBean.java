package beans;

public class CorriereBean {
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
	
	
	
	
	
}
