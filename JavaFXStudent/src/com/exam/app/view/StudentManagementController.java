package com.exam.app.view;

import java.net.*;
import java.util.*;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
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
    private Button btnSearch;

    @FXML
    private Button btnStdAdd;

    @FXML
    private Button btnUpdate;

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
	
	
	ObservableList<Student> observableList = FXCollections.observableArrayList(
				new Student(new SimpleStringProperty("2019225189"), new SimpleStringProperty("Hyeonu"),  new SimpleIntegerProperty(24))
				);
	
	@Override
	public void initialize(URL loaction, ResourceBundle resources) {
		stdIDColumn.setCellValueFactory(cellData -> cellData.getValue().stdIDProperty());
	    stdNameColumn.setCellValueFactory(cellData -> cellData.getValue().stdNameProperty());
	    stdAgeColumn.setCellValueFactory(cellData -> cellData.getValue().stdAgeProperty().asObject());
	    tableView.setItems(observableList);
	    
	    btnAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @SuppressWarnings("unused")
			public void handle(MouseEvent event) {
                tableView.getItems().add(new Student(new SimpleStringProperty(tfStdID.getText()), new SimpleStringProperty(tfStdName.getText()), 
                		 new SimpleIntegerProperty(Integer.parseInt(tfStdAge.getText()))));
            }//���콺 �̺�Ʈ�� �÷� ����
        });
	    
	    cbStdMajor.setItems(FXCollections.observableArrayList("��ǻ�Ͱ��к�", "AI����Ʈ�����а�", "�����к�", "�����а�"));
	    cbStdStatus.setItems(FXCollections.observableArrayList("����", "����"));
	}
	
	@FXML
	private void handleResetButtonAction(ActionEvent event) {
		// textfield objects can be reset by "" or null
		tfStdID.setText("");
		tfStdName.setText(null);
		tfStdAge.setText(null);
	}
	
	
}
