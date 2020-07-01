package com.school.question.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.school.question.model.DBFile;
import com.school.question.model.LoggedUser;
import com.school.question.model.Question;
import com.school.question.payload.UploadFileResponse;
import com.school.question.service.QuestionService;

@Controller
public class QuestionController {
	
	@Autowired
	QuestionService service;

	
	
	   @RequestMapping("/user/question")
	    public String studentQuestion(Model model) {
		   LoggedUser loggedUser = new LoggedUser();
		   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		   loggedUser.setUserName(authentication.getName());
		   model.addAttribute("loggedUser",loggedUser);
		   
		   return "question";
	        

	    }
	   
	   
	   @GetMapping("/user/questionList")
	   public String greetingForm(Model model) {
			
		   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			
		   List<Question> questionList= service.questionList(authentication.getName());
		   model.addAttribute("questionList",questionList);
			
			
			return "questionList";
	   }
	   
	   /**
	    *  current online question submit on this url 
	    * @param question
	    * @return
	    */

	   @PostMapping("/user/question")
	   public String greetingSubmit(@ModelAttribute Question question) {
		   
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			question.setUserName(authentication.getName());
			long millis=System.currentTimeMillis();  
		   
			question.setQuestion_date(new java.sql.Date(millis));
			
			service.save(question);
			
		    return "redirect:/user/questionList/" ;
		   
	      }
	   
	   /**
	    * this method is putting user and question into model
	    * @param id
	    * @param model
	    * @return
	    */
	   @GetMapping("/question/edit")
	   public String editQuestion(@RequestParam long id, Model model) {
		   LoggedUser loggedUser = new LoggedUser();
		   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		   loggedUser.setUserName(authentication.getName());
		   model.addAttribute("loggedUser",loggedUser);
		   
		   Optional<Question> question = service.serachQuestion(id);
		   model.addAttribute("question",question.get());
		   
		   return "questionEdit";
		   
	   }
	   
	   /**
	    * delete the record . right now i am not disabling it.
	    * @param id
	    * @return
	    */
	   
	   @GetMapping("/admin/question/delete")
	   public String deleteQuestion(@RequestParam long id) {
		   
		   service.deleteQuestion(id);
		   
		   return "redirect:/user/questionList/" ;
		   
	   }

}
