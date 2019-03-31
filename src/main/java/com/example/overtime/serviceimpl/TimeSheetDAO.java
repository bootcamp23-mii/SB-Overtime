/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.serviceimpl;

import com.example.overtime.entity.TimeSheet;
import com.example.overtime.repository.TimeSheetRepo;
import com.example.overtime.service.ITimeSheetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pandu
 */
@Service
public class TimeSheetDAO implements ITimeSheetDAO {

    @Autowired
    TimeSheetRepo trep;

    @Override
    public Iterable<TimeSheet> findAll() {
        return trep.findAll();
    }

    @Override
    public TimeSheet save(TimeSheet ts) {
        return trep.save(ts);
    }

    @Override
    public TimeSheet findById(String id) {
        return trep.findById(id).get();
    }

    @Override
    public void deleteById(String id) {
        trep.deleteWithId(id);
    }

    @Override
    public TimeSheet activeTimeSheet(String name) {
        return trep.activeTimesheet(name);
    }

}
