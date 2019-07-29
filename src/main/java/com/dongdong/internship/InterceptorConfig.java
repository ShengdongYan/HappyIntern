package com.dongdong.internship;

import com.dongdong.internship.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: ShengdongYan
 * @Date: 2019-07-25
 * @Version 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 重写添加拦截器方法
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").order(1).excludePathPatterns("/static/*","/css/*","/js/*","/img/*","/mapper/*", "/PDFfile/*");; //order是配置拦截器的顺序，顺序越小，越先

    }

    /**
     * 重写 父类 WebMvcConfigurationSupport 中的 addResourceHandlers 方法 可以实现 静态资源的访问
     * addResourceHandler 相对路径 项目中 resources 文件夹下文件访问
     * addResourceLocations 绝对路径 url 直接可以访问 resources 下的访问
     * @param registry
     */
    @Override
    public  void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/mapper/**").addResourceLocations("classpath:/static/mapper/");
        registry.addResourceHandler("/PDFfile/**").addResourceLocations("classpath:/PDFfile/");
    }


    /**
     * 重写 父类 WebMvcConfigurationSupport 中的 addViewControllers 方法 可以实现 页面跳转
     * addViewController 设置项目路径根路径
     * setViewName 设置访问根路径跳转到的页面
     * @param registry
     */
/*    @Override
   public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");

    }*/

}
