/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UseCases;
import java.util.*;
import Model.*;
/**
 *
 * @author Haneen
 */
public class ScheduleFactory {
    public static TimeTable generateSchedule(String [] days, int start, int end){
        TimeTable schedule = new TimeTable(days, RoomFactory.get_AllRooms(), start, end);
        ArrayList<Courses> courses = CourseFactory.getAllCourses();
        for(Courses c: courses){
            for(Session s: c.GetSessions()){
                schedule.addSession(s);
            }
        }
        return schedule;
    }
    
    public static TimeTable getSchedule(ArrayList<Courses> c, TimeTable finished){
        TimeTable filtered = new TimeTable(finished.getDaysStrings(), RoomFactory.get_AllRooms(),
                finished.getStartTime(), finished.getEndTime());
        for(Courses crs : c){
                filtered.insertSessionsAt(crs.GetSessions());
        }
        return filtered;
    }
}
