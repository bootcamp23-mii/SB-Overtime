/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.serviceimpl;

import com.example.overtime.entity.Division;
import com.example.overtime.repository.DivisionRepo;
import com.example.overtime.service.IDivisionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pandu
 */
@Service
public class DivisionDAO implements IDivisionDAO {

    @Autowired
    DivisionRepo drepo;

    @Override
    public Iterable<Division> findAll() {
        return drepo.findAll();
    }

    @Override
    public Division save(Division div) {
        return drepo.save(div);
    }

    @Override
    public Division findById(String id) {
        return drepo.findById(id).get();
    }

    @Override
    public void deleteById(String id) {
        drepo.deleteWithId(id);
    }

}
