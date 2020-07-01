package com.school.question.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.question.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	
	public List<Answer> findAll();
	
	@Query("FROM Answer WHERE studentName = ?1 or teacherName=?2")
	public List<Answer> answerList(String studentName,String teacherName);
}
