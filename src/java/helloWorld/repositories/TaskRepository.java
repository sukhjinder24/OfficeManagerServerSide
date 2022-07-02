/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloWorld.repositories;

import helloWorld.Database;
import helloWorld.entites.Task;
import helloWorld.mappers.TaskMapper;
import helloWorld.models.TaskModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sukhjinder
 */
public class TaskRepository {
    Connection connection=null;
    TaskMapper mapper=null;
    
    public TaskRepository(){
        connection=Database.getInstance().getConnection();
        mapper=new TaskMapper();
    }
    
    public List<TaskModel> search(){
        List<TaskModel> list=new ArrayList<>();
        try{
            String query="SELECT * FROM tasks";
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet set=statement.executeQuery();
            while(set.next()){
                list.add(mapper.resultSet(set));
            }
        }catch(Exception ex){
             Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public TaskModel getByID(int taskId){
        try{
            String query="SELECT * FROM tasks WHERE taskId=?";
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1, taskId);
            ResultSet set=statement.executeQuery();
            if(set.next()){
                return mapper.resultSet(set);
            }
        }catch(Exception ex){
             Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public TaskModel create(Task entity){
        try{
            String query="INSERT INTO tasks (title,description,status,empId,createdBy,deadline) VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt=connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, entity.title);
            stmt.setString(2, entity.description);
            stmt.setString(3, entity.status);
            stmt.setInt(4, entity.empId);
            stmt.setInt(5, entity.createdBy);
            stmt.setString(6, entity.getDeadline());
            int affectedRows=stmt.executeUpdate();
            if(affectedRows==0){
                throw new SQLException("Creating user failed, no rows affected.");
            }
            
            try(ResultSet generatedKeys=stmt.getGeneratedKeys()) {
                if(generatedKeys.next()){
                    entity.setTaskId(generatedKeys.getInt(1));
                }else{
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return mapper.toModel(entity);
        }catch(Exception ex){
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String deleteById(int id){
        try{
        String query="DELETE FROM tasks WHERE taskId=?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setInt(1, id);
        int affectedRows=statement.executeUpdate();
        if(affectedRows==1){
            return "Task Deleted successfully";
        }else if (affectedRows == 0) {
                return "No such task exists";
            }
        }catch(Exception ex){
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Failed to delete";
    }
    
    public String updateStatus(int id){
        try{
        String query="UPDATE tasks SET STATUS=\"completed\" WHERE taskId=?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setInt(1, id);
        int affectedRows=statement.executeUpdate();
        if(affectedRows==1){
            return "Status Updated successfully";
        }else if (affectedRows == 0) {
                return "Status Updation Failed";
            }
        }catch(Exception ex){
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Status Updation Failed";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    public List<TaskModel> getTasksByEmpId(int empId){
        List<TaskModel> list=new ArrayList<>();
        try{
            String query="SELECT * FROM tasks WHERE empId = ?";
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1, empId);
            ResultSet set=statement.executeQuery();
            while(set.next()){
                list.add(mapper.resultSet(set));
            }
        }catch(Exception ex){
             Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
