/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloWorld;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Sukhjinder
 */
public class Database {
    
    public static Database instance;
    
    private Database(){
    }
    
    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }
    
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");  
//            return DriverManager.getConnection("jdbc:mysql://localhost:3306/management","root","");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/taskmanager","root","");
        } catch(Exception e){ 
            System.out.println(e);
        }
        return null;
    }
}
