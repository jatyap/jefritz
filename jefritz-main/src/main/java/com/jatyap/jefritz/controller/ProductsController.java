package com.jatyap.jefritz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jatyap.jefritz.dao.ProductDAO;
import com.jatyap.jefritz.model.Product;

@Controller
public class ProductsController {

	@Autowired
	private ProductDAO productDAO;

	@RequestMapping("/products")
	public String productsMain(Model model) {
		List<Product> productList = this.productDAO.listProducts();
		model.addAttribute("product", new Product());
		model.addAttribute("products", productList);
		return "product/products";
	}

	@RequestMapping("/saveProduct")
	public ModelAndView saveProduct(Product product, Model model) {
		this.productDAO.saveProduct(product);
		return new ModelAndView("redirect:/products");
	}
	
	@RequestMapping("/deleteProduct")
	public ModelAndView deleteProduct(@RequestParam(value="productId", required=true) String productId){
		this.productDAO.deleteProduct(productId);
		return new ModelAndView("redirect:/products");
	}

}
