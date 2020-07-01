package com.school.question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.school.question.model.Answer;
import com.school.question.model.Question;
import com.school.question.service.AnswerServiceImp;

@Controller
public class AnswerController {
	
	@Autowired
	private AnswerServiceImp service;
	
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
	   @RequestMapping("/student/answer")
	    public String studentQuestion(Model model) {
		   model.addAttribute("message","Hello Kailash!!");
		   
		   return "studentQuestion";
	    }
	   
	   
	   /**
	    *  answerList method get method for current user he may be teacher or student
	    *  this list the answer for the user need to provide the user id . 
	    *  filter record on the basis of user id will list in this view  
	    * @param model
	    * @return
	    */

	   @GetMapping("/user/answerList")
	   public String answerList(Model model) {
		   
		   
		   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		   
		   List<Answer> answerList= service.answerList(authentication.getName(),authentication.getName());
		   
		   model.addAttribute("answerList",answerList);
	
	    	return "answer/answerList";
	        
	    }
	   
	   
	   /**
	    * this method for answer will populate the question_id form question page 
	    * and userName from current login account
	    * template is under the answer folder
	    * 
	    * @param id
	    * @param model
	    * @return
	    */
	   
	   @GetMapping("/admin/answer")
	   public String answer(@RequestParam long id, @RequestParam String studentName, Model model) {
		   
		    Answer answer = new Answer();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			answer.setTeacherName(authentication.getName());
			answer.setQuestion_id(id);
			answer.setStudentName(studentName);

			
	    	model.addAttribute("answer",answer);
	    	
	        return "answer/answer";
	        
	        
	    }
	   

	   /**
	    * collecting information in answer object and passing to service answerSave method
	    * this method return the view answer of the answer module
	    * @param answer
	    * @return
	    */
	   @PostMapping("/admin/answer")
	   public String answerSave(@ModelAttribute Answer answer) {
		   service.answerSave(answer);
	        return "answer/answer";
	        
	    }
	   
	   


}
