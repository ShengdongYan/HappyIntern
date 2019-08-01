package com.dongdong.internship.Controller;

import com.dongdong.internship.bean.Enterprise;
import com.dongdong.internship.bean.ResultInfo;
import com.dongdong.internship.mapper.EnterpriseMapper;
import com.dongdong.internship.util.ResultUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.IOException;

/**
 * @Author: ShengdongYan
 * @Date: 2019-07-30
 * @Version 1.0
 */

@Controller
@RequestMapping("/enterprise")
@SessionAttributes(value= {"enterprise","eid"},types=
        {com.dongdong.internship.bean.Enterprise.class,String.class})
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
    public String showProfile(){
        return "enterprise/enterpriseProfile";

    }


    @RequestMapping("/myAdvertisement")
    public void showadvertisements(){

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


}
