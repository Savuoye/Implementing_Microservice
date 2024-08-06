package com.example.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	List<Product> products = new ArrayList<Product>();

	public ProductServiceImpl() {
		products.add(new Product(Long.valueOf(1), "product-1", 1000));
		products.add(new Product(Long.valueOf(2), "product-2", 2000));
		products.add(new Product(Long.valueOf(3), "product-3", 3000));
		products.add(new Product(Long.valueOf(4), "product-4", 4000));
		products.add(new Product(Long.valueOf(5), "product-5", 5000));
		products.add(new Product(Long.valueOf(6), "product-6", 6000));
		products.add(new Product(Long.valueOf(7), "product-7", 7000));
		products.add(new Product(Long.valueOf(8), "product-8", 8000));
		products.add(new Product(Long.valueOf(9), "product-9", 9000));
		products.add(new Product(Long.valueOf(10), "product-10", 10000));
		products.add(new Product(Long.valueOf(11), "product-11", 11000));
		products.add(new Product(Long.valueOf(12), "product-12", 12000));
		products.add(new Product(Long.valueOf(13), "product-13", 13000));
		products.add(new Product(Long.valueOf(14), "product-14", 14000));
		products.add(new Product(Long.valueOf(15), "product-15", 15000));
		products.add(new Product(Long.valueOf(16), "product-16", 16000));
		products.add(new Product(Long.valueOf(17), "product-17", 17000));
		products.add(new Product(Long.valueOf(18), "product-18", 18000));
		products.add(new Product(Long.valueOf(19), "product-19", 19000));
		products.add(new Product(Long.valueOf(20), "product-20", 20000));
	}

	@Override
	public List<Product> getProducts() {
		return this.products;
	}

	@Override
	public Product getProduct(Long id) {
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
			Product product = iterator.next();
			if (product.getProductID().equals(id)) {
				return product;
			}
		}
		return null;
	}

	@Override
	public void createProduct(Long productID, String productName, Integer price) {
		Product product = new Product(productID, productName, price);
		this.products.add(product);
	}

	@Override
	public void updateProduct(Product product) {
		getProduct(product.getProductID()).setProductName(product.getProductName());
		getProduct(product.getProductID()).setProductPrice(product.getProductPrice());
	}

	@Override
	public void deleteProduct(Long id) {
		this.products.remove(getProduct(id));
	}

}
