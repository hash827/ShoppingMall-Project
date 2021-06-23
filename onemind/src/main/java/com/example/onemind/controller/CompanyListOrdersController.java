package com.example.onemind.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.onemind.domain.CompanyOrder;
import com.example.onemind.domain.Secondhand;
import com.example.onemind.service.PetStoreFacade;

@Controller
@SessionAttributes({"userSession", "orderCompanyList"})
public class CompanyListOrdersController {

	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

//	@RequestMapping("/shop/companyViewPurchaseList.do")
//	public ModelAndView handleRequest(@ModelAttribute("userSession") UserSession userSession) throws Exception {
//		String username = userSession.getAccount().getUsername();
//		return new ModelAndView("CompanyPurchaseList", "orderCompanyList", petStore.getCompanyOrdersByUsername(username));
//	}
	
	@RequestMapping("/shop/companyViewPurchaseList.do")
	public String handleRequest(@ModelAttribute("userSession") UserSession userSession, HttpServletRequest request, ModelMap model) throws Exception {
		String username = userSession.getAccount().getUsername();
		
		PagedListHolder<CompanyOrder> orderCompanyList = new PagedListHolder<CompanyOrder>(this.petStore.getCompanyOrdersByUsername(username));
		orderCompanyList.setPageSize(5);
		model.put("orderCompanyList", orderCompanyList);
		model.addAttribute("category", "purchase");
		return "CompanyPurchaseList";
	}

	@RequestMapping("/shop/companyViewPurchaseList2.do")
	public String handleRequest2(@RequestParam("page") String page,
			@ModelAttribute("orderCompanyList") PagedListHolder<CompanyOrder> orderCompanyList, BindingResult result)
			throws Exception {
		if (orderCompanyList == null) {
			throw new IllegalStateException("Cannot find pre-loaded");
		}
		if ("next".equals(page)) {
			orderCompanyList.nextPage();
		} else if ("previous".equals(page)) {
			orderCompanyList.previousPage();
		}
		return "CompanyPurchaseList";
	}

}