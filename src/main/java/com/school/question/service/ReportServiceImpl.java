package com.school.question.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.question.model.Answer;
import com.school.question.model.ITeacherReport;
import com.school.question.model.User;
import com.school.question.repository.AnswerRepository;
import com.school.question.repository.UserRepository;



@Service
public class ReportServiceImpl implements  ReportService {
	
	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	UserRepository userRepository;
	

	@Override
	public List<Answer> findAllAnswer() {
		return answerRepository.findAll();
	}

	/**
	 * this method return the list answer related with login user
	 */

	
	@Override
	public Optional<Answer> findSingleAnswerById(long id) {
		return answerRepository.findById(id);
	}

	@Override
	public List<User> getTeacherList(String roleAdmin) {
		return userRepository.findByUserRoles(roleAdmin);
	}

	@Override
	public List<User> getStudentList(String roleUser) {
		return userRepository.findByUserRoles(roleUser);
	}

	@Override
	public Long getTotalAnswer(String userName) {
		 return answerRepository.countByTeacherName(userName);
	}
	
	// remove this method
	public Long countCurrentMonthAns(String userName) {
		return answerRepository.countCurrentMonthAns(userName);
		}

	@Override
	public List<ITeacherReport> getTeacherReport(String userName) {
		return answerRepository.getTeacherReport(userName);
	}

	@Override
	public Optional<User> findByUserName(String teacherName) {
		return userRepository.findByUserName(teacherName);
	}

}
