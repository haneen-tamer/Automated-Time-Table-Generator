/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import UseCases.CourseFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author dell
 */


public class CourseformController implements Initializable {

    @FXML
    public Label ID;
    public Label Name;
    public Label NO_;
    public Label Teacher;
   public Label Room;
     public Label Type;
   public TextField t1;
   public TextField t2;
     public TextField t3;
   public Button B1;
  public  Button B2;
   public RadioButton R1;
   public RadioButton R2;
   public ComboBox C1;
    public ComboBox C2;
   private String id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id=CourseFactory.getNextID();
        t1=new TextField();
        t1.setText(id);
    }    
    
}
