package beans;

import java.io.Serializable;

public class CategoriaBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 946407622763758347L;
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
		return  nome ;
	}
	
	
	
}
