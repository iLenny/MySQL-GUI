/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mySQLUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Leibnix
 */
public class MySQLUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginPane loginPane = new LoginPane();
        loginPane.setCurrentStage(primaryStage);        
        
        Scene scene = new Scene(loginPane, 370, 350);
        
        primaryStage.setTitle("MySQL UI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String [] args) {
        launch(args);
    }
}
