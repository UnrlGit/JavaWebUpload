package com.athompson.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Cart {
    ArrayList<Product> products;

	public Cart() {
		this.products = new ArrayList<Product>();
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

    public void addProduct(Product product) {
    	boolean productExists = false;
    	for (Product productFromList : products) {
			if (product.getName().equals(productFromList.getName()) ) {
				//System.out.println(productFromList.getAmount()+":::"+product.getAmount());
				int currentAmount = productFromList.getAmount();
				int amountToAdd = product.getAmount();
				productFromList.setAmount(currentAmount+amountToAdd);
				//System.out.println("UPDEJTD PROD: "+ productFromList.getAmount());
				System.out.println("------------");
				System.out.println(currentAmount);
				System.out.println(amountToAdd);
				System.out.println(productFromList.getAmount());
				System.out.println("-------------");
				productExists = true;
			}
    	}
    	if(productExists== false) {
    		products.add(product);
    	}
    }
    
    public void removeProduct(Product product) {
    	//for (Product productFromList : products) {
		//	if (product.getId().equals(productFromList.getId() )) {
				products.remove(product);
		//	}
		//}
    }
    
    public void setProductAmount(Product product, int newAmount) {
    	for (Product productFromList : products) {
			if (product.getId().equals(productFromList.getId())) {
				productFromList.setAmount(newAmount);
			}
		}
    }
    
    public void reduceAmountByOne(Product product) {
    	for (Product productFromList : products) {
			if (product.getId().equals(productFromList.getId()) ) {
				if (productFromList.getAmount() == 1) {
					products.remove(product);
					return;
				}
				productFromList.setAmount(productFromList.getAmount()-1);
			}
		}
    }
    
    public int totalProducts() {
    	int total = 0;
    	
    	for (Product product : products) {
			total = total + product.getAmount();
		}
    	
    	return total;
    }
    
    public double totalPrice() {
    	double total = 0;
    	
    	for (Product product : products) {
			total = total + (product.getAmount()*product.getPrice());
		}
    	
    	return total;
    }

	public void increaseProductByOne(String productIdAdd) {
		for (Product productFromList : products) {
			if (productIdAdd.equals(productFromList.getId()) ) {
				
				productFromList.setAmount(productFromList.getAmount()+1);
			}
		}
		
	}
}
