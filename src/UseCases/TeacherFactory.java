/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UseCases;

/**
 *
 * @author Aya
 */
import Model.Prof;
import Model.TA;
import Model.Teacher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TeacherFactory {
    private static HashMap<String,Teacher>map;
    
    public static String getNextID(){
        Random rand =new Random();
        int n =rand.nextInt(map.size());
        String r=Integer.toString(n);
        while(map.containsKey(r)) {
            
            n++;
        }
        return r;
    }
    public static TA makeTA (String ID){
        
        Teacher T=new TA();
        map.put(T.getID(), T);
        return (TA) T;
    }
    public static Prof makeProf(String ID){
        Teacher T=new Prof();
        map.put(T.getID(), T);
        return (Prof)T;
    }
    public static void addTeacher(Teacher T){
        
        map.put(T.getID(), T);
        
    }
    
    public static Teacher getTeacher(String ID){
        
        return map.get(ID);
    }
    
    public static ArrayList<Teacher> getAllTeachers(){
        ArrayList <Teacher> A=new ArrayList<>();
        for(Teacher T: map.values()){
            A.add(T);
        }
        return A;
        
        
    }
    
    
    
}
