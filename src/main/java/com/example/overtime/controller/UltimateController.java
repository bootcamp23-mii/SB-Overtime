/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.overtime.entity.Division;
import com.example.overtime.entity.Employee;
import com.example.overtime.entity.Job;
import com.example.overtime.entity.Site;
import com.example.overtime.entity.TimeSheet;
import com.example.overtime.service.BCrypt;
import com.example.overtime.service.FileStorageService;
import com.example.overtime.serviceimpl.DivisionDAO;
import com.example.overtime.serviceimpl.EmailService;
import com.example.overtime.serviceimpl.EmployeeDAO;
import com.example.overtime.serviceimpl.JobDAO;
import com.example.overtime.serviceimpl.OvertimeDAO;
import com.example.overtime.serviceimpl.SiteDAO;
import com.example.overtime.serviceimpl.TimeSheetDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Pandu
 */
@Controller
public class UltimateController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat getMonth = new SimpleDateFormat("MMM");
    SimpleDateFormat getYear = new SimpleDateFormat("yy");

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

    // ==========PAGE CONTROLLER==========
    @GetMapping("/*")
    public String indexnull(Model model, HttpSession session) {
        return "error";
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {

        Date date = new Date();
        if (session.getAttribute("loginses") != null) {
            // FOR SOME REASSON I DISSABLE SOME OF FUNCTION TO REDUCE THE LOADING TIME OF
            model.addAttribute("updaterole", new Employee());
            model.addAttribute("divdata", ddao.findAll());
            session.setAttribute("notification", odao.findStatusByManager(session.getAttribute("loginses").toString()));
            session.setAttribute("logindata", edao.findById(session.getAttribute("loginses").toString()));

            if (tdao.activeTimeSheet(session.getAttribute("loginses").toString() + getMonth.format(date).toString()
                    + getYear.format(date).toString()) != null) {
                session.setAttribute("activetimesheet", tdao.activeTimeSheet(
                        session.getAttribute("loginses").toString() + getMonth.format(date) + getYear.format(date)));
            } else {
                session.setAttribute("activetimesheet", new TimeSheet("--", "No Active TimeSheet", date,
                        new Employee(session.getAttribute("loginses").toString())));
            }

            return "pages/content";
        } else {
            return "login";
        }
    }

    @PostMapping("/uploadFile")
    public String uploadFile(HttpSession session, @RequestParam("file") MultipartFile file) {
        String idEmployee = session.getAttribute("loginses").toString();
        Employee employee = fileStorageService.storeFile(idEmployee, file);
        return "redirect:/profile";
    }

    @GetMapping("/approval")
    public String approval(HttpSession session, Model model) {
        String data = (String) session.getAttribute("loginses");
        System.out.println("DATANYA : " + data);
        model.addAttribute("approvaldata", odao.findStatusByManager(data));
        return "pages/approvalManager";
    }

    @GetMapping("/history")
    public String history(HttpSession session, Model model) {
        String wewant = (String) session.getAttribute("loginses");
        System.out.println("WEWANT ADALAH" + wewant);
        model.addAttribute("historydata", odao.findHistoryByUser(wewant));
        return "pages/history";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        String idEmployee = (String) session.getAttribute("loginses");
        System.out.println(idEmployee);
        model.addAttribute("employeedata", edao.findById(idEmployee));
        return "pages/profile";
    }

    @GetMapping("/request")
    public String request(Model model) {
        return "pages/request";
    }

    @GetMapping("/status")
    public String status(HttpSession session, Model model) {
        String data = (String) session.getAttribute("loginses");
        System.out.println("DATANYA : " + data);
        model.addAttribute("statusdata", odao.findStatusByUser(data));
        return "pages/status";
    }

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

    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        if (session.getAttribute("loginses") != null) {
            session.removeAttribute("loginses");
        }
        return "login";
    }

    @GetMapping("/activation")
    public String activation(@RequestParam("xd") String hashString, Model model) {
        // CEK KETERSEDIAAN UNAME dan EMAIL
        Employee e = edao.findToken(hashString);
        String id = e.getId();
        String name = e.getName();
        String address = e.getAddress();
        String salary = e.getSalary().toString();
        String email = e.getEmail();
        String password = e.getPassword();
        String manager = e.getManager().getId();
        String division = e.getDivision().getId();
        String site = e.getSite().getId();
        String job = e.getJob().getId();

        System.out.println(email);
        if (email != null) {
            edao.save(new Employee(id, name, address, new Integer(Integer.valueOf(salary)), email, password,
                    new Integer("1"), new Employee(manager), new Division(division), new Site(site), new Job(job)));
            return "pages/activationUser";
        } else {
            return "pages/login";
        }
    }

    // ==========FUNCTIONAL TOOLS CONTROL==========
    @PostMapping("/loginmed")
    public String checkLogin(Model model, @RequestParam("loginId") String id,
            @RequestParam("loginPass") String password, HttpServletRequest request, HttpSession session) {

        if (edao.findById(id) != null) {
            Employee employee = edao.findById(id);
            String activeStatus = employee.getActivation().toString();

            if (BCrypt.checkpw(password, employee.getPassword()) && activeStatus.equals("1")) {

                String role = edao.findById(id).getJob().getId();

                System.out.println(role);

                session.setAttribute("loginses", id);
                session.setAttribute("roleloginses", role);

            } else {
                return "redirect:/login";
            }
        }
        return "redirect:/";
    }

    // TESTER
    @GetMapping("/tokentest")
    public String tokentest(@RequestParam("xd") String hashString, Model model) {
        Employee ed = edao.findToken(hashString);
        String cekName = ed.getEmail();
        if (cekName != null) {
            System.out.println(cekName);
        } else {
            System.out.println("NO DATA FOUND");
        }
        return cekName;
    }

    @GetMapping("/sendemail")
    public String signUpComplete() {

        String linkformail = "pandu4431@gmail.com";
        try {
            emailService.sendEmail("pandu4431@gmail.com", "ACCOUNT ACTIVATION TEST", "Pandu", "Activate",
                    "http://localhost:8081/activation?xd=" + linkformail);
        } catch (Exception ex) {
            log.info("error" + ex.getMessage());
        }
        return "redirect:/";
    }

    public void UploadPhoto() {
        String pathFile = "D:/372267-200.png";
        File file = new File(pathFile);

        byte[] b = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);
            for (int i = 0; i < b.length; i++) {
                System.out.print((char) b[i]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            e.printStackTrace();
        } catch (IOException e1) {
            System.out.println("Error Reading The File.");
            e1.printStackTrace();
        }
    }

}
