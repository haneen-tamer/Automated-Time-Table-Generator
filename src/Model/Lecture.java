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

    public LectureHall LectureHall;
    public Professor professor;

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
        if(!(professor instanceof Professor)) return false;
        this.professor =(Professor) professor;
        return true;
    }

    @Override
    public boolean setRoom(Room LectureHall) {
        if(!(LectureHall instanceof LectureHall)) return false;
        this.LectureHall =(LectureHall) LectureHall;
        return true;
    }  
    
    
    
}