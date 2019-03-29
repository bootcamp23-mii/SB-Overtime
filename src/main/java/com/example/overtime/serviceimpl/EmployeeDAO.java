/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.serviceimpl;

import com.example.overtime.entity.Employee;
import com.example.overtime.repository.EmployeeRepo;
import com.example.overtime.service.IEmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pandu
 */
@Service
public class EmployeeDAO implements IEmployeeDAO {

    @Autowired
    EmployeeRepo erep;

    @Override
    public Iterable<Employee> findAll() {
        return erep.findAll();
    }

    @Override
    public Employee save(Employee emp) {
        return erep.save(emp);
    }

    @Override
    public Employee findById(String id) {
        return erep.findById(id).get();
    }

    @Override
    public void deleteById(String id) {
        erep.deleteWithId(id);
    }

    @Override
    public void activation(String email) {
        erep.activation(email);
    }

    @Override
    public Employee findToken(String token) {
        return erep.findToken(token);
    }

}
