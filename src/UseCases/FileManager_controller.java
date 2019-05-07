/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UseCases;

import Model.Courses;
import Model.Room;
import Model.Teacher;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 *
 * @author dell
 */
public class FileManager_controller {
   private static final String Teachers_File="Teachers.txt";
   private static final String Courses_File="Courses.txt";
   private static final String Rooms_File="Rooms.txt";
    /*......................................................*/
     public static boolean SaveTeachers(ArrayList<Teacher> At)
    {
        
       try{
        FileOutputStream fos=new FileOutputStream(Teachers_File);
        BufferedOutputStream bos=new BufferedOutputStream(fos);
        ObjectOutputStream os=new ObjectOutputStream(bos);
        os.writeObject(At);
        os.close();
        
        return true;
        }
        catch(IOException e)
        {
            return false;
        }
        
    }
      public static boolean SaveCourses(ArrayList<Courses> Ac)
    {
        
       try{
        FileOutputStream fos=new FileOutputStream(Courses_File);
        BufferedOutputStream bos=new BufferedOutputStream(fos);
        ObjectOutputStream os=new ObjectOutputStream(bos);
        os.writeObject(Ac);
        os.close();
        
        return true;
        }
        catch(IOException e)
        {
            return false;
        }
    }
      public static boolean SaveRooms(ArrayList<Room> Ar)
    {
        
        try{
        FileOutputStream fos=new FileOutputStream(Rooms_File);
        BufferedOutputStream bos=new BufferedOutputStream(fos);
        ObjectOutputStream os=new ObjectOutputStream(bos);
        os.writeObject(Ar);
        os.close();
        
        return true;
        }
        catch(IOException e)
        {
            return false;
        }
    }
    public static boolean ReadTeachers() throws ClassNotFoundException
    {
        
        /*.........................................*/
        try{
        ArrayList<Teacher> At=new ArrayList<Teacher>();
        FileInputStream fis=new FileInputStream(Teachers_File);
        BufferedInputStream bis=new BufferedInputStream(fis);
        ObjectInputStream is=new ObjectInputStream(bis);
        At =(ArrayList<Teacher>)is.readObject();
        is.close();
        for(int i=0;i<At.size();i++)
        {
            TeacherFactory.addTeacher(At.get(i));
        }
        return true;
        }
        catch(IOException e)
        {
            System.out.println("FileNotFound");
            return false;
        }
        
    }
    public static boolean ReadCourses() throws ClassNotFoundException
    {
        
        /*.........................................*/
        try{
        ArrayList<Courses> Ac=new ArrayList<Courses>();
        FileInputStream fis=new FileInputStream(Courses_File);
        BufferedInputStream bis=new BufferedInputStream(fis);
        ObjectInputStream is=new ObjectInputStream(bis);
        
        Ac =(ArrayList<Courses>)is.readObject();
        is.close();
        for(int i=0;i<Ac.size();i++)
        {
            CourseFactory.addCourse(Ac.get(i));
        }
        return true;
        }
        catch(IOException e)
        {
            System.out.println("FileNotFound");
            return false;
        }
        
    }
     public static boolean ReadRooms() throws ClassNotFoundException
    {
        
        /*.........................................*/
        try{
        ArrayList<Room> Ar;
        FileInputStream fis=new FileInputStream(Rooms_File);
        BufferedInputStream bis=new BufferedInputStream(fis);
        ObjectInputStream is=new ObjectInputStream(bis);
        
        Ar =(ArrayList<Room>) is.readObject();
        is.close();
        for(int i=0;i<Ar.size();i++)
        {
           RoomFactory.Add_Room(Ar.get(i));
        }
        return true;
        }
        catch(IOException e)
        {
            System.out.println("FileNotFound");
            return false;
        }
        
    }

}
