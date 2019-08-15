package com.dongdong.internship.bean;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-14
 * @Version 1.0
 */
public class ApplyHistory {
    private  Apply apply;
    private  Student student;
    private  PDFFile file ;
    private  Advertisement advertisement;


    @Override
    public String toString() {
        return "ApplyHistory{" +
                "apply=" + apply +
                ", student=" + student +
                ", file=" + file +
                ", advertisement=" + advertisement +
                '}';
    }

    public Apply getApply() {
        return apply;
    }

    public void setApply(Apply apply) {
        this.apply = apply;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public PDFFile getFile() {
        return file;
    }

    public void setFile(PDFFile file) {
        this.file = file;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
}
