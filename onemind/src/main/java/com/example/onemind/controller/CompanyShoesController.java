package com.example.onemind.controller;

import java.io.File; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.onemind.domain.Auction;
import com.example.onemind.domain.AuctionAfterCollection;
import com.example.onemind.domain.CompanyShoes;
import com.example.onemind.service.PetStoreFacade;

@Controller
@SessionAttributes({ "userSession", "shoesList" })
public class CompanyShoesController {
	@Autowired
	private PetStoreFacade petStore;

	@RequestMapping("/shop/company/list.do")
	public String companyShoesList(HttpServletRequest request, Model model) {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		PagedListHolder<CompanyShoes> companys = new PagedListHolder<CompanyShoes>(petStore.getCompanyShoesList());
		companys.setPageSize(10);
		
		model.addAttribute("shoesList", companys);
		model.addAttribute("category", "list");
		return "CompanyShoesList";
	}
	@RequestMapping("/shop/company/list2.do")
	public String handleRequest2(@RequestParam("page") String page,
			@ModelAttribute("shoesList") PagedListHolder<CompanyShoes> shoesList, BindingResult result,Model model)
			throws Exception {
		if (shoesList == null) {
			throw new IllegalStateException("Cannot find pre-loaded");
		}
		if ("next".equals(page)) {
			shoesList.nextPage();
		} else if ("previous".equals(page)) {
			shoesList.previousPage();
		}
		model.addAttribute("shoesList", shoesList);
		model.addAttribute("category", "list");
		return "CompanyShoesList";
	}
	@RequestMapping("/shop/company/view.do")
	public String auctionView(@RequestParam String shoesId, Model model, HttpServletRequest request) {
		CompanyShoes company = petStore.getCompanyShoes(shoesId);
	
		
		model.addAttribute("company",company);
		return "CompanyShoesView";
	}

	
}