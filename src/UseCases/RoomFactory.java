/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UseCases;
import Model.Lab;
import Model.LectureHall;
import java.util.ArrayList;
import java.util.HashMap;
import Model.Room;
import static jdk.nashorn.internal.objects.NativeArray.map;
/**
 *
 * @author dell
 */
public class RoomFactory {
   private static HashMap<String,Room> map;
   /*.............................................*/
   public static boolean isUniqueName(String n)
   {
       if(map.containsValue(n)==true)
       {
           return true;
       }
       else
       {
           return false;
       }
   }
   public static Room Make_Class(String Name,int capicty)
   {
       Room r = new LectureHall();
       r.setName(Name);
       r.setCapacity(capicty);
       return r;
   }
   public static Room Make_Lab(String Name,int capicty)
   {
       Room r=new Lab();
       r.setName(Name);
       r.setCapacity(capicty);
       return r;
   }
   public static void Add_Room(Room r)
   {
       String key;
       /*.............*/
       key=r.getName();
       map.put(key, r);
   }
   public  static Room get_Room(String Name)
   {
       return map.get(Name);
   }
   public static ArrayList<Room> get_AllRooms()
   {
       ArrayList<Room> a=(ArrayList<Room>)map.values();
       /*...............................
       unnecessary comment.........*/
       return a;
   }
}