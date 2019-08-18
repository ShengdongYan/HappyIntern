package com.dongdong.internship.Controller;
import com.dongdong.internship.bean.*;
import com.dongdong.internship.mapper.EnterpriseMapper;
import com.dongdong.internship.mapper.ReportMapper;
import com.dongdong.internship.mapper.SupervisorMapper;
import com.dongdong.internship.util.ResultUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.corba.se.idl.InterfaceGen;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-15
 * @Version 1.0
 */
@Controller
@RequestMapping("/supervisor")
@SessionAttributes(value= {"supervisor","supid","student"},types=
        {com.dongdong.internship.bean.Enterprise.class,String.class, Student.class})
public class SupervisorController {

    @Autowired
    private SupervisorMapper supervisorMapper;

    @RequestMapping("/index")
    public String  studentIndex(){

        return "supervisor/supervisorIndex";
    }
    @RequestMapping("/registPage")
    public String  studentRegist(){

        return "supervisor/supervisorRegist";
    }

    @RequestMapping("/loginPage")
    public String  studentLoginPage(){

        return "supervisor/supervisorLogin";
    }


    @RequestMapping("/profilePage")
    public String showProfile(){
        return "supervisor/supervisorProfile";
    }

    @RequestMapping("/loginSupervisor")
    public void studentLogin(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String supname = request.getParameter("supname");
        String password = request.getParameter("password");
        Supervisor supervisor = supervisorMapper.querySupervisorByNamePassword(supname,password);

        if(supervisor ==null){
            ResultUtil.feedBack(response,"Wrong username or password ",null,false);
        }
        else {
            System.out.println("登录成功");
            ResultUtil.feedBack(response,"find one ",supervisor,true);
            model.addAttribute("supervisor",supervisor);
            model.addAttribute("supid",supervisor.getSupid());

        }
    }

    @RequestMapping("/registSupervisor")
    public void registSupervisor(HttpServletRequest request , HttpServletResponse response) throws IOException {

        System.out.println(request.getParameter("supname"));
        System.out.println(request.getParameter("school"));
       Supervisor supervisor= supervisorMapper.querySupervisorByNameSchool(request.getParameter("supname"),request.getParameter("school"));
        if (supervisor != null) {
            ResultUtil.feedBack(response,"The user has existes \r\n Please try another username \r\n Or try to find your account by your email",null,false);
        }
        else {
            try {
                 ResultInfo resultInfo = new ResultInfo();

                 Supervisor supervisor1 = new Supervisor();
                 supervisor1.setSupname(request.getParameter("supname"));
                 supervisor1.setPassword(request.getParameter("password"));
                 supervisor1.setSchool(request.getParameter("school"));
                 supervisor1.setSupmail(request.getParameter("supmail"));
                 supervisor1.setSuptitle(request.getParameter("suptitle"));
                 supervisorMapper.registSupervisor(supervisor1);

                resultInfo.setFlag(true);
                resultInfo.setErrorMsg("注册成功");
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(resultInfo);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(json);
            } catch (NumberFormatException ex) {
                String errorMsg = "";
                errorMsg += ex.getMessage() + "\r\n";
                StackTraceElement[] trace = ex.getStackTrace();
                for (StackTraceElement s : trace) {
                    errorMsg += "\tat " + s + "\r\n";
                }
                ResultUtil.feedBack(response, errorMsg, null, false);
            }
        }
    }

    @RequestMapping("/updateSupervisor")
    public void updateSupervisor(Model model, HttpServletRequest request, ModelMap modelMap, SessionStatus sessionStatus, HttpServletResponse response) throws IOException {

        Supervisor supervisor= supervisorMapper.querySupervisorByNameSchool(request.getParameter("supname"),request.getParameter("school"));
        if (supervisor != null) {
            ResultUtil.feedBack(response,"The user has existes \r\n Please try another username \r\n Or try to find your account by your email",null,false);
        }
        else {
            try {
                ResultInfo resultInfo = new ResultInfo();
                Supervisor supervisor1 = new Supervisor();
                supervisor1.setSupid((Integer) modelMap.get("supid"));
                supervisor1.setSupname(request.getParameter("supname"));
                supervisor1.setPassword(request.getParameter("password"));
                supervisor1.setSchool(request.getParameter("school"));
                supervisor1.setSupmail(request.getParameter("supmail"));
                supervisor1.setSuptitle(request.getParameter("suptitle"));

                supervisorMapper.updateSupervisor(supervisor1);
                sessionStatus.setComplete();
                resultInfo.setFlag(true);
                resultInfo.setErrorMsg("注册成功");
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(resultInfo);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(json);
            } catch (NumberFormatException ex) {
                String errorMsg = "";
                errorMsg += ex.getMessage() + "\r\n";
                StackTraceElement[] trace = ex.getStackTrace();
                for (StackTraceElement s : trace) {
                    errorMsg += "\tat " + s + "\r\n";
                }
                ResultUtil.feedBack(response, errorMsg, null, false);
            }
        }
    }


    @RequestMapping("/logOutSupervisor")
    public void studentLogOut(SessionStatus sessionStatus, HttpServletResponse response) throws IOException {
        sessionStatus.setComplete();
        ResultUtil.feedBack(response,"logout successful",null,true);
    }

    @Autowired
    private ReportMapper reportMapper;


    @RequestMapping("/listReport")
    public  void listReport(ModelMap modelMap, HttpServletResponse response) throws IOException {
       Supervisor supervisor = (Supervisor) modelMap.get("supervisor");
        Integer supid = supervisor.getSupid();
       List<Report> reports = reportMapper.queryReportBySupid(supid);
        ResultUtil.feedBack(response,"",reports,true);
    }



    @RequestMapping("/addFeedback")
    public  String  addFeedback(@Param("reportid")Integer reportid, @Param("feedback")String feedback){
        reportMapper.addFeedback(reportid,feedback);
        return "supervisor/supervisorIndex";
    }

    @RequestMapping("/showContent")
    public void showContent(@Param("reportid")Integer reportid,HttpServletResponse response) throws IOException {
        String content = reportMapper.queryContent(reportid);
        ResultUtil.feedBack(response,"",content,true);
    }

    @RequestMapping("/showFeedback")
    public void showFeedback(@Param("reportid")Integer reportid,HttpServletResponse response) throws IOException {
        String feedback = reportMapper.queryFeedback(reportid);
        ResultUtil.feedBack(response,"",feedback,true);
    }


}
