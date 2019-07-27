package com.dongdong.internship.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @Author: ShengdongYan
 * @Date: 2019-07-13
 * @Version 1.0
 */

@Controller
public class ControllerTest {


    @RequestMapping("/nihao")

    public String test() {
        System.out.println("成了");
        return "index";
    }

    @RequestMapping("/pdf")
    public String test2() {
        System.out.println("pdf");
        return "WEB-INF/infTest";
    }

    @RequestMapping(value = "/fileupload2")
    @ResponseBody
    public String fileupload2(HttpServletRequest request, MultipartFile upload) throws
            Exception {

        String Path = new String("PDFfile/");
        File fileDir = new File(Path);
        if (!fileDir.exists()) {
            // 递归生成文件夹
            fileDir.mkdirs();
        }

        System.out.println(fileDir.getAbsolutePath());


        // 先获取到要上传的文件目录
        //String path = request.getSession().getServletContext().getRealPath("/uploads");
        //String path = "/uploads";
        File file = new File(fileDir.getAbsolutePath());
        // 获取到上传文件的名称
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        // 把文件的名称唯一化
        filename = uuid + "_" + filename;
        // 上传文件
        upload.transferTo(new File(file, filename));
        System.out.println("文件上传");
        return "文件上传成功";
    }

    @RequestMapping("/showpdf")
    public String test3(HttpServletResponse response, HttpServletRequest request, String fileName) {
        System.out.println("出发");

        String urlPath = "";
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + "test");
        System.out.println(fileName);

        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;

        try {


            //下面几行说明从绝对路径可以

  /*          String Path = new String("PDFfile/");

            File pdf2 = new File(urlPath);
            System.out.println(pdf2.getAbsolutePath());
            String newpath = pdf2.getAbsolutePath()+"/PDFfile//test.pdf";*/
            String newpath = "PDFfile/test.pdf";  // 项目中用相对路径，但是相对路径千万不能加/开头，不然还是从盘符！
            File pdf = new File(newpath);
            System.out.println("请求PDF路径：" + pdf.getAbsolutePath());
            os = response.getOutputStream();
            //获得PDF文件流
            InputStream is = new FileInputStream(pdf);
            System.out.println("获取流结束。。。。");
            bis = new BufferedInputStream(is);
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

    }
}
