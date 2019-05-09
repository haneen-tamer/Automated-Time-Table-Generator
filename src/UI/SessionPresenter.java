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
import javafx.scene.control.Label;
import Model.Session;
import javafx.geometry.Orientation;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;


/**
 * FXML Controller class
 *
 * @author Haneen
 */
public class SessionPresenter extends FlowPane {
   
   private Label CourseTitle;
   
   private Label TeacherName;
   
   private Session session;
   
   public SessionPresenter(Session s){
       this.setOrientation(Orientation.VERTICAL);
       this.setStyle("-fx-border-color: black;");
       session=s;
       CourseTitle = new Label(session.getCourseTitle());
       this.TeacherName=new Label(" / " + session.getTeacher().getName());
       this.getChildren().add(CourseTitle);
       this.getChildren().add(TeacherName);
   }
    
}
