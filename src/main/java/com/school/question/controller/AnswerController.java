package com.school.question.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.school.question.dto.AnswerDto;
import com.school.question.model.Answer;
import com.school.question.model.Question;
import com.school.question.service.AnswerServiceImp;
import com.school.question.service.QuestionServiceImpl;

@Controller
public class AnswerController {
	
	@Autowired
	private AnswerServiceImp answerService;
	
	@Autowired
	private QuestionServiceImpl  questionService;
	
	
	   @RequestMapping("/student/answer")
	    public String studentQuestion(Model model) {
		   model.addAttribute("message","Hello Kailash!!");
		   return "studentQuestion";
	    }
	   
	   /**
	    * it will save a new record in answer table for same question_id
	    * @param model
	    * @param aid
	    * @param qid
	    * @return
	    */
	
	   @GetMapping("/user/answer/discuss")
	    public String answerDiscuss(Model model,@RequestParam long aid,@RequestParam long qid) {
		   List<Answer> answerList= answerService.findByQuestion_id(qid);
		   Optional<Answer> answer =  answerService.findById(aid);
		   model.addAttribute("answerList", answerList);
		   model.addAttribute("answer",answer.get());
		   return "answer/answerDiscuss";
	    }  
	   
/**
 * this will save a new record in answer table
 * @param model
 * @param aid
 * @param qid
 * @return
 */
	   @PostMapping("/user/student/discuss")
	   public String answerUserDiscuss(@ModelAttribute Answer answer,@RequestParam("file") MultipartFile file) {
		   try {
			   answer.setFileName(file.getOriginalFilename());
			   answer.setFileType(file.getContentType());
			   answer.setData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		   // to save the new record into answer table
		   answer.setId(0);
		   answerService.answerSave(answer);
		   return "redirect:/user/answerList";
	    }
	   
	   /**
	    * This is extra method i feel . we should remove this method edit functionality
	    * must be in control of teacher only
	    * @param model
	    * @param aid
	    * @param qid
	    * @return
	    */

	   @GetMapping("/admin/answer/edit")
	    public String answerEdit(Model model,@RequestParam long aid,@RequestParam long qid) {
		   Optional<Answer> answer =  answerService.findById(aid);
		   Optional<Question> question = questionService.findById(qid);
		   model.addAttribute("question",question.get());
		   model.addAttribute("answer",answer.get());
		   return "answer/answer";
	    }  
	   
	   /**
	    *  answerList get method for current user he may be teacher or student
	    *  to get list of answer user need to provide the user id . 
	    *  filter record on the basis of user id will list in this view  
	    * @param model
	    * @return
	    */

	   @GetMapping("/user/answerList")
	   public String answerList(Model model) {
		   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		   List<Answer> answerList= answerService.answerList(authentication.getName(),authentication.getName());
		   model.addAttribute("answerList",answerList);
	       return "answer/answerList";
	    }
	 
	   /**
	    * 
		* this method for answer screen. teacher will  populate the and submit 
	    * and userName from current login account template is under the answer folder
	    * @param id
	    * @param studentName  ( student identification)
	    * @param question ( question string)
	    * @param status (current status of question )
	    * @param subject (subject stream of question)
	    * @param model  ( form values )
	    * @return
	    * 
	    */
	   
	   @GetMapping("/admin/answer")
	   public String answer(@RequestParam long id, 
			   				@RequestParam String studentName,
			   				@RequestParam String question,
			   				@RequestParam String status,
			   				@RequestParam String subject,
			   				Model model) 
	   {
		    Answer answer = new Answer();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			answer.setTeacherName(authentication.getName());
			answer.setQuestion_id(id);
			answer.setStudentName(studentName); 
	    	model.addAttribute("answer",answer);
	    	model.addAttribute("question",question);
	    	model.addAttribute("status",status);
	    	model.addAttribute("subject",subject);
	        return "answer/answer";
	    }
	   

	   /**
	    * collecting information in answer object and passing to service answerSave method
	    * this method return the view answer of the answer module. only admin will save
	    * @param answer
	    * @return
	    */
	   @PostMapping("/admin/answer")
	   public String answerSave(@ModelAttribute Answer answer,@RequestParam("file") MultipartFile file) {
		   try {
			   answer.setFileName(file.getOriginalFilename());
			   answer.setFileType(file.getContentType());
			answer.setData(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   long millis=System.currentTimeMillis();  
		   answer.setAnswer_date(new java.sql.Date(millis));
		   Answer ans=  answerService.answerSave(answer);
		   questionService.modifyStatus(ans.getQuestion_id(),"Answered"); // First time answered:status save 
		   return "redirect:/user/answerList";
		      
	    }

	   /**
	    * this code download the image on the client machine. 
        * fileName is a numeric id of answer which is linked with image	 
	    * @param fileName
	    * @return
	    */
	   @GetMapping("/user/{fileName}")
	   public ResponseEntity getImage(@PathVariable long fileName) {
	   	Answer document = answerService.findById(fileName).get();
	   	return ResponseEntity.ok()
	   			.contentType(MediaType.parseMediaType("application/octet-stream"))
	   			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
	   			.body(document.getData());
	   }
}
