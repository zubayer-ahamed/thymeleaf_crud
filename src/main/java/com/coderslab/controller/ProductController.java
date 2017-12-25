package com.coderslab.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coderslab.model.Product;
import com.coderslab.services.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping
	public String loadProductPage(ModelMap model) {
		return updateProduct(0, model);
	}
	
	@RequestMapping("/{pid}")
	public String updateProduct(@PathVariable("pid") int pid, ModelMap model) {
		model.addAttribute("product", pid == 0 ? new Product() : productService.getById(pid));
		model.addAttribute("products", productService.getProducts());
		return "pages/product";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveOrUpdateProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, ModelMap model) throws Exception{
		if(result.hasErrors()) {
			model.addAttribute("products", productService.getProducts());
			return "pages/product";
		}
		if(product.getPid() == 0 || productService.getById(product.getPid()) == null) {
			productService.addProduct(product);
		}else {
			productService.updateProduct(product);
		}
		model.addAttribute("sm", "Successfull");
		return "redirect:/product";
	}
	
	@RequestMapping(value="/deleteProduct/{pid}", method=RequestMethod.GET)
	public String deleteProduct(@PathVariable("pid") int pid, ModelMap model) throws Exception{
		Product p = productService.getById(pid);
		productService.deleteProduct(p);
		return "redirect:/product";
	}
	
	
	
}
