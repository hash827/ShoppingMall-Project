package com.example.onemind.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.onemind.domain.Cart;
import com.example.onemind.domain.CompanyCart;
import com.example.onemind.domain.CompanyShoes;
import com.example.onemind.domain.Item;
import com.example.onemind.service.PetStoreFacade;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
@SessionAttributes("companyCart")
public class AddItemToCartController { 

	private PetStoreFacade petStore;

	@Autowired
	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@ModelAttribute("companyCart")
	public CompanyCart createCart() {
		return new CompanyCart();
	}
	@ResponseBody
	@RequestMapping("/shop/addItemToCart.do")
	public String addItemToCart(
			@RequestParam String shoesId, @RequestParam String size,
			@ModelAttribute("companyCart")CompanyCart cart ,Model model, HttpServletRequest request
			) throws Exception {
		String workingItemId = shoesId;
		String result = "";
		if (cart.containsItemId(workingItemId)) {
			/* cart.incrementQuantityByItemId(workingItemId); */
			result += "no";
		}
		else {
			// isInStock is a "real-time" property that must be updated
			// every time an item is added to the cart, even if other
			// item details are cached.
			/*
			 * boolean isInStock = this.petStore.isItemInStock(workingItemId); Item item =
			 * this.petStore.getItem(workingItemId);
			 */
			//받아오기
			
			//shoesId 이용해서 CompanyShoes 받아오기 
			CompanyShoes companyShoes = petStore.getCompanyShoes(shoesId);
			System.out.println("신발 사이즈" + size);
			//받아와서 companyShoes에 setSize 해주기
			companyShoes.setShoesSize(size);
			
			cart.addItem(companyShoes);
			
			result += "yes";
		}
		return result;
	}
}