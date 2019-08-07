package com.dongdong.internship.mapper;

import com.dongdong.internship.bean.PDFFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-07
 * @Version 1.0
 */

@Mapper
public interface InterestMapper {

    public void  addInterest(Integer sid,String interest);
    public  List<String> queryInterest(Integer sid);


}
