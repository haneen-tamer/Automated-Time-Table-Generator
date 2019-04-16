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
import Model.Schedule;
import Model.Session;
import Model.Teacher;
import java.util.ArrayList;

public class CriteriaTeacher implements Criteria {
    
    
   public Schedule meetsCriteria(ArrayList<Courses> course,Teacher T,Schedule old  ){
          Schedule S=new Schedule(old.getDaysStrings(),RoomFactory.get_AllRooms(),old.getStartTime(),old.getEndTime());
         
          
          ArrayList <Session> session=new ArrayList<>();
          for(Courses c: course){
            
          for(Session s: c.GetSessions())
              
               if(T==s.getTeacher()){
                   
                   session.add(s);
               }
          
           }
          S.insertSessionsAt(session);
          return S;
       }
      
   }
    

