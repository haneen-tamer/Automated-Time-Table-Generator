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
public class Schedule {
    private HashSet<Day> week;
    
    public Schedule(String [] days, ArrayList<Room> Rooms, int start, int end){
        week = new HashSet<>(days.length);
        for (String day : days) {
            week.add(new Day(day, Rooms, start, end));
        }
    }
    
    public int addSession(Session s){
        int state = Day.ROOM_OVERLAP;
        
        Iterator it = week.iterator();
        while(it.hasNext()){
            Day current = (Day) it.next();
            state = current.addSession(s);
            if(state == Day.NO_OVERLAP) return state;
        }
        
        return state;
    }
    
}
