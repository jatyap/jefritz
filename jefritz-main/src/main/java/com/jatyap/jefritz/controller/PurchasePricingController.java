package com.jatyap.jefritz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jatyap.jefritz.dao.ProductDAO;
import com.jatyap.jefritz.dao.ProductPricingDAO;
import com.jatyap.jefritz.entity.Product;
import com.jatyap.jefritz.entity.ProductPricing;
import com.jatyap.jefritz.entity.PurchaseProductPricing;

@Controller
public class PurchasePricingController {

	@Autowired
	private ProductPricingDAO pricingDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/products/purchasepricing")
	public String purchasePricing(Model model){
		List<ProductPricing> priceList = this.pricingDAO.listProductPricing(null);
		model.addAttribute("pricelist", priceList);
		return "product/purchasepricing";
	}
	
	@RequestMapping("/products/purchasepricing/new")
	public String newPurchasePricing(Model model){
		PurchaseProductPricing purchasePricing = new PurchaseProductPricing();
		model.addAttribute("productPricing", purchasePricing);
		return "/product/newpurchasepricing";
	}
	
	@ModelAttribute("products")
	public List<Product> getProductList(){
		return this.productDAO.listProducts();
	}
	
	@RequestMapping("/products/purchasepricing/save")
	public ModelAndView savePurchasePricing(PurchaseProductPricing pricing, Model model){
		this.pricingDAO.savePricing(pricing);
		return new ModelAndView("redirect:/products/purchasepricing");
	}
}
