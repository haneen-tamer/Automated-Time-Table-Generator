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
public class Section extends Session {

    public Lab lab;
    public TA ta;

     @Override
    Room getRoom(Room room) {
        return room;
    }

    @Override
    Teacher getTeacher(Teacher teacher) {
        return teacher;
    }
    
    public void setTa(TA ta) {
        this.ta = ta;
    }

    public TA getTa() {
        return ta;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }  
}
