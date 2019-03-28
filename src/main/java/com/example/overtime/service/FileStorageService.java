/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.service;

import com.example.overtime.entity.Employee;
import com.example.overtime.exception.FileStorageException;
import com.example.overtime.exception.MyFileNotFoundException;
import com.example.overtime.repository.DBRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author milhamafemi
 */
@Service
public class FileStorageService {
    @Autowired
    private DBRepository dbr;

     public Employee storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Employee e = dbr.findById("EMP1").get();
            Employee employee = new Employee(e.getEmail(), e.getName(), e.getAddress(), 
                    e.getSalary(), e.getEmail(), e.getPassword(), 
                    file.getBytes(), e.getActivation(),e.getManager(), e.getDivision(), e.getSite(), e.getJob());

            return dbr.save(employee);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Employee getFile(String fileId) {
        return dbr.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}
