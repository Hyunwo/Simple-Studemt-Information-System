package com.exam.app.view;

import java.net.*;
import java.util.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

class MyEventHandler implements EventHandler<ActionEvent> {
	@Override
	public void handle(ActionEvent event) {
		System.out.println("MyEventHandeler handle");
	}
}

public class StudentManagementController implements Initializable {

	@FXML
	private Button btnSearch;
	
	@FXML
	private Button btnSave;
	
	@FXML
	private Label lblStdID;
	
	@FXML
	private Label lblStdName;
	
	@FXML
	private Label lblStdMajor;
	
	@FXML
	private Label lblStdAge;
	
	@FXML
	private TextField tfStdID;
	
	@FXML
	private TextField tfStdName;
	
	@FXML
	private TextField tfStdMajor;
	
	@FXML
	private TextField tfStdAge;
	
	@FXML
	private TextArea taStdInformation;
	
	@FXML
	private void handleButtonAction(ActionEvent event) {
		// create a student object using a default constructor
		Student student = new Student();
		
		// obtain a stdID using the textfield
		// and save it to student object by using the setStdID method
		String stdID = tfStdID.getText();
		student.setStdID(stdID);
		
		// save a stdName in a shortcut
		student.setStdName(tfStdName.getText());
		
		// save a stdMajor in a shortcut
		student.setStdMajor(tfStdMajor.getText());
		
		// this might be okay. It supposed to be an integer value
		student.setStdAge(Integer.parseInt(tfStdAge.getText()));
		
		// display student record to TextArea
		// student information is stored in a student object
		taStdInformation.appendText(student.toString() + "\n"); // add a newline at the end
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	@FXML
	private void handleResetButtonAction(ActionEvent event) {
		// textfield objects can be reset by "" or null
		tfStdID.setText("");
		tfStdName.setText(null);
		tfStdMajor.setText(null);
		tfStdAge.setText(null);
	}
	
	
}
