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
      Schedule filter = new  Schedule(old.getDaysStrings(), RoomFactory.get_AllRooms(), old.getStartTime(), old.getEndTime());
      filter.insertSessionsAt(co);
      
      return filter;
    }

}
