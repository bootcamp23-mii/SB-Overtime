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

    // Overtime findHistoryByUser(String id);

    // Overtime findStatusByUser(String id);

    void deleteById(String id);
}
