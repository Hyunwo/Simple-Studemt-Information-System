package com.exam.app.view;

public class Student {

	private String stdID; // �й�
	private String stdName; // �̸�
	private String stdMajor; // ����
	private String stdGender; // ����
	private Integer stdAge; // ����
	private String stdAddress; // �ּ�
	private String stdStatus; // ���� ����

	
	public Student(String stdID, String stdName, String stdMajor, String stdGender, Integer stdAge, String stdAddress, String stdStatus) {
		this.stdID = stdID;
	    this.stdName = stdName;
	    this.stdMajor = stdMajor;
	    this.stdGender =stdGender;
	    this.stdAge = stdAge;
	    this.stdAddress = stdAddress;
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

	public String getStdGender() {
		return stdGender;
	}

	public void setStdGender(String stdGender) {
		this.stdGender = stdGender;
	}
	
	public Integer getStdAge() {
		return stdAge;
	}

	public void setStdAge(Integer stdAge) {
		this.stdAge = stdAge;
	}

	public String getStdAddress() {
		return stdAddress;
	}

	public void setStdAddress(String stdAddress) {
		this.stdAddress = stdAddress;
	}
	
	public String getStdStatus() {
		return stdStatus;
	}

	public void setStdStatus(String stdStatus) {
		this.stdStatus = stdStatus;
	}
	
}
