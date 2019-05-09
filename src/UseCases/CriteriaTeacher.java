/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UseCases;

/**
 *
 * @author Aya
 */
import Model.Courses;
import Model.TimeTable;
import Model.Session;
import Model.Teacher;
import java.util.ArrayList;

public class CriteriaTeacher implements Criteria {
    
    
   public static TimeTable meetsCriteria(ArrayList<Courses> course,Teacher T,TimeTable old  ){
          TimeTable S=new TimeTable(old.getDaysStrings(),RoomFactory.get_AllRooms(),old.getStartTime(),old.getEndTime());
         
          
          ArrayList <Session> session=new ArrayList<>();
          for(Courses c: course){
            
          for(Session s: c.GetSessions())
              
               if(s.getTeacher().getID().equals(T.getID())){
                   
                   session.add(s);
               }
          
           }
          S.insertSessionsAt(session);
          return S;
       }
      
   }
    

