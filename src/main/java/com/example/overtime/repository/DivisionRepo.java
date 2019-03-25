package com.example.overtime.repository;

import com.example.overtime.entity.Division;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * DivisionRepo
 */
public interface DivisionRepo extends CrudRepository<Division, String> {

    @Modifying
    @Query(value = "DELETE DIVISION WHERE id=?1", nativeQuery = true)
    public void deleteWithId(String id);
}
