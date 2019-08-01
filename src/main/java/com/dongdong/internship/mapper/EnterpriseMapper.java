package com.dongdong.internship.mapper;

import com.dongdong.internship.bean.Enterprise;
import com.dongdong.internship.bean.Student;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-01
 * @Version 1.0
 */
public interface EnterpriseMapper {


    public Enterprise queryEnterpriseByNamePassword(@Param("ename")String sname, @Param("password") String password);
    public void registEnterprise(Enterprise enterprise);
    public Enterprise  queryEnterpriseByName(String ename);


}
