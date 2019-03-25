/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.service;

import com.example.overtime.entity.Division;

/**
 *
 * @author Pandu
 */
public interface IDivisionDAO {

    Iterable<Division> findAll();

    Division save(Division div);

    Division findById(String id);

    void deleteById(String id);
}
