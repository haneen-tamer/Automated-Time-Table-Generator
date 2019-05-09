/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.Courses;
import Model.Lecture;
import Model.Room;
import Model.Section;
import Model.Session;
import Model.Teacher;
import UseCases.CourseFactory;
import UseCases.RoomFactory;
import UseCases.TeacherFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dell
 */


public class CourseformController implements Initializable {

    @FXML
    public Label ID;
    @FXML
    public Label Name;
    public Label NO_;
    @FXML
    public Label Teacher;
    @FXML
   public Label Room;
    @FXML
     public Label Type;
    @FXML
    public Label Duration;
    @FXML
    public TextField t4;
    @FXML
   public TextField t3;
    @FXML
   public TextField t1;
    @FXML
     public TextField t2;
    @FXML
   public Button B1;
    @FXML
  public  Button B2;
    @FXML
   public RadioButton R1;
    @FXML
   public RadioButton R2;
   public ComboBox<String>c1;
    @FXML
   public ComboBox <String> C2;
    @FXML
   private AnchorPane pane;
   ArrayList<Teacher>a;
   ArrayList<String>s1;
   ArrayList<Room>r;
   ArrayList<String>s2;
   private String id;
   Session s;
   Courses c;
   int number_of_Students=0;
   String Course_name="";
   int Duration_=0;
   String Teacher_="";
   String Room_="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id=CourseFactory.getNextID();
        t3.setText(id);
        t3.setDisable(false);
        c = new Courses();
        c.setId(id);
        
        get_teachers();
        c1.getItems().addAll(fillChoiceBoxByTeachers());
        get_rooms();
        C2.getItems().addAll(getChoiceBoxFillByRooms());
        
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
    @FXML
    public void ADD_session(ActionEvent e) throws Exception
    {
        Teacher teacher;
        Room room;
        
        try
        {
            
        number_of_Students=Integer.valueOf(t1.getText());
        Course_name=t2.getText();
        Duration_=Integer.valueOf(Duration.getText());
        Teacher_=c1.getValue();
        Room_=C2.getValue();
        if(Course_name.equals("")||Teacher.equals("")||Room_.equals("")||number_of_Students==0||(R1.isSelected()==false&&R2.isSelected()==false)||Duration_==0)
        {
            
            throw new Exception();
            
        }
        if(R1.isSelected()==true)
        {
            s=new Lecture();
            s.setCourseTitle(Course_name);
            s.setDuration(Duration_);
            teacher=get_Teacher(a,Teacher_);
            s.setTeacher(teacher);
            room=get_Room(r,Room_);
            s.setRoom(room);
        }
        else if(R2.isSelected()==true)
        {
            s=new Section();
            s.setCourseTitle(Course_name);
            s.setDuration(Duration_);
            teacher=get_Teacher(a,Teacher_);
            s.setTeacher(teacher);
            room=get_Room(r,Room_);
            s.setRoom(room);
            
        }
        
        c.setName(Course_name);
        c.AddSession(s);
        c.set_number_of_students(number_of_Students);
        }
    
       catch(NumberFormatException ne)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "You must enter number!");
                    alert.show();
//        JOptionPane.showMessageDialog(null,"You must enter number","Warning",JOptionPane.WARNING_MESSAGE);
         
         return;
        }
        catch(Exception ee)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "You must enter All Required Data !");
                    alert.show();
//         JOptionPane.showMessageDialog(null,"You must enter All Required Data ","Warning",JOptionPane.WARNING_MESSAGE);
         
         return;
        }
        c1.getSelectionModel().clearSelection();
         C2.getSelectionModel().clearSelection();
         R1.setSelected(false);
         R2.setSelected(false);
         t2.clear();
         t1.clear();
         t4.clear();
         
    }
    @FXML
     public void Add_course(ActionEvent ee)
    {
        CourseFactory.addCourse(c);
        c = new Courses();
        id=CourseFactory.getNextID();
        t3.setText(id);
        c.setId(id);
        //JOptionPane.showMessageDialog(null," succsseful added ","Succsseful",JOptionPane.OK_OPTION);
    }
    public void get_teachers()
    {
        a=new ArrayList();
        s1=new ArrayList();
        a=TeacherFactory.getAllTeachers();
        for(int i=0;i<a.size();i++)
        {
            s1.add(i, a.get(i).getName());
        }
    }
    public void get_rooms()
    {
        r=RoomFactory.get_AllRooms();
        s2=new ArrayList();
        for(int i=0;i<r.size();i++)
        {
            s2.add(i, r.get(i).getName());
        }
    }
    public Teacher get_Teacher(ArrayList<Teacher> a,String Value)
    {
        int i;
        for( i=0;i<a.size();i++)
        {
            if(a.get(i).getName().equals(Value)==true)
            {
                break;
            }
        }
        return a.get(i);
    }
    public Room get_Room(ArrayList<Room> r,String Value)
    {
        int i;
        for( i=0;i<r.size();i++)
        {
            if(r.get(i).getName().equals(Value)==true)
            {
                break;
            }
        }
        return r.get(i);
    }
    @FXML
   public void BackToHome() throws IOException
    {     
        AnchorPane home = FXMLLoader.load(getClass().getResource("HomeForm.fxml"));
        pane.getChildren().setAll(home);
    }
    
}
