package com.dongdong.internship.mapper;

import com.dongdong.internship.bean.PDFFile;
import com.dongdong.internship.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ShengdongYan
 * @Date: 2019-07-29
 * @Version 1.0
 */
@Mapper
public interface FileMapper {

    public void  addFile(PDFFile pdf);
    public void  deleteFile(Integer fid);
    public PDFFile queryFileByPlace(String filePlace);
    public List<PDFFile> queryFileBySid(Integer sid);
    public PDFFile queryFileByFid(Integer fid);



   // public Student  queryStudentByNamePassword(@Param("sname")String sname, @Param("password") String password);


}
