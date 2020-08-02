package com.school.question.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Question> questionList(Authentication authentication) {
		List<Question> questionList;
		String role = authentication.getAuthorities().toArray()[0].toString();
		String name = authentication.getName();
		//String subject =authentication.
		
		if(role.equals("ROLE_ADMIN")) {
			    User user = userRepository.findByUserName(name).get();
				questionList = questionRepository.findBySubject(user.getSubject());
			}
			else {
				if ( role.equals("ROLE_SUPER"))
					questionList =	questionRepository.findAll();
				else
					questionList = questionRepository.findByStudentName(name);
			}
		return questionList;
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
 * if question is closed it cannot opened again
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
