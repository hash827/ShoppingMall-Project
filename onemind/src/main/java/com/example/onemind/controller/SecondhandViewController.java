package com.example.onemind.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.example.onemind.domain.Auction;
import com.example.onemind.domain.Cart;
import com.example.onemind.domain.Secondhand;
import com.example.onemind.service.PetStoreFacade;

@Controller
@SessionAttributes({ "secondhand", "secondhandList" })
public class SecondhandViewController {
	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@RequestMapping("/shop/main/secondhand.do")
	public String handleRequest(@RequestParam(value = "secondhandState", defaultValue = "ALL") String secondhandState,
			HttpServletRequest request, ModelMap model) throws Exception {
		PagedListHolder<Secondhand> secondhandList = null;
		if(secondhandState.equals("ALL")) {
			secondhandList = new PagedListHolder<Secondhand>(this.petStore.getSecondhandList());
		} else {
			secondhandList = new PagedListHolder<Secondhand>(this.petStore.getSecondhandsByState(secondhandState));
		}
		secondhandList.setPageSize(5);
		model.put("secondhandList", secondhandList);
		model.put("secondhandState", secondhandState);
		model.addAttribute("category", "main");
		return "SecondhandMain";
	}

	@RequestMapping("/shop/main/secondhand2.do")
	public String handleRequest2(@RequestParam("page") String page,
			@ModelAttribute("secondhandList") PagedListHolder<Secondhand> secondhandList, BindingResult result)
			throws Exception {
		if (secondhandList == null) {
			throw new IllegalStateException("Cannot find pre-loaded");
		}
		if ("next".equals(page)) {
			secondhandList.nextPage();
		} else if ("previous".equals(page)) {
			secondhandList.previousPage();
		}
		return "SecondhandMain";
	}
}
