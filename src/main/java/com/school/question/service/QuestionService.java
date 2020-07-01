package com.school.question.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.question.filterQuery.QuestionSpecification;
import com.school.question.filterQuery.SearchCriteria;
import com.school.question.model.Question;
import com.school.question.repository.QuestionRepository;
import com.school.question.repository.TutorialRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	public boolean save(Question question) {
		questionRepository.save(question);
		return true;
	}

	public List<Question> questionList(String userName) {
		//return questionRepository.findByUserName(userName);
		return questionRepository.findAll();
	}


	public Optional<Question> serachQuestion(long id) {
		return questionRepository.findById(id);
	}
	
	
	
public Question updateQuestion(Question question) {
		return questionRepository.save(question);
	}



public void deleteQuestion(long id) {
	 questionRepository.deleteById(id);  
}



}
