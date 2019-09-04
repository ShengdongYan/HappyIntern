package com.dongdong.internship.Controller;

import com.dongdong.internship.bean.Enterprise;
import com.dongdong.internship.bean.Student;
import com.dongdong.internship.bean.Supervisor;
import com.dongdong.internship.util.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: ShengdongYan
 * @Date: 2019-07-27
 * @Version 1.0
 */
@Controller
@RequestMapping("/loginTest")
@SessionAttributes(value= {"student","username"},types=
        {com.dongdong.internship.bean.Student.class,String.class})
public class LoginTestController {

    @RequestMapping("/student")
    public  void studentLoginTest(ModelMap modelMap,HttpServletResponse response) throws IOException {
        Student student = (Student) modelMap.get("student");
        String username = (String) modelMap.get("username");
        if (student!=null) {
            ResultUtil.feedBack(response, "", student, true);
        }
        else
            ResultUtil.feedBack(response, "Please log in !", student, false);
    }

    @RequestMapping("/enterprise")
    public void enterpriseLoginTest(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) throws IOException {
         HttpSession session = request.getSession();
          Enterprise enterprise = (Enterprise) session.getAttribute("enterprise");

        if (enterprise!=null) {
            ResultUtil.feedBack(response, "", enterprise, true);
        }
        else
            ResultUtil.feedBack(response, "Please log in !", null, false);
    }


    @RequestMapping("/supervisor")
    public void supervisorLoginTest(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Supervisor supervisor = (Supervisor) session.getAttribute("supervisor");

        if (supervisor!=null) {
            ResultUtil.feedBack(response, "", supervisor, true);
        }
        else
            ResultUtil.feedBack(response, "Please log in !", null, false);
    }
}
