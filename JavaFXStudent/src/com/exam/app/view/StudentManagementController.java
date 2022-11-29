package com.exam.app.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private Button btnClose;
    
    @FXML
    private Button btnMinimize;

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
    private AnchorPane main_form;
    
    @FXML
    private AnchorPane AddStudent_form;
    
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
	
	private Connection connect;
	private PreparedStatement prepare;
	private Statement statement;
	private ResultSet result;
	
	ObservableList<Student> observableList = FXCollections.observableArrayList();
	PreparedStatement pst = null;
	
	
	public ObservableList<Student> addStudentsListData() {
		
		String studentViewQuery = "SELECT * FROM Student";
		
		connect = DatabaseConnection.getDBConnection();
		
		try {
			Student studentD;
			prepare = connect.prepareStatement(studentViewQuery);
			result = prepare.executeQuery();
			
			while (result.next()) {
				studentD = new Student(result.getString("�й�"), result.getString("�̸�"), result.getString("�а�"), result.getInt("����"), result.getString("���� ����"));
				observableList.add(studentD);
			}
		} catch(SQLException e) {
			Logger.getLogger(StudentManagementController.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
			
		} return observableList;
	}
	
	// ���̺� ����
	private ObservableList<Student> addStudentsListD;
	public void addStudentsShowListData() {
		addStudentsListD = addStudentsListData();
		
		stdIDColumn.setCellValueFactory(new PropertyValueFactory<>("stdID"));
		stdNameColumn.setCellValueFactory(new PropertyValueFactory<>("stdName"));
		stdMajorColumn.setCellValueFactory(new PropertyValueFactory<>("stdMajor"));
		stdAgeColumn.setCellValueFactory(new PropertyValueFactory<>("stdAge"));
		stdStatusColumn.setCellValueFactory(new PropertyValueFactory<>("stdStatus"));
		
		tableView.setItems(addStudentsListD);
	}
	
	// tableView���� �����ϸ� textfield�� ���̰� �Ѵ�
	public void addStudentsSelect() {
		
		Student studentD = tableView.getSelectionModel().getSelectedItem();
		int num = tableView.getSelectionModel().getSelectedIndex();
		
		if( (num - 1) < -1) {
			return;
		}
		
		tfStdID.setText(studentD.getStdID());
		tfStdName.setText(studentD.getStdName());
		tfStdMajor.setText(studentD.getStdMajor());
		tfStdAge.setText(String.valueOf(studentD.getStdAge()));
		tfStdStatus.setText(studentD.getStdStatus());
	}
	
	// ���
	public void addStudentsAdd() {
		String insertData = "INSERT INTO Student " + "(�й�, �̸�, �а�, ����, `���� ����`) " + "VALUES(?,?,?,?,?)";

        connect = DatabaseConnection.getDBConnection();

        try {
            Alert alert;

            // ��ĭ üũ �� ����
            if (tfStdID.getText().isEmpty() || tfStdName.getText().isEmpty() || tfStdMajor.getText().isEmpty()|| tfStdAge.getText().isEmpty() || tfStdStatus.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("��ĭ�� ä���ּ���");
                alert.showAndWait();
            } else {
                // stdID�� �ִ��� üũ
                String checkData = "SELECT �й� FROM Student WHERE �й� = '" + tfStdID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                // Error
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(tfStdID.getText() + "�� �̹� �����մϴ�.");
                    alert.showAndWait();
                } else {
                	prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, tfStdID.getText());
                    prepare.setString(2, tfStdName.getText());
                    prepare.setString(3, tfStdMajor.getText());
                    prepare.setString(4, tfStdAge.getText());
                    prepare.setString(5, tfStdStatus.getText());

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("�˸�");
                    alert.setHeaderText(null);
                    alert.setContentText("��ϵǾ����ϴ�.");
                    alert.showAndWait();

                	// ������Ʈ
                    observableList.clear();
                    addStudentsShowListData();
                    // �ʱ�ȭ
                    addStudentsClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	// �ʱ�ȭ
	public void addStudentsClear() {
		tfStdID.setText("");
		tfStdName.setText(null);
		tfStdMajor.setText(null);
		tfStdAge.setText(null);
		tfStdStatus.setText(null);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		
		addStudentsShowListData();
		
	    // Combobox list
	    cbStdMajor.setItems(FXCollections.observableArrayList("��ǻ�Ͱ��к�", "AI����Ʈ�����а�", "�����к�", "�����а�"));
	    cbStdStatus.setItems(FXCollections.observableArrayList("����", "����"));
	}
	
	public void addStudentsSearch() {
		// ����
				FilteredList<Student> filteredData = new FilteredList<>(observableList, b -> true);
				
				tfStdSearch.textProperty().addListener((observable, oldValue, newValue) ->{
					filteredData.setPredicate(Student -> {
						
						if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
							return true;
						}
						
						String searchKeyword = newValue.toLowerCase();
						
						if (Student.getStdID().toLowerCase().indexOf(searchKeyword) > -1) {
							return true;
						} else if (Student.getStdName().toLowerCase().indexOf(searchKeyword) > -1) {
							return true;
						} else if (Student.getStdMajor().toLowerCase().indexOf(searchKeyword) > -1) {
							return true;
						} else if (Student.getStdAge().toString().indexOf(searchKeyword) > -1) {
							return true;
						} else if (Student.getStdStatus().toLowerCase().indexOf(searchKeyword) > -1) {
							return true;
						} else
							return false; // ã�� ���� ����
					});
				});
				
				SortedList<Student> sortedData = new SortedList <>(filteredData);
				
				sortedData.comparatorProperty().bind(tableView.comparatorProperty());
				
				// ����, ���� tableView�� ����
				tableView.setItems(sortedData);
	}
	
	
	//â ��ȯ
//	public void switchFrom(ActionEvent event) {
//		if (event.getSource() == btnStdAdd) {
//			AddStudent_form.setVisible(true);
//			//������ form�� flase
//			//Ŭ���� �� ��ȭ -> �ٸ� ������ ������ ��
//			btnStdAdd.setStyle("-fx-background-color:linear-gradient(to bottom right, #585c5e, #999c9a);");
//			addStudentsShowListData();
//			
//		}
//		//else if()
//	}
	
	// ���
	public void minimize() {
		Stage stage = (Stage)main_form.getScene().getWindow();
		stage.setIconified(true);
	}
	
	// ����
	public void close() {
		System.exit(0);
	}
}
