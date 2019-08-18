package com.dongdong.internship.bean;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-16
 * @Version 1.0
 */
public class Report {

    private  Integer reportid;
    private  String sname;
    private  Integer supid;
    private  String reporttitle;
    private String content;
    private  String feedback;
    private  String reportdate;

    @Override
    public String toString() {
        return "Report{" +
                "reportid=" + reportid +
                ", sname='" + sname + '\'' +
                ", supid=" + supid +
                ", reporttitle='" + reporttitle + '\'' +
                ", content='" + content + '\'' +
                ", feedback='" + feedback + '\'' +
                ", reportdate='" + reportdate + '\'' +
                '}';
    }

    public String getReportdate() {
        return reportdate;
    }

    public void setReportdate(String reportdate) {
        this.reportdate = reportdate;
    }

    public Integer getReportid() {
        return reportid;
    }

    public void setReportid(Integer reportid) {
        this.reportid = reportid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getSupid() {
        return supid;
    }

    public void setSupid(Integer supid) {
        this.supid = supid;
    }

    public String getReporttitle() {
        return reporttitle;
    }

    public void setReporttitle(String reporttitle) {
        this.reporttitle = reporttitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
