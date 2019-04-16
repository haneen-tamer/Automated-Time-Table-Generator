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
public class Lecture extends Session {

    public Class LectureHall;
    public Prof professor;

    @Override
    public Room getRoom() {
        return LectureHall;
    }

    @Override
    public Teacher getTeacher() {
        return professor;
    }
    
    @Override
    public boolean setTeacher(Teacher professor) {
        if(!(professor instanceof Prof)) return false;
        this.professor =(Prof) professor;
        return true;
    }

    @Override
    public boolean setRoom(Room LectureHall) {
        if(!(LectureHall instanceof Class)) return false;
        this.LectureHall =(Class) LectureHall;
        return true;
    }  
    
    
    
}