package com.example.onemind.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CompanyShoes implements Serializable{
	private String shoesId;
	private String shoesName;
	private int price;
	private String shoesImage;
	private String shoesColor;
	private String shoesSize;
	private String shoesDescription;
	
	public String getShoesId() {
		return shoesId;
	}
	public void setShoesId(String shoesId) {
		this.shoesId = shoesId;
	}
	public String getShoesName() {
		return shoesName;
	}
	public void setShoesName(String shoesName) {
		this.shoesName = shoesName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getShoesImage() {
		return shoesImage;
	}
	public void setShoesImage(String shoesImage) {
		this.shoesImage = shoesImage;
	}
	public String getShoesColor() {
		return shoesColor;
	}
	public void setShoesColor(String shoesColor) {
		this.shoesColor = shoesColor;
	}
	public String getShoesSize() {
		return shoesSize;
	}
	public void setShoesSize(String shoesSize) {
		this.shoesSize = shoesSize;
	}
	public String getShoesDescription() {
		return shoesDescription;
	}
	public void setShoesDescription(String shoesDescription) {
		this.shoesDescription = shoesDescription;
	}
	
	
	
}
