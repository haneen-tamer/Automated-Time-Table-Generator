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
public interface Saveable {
    
    
    public Saveable getInstance(String line);
    public String getSaveableLine();
    
}
