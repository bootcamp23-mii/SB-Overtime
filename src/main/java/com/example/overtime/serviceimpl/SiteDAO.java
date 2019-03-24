/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.serviceimpl;

import com.example.overtime.entity.Site;
import com.example.overtime.repository.SiteRepo;
import com.example.overtime.service.ISiteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pandu
 */
@Service
public class SiteDAO implements ISiteDAO {

    @Autowired
    SiteRepo srep;

    @Override
    public Iterable<Site> findAll() {
        return srep.findAll();
    }

    @Override
    public Site save(Site st) {
        return srep.save(st);
    }

    @Override
    public void findById(String id) {
        srep.findById(id);
    }

    @Override
    public void deleteById(String id) {
        srep.deleteWithId(id);
    }

}
