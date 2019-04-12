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
    Room getRoom(Room room) {
        return room;
    }

    @Override
    Teacher getTeacher(Teacher teacher) {
        return teacher;
    }
    
    public void setProfessor(Prof professor) {
        this.professor = professor;
    }
  
    public Prof getProfessor() {
        return professor;
    }
    public Class getLectureHall() {
        return LectureHall;
    }
    
    public void setLectureHall(Class LectureHall) {
        this.LectureHall = LectureHall;
    }
}