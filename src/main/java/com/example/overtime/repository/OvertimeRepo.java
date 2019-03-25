/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.repository;

import com.example.overtime.entity.Overtime;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pandu
 */
public interface OvertimeRepo extends CrudRepository<Overtime, String> {

    @Modifying
    @Query(value = "DELETE OVERTIME WHERE id='?'1", nativeQuery = true)
    public void deleteWithId(String id);

    @Modifying
    @Query(value = "SELECT FROM  WHERE STATUS = 'STA01' and timesheet in(from TimeSheet where employee = '?'1)", nativeQuery = true)
    public Overtime getStatus(String id);

    @Modifying
    @Query(value = "SELECT FROM  WHERE STATUS != 'STA01' and timesheet in(from TimeSheet where employee = '?'1)", nativeQuery = true)
    public Overtime getHistory(String id);
}
