/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UseCases;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.Serializable;
/**
 *
 * @author dell
 */
public class FileManger {
    
    /*....................................*/
    public static boolean SaveToFile(String FileName,ArrayList<Serializable> a)
    {
        try{
        FileOutputStream fos=new FileOutputStream(FileName);
        BufferedOutputStream bos=new BufferedOutputStream(fos);
        ObjectOutputStream os=new ObjectOutputStream(bos);
        os.writeObject(a);
        os.close();
        
        return true;
        }
        catch(IOException e)
        {
            return false;
        }
        
    }
    public static ArrayList<Serializable> ReadFile(String FileName) throws ClassNotFoundException
    {
        ArrayList<Serializable> a=new ArrayList<Serializable>();
        /*..................................................*/
        try{
        FileInputStream fis=new FileInputStream(FileName);
        BufferedInputStream bis=new BufferedInputStream(fis);
        ObjectInputStream is=new ObjectInputStream(bis);
        a=(ArrayList<Serializable>) is.readObject();
        is.close();
        
        return a;
        }
        catch(IOException e)
        {
            System.out.println("FileNotFound");
            return a;
        }
        
    }
}
