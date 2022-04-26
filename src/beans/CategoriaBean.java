package beans;

public class CategoriaBean {
	private String nome;
	private String descrizione;
	
	public CategoriaBean() {
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "CategoriaBean [nome=" + nome + ", descrizione=" + descrizione + "]";
	}
	
	
	
}
