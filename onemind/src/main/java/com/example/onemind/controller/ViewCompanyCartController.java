package com.example.onemind.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.example.onemind.domain.Cart;
import com.example.onemind.domain.CartItem;
import com.example.onemind.domain.CompanyCart;
import com.example.onemind.domain.CompanyCartItem;

@Controller
@SessionAttributes("companyCart")
public class ViewCompanyCartController { 
	
	@ModelAttribute("companyCart")
	public CompanyCart createCart(HttpSession session) {
		CompanyCart cart = (CompanyCart)session.getAttribute("companyCart");
		if (cart == null) cart = new CompanyCart();
		return cart;
	}
	
	@RequestMapping("/shop/company/viewCart.do")
	public ModelAndView viewCart(
			HttpServletRequest request,
			@RequestParam(value="page", required=false) String page,
			@ModelAttribute("companyCart") CompanyCart cart) 
			throws Exception {
		UserSession userSession = 
			(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		handleRequest(page, cart, userSession);
		return new ModelAndView("CompanyCart", "cart", cart);
	}

	@RequestMapping("/shop/company/checkout.do")
	public ModelAndView checkout(
			HttpServletRequest request,
			@RequestParam(value="page", required=false) String page,
			@ModelAttribute("companyCart") CompanyCart cart) 
			throws Exception {
		UserSession userSession = 
			(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		handleRequest(page, cart, userSession);
		return new ModelAndView("CompanyCheckout", "cart", cart);
	}
	
	private void handleRequest(String page, CompanyCart cart, UserSession userSession)
			throws Exception {
		if (userSession != null) {
			if ("next".equals(page)) {
				userSession.getMyList().nextPage();
			}
			else if ("previous".equals(page)) {
				userSession.getMyList().previousPage();
			}
		}
		if ("nextCart".equals(page)) {
			cart.getCartItemList().nextPage();
		}
		else if ("previousCart".equals(page)) {
			cart.getCartItemList().previousPage();
		}
	}
	@RequestMapping("/shop/company/removeItemFromCart.do")
	public ModelAndView handleRequest(
			@RequestParam("workingItemId") String workingItemId,
			@ModelAttribute("companyCart") CompanyCart cart
		) throws Exception {
		cart.removeItemById(workingItemId);
		return new ModelAndView("CompanyCart", "cart", cart);
	}
	@RequestMapping("/shop/company/updateCartQuantities.do")
	public ModelAndView handleRequest(
			HttpServletRequest request,	
			@ModelAttribute("companyCart") CompanyCart cart) throws Exception {
		Iterator<CompanyCartItem> cartItems = cart.getAllCartItems();
		while (cartItems.hasNext()) {
			CompanyCartItem cartItem = (CompanyCartItem) cartItems.next();
			String itemId = cartItem.getCompanyShoes().getShoesId();
			try {
				int quantity = Integer.parseInt(request.getParameter(itemId));
				cart.setQuantityByItemId(itemId, quantity);
				if (quantity < 1) {
					cartItems.remove();
				}
			}
			catch (NumberFormatException ex) {
				// ignore on purpose
			}
		}
		return new ModelAndView("CompanyCart", "cart", cart);
	}
}
