package com.school.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.school.question.repository.AnswerRepository;
import com.school.question.repository.DBFileRepository;
import com.school.question.repository.LoginRepository;
import com.school.question.repository.TutorialRepository;

@Service
public class LoginService {
	
	  @Autowired
	  private LoginRepository repository;
	  
	  
	/*
	 * @Query( value = "SELECT * FROM User ORDER BY id", countQuery =
	 * "SELECT count(*) FROM User", nativeQuery = true)
	 */
	  public boolean validateUser(){
		  return true;
	  }
	  
	  

	

}
