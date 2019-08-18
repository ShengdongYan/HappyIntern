package com.dongdong.internship.mapper;

import com.dongdong.internship.bean.Apply;
import com.dongdong.internship.bean.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-16
 * @Version 1.0
 */
@Mapper
public interface ReportMapper {

    public  void addReport(Report report);
    public  List<Report>  queryReportBySname(String sname);
    public  List<Report>  queryReportBySupid(Integer supid);
    public  String      queryFeedback(Integer reportid);
    public  void addFeedback(@Param("reportid") Integer reportid, @Param("feedback") String feedback);
    public  String queryContent(Integer reportid);

}
