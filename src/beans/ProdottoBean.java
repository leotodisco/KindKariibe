package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdottoBean {
	
	public ProdottoBean() {
		
	}
	
	private String nome;
	private String tipo;
	private String descrizione;
	private Double quantitaResidua;
	private Double prezzo;
	private Double IVA;
	private Double peso;
	private CategoriaBean categoria; //può essere null
	private ArrayList<String> pathImage = new ArrayList<>(); //path immagini
	private ArrayList<String> gusti = new ArrayList<>(); //id gusti
	
	
	
	
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
	
	public ArrayList<String> getGusti() {
		return gusti;
	}
	
	public void setGusti(Optional<ArrayList<String>> gusti) {
		this.gusti = gusti.orElse(null);
	}
	
	
	
	
	
	
	
}
