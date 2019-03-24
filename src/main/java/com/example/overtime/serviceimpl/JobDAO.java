/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.serviceimpl;

import com.example.overtime.entity.Job;
import com.example.overtime.repository.JobRepo;
import com.example.overtime.service.IJobDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pandu
 */
@Service
public class JobDAO implements IJobDAO {

    @Autowired
    JobRepo jrep;

    @Override
    public Iterable<Job> findAll() {
        return jrep.findAll();
    }

    @Override
    public Job save(Job jb) {
        return jrep.save(jb);
    }

    @Override
    public void findById(String id) {
        jrep.findById(id);
    }

    @Override
    public void deleteById(String id) {
        jrep.deleteWithId(id);
    }

}
