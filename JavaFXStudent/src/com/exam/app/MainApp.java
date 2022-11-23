package com.exam.app;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/StudentManagement2.fxml"));
		//fxml ���� ������ �ε��Ͽ� loader�� ����
		Parent root = loader.load(); //fxml���� ������ ȭ�� ������ root�� ����
		Scene scene = new Scene(root); //ȭ�� ������ �������� ��� ����
		
		primaryStage.setTitle("�л� ���� �ý���"); //â ����
		primaryStage.setScene(scene); //scene�� �����쿡 �ø�
		primaryStage.setResizable(false); //â ũ�� �缳�� ���� ����
		primaryStage.show(); //â ����
	}

	public static void main(String[] args) {
		launch(args);
	}
}
