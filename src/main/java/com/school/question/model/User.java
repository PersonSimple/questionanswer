package com.school.question.model;

import java.sql.Date;
import java.util.Arrays;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user")
public class User {

  @Id
  @Column(name = "user_id")
  @GeneratedValue (strategy=GenerationType.AUTO)
  private long id;

  @Column(name = "userName")
  private String userName;


  @Column(name = "password")
  private String password;

  @Column(name = "roles")
  private String roles;
  
  @Column(name = "created_date")
  private Date created_date;
  
 

public User() {
	super();
	// TODO Auto-generated constructor stub
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getRoles() {
	return roles;
}

public void setRoles(String roles) {
	this.roles = roles;
}

public Date getCreated_date() {
	return created_date;
}

public void setCreated_date(Date created_date) {
	this.created_date = created_date;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((created_date == null) ? 0 : created_date.hashCode());
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((roles == null) ? 0 : roles.hashCode());
	result = prime * result + ((userName == null) ? 0 : userName.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	User other = (User) obj;
	if (created_date == null) {
		if (other.created_date != null)
			return false;
	} else if (!created_date.equals(other.created_date))
		return false;
	if (id != other.id)
		return false;
	if (password == null) {
		if (other.password != null)
			return false;
	} else if (!password.equals(other.password))
		return false;
	if (roles == null) {
		if (other.roles != null)
			return false;
	} else if (!roles.equals(other.roles))
		return false;
	if (userName == null) {
		if (other.userName != null)
			return false;
	} else if (!userName.equals(other.userName))
		return false;
	return true;
}






 

 
  
}//end of class 
