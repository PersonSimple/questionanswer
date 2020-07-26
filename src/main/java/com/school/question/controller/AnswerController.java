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
	    * it will save a new record in answer table for same question_id
	    * @param model
	    * @param aid
	    * @param qid
	    * @return
	    */
	
	   @GetMapping("/user/answer/discuss")
	    public String answerDiscuss(Model model,@RequestParam long aid,@RequestParam long qid) {
		   Optional<Answer> answer =  answerService.findById(aid);
		   answer.get().setId(0);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   answerService.answerSave(answer);
	       return "answer/answerDiscuss";
	        
	    }

	   
	   ///----------------------------------
	
	   
	   
	   @GetMapping("/user/answer/edit")
	    public String answerEdit(Model model,@RequestParam long aid,@RequestParam long qid) {
		   Optional<Answer> answer =  answerService.findById(aid);
		   Optional<Question> question = questionService.findById(qid);
		   /*AnswerDto dto = new AnswerDto();
		   dto.setAnswer(answer.get());
		   dto.setQuestion(question.get());*/
		   
		   
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
			answer.setStudentName(studentName); // this value can be fetched from database using the question id
	    	model.addAttribute("answer",answer);
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
		   answerService.answerSave(answer);
	        return "answer/answer";
	        
	    }
	   
	   
/*	   @GetMapping(path = { "/user/{id}" })
	    public Answer getImage(@PathVariable("id") long id) throws IOException {
	        final Optional<Answer> retrievedImage = service.findById(id);
	        Answer img = new Answer(retrievedImage.get().getName(), retrievedImage.get().getType(),
	                decompressBytes(retrievedImage.get().getPicByte()));
	        return retrievedImage.get();
	    }
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
