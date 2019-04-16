/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UseCases;

import Model.Courses;
import Model.Room;
import Model.Teacher;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class FileManager_controller {
   private String Teachers_File;
   private String Courses_File;
   private String Rooms_File;
    /*......................................................*/
     public boolean SaveTeachers(ArrayList<Teacher> At)
    {
        
        if(FileManger.SaveToFile(Teachers_File, At)==true)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
      public boolean SaveCourses(ArrayList<Courses> Ac)
    {
        
        if(FileManger.SaveToFile(Courses_File, Ac)==true)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
      public boolean SaveRooms(ArrayList<Room> Ar)
    {
        
        if(FileManger.SaveToFile(Rooms_File, Ar)==true)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
}
