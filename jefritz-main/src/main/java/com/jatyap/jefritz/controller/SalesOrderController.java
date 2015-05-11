package com.jatyap.jefritz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jatyap.jefritz.dao.SalesOrderDAO;
import com.jatyap.jefritz.entity.SalesOrder;

@Controller
public class SalesOrderController {

	@Autowired
	private SalesOrderDAO orderDAO;
	
	@ModelAttribute("orderList")
	public List<SalesOrder> getOrderList(){
		return this.orderDAO.getOrders();
	}
	
	@RequestMapping("/salesorder")
	public String list(Model model) {
		return "salesorder/list";
	}
	
	@RequestMapping("/salesorder/new")
	public String newOrder(Model model){
		SalesOrder order = new SalesOrder();
		model.addAttribute("salesOrder", order);
		return "salesorder/new";
	}
	
	@RequestMapping("/salesorder/save")
	public String saveOrder(SalesOrder order, Model model){
		this.orderDAO.saveOrder(order);
		return "redirect:/salesorder";
	}
}
