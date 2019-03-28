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
import com.example.overtime.serviceimpl.UploadFileResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.mail.Quota.Resource;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        // if (session.getAttribute("loginses").toString() == null) {
        // return "login";
        // }
        return "error";
    }

    @GetMapping("/")
    public String index(Model model) {

        // FOR SOME REASSON I DISSABLE SOME OF FUNCTION TO REDUCE THE LOADING TIME OF
        // THE APP, CC:PANDU
        model.addAttribute("updaterole", new Employee());
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
        // String id = "EMP1";
        // model.addAttribute("userhistory", odao.findHistoryByUser(id));
        // model.addAttribute("userstatus", odao.findStatusByUser(id));
        return "pages/content";
    }

    @GetMapping("/upload")
    public String upload() {
        return "/upload";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        Employee employee = fileStorageService.storeFile(file);

//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(employee.getId())
//                .toUriString();
//
//        new UploadFileResponse(employee.getName(), fileDownloadUri,
//                file.getContentType(), file.getSize());
        return "redirect:/profile";
    }

//    @PostMapping("/uploadMultipleFiles")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        Employee employee = fileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + employee.getName() + "\"")
                .body(new ByteArrayResource(employee.getPhoto()));
    }

    @GetMapping("/lihatFile")
    public ResponseEntity<byte[]> getImage() throws IOException {
        Employee employee = fileStorageService.getFile("EMP2");

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(employee.getPhoto());
    }

    @GetMapping("/contentadmin")
    public String contadm(Model model) {
        return "pages/testadmin";
    }

    @GetMapping("/changerole")
    public String changerole(Model model) {
        model.addAttribute("empdata", edao.findAll());
        model.addAttribute("divdata", ddao.findAll());
        model.addAttribute("sitedata", sdao.findAll());
        model.addAttribute("jobdata", jdao.findAll());

        return "pages/adminUserAccess";
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
        model.addAttribute("empdata", edao.findAll());
        model.addAttribute("divdata", ddao.findAll());
        model.addAttribute("sitedata", sdao.findAll());
        model.addAttribute("jobdata", jdao.findAll());

        return "pages/adminCreateUser";
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
    public String status(HttpSession session, Model model) {
        String data = (String) session.getAttribute("loginses");
        System.out.println("DATANYA : " + data);
        model.addAttribute("statusdata", odao.findStatusByUser(data));
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
            emailService.sendEmail("pandu4431@gmail.com", "BABI");
        } catch (Exception ex) {
            log.info("error" + ex.getMessage());
        }
        return "redirect:/";
    }

    public void UploadPhoto() {
        String folderName = "resources";
        // String uploadPath = request.getServletContext().getRealPath("tf-signature") +
        // File.separator + folderName;
        String pathFile = "D:/372267-200.png";
        File file = new File("D:/372267-200.png");

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

            if (BCrypt.checkpw(password, employee.getPassword())) {
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

    @RequestMapping(value = "/updaterole", method = RequestMethod.POST)
    public String updateRole(@ModelAttribute("updaterole") Employee employee) {
        edao.save(employee);
        return "redirect:/";
    }

    @RequestMapping(value = "/empsave", method = RequestMethod.POST)
    public String empSave(String id, @RequestParam("tf-name") String name, @RequestParam("tf-address") String address,
            @RequestParam("tf-salary") String salary, @RequestParam("tf-email") String email, String password,
            String activation, @RequestParam("tf-manager") String manager, @RequestParam("cb-division") String division,
            @RequestParam("cb-site") String site, @RequestParam("cb-job") String job) throws Exception {
        password = "EMP" + email;
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        String subject = "ACCOUNT ACTIVATION";
        edao.save(new Employee("id", name, address, new Integer(Integer.valueOf(salary)), email, passwordHash,
                new Integer("0"), new Employee(manager), new Division(division), new Site(site), new Job(job)));
        emailService.sendEmail(email, subject);
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

    // @RequestMapping(value = "/ovtdelete", method = RequestMethod.POST)
    // public String ovtDelete(@ModelAttribute("ovtdelete") Overtime ovt) {
    // odao.deleteById(ovt.getId());
    // return "redirect:/";
    // }
    @RequestMapping(value = "/ovtdelete", method = RequestMethod.POST)
    public String ovtDelete(@RequestParam() String id) {
        odao.deleteById(id);
        return "redirect:/";
    }

    // @RequestMapping(value = "/ovtaccept")
    // public String ovtAccept(@RequestParam) {
    // return "redirect:/";
    // }
    @RequestMapping(value = "/ovtaccept")
    public String ovtAccept(@RequestParam("AotId") String id, @RequestParam("Aotdate") String date,
            @RequestParam("Aotduration") String timeduration, @RequestParam("Aotdesc") String keterangan,
            @RequestParam("Atimesheet") String timesheet) throws NumberFormatException, ParseException {
        System.out.println("INI IDINYA  HAHA " + id);
        odao.save(new Overtime(id, sdf.parse(date), Integer.valueOf(timeduration), keterangan, new TimeSheet(timesheet),
                new Status("STA02")));
        return "redirect:/approval";
    }

    @RequestMapping(value = "/ovtreject", method = RequestMethod.POST)
    public String ovtReject(@RequestParam("AotId") String id, @RequestParam("Aotdate") String date,
            @RequestParam("Aotduration") String timeduration, @RequestParam("Aotdesc") String keterangan,
            @RequestParam("Atimesheet") String timesheet) throws NumberFormatException, ParseException {
        System.out.println("INI IDINYA  HAHA " + id);
        odao.save(new Overtime(id, sdf.parse(date), Integer.valueOf(timeduration), keterangan, new TimeSheet(timesheet),
                new Status("STA03")));
        return "redirect:/approval";
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
