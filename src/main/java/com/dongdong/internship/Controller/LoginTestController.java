package com.dongdong.internship.Controller;

import com.dongdong.internship.bean.Student;
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
            ResultUtil.feedBack(response, "登录了", student, true);
        }
        else
            ResultUtil.feedBack(response, "没登录,来自登录检测器", student, false);
    }

}
