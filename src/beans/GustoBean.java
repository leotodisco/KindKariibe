package beans;

import java.io.Serializable;

public class GustoBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4888905046805213326L;
	private String nome,descrizione,colore;
	private Double quantitaInMagazzino;
	
	
	public GustoBean() {
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public String getColore() {
		return colore;
	}
	
	public Double getquantitaInMagazzino() {
		return this.quantitaInMagazzino;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public void setColore(String colore) {
		this.colore = colore;
	}
	
	public void setquantitaInMagazzino(double quantita) {
		this.quantitaInMagazzino = quantita;
	}
	

	@Override
	public String toString() {
		return "GustoBean [nome=" + nome + ", descrizione=" + descrizione + ", colore=" + colore + ", quantit� in magazzino ="
	+quantitaInMagazzino+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colore == null) ? 0 : colore.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((quantitaInMagazzino == null) ? 0 : quantitaInMagazzino.hashCode());
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
		GustoBean other = (GustoBean) obj;
		if (colore == null) {
			if (other.colore != null)
				return false;
		} else if (!colore.equals(other.colore))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (quantitaInMagazzino == null) {
			if (other.quantitaInMagazzino != null)
				return false;
		} else if (!quantitaInMagazzino.equals(other.quantitaInMagazzino))
			return false;
		return true;
	}
	
	
	
	
}