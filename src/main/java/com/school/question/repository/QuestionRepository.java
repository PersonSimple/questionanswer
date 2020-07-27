package com.school.question.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.question.model.Question;
;



public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	/*@Query("FROM Question WHERE studentName = ?1")
	List<Question> findByUserName(String userName);
	*/
	List<Question> findByStudentName(String userName);
	
	List<Question> findBySubject(String userName);
	
	/*@Query("FROM Question WHERE userName = ?1")
	List<Question> findByUserAndSubject(String userName,String subject);
	*/
}
