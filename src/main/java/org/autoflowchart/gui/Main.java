package org.autoflowchart.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.show();
        System.out.println();
        //develop test 2

        System.out.println();
        System.out.println("аолылоалыоаы");
        //develop branch test
    }

    public static void main(String[] args) {
        launch(args);
    }
}