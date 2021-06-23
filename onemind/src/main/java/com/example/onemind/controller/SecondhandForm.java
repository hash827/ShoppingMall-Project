package com.example.onemind.controller;

import java.io.Serializable;

import com.example.onemind.domain.Account;
import com.example.onemind.domain.Secondhand;

@SuppressWarnings("serial")
public class SecondhandForm implements Serializable {

	private Secondhand secondhand;
	private boolean newSecondhand;

	public SecondhandForm(Secondhand secondhand) {
		this.secondhand = secondhand;
		this.newSecondhand = false;
	}

	public SecondhandForm() {
		this.secondhand = new Secondhand();
		this.newSecondhand = true;
	}

	public Secondhand getSecondhand() {
		return secondhand;
	}

	public boolean isNewSecondhand() {
		return newSecondhand;
	}

	public void setNewSecondhand(boolean newSecondhand) {
		this.newSecondhand = newSecondhand;
	}
}
