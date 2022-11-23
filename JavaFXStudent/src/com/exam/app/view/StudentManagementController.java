package com.exam.app.view;

import java.net.*;
import java.util.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
	private Button btnAdd;
	
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
	private TableView<Student> tableView;
	
	@FXML
	private TableColumn<Student, String> stdIDColumn;
	
	@FXML
	private TableColumn<Student, String> stdNameColumn;
	
	@FXML
	private TableColumn<Student, String> stdMajorColumn;
	
	@FXML
	private TableColumn<Student, Integer> stdAgeColumn;
	/*
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
		
		// student information is stored in a student object
		taStdInformation.appendText(student.toString() + "\n"); // add a newline at the end
	}
	*/
	
	ObservableList<Student> observableList = FXCollections.observableArrayList(
				new Student(new SimpleStringProperty("2019225189"), new SimpleStringProperty("Hyeonu"), new SimpleStringProperty("컴퓨터공학부"), new SimpleIntegerProperty(24))
				);
	
	@Override
	public void initialize(URL loaction, ResourceBundle resources) {
		stdIDColumn.setCellValueFactory(cellData -> cellData.getValue().stdIDProperty());
	    stdNameColumn.setCellValueFactory(cellData -> cellData.getValue().stdNameProperty());
	    stdMajorColumn.setCellValueFactory(cellData -> cellData.getValue().stdMajorProperty());
	    stdAgeColumn.setCellValueFactory(cellData -> cellData.getValue().stdAgeProperty().asObject());
	    tableView.setItems(observableList);
	    
	    btnAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @SuppressWarnings("unused")
			public void handle(MouseEvent event) {
                tableView.getItems().add(new Student(new SimpleStringProperty(tfStdID.getText()), new SimpleStringProperty(tfStdName.getText()), 
                		new SimpleStringProperty(tfStdMajor.getText()), new SimpleIntegerProperty(Integer.parseInt(tfStdAge.getText()))));
            }
        });
	}
	
	@FXML
	private void handleResetButtonAction(ActionEvent event) {
		// textfield objects can be reset by "" or null
		tfStdID.setText("");
		tfStdName.setText(null);
		tfStdMajor.setText(null);
		tfStdAge.setText(null);
	}
	
	/*
	@FXML
	private void handleSearchButtonAction(ActionEvent event) {
		
		stdIDColumn
		stdNameColumn
		stdMajorColumn
		stdAgeColumn
	}
	*/
}
