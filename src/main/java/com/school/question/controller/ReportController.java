package com.school.question.controller;

import java.util.List;
import java.util.Optional;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*import com.javabycode.model.AjaxResponseBody;
import com.javabycode.model.LoginForm;*/
import com.school.question.model.Answer;
import com.school.question.model.ITeacherReport;
import com.school.question.model.User;
import com.school.question.service.ReportServiceImpl;

@Controller
public class ReportController {

	@Autowired
	private ReportServiceImpl  reportService;
	   
	   @GetMapping("/super/admin/report")
	    public String getReportHome() {
		   return "report/reportHome";
	    }  

	   
   @GetMapping("/super/admin/teacherList")
	    public String getTeacherList(@RequestParam long id,Model model) {
	    List<User>  userList=  reportService.getTeacherList("ROLE_ADMIN");
	    model.addAttribute("userList", userList);
	    
	    //System.out.println(userList.toString());
		return "report/teacherList";
	    }  
   
   @GetMapping("/super/admin/studentList")
	    public String getStudentList(Model model) {
	        List<User>  userList = reportService.getStudentList("ROLE_USER");
		    model.addAttribute("userList", userList);
		    return "report/studentList";
	    }  
 
	@GetMapping("/super/admin/answerList")
	    public String getAnswerList(Model model) {
	       List<Answer> ansList = reportService.findAllAnswer();
	       model.addAttribute("ansList", ansList);
		   return "report/reportHome";
	    }  

	
	
	@GetMapping("/super/admin/performance")
    public String getTotalAndMonthCountAjax(@RequestParam String teacherName,Model model) {
        Optional<User>  teacherInfo = reportService.findByUserName(teacherName);
        List<ITeacherReport> totalAnser = reportService.getTeacherReport(teacherName);
        model.addAttribute("totalAnswer",totalAnser);
        model.addAttribute("teacherInfo",teacherInfo.get());
        return "report/teacherReport";
    }
	
	
	/**
	    * it will save a new record in answer table for same question_id
	    * @param model
	    * @param aid
	    * @param qid
	    * @return
	    */
	
	 /*
	   @GetMapping("/super/answer/discuss")
	    public String answerDiscuss(Model model,@RequestParam long aid,@RequestParam long qid) {
		   Optional<Answer> answer =  answerService.findById(aid);
		   answer.get().setId(0);
		   model.addAttribute("answer",answer.get());
		   return "answer/answerDiscuss";
	    }  
	
	   
*//**
 * this will save a new record in answer table
 * @param model
 * @param aid
 * @param qid
 * @return
 *//*
	   @PostMapping("/super/student/discuss")
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

	   @GetMapping("/super/answer/edit")
	    public String answerEdit(Model model,@RequestParam long aid,@RequestParam long qid) {
		   Optional<Answer> answer =  answerService.findById(aid);
		   Optional<Question> question = questionService.findById(qid);
		   AnswerDto dto = new AnswerDto();
		   dto.setAnswer(answer.get());
		   dto.setQuestion(question.get());
		   
		   
		   model.addAttribute("question",question.get());
		   model.addAttribute("answer",answer.get());
		  
		   
		   return "answer/answer";
	    }  
	   
	   *//**
	    *  answerList method get method for current user he may be teacher or student
	    *  this list the answer for the user need to provide the user id . 
	    *  filter record on the basis of user id will list in this view  
	    * @param model
	    * @return
	    *//*

	   @GetMapping("/super/answerList")
	   public String answerList(Model model) {
		   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		   List<Answer> answerList= answerService.answerList(authentication.getName(),authentication.getName());
		   model.addAttribute("answerList",answerList);
	       return "answer/answerList";
	    }
	   
	   
	   *//**
	    * this method for answer will populate the question_id form question page 
	    * and userName from current login account
	    * template is under the answer folder
	    * 
	    * @param id
	    * @param model
	    * @return
	    *//*
	   
	   @GetMapping("/super/admin/answer")
	   public String answer(@RequestParam long id, @RequestParam String studentName, Model model) {
		   
		    Answer answer = new Answer();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			answer.setTeacherName(authentication.getName());
			answer.setQuestion_id(id);
			answer.setStudentName(studentName); // this value can be fetched from database using the question id
	    	model.addAttribute("answer",answer);
	        return "answer/answer";
	        
	    }
	   

	   *//**
	    * collecting information in answer object and passing to service answerSave method
	    * this method return the view answer of the answer module. only admin will save
	    * @param answer
	    * @return
	    */
	 /*
	   @PostMapping("/super/admin/answer")
	   public String answerSave(@ModelAttribute Answer answer,@RequestParam("file") MultipartFile file) {
		   try {
			   answer.setFileName(file.getOriginalFilename());
			   answer.setFileType(file.getContentType());
			answer.setData(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   answerService.answerSave(answer);
	       return "answer/answer";
	        
	    }
	   
	   
	    @GetMapping("/super/{fileName}")
	    public ResponseEntity getImage(@PathVariable long fileName) {
	   	Answer document = answerService.findById(fileName).get();
	   	return ResponseEntity.ok()
	   			.contentType(MediaType.parseMediaType("application/octet-stream"))
	   			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
	   			.body(document.getData());
	   }
*/

}
