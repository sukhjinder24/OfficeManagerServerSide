/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloWorld.apis;

import com.google.gson.Gson;
import helloWorld.entites.Employee;
import helloWorld.mappers.EmployeeMapper;
import helloWorld.models.EmployeeModel;
import helloWorld.models.PageResponse;
import helloWorld.repositories.EmployeeRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

@Path("employees")
public class Employees {

    public Gson gson;
    public EmployeeRepository repository;
    public EmployeeMapper mapper;

    public Employees() {
        gson = new Gson();
        repository = new EmployeeRepository();
    }

    //entity-->>database(Actual variable names, Here Employee)
    //model-->>>I/O (variable names can differ, Here EmployeeModel)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String create(String data) {
        Employee entity = gson.fromJson(data, Employee.class);
        String model = repository.create(entity);
        PageResponse res = new PageResponse();
        return gson.toJson(model);
    }

    @PUT
    @Path("/attend/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String markAttend(@PathParam("id") PathSegment ps) {
        int id = Integer.valueOf(ps.getPath());
        String res = repository.markAttendance(id);
        return gson.toJson(res);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String search() {
        List<EmployeeModel> list = repository.search();
        PageResponse<EmployeeModel> response = new PageResponse<>();
        response.setItems(list);
        response.setIsSuccess(true);
        return gson.toJson(response);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getById(@PathParam("id") PathSegment ps, @Context HttpServletRequest request) {
        int empId = Integer.valueOf(ps.getPath());
        EmployeeModel model = repository.get(empId);
        return gson.toJson(model);
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteById(@PathParam("id") PathSegment ps) {
        int id = Integer.valueOf(ps.getPath());
        String res = repository.deleteById(id);
        return gson.toJson(res);
    }

    @Path("signup")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String signUp(String data) {
        Employee entity = gson.fromJson(data, Employee.class);

        EmployeeModel model = repository.signUp(entity);
        return gson.toJson(model);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(String data) {
        String username = gson.fromJson(data, String.class);
        EmployeeModel model = repository.login(username);
        return gson.toJson(model);
    }

    @Path("update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String update(String data) {
        Employee entity = gson.fromJson(data, Employee.class);
        String res = repository.update(entity);
        return gson.toJson(res);
    }

    @GET
    @Path("salary/{year}/{month}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSalaryById(@PathParam("id") PathSegment ps, @PathParam("year") PathSegment py, @PathParam("month") PathSegment pm) {
        int id = Integer.valueOf(ps.getPath());
        String month = pm.getPath();
        String year = py.getPath();
        String res = repository.getSalaryById(id, month, year);
        return gson.toJson(res);
    }

//    @POST
//    @Path("/user/dp")
//    @Consumes({MediaType.MULTIPART_FORM_DATA})
//    public Response uploadPdfFile(@FormDataParam("file") FormDataMultiPart file
//            ,@FormDataParam("fileName") RequestBody fn
//    ) throws Exception {
//
//        ContentDisposition cd=file.getContentDisposition();
//        String fileName=cd.getFileName();
////        InputStream fileInputStream=new FileInputStream(file);
//        
////        String fileName=file.getName();
////        String fileName="a";
//        
//        
//        String uploadedFileLocation = "g://Server/"+fileName+".png";
////        String s = writeToFile(fileInputStream, uploadedFileLocation);
//        
//
//        return Response.ok( "\n Final: Data uploaded successfully to " + uploadedFileLocation + "!!").build();
//    }
//    
//    @POST
//    @Path("/user/dp")
//    @Consumes({MediaType.MULTIPART_FORM_DATA})
//    public Response uploadPdfFile(@FormDataParam("file") File file
////            ,@FormDataParam("fileName") RequestBody fileName
//    ) throws Exception {
//
//        InputStream fileInputStream=new FileInputStream(file);
//        
////        String fileName=file.getName();
//        String fileName=file.getName();
////        String fileName="a";
//        
//        
//        String uploadedFileLocation = "g://Server/"+fileName+".png";
//        String s = writeToFile(fileInputStream, uploadedFileLocation);
//        
//
//        return Response.ok(s + "\n Final: Data uploaded successfully to " + uploadedFileLocation + "!!").build();
//    }
//
//    private String writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
//        String res = "";
//        try {
//
//            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
//           byte[] bucket = new byte[1024];
//           
////            BufferedImage bufferedImage = ImageIO.read(uploadedInputStream);
////            ImageIO.write(bufferedImage, "png", new File(uploadedFileLocation));
//
//           while ((uploadedInputStream.read(bucket, 0, bucket.length)) != -1) {
//                out.write(bucket);
//            }
//            out.flush();
//            out.close();
//            res = "done";
//
//        } catch (IOException e) {
//            res = e.getMessage();
//        }
//        return res;
//    }
}
