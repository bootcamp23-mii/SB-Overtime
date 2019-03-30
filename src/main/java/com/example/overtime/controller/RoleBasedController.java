package com.example.overtime.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.overtime.entity.Division;
import com.example.overtime.entity.Employee;
import com.example.overtime.entity.Job;
import com.example.overtime.entity.Overtime;
import com.example.overtime.entity.Site;
import com.example.overtime.entity.Status;
import com.example.overtime.entity.TimeSheet;
import com.example.overtime.service.FileStorageService;
import com.example.overtime.serviceimpl.DivisionDAO;
import com.example.overtime.serviceimpl.EmailService;
import com.example.overtime.serviceimpl.EmployeeDAO;
import com.example.overtime.serviceimpl.JobDAO;
import com.example.overtime.serviceimpl.OvertimeDAO;
import com.example.overtime.serviceimpl.SiteDAO;
import com.example.overtime.serviceimpl.TimeSheetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoleBasedController {

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

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/changerole")
    public String changerole(Model model) {
        model.addAttribute("empdata", edao.findAll());
        model.addAttribute("divdata", ddao.findAll());
        model.addAttribute("sitedata", sdao.findAll());
        model.addAttribute("jobdata", jdao.findAll());
        return "pages/adminUserAccess";
    }

    @GetMapping("/createuser")
    public String createuser(Model model) {
        model.addAttribute("empdata", edao.findAll());
        model.addAttribute("divdata", ddao.findAll());
        model.addAttribute("sitedata", sdao.findAll());
        model.addAttribute("jobdata", jdao.findAll());
        return "pages/adminCreateUser";
    }

    @RequestMapping(value = "/updaterole", method = RequestMethod.POST)
    public String updateRole(@RequestParam("UAid") String ids, @RequestParam("UAjob") String newJob,
            @ModelAttribute("updaterole") Employee employee) {

        Employee ep = edao.findById(ids);
        String id = ep.getId();
        String name = ep.getName();
        String address = ep.getAddress();
        String salary = ep.getSalary().toString();
        String email = ep.getEmail();
        String password = ep.getPassword();
        byte[] photos = ep.getPhoto();
        int activation = ep.getActivation();
        String manager = ep.getManager().getId();
        String division = ep.getDivision().getId();
        String site = ep.getSite().getId();
        String job = ep.getJob().getId();

        System.out.println(id + " " + name + " " + address + " " + salary + " " + email + " " + manager + " " + division
                + " " + site + " " + job);

        edao.save(new Employee(id, name, address, new Integer(Integer.valueOf(salary)), email, password, photos,
                activation, new Employee(manager), new Division(division), new Site(site), new Job(newJob)));

        return "redirect:/";
    }

}