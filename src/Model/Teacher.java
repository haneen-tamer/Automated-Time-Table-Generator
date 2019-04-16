/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Aya
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
 class TimePair{
    
   int Start_Time;

    public int getStart_Time() {
        return Start_Time;
    }

    public int getEnd_Time() {
        return End_Time;
    }
   int End_Time;
   
    TimePair(int startTime,int endTime){
       this.Start_Time=startTime;
       this.End_Time=endTime;
    }
}
public abstract class Teacher implements Serializable{
    protected String ID;
    protected String Name;
    protected String E_Mail;
    protected String Phone;
    protected int noOfCourses;

    protected HashMap<String,ArrayList <TimePair>> Status;
    
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public void setE_Mail(String E_Mail) {
        this.E_Mail = E_Mail;
    }
    
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
    
    public void setNoOfCourses(int noOfCourses) {
        this.noOfCourses = noOfCourses;
    }
    
    public String getID() {
        return ID;
    }
    public String getName() {
        return Name;
    }
    public String getE_Mail() {
        return E_Mail;
    }
    public String getPhone() {
        return Phone;
    }
    
    public int getNoOfCourses() {
        return noOfCourses;
    }
    
    
    Teacher(){
        Status=new HashMap<>();
    }
    

    public void incNoOfCourses(){
        noOfCourses++;
    }
    
    public boolean addTimePair(String Day ,int startTime,int endTime){
        
        if(checkAvailable(Day,startTime,endTime)){
            if(!Status.containsKey(Day)){
                Status.put(Day, new ArrayList<>()); //insert
            }
            Status.get(Day).add(new TimePair(startTime, endTime));
            return true;
        }else return false;
    }
    private boolean checkAvailable(String Day ,int start,int end){
        if(!Status.containsKey(Day)){
            return true;
        }
        ArrayList<TimePair> arr = Status.get(Day); 
        for(int i=0; i<arr.size(); i++){
            TimePair current = arr.get(i);
            
            if(start> current.getStart_Time() && start<current.getEnd_Time()){
                return false;
            }
            if(end >current.getStart_Time()&&end<current.getEnd_Time()){
                return false;
            }
            
        }
         return true;
    }
}
