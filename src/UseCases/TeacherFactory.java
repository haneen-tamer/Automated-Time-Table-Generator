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
import Model.Professor;
import Model.TeacherAssistant;
import Model.Teacher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TeacherFactory {
    private static HashMap<String,Teacher>map= new HashMap<>();
    
    public static String getNextID(){
        Random rand =new Random();
        int n =rand.nextInt(map.size()+1);
        String r=Integer.toString(n);
        while(map.containsKey(r)) {
            n++;
            r=Integer.toString(n);
        }
        return r;
    }
    public static TeacherAssistant makeTA (String ID){
        
        Teacher T=new TeacherAssistant();
        map.put(T.getID(), T);
        return (TeacherAssistant) T;
    }
    public static Professor makeProf(String ID){
        Teacher T=new Professor();
        map.put(T.getID(), T);
        return (Professor)T;
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
