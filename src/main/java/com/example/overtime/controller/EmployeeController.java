package com.example.overtime.controller;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.overtime.entity.Division;
import com.example.overtime.entity.Employee;
import com.example.overtime.entity.Job;
import com.example.overtime.entity.Site;
import com.example.overtime.service.BCrypt;
import com.example.overtime.serviceimpl.DivisionDAO;
import com.example.overtime.serviceimpl.EmailService;
import com.example.overtime.serviceimpl.EmployeeDAO;
import com.example.overtime.serviceimpl.JobDAO;
import com.example.overtime.serviceimpl.OvertimeDAO;
import com.example.overtime.serviceimpl.SiteDAO;
import com.example.overtime.serviceimpl.TimeSheetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static Logger log = LoggerFactory.getLogger(UltimateController.class);

    @Autowired
    DivisionDAO ddao;

    @Autowired
    EmployeeDAO edao;

    @Autowired
    JobDAO jdao;

    @Autowired
    OvertimeDAO odao;

    @Autowired
    SiteDAO sdao;

    @Autowired
    TimeSheetDAO tdao;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/empsave", method = RequestMethod.POST)
    public String empSave(String id, @RequestParam("tf-name") String name, @RequestParam("tf-address") String address,
            @RequestParam("tf-salary") String salary, @RequestParam("tf-email") String email, String password,
            String activation, @RequestParam("cb-man") String manager, @RequestParam("cb-division") String division,
            @RequestParam("cb-site") String site, @RequestParam("cb-job") String job) throws Exception {
        password = "EMP" + email;
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        String token = email.split("@")[0];
        String subject = "ACCOUNT ACTIVATION";
        String linkformail = "http://localhost:8081/activation?xd=" + token;
        System.out.println(linkformail);

        edao.save(new Employee("id", name, address, new Integer(Integer.valueOf(salary)), email, passwordHash,
                new Integer("0"), new Employee(manager), new Division(division), new Site(site), new Job(job)));
        Employee getemp = edao.findToken(email);
        String getid = getemp.getId();
        emailService.sendEmail(email, subject, name, "Congratulation, your Account have been created, \nWith Username :"+getid+"\nAnd Password : "+password+"\n click the button below to activate your account", linkformail);
        return "redirect:/";
    }

    @RequestMapping(value = "/empdelete", method = RequestMethod.POST)
    public String empDelete(@ModelAttribute("empdelete") Employee emp) {
        edao.deleteById(emp.getId());
        return "redirect:/";
    }

    @RequestMapping(value = "/empdelete/{id}", method = RequestMethod.GET)
    public ModelAndView empdelete(@PathVariable String id, ModelMap model) {
        edao.deleteById(id);
        return new ModelAndView("redirect:/");
    }

}