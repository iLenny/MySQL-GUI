package mySQLUI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

/**
 *
 * @author Leibnix
 */
public class ViewTablesPane extends Pane {
    
    public ViewTablesPane(Connection conn) {
        ObservableList<String> tables  = FXCollections.observableArrayList();
        ListView<String> list = new ListView<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet result = stm.executeQuery("SHOW TABLES");
            while(result.next()) {
                tables.add(result.getString(1));
            }     
            
        } catch (SQLException ex) {
           
        }
        
        list.setItems(tables);
        this.getChildren().add(list);
        
    }
}
