package com.school.question.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.school.question.model.Answer;
import com.school.question.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByUserName(String userName);
}
