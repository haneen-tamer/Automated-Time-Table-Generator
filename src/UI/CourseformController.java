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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
   ArrayList<Teacher>a;
   ArrayList<String>s1;
   ArrayList<Room>r;
   ArrayList<String>s2;
   private String id;
   Session s;
   Courses c;
    @FXML
    private Label No_;
    @FXML
    private ComboBox<?> C1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id=CourseFactory.getNextID();
        t3.setText(id);
        t3.setDisable(false);
        get_teachers();
        //c1.setItems((ObservableList<String>) s);
        get_rooms();
        //C2.setItems((ObservableList<String>)s2);
    }   
    @FXML
    public void ADD_session(ActionEvent e) throws Exception
    {
        Teacher teacher;
        Room room;
        //c1.getValue()==null
        //C2.getValue()==null
        try
        {   
        if(t2.getText().equals(" ")||t1.getText().equals(" "))
        {
            
            throw new Exception();
            
        }
        //else if(String.valueOf(t2.getText())instanceof String)
        //{
            //throw new NumberFormatException ();
        //}
        
                
        if(R1.isSelected()==true)
        {
            s=new Lecture();
            s.setCourseTitle(t2.getText());
            teacher=get_Teacher(a,c1.getValue());
            s.setTeacher(teacher);
            room=get_Room(r,C2.getValue());
            s.setRoom(room);
        }
        else if(R2.isSelected()==true)
        {
            s=new Section();
            s.setCourseTitle(t2.getText());
            teacher=get_Teacher(a,c1.getValue());
            s.setTeacher(teacher);
            room=get_Room(r,C2.getValue());
            s.setRoom(room);
            
        }
        
        c.setName(t2.getText());
        c.AddSession(s);
        int n=Integer.parseInt(t1.getText());
        c.set_number_of_students(n);
        }
        catch(Exception ee)
        {
         JOptionPane.showMessageDialog(null,"You must enter All Required Data ","Warning",JOptionPane.WARNING_MESSAGE);
         //c1.getSelectionModel().clearSelection();
         //C2.getSelectionModel().clearSelection();
         R1.setSelected(false);
         R2.setSelected(false);
         t2.clear();
         t1.clear();
         return;
        }
    }
    @FXML
     public void Add_course(ActionEvent ee)
    {
        CourseFactory.addCourse(c);
         JOptionPane.showMessageDialog(null," succsseful added ","Succsseful",JOptionPane.OK_OPTION);
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
   
    
}
