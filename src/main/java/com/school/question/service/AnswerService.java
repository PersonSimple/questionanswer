package com.school.question.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.question.model.Answer;
import com.school.question.repository.AnswerRepository;




public interface AnswerService {
	public List<Answer> findAll();
	public List<Answer> answerList(String teacherName, String studentName);
	
	public Answer answerSave(Answer answer);

}

