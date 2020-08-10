package com.school.question.service;

import java.util.HashMap;
import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.school.question.model.Answer;
import com.school.question.model.ITeacherReport;
//import com.school.question.model.LoginForm;
import com.school.question.model.User;
//import com.school.question.payload.TeacherResponseAjax;
//import com.school.question.repository.AnswerRepository;
import java.util.Optional;

public interface ReportService {
	public List<Answer> findAllAnswer();
	//public List<Answer> answerList(String teacherName, String studentName);
	public Optional<Answer> findSingleAnswerById(long id);
	public List<User>  getTeacherList(String roleAdmin);
	public List<User>  getStudentList(String roleUser);
	public Long getTotalAnswer(String userName); //
	
	public List<ITeacherReport> getTeacherReport(String userName);
	public Optional<User> getTeacherInfo(String teacherName);
	public Optional<User> findByUserName(String userName);
	
	public Optional<User>getTeacherRecord(String teacherName);
//
// public List<Question>  getQuestionList();
// public List<Answer>  getAnswerList();
//
	/**
	 * this method is use to toggle the user status. it will change the status value 
	 * form 0 to 1 or from 1 to 0
	 * @param user
	 * @return
	 */
	public User	toggleUserAccss(User user);
	
}

