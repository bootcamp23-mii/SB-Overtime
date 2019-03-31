/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.repository;

import com.example.overtime.entity.TimeSheet;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pandu
 */
public interface TimeSheetRepo extends CrudRepository<TimeSheet, String> {

    @Modifying
    @Query(value = "DELETE TIMESHEET WHERE id=?1", nativeQuery = true)
    public void deleteWithId(String id);

    @Query(value = "SELECT * FROM tb_t_time_sheet WHERE name LIKE ?1%", nativeQuery = true)
    public TimeSheet activeTimesheet(String name);

    @Query(value = "SELECT * FROM tb_t_time_sheet WHERE status = 'STA01' and employee IN(SELECT id FROM tb_m_employee WHERE manager=?1)", nativeQuery = true)
    public Iterable<TimeSheet> getTimesheetByMgr(String id);
}
