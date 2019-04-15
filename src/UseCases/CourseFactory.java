/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UseCases;
import java.util.*;
import Model.*;

/**
 *
 * @author Haneen
 */
public class CourseFactory {
    private static HashMap<String, Courses> map;
    
    public static String getNextID() {
        int id = map.size()+1;
        while(map.containsKey(""+id))id++;
        return ""+id;
    }
    
    public static Courses getCourse(String id){
        if(map.containsKey(id)){
            return map.get(id);
        }else{
            Courses c= new Courses();
            c.setId(id);
            map.put(id, c);
            return c;
        }
    }
    
    public static void addCourse(Courses c){
        map.put(c.getId(), c);
    }
    
    public static ArrayList<Courses> getAllCourses(){
        ArrayList<Courses> all = new ArrayList<>();
        for(Courses c: map.values()){
            all.add(c);
        }
        return all;
    }
}
