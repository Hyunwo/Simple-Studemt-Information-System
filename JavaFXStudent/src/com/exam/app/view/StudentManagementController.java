package com.exam.app.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class StudentManagementController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelect;

    @FXML
    private Button btnImage;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnStdAdd;

    @FXML
    private Button btnUpdate;
    
    @FXML
    private Button btnHome;

    @FXML
    private ImageView imageView;

    @FXML
    private Label lblStdAge;

    @FXML
    private Label lblStdID;

    @FXML
    private Label lblStdMajor;

    @FXML
    private Label lblStdName;

    @FXML
    private Label lblStdStatus;
    
    @FXML
    private TextField tfStdAge;

    @FXML
    private TextField tfStdID;

    @FXML
    private TextField tfStdName;
    
    @FXML
    private TextField tfStdMajor;
    
    @FXML
    private TextField tfStdStatus;

    @FXML
    private TextField tfStdSearch;
    
    @FXML
    private ComboBox<String> cbStdMajor;

    @FXML
    private ComboBox<String> cbStdStatus;

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
	
	@FXML
    private TableColumn<Student, String> stdStatusColumn;
	
	
	ObservableList<Student> observableList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getDBConnection();
		
		String studentViewQuery = "SELECT 학번, 이름, 전공, 나이, `재적 상태` FROM Student";
		
		try {
			
			Statement statement = connectDB.createStatement();
			ResultSet queryOutput = statement.executeQuery(studentViewQuery);
			
			while (queryOutput.next()) {
				
				String querystdID = queryOutput.getString("학번");
				String querystdName = queryOutput.getString("이름");
				String querystdMajor = queryOutput.getString("전공");
				Integer querystdAge = queryOutput.getInt("나이");
				String querystdStatus = queryOutput.getString("재적 상태");
				
				// Populate the ObservationList
				observableList.add(new Student(querystdID, querystdName, querystdMajor, querystdAge, querystdStatus));
				
			}
			
			// PropertyValueFactory corresponds to the new Student fields
			// The table column is the one you annotate above
			stdIDColumn.setCellValueFactory(new PropertyValueFactory<>("stdID"));
			stdNameColumn.setCellValueFactory(new PropertyValueFactory<>("stdName"));
			stdMajorColumn.setCellValueFactory(new PropertyValueFactory<>("stdMajor"));
			stdAgeColumn.setCellValueFactory(new PropertyValueFactory<>("stdAge"));
			stdStatusColumn.setCellValueFactory(new PropertyValueFactory<>("stdStatus"));
			
			tableView.setItems(observableList);
			
		} catch(SQLException e) {
			Logger.getLogger(StudentManagementController.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
			
		}
		
		// Initial filtered list
		FilteredList<Student> filteredData = new FilteredList<>(observableList, b -> true);
		
		tfStdSearch.textProperty().addListener((observable, oldValue, newValue) ->{
			filteredData.setPredicate(Student -> {
				
				// If no search value then display all records or whatever records it current have. no changes.
				if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
					return true;
				}
				
				String searchKeyword = newValue.toLowerCase();
				
				if (Student.getStdID().toLowerCase().indexOf(searchKeyword) > -1) {
					return true; // Means we found a match in StdID
				} else if (Student.getStdName().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				} else if (Student.getStdMajor().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				} else if (Student.getStdAge().toString().indexOf(searchKeyword) > -1) {
					return true;
				} else if (Student.getStdStatus().toLowerCase().indexOf(searchKeyword) > -1) {
					return true;
				} else
					return false; // no match found
			});
		});
		
		SortedList<Student> sortedData = new SortedList <>(filteredData);
		
		// Bind sorted result with table view
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
		// Apply filtered and sorted data to the table view
		tableView.setItems(sortedData);
		
		
		// This is for AddButton
		stdIDColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("stdID"));
		stdNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("stdName"));
		stdMajorColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("stdMajor"));
		stdAgeColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("stdAge"));
		stdStatusColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("stdStatus"));
		
	    // Combobox list
	    cbStdMajor.setItems(FXCollections.observableArrayList("컴퓨터공학부", "AI소프트웨어학과", "건축학부", "기계공학과"));
	    cbStdStatus.setItems(FXCollections.observableArrayList("재학", "휴학"));
	}
	
	// AddButton
	@FXML
	void handleAddButtonAction(ActionEvent event) {
		Student student = new Student(tfStdID.getText(), tfStdName.getText(), tfStdMajor.getText(), 
				Integer.parseInt(tfStdAge.getText()), tfStdStatus.getText());
		ObservableList<Student> students = tableView.getItems();
		students.add(student);
		tableView.setItems(students);
	}
	
	@FXML
    void handleHideButtonAction(MouseEvent event) {
		//btnHome.setVisible(false);
    }
	
	// ResetButton
	@FXML
	private void handleResetButtonAction(ActionEvent event) {
		// textfield objects can be reset by "" or null
		tfStdID.setText("");
		tfStdName.setText(null);
		tfStdMajor.setText(null);
		tfStdAge.setText(null);
		tfStdStatus.setText(null);
	}
	
	
}
