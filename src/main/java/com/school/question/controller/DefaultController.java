package com.school.question.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
/*
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
*/

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.question.model.LoggedUser;
import com.school.question.model.User;
import com.school.question.service.AnswerServiceImp;
import com.school.question.service.LoginService;

@Controller
public class DefaultController {
	
	@Autowired
	LoginService service;

    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }
    
    
    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }



	/*
	 * @GetMapping("/questionList") public String questionList() { return
	 * "/questionList"; }
	 */

	/*
	 * @GetMapping("/question") public String questionPage(Model model) {
	 * 
	 * LoggedUser loggedUser = new LoggedUser(); Authentication authentication =
	 * SecurityContextHolder.getContext().getAuthentication();
	 * 
	 * loggedUser.setUserName(authentication.getName()); // loggedUser.setRoles(
	 * authentication.getAuthorities());
	 * 
	 * model.addAttribute("loggedUser", loggedUser);
	 * 
	 * return "/question"; }
	 */

	/*
	 * @GetMapping("/login") public String login() { return "/login"; }
	 * 
	 */
    
	/*
	 * @PostMapping("/login") public String loginRequest(Model model) {
	 * 
	 * System.out.println (model.getAttribute("user").toString());
	 * 
	 * 
	 * 
	 * 
	 * return "/login"; }
	 */
    
    @RequestMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/error")
    public String error() {
        return "/error/403";
    }
    
    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}