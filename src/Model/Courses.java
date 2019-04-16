/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;
import java.io.Serializable;  

/**
 *
 * @author egypt
 */
public class Courses implements Serializable{
    
    protected String id;
    protected String name;
    protected int NoOfstudents;  
    protected ArrayList<Session> sessions = new ArrayList<Session>();
    
    public void AddSession(Session session)
    {
        sessions.add(session);
    }
    
    public ArrayList<Session> GetSessions(){
        return new ArrayList<Session>(sessions);
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
}
