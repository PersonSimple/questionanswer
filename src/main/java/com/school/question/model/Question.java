package com.school.question.model;

//import java.sql.Date;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "question")
public class Question {

  @Id
  @Column(name = "question_id")
  @GeneratedValue (strategy=GenerationType.AUTO)
  private long id;

  @Column(name = "question")
  private String question;


  @Column(name = "subject")
  private String subject;
 
  
  @Basic(optional = false)
  @Column(name = "question_date",  updatable = false)
  //@Temporal(TemporalType.TIMESTAMP)
  private Date question_date;
  
  
  @Column(name="userName")
  private String userName;

public Question() {
	super();
	
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getQuestion() {
	return question;
}

public void setQuestion(String question) {
	this.question = question;
}

public Date getQuestion_date() {
	return question_date;
}

public void setQuestion_date(Date question_date) {
	this.question_date = question_date;
}



public String getSubject() {
	return subject;
}

public void setSubject(String subject) {
	this.subject = subject;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + ((question == null) ? 0 : question.hashCode());
	result = prime * result + ((question_date == null) ? 0 : question_date.hashCode());
	result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
	Question other = (Question) obj;
	if (id != other.id)
		return false;
	if (question == null) {
		if (other.question != null)
			return false;
	} else if (!question.equals(other.question))
		return false;
	if (question_date == null) {
		if (other.question_date != null)
			return false;
	} else if (!question_date.equals(other.question_date))
		return false;
	if (subject == null) {
		if (other.subject != null)
			return false;
	} else if (!subject.equals(other.subject))
		return false;
	if (userName == null) {
		if (other.userName != null)
			return false;
	} else if (!userName.equals(other.userName))
		return false;
	return true;
}

@Override
public String toString() {
	return "Question [id=" + id + ", question=" + question + ", subject=" + subject + ", question_date=" + question_date
			+ ", userName=" + userName + "]";
}



  
 

 
  
}
