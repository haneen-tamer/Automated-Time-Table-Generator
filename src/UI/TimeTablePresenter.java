/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.Courses;
import Model.Room;
import Model.Session;
import Model.TimeTable;
import UseCases.CourseFactory;
import UseCases.RoomFactory;
import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author Haneen
 */
public class TimeTablePresenter extends GridPane {
    private TimeTable schedule;
    private int Duration;
    private GridPane [] scheduelesPerDay;
    private HashMap<String, Integer> RoomRowIndx;
    public enum Day {
        SAT, SUN, MON, TUE, WED,THU
    }
    
    public TimeTablePresenter(TimeTable t){
        super();
        scheduelesPerDay = new GridPane[Day.values().length];
        RoomRowIndx = new HashMap<>();
        this.schedule = t;
        this.setStyle("-fx-border-color: black;");
        Duration = this.schedule.getEndTime() - this.schedule.getStartTime()+1;
        insertDays();
        addTimeSlots();
        for(Courses c: CourseFactory.getAllCourses()){
            for(Session s: c.GetSessions()){
                insertSession(s);
            }
        }
    }
    
    private void insertSession(Session s){
        SessionPresenter sp = new SessionPresenter(s);
        sp.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setVgrow(sp, Priority.ALWAYS);
        GridPane.setHgrow(sp, Priority.ALWAYS);
        
        int duration = s.getDuration();
        int dayIndx = Day.valueOf(s.getDay()).ordinal();
        GridPane toAddTo = scheduelesPerDay[dayIndx];
        if(toAddTo==null){
            toAddTo = new GridPane();
            toAddTo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            scheduelesPerDay[dayIndx] = toAddTo;
            insertRooms(toAddTo);
            addTimeSlots(toAddTo);
            toAddTo.setStyle("-fx-padding: 0,0,0,0; -fx-border-color: black");
            this.add(toAddTo, 1, dayIndx+1, Duration+1, 1);
//                scheduleGrid.setGridLinesVisible(true);
        }
        
        toAddTo.add(sp, 
                getColIndxFromStartTime(s.getStartTime()),
                RoomRowIndx.get(s.getRoom().getName()),
                duration, 1);
    }
    
    private void insertDays(){
        RowConstraints rowFirst = new RowConstraints(
                    GridPane.USE_PREF_SIZE,
                40*RoomFactory.get_AllRooms().size(),
                GridPane.USE_PREF_SIZE);
        this.getRowConstraints().add(rowFirst);
        int i=1;
        for(Day d : Day.values()){
            RowConstraints row = new RowConstraints(
                    GridPane.USE_PREF_SIZE,
                40*RoomFactory.get_AllRooms().size(),
                GridPane.USE_PREF_SIZE);
            this.getRowConstraints().add(row);
            Label l = new Label(d.name());
            l.setStyle("-fx-padding: 0,0,0,0; -fx-border-color: black");
            l.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            this.addRow(i, l);
            i++;
        }
    }
    
    private void insertRooms(GridPane gp){
        int i=1;
        for(Room r: RoomFactory.get_AllRooms()){
            RowConstraints row = new RowConstraints(
                    GridPane.USE_PREF_SIZE,80,GridPane.USE_PREF_SIZE);
            gp.getRowConstraints().add(row);
            Label l = new Label(r.getName());
            l.setStyle("-fx-padding: 0,0,0,0; -fx-border-color: black");
            l.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            gp.addRow(i, l);
            this.RoomRowIndx.put(r.getName(), i);
        }
            
    }
    
    private int getColIndxFromStartTime(int start){
        return start - this.schedule.getStartTime()+1;
    }
    
    public void addTimeSlots(){
        int StartTime = this.schedule.getStartTime();
        for(int i=0; i<Duration; i++){
            Label l;
            l= new Label(""+(StartTime+i)+" - "+(StartTime+i+1)+"");
            l.setStyle("-fx-padding: 0,0,0,0; -fx-border-color: black");
            l.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            ColumnConstraints column1 = new ColumnConstraints(
                    GridPane.USE_PREF_SIZE,80,GridPane.USE_PREF_SIZE);
            this.getColumnConstraints().add(column1);
            this.add(l,i+2, 0);
        }
//        this.setGridLinesVisible(true);
        System.out.println("#of cols: "+this.getColumnConstraints().size());
    }
    
    public void addTimeSlots(GridPane gp){
        for(int i=0; i<=Duration; i++){
            ColumnConstraints column1 = new ColumnConstraints(
                    GridPane.USE_PREF_SIZE,80,GridPane.USE_PREF_SIZE);
            gp.getColumnConstraints().add(column1);
        }
    }
    
    
    
}
