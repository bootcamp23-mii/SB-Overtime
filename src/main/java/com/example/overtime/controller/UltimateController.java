/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.controller;

import com.example.overtime.serviceimpl.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Pandu
 */
@Controller
public class UltimateController {

    private static Logger log = LoggerFactory.getLogger(UltimateController.class);

    @Autowired
    RegionDAO rdao;

    
    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String index(Model model) {
//        model.addAttribute("regionData", rdao.findAll());
//        model.addAttribute("regionsave", new Regions());
//        model.addAttribute("regionedit", new Regions());
//        model.addAttribute("regiondelete", new Regions());
        return "index";
    }

    @GetMapping("/sendemail")
    public String signUpComplete() {
        try {
            emailService.sendEmail("pandu4431@gmail.com");
        } catch (Exception ex) {
            log.info("error" + ex.getMessage());
        }
        return "redirect:/";
    }
    
    

}
