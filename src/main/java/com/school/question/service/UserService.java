package com.school.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.question.repository.AnswerRepository;
import com.school.question.repository.TutorialRepository;
import com.school.question.repository.UserRepository;

@Service
public class UserService {
	
	  @Autowired
	  UserRepository repository;
	

}
