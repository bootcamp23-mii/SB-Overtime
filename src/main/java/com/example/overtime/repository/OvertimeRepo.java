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
    @Query(value = "DELETE OVERTIME WHERE id='?1'", nativeQuery = true)
    public void deleteWithId(String id);

    @Query(value = "select * from tb_t_overtime where status = 'STA01' and timesheet in(select id from tb_t_time_sheet where employee = ?1)", nativeQuery = true)
    public Iterable<Overtime> getStatus(String id);
    
    @Query(value = "SELECT * from tb_t_overtime where status = 'STA01' and timesheet in(select id from tb_t_time_sheet where employee in(select id from tb_m_employee where manager =?1))", nativeQuery = true)
    public Iterable<Overtime> getStatusByManager(String id);

    @Query(value = "select * from tb_t_overtime where status != 'STA01' and timesheet in(select id from tb_t_time_sheet where employee = ?1)", nativeQuery = true)
    public Iterable<Overtime> getHistory(String id);

    @Query(value = "update tb_t_overtime set status ='STA02' where id = ?1", nativeQuery = true)
    public Overtime acceptOvertime(String id);

    @Query(value = "update tb_t_overtime set status ='STA03' where id = ?1", nativeQuery = true)
    public Overtime rejectOvertime(String id);
}
