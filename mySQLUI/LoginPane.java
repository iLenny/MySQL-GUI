package mySQLUI;

import java.net.Inet6Address;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 
 * @author Leibniz H. Berihuete
 * Date Started: 6/17/2016 8:30 AM
 * 
 * LoginPane class:
 * The purpose of this class is to represent the login screen, in
 * order to prompt the user to login. This class is inheriting one
 * of the layouts from the javaFX library--the Pane class. 
 */
public class LoginPane extends AnchorPane {
    // Labels:
    private Label userLabel;
    private Label passwordLabel;
    private Label serverLabel;
    private Label databaseLabel;
    private Label messageLabel;
    
    // TextFields:
    private TextField userField;
    private TextField passwordField;
    private TextField serverField;
    private TextField databaseField;
    
    // Login Button
    private Button loginButton;
    
    // Current Stage/window:
    private Stage currentStage;
    
    
    
/* ***********************
        CONSTRUCTOR
 * ***********************/    
    public LoginPane () {
       initLabels();       
       initFields();       
       initLoginButton();
       buildPane();
       
        
    }
    
/* ***************************
         INITIALIZERS
 * ***************************/    
    private void initLabels() {
        // Initialize labels:
        userLabel = new Label("Username");
        passwordLabel = new Label("Password");
        serverLabel = new Label("Server address");
        databaseLabel = new Label("Database name");
        messageLabel = new Label();
        
        userLabel.setTextFill(Color.BLACK);
        passwordLabel.setTextFill(Color.BLACK);
        serverLabel.setTextFill(Color.BLACK);
        databaseLabel.setTextFill(Color.BLACK);
        messageLabel.setTextFill(Color.FIREBRICK);
        
        print("|FROM LoginPane: initLabels|: All labels have been initialized");
    }
    
    private void initFields() {
        // Initialize text fields:
        userField = new TextField();
        passwordField = new PasswordField();
        serverField = new TextField();
        databaseField = new TextField();
        
//        userField.setStyle("-fx-background-color: BLACK; -fx-text-fill: WHITE;");
//        passwordField.setStyle("-fx-background-color: BLACK; -fx-text-fill: WHITE;");
//        serverField.setStyle("-fx-background-color: BLACK; -fx-text-fill: WHITE;");
//        databaseField.setStyle("-fx-background-color: BLACK; -fx-text-fill: WHITE;");
        
        userField.setPrefColumnCount(25);
        
        print("|FROM LoginPane: initFields|: All text-fields have been initialized");
    }
    
    private void initLoginButton() {
        // Initialize login-button:
        loginButton = new Button("Login"); 
        loginButton.setPrefWidth(100);
        
        print("|FROM LoginPane: initLoginButton|: Login-button has been initialized");
        
        // build login-button functionality:
        loginButton.setOnMousePressed(e->{
            messageLabel.setText("");
        });
              
        loginButton.setOnMouseClicked((MouseEvent e) -> {
            print("|FROM LoginPane: initLoginButton|: Login-button has been clicked");
            
            if (currentStage != null) {
                String username = userField.getText();
                String password = passwordField.getText();
                String server = serverField.getText();
                String database = databaseField.getText();
                String DB_URL = "jdbc:mysql://" + server + "/" + database +"?useSSL=false";
                try {
                    
                    print("|FROM LoginPane|: Verifying database connection...");
                    Connection connection = DriverManager.getConnection(DB_URL, username, password);
                    print("|FROM LoginPane|: Connected to database " + database);
                    print("CONNECTION SUCCESSFUL");
                    messageLabel.setText("CONNECTION SUCCESSFUL");
                    messageLabel.setTextFill(Color.GREEN);
                    
                    currentStage.setScene(new Scene(new ViewTablesPane(connection), 350, 350));
                }
                catch(SQLException ex) {
                    
                    messageLabel.setText("CONNECTION FAILED\nInput information may be incorrect \nOR server may be currently down");
                    messageLabel.setTextFill(Color.FIREBRICK);
                    System.err.println("|FROM LoginPane|: CONNECTION FAILED");
                    System.err.println("Please check that the input information is correct");
                    
                }
                
                
            }
            else {
                System.err.println("|ERROR FROM LoginPane: initLoginButton|: no 'currentStage' has been assigned\n" +
                                   "\tSolution: use 'setCurrentStage' method to indicate what stage you" +
                                   "\n\tare currently using");

                
            }
        });
        
        print("|FROM initLoginButton|: Login-button's mouse-click-event has been built");
    }
    
    private void buildPane() {
        // Put each label with its corresspoding field in a vertical box:
        VBox userBox = new VBox(userLabel, userField);
        VBox passBox = new VBox(passwordLabel, passwordField);
        VBox serverBox = new VBox(serverLabel, serverField);
        VBox databaseBox = new VBox(databaseLabel, databaseField);
        
 
        
        // Put all the above boxes inside another vertical box along with the loginButton
        VBox inputBox = new VBox(userBox, passBox, serverBox, databaseBox, loginButton);
        inputBox.setSpacing(15);
        inputBox.setPadding(new Insets(20,20,20,20));
        // Create a title
        Label title = new Label("MySQL UI");
        title.setFont(new Font(30));
        
        title.setTranslateX(100);
        loginButton.setTranslateX(195);
        messageLabel.relocate(20, 285);
        
        this.setStyle("-fx-background-color: WHITE;");
        
        this.getChildren().addAll(new VBox(title, inputBox), messageLabel);
        
    }

/* *************************
          MUTATORS
 * *************************/    
    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

/* ***********************
         ACCESSORS
 * ***********************/    
    public Stage getCurrentStage() {
        return currentStage;
    }
    
       
    // For Debugging:
    private void print(String str) {
        System.out.println(str);
    }
    
    
}
