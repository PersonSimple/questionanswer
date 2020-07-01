package com.school.question.model;
import javax.persistence.*;


@Entity
@Table(name = "files")
public class DBFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="fileName")
    private String fileName;

    @Column(name="fileType")
    private String fileType;
    
    @Column(name="question_id")
    private long question_id;

    @Lob
    private byte[] data;

    public DBFile() {

    }

 
	public DBFile( String fileName, String fileType, long question_id, byte[] data) {
		super();
		
		this.fileName = fileName;
		this.fileType = fileType;
		this.question_id = question_id;
		this.data = data;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

   
}