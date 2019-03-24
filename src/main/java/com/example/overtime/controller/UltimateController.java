/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.overtime.controller;

import javax.servlet.http.HttpServletRequest;

import com.example.overtime.entity.Division;
import com.example.overtime.entity.Employee;
import com.example.overtime.entity.Job;
import com.example.overtime.entity.Overtime;
import com.example.overtime.entity.Site;
import com.example.overtime.entity.TimeSheet;
import com.example.overtime.service.BCrypt;
import com.example.overtime.serviceimpl.DivisionDAO;
import com.example.overtime.serviceimpl.EmailService;
import com.example.overtime.serviceimpl.EmployeeDAO;
import com.example.overtime.serviceimpl.JobDAO;
import com.example.overtime.serviceimpl.OvertimeDAO;
import com.example.overtime.serviceimpl.SiteDAO;
import com.example.overtime.serviceimpl.TimeSheetDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Pandu
 */
@Controller
public class UltimateController {

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

    // ==========PAGE CONTROLLER==========
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("divdata", ddao.findAll());
        model.addAttribute("divsave", new Division());
        model.addAttribute("divdelete", new Division());

        model.addAttribute("empdata", edao.findAll());
        model.addAttribute("empsave", new Employee());
        model.addAttribute("empdelete", new Employee());

        model.addAttribute("jobdata", jdao.findAll());
        model.addAttribute("jobsave", new Job());
        model.addAttribute("jobdelete", new Job());

        model.addAttribute("ovtdata", odao.findAll());
        model.addAttribute("ovtsave", new Overtime());
        model.addAttribute("ovtdelete", new Overtime());

        model.addAttribute("sitedata", sdao.findAll());
        model.addAttribute("sitesave", new Site());
        model.addAttribute("sitedelete", new Site());

        model.addAttribute("tsdata", tdao.findAll());
        model.addAttribute("tssave", new TimeSheet());
        model.addAttribute("tsdelete", new TimeSheet());
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/content")
    public String content(Model model) {
        return "pages/content";
    }

    @GetMapping("/activation")
    public String activation(Model model) {
        return "pages/activationUser";
    }

    @GetMapping("/createuser")
    public String createuser(Model model) {
        return "pages/adminCreateUser";
    }

    @GetMapping("/approval")
    public String approval(Model model) {
        return "pages/approvalManager";
    }

    @GetMapping("/history")
    public String history(Model model) {
        return "pages/history";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return "pages/profile";
    }

    @GetMapping("/request")
    public String request(Model model) {
        return "pages/request";
    }

    @GetMapping("/status")
    public String status(Model model) {
        return "pages/status";
    }

    // @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    // public String detailRefresh(@PathVariable long id, Model model) {
    //
    // T element = service.findOne(id);
    //
    // return "/pages :: ELEMENT"; // fragment
    // }

    // ==========FUNCTIONAL TOOLS CONTROL==========
    @GetMapping("/sendemail")
    public String signUpComplete() {
        try {
            emailService.sendEmail("pandu4431@gmail.com");
        } catch (Exception ex) {
            log.info("error" + ex.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/loginmed")
    public String checkLogin(@RequestParam("loginId") String id, @RequestParam("loginPass") String password,
            HttpServletRequest request) {
        if (edao.findById(id) != null) {
            Employee employee = edao.findById(id);
            if (BCrypt.checkpw(password, employee.getPassword())) {
                request.getSession().setAttribute("loginses", id);
            } else {
                return "redirect:/login";
            }
        }
        return "redirect:/content";
    }

    // REMAPING ALL THE CONTROLLER NEED
    // CONTROLLER LIST
    // GET
    // -GET ALL DATA DIVISION
    // -GET ALL DATA EMPLOYEE
    // -GET ALL DATA OVERTIME
    // -GET ALL DATA SITE
    // -GET ALL DATA JOB
    // -GET ALL DATA TIMESHEET
    //
    // POST
    // -POST DATA
    // -OVERTIME(REQUEST OVERTIME)
    // -POST DATA EMPLOYE(ADMIN)

    // ==========MAPPING ALL CONTROLLER NEED==========
    @RequestMapping(value = "/divsave", method = RequestMethod.POST)
    public String divSave(@ModelAttribute("regionsave") Division div) {
        ddao.save(div);
        return "redirect:/";
    }

    @RequestMapping(value = "/divdelete", method = RequestMethod.POST)
    public String divDelete(@ModelAttribute("divdelete") Division div) {
        ddao.deleteById(div.getId());
        return "redirect:/";
    }

    @RequestMapping(value = "/divdelete/{id}", method = RequestMethod.GET)
    public ModelAndView divdelete(@PathVariable String id, ModelMap model) {
        ddao.deleteById(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/empsave", method = RequestMethod.POST)
    public String empSave(@ModelAttribute("empsave") Employee emp) {
        edao.save(emp);
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

    @RequestMapping(value = "/jobsave", method = RequestMethod.POST)
    public String jobSave(@ModelAttribute("jobsave") Job job) {
        jdao.save(job);
        return "redirect:/";
    }

    @RequestMapping(value = "/jobdelete", method = RequestMethod.POST)
    public String jobDelete(@ModelAttribute("jobdelete") Job job) {
        jdao.deleteById(job.getId());
        return "redirect:/";
    }

    @RequestMapping(value = "/jobdelete/{id}", method = RequestMethod.GET)
    public ModelAndView jobdelete(@PathVariable String id, ModelMap model) {
        jdao.deleteById(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/ovtsave", method = RequestMethod.POST)
    public String ovtSave(@ModelAttribute("ovtsave") Overtime ovt) {
        odao.save(ovt);
        return "redirect:/";
    }

    @RequestMapping(value = "/ovtdelete", method = RequestMethod.POST)
    public String ovtDelete(@ModelAttribute("ovtdelete") Overtime ovt) {
        odao.deleteById(ovt.getId());
        return "redirect:/";
    }

    @RequestMapping(value = "/ovtdelete/{id}", method = RequestMethod.GET)
    public ModelAndView ovtdelete(@PathVariable String id, ModelMap model) {
        odao.deleteById(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/sitesave", method = RequestMethod.POST)
    public String siteSave(@ModelAttribute("sitesave") Site site) {
        sdao.save(site);
        return "redirect:/";
    }

    @RequestMapping(value = "/sitedelete", method = RequestMethod.POST)
    public String siteDelete(@ModelAttribute("sitedelete") Site site) {
        sdao.deleteById(site.getId());
        return "redirect:/";
    }

    @RequestMapping(value = "/sitedelete/{id}", method = RequestMethod.GET)
    public ModelAndView sitedelete(@PathVariable String id, ModelMap model) {
        sdao.deleteById(id);
        return new ModelAndView("redirect:/");
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
