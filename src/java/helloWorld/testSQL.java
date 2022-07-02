/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloWorld;

import helloWorld.repositories.EmployeeRepository;
import helloWorld.mappers.EmployeeMapper;
import helloWorld.mappers.TaskMapper;
import helloWorld.models.EmployeeModel;
import helloWorld.models.TaskModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sukhjinder
 */
public class testSQL {

    public static void main(String[] args) {
        Connection connection = null;
        connection = Database.getInstance().getConnection();

//      CreateTask (Adding new Task)
//        TaskModel taskModel = new TaskModel();
//        taskModel.id = 104;
//        taskModel.title = "tsting";
//        taskModel.description = "To test the developed solution";
//        taskModel.status = "closed";
//        taskModel.empID = 1012;
//        try {
//            Statement statement = connection.createStatement();
//            statement.executeUpdate("INSERT INTO tasks VALUES (" + taskModel.id + ", \"" + taskModel.title + "\",\"" + taskModel.description +  "\",\"" + taskModel.status + "\", " + taskModel.empID + ");");
////            System.out.println("INSERT INTO tasks VALUES (" + taskModel.id + ", \"" + taskModel.title + "\",\"" + taskModel.description  + "\",\"" + taskModel.status + "\", " + taskModel.empID + ");");
//        } catch (Exception ex) {
//            Logger.getLogger(EmployeeMapper.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        updateTask
//        String refColumnName="id";
//        String refColumnValue="101";
//        String targetColumn="title";
//        String targetValue="planning";
//
//        try{
//            Statement statement=connection.createStatement();
//            statement.execute("UPDATE tasks SET "+targetColumn+" = \""+targetValue+"\" WHERE "+refColumnName+" = \""+refColumnValue+"\"");
//        }catch(Exception ex){
//            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        getTask
//
//        String refColumnName="id";
//        String refColumnValue="102";
//        String targetColumn="description";
//        
////        String refColumnName="name";
////        String refColumnValue="Tenth";
////        String targetColumn="id";
//        Statement statement;
//        try{
//            statement=connection.createStatement();
//            ResultSet res=statement.executeQuery("SELECT * FROM tasks WHERE "+refColumnName+" = \""+refColumnValue+"\";");
//            if(res.next()){
//                System.out.println(res.getString(targetColumn));
//            }
////            if(res.next()){
////                System.out.println(res.getString(targetColumn));
////            }
//        }catch(Exception ex){
//             Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//   
//        deleteTask
//
//        String refColumnName="id";
//        String refColumnValue="101";
//        Statement statement;
//        try{
//            statement=connection.createStatement();
//            statement.execute("DELETE FROM tasks WHERE "+refColumnName+" = \""+refColumnValue+"\"");
//            
//        }catch(Exception ex){
//             Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        SearchTask
//
//        TaskMapper mapper = new TaskMapper();
//        Statement stmt;
//        try {
//            stmt = connection.createStatement();
//            ResultSet set = stmt.executeQuery("select * from tasks");
//            for (TaskModel taskModel : mapper.resultSet(set)) {
//                System.out.println("id: " + taskModel.id + ", title: " + taskModel.title + ", description: " + taskModel.description + ", status: " + taskModel.status + ", empID: " + taskModel.empID);
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(TaskRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        update
//        String refColumnName="id";
//        String refColumnValue="1010";
//        String targetColumn="name";
//        String targetValue="tenth";
//
//        try{
//            Statement statement=connection.createStatement();
//            statement.execute("UPDATE employees SET "+targetColumn+" = \""+targetValue+"\" WHERE "+refColumnName+" = \""+refColumnValue+"\"");
//        }catch(Exception ex){
//            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//
////        delete
//
//        String refColumnName="id";
//        String refColumnValue="1010";
//        Statement statement;
//        try{
//            statement=connection.createStatement();
//            statement.execute("DELETE FROM employees WHERE "+refColumnName+" = \""+refColumnValue+"\"");
//            
//        }catch(Exception ex){
//             Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
////        get
//
//        String refColumnName="id";
//        String refColumnValue="1010";
//        String targetColumn="name";
//        
////        String refColumnName="name";
////        String refColumnValue="Tenth";
////        String targetColumn="id";
//        Statement statement;
//        try{
//            statement=connection.createStatement();
////            ResultSet res=statement.executeQuery("SELECT "+targetColumn+" FROM employees WHERE "+refColumnName+" = \""+refColumnValue+"\";");
//            ResultSet res=statement.executeQuery("SELECT * FROM employees WHERE "+refColumnName+" = \""+refColumnValue+"\";");
//            if(res.next()){
//                System.out.println(res.getString(targetColumn));
//            }
//        }catch(Exception ex){
//             Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        Search
        EmployeeMapper mapper = new EmployeeMapper();
        Statement stmt;
        try {
            stmt = connection.createStatement();
            ResultSet set = stmt.executeQuery("select * from employees");
//            for (EmployeeModel employeeModel : mapper.resultSet(set)) {
//                System.out.println("id: " + employeeModel.id + ", name: " + employeeModel.name);
//            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
//
//        Create
//        EmployeeModel employeeModel=new EmployeeModel();
//        employeeModel.id=1010;
//        employeeModel.name="Tenth";
//        Statement statement;
//        try{
//            statement=connection.createStatement();
//            statement.executeUpdate("INSERT INTO employees VALUES("+employeeModel.id+",\""+employeeModel.name+"\")");
//        }catch(Exception ex){
//            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
