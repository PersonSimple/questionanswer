package com.school.question.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.school.question.filterQuery.QuestionSpecification;
import com.school.question.filterQuery.SearchCriteria;
import com.school.question.model.Question;
import com.school.question.model.User;
import com.school.question.repository.QuestionRepository;
import com.school.question.repository.TutorialRepository;
import com.school.question.repository.UserRepository;

@Service
public class QuestionServiceImpl {

	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public boolean save(Question question) {
		questionRepository.save(question);
		return true;
	}

	/*public List<Question> questionList(String userName) {
		return questionRepository.findByUserName(userName);
		//return questionRepository.findAll(); //old method which get all the result
		//return questionRepository.findByUserAndSubject(userName, subject);
	}*/
	
	public List<Question> questionList(Authentication authentication) {
		List<Question> questionList;
		String role = authentication.getAuthorities().toArray()[0].toString();
		String name = authentication.getName();
		//String subject =authentication.
		
		if(role.equals("ROLE_ADMIN")) {
			    User user = userRepository.findByUserName(name).get();
				questionList = questionRepository.findBySubject(user.getSubject());
			}
			else {
				if ( role.equals("ROLE_SUPER"))
					questionList =	questionRepository.findAll();
				else
					questionList = questionRepository.findByStudentName(name);
			}
		
		return questionList;
		
		
		//return questionRepository.findAll(); //old method which get all the result
		//return questionRepository.findByUserAndSubject(userName, subject);
	}

	

	public Optional<Question> findById(long id) {
		return questionRepository.findById(id);
	}
	
	
	
public Question updateQuestion(Question question) {
		return questionRepository.save(question);
	}



public void deleteQuestion(long id) {
	 questionRepository.deleteById(id);  
}



}
