package com.school.question.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.school.question.model.ExamUserDetail;
import com.school.question.model.User;
import com.school.question.repository.UserRepository;

@Service
public class ExamUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public ExamUserDetail loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findByUserName(userName.trim());
		user.orElseThrow(() -> new UsernameNotFoundException(" User Not Found !"));
		
		return user.map(ExamUserDetail::new).get();

	}
	
	


}
