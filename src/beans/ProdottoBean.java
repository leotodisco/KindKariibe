package beans;

import java.util.ArrayList;
import java.util.Optional;

public class ProdottoBean {
	
	public ProdottoBean() {
		
	}
	
	private String nome;
	private Integer id;
	private String tipo;
	private String descrizione;
	private Double quantitaResidua;
	private Double prezzo;
	private Double IVA;
	private Double peso;
	private CategoriaBean categoria; //può essere null
	private ArrayList<String> pathImage = new ArrayList<>(); //path immagini
	private ArrayList<GustoBean> gusti = new ArrayList<>(); //gusti
	
	
	
	
	@Override
	public String toString() {
		return "ProdottoBean [nome=" + nome + ", tipo=" + tipo + ", descrizione=" + descrizione + ", quantitaResidua="
				+ quantitaResidua + ", prezzo=" + prezzo + ", IVA=" + IVA + ", peso=" + peso + ", categoria="
				+ categoria + ", pathImage=" + pathImage + "]";
	}
	public void addImmagine(String s) {
		this.pathImage.add(s);
	}
	
	/*
	 * metodo che prende in input l'URL dell'immagine e la mette in prima posizione
	 * poichè la vetrina del catalogo prende sempre la prima immagine presente in lista
	 * */
	public void addImmaginePrimaPosizione(String s) {
		if(s.equals(pathImage.get(0))) //se la foto non è stata modificata
			return;
			
		this.pathImage.add(0,s);
	}
	
	//RELAZIONE ESTENSIONE 
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	//URL IMMAGINE - RELAZIONE "possessoImmagine"
	public ArrayList<String> getPathImage() {
		return pathImage;
	}
	public void setPathImage(ArrayList<String> pathImage) {
		this.pathImage = pathImage;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Double getQuantitaResidua() {
		return quantitaResidua;
	}
	public void setQuantitaResidua(Double quantitaResidua) {
		this.quantitaResidua = quantitaResidua;
	}
	public Double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	public Double getIVA() {
		return IVA;
	}
	public void setIVA(Double iVA) {
		IVA = iVA;
	}
	public CategoriaBean getCategoria() {
		return categoria;
	}
	public void setCategoria(Optional<CategoriaBean> categoria) {
		this.categoria = categoria.get();
	}
	
	public ArrayList<GustoBean> getGusti() {
		return gusti;
	}
	
	public void setGusti(Optional<ArrayList<GustoBean>> gusti) {
		this.gusti = gusti.orElse(null);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer iD2) {
		this.id = iD2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IVA == null) ? 0 : IVA.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((gusti == null) ? 0 : gusti.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((pathImage == null) ? 0 : pathImage.hashCode());
		result = prime * result + ((peso == null) ? 0 : peso.hashCode());
		result = prime * result + ((prezzo == null) ? 0 : prezzo.hashCode());
		result = prime * result + ((quantitaResidua == null) ? 0 : quantitaResidua.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		ProdottoBean other = (ProdottoBean) obj;
		if (IVA == null) {
			if (other.IVA != null)
				return false;
		} else if (!IVA.equals(other.IVA))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (gusti == null) {
			if (other.gusti != null)
				return false;
		} else if (!gusti.equals(other.gusti))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pathImage == null) {
			if (other.pathImage != null)
				return false;
		} else if (!pathImage.equals(other.pathImage))
			return false;
		if (peso == null) {
			if (other.peso != null)
				return false;
		} else if (!peso.equals(other.peso))
			return false;
		if (prezzo == null) {
			if (other.prezzo != null)
				return false;
		} else if (!prezzo.equals(other.prezzo))
			return false;
		if (quantitaResidua == null) {
			if (other.quantitaResidua != null)
				return false;
		} else if (!quantitaResidua.equals(other.quantitaResidua))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	
	
	
	
	
	
	
	
}
