/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.service;

import com.example.overtime.entity.Overtime;

/**
 *
 * @author Pandu
 */
public interface IOvertimeDAO {
    Iterable<Overtime> findAll();

    Overtime save(Overtime ot);

    Overtime findById(String id);

    Overtime acceptOvertime(String id);

    Overtime rejectOvertime(String id);

    Iterable<Overtime> findHistoryByUser(String id);

    Iterable<Overtime> findStatusByUser(String id);

    Iterable<Overtime> findStatusByManager(String id);

    void deleteById(String id);
}
