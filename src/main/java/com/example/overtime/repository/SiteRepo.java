/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.repository;

import com.example.overtime.entity.Site;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pandu
 */
public interface SiteRepo extends CrudRepository<Site, Integer >{
    
}
