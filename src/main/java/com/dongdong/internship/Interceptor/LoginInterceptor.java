package com.dongdong.internship.Interceptor;

import com.dongdong.internship.bean.Student;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

/**
 * @Author: ShengdongYan
 * @Date: 2019-07-25
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * controller方法调用前调用。
     *
     * @param request
     * @param response
     * @param handler
     * @return 往下执行则返回true，否则返回false
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         System.out.println("前置拦截器");

/*        Student student = (Student) request.getSession().getAttribute("student");
        if(null == student){
            response.sendRedirect("login");
        }else{
           return true;
        }*/

        return true;
    }

    /**
     * controller方法调用后视图渲染前执行。
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //System.out.println("返回拦截器");
    }

    /**
     * controller方法调用且视图渲染完成后执行
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
      // System.out.println("方法执行完毕拦截器");
    }
}
