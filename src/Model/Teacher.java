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
import java.util.HashMap;

 class TimePair{
    
   int Start_Time;
   int End_Time;
}
public class Teacher {
    protected String ID;
    protected String Name;
    protected String E_Mail;
    protected String Phone;
    protected int noOfCourses;

    protected HashMap<String,TimePair> x;
    
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
        x=new HashMap<String,TimePair>();
    }
    
    protected int StartTime;
    protected int Duration;
    protected String day;
    
    public void setStartTime(int StartTime) {
        this.StartTime = StartTime;
    }

    public int getEndTime() {
        return StartTime + Duration;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }

    public int getStartTime() {
        return StartTime;
    }

    public int getDuration() {
        return Duration;
    }
}
