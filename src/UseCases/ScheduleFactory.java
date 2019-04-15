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
    public static Schedule generateSchedule(String [] days, int start, int end){
        Schedule schedule = new Schedule(days, RoomFactory.getAllRooms(), start, end);
        ArrayList<Courses> courses = CourseFactory.getAllCourses();
        for(Courses c: courses){
            for(Session s: c.GetSessions()){
                schedule.addSession(s);
            }
        }
        return schedule;
    }
    
    public static Schedule getSchedule(ArrayList<Courses> c, Schedule finished){
        Schedule filtered = new Schedule(finished.getDaysStrings(), RoomFactory.getAllRooms(),
                finished.getStartTime(), finished.getEndTime());
        for(Courses crs : c){
                filtered.insertSessionsAt(crs.GetSessions());
        }
        return filtered;
    }
}
