package com.example.dddstart.order.ui;

import com.example.dddstart.order.query.application.OrderDetailService;
import com.example.dddstart.order.query.application.OrderDetail;
import com.example.dddstart.order.query.dao.OrderSummaryDao;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class MyOrderController {
	private OrderDetailService orderDetailService;
	private OrderSummaryDao orderSummaryDao;

	public MyOrderController(OrderDetailService orderDetailService,
	                         OrderSummaryDao orderSummaryDao) {
		this.orderDetailService = orderDetailService;
		this.orderSummaryDao = orderSummaryDao;
	}

	@RequestMapping("/my/orders")
	public String orders(ModelMap modelMap) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelMap.addAttribute("orders", orderSummaryDao.findByOrdererId(user.getUsername()));
		return "my/orders";
	}

	@RequestMapping("/my/orders/{orderNo}")
	public String orderDetail(@PathVariable("orderNo") String orderNo, ModelMap modelMap) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetail(orderNo);
		if (orderDetail.isPresent()) {
			if (orderDetail.get().getOrderer().getMemberId().getId().equals(user.getUsername())) {
				modelMap.addAttribute("order", orderDetail.get());
				return "my/orderDetail";
			} else {
				return "my/notYourOrder";
			}
		} else {
			return "my/noOrder";
		}
	}
}
