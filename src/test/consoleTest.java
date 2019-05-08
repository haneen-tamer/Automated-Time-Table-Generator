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
    public static void main(String [] args) throws Exception{
        //if files are empty
        Scanner input = new Scanner(System.in);
        if(true){
            ReadRooms(input);
            ReadTeachers(input);
            ReadCourses(input);
            
            ScheduleDialog(input);
        }else{
            if(FileManager_controller.ReadRooms())
                printFilereadingStatus("");
            else printFilereadingStatus(" not ");
            if(FileManager_controller.ReadTeachers())
                    printFilereadingStatus("");
            else printFilereadingStatus(" not ");
            if(FileManager_controller.ReadCourses())
                    printFilereadingStatus("");
            else printFilereadingStatus(" not ");
            
            ScheduleDialog(input);
        
    }
        
    }
    public static void ScheduleDialog(Scanner input)
    {
        ArrayList<String> days = new ArrayList<>(7);
        System.out.println("Enter the Days you want :");
        String d = input.next();
        while(!d.contains("end")){
            days.add(d);
            d=input.next();
        }
        System.out.println("Enter Start Time :");
        int Start=input.nextInt();
        System.out.println("Enter End Time :");
        int end=input.nextInt();
        
        String [] daysArr = new String[days.size()];
        days.toArray(daysArr);
        TimeTable t = ScheduleFactory.generateSchedule(daysArr, Start, end);
        traverseTimeTable(t);
    }
    
    public static void printFilereadingStatus(String status){
        System.out.println("File Reading was "+status+" succesful!");
    }
    
    public static void ReadRooms(Scanner input){
        System.out.print("Enter Number of Rooms: ");
            int numRooms = input.nextInt();
            for(int i=0; i<numRooms; i++)
                ReadSingleRoom(input);
            FileManager_controller.SaveRooms(RoomFactory.get_AllRooms());
    }
    public static void ReadSingleRoom(Scanner input){
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
    
    public static void ReadTeachers(Scanner input){
        System.out.print("Enter Number of Teachers: ");
            int numTeachers = input.nextInt();
            for(int i=0; i<numTeachers; i++)
                ReadSingleTeacher(input);
            FileManager_controller.SaveTeachers(TeacherFactory.getAllTeachers());
    }
    public static void ReadSingleTeacher(Scanner input){
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
        
        System.out.print("Enter your postion (Prof/TA)");
        String pos=input.next();
        Teacher t;
        if(pos.equals("Prof")){
            t=TeacherFactory.makeProf(ID);
            t.setE_Mail(email);
        t.setName(name);
        t.setPhone(phone);
        }
        else if(pos.equals("TA")){
            t=TeacherFactory.makeTA(ID);
            t.setE_Mail(email);
        t.setName(name);
        t.setPhone(phone);
        }
        else{
            System.out.println("Invalid Type !!");
        }
        
          
    }
    
    public static void ReadCourses(Scanner input){
        System.out.print("Enter Number of Courses: ");
            int numCourse= input.nextInt();
            for(int i=0; i<numCourse; i++)
                ReadSingleCourse(input);
            FileManager_controller.SaveCourses(CourseFactory.getAllCourses());
    }
    public static void ReadSingleCourse(Scanner input){
        System.out.println("Enter Courses Data: ");
        System.out.print("Course ID: ");
        String ID=CourseFactory.getNextID();
        System.out.println(ID);
        
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
            System.out.print("Enter Teacher ID:");
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
        String p = s.getCourseTitle()+" "+s.getTeacher();
            System.out.print(p+"\t");
        }
    public static void traverseTimeTable(TimeTable t){
        RoomFactory.get_AllRooms().stream().forEach((r) -> {
            System.out.print(r.getName()+"\t");
            String day, before="";
            for(Session s: t.getRoomSchedule(r)){
                day = s.getDay();
                if(!day.equals(before)){
                    System.out.print(day+"\t");
                    before=day;
                }
                displaySession(s);
                
            };
            
        });
    }
}
