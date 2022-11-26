package com.exam.app.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Student {

	private StringProperty stdID;
	private StringProperty stdName;
	private IntegerProperty stdAge;

	
	public Student(StringProperty stdID, StringProperty stdName,  IntegerProperty stdAge) {
		this.stdID = stdID;
	    this.stdName = stdName;
	    this.stdAge = stdAge;
	}
	
	public StringProperty stdIDProperty() {
		return stdID;
	}
	public StringProperty stdNameProperty() {
	    return stdName;
	}
	public IntegerProperty stdAgeProperty() {
	    return stdAge;
	}
	
}
