package com.example.overtime.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.overtime.entity.Overtime;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OvertimeController {

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

    @RequestMapping(value = "/ovtsave", method = RequestMethod.POST)
    public String ovtSave(String id, @RequestParam("tf-date") String date,
            @RequestParam("tf-duration") String timeduration, @RequestParam("tf-description") String keterangan,
            @RequestParam("tf-timesheet") String timesheet, String status)
            throws NumberFormatException, ParseException {
        odao.save(new Overtime("id", sdf.parse(date), Integer.valueOf(timeduration), keterangan,
                new TimeSheet(timesheet), new Status("STA01")));
        return "redirect:/";
    }

    @RequestMapping(value = "/ovtdelete", method = RequestMethod.POST)
    public String ovtDelete(@RequestParam() String id) {
        odao.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/ovtaccept")
    public String ovtAccept(@RequestParam("AotId") String id, @RequestParam("Aotdate") String date,
            @RequestParam("Aotduration") String timeduration, @RequestParam("Aotdesc") String keterangan,
            @RequestParam("Atimesheet") String timesheet) throws NumberFormatException, ParseException {
        System.out.println("INI IDINYA  HAHA " + id);
        odao.save(new Overtime(id, sdf.parse(date), Integer.valueOf(timeduration), keterangan, new TimeSheet(timesheet),
                new Status("STA02")));
        TimeSheet ts = tdao.findById(timesheet);
        String emailFromTs = ts.getEmployee().getEmail();
        String employeeFromTs = ts.getEmployee().getName();
        try {
            emailService.sendEmail(emailFromTs, "OVERTIME " + id + " ACCEPTED", employeeFromTs, "LOGIN",
                    "http://localhost:8081/login");
        } catch (Exception ex) {
            log.info("error" + ex.getMessage());
        }
        return "redirect:/approval";
    }

    @RequestMapping(value = "/ovtreject", method = RequestMethod.POST)
    public String ovtReject(@RequestParam("AotId") String id, @RequestParam("Aotdate") String date,
            @RequestParam("Aotduration") String timeduration, @RequestParam("Aotdesc") String keterangan,
            @RequestParam("Atimesheet") String timesheet) throws NumberFormatException, ParseException {
        System.out.println("INI IDINYA  HAHA " + id);
        odao.save(new Overtime(id, sdf.parse(date), Integer.valueOf(timeduration), keterangan, new TimeSheet(timesheet),
                new Status("STA03")));
        TimeSheet ts = tdao.findById(timesheet);
        String emailFromTs = ts.getEmployee().getEmail();
        String employeeFromTs = ts.getEmployee().getName();
        try {
            emailService.sendEmail(emailFromTs, "OVERTIME " + id + " REJECTION", employeeFromTs, "LOGIN",
                    "http://localhost:8081/login");
        } catch (Exception ex) {
            log.info("error" + ex.getMessage());
        }
        return "redirect:/approval";
    }

    @RequestMapping(value = "/ovtdelete/{id}", method = RequestMethod.GET)
    public ModelAndView ovtdelete(@PathVariable String id, ModelMap model) {
        odao.deleteById(id);
        return new ModelAndView("redirect:/");
    }
}