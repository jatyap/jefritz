package com.jatyap.jefritz.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jatyap.jefritz.dao.OrderDAO;
import com.jatyap.jefritz.dao.ProductDAO;
import com.jatyap.jefritz.dao.ProductPricingDAO;
import com.jatyap.jefritz.model.Order;
import com.jatyap.jefritz.model.OrderType;
import com.jatyap.jefritz.model.Product;
import com.jatyap.jefritz.model.ProductPricing;
import com.jatyap.jefritz.model.PurchaseOrder;
import com.jatyap.jefritz.model.PurchaseOrderDetail;

@Controller
public class PurchaseOrderController {

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProductPricingDAO pricingDAO;

	@ModelAttribute("orderList")
	public List<Order> orders() {
		return this.orderDAO.getOrders(OrderType.PURCHASE);
	}

	// @ModelAttribute("products")
	// public List<Product> products() {
	// return this.productDAO.listProducts();
	// }

	@ModelAttribute("priceList")
	public List<ProductPricing> priceList() {
		return pricingDAO.listProductPricing(null);
	}

	@ModelAttribute("priceListJSON")
	public String priceListJSON() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer();
		String priceListJSON = null;
		try {
			List<ProductPricing> priceList = this.priceList();
			List<Map> convertedList = new ArrayList<Map>();
			for(ProductPricing pricing : priceList){
				convertedList.add(mapper.convertValue(pricing, Map.class));
			}
			priceListJSON = writer.writeValueAsString(convertedList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return priceListJSON;
	}

	@RequestMapping("/purchaseorder")
	public String list(Model model) {
		return "purchaseorder/list";
	}

	@RequestMapping(value = "/purchaseorder/create")
	public String newOrder(Model model, HttpSession session) {
		PurchaseOrder order = new PurchaseOrder();
		order.addOrderDetail(new PurchaseOrderDetail());
		model.addAttribute("order", order);
		return "purchaseorder/create";
	}

	@RequestMapping(value = "/purchaseorder/create", params = { "addDetail" })
	public ModelAndView addOrderDetail(PurchaseOrder order) {
		order.addOrderDetail(new PurchaseOrderDetail());
		ModelAndView modelView = new ModelAndView("purchaseorder/create");
		modelView.addObject("order", order);
		return modelView;
	}

	@RequestMapping(value = "/purchaseorder/create", params = { "save" })
	public String saveOrder(PurchaseOrder order) {
		System.out.println("Save Size: " + order.getOrderDetails().size());
		for (PurchaseOrderDetail detail : order.getOrderDetails()) {
			System.out.println(detail.getQuantity() + " : "
					+ detail.getProduct() + " : " + detail.getPricePerUnit());
		}
		// this.orderDAO.saveOrder(order);
		return "redirect:/purchaseorder";
	}

}
