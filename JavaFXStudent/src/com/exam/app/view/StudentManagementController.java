package com.exam.app.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class StudentManagementController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelect;

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
    private Label hometotal;
    
    @FXML
    private Label homeStd;

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
    private AnchorPane home_form;
    
    @FXML
    private AnchorPane AddStudent_form;
    
    @FXML
    private AnchorPane studentGrade_form;
    
    @FXML
    private BarChart<?, ?> stdGradeBarChart;
    
    @FXML
    private BarChart<?, ?> homeBarChart;

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
    
    @FXML
    private TableColumn<Student, String> stdGradeAvgColumn;
   
    
   ObservableList<Student> observableList = FXCollections.observableArrayList();
   
   public ObservableList<Student> addStudentsListData() {
      
      String studentViewQuery = "SELECT * FROM Student";
      
      Connection connect = DatabaseConnection.getDBConnection();
      
      try {
         Student studentD;
         PreparedStatement prepare = connect.prepareStatement(studentViewQuery);
         ResultSet result = prepare.executeQuery();
         
         while (result.next()) {
            studentD = new Student(result.getString("????"), result.getString("????"), result.getString("????"), result.getString("????"), result.getInt("????"), result.getString("????"), result.getString("???? ????"));
            observableList.add(studentD);
         }
      } catch(Exception e) {
         e.printStackTrace();
         
      } return observableList;
   }
   
   // ?????? ????
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
   
   // tableView???? ???????? textfield?? ?????? ????
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
   
   // ????
   public void addStudentsAdd() {
      String insertData = "INSERT INTO Student " + "(????, ????, ????, ????, ????, ????, `???? ????`) " + "VALUES(?,?,?,?,?,?,?)";

      Connection connect = DatabaseConnection.getDBConnection();

        try {
            Alert alert;

            // ???? ???? ?? ????
            if (tfStdID.getText().isEmpty() || tfStdName.getText().isEmpty() || tfStdMajor.getText().isEmpty()|| tfStdGender.getText().isEmpty() 
                  || tfStdAge.getText().isEmpty() || tfStdAddress.getText().isEmpty() || tfStdStatus.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("?????? ??????????");
                alert.showAndWait();
            } else {
                // stdID?? ?????? ????
                String checkData = "SELECT ???? FROM Student WHERE ???? = '" + tfStdID.getText() + "'";

                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(checkData);

                // Error
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(tfStdID.getText() + "?? ???? ??????????.");
                    alert.showAndWait();
                } else {
                   PreparedStatement prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, tfStdID.getText());
                    prepare.setString(2, tfStdName.getText());
                    prepare.setString(3, tfStdMajor.getText());
                    prepare.setString(4, tfStdGender.getText());
                    prepare.setString(5, tfStdAge.getText());
                    prepare.setString(6, tfStdAddress.getText());
                    prepare.setString(7, tfStdStatus.getText());

                    prepare.executeUpdate();

                    // ?????????? ?????? ????
                    String insertStudentGrade = "INSERT INTO student_grade " + "(????, ????, `????(1-1)`, `????(1-2)`, `????(2-1)`, `????(2-2)`, `????(3-1)`, `????(3-2)`, `????(4-1)`, `????(4-2)`) " + "VALUES(?,?,?,?,?,?,?,?,?,?)";
                    
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
                    alert.setTitle("????");
                    alert.setHeaderText(null);
                    alert.setContentText("??????????????.");
                    alert.showAndWait();

                   // ????????
                    addStudentsShowListData();
                    // ??????
                    addStudentsClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
   // ????
   public void addStudentsDel() {
      String delData = "DELETE " + "FROM Student " + "where ???? = ?";

        Connection connect = DatabaseConnection.getDBConnection();

        try {
            Alert alert;

            // ???? ???? ?? ????
            if (tfStdID.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("?????? ?????? ??????????");
                alert.showAndWait();
            } else {
                // stdID?? ?????? ????
                String checkData = "SELECT ???? FROM Student WHERE ???? = '" + tfStdID.getText() + "'";

                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(checkData);
                if ( result.next() ) {
                    
                    PreparedStatement prepare = connect.prepareStatement(delData);
                    prepare.setString(1, tfStdID.getText());
                    prepare.executeUpdate();
                    
                    // ?????????? ?????? ????
                    String delStudentGrade = "DELETE " + "FROM student_grade " + "where ???? = ?";
                    
                    prepare = connect.prepareStatement(delStudentGrade);
                    prepare.setString(1, tfStdID.getText());
                    
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("????");
                    alert.setHeaderText(null);
                    alert.setContentText("??????????????.");
                    alert.showAndWait();

                   // ????????
                    addStudentsShowListData();
                    // ??????
                    addStudentsClear();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(tfStdID.getText() + "?? ???????? ????????. ");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      
   
// ????
   public void addStudentsFix() {
      String updateData = "UPDATE Student "
                     + "SET ????=?, ????=?, ????=?, ????=?, ????=?, `???? ????`=? "
                     + "WHERE ????=?";

        Connection connect = DatabaseConnection.getDBConnection();

        try {
            Alert alert;

            // ???? ???? ?? ????
            if (tfStdID.getText().isEmpty() || tfStdName.getText().isEmpty() || tfStdMajor.getText().isEmpty()|| tfStdGender.getText().isEmpty() 
                  || tfStdAge.getText().isEmpty() || tfStdAddress.getText().isEmpty() || tfStdStatus.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("?????? ??????????");
                alert.showAndWait();
            } else {
                // stdID?? ?????? ????
                String checkData = "SELECT ???? FROM Student WHERE ???? = '" + tfStdID.getText() + "'";

                Statement statement = connect.createStatement();
                ResultSet result = statement.executeQuery(checkData);
                
                if ( result.next() ) {
                    PreparedStatement prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, tfStdName.getText());
                    prepare.setString(2, tfStdMajor.getText());
                    prepare.setString(3, tfStdGender.getText());
                    prepare.setString(4, tfStdAge.getText());
                    prepare.setString(5, tfStdAddress.getText());
                    prepare.setString(6, tfStdStatus.getText());
                    prepare.setString(7, tfStdID.getText());
                    
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("????");
                    alert.setHeaderText(null);
                    alert.setContentText("??????????????.");
                    alert.showAndWait();

                   // ????????
                    addStudentsShowListData();
                    // ??????
                    addStudentsClear(); 
                //error
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(tfStdID.getText() + "?? ???????? ????????.");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
   
   public ObservableList<Student> studentGradeListData(){
      
      ObservableList<Student> listData = FXCollections.observableArrayList();
      
      String sql = "SELECT * FROM student_grade";
      
      Connection connect = DatabaseConnection.getDBConnection();
      
      try {
         Student studentD;
         
         PreparedStatement prepare = connect.prepareStatement(sql);
         ResultSet result = prepare.executeQuery();
         
         while(result.next()) {
            studentD = new Student(result.getString("????"), result.getString("????"), result.getDouble("????(1-1)"), result.getDouble("????(1-2)")
                  , result.getDouble("????(2-1)"), result.getDouble("????(2-2)"), result.getDouble("????(3-1)"), result.getDouble("????(3-2)"), result.getDouble("????(4-1)"), result.getDouble("????(4-2)"), result.getDouble("????"));
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
      stdGradeAvgColumn.setCellValueFactory(new PropertyValueFactory<>("stdGradeAvg"));
      
      tableView2.setItems(studentGradeList);
      
   }
   
   // tableView2???? ???????? textfield?? ?????? ????
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
   
   // count
   public int count() {
      
      int cnt = 0;
      
      if(Double.parseDouble(tf11.getText()) != 0) {
          cnt += 1;
       } else return cnt;
      
       if(Double.parseDouble(tf12.getText()) != 0) {
          cnt += 1;
       } else return cnt;
       
       if(Double.parseDouble(tf21.getText()) != 0) {
          cnt += 1;
       } else return cnt;
       
       if(Double.parseDouble(tf22.getText()) != 0) {
          cnt += 1;
       } else return cnt;
       
       if(Double.parseDouble(tf31.getText()) != 0) {
          cnt += 1;
       } else return cnt;
       
       if(Double.parseDouble(tf32.getText()) != 0) {
          cnt += 1;
       } else return cnt;
       
       if(Double.parseDouble(tf41.getText()) != 0) {
          cnt += 1;
       } else return cnt;
       
       if(Double.parseDouble(tf42.getText()) != 0) {
          cnt += 1;
       } else return cnt;
       return cnt;
   }
   
   // ???? ????
   public void studentGradeUpdate() {
      
      Connection connect = DatabaseConnection.getDBConnection();

      double stdGradeAvgResult = 0;
      
        try {
           
           stdGradeAvgResult = (Double.parseDouble(tf11.getText()) + Double.parseDouble(tf12.getText()) + Double.parseDouble(tf21.getText()) 
           + Double.parseDouble(tf22.getText()) + Double.parseDouble(tf31.getText()) + Double.parseDouble(tf32.getText()) + Double.parseDouble(tf41.getText()) + Double.parseDouble(tf42.getText())) / count();
           
            String updateData = "UPDATE student_grade SET "
                  + " `????(1-1)` = '" + tf11.getText() + "', `????(1-2)` = '" + tf12.getText() + "', `????(2-1)` = '" + tf21.getText() + "', `????(2-2)` = '" + tf22.getText()
                  + "', `????(3-1)` = '" + tf31.getText() + "', `????(3-2)` = '" + tf32.getText() + "', `????(4-1)` = '" + tf41.getText() + "', `????(4-2)` = '" + tf42.getText()
                  + "', ???? = '" + stdGradeAvgResult + "' WHERE ???? = '" + tfStdID2.getText() + "'";

            Alert alert;
            
            Statement statement = connect.createStatement();
            statement.executeUpdate(updateData);

            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("????");
            alert.setHeaderText(null);
            alert.setContentText("??????????????.");
            alert.showAndWait();

            // ????????
            studentGradeShowListData();
            studentGradeBarChart();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
   // ??????
   public void addStudentsClear() {
      tfStdID.setText("");
      tfStdName.setText(null);
      tfStdMajor.setText(null);
      tfStdGender.setText(null);
      tfStdAge.setText(null);
      tfStdAddress.setText(null);
      tfStdStatus.setText(null);
      
      tfStdID2.setText("");
      tfStdName2.setText(null);
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
      
	  btnHome.setStyle("-fx-background-color:#3e3e3f;");
	  homeXYChart();
	  home();
	  homeNow();
      addStudentsShowListData();
      studentGradeShowListData();
      studentGradeBarChart();
      
   }
   
   // ????
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
                     return false; // ???? ???? ????
               });
            });
            
            SortedList<Student> sortedData = new SortedList <>(filteredData);
            
            sortedData.comparatorProperty().bind(tableView.comparatorProperty());
            
            // ????, ???? tableView?? ????
            tableView.setItems(sortedData);
   }
   
   // ???? ????
   public void studentGradeBarChart() {
      
      stdGradeBarChart.getData().clear();
      
      String chartsql = "SELECT ????, ???? FROM student_grade";
      
      Connection connect = DatabaseConnection.getDBConnection();
      
      try {
         XYChart.Series chart = new XYChart.Series();
         
         PreparedStatement prepare = connect.prepareStatement(chartsql);
         ResultSet result = prepare.executeQuery();
         
         while(result.next()) {
            chart.getData().add(new XYChart.Data(result.getString(1), result.getDouble(2)));
         }
         
         stdGradeBarChart.getData().add(chart);
         
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
   
   //?? ????
   public void switchFrom(ActionEvent event) {
      if (event.getSource() == btnStdAdd) {
         AddStudent_form.setVisible(true);
         studentGrade_form.setVisible(false);
         home_form.setVisible(false);
         //?????? form?? flase
         
         //?????? ?? ????
         btnStdAdd.setStyle("-fx-background-color: #3e3e3f;");
         btnStdGrade.setStyle("-fx-background-color:transparent");
         btnHome.setStyle("-fx-background-color:transparent");
         addStudentsShowListData();
         
      }
      else if (event.getSource() == btnStdGrade) {
         AddStudent_form.setVisible(false);
         studentGrade_form.setVisible(true);
         home_form.setVisible(false);
         
         btnStdAdd.setStyle("-fx-background-color:transparent");
         btnStdGrade.setStyle("-fx-background-color:#3e3e3f;");
         btnHome.setStyle("-fx-background-color:transparent");
         studentGradeBarChart();
         studentGradeShowListData();
         
      }
      else if (event.getSource() == btnHome) {
         AddStudent_form.setVisible(false);
         studentGrade_form.setVisible(false);
         home_form.setVisible(true);
         
         btnStdAdd.setStyle("-fx-background-color:transparent");
         btnStdGrade.setStyle("-fx-background-color:transparent");
         btnHome.setStyle("-fx-background-color:#3e3e3f;");
         home();
         homeNow();
         homeXYChart();
      }
   }
   
   // ???? ??????
   public void home() {

       String sql = "SELECT COUNT(????) FROM Student";
       Connection connect = DatabaseConnection.getDBConnection();

       int count = 0;

       try {
           PreparedStatement prepare = connect.prepareStatement(sql);
           ResultSet result = prepare.executeQuery();

           if (result.next()) {
               count = result.getInt("COUNT(????)");
           }

           hometotal.setText(String.valueOf(count));

       } catch (Exception e) {
           e.printStackTrace();
       }

   }
   
   // ????????
   public void homeNow() {

       String sql = "SELECT COUNT(????) FROM Student WHERE `???? ????` = '????'";
       Connection connect = DatabaseConnection.getDBConnection();

       int count = 0;

       try {
           PreparedStatement prepare = connect.prepareStatement(sql);
           ResultSet result = prepare.executeQuery();

           if (result.next()) {
               count = result.getInt("COUNT(????)");
           }

           homeStd.setText(String.valueOf(count));

       } catch (Exception e) {
           e.printStackTrace();
       }

   }

   // ?????? ???? ???? ??????
   public void homeXYChart() {

	   homeBarChart.getData().clear();

       String sql = "SELECT ????, COUNT(????) FROM student WHERE `???? ????` = '????' group by ????";

       Connection connect = DatabaseConnection.getDBConnection();

       try {
           XYChart.Series chart = new XYChart.Series();

           PreparedStatement prepare = connect.prepareStatement(sql);
           ResultSet result = prepare.executeQuery();

           while (result.next()) {
               chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
           }

           homeBarChart.getData().add(chart);

       } catch (Exception e) {
           e.printStackTrace();
       }

   }
   
   
}
