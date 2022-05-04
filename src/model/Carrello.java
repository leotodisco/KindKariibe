package model;

import java.util.HashMap;
import beans.ProdottoBean;

public class Carrello {

	private HashMap<ProdottoBean,Integer> products;
	
	public Carrello() {
		products = new HashMap<>();
	}
	
	public void addProduct(ProdottoBean product) {
		
		boolean flag = false;
		
			for(ProdottoBean prodotto : products.keySet())
			{
				if(prodotto.getNome().equals(product.getNome()))
				{
					flag = true;
					products.replace(prodotto, products.get(prodotto) + 1);
				}
			
			}
			
			if(!flag)
			{
				products.put(product, 1);
			}
			
		
	}
	
	public void deleteProduct(ProdottoBean product) {
		
		for(ProdottoBean prodotto : products.keySet())
		{
			if(prodotto.getNome().equals(product.getNome()))
			{
				if(products.get(prodotto) == 1)
				{
					products.remove(prodotto);
				}
				else
				{
					products.replace(prodotto, products.get(prodotto) - 1);
				}
				
				
			}
		}
		
	}
		
	public HashMap<ProdottoBean, Integer> getProducts() {
		return  products;
	}

	@Override
	public String toString() {
		return products.keySet().toString() ;
	}
	
	
	
	
	
}
