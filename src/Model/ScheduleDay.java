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
public class ScheduleDay {
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
    
    private HashMap<String, PriorityQueue<Session>> mapPerDay;
    private String name;
    private int startTime;
    private int endTime;

    public boolean setStartTime(int startTime) {
        if(startTime< ScheduleDay.START_TIME)return false;
        this.startTime = startTime;
        return true;
        
    }

    public boolean setEndTime(int endTime) {
        if(endTime> ScheduleDay.END_TIME) return false;
        this.endTime = endTime;
        return true;
    }
    
    public ScheduleDay(String name, ArrayList<Room> Rooms, int start, int end){
        this.name=name;
        mapPerDay = new HashMap<>(Rooms.size());
        this.startTime=start;
        this.endTime=end;
        for(Room r :Rooms){
            mapPerDay.put(r.getName(),
                    new PriorityQueue<>(end-start, new ScheduleDay.SessionComparator()));
        }
        System.out.println(mapPerDay.values().size());
        }
    
    //returns start time of gap of s.getDuration() length
    //or 0 if none suitable
    private ArrayList<Integer> getSuitableStartTimes(Iterator it, Session toInsert){ 
        ArrayList<Integer> possibleTimes = new ArrayList<>();
        Session before = (Session) it.next();
        while(it.hasNext()){
            Session after = (Session) it.next();
            int gap = after.getStartTime()-before.getEndTime();
            if(gap>0 && before.getEndTime()<this.endTime 
                    && gap<=toInsert.getDuration()) {
                for(int i=before.getEndTime(); i<=after.getStartTime(); i++)
                    possibleTimes.add(i);
            }
                
            before = after;
        }
        return possibleTimes;
    }
    
    public int addSession(Session s){
        //System.out.println(s.getRoom().getName());
        PriorityQueue<Session> room_sch = mapPerDay.get(s.getRoom().getName()) ;
        System.out.println(s.getRoom().getName()+" capacity :"+room_sch.size());
//        if(room_sch==null){
//            room_sch=new PriorityQueue<>(this.endTime -this.startTime, new ScheduleDay.SessionComparator());
//        }
        if (room_sch.size()!=0){
            boolean canFit = false;
            System.out.println("room not empty");
            Iterator it = room_sch.iterator();
            ArrayList<Integer> startTimes = getSuitableStartTimes(it, s);
            
            if(startTimes.isEmpty()){
                System.out.println("Previous sess: "
                        +room_sch.peek().getCourseTitle()+" ends at "
                        +room_sch.peek().getEndTime());
                for(int i=room_sch.peek().getEndTime(); i< this.endTime;i++){
                    if(i+s.getDuration()<=this.endTime)
                        startTimes.add(i);
                   else break;
                }
                    
                if(startTimes.isEmpty())return ScheduleDay.ROOM_OVERLAP;
            }

            for (Integer startTime1 : startTimes) {
               System.out.println("gaps: "+startTime1);
                int start = startTime1;
                if(s.getTeacher().addTimePair(this.name, start, start+ s.getDuration())){
                    s.setStartTime(start);
                    System.out.println("Starttime of "+s.getCourseTitle()+" is "
                            + s.getStartTime());
                    s.setDay(name);
                    room_sch.add(s);
                    canFit = true;
                    break;
                }
            }
            if(canFit) return ScheduleDay.NO_OVERLAP;
            else return ScheduleDay.TEACHER_OVERLAP;
        }

         else{
            s.setStartTime(this.startTime);
            //System.out.println("Starttime of "+s.getCourseTitle()+" is "+ s.getStartTime());
            if(!s.getTeacher()
                .addTimePair(this.name, ScheduleDay.START_TIME, s.getEndTime())) 
                return ScheduleDay.TEACHER_OVERLAP;
            room_sch.add(s);
            s.setDay(name);
            return ScheduleDay.NO_OVERLAP;
        }
        
    }
  
    public void insertSessionAt(Session s){
        PriorityQueue<Session> room_sch = mapPerDay.get(s.getRoom().getName()) ;
        room_sch.add(s);
    }
    
    public int getStartTime(){
        return this.startTime;
    }
    public int getEndTime(){
        return this.endTime;
    }
    public ArrayList<Session> getRoomSchedule(Room r){
        ArrayList<Session> arr = new ArrayList<>();
        Iterator it = mapPerDay.get(r.getName()).iterator();
        while(it.hasNext()){
            arr.add((Session)it.next());
        }
        return arr;
    }
    
}
