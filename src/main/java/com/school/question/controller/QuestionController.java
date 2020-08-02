package com.school.question.controller;

import java.util.List;
import java.util.Optional;

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

import com.school.question.model.ExamUserDetail;
import com.school.question.model.LoggedUser;
import com.school.question.model.Question;
import com.school.question.service.QuestionServiceImpl;

@Controller
public class QuestionController {
	
	@Autowired
	QuestionServiceImpl service;

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
		   String role = authentication.getAuthorities().toArray()[0].toString();
		  // List<Question> questionList= service.questionList(authentication.getName());
		   List<Question> questionList= service.questionList(authentication);
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

			question.setStudentName(authentication.getName());
			long millis=System.currentTimeMillis();  
			question.setQuestion_date(new java.sql.Date(millis));//saving the date form controller
			question.setStatus("Active"); // first time asked
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
		   
		   Question question = service.findById(id).get();
		   String loginRole =  ((ExamUserDetail)authentication.getPrincipal()).getAuthorities().toString();
		
		   model.addAttribute("question",question);
		   model.addAttribute("flag",Boolean.TRUE);
			if (("C").equals(question.getStatus()) && "[ROLE_USER]".equals(loginRole)){
				model.addAttribute("msg","Student can't edit closed question");
				model.addAttribute("flag",Boolean.FALSE);
			}
		   return "questionEdit";
	   }
	   
	   /**
	    * delete the record. It means we need to close the record for further discussion 
	    * but teacher can update this question to enhance the answer for all.
	    * @param id
	    * @return
	    */
	   
	   @GetMapping("/admin/question/closeIt")
	   public String closeIt(@RequestParam long id) {
		   
		   service.closeIt(id);
		   
		   return "redirect:/user/questionList/" ;
		   
	   }

}
