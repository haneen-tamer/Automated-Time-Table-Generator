/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import Model.*;
import UseCases.*;
import java.util.*;
/**
 *
 * @author Haneen
 */
public class consoleTest {
    public static void main(String [] args){
        //if files are empty
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Number of Rooms: ");
        int numRooms = input.nextInt();
        for(int i=0; i<numRooms; i++)
            ReadRoom(input);
        
    }
    
    public static void ReadRoom(Scanner input){
        System.out.println("Enter Room Data: ");
        System.out.print("Enter Room Name: ");
        String name = input.next();
        System.out.print("Enter Room Capacity: ");
        int Capacity = input.nextInt();
        System.out.print("Enter Room Type:(L/C) ");
        String type = input.next();
        if(type=="C"){
            Room r =RoomFactory.Make_Class(name, Capacity);
        }
        else if(type=="L"){
            Room r=RoomFactory.Make_Lab(name, Capacity);
        }else{
            System.out.println("Invalid character! ");
        }
    }
}
