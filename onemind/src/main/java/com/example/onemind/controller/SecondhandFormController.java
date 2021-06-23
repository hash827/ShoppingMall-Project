package com.example.onemind.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import com.example.onemind.domain.Account;
import com.example.onemind.domain.Category;
import com.example.onemind.domain.Product;
import com.example.onemind.domain.Secondhand;
import com.example.onemind.service.AccountFormValidator;
import com.example.onemind.service.PetStoreFacade;

@Controller
//@RequestMapping({ "/shop/secondhandAdd.do", "/shop/secondhandEdit.do" })
public class SecondhandFormController {

	@Value("SecondhandAddForm")
	private String formViewName1;
	@Value("SecondhandEditForm")
	private String formViewName2;
	@Value("SecondhandMain")
	private String successViewName;

	@Autowired
	private PetStoreFacade petStore;

	public void setPetStore(PetStoreFacade petStore) {
		this.petStore = petStore;
	}

	@ModelAttribute("secondhand")
	public Secondhand formBacking(HttpServletRequest request) throws Exception {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			Secondhand secondhand = new Secondhand();
			return secondhand;
		} else {
			return new Secondhand();
		}
	}

	@RequestMapping(value = "/shop/secondhandAdd.do", method = RequestMethod.GET)
	public String showForm(ModelMap model) {
		model.addAttribute("category", "add");
		return formViewName1;
	}

	@RequestMapping(value = "/shop/secondhandEdit.do", method = RequestMethod.GET)
	public String showForm2(HttpServletRequest request, @RequestParam("secondhandId") String secondhandId,
			ModelMap model) throws Exception {
		Secondhand secondhand = petStore.getSecondhand(secondhandId);
		model.put("secondhand", secondhand);
		model.put("fileName", secondhand.getFileName());
		// model.addAttribute("category", "add");
		return formViewName2;
	}

	@RequestMapping(value = "/shop/secondhandAdd.do", method = RequestMethod.POST)
	public String onSubmit(MultipartHttpServletRequest request,
			@ModelAttribute("secondhand") SecondhandForm secondhandForm, BindingResult result) throws IOException {

		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		Account account = petStore.getAccount(userSession.getAccount().getUsername());
		secondhandForm.getSecondhand().initSecondhand(account);
		
		//경로가 사용자마다 다름
		String base_path = "C:\\Users\\USER\\Desktop\\ssdtp\\onemind\\src\\main\\resources\\static\\images\\secondhand\\";
		MultipartFile report = request.getFile("report");
		String fileName = secondhandForm.getSecondhand().getSecondhandId()+report.getOriginalFilename();
		long fileSize = report.getSize();
		byte[] fileData = report.getBytes();
		File folder = new File(base_path);
		if (!folder.exists())
			folder.mkdir();
		FileOutputStream fos = new FileOutputStream(base_path + fileName);
		fos.write(fileData);
		fos.close();
		secondhandForm.getSecondhand().setFileName(fileName);
		secondhandForm.getSecondhand().setFileSize(fileSize);
		secondhandForm.getSecondhand().setFileData(fileData);
		petStore.insertSecondhand(secondhandForm.getSecondhand());

		return "redirect:/shop/main/secondhand.do";
	}
	
	@RequestMapping(value = "/shop/secondhandEdit.do", method = RequestMethod.POST)
	public String onSubmit2(MultipartHttpServletRequest request,
			@ModelAttribute("secondhand") SecondhandForm secondhandForm, BindingResult result) throws IOException {

//		String base_path = "C:\\softImage";
//		MultipartFile report = request.getFile("report");
//		String fileName = report.getOriginalFilename();
//		long fileSize = report.getSize();
//		byte[] fileData = report.getBytes();
//		File folder = new File(base_path);
//		if (!folder.exists())
//			folder.mkdir();
//		FileOutputStream fos = new FileOutputStream(base_path + "\\" + fileName);
//		fos.write(fileData);
//		fos.close();
//		secondhandForm.getSecondhand().setFileName(fileName);
//		secondhandForm.getSecondhand().setFileSize(fileSize);
//		secondhandForm.getSecondhand().setFileData(fileData);
		
		MultipartFile report = request.getFile("report");
		String fileName = secondhandForm.getSecondhand().getSecondhandId()+report.getOriginalFilename();
		long fileSize = report.getSize();
		byte[] fileData = report.getBytes();
		
		String base_path = "C:\\Users\\USER\\Desktop\\ssdtp\\onemind\\src\\main\\resources\\static\\images\\secondhand\\";
		FileOutputStream fos = new FileOutputStream(base_path + fileName);
		fos.write(fileData);
		fos.close();
		
		secondhandForm.getSecondhand().setFileName(fileName);
		secondhandForm.getSecondhand().setFileSize(fileSize);
		secondhandForm.getSecondhand().setFileData(fileData);
		petStore.updateSecondhand(secondhandForm.getSecondhand());

		return "redirect:/shop/main/secondhand.do";
	}

}
