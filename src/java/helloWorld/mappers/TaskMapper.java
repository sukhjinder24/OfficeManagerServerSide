/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloWorld.mappers;

import helloWorld.entites.Employee;
import helloWorld.entites.Task;
import helloWorld.models.EmployeeModel;
import helloWorld.models.TaskModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sukhjinder
 */
public class TaskMapper {
    
     public TaskModel toModel(Task entity){
     
        TaskModel model = new TaskModel();
        model.setTaskId(entity.getTaskId());
        model.setTitle(entity.getTitle());
        model.setDescription(entity.getDescription());
        model.setStatus(entity.getStatus());
        model.setEmpId(entity.getEmpId());
        model.setCreatedBy(entity.getCreatedBy());
  
        return model;
    }
     public TaskModel resultSet(ResultSet set){
     
        TaskModel model = new TaskModel();
        try{
        model.setTaskId(set.getInt("taskId"));
        model.setTitle(set.getString("title"));
        model.setDescription(set.getString("description"));
        model.setStatus(set.getString("status"));
        model.setEmpId(set.getInt("empId"));
        model.setCreatedBy(set.getInt("createdBy"));
        model.setDateAssigned(set.getDate("AssignedAt").toString());
        model.setDeadline(set.getString("deadline"));
//        model.se
        }catch(Exception ex){
            System.err.println(ex);
        }
  
        return model;
    }
     
     
//    public ArrayList<TaskModel> resultSet(ResultSet set){
//        ArrayList<TaskModel> list=new ArrayList<>();
//        
//        try{
//            while(set.next()){
//                TaskModel taskModel=new TaskModel();
//                taskModel.taskId=set.getInt("taskId");
//                taskModel.title=set.getString("title");
//                taskModel.description=set.getString("description");
//                taskModel.status=set.getString("status");
//                taskModel.empId=set.getInt("empId");
//                list.add(taskModel);
//            }
//            return list;
//        }catch(Exception ex){
//            Logger.getLogger(EmployeeMapper.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
}
