/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.service;

import com.example.overtime.entity.Job;

/**
 *
 * @author Pandu
 */
public interface IJobDAO {
    Iterable<Job> findAll();

    Job save(Job jb);


    void findById(String id);

    void deleteById(String id);
}
