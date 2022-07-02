/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloWorld;

import helloWorld.repositories.EmployeeRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

import java.sql.*;
import java.util.*;

import helloWorld.mappers.EmployeeMapper;
import helloWorld.models.EmployeeModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.POST;

/**
 * REST Web Service
 *
 * @author Sukhjinder
 */
@Path("helloWorld")
//http://localhost:8084/Hello/webresources/helloWorld
public class HelloWorld {
    Gson gson=new Gson();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HelloWorld
     */
    public HelloWorld() {
    }

    /**
     * Retrieves representation of an instance of helloWorld.HelloWorld
     *
     * @return an instance of java.lang.String
     */
    @Path("employees")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllEmployees(){
        EmployeeRepository er=new EmployeeRepository();
        return gson.toJson(er.search());
    }
    
    @Path("tasks")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllTasks(){
        TaskRepository tr=new TaskRepository();
        return gson.toJson(tr.search());
    }
    /**
     * PUT method for updating or creating an instance of HelloWorld
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putHtml(String content) {
    }
}
