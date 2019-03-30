/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.overtime.entity.Division;
import com.example.overtime.entity.Employee;
import com.example.overtime.entity.Job;
import com.example.overtime.entity.Overtime;
import com.example.overtime.entity.Site;
import com.example.overtime.entity.Status;
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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Pandu
 */
@Controller
public class UltimateController {

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

    // ==========PAGE CONTROLLER==========
    @GetMapping("/*")
    public String indexnull(Model model, HttpSession session) {
        return "error";
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {

        // FOR SOME REASSON I DISSABLE SOME OF FUNCTION TO REDUCE THE LOADING TIME OF

        model.addAttribute("updaterole", new Employee());
        model.addAttribute("divdata", ddao.findAll());
        if (session.getAttribute("loginses") != null) {
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

    // ==========FUNCTIONAL TOOLS CONTROL==========
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
            emailService.sendEmail("pandu4431@gmail.com", "ACTIVET", "Pandu", "Activate",
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

    @PostMapping("/loginmed")
    public String checkLogin(@RequestParam("loginId") String id, @RequestParam("loginPass") String password,
            HttpServletRequest request, HttpSession session) {

        if (edao.findById(id) != null) {
            Employee employee = edao.findById(id);
            String activeStatus = employee.getActivation().toString();

            if (BCrypt.checkpw(password, employee.getPassword()) && activeStatus.equals("1")) {
                String role = edao.findById(id).getJob().getId();
                String uname = edao.findById(id).getName();
                String uaddress = edao.findById(id).getAddress();
                String usalary = edao.findById(id).getSalary().toString();
                String umail = edao.findById(id).getEmail();
                String umanager = edao.findById(id).getManager().getName();
                String udivision = edao.findById(id).getDivision().getName();
                String usite = edao.findById(id).getSite().getName();

                System.out.println(role);

                session.setAttribute("loginses", id);
                session.setAttribute("roleloginses", role);

                session.setAttribute("uname", uname);
                session.setAttribute("uaddress", uaddress);
                session.setAttribute("usalary", usalary);
                session.setAttribute("umail", umail);
                session.setAttribute("umanager", umanager);
                session.setAttribute("udivision", udivision);
                session.setAttribute("usite", usite);
            } else {
                return "redirect:/login";
            }
        }
        return "redirect:/";
    }

    // ==========MAPPING ALL CONTROLLER NEED==========
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

    @RequestMapping(value = "/tssave", method = RequestMethod.POST)
    public String tsSave(@ModelAttribute("tssave") TimeSheet ts) {
        tdao.save(ts);
        return "redirect:/";
    }

    @RequestMapping(value = "/tsdelete", method = RequestMethod.POST)
    public String tsDelete(@ModelAttribute("tsdelete") TimeSheet ts) {
        tdao.deleteById(ts.getId());
        return "redirect:/";
    }

    @RequestMapping(value = "/tsdelete/{id}", method = RequestMethod.GET)
    public ModelAndView tsdelete(@PathVariable String id, ModelMap model) {
        tdao.deleteById(id);
        return new ModelAndView("redirect:/");
    }

}
