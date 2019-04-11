/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author egypt
 */
public class Session {
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
