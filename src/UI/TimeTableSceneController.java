/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.TimeTable;
import UseCases.RoomOverlapException;
import UseCases.ScheduleFactory;
import UseCases.TeacherOverlapException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Haneen
 */
public class TimeTableSceneController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField startTxtBox;
    @FXML
    private TextField endTextBox;
    @FXML
    private ScrollPane daysScrollPane;
    @FXML
    private ScrollPane TimeTableScrollPane;
    @FXML
    private Pane TimeTableData;
    private FlowPane daysPane;
    public HashSet<String> checkedDays;
    private ArrayList<CheckBox> daysCheckBoxes;
    private TimeTable generatedTimeTable;
    
    //Filter
    @FXML
    private Pane filter;
    @FXML
    private ChoiceBox FilterRoom;
    @FXML
    private ChoiceBox FilterTeacher;
    @FXML
    private ChoiceBox FilterCourse;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        checkedDays = new HashSet<>();
        daysCheckBoxes = new ArrayList<>();
        daysPane = new FlowPane();
        daysPane.setOrientation(Orientation.VERTICAL);
        for(TimeTablePresenter.Day d: TimeTablePresenter.Day.values()){
            CheckBox c = new CheckBox(d.toString());
            daysCheckBoxes.add(c);
            daysPane.getChildren().add(c);
        }
        
        daysScrollPane.setContent(daysPane);
    } 
    
    public void makeSchedule(){
        
        for(CheckBox c:daysCheckBoxes){
            if(c.isSelected())checkedDays.add(c.getText());
        }
        
        if(checkedDays.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING, "No days have been checked!");
            alert.show();
            return;
        }
        
        int start=0, end=0;
        try{
            start = Integer.valueOf(startTxtBox.getText());
            end = Integer.valueOf(endTextBox.getText());
            if (end<12&& start>=end)end+=12;
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Numbers only!");
            alert.show();
            return;
        }
        
        try{
            String [] arr = new String[checkedDays.size()];
            int i=0;
            for(String s: checkedDays){
                arr[i]=s;
                i++;
            }
            generatedTimeTable = ScheduleFactory.generateSchedule(
                    arr, start, end);

        }catch(RoomOverlapException re){
            String message = "Session "+ re.getSession().getCourseTitle()
                    + " can not fit in room "+re.getSession().getRoom().getName();
            Alert alert = new Alert(Alert.AlertType.WARNING, message);
            alert.show();
            return;
        }catch(TeacherOverlapException te){
            String message = "Teacher "+te.getSession().getTeacher().getName()+
                    " is not available to teach session "+ te.getSession().getCourseTitle();
            Alert alert = new Alert(Alert.AlertType.WARNING, message);
            alert.show();
            return;
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        
        TimeTablePresenter t = new TimeTablePresenter(generatedTimeTable);
        TimeTableScrollPane.setContent(t);
    }
    public void BackToHome() throws IOException
    {     
        AnchorPane home = FXMLLoader.load(getClass().getResource("HomeForm.fxml"));
        pane.getChildren().setAll(home);
    }
}
