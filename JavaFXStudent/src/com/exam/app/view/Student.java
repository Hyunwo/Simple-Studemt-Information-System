package com.exam.app.view;

//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.StringProperty;

public class Student {

	private String stdID; //�й�
	private String stdName; //�̸�
	private String stdMajor; //����
	private Integer stdAge; //����
	private String stdStatus; //���� ����

	
	public Student(String stdID, String stdName, String stdMajor, Integer stdAge, String stdStatus) {
		this.stdID = stdID;
	    this.stdName = stdName;
	    this.stdMajor = stdMajor;
	    this.stdAge = stdAge;
	    this.stdStatus = stdStatus;
	}
	
	public String getStdID() {
		return stdID;
	}

	public void setStdID(String stdID) {
		this.stdID = stdID;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public String getStdMajor() {
		return stdMajor;
	}

	public void setStdMajor(String stdMajor) {
		this.stdMajor = stdMajor;
	}

	public Integer getStdAge() {
		return stdAge;
	}

	public void setStdAge(Integer stdAge) {
		this.stdAge = stdAge;
	}

	public String getStdStatus() {
		return stdStatus;
	}

	public void setStdStatus(String stdStatus) {
		this.stdStatus = stdStatus;
	}
	
}
