/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import Model.*;
import UseCases.*;
import java.util.*;
/**
 *
 * @author Haneen
 */
public class consoleTest {
    public static void main(String [] args){
        //if files are empty
        //HashMap<Room, Room> jk = HashMap<>();
        //System.out.print(jk.size());
        
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Number of Teachers: ");
        int numTeachers = input.nextInt();
        for(int i=0; i<numTeachers; i++)
            ReadTeacher(input);
        
        System.out.print("Enter Number of Rooms: ");
        int numRooms = input.nextInt();
        for(int i=0; i<numRooms; i++)
            ReadRoom(input);
        
        
        
        
        System.out.print("Enter Number of Courses: ");
        int numCourse= input.nextInt();
        for(int i=0; i<numCourse; i++)
            ReadCourses(input);
        
        String [] days = new String[2];
        System.out.println("Enter the Days you want :");
        for(int i=0;i<days.length;i++){
            days[i]=input.next();
        }
        System.out.println("Enter Start Time :");
        int Start=input.nextInt();
        System.out.println("Enter End Time :");
        int end=input.nextInt();
        
        TimeTable t =ScheduleFactory.generateSchedule(days, Start, end);
        for(Room r: RoomFactory.get_AllRooms()){
            for(Session s: t.getRoomSchedule(r)){
                displaySession(s);
            }
        }
    }
    
    public static void ReadRoom(Scanner input){
        System.out.println("Enter Room Data: ");
        System.out.print("Enter Room Name: ");
        String name = input.next();
        System.out.print("Enter Room Capacity: ");
        int Capacity = input.nextInt();
        System.out.print("Enter Room Type:(L/C) ");
        String type = input.next();
        if(type.equals("C")){
            Room r =RoomFactory.Make_Class(name, Capacity);
        }
        else if(type.equals("L")){
            Room r=RoomFactory.Make_Lab(name, Capacity);
        }else{
            System.out.println("Invalid character! ");
        }
    }
    
    public static void ReadTeacher(Scanner input){
        System.out.println("Enter Teacher Data: ");
        System.out.print("Teacher ID: ");
        String ID=TeacherFactory.getNextID();
        System.out.print(ID);
        
        System.out.print("Enter Teacher Name: ");
        String name = input.next();
        System.out.print("Enter Teacher Email: ");
        String email = input.next();
        System.out.print("Enter Teacher Phone:(L/C) ");
        String phone = input.next();
        System.out.print("Enter Number of courses:");
        int num_courses= input.nextInt();
        System.out.print("Enter your postion (Prof/TA)");
        String pos=input.next();
        Teacher t;
        if(pos.equals("Prof")){
            t=TeacherFactory.makeProf(ID);
        }
        else if(pos.equals("TA")){
            t=TeacherFactory.makeTA(ID);
        }
        else{
            System.out.println("Invalid Type !!");
        }
        
          
    }
    public static void ReadCourses(Scanner input){
        System.out.println("Enter Courses Data: ");
        System.out.print("Course ID: ");
        String ID=CourseFactory.getNextID();
        System.out.print(ID);
        
        System.out.print("Enter Course Name: ");
        String name =input.next();
        System.out.print("Enter Number of Students :");
        int num=input.nextInt();
        System.out.print("Enter Number of Sessions ");
        int sess=input.nextInt();
        Courses c=CourseFactory.getCourse(ID);
        c.setName(name);
        for(int i=0;i<sess;i++){
            System.out.println("Enter Duration ");
            int dur=input.nextInt();
            System.out.print("Enter Room Name :");
            String room_name=input.next();
            Room room=RoomFactory.get_Room(room_name);
            System.out.print("Enter Teacher Name:");
            String Teacher_name=input.next();
            Teacher teacher=TeacherFactory.getTeacher(Teacher_name);
            System.out.print("Enter Room (L/S):");
            String type=input.next();
            Session s;
            if(type.equals("L")){
                s=new Lecture();
                s.setDuration(dur);
                s.setRoom(room);
                s.setTeacher(teacher);
                c.AddSession(s);
            }
            else if(type.equals("S")){
                s=new Section();
                s.setDuration(dur);
                s.setRoom(room);
                s.setTeacher(teacher);
                c.AddSession(s);
            }
            else{
                System.out.println("Invalid character !");
            }
            
            
        }
        
    }
    
    public static void displaySession(Session s){
            System.out.print(s);
        }
}
