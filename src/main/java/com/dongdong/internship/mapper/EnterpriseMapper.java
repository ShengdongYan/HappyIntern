package com.dongdong.internship.mapper;

import com.dongdong.internship.bean.Enterprise;
import com.dongdong.internship.bean.Student;
import com.sun.tools.javac.comp.Enter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-01
 * @Version 1.0
 */
@Mapper
public interface EnterpriseMapper {



    public void  updateEnterprise(Enterprise enterprise);
    public Enterprise queryEnterpriseByNamePassword(@Param("ename")String sname, @Param("password") String password);
    public void registEnterprise(Enterprise enterprise);
    public Enterprise  queryEnterpriseByName(String ename);


}
