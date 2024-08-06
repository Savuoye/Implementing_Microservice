package com.example.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.CustomError;
import com.example.model.Product;
import com.example.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	public ProductService productService;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleError(HttpServletRequest req, Exception ex) {

		System.out.println(ex.getStackTrace());
		CustomError error = new CustomError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
	}

	@GetMapping
	public List<Product> getProducts() {

		return this.productService.getProducts();
	}

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable("id") Long id) {

		return this.productService.getProduct(id);
	}

	@PostMapping
	public Map<String, String> createProduct(@RequestParam(value = "id") Long id,
			@RequestParam(value = "name") String name, @RequestParam(value = "price") Integer price) {

		this.productService.createProduct(id, name, price);
		Map<String, String> map = new HashMap<>();
		map.put("status", "Product added");
		return map;
	}

	@PutMapping
	public Product updateProduct(@RequestBody Product product) {

		this.productService.updateProduct(product);
		return product;
	}

	@DeleteMapping("/{id}")
	public Map<String, String> deleteProduct(@PathVariable("id") Long id) {
		this.productService.deleteProduct(id);
		Map<String, String> map = new HashMap<>();
		map.put("status", "Product deleted");
		return map;
	}

	@PatchMapping("/{id}")
	public @ResponseBody void saveManager(@PathVariable("id") Long id, @RequestBody Map<Object, Object> fields) {

		Product product = this.productService.getProduct(id);

		fields.forEach((k, v) -> {
			Field field = ReflectionUtils.findField(Product.class, (String) k);
			field.setAccessible(true);
			ReflectionUtils.setField(field, product, v);
		});

		this.productService.updateProduct(product);
	}
}
