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
public class Courses {
    
    protected int id;
    protected String name;
    protected int NoOfstudents;  
    protected ArrayList<Session> sessions = new ArrayList<Session>();
    
    public void AddSession(Session session)
    {
        sessions.add(session);
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
}
