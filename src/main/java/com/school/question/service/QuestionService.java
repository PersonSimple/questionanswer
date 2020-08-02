package com.school.question.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.school.question.filterQuery.QuestionSpecification;
import com.school.question.filterQuery.SearchCriteria;
import com.school.question.model.Question;
import com.school.question.repository.QuestionRepository;
import com.school.question.repository.TutorialRepository;

@Service
public interface QuestionService {

	public boolean save(Question question);

	public List<Question> questionList(Authentication authentication);

	public Question updateQuestion(Question question);

	public void deleteQuestion(long id);

	public Optional<Question> findById(long id);
	
	public Question closeIt(@RequestParam long id);
	
	
}
