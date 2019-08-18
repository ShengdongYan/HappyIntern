package com.dongdong.internship.mapper;

import com.dongdong.internship.bean.Enterprise;
import com.dongdong.internship.bean.Supervisor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-16
 * @Version 1.0
 */

@Mapper
public interface SupervisorMapper {
    public void  updateSupervisor(Supervisor supervisor);
    public Supervisor querySupervisorByNamePassword(@Param("supname")String supname, @Param("password") String password);
    public void registSupervisor(Supervisor supervisor);
    public Supervisor  querySupervisorByNameSchool(@Param("supname")String supname, @Param("school") String school);
}
