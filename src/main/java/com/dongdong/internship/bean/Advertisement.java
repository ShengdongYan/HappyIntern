package com.dongdong.internship.bean;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-03
 * @Version 1.0
 */
public class Advertisement {
    private  Integer aid;
    private  Integer  eid;
    private String content;
    private  String title;
    private  String enddate;
    private String  imgpath;
    private  String workplace;
    private  String salary;
    private  String contact;
    private  String feature;

    @Override
    public String toString() {
        return "Advertisement{" +
                "aid=" + aid +
                ", eid=" + eid +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", enddate='" + enddate + '\'' +
                ", imgpath='" + imgpath + '\'' +
                ", workplace='" + workplace + '\'' +
                ", salary=" + salary +
                ", contact='" + contact + '\'' +
                ", feature='" + feature + '\'' +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }


    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getImgpath() {
        return imgpath;
    }




    public String getTitle() {
        return title;
    }

    public String getWorkplace() {
        return workplace;
    }

    public String getSalary() {
        return salary;
    }

    public String getContact() {
        return contact;
    }

    public String getFeature() {
        return feature;
    }

    public void setAid(Integer aid) {

        this.aid = aid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public Integer getAid() {
        return aid;
    }

    public Integer getEid() {
        return eid;
    }

    public String getContent() {
        return content;
    }



    public String getEnddate() {
        return enddate;
    }
}
