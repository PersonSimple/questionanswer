package com.school.question.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.school.question.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * only active use can login where status =1
	 * @param userName
	 * @return
	 */
	@Query("FROM User u WHERE u.userName =?1 and u.status = 1")
	public Optional<User> findByUserName(String userName);
	
	
	
	/**
	 *  this method for summary of active/inactive teacher
	 * @param userName
	 * @return
	 */
	@Query("FROM User u WHERE u.userName =?1")
	public Optional<User> getTeacherRecord(String userName);
	
	
	@Query("FROM User u WHERE u.roles = ?1")
	public List<User> findByUserRoles(String role);
	
	@Query("FROM User u WHERE u.userName =?1")
	public Optional<User> toggleUserAccss(String userName);
	
	
	
}
