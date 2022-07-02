/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloWorld.apis;

import com.google.gson.Gson;
import helloWorld.entites.Task;
import helloWorld.mappers.TaskMapper;
import helloWorld.models.PageResponse;
import helloWorld.models.TaskModel;
import helloWorld.repositories.TaskRepository;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author Sukhjinder
 */
@Path("tasks")
public class Tasks {
    public Gson gson;
    public TaskRepository repository;
    public TaskMapper mapper;
    
    public Tasks(){
        gson=new Gson();
        repository=new TaskRepository();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String create(String data){
        Task entity=gson.fromJson(data, Task.class);
        TaskModel model=repository.create(entity);
        return gson.toJson(model);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String search(){
        List<TaskModel> list=repository.search();
        PageResponse<TaskModel> response = new PageResponse<>();
        response.setItems(list);
        response.setIsSuccess(true);
        return gson.toJson(response);
//        return gson.toJson(list);
    }
    
    @GET
    @Path("/empId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTasksByEmpId(@PathParam("id") PathSegment ps){
        int EmpId=Integer.valueOf(ps.getPath());
        List<TaskModel> list=repository.getTasksByEmpId(EmpId);
        PageResponse<TaskModel> response = new PageResponse<>();
        response.setItems(list);
        response.setIsSuccess(true);
        return gson.toJson(response);
//        return gson.toJson(list);
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getById(@PathParam("id") PathSegment ps){
        int taskId=Integer.valueOf(ps.getPath());
        TaskModel model = repository.getByID(taskId);
        return gson.toJson(model);
    }
        
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteById(@PathParam("id") PathSegment ps){
        int taskId=Integer.valueOf(ps.getPath());
        String res=repository.deleteById(taskId);
        return gson.toJson(res);
    }
    
    @Path("/updateStatus/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateStatus(@PathParam("id") PathSegment ps) {
        int taskId=Integer.valueOf(ps.getPath());
        String res = repository.updateStatus(taskId);
        return gson.toJson(res);
    }
    
    
}