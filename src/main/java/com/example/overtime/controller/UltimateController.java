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

    // ==========PAGE CONTROLLER==========
    @GetMapping("/*")
    public String indexnull(Model model) {
        return "error";
    }

    @GetMapping("/")
    public String index(Model model) {

        // FOR SOME REASSON I DISSABLE SOME OF FUNCTION TO REDUCE THE LOADING TIME OF
        // THE APP, CC:PANDU

        model.addAttribute("divdata", ddao.findAll());
        // model.addAttribute("divsave", new Division());
        // model.addAttribute("divdelete", new Division());

        // model.addAttribute("empdata", edao.findAll());
        // model.addAttribute("empsave", new Employee());
        // model.addAttribute("empdelete", new Employee());

        // model.addAttribute("jobdata", jdao.findAll());
        // model.addAttribute("jobsave", new Job());
        // model.addAttribute("jobdelete", new Job());

        // model.addAttribute("ovtdata", odao.findAll());
        // model.addAttribute("ovtsave", new Overtime());
        // model.addAttribute("ovtdelete", new Overtime());

        // model.addAttribute("sitedata", sdao.findAll());
        // model.addAttribute("sitesave", new Site());
        // model.addAttribute("sitedelete", new Site());

        // model.addAttribute("tsdata", tdao.findAll());
        // model.addAttribute("tssave", new TimeSheet());
        // model.addAttribute("tsdelete", new TimeSheet());

        // ==========INDIRECT METHOD==========
        // HARD CODED SAMPLE OF EMP1
        // String id = "EMP1";
        // model.addAttribute("userhistory", odao.findHistoryByUser(id));
        // model.addAttribute("userstatus", odao.findStatusByUser(id));

        return "pages/content";
    }

    @GetMapping("/contentadmin")
    public String contadm(Model model) {
        return "pages/testadmin";
    }

    @GetMapping("/contentmanager")
    public String contman(Model model) {
        return "pages/testmanager";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    // @GetMapping("/content")
    // public String content(Model model) {
    // return "pages/content";
    // }

    @GetMapping("/activation")
    public String activation(Model model) {
        return "pages/activationUser";
    }

    @GetMapping("/createuser")
    public String createuser(Model model) {
        model.addAttribute("divdata", ddao.findAll());
        model.addAttribute("sitedata", sdao.findAll());
        model.addAttribute("jobdata", jdao.findAll());

        return "pages/adminCreateUser";
    }

    @GetMapping("/approval")
    public String approval(Model model) {
        return "pages/approvalManager";
    }

    @GetMapping("/history")
    public String history(Model model) {
        model.addAttribute("historydata", odao.findHistoryByUser("EMP1"));
        // model.addAttribute("historydata", odao.findAll());
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
        model.addAttribute("statusdata", odao.findStatusByUser("EMP2"));
        return "pages/status";
    }

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
            HttpServletRequest request, HttpSession session) {

        if (edao.findById(id) != null) {
            Employee employee = edao.findById(id);

            if (BCrypt.checkpw(password, employee.getPassword())) {
                String role = edao.findById(id).getJob().getId();
                System.out.println(role);
                session.setAttribute("loginses", id);
                session.setAttribute("roleloginses", role);
                // request.getSession().setAttribute("loginses", id);
                // request.getSession().setAttribute("roleloginses", role);
            } else {
                return "redirect:/login";
            }
        }
        return "redirect:/";
    }

    // @GetMapping("/")
    // public String process(Model model, HttpSession session) {
    // @SuppressWarnings("unchecked")
    // List<String> messages = (List<String>)
    // session.getAttribute("MY_SESSION_MESSAGES");

    // if (messages == null) {
    // messages = new ArrayList<>();
    // }
    // model.addAttribute("sessionMessages", messages);

    // return "index";
    // }

    // @PostMapping("/persistMessage")
    // public String persistMessage(@RequestParam("msg") String msg,
    // HttpServletRequest request) {
    // @SuppressWarnings("unchecked")
    // List<String> messages = (List<String>)
    // request.getSession().getAttribute("MY_SESSION_MESSAGES");
    // if (messages == null) {
    // messages = new ArrayList<>();
    // request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
    // }
    // messages.add(msg);
    // request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
    // return "redirect:/";
    // }

    // @PostMapping("/logout")
    // public String destroySession(HttpServletRequest request) {
    // request.getSession().invalidate();
    // return "redirect:/login";
    // }

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
    public String empSave(String id, @RequestParam("tf-name") String name, @RequestParam("tf-address") String address,
            @RequestParam("tf-salary") String salary, @RequestParam("tf-email") String email, String activation,
            @RequestParam("tf-manager") String manager, @RequestParam("cb-division") String division,
            @RequestParam("cb-site") String site, @RequestParam("cb-job") String job) {
        edao.save(new Employee("id", name, address, new Integer(Integer.valueOf(salary)), email, new Integer("0"),
                new Employee(manager), new Division(division), new Site(site), new Job(job)));
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
    public String ovtSave(String id, @RequestParam("tf-date") String date,
            @RequestParam("tf-duration") String timeduration, @RequestParam("tf-description") String keterangan,
            @RequestParam("tf-timesheet") String timesheet, String status)
            throws NumberFormatException, ParseException {
        odao.save(new Overtime("id", sdf.parse(date), Integer.valueOf(timeduration), keterangan,
                new TimeSheet(timesheet), new Status("STA01")));
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
