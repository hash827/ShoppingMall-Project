package com.example.onemind;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier(value = "signonInterceptor")
	private HandlerInterceptor interceptor;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/shop/index.do").setViewName("index");
		registry.addViewController("/shop/signonForm.do").setViewName("SignonForm");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor).addPathPatterns("/shop/editAccount.do", "/shop/company/listOrders.do",
				"/shop/company/checkout.do", "/shop/company/viewOrder.do", "/shop/company/newOrder.do",
				"/shop/secondhandAdd.do", "/shop/secondhandEdit.do", "/shop/secondhandViewMyList.do",
				"/shop/secondhandViewFavoriteList.do", "/shop/secondhandFavoriteInsert.do",
				"/shop/secondhandViewDetail.do", "/shop/secondhandViewChattingList.do", "/shop/secondhandViewChat.do",
				"/shop/auction/list.do", "/shop/auction/favorite/list.do", "/shop/main/auctionList.do", "/shop/secondhandViewPurchaseList.do", "/shop/companyViewPurchaseList.do");
	}

}
