/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UseCases;
import Model.*;
import java.util.ArrayList;
/**
 *
 * @author Haneen
 */
public class FilterRoom {
    public static Schedule meetsCriteria(Schedule old, Room r){
        Schedule filtered =  new Schedule(old.getDaysStrings(), RoomFactory.get_AllRooms(),
        old.getStartTime(), old.getEndTime());
        filtered.insertSessionsAt(old.getRoomSchedule(r));
        return filtered;
    }
}
