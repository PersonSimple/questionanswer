package com.school.question.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.school.question.model.Answer;
import com.school.question.model.Question;
import com.school.question.model.User;
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
/*	@Override
	public List<Answer> answerList(String studentName,String teacherName) {
		return answerRepository.answerList(studentName,teacherName);
	}*/

	/**
	 * this method return the list of answer related with login user
	 */
	@Override
	public Page<Answer> answerPageList(int pageNumber) {
		Page<Answer> page =null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Pageable pageable = PageRequest.of(pageNumber-1, 5);
		page = answerRepository.answerPageList(authentication.getName(),authentication.getName(),pageable);
		return page;
		}
	
	
	/*@Override
	public Page<Answer> answerList(int pageNumber) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return answerRepository.answerList(authentication.getName(),authentication.getName());
	}*/

	
	
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

	@Override
	public List<Answer> searchAnswer(String answer) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return answerRepository.searchAnswer(authentication.getName(),authentication.getName(),answer);
	}
	
	
}
