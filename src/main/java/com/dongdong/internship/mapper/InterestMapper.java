package com.dongdong.internship.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-07
 * @Version 1.0
 */

@Mapper
public interface InterestMapper {
    public void  addinterest(@Param("sname") String sname, @Param("interest") String interest);
    public  List<String> queryInterest(String  sname);


}
