package com.school.question.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.question.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByUserName(String userName);
	
	@Query("FROM User WHERE roles = ?1")
	public List<User> findByUserRoles(String role);
	
	
}
