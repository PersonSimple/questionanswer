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
@Table(name = "answer")
public class Answer {

  
  
  @Id
  @Column(name = "answer_id")
  @GeneratedValue (strategy=GenerationType.AUTO)
  private long id;

  @Column(name = "answer")
  private String answer;


  @Column(name="fileName")
  private String fileName;

  @Column(name="fileType")
  private String fileType;
  
  @Lob
  private byte[] data;

  @Column(name = "answer_date")
  private Date answer_date;
  
  @Column(name="studentName")
  private String studentName;
  
  @Column(name="teacherName")
  private String teacherName;
  
  @Column(name="question_id")
  private long question_id;

  public String getFileName() {
	return fileName;
}

public void setFileName(String fileName) {
	this.fileName = fileName;
}

public String getFileType() {
	return fileType;
}

public void setFileType(String fileType) {
	this.fileType = fileType;
}

public Answer() {
	super();
	// TODO Auto-generated constructor stub
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getAnswer() {
	return answer;
}

public void setAnswer(String answer) {
	this.answer = answer;
}

public byte[] getData() {
	return data;
}

public void setData(byte[] data) {
	this.data = data;
}

public Date getAnswer_date() {
	return answer_date;
}

public void setAnswer_date(Date answer_date) {
	this.answer_date = answer_date;
}

public long getQuestion_id() {
	return question_id;
}

public void setQuestion_id(long question_id) {
	this.question_id = question_id;
}

public String getStudentName() {
	return studentName;
}


public void setStudentName(String studentName) {
	this.studentName = studentName;
}


public String getTeacherName() {
	return teacherName;
}
public void setTeacherName(String teacherName) {
	this.teacherName = teacherName;
}







@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((answer == null) ? 0 : answer.hashCode());
	result = prime * result + ((answer_date == null) ? 0 : answer_date.hashCode());
	result = prime * result + (int) (id ^ (id >>> 32));
	result = prime * result + (int) (question_id ^ (question_id >>> 32));
	result = prime * result + ((studentName == null) ? 0 : studentName.hashCode());
	result = prime * result + ((teacherName == null) ? 0 : teacherName.hashCode());
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
	Answer other = (Answer) obj;
	if (answer == null) {
		if (other.answer != null)
			return false;
	} else if (!answer.equals(other.answer))
		return false;
	if (answer_date == null) {
		if (other.answer_date != null)
			return false;
	} else if (!answer_date.equals(other.answer_date))
		return false;
	if (id != other.id)
		return false;
	if (question_id != other.question_id)
		return false;
	if (studentName == null) {
		if (other.studentName != null)
			return false;
	} else if (!studentName.equals(other.studentName))
		return false;
	if (teacherName == null) {
		if (other.teacherName != null)
			return false;
	} else if (!teacherName.equals(other.teacherName))
		return false;
	return true;
}







  
}//end of class 
