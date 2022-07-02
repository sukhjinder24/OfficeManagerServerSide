/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloWorld.mappers;

import helloWorld.entites.Employee;
import helloWorld.models.EmployeeModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sukhjinder
 */
public class EmployeeMapper {

    public EmployeeModel toModel(Employee entity) {

        EmployeeModel model = new EmployeeModel();
        model.setEmpId(entity.getEmpId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());

        model.setEmail(entity.getEmail());
        model.setImageURL(entity.getImageURL());
        model.setPass(entity.getPass());
        model.setRole(entity.getRole());
        model.setStatus(entity.getStatus());
        model.setSalary(entity.getSalary());
        model.setGender(entity.getGender());

        return model;
    }

    public EmployeeModel resultSet(ResultSet set) {
        EmployeeModel model = null;
        try {
            model = new EmployeeModel();
            model.setEmpId(set.getInt(1));
            model.setFirstName(set.getString(2));
            model.setLastName(set.getString(3));

            model.setEmail(set.getString(4));
            model.setImageURL(set.getString(5));
            model.setPass(set.getString(6));
            model.setRole(set.getString(7));
            model.setStatus(set.getString(8));
            model.setSalary(Integer.valueOf(set.getString(11)));
            model.setGender(set.getString(12));
        } catch (Exception e) {
            System.err.println(e);
        }
        return model;
    }

}
