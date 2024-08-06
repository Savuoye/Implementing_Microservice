package com.example.service;

import java.util.List;
import com.example.model.Product;

public interface ProductService {

	public List<Product> getProducts();

	public Product getProduct(Long id);

	public void createProduct(Long productID, String productName, Integer price);

	public void updateProduct(Product product);

	public void deleteProduct(Long id);
}
