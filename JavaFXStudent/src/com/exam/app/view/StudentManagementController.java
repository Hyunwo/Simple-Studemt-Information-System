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
				studentD = new Student(result.getString("학번"), result.getString("이름"), result.getString("학과"), result.getInt("나이"), result.getString("재적 상태"));
				observableList.add(studentD);
			}
		} catch(SQLException e) {
			Logger.getLogger(StudentManagementController.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
			
		} return observableList;
	}
	
	// 테이블 보기
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
	
	// tableView에서 선택하면 textfield에 보이게 한다
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
	
	// 등록
	public void addStudentsAdd() {
		String insertData = "INSERT INTO Student " + "(학번, 이름, 학과, 나이, `재적 상태`) " + "VALUES(?,?,?,?,?)";

        connect = DatabaseConnection.getDBConnection();

        try {
            Alert alert;

            // 빈칸 체크 및 에러
            if (tfStdID.getText().isEmpty() || tfStdName.getText().isEmpty() || tfStdMajor.getText().isEmpty()|| tfStdAge.getText().isEmpty() || tfStdStatus.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("빈칸을 채워주세요");
                alert.showAndWait();
            } else {
                // stdID가 있는지 체크
                String checkData = "SELECT 학번 FROM Student WHERE 학번 = '" + tfStdID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                // Error
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(tfStdID.getText() + "는 이미 존재합니다.");
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
                    alert.setTitle("알림");
                    alert.setHeaderText(null);
                    alert.setContentText("등록되었습니다.");
                    alert.showAndWait();

                	// 업데이트
                    observableList.clear();
                    addStudentsShowListData();
                    // 초기화
                    addStudentsClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	// 초기화
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
	    cbStdMajor.setItems(FXCollections.observableArrayList("컴퓨터공학부", "AI소프트웨어학과", "건축학부", "기계공학과"));
	    cbStdStatus.setItems(FXCollections.observableArrayList("재학", "휴학"));
	}
	
	public void addStudentsSearch() {
		// 필터
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
							return false; // 찾는 값이 없음
					});
				});
				
				SortedList<Student> sortedData = new SortedList <>(filteredData);
				
				sortedData.comparatorProperty().bind(tableView.comparatorProperty());
				
				// 필터, 정렬 tableView에 적용
				tableView.setItems(sortedData);
	}
	
	
	//창 변환
//	public void switchFrom(ActionEvent event) {
//		if (event.getSource() == btnStdAdd) {
//			AddStudent_form.setVisible(true);
//			//나머지 form은 flase
//			//클릭시 색 변화 -> 다른 색으로 변경할 것
//			btnStdAdd.setStyle("-fx-background-color:linear-gradient(to bottom right, #585c5e, #999c9a);");
//			addStudentsShowListData();
//			
//		}
//		//else if()
//	}
	
	// 축소
	public void minimize() {
		Stage stage = (Stage)main_form.getScene().getWindow();
		stage.setIconified(true);
	}
	
	// 종료
	public void close() {
		System.exit(0);
	}
}
