package com.school.question.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.question.model.Answer;
import com.school.question.model.ITeacherReport;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	
	public List<Answer> findAll();
	
	@Query("FROM Answer WHERE studentName = ?1 or teacherName=?2")
	public List<Answer> answerList(String studentName,String teacherName);
	
	
	public Long countByTeacherName(String teacherName);
	
	@Query("SELECT count(teacherName) FROM Answer WHERE  teacherName=?1 and MONTH(answer_date)= 7")
	public Long countCurrentMonthAns(String teacherName);
	
	
 
	@Query(value = "SELECT MONTH(answer_date) AS monthData, "
			+ "COUNT(answer_date) AS answerData, YEAR(answer_date) AS sessionYear "
			+ "from  (SELECT answer_date FROM answer WHERE teacher_name=?1) AS answer  "
			+ "GROUP BY MONTH(answer_date)",nativeQuery = true)
	public	List<ITeacherReport> getTeacherReport(String teacherName);
	
	/*@Query("SELECT a.year AS monthData, COUNT(a.year) AS answerData "
			  + "FROM Answer AS a GROUP BY a.year ORDER BY a.year DESC")
		public	List<ITeacherReport> teacherReport(String teacherName);
	*/
	
	
	
	
	

}
