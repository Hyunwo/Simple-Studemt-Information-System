package com.exam.app.view;

public class Student {

	private String stdID = "";
	private String stdName = "";
	private String stdMajor = "";
	private int stdAge;

	
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

	public int getStdAge() {
		return stdAge;
	}

	public void setStdAge(int stdAge) {
		this.stdAge = stdAge;
	}

	@Override
	public String toString() {
		return "Student(" + "stdID=" + stdID + ", stdName=" + stdName + ", stdMajor=" + stdMajor + ", stdAge=" + stdAge + ")";
	}
	
}
