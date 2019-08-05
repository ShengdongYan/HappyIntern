package com.dongdong.internship.Controller;

import com.dongdong.internship.bean.Enterprise;
import com.dongdong.internship.bean.ResultInfo;
import com.dongdong.internship.bean.Student;
import com.dongdong.internship.mapper.EnterpriseMapper;
import com.dongdong.internship.util.ResultUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
import javax.sound.midi.Soundbank;
import javax.xml.transform.Source;
import java.io.IOException;

/**
 * @Author: ShengdongYan
 * @Date: 2019-07-30
 * @Version 1.0
 */

@Controller
@RequestMapping("/enterprise")
@SessionAttributes(value= {"enterprise","eid","aid"},types=
        {com.dongdong.internship.bean.Enterprise.class,String.class,Integer.class})
public class EnterpriseController {




    @RequestMapping("/index")
    public String  studentIndex(){

        return "enterprise/enterpriseIndex";
    }

    @RequestMapping("/registPage")
    public String  studentRegist(){

        return "enterprise/enterpriseRegist";
    }

    @RequestMapping("/loginPage")
    public String  studentLoginPage(){

        return "enterprise/enterpriseLogin";
    }



    @RequestMapping("/profilePage")
    public String showProfile(@Param("aid")Integer aid, Model model){
        System.out.println("这个AID 是"+aid);
        model.addAttribute(aid);
        return "enterprise/enterpriseProfile";

    }







    @RequestMapping("/showPostPage")
    public  String showPost(ModelMap modelMap){
        Enterprise enterprise= (Enterprise) modelMap.get("student");
        System.out.println(enterprise);
        return "enterprise/enterprisePostPage";
    }



    @RequestMapping("/loginEnterprise")
    public void studentLogin(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ename = request.getParameter("ename");
        String password = request.getParameter("password");
        Enterprise enterprise = enterpriseMapper.queryEnterpriseByNamePassword(ename,password);

        if(enterprise ==null){
            ResultUtil.feedBack(response,"Wrong username or password ",null,false);
        }
        else {
            System.out.println("登录成功");
            ResultUtil.feedBack(response,"find one ",enterprise,true);
            model.addAttribute("enterprise",enterprise);
            model.addAttribute("eid",enterprise.getEid());

        }
    }
    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @RequestMapping("/registEnterprise")
    public void regist(HttpServletRequest request , HttpServletResponse response) throws IOException {
        System.out.println(request.getParameter("ename"));
        System.out.println(enterpriseMapper.queryEnterpriseByName(request.getParameter("ename")));
        Enterprise enterprise2 = enterpriseMapper.queryEnterpriseByName(request.getParameter("ename"));
        if (enterprise2 != null) {
            ResultUtil.feedBack(response,"The user has existes \r\n Please try another username \r\n Or try to find your account by your email",null,false);
        }
        else {
            try {
                ResultInfo resultInfo = new ResultInfo();
                Enterprise enterprise = new Enterprise();
                enterprise.setEname(request.getParameter("ename"));
                enterprise.setEmail(request.getParameter("email"));
                enterprise.setPassword(request.getParameter("password"));
                enterprise.setWebsite(request.getParameter("website"));
                enterpriseMapper.registEnterprise(enterprise);
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

    @RequestMapping("/updateEnterprise")
    public void updateEnterprise(Model model, HttpServletRequest request, ModelMap modelMap, SessionStatus sessionStatus, HttpServletResponse response) throws IOException {
        Enterprise enterprise = enterpriseMapper.queryEnterpriseByName((String)modelMap.get("ename"));
        if(enterprise!=null){
            ResultUtil.feedBack(response,"The user has existes \r\n Please try another username \r\n Or try to find your account by your email",null,false);
        }

        else {
            try {

                enterprise = new Enterprise();
                enterprise.setEid((Integer) modelMap.get("eid"));
                enterprise.setWebsite(request.getParameter("website"));
                enterprise.setEmail(request.getParameter("email"));
                enterprise.setEname(request.getParameter("ename"));
                enterprise.setPassword(request.getParameter("password"));
                enterpriseMapper.updateEnterprise(enterprise);
                sessionStatus.setComplete();
                model.addAttribute("enterprise",enterprise);
                model.addAttribute("eid",enterprise.getEid());
                ResultUtil.feedBack(response,"profile update successful",enterprise,true);
            }catch (NumberFormatException ex) {

                ex.printStackTrace();
                ResultUtil.feedBack(response, "profile update fail", null, false);
            }
        }


    }



    @RequestMapping("/logOutEnterprise")
    public void studentLogOut(SessionStatus sessionStatus, HttpServletResponse response) throws IOException {
        sessionStatus.setComplete();
        ResultUtil.feedBack(response,"logout successful",null,true);

    }


    @RequestMapping("/uploadAdvertisement")
    public String uploadImg(){
      return  "forward:/advertisementUpload";

    }


}
