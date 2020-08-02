package com.school.question.model;

import java.sql.Date;
import java.util.Arrays;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "user_profile")
public class User {

  @Id
  @Column(name = "user_id")
  @GeneratedValue (strategy=GenerationType.AUTO)
  private long id;

  @Column(name = "firstName",length=20)
  private String firstName;


  @Column(name = "lastName",length=20)
  private String lastName;
  
//This id must be generated with the combination of firstname_lastname@generatedId.com
  @Column(name = "userName",length=40)
  private String userName;


  @Column(name = "password",length=12)
  private String password;

  @Column(name = "roles",length=10)
  private String roles;
  
  @Column(name = "created_date")
  private Date created_date;
  
  @Column(name = "subject" ,length=3)
  private String subject;
  
  //this is for class or grade
  @Column(name = "standard",length=2)
  private String standard;
  
  //status will be active= true,1 of false,0
  //@Column(name = "status",length=1, default "1")
  @Column(columnDefinition = "varchar(1) default '1'")
  private String status; 
 

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

public String getSubject() {
	return subject;
}

public void setSubject(String subject) {
	this.subject = subject;
}

public String getStandard() {
	return standard;
}

public void setStandard(String standard) {
	this.standard = standard;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}


public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

}//end of class 
