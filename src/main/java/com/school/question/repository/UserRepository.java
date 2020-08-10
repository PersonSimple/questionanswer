package com.school.question.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.school.question.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * only active use can login where status =1
	 * @param userName
	 * @return
	 */
	
	
	@Query(" FROM User u WHERE u.userName =?1 and u.status = 1")
	public Optional<User> findByUserName(String userName);
	
	/**
	 *  this method for summary of active/inactive teacher
	 * @param userName
	 * @return
	 */
	@Query(" FROM User u WHERE u.userName =?1")
	public Optional<User> getTeacherRecord(String userName);
	
	
	@Query(" FROM User u WHERE u.roles = ?1")
	public List<User> findByUserRoles(String role);
	
	@Query(" FROM User u WHERE u.userName =?1")
	public Optional<User> toggleUserAccss(String userName);
	
	@Modifying
	@Transactional
	@Query(" UPDATE User u SET u.password = ?1 WHERE u.id = ?2")
	public int changePassword(String password, long id);
	//public Optional<User> changePassword(@Param("password") String password,@Param("id") long id);
}
