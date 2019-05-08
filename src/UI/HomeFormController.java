/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import UseCases.FileManager_controller;
import UseCases.RoomFactory;

/**
 * FXML Controller class
 *
 * @author egypt
 */
public class HomeFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane pane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            FileManager_controller.ReadCourses();
            FileManager_controller.ReadRooms();
            FileManager_controller.ReadTeachers();
            if(RoomFactory.get_AllRooms().isEmpty())
            {
                AnchorPane room = FXMLLoader.load(getClass().getResource("roomForm.fxml"));
                pane.getChildren().setAll(room);
            }
        }
        catch(Exception ee)
        {
           System.out.println("ClassNotFound");
        }
       
       
    }    
    
    public void MakeSchedule() throws IOException
    {
        AnchorPane schedule = FXMLLoader.load(getClass().getResource("TimeTableScene.fxml"));
        pane.getChildren().setAll(schedule);
    }
     public void AddTeacher() throws IOException
    {
        AnchorPane teacher = FXMLLoader.load(getClass().getResource("Teacher.fxml"));
        pane.getChildren().setAll(teacher);
    }
      public void AddCourse() throws IOException
    {
        AnchorPane course = FXMLLoader.load(getClass().getResource("Courseform.fxml"));
        pane.getChildren().setAll(course);
    }
    public void AddRoom() throws IOException
    {
       AnchorPane room = FXMLLoader.load(getClass().getResource("roomForm.fxml"));
        pane.getChildren().setAll(room);
    }
}
