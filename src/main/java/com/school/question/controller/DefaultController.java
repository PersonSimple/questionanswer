package com.school.question.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.question.model.User;
import com.school.question.service.LoginService;

@Controller
public class DefaultController {
	
	@Autowired
	LoginService service;

    @GetMapping("/")
    public String login() {
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

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
    
    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }
    
    @GetMapping("/admin/userCreation")
    public String createUser( Model model) {
    	model.addAttribute("user", new User());
        return "loginPage";
    }
    
      
    /**
     * this method create user and password. username should be unique
     * @param user
     * @return
     */
    @PostMapping("/admin/userCreation")
    public String saveUser(@ModelAttribute User user) {
    	long millis=System.currentTimeMillis();  
    	user.setCreated_date(new java.sql.Date(millis));//saving the date form controller
		service.save(user);
        return "/home";
    }
   
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