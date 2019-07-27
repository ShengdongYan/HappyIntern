package com.dongdong.internship.bean;

/**
 * @Author: ShengdongYan
 * @Date: 2019-07-26
 * @Version 1.0
 */
public class Student {
    private  Integer sid;
    private String password;
    private  String supname;
    private String sname;
    private  Integer sage;
    private  String school;
    private String smail;


    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", password='" + password + '\'' +
                ", supname='" + supname + '\'' +
                ", sname='" + sname + '\'' +
                ", sage=" + sage +
                ", school='" + school + '\'' +
                ", smail='" + smail + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setSupname(String supname) {
        this.supname = supname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setSage(Integer sage) {
        this.sage = sage;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setSmail(String smail) {
        this.smail = smail;
    }

    public Integer getSid() {
        return sid;
    }

    public String getSupname() {
        return supname;
    }

    public String getSname() {
        return sname;
    }

    public Integer getSage() {
        return sage;
    }

    public String getSchool() {
        return school;
    }

    public String getSmail() {
        return smail;
    }
}
