package com.school.question.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.question.model.Answer;
import com.school.question.model.Question;
import com.school.question.model.User;
import com.school.question.repository.AnswerRepository;
import com.school.question.repository.QuestionRepository;
import com.school.question.repository.TutorialRepository;
import com.school.question.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	  @Autowired
	  UserRepository teacherRepo;

	  @Autowired
	  QuestionRepository questionRepo;
	  
	  @Autowired
	  AnswerRepository answerRepo;
	  
	  

	/*@Override
	public List<User> getTeacherList() {
		return teacherRepo.findAll();
	}

	@Override
	public List<Question> getQuestionList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Answer> getAnswerList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getStudentList() {
		// TODO Auto-generated method stub
		return null;
	}*/
	

}
