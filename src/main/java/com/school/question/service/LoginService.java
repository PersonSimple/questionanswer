package com.school.question.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.school.question.model.User;
import com.school.question.repository.AnswerRepository;
import com.school.question.repository.DBFileRepository;
import com.school.question.repository.LoginRepository;
import com.school.question.repository.TutorialRepository;

@Service
public class LoginService {
	
	  @Autowired
	  private LoginRepository repository;
	  
	  public boolean validateUser(){
		  return true;
	  }
	  
	  public User save(User user){
		user =   repository.save(user);
		user = setUserLogin(user);
		user = repository.save(user);  // Hoping this it will update the record.
		return user;
		  
	  }

	
	  public User setUserLogin(User user) {
	  	StringBuffer str= new StringBuffer();
	  	user.setUserName( str.append(user.getFirstName())
	  		.append("_")
	  		.append(user.getLastName())
	  		.append("_")
	  		.append(user.getId())
	  		.append("@gmail.com").toString());
	     return user;
	  }

}
