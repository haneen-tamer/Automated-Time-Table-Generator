/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.io.Serializable;

/**
 *
 * @author egypt
 */
public abstract class Session implements Serializable{
    
    protected int StartTime;
    protected int Duration;
    protected String day;
    protected String CourseTitle;

    public abstract Room getRoom();
    public abstract Teacher getTeacher();
    public abstract boolean setTeacher(Teacher t);
    public abstract boolean setRoom(Room r);

    public void setCourseTitle(String t){
        this.CourseTitle=t;
    }
    public String getCourseTitle(){
        return this.CourseTitle;
    }
    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }
    
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
