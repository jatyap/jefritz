package com.jatyap.jefritz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jatyap.jefritz.dao.OrderDAO;
import com.jatyap.jefritz.dao.ProductDAO;
import com.jatyap.jefritz.dao.ProductPricingDAO;
import com.jatyap.jefritz.entity.Order;
import com.jatyap.jefritz.entity.OrderType;
import com.jatyap.jefritz.entity.ProductPricing;
import com.jatyap.jefritz.entity.PurchaseOrder;
import com.jatyap.jefritz.entity.PurchaseOrderDetail;

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


	@ModelAttribute("priceList")
	public List<ProductPricing> priceList() {
		return pricingDAO.listProductPricing(null);
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
