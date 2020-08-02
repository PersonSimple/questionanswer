package com.school.question.model;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ExamUserDetail implements UserDetails {
	
	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;;
	private Date created_date;
	private String subject;
	private String standard;

	public ExamUserDetail() {
		super();
		
	}
	
	public ExamUserDetail(User user) {
		this.userName = user.getUserName();
		this.password=user.getPassword();
		this.subject =user.getSubject();
		this.standard= user.getStandard();
		this.authorities = Arrays.stream(user.getRoles().split(","))
				                 .map(SimpleGrantedAuthority::new )
				                 .collect(Collectors.toList());
				                 
		this.created_date=user.getCreated_date();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}
	
	

	public String getSubject() {
		return subject;
	}

	public String getStandard() {
		return standard;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "ExamUserDetail [userName=" + userName + ", password=" + password + ", authorities=" + authorities
				+ ", created_date=" + created_date + ", subject=" + subject + ", standard=" + standard + "]";
	}

}
