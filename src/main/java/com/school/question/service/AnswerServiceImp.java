package com.school.question.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.question.model.Answer;
import com.school.question.repository.AnswerRepository;



@Service
public class AnswerServiceImp implements  AnswerService {
	
	@Autowired
	AnswerRepository answerRepository;

	@Override
	public List<Answer> findAll() {
		return answerRepository.findAll();
	}

	/**
	 * this method return the list answer related with login user
	 */
	@Override
	public List<Answer> answerList(String studentName,String teacherName) {
		return answerRepository.answerList(studentName,teacherName);
	}

	
	

	
	@Override
	public Answer answerSave(Answer answer) {
		return answerRepository.save(answer);
	}
	
	
	@Override
	public Optional<Answer> findById(long id) {
		return answerRepository.findById(id);
	}

	@Override
	public List<Answer> findByQuestion_id(long qid) {
		
		return answerRepository.findByQuestion_id(qid);
	}
	
	
}
