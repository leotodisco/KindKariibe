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
	private ArrayList<String> pathImage; //path immagini
	private ArrayList<String> gusti; //id gusti
	
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
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
