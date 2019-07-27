package com.dongdong.internship.mapper;


import com.dongdong.internship.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ShengdongYan
 * @Date: 2019-07-26
 * @Version 1.0
 */
@Mapper
public interface StudentMapper {
    public List<Student> queryStudentList();
    public Student  queryStudentByName(String sname);
    public Student  queryStudentByNamePassword(@Param("sname")String sname,@Param("password") String password);

    public void registStudent(Student student);





}
