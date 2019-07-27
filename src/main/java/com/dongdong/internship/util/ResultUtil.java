package com.dongdong.internship.util;

import com.dongdong.internship.bean.ResultInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: ShengdongYan
 * @Date: 2019-07-27
 * @Version 1.0
 */
public class ResultUtil {
    public static void  feedBack(HttpServletResponse response , String errorMsg , Object data, boolean flag) throws IOException {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(flag);
        resultInfo.setData(data);
        resultInfo.setErrorMsg(errorMsg);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultInfo);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
