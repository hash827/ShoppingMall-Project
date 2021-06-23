package com.example.onemind.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class Secondhand implements Serializable {

	/* Private Fields */
	private String secondhandId;
	private String username;
	private String title;
	private String price;
	private String secondhandDescription;
	private Date secondhandDate;
	private String secondhandState;
	private String filename;
	private Long filesize;
	private byte[] filedata;
	private String buyer;
	private Date pDate;

	/* JavaBeans Properties */
	public String getSecondhandId() {
		return secondhandId;
	}

	public Date getpDate() {
		return pDate;
	}

	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}

	public void setSecondhandId(String secondhandId) {
		this.secondhandId = secondhandId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFileName() {
		return filename;
	}

	public void setFileName(String fileName) {
		this.filename = fileName;
	}

	public Long getFileSize() {
		return filesize;
	}

	public void setFileSize(Long fileSize) {
		this.filesize = fileSize;
	}

	public byte[] getFileData() {
		return filedata;
	}

	public void setFileData(byte[] fileData) {
		this.filedata = fileData;
	}

	public Date getSecondhandDate() {
		return secondhandDate;
	}

	public void setSecondhandDate(Date secondhandDate) {
		this.secondhandDate = secondhandDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSecondhandState() {
		return secondhandState;
	}

	public void setSecondhandState(String secondhandState) {
		this.secondhandState = secondhandState;
	}

	public String getSecondhandDescription() {
		return secondhandDescription;
	}

	public void setSecondhandDescription(String secondhandDescription) {
		this.secondhandDescription = secondhandDescription;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public void initSecondhand(Account account) {
		username = account.getUsername();
		// java 현재시간+랜덤문자로 고유한 유니크 값 만들기
		// https://toshi15shkim.github.io/articles/2019-10/java-unique-id
		String uniqueId = "";
        SimpleDateFormat sdf = new SimpleDateFormat("ddmmss");
        Calendar dateTime = Calendar.getInstance();
        uniqueId = sdf.format(dateTime.getTime());
        //ddmmss+랜덤문자3개 = 9
        uniqueId = uniqueId + RandomStringUtils.randomAlphanumeric(3);
        setSecondhandId(uniqueId);
	    secondhandDate = new Date();
	    //setBuyer(null);
//	    SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
//	    String sd = format.format(secondhandDate);
//	    setSecondhandId(username+sd.substring(0,14));
	}
	
	public String toString() {
	    return getSecondhandId();
	  }
}
