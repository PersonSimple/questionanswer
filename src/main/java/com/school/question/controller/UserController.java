package com.school.question.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.school.question.model.User;
import com.school.question.service.LoginService;
//delete this file once tested 
@Controller
public class UserController {
	
	@Autowired
	private LoginService loginService;
	/*
	
	@Autowired
	UserService service;
	
	
	   @PostMapping("/uploadFile")
	    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
	      DBFile dbFile = dbFileStorageService.storeFile(file);

	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(dbFile.getId())
	                .toUriString();

	        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
	                file.getContentType(), file.getSize());
	    }
	   */
	/**
	 * link on menu bar of on home page
	 * @param model
	 * @return
	 */
	   @GetMapping("/admin/enterLoginId")
	    public String enterLoginId(Model model) {
		   model.addAttribute("user", new User());
		   return "login/enterLoginId";
	    }
	   
	   /**
	    * enter login to search the user 
	    * @param model
	    * @return
	    */
	   @PostMapping("/admin/searchUsingLoginId")
	    public String searchByLoginId(Model model,@RequestParam String userName) {
		   System.out.println( "UserName " +userName);
		   User user = loginService.searchByLoginId(userName).get();
		   model.addAttribute("user", user);
		   
		   //using this userName we get the user object 
		   //loginService.searchByLoginId();
		   return "login/changePassword";
	    }
	   
	   
	   @PostMapping("/admin/changePassword")
	    public String changePassword(Model model ,@RequestParam String password,@RequestParam Long id) {
		    //call the method to change the password.
		  loginService.changePassword(password,id);
		  model.addAttribute("message","Password Changed");
		  return "home";
	    }
	   

}
