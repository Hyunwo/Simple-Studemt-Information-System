package com.exam.app.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Student {

	private StringProperty stdID;
	private StringProperty stdName;
	private StringProperty stdMajor;
	private IntegerProperty stdAge;

	
	public Student(StringProperty stdID, StringProperty stdName, StringProperty stdMajor, IntegerProperty stdAge) {
		this.stdID = stdID;
	    this.stdName = stdName;
	    this.stdMajor = stdMajor;
	    this.stdAge = stdAge;
	}
	
	public StringProperty stdIDProperty() {
		return stdID;
	}
	public StringProperty stdNameProperty() {
	    return stdName;
	}
	public StringProperty stdMajorProperty() {
	    return stdMajor;
	}
	public IntegerProperty stdAgeProperty() {
	    return stdAge;
	}
	
}
