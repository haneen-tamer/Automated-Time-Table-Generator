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
import UseCases.FileManager_controller;

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
    
 /*   private static boolean NumberOrNot(String text) {
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
     */
    @FXML
    public void check()
    {
       String name = "";
       int cap = 0;
       try
       {
           name = RoomName.getText();
           
           if(name.equals("")||Capacity.getText().equals("")||(!lecture.isSelected() && !lab.isSelected()))
           throw new Exception();
            
        cap = Integer.valueOf(Capacity.getText());
        
         if(!RoomFactory.isUniqueName(name))
        {
            Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Exception");
           alert.setHeaderText("Failture");
           alert.setContentText("This Name is Used !");
           alert.showAndWait();
           return;
        }
       
            if(lecture.isSelected())
        {
           RoomFactory.Make_Class(name,cap);
           RoomName.clear();
           Capacity.clear();
           lecture.selectedProperty().setValue(false);
        }
            
       else if(lab.isSelected())
       {
           RoomFactory.Make_Lab(name, cap);
           RoomName.clear();
           Capacity.clear();
           lab.selectedProperty().setValue(false);
       } 
       }
       catch(NumberFormatException ex){
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Exception");
           alert.setHeaderText("Failture");
           alert.setContentText("Sorry, You Have to Enter Numbers only! ");
           alert.showAndWait();
           return;
       }
       catch(Exception e){
           Alert a=new Alert(AlertType.ERROR);
           a.setTitle("Exception");
           a.setHeaderText("Failture");
           a.setContentText("Sorry, You Have to Enter Data First! ");
           a.showAndWait();
           return;
       }
    }
    /*   if(RoomName.getText().equals("") || Capacity.getText().equals(""))
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
     */  
         
    public void BackToHome() throws IOException
    {
        
        AnchorPane home = FXMLLoader.load(getClass().getResource("HomeForm.fxml"));
        pane.getChildren().setAll(home);
        FileManager_controller.SaveRooms(RoomFactory.get_AllRooms());
    }
}
