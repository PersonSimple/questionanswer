package com.school.question.dto;

import java.sql.Date;

import com.school.question.model.Answer;
import com.school.question.model.Question;


public class AnswerDto {

  private Answer answer;
  private Question question;
public Answer getAnswer() {
	return answer;
}
public void setAnswer(Answer answer) {
	this.answer = answer;
}
public Question getQuestion() {
	return question;
}
public void setQuestion(Question question) {
	this.question = question;
}
  
  
}
