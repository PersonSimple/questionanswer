package com.school.question.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.question.model.User;
import com.school.question.repository.UserRepository;

@Service
public class LoginService {
	
	  @Autowired
	  private PasswordEncoder encoder;
	
	  @Autowired
	  private UserRepository userRepository;
	  
	  public boolean validateUser(){
		  return true;
	  }
	  
	  public User save(User user){
		user =   userRepository.save(user);
		user = setUserLogin(user); //generate the specific login id [first_last_id@gmail.com]
		user.setPassword(encoder.encode(user.getPassword()));  //secure bcrypt hashing with out salt
		user = userRepository.save(user);  // Hoping this it will update the record lognId and encrypt password
		return user;
		  
	  }

	  
	
	  public User setUserLogin(User user) {
	  	StringBuffer str= new StringBuffer();
	  	user.setUserName( str.append(user.getFirstName())
	  		.append("_")
	  		.append(user.getLastName())
	  		.append("_")
	  		.append(user.getId())
	  		.append("@gmail.com").toString());
	     return user;
	  }

	  
	  public void changePassword(String password, Long id){
		  password = encoder.encode(password);// BCryptPasswordEncoder
		  userRepository.changePassword( password,  id);
		  
	  }
	  
	  public Optional<User> searchByLoginId(String userName){
		return userRepository.findByUserName( userName);
		 
	  }
}
