package com.school.question.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.question.model.Answer;
import com.school.question.model.ITeacherReport;
import com.school.question.model.Question;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	
	public List<Answer> findAll();
	
	// if you are using student and teacher both then why are you not using findAll() method here
/*	@Query(" FROM Answer WHERE studentName = ?1 or teacherName=?2")
	public List<Answer> answerList(String studentName,String teacherName);*/
	
	
	@Query(" FROM Answer a WHERE a.studentName = ?1 or a.teacherName=?2 and a.answer LIKE %?3%")
	public List<Answer> searchAnswer(String studentName,String teacherName,String answer);

	
	
	@Query(" FROM Answer a WHERE a.studentName = ?1 or a.teacherName=?2")
	public Page<Answer> answerPageList(String studentName,String teacherName,Pageable page);
	
	
	public Long countByTeacherName(String teacherName);
	
/*	@Query("SELECT count(teacherName) FROM Answer WHERE  teacherName=?1 and MONTH(answer_date)= 7")
	public Long countCurrentMonthAns(String teacherName);
*/	
	
 
	@Query(value = "SELECT MONTH(answer_date) AS monthData, "
			+ "COUNT(answer_date) AS answerData, YEAR(answer_date) AS sessionYear "
			+ "from  (SELECT answer_date FROM answer WHERE teacher_name=?1) AS answer  "
			+ "GROUP BY MONTH(answer_date)",nativeQuery = true)
	public	List<ITeacherReport> getTeacherReport(String teacherName);
	
	
	@Query("FROM Answer WHERE question_id = ?1")
	public	List<Answer> findByQuestion_id(long question_id);
	}
