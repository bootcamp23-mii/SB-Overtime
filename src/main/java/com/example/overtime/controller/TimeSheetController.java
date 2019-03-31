package com.example.overtime.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.overtime.entity.Employee;
import com.example.overtime.entity.Overtime;
import com.example.overtime.entity.Status;
import com.example.overtime.entity.TimeSheet;
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
public class TimeSheetController {

    SimpleDateFormat getMonth = new SimpleDateFormat("MMM");
    SimpleDateFormat getYears = new SimpleDateFormat("yy");

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

    @RequestMapping(value = "/tssave", method = RequestMethod.POST)
    public String tsSave(HttpSession session, @ModelAttribute("tssave") TimeSheet ts) {
        Date date = new Date();
        String month = getMonth.format(date);
        String year = getYears.format(date);
        String empIndex = session.getAttribute("loginses").toString();
        tdao.save(new TimeSheet("id", empIndex + month + year, date, new Employee(empIndex), new Status("STA01")));
        return "redirect:/";
    }
    
    @RequestMapping(value = "/tsaccept", method = RequestMethod.POST)
    public String tsAccept(HttpSession session, @ModelAttribute("tssave") TimeSheet ts) {
        Date date = new Date();
        String month = getMonth.format(date);
        String year = getYears.format(date);
        String empIndex = session.getAttribute("loginses").toString();
        tdao.save(new TimeSheet("id", empIndex + month + year, date, new Employee(empIndex), new Status("STA02")));
        return "redirect:/";
    }
    
    @RequestMapping(value = "/tsreject", method = RequestMethod.POST)
    public String tsReject(HttpSession session, @ModelAttribute("tssave") TimeSheet ts) {
        Date date = new Date();
        String month = getMonth.format(date);
        String year = getYears.format(date);
        String empIndex = session.getAttribute("loginses").toString();
        tdao.save(new TimeSheet("id", empIndex + month + year, date, new Employee(empIndex), new Status("STA03")));
        return "redirect:/";
    }

    @RequestMapping(value = "/tsdelete", method = RequestMethod.POST)
    public String tsDelete(@ModelAttribute("tsdelete") TimeSheet ts) {
        tdao.deleteById(ts.getId());
        return "redirect:/";
    }
}
