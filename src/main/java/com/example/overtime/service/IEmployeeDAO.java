/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.service;

import com.example.overtime.entity.Employee;

/**
 *
 * @author Pandu
 */
public interface IEmployeeDAO {
    Iterable<Employee> findAll();

    Employee save(Employee emp);


    void findById(String id);

    void deleteById(String id);
}
