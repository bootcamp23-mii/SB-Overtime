/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.repository;

import com.example.overtime.entity.Job;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pandu
 */
public interface JobRepo extends CrudRepository<Job, String> {
    @Modifying
    @Query(value = "DELETE JOB WHERE id='?'1", nativeQuery = true)
    public void deleteWithId(String id);
}
