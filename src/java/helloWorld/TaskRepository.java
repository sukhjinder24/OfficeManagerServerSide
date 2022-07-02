///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package helloWorld;
//
//import helloWorld.entites.Task;
//import helloWorld.repositories.EmployeeRepository;
//import helloWorld.mappers.EmployeeMapper;
//import helloWorld.mappers.TaskMapper;
//import helloWorld.models.EmployeeModel;
//import helloWorld.models.TaskModel;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author Sukhjinder
// */
//public class TaskRepository {
//
//    Connection connection = null;
//    TaskMapper mapper = null;
//
//    public TaskRepository() {
//        connection = Database.getInstance().getConnection();
//        mapper = new TaskMapper();
//    }
//    
//    public void create(TaskModel taskModel) {
//        try {
//            Statement statement = connection.createStatement();
//            statement.executeUpdate("INSERT INTO tasks VALUES (" + taskModel.id + ", \"" + taskModel.title + "\",\"" + taskModel.description + "\",\"" + taskModel.status + "\", " + taskModel.empId + ");");
//        } catch (Exception ex) {
//            Logger.getLogger(EmployeeMapper.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void update(String refColumnName, String refColumnValue, String targetColumn, String targetValue) {
//        try {
//            Statement statement = connection.createStatement();
//            statement.execute("UPDATE tasks SET " + targetColumn + " = \"" + targetValue + "\" WHERE " + refColumnName + " = \"" + refColumnValue + "\"");
//        } catch (Exception ex) {
//            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public TaskModel get(String refColumnName, String refColumnValue, String targetColumn) {
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet res = statement.executeQuery("SELECT * FROM tasks WHERE " + refColumnName + " = \"" + refColumnValue + "\";");
//            if (res.next()) {
//                System.out.println(res.getString(targetColumn));
//            }
//            return mapper.resultSet(res).get(0);
//        } catch (Exception ex) {
//            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public List<TaskModel> search() {
//
//        Statement stmt;
//        try {
//            stmt = connection.createStatement();
//            ResultSet set = stmt.executeQuery("select * from tasks");
//            return (mapper.resultSet(set));
//
//        } catch (SQLException ex) {
//            Logger.getLogger(TaskRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return new ArrayList<>();
//    }
//
//    public void delete(String refColumnName, String refColumnValue) {
//        try {
//            Statement statement = connection.createStatement();
//            statement.execute("DELETE FROM tasks WHERE " + refColumnName + " = \"" + refColumnValue + "\"");
//        } catch (Exception ex) {
//            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//   
//
//}
