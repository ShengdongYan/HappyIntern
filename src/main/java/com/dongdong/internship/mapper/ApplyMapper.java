package com.dongdong.internship.mapper;
import com.dongdong.internship.bean.Apply;
import com.sun.tools.corba.se.idl.InterfaceGen;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-13
 * @Version 1.0
 */
@Mapper
public interface ApplyMapper {
    public void addApply(Apply apply);
    public List<Apply>  queryApplyByAid(Integer aid);
    public List<Apply>  queryApplyBySname(String sname);
    public void  deleteApply(Integer applyid);
    public String queryStatus(Integer applyid);
    public void  changeFeedback(@Param("applyid")Integer applyid, @Param("feedback") String feedback);
    public void  changeStatus(@Param("applyid")Integer applyid, @Param("status") String status);
    public  String queryFeedback(Integer applyid);
    public Integer countByAid(Integer aid);

  /*  public   List<Advertisement> searchAdvertisement(@Param("date") String date, @Param("interest") String interest);


*/
}
