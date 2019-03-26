/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.serviceimpl;

import com.example.overtime.entity.Overtime;
import com.example.overtime.repository.OvertimeRepo;
import com.example.overtime.service.IOvertimeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pandu
 */
@Service
public class OvertimeDAO implements IOvertimeDAO {

    @Autowired
    OvertimeRepo orep;

    @Override
    public Iterable<Overtime> findAll() {
        return orep.findAll();
    }

    @Override
    public Overtime save(Overtime ot) {
        return orep.save(ot);
    }

    @Override
    public Overtime findById(String id) {
        return orep.findById(id).get();
    }

    @Override
    public void deleteById(String id) {
        orep.deleteWithId(id);
    }

    @Override
    public Iterable<Overtime> findHistoryByUser(String id) {
        return orep.getHistory(id);
    }

    @Override
    public Overtime findStatusByUser(String id) {
        return orep.getStatus(id);
    }

}
