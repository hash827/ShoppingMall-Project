package com.example.onemind.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.example.onemind.dao.mybatis.mapper.LineCompanyItemMapper;
import com.example.onemind.dao.mybatis.mapper.LineItemMapper;
import com.example.onemind.domain.Account;
import com.example.onemind.domain.CompanyCart;
import com.example.onemind.domain.CompanyOrder;
import com.example.onemind.domain.CompanyShoes;
import com.example.onemind.domain.LineCompanyItem;
import com.example.onemind.domain.LineItem;
import com.example.onemind.domain.Order;
import com.example.onemind.service.CompanyOrderValidator;
import com.example.onemind.service.PetStoreFacade;

@Controller
@SessionAttributes("userSession")
public class ViewCompanyOrderController {

	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@Autowired
	protected LineCompanyItemMapper lineCompanyItemMapper;

	@RequestMapping("/shop/company/viewOrder.do")
	public ModelAndView handleRequest(@ModelAttribute("userSession") UserSession userSession,
			@RequestParam("orderId") int orderId) throws Exception {
		CompanyOrder order = this.petStore.getCompanyOrder(orderId);
		if (userSession.getAccount().getUsername().equals(order.getUsername())) {
			return new ModelAndView("ViewCompanyOrder", "companyOrder", order);
		} else {
			return new ModelAndView("Error", "message", "You may only view your own orders.");
		}
	}

	@RequestMapping("/shop/company/viewOrder2.do")
	public ModelAndView handleRequest2(@ModelAttribute("userSession") UserSession userSession,
			@RequestParam("orderId") int orderId) throws Exception {
		CompanyOrder order = this.petStore.getCompanyOrder(orderId);
		String si = null;
		List<LineCompanyItem> l = lineCompanyItemMapper.getLineCompanyItemsByOrderId(orderId);
		for (int i = 0; i < l.size(); i++) {
			if (l.get(i).getOrderId() == order.getOrderId()) {
				si = l.get(i).getShoesId();
			}
		}
		if (si != null) {
			CompanyShoes shoes = this.petStore.getCompanyShoes(si);
			if (userSession.getAccount().getUsername().equals(order.getUsername())) {
				//return new ModelAndView("ViewCompanyOrder", "companyOrder", order);
				ModelAndView mav = new ModelAndView("ViewCompanyOrder2");
				mav.addObject("companyOrder", order);
				mav.addObject("shoes", shoes);
				return mav;
			}
		}
		return new ModelAndView("Error", "message", "You may only view your own orders.");

	}
}
