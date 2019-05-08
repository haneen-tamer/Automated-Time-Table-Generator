/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UseCases;

import Model.Session;

/**
 *
 * @author Haneen
 */
public class TeacherOverlapException extends Exception {
    Session cause;
    public TeacherOverlapException(Session s){
        cause =s;
    }
    public Session getSession(){
        return cause;
    }
}
