/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.repository;

import com.example.overtime.entity.Employee;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pandu
 */
public interface EmployeeRepo extends CrudRepository<Employee, String> {

    @Modifying
    @Query(value = "DELETE EMPLOYEE WHERE id=?1", nativeQuery = true)
    public void deleteWithId(String id);

    @Query(value = "SELECT * FROM tb_m_employee WHERE email like ?1%", nativeQuery = true)
    public Employee findToken(String email);

    @Modifying
    @Query(value = "UPDATE tb_m_employee SET activation ='1' WHERE email=?1 ", nativeQuery = true)
    public void activation(String email);
}
