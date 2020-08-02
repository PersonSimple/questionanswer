package com.school.question.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
	

	/*
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
	   @RequestMapping("/student/general")
	    public String studentQuestion(Model model) {
		   
		   model.addAttribute("message","Hello Kailash!!");
		   
		   return "studentQuestion";
	        

	    }

}
