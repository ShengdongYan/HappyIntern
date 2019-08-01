package com.dongdong.internship.bean;

import java.util.Date;

/**
 * @Author: ShengdongYan
 * @Date: 2019-07-29
 * @Version 1.0
 */

public class PDFFile {
    private  Integer fid;
    private String  fname;
    private Integer sid;
    private String fplace;
    private String fdescription;
    private Date date;
    private String   createdate;


    public void setFdescription(String fdescription) {
        this.fdescription = fdescription;
    }

    public String getFdescription() {
        return fdescription;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public Date getDate() {
        return date;
    }

    public String getCreatedate() {
        return createdate;
    }

    public Integer getFid() {
        return fid;
    }

    public String getFname() {
        return fname;
    }

    public Integer getSid() {
        return sid;
    }

    public String getFplace() {
        return fplace;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setFplace(String fplace) {
        this.fplace = fplace;
    }

    @Override
    public String toString() {
        return "PDFFile{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                ", sid=" + sid +
                ", fplace='" + fplace + '\'' +
                ", fdescription='" + fdescription + '\'' +
                ", date=" + date +
                ", createdate='" + createdate + '\'' +
                '}';
    }
}
