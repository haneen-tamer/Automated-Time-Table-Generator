/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.*;
/**
 *
 * @author Haneen
 */
public class TimeTable {
    private HashMap<String, ScheduleDay> week;
    
    public TimeTable(String [] days, ArrayList<Room> Rooms, int start, int end){
        week = new HashMap<>(days.length);
        for (String day : days) {
            week.put(day, new ScheduleDay(day, Rooms, start, end));
        }
    }
    
    public int addSession(Session s){
        int state = ScheduleDay.ROOM_OVERLAP;
        
        ArrayList<ScheduleDay> Days= (ArrayList) week.values();
        for(ScheduleDay current: Days){
            state = current.addSession(s);
            if(state == ScheduleDay.NO_OVERLAP) return state;
        }
        
        return state;
    }
    
    public void insertSessionsAt(ArrayList<Session> sessions){
        for(Session s: sessions){
            week.get(s.getDay()).insertSessionAt(s);
        }
    }
    
    public int getStartTime(){
        ArrayList<ScheduleDay> arr= (ArrayList) week.values();
        for(ScheduleDay d: arr){
            return d.getStartTime();
        }
        return 0;
    }
    
    public int getEndTime(){
        ArrayList<ScheduleDay> arr= (ArrayList) week.values();
        for(ScheduleDay d: arr){
            return d.getEndTime();
        }
        return 0;
    }
    
    public String [] getDaysStrings(){
        return (String []) week.entrySet().toArray();
    }
    public ArrayList<Session> getRoomSchedule(Room r){
        ArrayList<Session> arr = new ArrayList<>();
        ArrayList<ScheduleDay> days = (ArrayList) week.values();
        for(ScheduleDay d: days){
            ArrayList<Session> arrSessions =d.getRoomSchedule(r);
            for(Session s: arrSessions)
                arr.add(s);
        }
        return arr;
    }
    
}
