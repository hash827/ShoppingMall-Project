package com.example.onemind.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CompanyCartItem implements Serializable {

	/* Private Fields */

	private CompanyShoes companyShoes;
	private int quantity;

	/* JavaBeans Properties */

	public int getQuantity() {
		return quantity;
	}

	public CompanyShoes getCompanyShoes() {
		return companyShoes;
	}

	public void setCompanyShoes(CompanyShoes companyShoes) {
		this.companyShoes = companyShoes;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		if (companyShoes != null) {
			return companyShoes.getPrice() * quantity;
		} else {
			return 0;
		}
	}

	/* Public methods */

	public void incrementQuantity() {
		quantity++;
	}

}
