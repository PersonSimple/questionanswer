package com.school.question.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.school.question.filterQuery.QuestionSpecification;
import com.school.question.filterQuery.SearchCriteria;
import com.school.question.model.Question;
import com.school.question.model.User;
import com.school.question.repository.QuestionRepository;
import com.school.question.repository.TutorialRepository;
import com.school.question.repository.UserRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public boolean save(Question question) {
		questionRepository.save(question);
		return true;
	}


	@Override
	public List<Question> searchQuestion(Authentication authentication,String question) {
		List<Question> questionList =null;
		String role = authentication.getAuthorities().toArray()[0].toString();
		String studentName = authentication.getName();//logged in user id
		
		if(("ROLE_ADMIN".equals(role))) {
			    User user = userRepository.findByUserName(studentName).get();
			    String subject = user.getSubject();
				questionList = questionRepository.findBySubject(subject,question);
			}
			else {
				if (("ROLE_SUPER").equals(role))
					questionList =	questionRepository.findAll(question);
				else
					questionList = questionRepository.findByStudentName(studentName,question);
			}
		return questionList;
		}

	
	@Override
	public Page<Question> questionList(Authentication authentication,int pageNumber) {
		Page<Question> page =null;
		String role = authentication.getAuthorities().toArray()[0].toString();
		String student_Name = authentication.getName();//logged in user id
		
		Pageable pageable = PageRequest.of(pageNumber-1, 5);
		
		if(("ROLE_ADMIN".equals(role))) {
			    User user = userRepository.findByUserName(student_Name).get();
			    String subject = user.getSubject();
				//questionList = questionRepository.findBySubject(user.getSubject());
			    page =	questionRepository.findBySubject(subject,pageable);
			}
			else {
				if (("ROLE_SUPER").equals(role))
					//questionList =	questionRepository.findAll();
					 page =	questionRepository.findAll(pageable);
				else
					//questionList = questionRepository.findByStudentName(name);
					page = questionRepository.findByStudentName(student_Name,pageable);
			}
		//return questionList;
		return page;
		}

	@Override
	public Optional<Question> findById(long id) {
		return questionRepository.findById(id);
	}

	@Override
	public Question updateQuestion(Question question) {
		return questionRepository.save(question);
	}
	@Override
	public void deleteQuestion(long id) {
	 questionRepository.deleteById(id);  
}
/**
 * if question is closed it is fixed status can't change further
 */
	@Override
	public Question closeIt(long id) {
		//fetch the question and update the status
		Question quest = questionRepository.findById(id).get();
		if (quest.getStatus().equals("Active")
				|| quest.getStatus().equals("Pending")
				|| quest.getStatus().equals("Answered")
				){
			quest.setStatus("Closed");
			quest =questionRepository.save(quest);
		}
		
		return quest;
		
	}
	
	/**
	 *  this method return question with updated status.
	 * @param id
	 * @param status
	 * @return
	 */
	public Question modifyStatus(long id,String status)
	{
		Question quest = questionRepository.findById(id).get();
		quest.setStatus(status);
		return questionRepository.save(quest);
	}
}
