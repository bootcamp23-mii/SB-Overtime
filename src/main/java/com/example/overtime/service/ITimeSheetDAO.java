/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.service;

import com.example.overtime.entity.TimeSheet;

/**
 *
 * @author Pandu
 */
public interface ITimeSheetDAO {

    Iterable<TimeSheet> findAll();

    TimeSheet save(TimeSheet ts);

    TimeSheet findById(String id);

    TimeSheet activeTimeSheet(String name);

    void deleteById(String id);
}
