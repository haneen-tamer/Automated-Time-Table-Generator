/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import UseCases.RoomFactory;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author egypt
 */
public class RoomFormController implements Initializable {
    @FXML
     private TextField RoomName;
    @FXML
     private TextField Capacity;
    @FXML
    private RadioButton lecture;
    @FXML
    private RadioButton lab;
     @FXML
    private AnchorPane pane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {              
    }    
    
    private static boolean NumberOrNot(String text) {
         try
        {
            Integer.parseInt(text);
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        return true;    
     }
     
    @FXML
    public void check()
    {
       
       if(RoomName.getText().equals("") || Capacity.getText().equals(""))
         {
          Alert alert = new Alert(AlertType.WARNING);
          alert.setTitle("Exception");
          alert.setHeaderText(null);
          alert.setContentText("You have to fill the TextField..");
          alert.showAndWait();
         }
       
         if(!lecture.isSelected() && !lab.isSelected())
         {
          Alert alert = new Alert(AlertType.WARNING);
          alert.setTitle("Exception");
          alert.setHeaderText(null);
          alert.setContentText("You have to choose one of Room's type..");
          alert.showAndWait();
         }
        
         
       String name = RoomName.getText();
       int cap = Integer.valueOf(Capacity.getText());
       if(!NumberOrNot(Capacity.getText()))
      {
          Alert alert = new Alert(AlertType.WARNING);
          alert.setTitle("Exception");
          alert.setHeaderText(null);
          alert.setContentText("You can only input Numbers..");
          alert.showAndWait();
      }   
       if(lecture.isSelected())
      {
           RoomFactory.Make_Class(name, cap);
           RoomName.setText(" ");
           Capacity.setText(" ");
           lecture.selectedProperty().setValue(false);

      }
       else if(lab.isSelected())
       {
           RoomFactory.Make_Lab(name, cap);
           RoomName.setText(" ");
           Capacity.setText(" ");
           lab.selectedProperty().setValue(false);
       }         
}
    public void BackToHome() throws IOException
    {
        AnchorPane home = FXMLLoader.load(getClass().getResource("HomeForm.fxml"));
        pane.getChildren().setAll(home);
    }
}
