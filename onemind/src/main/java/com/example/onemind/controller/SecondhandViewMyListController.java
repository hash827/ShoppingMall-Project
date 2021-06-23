package com.example.onemind.controller;

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

import com.example.onemind.domain.Cart;
import com.example.onemind.domain.Category;
import com.example.onemind.domain.Product;
import com.example.onemind.domain.Secondhand;
import com.example.onemind.service.PetStoreFacade;

@Controller
@SessionAttributes({ "userSession", "secondhand", "secondhandList" })
public class SecondhandViewMyListController {
	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@RequestMapping("/shop/secondhandViewMyList.do")
	public String handleRequest(HttpServletRequest request, ModelMap model) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String username = userSession.getAccount().getUsername();
		
		PagedListHolder<Secondhand> secondhandList = new PagedListHolder<Secondhand>(this.petStore.getSecondhandsByUsername(username));
		secondhandList.setPageSize(5);
		model.put("secondhandList", secondhandList);
		model.addAttribute("category", "myList");
		return "SecondhandMyList";
	}

	@RequestMapping("/shop/secondhandViewMyList2.do")
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
		return "SecondhandMyList";
	}
	
	@RequestMapping("/shop/secondhandDelete.do")
	public String handleRequest3(
			@RequestParam("secondhandId") String secondhandId
		) throws Exception {
		Secondhand secondhand = petStore.getSecondhand(secondhandId);
		petStore.deleteSecondhand(secondhand);
		return "redirect:/shop/secondhandViewMyList.do";
	}
}
