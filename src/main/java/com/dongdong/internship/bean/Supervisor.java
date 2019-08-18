package com.dongdong.internship.bean;

import java.security.PrivateKey;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-16
 * @Version 1.0
 */
public class Supervisor {


    private Integer supid;
    private  String supname;
    private String password ;
    private  String suptitle;
    private String school;
    private  String supmail;

    @Override
    public String toString() {
        return "Supervisor{" +
                "supid=" + supid +
                ", supname='" + supname + '\'' +
                ", password='" + password + '\'' +
                ", suptitle='" + suptitle + '\'' +
                ", school='" + school + '\'' +
                ", supmail='" + supmail + '\'' +
                '}';
    }

    public Integer getSupid() {
        return supid;
    }

    public void setSupid(Integer supid) {
        this.supid = supid;
    }

    public String getSupname() {
        return supname;
    }

    public void setSupname(String supname) {
        this.supname = supname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSuptitle() {
        return suptitle;
    }

    public void setSuptitle(String suptitle) {
        this.suptitle = suptitle;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSupmail() {
        return supmail;
    }

    public void setSupmail(String supmail) {
        this.supmail = supmail;
    }
}
