package model;

import java.util.ArrayList;
import java.util.List;

import beans.ProdottoBean;

public class Carrello {

	private List<ProdottoBean> products;
	
	public Carrello() {
		products = new ArrayList<ProdottoBean>();
	}
	
	public void addProduct(ProdottoBean product) {
		products.add(product);
	}
	
	public void deleteProduct(ProdottoBean product) {
		for(ProdottoBean prod : products) {
			if(prod.getNome() == product.getNome()) {
				products.remove(prod);
				break;
			}
		}
 	}
	
	public List<ProdottoBean> getProducts() {
		return  products;
	}
}
