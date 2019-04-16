/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UseCases;
import Model.Courses;
import Model.Schedule;
import Model.Session;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author egypt
 */
public class CriteriaCourse implements Criteria {

    public Schedule meetsCriteria(Courses c ,Schedule old) {
      
      ArrayList<Session> co = c.GetSessions();
<<<<<<< HEAD
      Schedule filter = Schedule(old.getDaysStrings(), RoomFactory.getAllRooms(), old.getStartTime(), old.getEndTime());
=======
      Schedule filter = new  Schedule(old.getDaysStrings(), RoomFactory.get_AllRooms(), old.getStartTime(), old.getEndTime());
>>>>>>> 30ae1058e386224131bb80e4fa1ba9cf3f69ea57
      filter.insertSessionsAt(co);
      
      return filter;
    }

}
