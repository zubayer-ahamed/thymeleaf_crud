package com.coderslab.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.coderslab.model.Product;

@Service
public class ProductService {

	private List<Product> products = new ArrayList<>();

	public void addProduct(Product product) throws Exception {
		if (products.stream().filter(r -> r.getPid() == product.getPid()).count() > 0) {
			throw new Exception("Product id '" + product.getPid() + "' already exist");
		}
		product.setPid(new Random().nextInt());
		products.add(product);
	}

	public void updateProduct(Product product) throws Exception {
		List<Product> list = products.stream().filter(r -> r.getPid() == product.getPid()).collect(Collectors.toList());
		if (list.isEmpty()) {
			throw new Exception("No Product found with product id '" + product.getPid() + "' for update");
		}
		products.remove(list.get(0));
		products.add(product);
	}

	public void deleteProduct(Product product) throws Exception {
		List<Product> list = products.stream().filter(r -> r.getPid() == product.getPid()).collect(Collectors.toList());
		if (list.isEmpty()) {
			throw new Exception("No Product found with product id '" + product.getPid() + "' for deletion");
		}
		products.remove(list.get(0));
	}

	public List<Product> find(String hint) {
		return hint.equals("?") ? products
				: products.stream().filter(d -> d.getPname().toLowerCase().contains(hint.toLowerCase()))
						.collect(Collectors.toList());
	}

	public Product getById(int id) {
		List<Product> list = products.stream().filter(r -> r.getPid() == id).collect(Collectors.toList());
		if (list.size() != 1) {
			return null;
		}
		return list.get(0);
	}

	public List<Product> getProducts() {
		return products;
	}
	

}
