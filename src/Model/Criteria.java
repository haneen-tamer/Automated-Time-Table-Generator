/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;

/**
 *
 * @author egypt
 */
public interface Criteria {
    
    public Schedule meetCriteria(ArrayList<Object> filters);
}