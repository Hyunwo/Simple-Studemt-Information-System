package com.exam.app.view;

public class StudentApp {

	public static void main(String[] args) {
		
		System.out.println("Student Infomation System");
		
		// create an object using the default constructor
		Student student = new Student();

		// set student ID, Name, Major, Age
		student.setStdID("2019225189");
		student.setStdName("������");
		student.setStdMajor("��ǻ�Ͱ��к�");
		student.setStdAge(24);
		
		// display student object information
		System.out.println(student.toString());
	}
}
