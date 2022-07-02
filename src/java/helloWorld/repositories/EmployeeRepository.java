/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloWorld.repositories;

import helloWorld.Database;
import helloWorld.entites.Employee;
import helloWorld.mappers.EmployeeMapper;
import helloWorld.models.EmployeeModel;
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
public class EmployeeRepository {

    Connection connection = null;
    EmployeeMapper mapper = null;

    public EmployeeRepository() {
        connection = Database.getInstance().getConnection();
        mapper = new EmployeeMapper();
    }

    public String create(Employee entity) {
        try {
            String SQL_INSERT = "INSERT INTO employees (firstName,lastName,email,imageURL,pass,role,status,salary,gender) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.firstName);
            statement.setString(2, entity.lastName);
            statement.setString(3, entity.email);
            statement.setString(4, entity.imageURL);
            statement.setString(5, entity.pass);
            statement.setString(6, entity.role);
            statement.setString(7, entity.status);
            statement.setString(8, String.valueOf(entity.salary));
            statement.setString(9, entity.gender);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 1) {
                return "Employee Added Successfully";
            }
        } catch (Exception ex) {
            if (ex.getMessage().startsWith("Duplicate entry ")) {
                return "user with entered email already exists";
            }
            return "Failed to add user try Again later";
        }
        return "Failed to add user try Again later";
    }

       public List<EmployeeModel> search() {
        List<EmployeeModel> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM employees";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                list.add(mapper.resultSet(set));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       
    public EmployeeModel signUp(Employee entity) {
        try {
            String SQL_INSERT = "INSERT INTO employees (email,pass) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.email);
            statement.setString(2, entity.pass);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setEmpId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return mapper.toModel(entity);
        } catch (Exception ex) {
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

 

    public EmployeeModel get(int id) {
        try {
            String query = "SELECT * FROM employees WHERE empId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            System.out.println(statement.toString());
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return mapper.resultSet(set);
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public EmployeeModel signIn(Employee entity) {
        try {

            String query = "Select * FROM employees WHERE email=? and pass=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, entity.email);
            statement.setString(2, entity.pass);

            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return mapper.resultSet(set);
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String deleteById(int id) {
        try {
            String query = "DELETE FROM employees WHERE empId=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 1) {
                return "Employee Deleted Successfully";
            } else if (affectedRows == 0) {
                return "No such employee exists";
            }
        } catch (SQLException sqlEx) {
            if (sqlEx.getMessage().equals("Cannot delete or update a parent row: a foreign key constraint fails (`taskmanager`.`tasks`, CONSTRAINT `empId` FOREIGN KEY (`empId`) REFERENCES `employees` (`empID`))")) {
                return "Can't delete this Employee as some task depends on it";
            }
            return sqlEx.getMessage();
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "Failed to delete";
    }

    public EmployeeModel login(String username) {

        try {
            String query = "SELECT * FROM employees WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                return mapper.resultSet(set);
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;

    }

    public String markAttendance(int id) {

        try {
            String query = "INSERT INTO attend VALUES (?,CURRENT_TIMESTAMP(),1)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to mark Attendance");
            }
            if (affectedRows == 1) {
                return "Attendence Marked";
            }
        } catch (SQLException ex) {
            if (ex.getMessage().startsWith("Duplicate entry ") && ex.getMessage().endsWith(" for key 'PRIMARY'")) {
                return "Attendence Already Marked";
            } else {
                Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
                return "Failed to mark Attendance";
            }
        }
        return null;

    }

    public String update(Employee entity) {
        try {
            String query = "UPDATE employees SET firstName=?,lastName=?,pass=? WHERE empId=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, entity.firstName);
            statement.setString(2, entity.lastName);
            statement.setString(3, entity.pass);
            statement.setInt(4, entity.empId);

            int affectedRows = statement.executeUpdate();
            if (affectedRows != 1) {
                return "Failed to update try again later";
            } else {
                return "Updated Successfully";
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
    }

    public String getSalaryById(int id, String month, String year) {
        String salary = "";
        try {
            String query = "SELECT Round(COUNT(a.STATUS)*(e.salary/30))as balance FROM attend a ,employees e WHERE e.empID=? AND e.empID=a.empID AND a.DATE LIKE ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, year + "-" + month + "-%%");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                salary = set.getString("balance");
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return salary;
    }

}
