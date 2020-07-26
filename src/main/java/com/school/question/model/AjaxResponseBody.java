package com.school.question.model;

import java.util.List;

import com.school.question.payload.TeacherResponseAjax;

public class AjaxResponseBody {

    String msg;
    TeacherResponseAjax result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

	public TeacherResponseAjax getResult() {
		return result;
	}

	public void setResult(TeacherResponseAjax result) {
		this.result = result;
	}

}
