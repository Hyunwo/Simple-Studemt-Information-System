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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
    private Button btnReset2;
    
    @FXML
    private Button btnStdAdd;
    
    @FXML
    private Button btnStdAdd2;

    @FXML
    private Button btnUpdate;
    
    @FXML
    private Button btnHome;
    
    @FXML
    private Button btnStdGrade;

    @FXML
    private ImageView imageView;

    @FXML
    private Label lblStdID;

    @FXML
    private Label lblStdName;
    
    @FXML
    private Label lblStdMajor;
    
    @FXML
    private Label lblStdGender;
    
    @FXML
    private Label lblStdAge;
    
    @FXML
    private Label lblStdAddress;

    @FXML
    private Label lblStdStatus;
    
    @FXML
    private Label lbl11;

    @FXML
    private Label lbl12;

    @FXML
    private Label lbl21;

    @FXML
    private Label lbl22;

    @FXML
    private Label lbl31;

    @FXML
    private Label lbl32;

    @FXML
    private Label lbl41;

    @FXML
    private Label lbl42;

    @FXML
    private TextField tfStdID;

    @FXML
    private TextField tfStdName;
    
    @FXML
    private TextField tfStdMajor;
    
    @FXML
    private TextField tfStdGender;
    
    @FXML
    private TextField tfStdAge;
    
    @FXML
    private TextField tfStdAddress;
    
    @FXML
    private TextField tfStdStatus;

    @FXML
    private TextField tfStdSearch;
    
    @FXML
    private TextField tfStdID2;

    @FXML
    private TextField tfStdName2;
    
    @FXML
    private TextField tf11;

    @FXML
    private TextField tf12;

    @FXML
    private TextField tf21;

    @FXML
    private TextField tf22;

    @FXML
    private TextField tf31;

    @FXML
    private TextField tf32;

    @FXML
    private TextField tf41;

    @FXML
    private TextField tf42;
    
    @FXML
    private AnchorPane main_form;
    
    @FXML
    private AnchorPane AddStudent_form;
    
    @FXML
    private AnchorPane studentGrade_form;
    
    @FXML
    private ComboBox<String> cbStdMajor;

    @FXML
    private ComboBox<String> cbStdStatus;

	@FXML
	private TableView<Student> tableView;
	
	@FXML
	private TableView<Student> tableView2;
	
	@FXML
	private TableColumn<Student, String> stdIDColumn;
	
	@FXML
	private TableColumn<Student, String> stdNameColumn;
	
	@FXML
	private TableColumn<Student, String> stdMajorColumn;
	
    @FXML
    private TableColumn<Student, String> stdGenderColumn;
	
	@FXML
	private TableColumn<Student, Integer> stdAgeColumn;
	
    @FXML
    private TableColumn<Student, String> stdAddressColumn;
	
	@FXML
    private TableColumn<Student, String> stdStatusColumn;
	
    @FXML
    private TableColumn<Student, String> std11Column;

    @FXML
    private TableColumn<Student, String> std12Column;

    @FXML
    private TableColumn<Student, String> std21Column;

    @FXML
    private TableColumn<Student, String> std22Column;

    @FXML
    private TableColumn<Student, String> std31Column;

    @FXML
    private TableColumn<Student, String> std32Column;

    @FXML
    private TableColumn<Student, String> std41Column;

    @FXML
    private TableColumn<Student, String> std42Column;
    
    @FXML
    private TableColumn<Student, String> stdIDColumn2;
    
    @FXML
    private TableColumn<Student, String> stdNameColumn2;
	
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
				studentD = new Student(result.getString("학번"), result.getString("이름"), result.getString("학과"), result.getString("성별"), result.getInt("나이"), result.getString("주소"), result.getString("재적 상태"));
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
		observableList.clear();
		addStudentsListD = addStudentsListData();
		
		stdIDColumn.setCellValueFactory(new PropertyValueFactory<>("stdID"));
		stdNameColumn.setCellValueFactory(new PropertyValueFactory<>("stdName"));
		stdMajorColumn.setCellValueFactory(new PropertyValueFactory<>("stdMajor"));
		stdGenderColumn.setCellValueFactory(new PropertyValueFactory<>("stdGender"));
		stdAgeColumn.setCellValueFactory(new PropertyValueFactory<>("stdAge"));
		stdAddressColumn.setCellValueFactory(new PropertyValueFactory<>("stdAddress"));
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
		tfStdGender.setText(studentD.getStdGender());
		tfStdAge.setText(String.valueOf(studentD.getStdAge()));
		tfStdAddress.setText(studentD.getStdAddress());
		tfStdStatus.setText(studentD.getStdStatus());
	}
	
	// 등록
	public void addStudentsAdd() {
		String insertData = "INSERT INTO Student " + "(학번, 이름, 학과, 성별, 나이, 주소, `재적 상태`) " + "VALUES(?,?,?,?,?,?,?)";

        connect = DatabaseConnection.getDBConnection();

        try {
            Alert alert;

            // 빈칸 체크 및 에러
            if (tfStdID.getText().isEmpty() || tfStdName.getText().isEmpty() || tfStdMajor.getText().isEmpty()|| tfStdGender.getText().isEmpty() 
            		|| tfStdAge.getText().isEmpty() || tfStdAddress.getText().isEmpty() || tfStdStatus.getText().isEmpty()) {
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
                    prepare.setString(4, tfStdGender.getText());
                    prepare.setString(5, tfStdAge.getText());
                    prepare.setString(6, tfStdAddress.getText());
                    prepare.setString(7, tfStdStatus.getText());

                    prepare.executeUpdate();

                    // 학생등록시 성적도 등록
                    String insertStudentGrade = "INSERT INTO student_grade " + "(학번, 이름, `학점(1-1)`, `학점(1-2)`, `학점(2-1)`, `학점(2-2)`, `학점(3-1)`, `학점(3-2)`, `학점(4-1)`, `학점(4-2)`) " + "VALUES(?,?,?,?,?,?,?,?,?,?)";
                    
                    prepare = connect.prepareStatement(insertStudentGrade);
                    prepare.setString(1, tfStdID.getText());
                    prepare.setString(2, tfStdName.getText());
                    prepare.setString(3, "0.0");
                    prepare.setString(4, "0.0");
                    prepare.setString(5, "0.0");
                    prepare.setString(6, "0.0");
                    prepare.setString(7, "0.0");
                    prepare.setString(8, "0.0");
                    prepare.setString(9, "0.0");
                    prepare.setString(10, "0.0");
                    
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("알림");
                    alert.setHeaderText(null);
                    alert.setContentText("등록되었습니다.");
                    alert.showAndWait();

                	// 업데이트
                    addStudentsShowListData();
                    // 초기화
                    addStudentsClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	public ObservableList<Student> studentGradeListData(){
		
		ObservableList<Student> listData = FXCollections.observableArrayList();
		
		String sql = "SELECT * FROM student_grade";
		
		connect = DatabaseConnection.getDBConnection();
		
		try {
			Student studentD;
			
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();
			
			while(result.next()) {
				studentD = new Student(result.getString("학번"), result.getString("이름"), result.getDouble("학점(1-1)"), result.getDouble("학점(1-2)")
						, result.getDouble("학점(2-1)"), result.getDouble("학점(2-2)"), result.getDouble("학점(3-1)"), result.getDouble("학점(3-2)"), result.getDouble("학점(4-1)"), result.getDouble("학점(4-2)"));
				listData.add(studentD);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}
	
	private ObservableList<Student> studentGradeList;
	private void studentGradeShowListData() {
		studentGradeList = studentGradeListData();
		
		stdIDColumn2.setCellValueFactory(new PropertyValueFactory<>("stdID"));
		stdNameColumn2.setCellValueFactory(new PropertyValueFactory<>("stdName"));
		std11Column.setCellValueFactory(new PropertyValueFactory<>("std11"));
		std12Column.setCellValueFactory(new PropertyValueFactory<>("std12"));
		std21Column.setCellValueFactory(new PropertyValueFactory<>("std21"));
		std22Column.setCellValueFactory(new PropertyValueFactory<>("std22"));
		std31Column.setCellValueFactory(new PropertyValueFactory<>("std31"));
		std32Column.setCellValueFactory(new PropertyValueFactory<>("std32"));
		std41Column.setCellValueFactory(new PropertyValueFactory<>("std41"));
		std42Column.setCellValueFactory(new PropertyValueFactory<>("std42"));
		
		tableView2.setItems(studentGradeList);
		
	}
	
	// tableView2에서 선택하면 textfield에 보이게 한다
	public void studentGradesSelect() {
		
		Student studentD = tableView2.getSelectionModel().getSelectedItem();
	    int num = tableView2.getSelectionModel().getSelectedIndex();

	    if ((num - 1) < -1) {
	    	return;
	        }

	    tfStdID2.setText(studentD.getStdID());
	    tfStdName2.setText(studentD.getStdName());
	    tf11.setText(String.valueOf(studentD.getStd11()));
	    tf12.setText(String.valueOf(studentD.getStd12()));
	    tf21.setText(String.valueOf(studentD.getStd21()));
	    tf22.setText(String.valueOf(studentD.getStd22()));
	    tf31.setText(String.valueOf(studentD.getStd31()));
	    tf32.setText(String.valueOf(studentD.getStd32()));
	    tf41.setText(String.valueOf(studentD.getStd41()));
	    tf42.setText(String.valueOf(studentD.getStd42()));
	}
	
	// 학점 추가
	public void studentGradeUpdate() {

        connect = DatabaseConnection.getDBConnection();


        try {

            String updateData = "UPDATE student_grade SET "
                  + " `학점(1-1)` = '" + tf11.getText() + "', `학점(1-2)` = '" + tf12.getText() + "', `학점(2-1)` = '" + tf21.getText() + "', `학점(2-2)` = '" + tf22.getText()
                  + "', `학점(3-1)` = '" + tf31.getText() + "', `학점(3-2)` = '" + tf32.getText() + "', `학점(4-1)` = '" + tf41.getText() + "', `학점(4-2)` = '" + tf42.getText()
                  + "' WHERE 학번 = '" + tfStdID2.getText() + "'";

            Alert alert;
            
            statement = connect.createStatement();
            statement.executeUpdate(updateData);

            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("알림");
            alert.setHeaderText(null);
            alert.setContentText("등록되었습니다.");
            alert.showAndWait();

            // 업데이트
            studentGradeShowListData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	// 초기화
	public void addStudentsClear() {
		tfStdID.setText("");
		tfStdName.setText(null);
		tfStdMajor.setText(null);
		tfStdGender.setText(null);
		tfStdAge.setText(null);
		tfStdAddress.setText(null);
		tfStdStatus.setText(null);
		
		tf11.setText("");
		tf12.setText("");
		tf21.setText("");
		tf22.setText("");
		tf31.setText("");
		tf32.setText("");
		tf41.setText("");
		tf42.setText(null);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		
		addStudentsShowListData();
		
		studentGradeShowListData();
		
	    // Combobox list
	    cbStdMajor.setItems(FXCollections.observableArrayList("컴퓨터공학부", "AI소프트웨어학과", "건축학부", "기계공학과", "경영학과"));
	    cbStdStatus.setItems(FXCollections.observableArrayList("재학", "휴학"));
	}
	
	// 필터
	public void addStudentsSearch() {
		
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
						} else if (Student.getStdGender().toLowerCase().indexOf(searchKeyword) > -1) {
							return true;
						} else if (Student.getStdAge().toString().indexOf(searchKeyword) > -1) {
							return true;
						} else if (Student.getStdAddress().toLowerCase().indexOf(searchKeyword) > -1) {
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
	public void switchFrom(ActionEvent event) {
		if (event.getSource() == btnStdAdd) {
			AddStudent_form.setVisible(true);
			studentGrade_form.setVisible(false);
			//나머지 form은 flase
			
			//클릭시 색 변화
			btnStdAdd.setStyle("-fx-background-color:linear-gradient(to bottom right, #CE9FFC, #7367F0);");
			btnStdGrade.setStyle("-fx-background-color:transparent");
			addStudentsShowListData();
			
		}
		else if (event.getSource() == btnStdGrade) {
			AddStudent_form.setVisible(false);
			studentGrade_form.setVisible(true);
			
			btnStdAdd.setStyle("-fx-background-color:transparent");
			btnStdGrade.setStyle("-fx-background-color:linear-gradient(to bottom right, #CE9FFC, #7367F0);");
			studentGradeShowListData();
		}
	}
	
	
}
