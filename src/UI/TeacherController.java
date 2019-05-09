package UI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Teacher;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import UseCases.*;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javax.swing.JOptionPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author Aya
 */
 
public class TeacherController implements Initializable {
    
   @FXML
   private AnchorPane pane;
    @FXML
   private Label IDField; 
    @FXML
   private TextField NameField;
    @FXML
   private TextField EmailField;
    @FXML
   private TextField PhoneField;
    @FXML
   private RadioButton ProfField;
   
    @FXML
   private RadioButton TAfield;
    @FXML
   private Button buttonField;
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        IDnum();
        
    }   
    class myException extends Exception{
       public myException(){
           super ();
       }
    }
   
    public void IDnum(){
        
     String I=""+TeacherFactory.getNextID();
     IDField.setText(I);
     
}
    @FXML
    public void Button(){
     
      String Id=" ";
      String name =" ";
      String Email=" ";
      String phone=" ";
      String Prof=" ";
      String TA=" ";
      
      
      
      try{
      
      name= NameField.getText();
      Email= EmailField.getText();
      phone= PhoneField.getText();
      Prof=ProfField.getText();
      TA=TAfield.getText();
      
       if(name.equals("")||phone.equals("")||Email.equals("")){
           throw new Exception();
       }
       
       
       
       
       if(Prof.equals("")&&TA.equals("")){
           throw new myException();
       }
       int p=Integer.parseInt(phone);
       
      }

       
      catch(myException ea){
          Alert a=new Alert(AlertType.WARNING);
           a.setTitle("Exception");
           a.setHeaderText("Failture");
           a.setContentText("Sorry, You Have to Choose one Button! ");
           a.showAndWait();

           return;
      }
      
    catch(NumberFormatException ex){
          Alert a=new Alert(AlertType.WARNING);
           a.setTitle("Exception");
           a.setHeaderText("Failture");
           a.setContentText("Sorry, You Have to Input Numbers only");
           a.showAndWait();

           
           return;
      }
      
       catch(Exception e){
           Alert a=new Alert(AlertType.WARNING);
           a.setTitle("Exception");
           a.setHeaderText("Failture");
           a.setContentText("Sorry, You Have to fill all TextFields ");
           a.showAndWait();

           return;
        
       }
      if(ProfField.isSelected()){
          Teacher T=TeacherFactory.makeProf(IDField.getText());
          T.setName(name);
          T.setPhone(phone);
          T.setE_Mail(Email);
          
      }
      else{
          Teacher T=TeacherFactory.makeTA(IDField.getText());
          T.setName(name);
          T.setPhone(phone);
          T.setE_Mail(Email);
      }
          IDnum();
         
           NameField.clear();
           PhoneField.clear();
           EmailField.clear();
           ProfField.setSelected(false);
           TAfield.setSelected(false);

}    
   @FXML
   public void BackToHome() throws IOException
    {     
        AnchorPane home = FXMLLoader.load(getClass().getResource("HomeForm.fxml"));
        pane.getChildren().setAll(home);
        FileManager_controller.SaveTeachers(TeacherFactory.getAllTeachers());
    }
       

}
