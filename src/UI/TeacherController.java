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
import javafx.event.ActionEvent;
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
   private TextField IDField; 
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
   @FXML
    private AnchorPane pane;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
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
     
      
       if(name.equals("")||phone.equals("")||Email.equals("")){
           throw new Exception();
       }
       
       if(String.valueOf(PhoneField.getText()) instanceof String ){
            throw new NumberFormatException();
       }
//           Teacher T;
//       if(ProfField.isSelected()){
//           T=TeacherFactory.makeProf(Id);
//           T.setName(name);
//           T.setE_Mail(Email);
//           T.setPhone(phone);
//       }
//       else {
//           T=TeacherFactory.makeTA(Id);
//           T.setName(name);
//           T.setE_Mail(Email);
//           T.setPhone(phone);
//       }
//      }
//      else
//           throw new NumberFormatException();
       
     }
      
    catch(NumberFormatException ex){
          Alert a=new Alert(AlertType.ERROR);
           a.setTitle("Exception");
           a.setHeaderText("Failture");
           a.setContentText("Sorry, You Have to Enter Numbers only  ");
           a.showAndWait();
           NameField.clear();
           PhoneField.clear();
           EmailField.clear();
           return;
      }
      
       catch(Exception e){
           Alert a=new Alert(AlertType.ERROR);
           a.setTitle("Exception");
           a.setHeaderText("Failture");
           a.setContentText("Sorry, You Have to Enter Data First! ");
           a.showAndWait();
           NameField.clear();
           PhoneField.clear();
           EmailField.clear();
           return;              
       }
      
      
//      NameField.clear();
//      PhoneField.clear();
//      EmailField.clear();
      
               
   
       /*Teacher T;
       if(ProfField.isSelected()){
           T=TeacherFactory.makeProf(Id);
           T.setName(name);
           T.setE_Mail(Email);
           T.setPhone(phone);
       }
       else {
           T=TeacherFactory.makeTA(Id);
           T.setName(name);
           T.setE_Mail(Email);
           T.setPhone(phone);
       }*/
       
} 
    public void BackToHome() throws IOException
    {
        AnchorPane home = FXMLLoader.load(getClass().getResource("HomeForm.fxml"));
        pane.getChildren().setAll(home);
    }

       
//       int phone=Integer.parseInt(PhoneField.getText());
//      
//       try{
//           if(ProfField.isSelected()){
//             Teacher T= TeacherFactory.makeProf(IDField.getText());
//             T.setName(NameField);
//             T.setE_Mail(EmailField);
//            }
//            
//            else{
//                
//              Teacher T=TeacherFactory.makeTA(IDField.getText());
//            }
//           if(NameField.getText().equals(" ")||PhoneField.getText().equals(" ")||EmailField.getText().equals(" ")){
//               throw new Exception();
//           }
//            
//        }
//       catch(Exception e){
//           
//              Alert a=new Alert(AlertType.WARNING);
//              a.setTitle("Exception");
//              a.setHeaderText("Failture");
//              a.setContentText("Sorry, You Have to Enter Data First! ");
//              a.showAndWait();
//           return ;
//           
//       }
//       
//       try{
//           if(!ProfField.isSelected() && !TAfield.isSelected()){
//            throw new Exception();
//             
//           }
//       }
//       catch(Exception e){
//           Alert a=new Alert(AlertType.WARNING);
//              a.setTitle("Exception");
//              a.setHeaderText("Failture");
//              a.setContentText("Sorry, You Have to choose one Button ! ");
//              a.showAndWait();
//              return;
//       }
//       
//       try {
//        Integer.parseInt(PhoneField.getText());
//            
//       }
//       catch (Exception e ){
//               Alert a=new Alert(AlertType.ERROR);
//               a.setTitle("Error  ");
//               a.setHeaderText("Failture");
//               a.setContentText("Sorry, You Have to Add Digits only ! ");
//               a.showAndWait();
//               PhoneField.setText(" ");
//               return;
//           }
//       
//       
////       NameField.setText(" ");
////       PhoneField.setText(" ");
////       EmailField.setText(" ");
//       
//      
//        
//    }


 
}
