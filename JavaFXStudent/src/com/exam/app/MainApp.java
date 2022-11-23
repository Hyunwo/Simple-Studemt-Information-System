package com.exam.app;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/StudentManagement2.fxml"));
		//fxml 파일 정보를 로드하여 loader에 저장
		Parent root = loader.load(); //fxml에서 가져온 화면 정보를 root에 저장
		Scene scene = new Scene(root); //화면 정보를 바탕으로 장면 생성
		
		primaryStage.setTitle("학사 관리 시스템"); //창 제목
		primaryStage.setScene(scene); //scene을 윈도우에 올림
		primaryStage.setResizable(false); //창 크기 재설정 여부 설정
		primaryStage.show(); //창 띄우기
	}

	public static void main(String[] args) {
		launch(args);
	}
}
