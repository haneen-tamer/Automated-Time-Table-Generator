/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.Courses;
import Model.Room;
import UseCases.*;
import Model.Session;
import Model.Teacher;
import Model.TimeTable;
import UseCases.RoomFactory;
import UseCases.RoomOverlapException;
import UseCases.ScheduleFactory;
import UseCases.TeacherFactory;
import UseCases.TeacherOverlapException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
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

      //  filter.setVisible(false);

        

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
        
        //filterTeacher
         for( Teacher t:TeacherFactory.getAllTeachers()){
            this.FilterTeacher.getItems().add(t.getName()+"\t"+"("+t.getID()+")");
        }
         
         //RoomTeacher
         for(Room r:RoomFactory.get_AllRooms()){
            this.FilterRoom.getItems().add(r.getName());
        }
    } 
    
    @FXML
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
            ArrayList<String> arr = getChoiceBoxFillByRooms();
            String first = re.getSession().getRoom().getName();
            ChoiceDialog<String> dialog = new ChoiceDialog<>(first, arr);
            dialog.setTitle("Overlap found");
            dialog.setHeaderText(message);
            dialog.setContentText("Choose a Room:");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                String t = result.get();
                String ID = t.substring(t.indexOf('('), t.indexOf(')'));
                if(!re.getSession().setTeacher(TeacherFactory.getTeacher(ID))){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "This teacher is incompatible with this session!");
                    alert.show();
                    return;
                }
                System.out.println("Your choice: " + result.get());
            }
//            Alert alert = new Alert(Alert.AlertType.WARNING, message);
//            alert.show();
            return;
        }catch(TeacherOverlapException te){
            
            String message = "Teacher "+te.getSession().getTeacher().getName()+
                    " is not available to teach session "+ te.getSession().getCourseTitle();
            ArrayList<String> arr = fillChoiceBoxByTeachers();
            
            String first = te.getSession().getTeacher().getName()
                    +"\t"+"("+te.getSession().getTeacher().getID()+")";
            ChoiceDialog<String> dialog = new ChoiceDialog<>(first, arr);
            dialog.setTitle("Overlap found");
            dialog.setHeaderText(message);
            dialog.setContentText("Choose a teacher:");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                String t = result.get();
                String ID = t.substring(t.indexOf('(')+1, t.indexOf(')')-1);
                if(!te.getSession().setTeacher(TeacherFactory.getTeacher(ID))){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "This teacher is incompatible with this session!");
                    alert.show();
                    return;
                }
                System.out.println("Your choice: " + result.get());
            }
            
//            Alert alert = new Alert(Alert.AlertType.WARNING, message);
//            alert.show();
            return;
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        
        TimeTablePresenter t = new TimeTablePresenter(generatedTimeTable);
        TimeTableScrollPane.setContent(t);
        
        //show filter pane here
        TimeTableData = filter;
        TimeTableData.setVisible(true);
        FilterCourse.getItems().addAll(fillChoiceBoxByCourses());
        FilterTeacher.getItems().addAll(fillChoiceBoxByTeachers());
        FilterRoom.getItems().addAll(getChoiceBoxFillByRooms());
    }
    
    private ArrayList<String> getChoiceBoxFillByRooms(){
        ArrayList<String> arr = new ArrayList<>();
            for(Room r:RoomFactory.get_AllRooms()){
                arr.add(r.getName());
            }
        return arr;
    }
    
    private ArrayList<String> fillChoiceBoxByTeachers(){
        ArrayList<String> arr = new ArrayList<>();
        for( Teacher t:TeacherFactory.getAllTeachers()){
            arr.add(t.getName()+"\t"+"("+t.getID()+")");
        }
        return arr;
    }
    
    private ArrayList<String> fillChoiceBoxByCourses(){
        ArrayList<String> arr = new ArrayList<>();
        for( Courses c:CourseFactory.getAllCourses()){
            arr.add(c.getName()+"\t"+"("+c.getId()+")");
        }
        return arr;
    }
    
    @FXML
    public void filterByRoom(){
        Room r = RoomFactory.get_Room((String)FilterRoom.getValue());
        TimeTable filtered = UseCases.FilterRoom.meetsCriteria(generatedTimeTable, r);
        setTimeTableGrid(filtered);
    }
    @FXML
    public void filterByTeacher(){
        String choice =(String) FilterTeacher.getValue();
        String ID = choice.substring(choice.indexOf('(')+1, choice.indexOf(')')-1);
        Teacher t = TeacherFactory.getTeacher(ID);
        TimeTable filtered = CriteriaTeacher
                .meetsCriteria(CourseFactory.getAllCourses(),
                        t,
                        generatedTimeTable);
        setTimeTableGrid(filtered);
    }
    @FXML
    public void filterByCourse(){
        String choice =(String) FilterCourse.getValue();
        String ID = choice.substring(choice.indexOf('(')+1, choice.indexOf(')')-1);
        Courses c = CourseFactory.getCourse(ID);
        TimeTable filtered = CriteriaCourse
                .meetsCriteria(c, generatedTimeTable);
        setTimeTableGrid(filtered);
    }
    
    private void setTimeTableGrid(TimeTable tt){
        TimeTablePresenter t = new TimeTablePresenter(tt);
        TimeTableScrollPane.setContent(t);
    }

    @FXML
    public void BackToHome() throws IOException
    {     
        AnchorPane home = FXMLLoader.load(getClass().getResource("HomeForm.fxml"));
        pane.getChildren().setAll(home);
    }
}
