package com.example.onemind.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.example.onemind.domain.Account;
import com.example.onemind.domain.CompanyCart;
import com.example.onemind.domain.CompanyOrder;
import com.example.onemind.domain.CompanyShoes;
import com.example.onemind.domain.LineCompanyItem;
import com.example.onemind.service.CompanyOrderValidator;
import com.example.onemind.service.PetStoreFacade;

@Controller
@SessionAttributes({"companyCart", "companyOrderForm"})
public class CompanyOrderController {
	@Autowired
	private PetStoreFacade petStore;
	@Autowired
	private CompanyOrderValidator companyOrderValidator;
	
	@ModelAttribute("companyOrderForm")
	public CompanyOrderForm createOrderForm() {
		return new CompanyOrderForm();
	}
	
	private String username;
	
	@ModelAttribute("creditCardTypes")
	public List<String> referenceData() {
		ArrayList<String> creditCardTypes = new ArrayList<String>();
		creditCardTypes.add("Visa");
		creditCardTypes.add("MasterCard");
		creditCardTypes.add("American Express");
		return creditCardTypes;			
	}
	
	@RequestMapping("/shop/company/newOrder.do")
	public String initNewOrder(HttpServletRequest request,
			@ModelAttribute("companyCart") CompanyCart cart,
			@ModelAttribute("companyOrderForm") CompanyOrderForm orderForm
			) throws ModelAndViewDefiningException {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		username = userSession.getAccount().getUsername();
		if (cart != null) {
			// Re-read account from DB at team's request.
			Account account = petStore.getAccount(userSession.getAccount().getUsername());
			orderForm.getCompanyOrder().initCompanyOrder(account, cart);
			return "CompanyNewOrderForm";	
		}
		else {
			ModelAndView modelAndView = new ModelAndView("Error");
			modelAndView.addObject("message", "An order could not be created because a cart could not be found.");
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}
	
	@RequestMapping("/shop/company/newOrderSubmitted.do")
	public String bindAndValidateOrder(HttpServletRequest request,
			@ModelAttribute("companyOrderForm") CompanyOrderForm companyOrderForm, 
			BindingResult result) {
		if (companyOrderForm.didShippingAddressProvided() == false) {	
			// from NewOrderForm
			companyOrderValidator.validateCreditCard(companyOrderForm.getCompanyOrder(), result);
			companyOrderValidator.validateBillingAddress(companyOrderForm.getCompanyOrder(), result);
			if (result.hasErrors()) return "CompanyNewOrderForm";
			
			if (companyOrderForm.isShippingAddressRequired() == true) {
				companyOrderForm.setShippingAddressProvided(true);
				return "CompanyShippingForm";
			}
			else {			
				return "ConfirmCompanyOrder";
			}
		}
		else {		// from ShippingForm
			companyOrderValidator.validateShippingAddress(companyOrderForm.getCompanyOrder(), result);
			if (result.hasErrors()) return "CompanyShippingForm";
			return "ConfirmCompanyOrder";
		}
	}
	
	@RequestMapping("/shop/company/confirmOrder.do")
	protected ModelAndView confirmOrder(
			@ModelAttribute("companyOrderForm") CompanyOrderForm companyOrderForm, 
			SessionStatus status) {
		List<LineCompanyItem> cList = companyOrderForm.getCompanyOrder().getLineCompanyItems();
		List<String> l = new ArrayList<String>();

		for (int i = 0; i < cList.size(); i++) {
			String shoesId = cList.get(i).getShoesId();
			if (!l.contains(shoesId)) {
				l.add(shoesId);
			} else {
				ModelAndView mav = new ModelAndView("ViewCompanyOrder");
				mav.addObject("message", "상품 중복으로 인해 주문이 취소되었습니다.");
				mav.addObject("url", "/shop/index.do");
				status.setComplete();
				return mav;
			}
		}
		petStore.insertCompanyOrder(companyOrderForm.getCompanyOrder());
		ModelAndView mav = new ModelAndView("ViewCompanyOrder");
		mav.addObject("companyOrder", companyOrderForm.getCompanyOrder());
		mav.addObject("message", "Thank you, your order has been submitted.");
		status.setComplete();  // remove sessionCart and orderForm from session
		return mav;
	}
}