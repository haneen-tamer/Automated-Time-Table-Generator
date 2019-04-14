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
public class Day {
    private static class SessionComparator implements Comparator<Session>{

        @Override
        public int compare(Session s1, Session s2){
            return s2.getStartTime()-s1.getStartTime();
        }
    };
    
    public static final String Sat = "Saturday";
    public static final String Sun = "Sunday";
    public static final String Mon = "Monday";
    public static final String Tues = "Tuesday";
    public static final String Wed = "Wednesday";
    public static final String Thurs = "Thurs";
    public static final String Fri = "Friday";
    
    public static final int START_TIME=8;
    public static final int END_TIME=20;
    
    public static final int ROOM_OVERLAP=2;
    public static final int TEACHER_OVERLAP=1;
    public static final int NO_OVERLAP=0;
    
    private HashMap<Room, PriorityQueue<Session>> mapPerDay;
    private String name;
    private int startTime;
    private int endTime;

    public boolean setStartTime(int startTime) {
        if(startTime< Day.START_TIME)return false;
        this.startTime = startTime;
        return true;
        
    }

    public boolean setEndTime(int endTime) {
        if(endTime> Day.END_TIME) return false;
        this.endTime = endTime;
        return true;
    }
    
    public Day(String name, ArrayList<Room> Rooms, int start, int end){
        this.startTime=start;
        this.endTime=end;
        Iterator it = Rooms.iterator();
        while(it.hasNext()){
            mapPerDay.put((Room)it.next(),
                    new PriorityQueue<>(end-start, new Day.SessionComparator()));
        }
    }
    
    //returns start time of gap of s.getDuration() length
    //or 0 if none suitable
    private ArrayList<Integer> getSuitableStartTimes(Iterator it, Session toInsert){ 
        ArrayList<Integer> possibleTimes = new ArrayList<>();
        Session before = (Session) it.next();
        while(it.hasNext()){
            Session after = (Session) it.next();
            int gap = after.getStartTime()-before.getEndTime();
            if(gap>0 && gap<this.endTime) possibleTimes.add(gap);
            before = after;
        }
        return possibleTimes;
    }
    
    public int addSession(Session s){
        PriorityQueue<Session> room_sch = mapPerDay.get(s.getRoom()) ;
        if (!room_sch.isEmpty()){
            boolean canFit = false;
            Iterator it = room_sch.iterator();
            ArrayList<Integer> startTimes = getSuitableStartTimes(it, s);
            if(startTimes.isEmpty()) return Day.ROOM_OVERLAP;
            for (Integer startTime1 : startTimes) {
                int start = startTime1;
                if(s.getTeacher().addTimePair(this.name, start, start+ s.getDuration())){
                    s.setStartTime(start);
                    room_sch.add(s);
                    canFit = true;
                    break;
                }
            }
            if(canFit) return Day.NO_OVERLAP;
            else return Day.TEACHER_OVERLAP;
        }else{
            s.setStartTime(this.startTime);
            if(!s.getTeacher()
                .addTimePair(this.name, Day.START_TIME, s.getEndTime())) 
                return Day.TEACHER_OVERLAP;
            room_sch.add(s);
            return Day.NO_OVERLAP;
        }
        
    }
  
    public void insertSessionAt(Session s){
        PriorityQueue<Session> room_sch = mapPerDay.get(s.getRoom()) ;
        room_sch.add(s);
    }
    
}
