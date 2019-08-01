package com.dongdong.internship.bean;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-01
 * @Version 1.0
 */
public class Enterprise {
    private  Integer eid;
    private  String website;
    private  String ename;
    private  String password;
    private  String email;

    public void setEdi(Integer eid) {
        this.eid = eid;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEid() {
        return eid;
    }

    public String getWebsite() {
        return website;
    }

    public String getEname() {
        return ename;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "eid=" + eid +
                ", website='" + website + '\'' +
                ", ename='" + ename + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
