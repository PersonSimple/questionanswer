package com.school.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.school.question.model.Answer;
import com.school.question.model.User;

public interface LoginRepository extends JpaRepository<User, Long> {
	
   // public User update(User user);
}
