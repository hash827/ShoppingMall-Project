package com.example.onemind.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.example.onemind.domain.Account;
import com.example.onemind.domain.Secondhand;
import com.example.onemind.service.PetStoreFacade;

@Controller
public class SecondhandViewDetail {
	
	@Value("SecondhandDetail")
	private String successViewName;

	@Autowired
	private PetStoreFacade petStore;
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}
	
	@RequestMapping(value="/shop/secondhandViewDetail.do")
	public String handleRequest(HttpServletRequest request, @RequestParam("secondhandId") String secondhandId,
			ModelMap model) throws Exception {
		Secondhand secondhand = petStore.getSecondhand(secondhandId);
		
//		String filePath = "C:\\softImage\\" + petStore.getSecondhand(secondhandId).getFileName();
		String filePath = "/images/secondhand/" + petStore.getSecondhand(secondhandId).getFileName();
		model.put("secondhand", secondhand);
		model.put("secondhandImage", filePath);
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String username = userSession.getAccount().getUsername();
		model.put("username", username);
		if (petStore.getSecondhandFavorite(username+secondhandId) == null) {
			filePath ="/images/emptyheart.png";
			model.put("secondhandFavoriteImage", filePath);
		}
		else {
			filePath ="/images/fullheart.png";
			model.put("secondhandFavoriteImage", filePath);
		}
		
		return successViewName;
	}

}
