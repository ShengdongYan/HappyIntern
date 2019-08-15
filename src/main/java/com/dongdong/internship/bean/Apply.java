package com.dongdong.internship.bean;

/**
 * @Author: ShengdongYan
 * @Date: 2019-07-29
 * @Version 1.0
 */


public class Apply {
    private  Integer applyid;
    private String sname;
    private Integer fid;
    private Integer aid;
    private String   applydate;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "applyid=" + applyid +
                ", sname='" + sname + '\'' +
                ", fid=" + fid +
                ", aid=" + aid +
                ", applydate='" + applydate + '\'' +
                '}';
    }

    public String getApplydate() {
        return applydate;
    }

    public void setApplydate(String applydate) {
        this.applydate = applydate;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public void setApplyid(Integer applyid) {

        this.applyid = applyid;
    }

    public Integer getApplyid() {
        return applyid;
    }

    public String getSname() {
        return sname;
    }

    public Integer getFid() {
        return fid;
    }

    public Integer getAid() {
        return aid;
    }


}
