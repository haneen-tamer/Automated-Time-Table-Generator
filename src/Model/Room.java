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
abstract class Room {
    //unnecessary comment
    protected String name;
    protected int capacity;


    abstract void setName(String name);

    abstract void setCapacity(int capacity);

    abstract String getName();

    abstract int getCapacity();
    
}
