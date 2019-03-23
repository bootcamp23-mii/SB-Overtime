package com.example.overtime.repository;

import com.example.overtime.entity.Division;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * DivisionRepo
 */
public interface DivisionRepo extends CrudRepository<Division, Integer >{

}