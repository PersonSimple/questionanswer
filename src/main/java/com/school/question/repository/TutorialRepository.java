package com.school.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.question.model.Tutorial;

//import com.bezkoder.spring.files.excel.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
