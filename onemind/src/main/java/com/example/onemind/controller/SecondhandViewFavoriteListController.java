package com.example.onemind.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.util.WebUtils;

import com.example.onemind.domain.Account;
import com.example.onemind.domain.Secondhand;
import com.example.onemind.domain.SecondhandFavorite;
import com.example.onemind.service.PetStoreFacade;

@Controller
@SessionAttributes({ "userSession", "secondhand", "secondhandFavorite", "secondhandFavoriteList" })
public class SecondhandViewFavoriteListController {
	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@RequestMapping("/shop/secondhandViewFavoriteList.do")
	public String handleRequest(HttpServletRequest request, ModelMap model) throws Exception {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String username = userSession.getAccount().getUsername();

		PagedListHolder<SecondhandFavorite> secondhandFavoriteList = new PagedListHolder<SecondhandFavorite>(
				this.petStore.getSecondhandFavoritesByUsername(username));
		secondhandFavoriteList.setPageSize(5);
		
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		List<SecondhandFavorite> list = this.petStore.getSecondhandFavoritesByUsername(username);
		for (int i=0; i<list.size(); i++) {
			Map<String,String> map = new HashMap<String, String>();
			SecondhandFavorite favorite = list.get(i);
			Secondhand second = this.petStore.getSecondhand(favorite.getSecondhandId());
			map.put("secondhandFavoriteId", favorite.getSecondhandFavoriteId());
			map.put("secondhandId", second.getSecondhandId());
			map.put("secondhandState", second.getSecondhandState());
			map.put("title", second.getTitle());
			map.put("price", second.getPrice());
			resultList.add(map);
		}
		model.put("secondhandFavoriteList", secondhandFavoriteList);
		model.put("secondhand", resultList);
		model.addAttribute("category", "favorite");
		return "SecondhandFavoriteList";
	}

	@RequestMapping("/shop/secondhandViewFavoriteList2.do")
	public String handleRequest2(@RequestParam("page") String page,
			@ModelAttribute("secondhandFavoriteList") PagedListHolder<SecondhandFavorite> secondhandFavoriteList,
			BindingResult result) throws Exception {
		if (secondhandFavoriteList == null) {
			throw new IllegalStateException("Cannot find pre-loaded");
		}
		if ("next".equals(page)) {
			secondhandFavoriteList.nextPage();
		} else if ("previous".equals(page)) {
			secondhandFavoriteList.previousPage();
		}
		return "SecondhandFavoriteList";
	}

	@RequestMapping("/shop/secondhandFavoriteDelete.do")
	public String handleRequestDeleteFavorite(HttpServletRequest request,
			@RequestParam("secondhandFavoriteId") String secondhandFavoriteId) throws Exception {
		SecondhandFavorite secondhandFavorite = petStore.getSecondhandFavorite(secondhandFavoriteId);
		petStore.deleteSecondhandFavorite(secondhandFavorite);
		return "redirect:/shop/secondhandViewFavoriteList.do";
	}

	@RequestMapping(value = "/shop/secondhandFavoriteInsert.do")
	public String handleRequestInsertFavorite(HttpServletRequest request,
			@RequestParam("secondhandId") String secondhandId) throws Exception {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		Account account = petStore.getAccount(userSession.getAccount().getUsername());
		Secondhand secondhand = petStore.getSecondhand(secondhandId);

		if (checkFavorite(account, secondhand)) {
			SecondhandFavorite secondhandFavorite = petStore
					.getSecondhandFavorite(account.getUsername() + secondhand.getSecondhandId());
			petStore.deleteSecondhandFavorite(secondhandFavorite);
		} else {
			SecondhandFavorite secondhandFavorite = new SecondhandFavorite();
			secondhandFavorite.initSecondhandFavorite(account, secondhand);

			petStore.insertSecondhandFavorite(secondhandFavorite);
		}

		return "redirect:/shop/secondhandViewFavoriteList.do";
	}

	public boolean checkFavorite(Account account, Secondhand secondhand) {
		if (petStore.getSecondhandFavorite(account.getUsername() + secondhand.getSecondhandId()) == null)
			return false;
		else
			return true;
	}
}