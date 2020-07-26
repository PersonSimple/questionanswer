package com.school.question.payload;
public class TeacherResponseAjax {
  
    private long totalCount;
    private long monthCount;
    
    
    
	

	public long getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}


	public long getMonthCount() {
		return monthCount;
	}


	public void setMonthCount(long monthCount) {
		this.monthCount = monthCount;
	}


	


	public TeacherResponseAjax() {
		super();
	}


	public TeacherResponseAjax( long totalCount, long monthCount) {
		super();
		this.totalCount = totalCount;
		this.monthCount = monthCount;
		
	}
    

  
}