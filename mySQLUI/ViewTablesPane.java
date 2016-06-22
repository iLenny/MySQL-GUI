package mySQLUI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Leibniz H. Berihuete
 */
public class ViewTablesPane extends Pane {
    public ViewTablesPane(Connection conn) {
        // Create ObservableList of Strings--representing table items
        ObservableList<String> tableItems  = FXCollections.observableArrayList();
        
        // Get tableItems:
        try {
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("SHOW TABLES");
            while(result.next()) {
                tableItems.add(result.getString(1));
            }
            
            
        } catch (SQLException ex) {
           System.err.println("|FROM ViewTablesPane|: UNABLE TO OBTAIN DATA");
        }
        
        // ListView to be able to show the tableItems:
        ListView list = new ListView();
        list.setItems(tableItems);
        list.setPrefWidth(350);
        
        // Create a title:
        Label title = new Label("TABLES");
        title.setFont(new Font(30));
        title.setTranslateX(120);
        
        title.setTextFill(Color.WHITE);
        
        this.setStyle("-fx-background-color: LIGHTSLATEGREY");
        
        
        // Put it inside this container
        this.getChildren().add(new VBox(title, list));
        

        
    }
}
