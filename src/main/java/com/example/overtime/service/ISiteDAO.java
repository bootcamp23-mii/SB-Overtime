/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.service;

import com.example.overtime.entity.Site;

/**
 *
 * @author Pandu
 */
public interface ISiteDAO {
    Iterable<Site> findAll();

    Site save(Site st);

    Site edit(Site st);

    void findById(Integer id);

    void deleteById(Integer id);
}
