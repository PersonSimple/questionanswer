package com.school.question.repository;
//import com.example.filedemo.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.question.model.DBFile;

//import com.bezkoder.spring.files.excel.model.DBFile;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, Long> {

}