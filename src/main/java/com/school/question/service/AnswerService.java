package com.school.question.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.security.core.Authentication;

import com.school.question.model.Answer;
import com.school.question.repository.AnswerRepository;
import java.util.Optional;




public interface AnswerService {
	public List<Answer> findAll();
	/*
	 * public List<Answer> answerList(String teacherName, String studentName);
	*/
	
	public Optional<Answer> findById(long id);
	public Answer answerSave(Answer answer);
	public List<Answer> findByQuestion_id(long qid);

	public List<Answer> searchAnswer(String answer);

/**
 * for pagination and sorting
 * @param pageNumber
 * @return
 */
	public Page<Answer> answerPageList(int pageNumber);
}



