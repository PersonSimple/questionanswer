package com.school.question.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;

import com.school.question.model.Question;


public interface QuestionRepository extends JpaRepository<Question, Long> 
//public interface QuestionRepository extends PagingAndSortingRepository <Question, Long>
{
	
	/*@Query("FROM Question WHERE studentName = ?1")
	List<Question> findByUserName(String userName);
	*/
	//List<Question> findByStudentName(String userName);
	
	//List<Question> findBySubject(String userName);
	
/*	@Query(value = " FROM Question q WHERE q.question LIKE (%question%)")
	List<Question> findByQuestion(String question);
*/	
	
	@Query(value = " FROM Question q WHERE q.subject=?1")
	Page<Question> findBySubject(String subject,Pageable page);
	
	@Query(value = " FROM Question q WHERE q.studentName=?1")
	Page<Question> findByStudentName(String studentName,Pageable page);
	
	
	/**
	 * Search Queries for question list page subject and like question
	 * @param subject
	 * @param question
	 * @return
	 */
	
	@Query (value = " FROM Question q WHERE q.subject=?1 AND q.question LIKE %?2%")
	List<Question> findBySubject(String subject,String question);
	/**
	 * Search query on subject and like question parameter
	 * @param subject
	 * @param question
	 * @return
	 */
	
	@Query (value = " FROM Question q WHERE q.question LIKE %?1%")
	List<Question>	findAll(String question);
	
	/**
	 * Search query for student name and like question
	 * @param studentName
	 * @param question
	 * @return
	 */
	@Query (value = " FROM Question q WHERE q.studentName=?1 AND q.question LIKE %?2%")
	List<Question> findByStudentName(String studentName,String question);
	
	
	/*@Query("FROM Question WHERE userName = ?1")
	List<Question> findByUserAndSubject(String userName,String subject);
	*/
	

}
