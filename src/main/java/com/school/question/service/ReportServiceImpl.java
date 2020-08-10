package com.school.question.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	BCryptPasswordEncoder encoder;
	

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
	
	@Override
	public List<ITeacherReport> getTeacherReport(String userName) {
		return answerRepository.getTeacherReport(userName);
	}

	@Override
	public Optional<User> findByUserName(String teacherName) {
		return userRepository.findByUserName(teacherName);
	}
	
	/**
	 * enable or disable to user access unique userName required to search 
	 * then disable. It return a string value enable/disable toggle according
	 * to current value 0 or 1 
	 */

	@Override
    public User	toggleUserAccss(User user) {
    	
    	if("1".equals(user.getStatus())) {
    		user.setStatus("0");
    	}
    	else {
    		user.setStatus("1");
    	}
    	//user.setPassword(encoder.encode(user.getPassword()));
    	return userRepository.save(user);
    }

	
	
	/*@Override
    public User	changePassword(User user) {
    	user.setPassword(encoder.encode(user.getPassword()));
    	return userRepository.save(user);
    }*/
	
	
	/**
	 * this method get the teacher information. Call the userRepository's toggleUserAccess(teacherName).
	 * Collect User Object and return to controller
	 * 
	 */
	@Override
	public Optional<User> getTeacherInfo(String teacherName) {
		return userRepository.toggleUserAccss(teacherName);
	}

	/**
	 * get record for performance active/inactive both
	 */
	@Override
	public Optional<User> getTeacherRecord(String teacherName) {
		return userRepository.getTeacherRecord(teacherName);
	}
}
