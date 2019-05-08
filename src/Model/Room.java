package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

/**
 *
 * @author egypt
 */
public abstract class Room implements Serializable{
    //unnecessary comment
    protected String name;
    protected int capacity;



    public abstract void setName(String name);

    public abstract void setCapacity(int capacity);

    public abstract String getName();

    public abstract int getCapacity();

    
}
