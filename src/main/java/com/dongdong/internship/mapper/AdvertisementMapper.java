package com.dongdong.internship.mapper;

import com.dongdong.internship.bean.Advertisement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-03
 * @Version 1.0
 */
@Mapper
public interface AdvertisementMapper {
     public void  addAdvertisement(Advertisement advertisement);
     public void  deleteAdvertisement(Integer aid);
     public List<Advertisement> queryAdvertisementByEid(Integer eid);
     public Advertisement queryAdvertisementByaid(Integer aid);
     public   List<Advertisement> searchAdvertisement(@Param("date") String date,@Param("interest") String interest);



}
