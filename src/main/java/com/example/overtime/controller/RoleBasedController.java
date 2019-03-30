package com.example.overtime.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.overtime.entity.Division;
import com.example.overtime.entity.Employee;
import com.example.overtime.entity.Job;
import com.example.overtime.entity.Site;
import com.example.overtime.service.BCrypt;
import com.example.overtime.service.FileStorageService;
import com.example.overtime.serviceimpl.DivisionDAO;
import com.example.overtime.serviceimpl.EmployeeDAO;
import com.example.overtime.serviceimpl.JobDAO;
import com.example.overtime.serviceimpl.OvertimeDAO;
import com.example.overtime.serviceimpl.SiteDAO;
import com.example.overtime.serviceimpl.TimeSheetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    private FileStorageService fileStorageService;

    // ADMIN
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

    // ALL ROLE
    @RequestMapping(value = "/changepass", method = RequestMethod.POST)
    public String changepass(HttpSession session, @RequestParam("oldpass") String oldpass,
            @RequestParam("newpass") String newpass, @RequestParam("newretype") String newpass2) {
        String userid = session.getAttribute("loginses").toString();
        System.out.println(oldpass + " " + newpass + " " + newpass2 + " " + userid);
        Employee ep = edao.findById(userid);
        String id = ep.getId();
        String name = ep.getName();
        String address = ep.getAddress();
        String salary = ep.getSalary().toString();
        String email = ep.getEmail();
        // String password = ep.getPassword();
        String manager = ep.getManager().getId();
        String division = ep.getDivision().getId();
        String site = ep.getSite().getId();
        String job = ep.getJob().getId();

        System.out.println(id + " " + name + " " + address + " " + salary + " " + email + " " + manager + " " + division
                + " " + site + " " + job);

        if (BCrypt.checkpw(oldpass, ep.getPassword()) && newpass.equals(newpass2)) {
            String passwordHash = BCrypt.hashpw(newpass, BCrypt.gensalt());
            edao.save(new Employee(id, name, address, new Integer(Integer.valueOf(salary)), email, passwordHash,
                    new Integer("1"), new Employee(manager), new Division(division), new Site(site), new Job(job)));
        }
        return "redirect:/";
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        Employee employee = fileStorageService.getFile(fileId);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + employee.getName() + "\"")
                .body(new ByteArrayResource(employee.getPhoto()));
    }

    @GetMapping("/lihatFile")
    public ResponseEntity<byte[]> getImage(HttpSession session) throws IOException {

        String idEmployee = session.getAttribute("loginses").toString();
        Employee employee = fileStorageService.getFile(idEmployee);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(employee.getPhoto());
    }

}