package com.dongdong.internship.Controller;

import com.dongdong.internship.bean.Advertisement;
import com.dongdong.internship.bean.Enterprise;
import com.dongdong.internship.bean.PDFFile;
import com.dongdong.internship.bean.Student;
import com.dongdong.internship.mapper.AdvertisementMapper;
import com.dongdong.internship.util.ResultUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-03
 * @Version 1.0
 */
@Controller
@SessionAttributes(value= {"enterprise","eid","aid"},types=
        {com.dongdong.internship.bean.Enterprise.class,Integer.class,Integer.class})
public class AdvertisementConbtroller {
    @Autowired
    private AdvertisementMapper advertisementMapper;

    @RequestMapping(value = "/advertisementUpload")  // 记住，每十天不用，系统会自动删除为项目创建的临时文件，到时候会报错，解决办法看收藏
    public String fileupload3(ModelMap modelMap, HttpServletRequest request, MultipartFile upload) throws
            Exception {

        System.out.println("转发成功");
        Enterprise enterprise = (Enterprise)modelMap.get("enterprise");
        Integer eid = enterprise.getEid();
        Advertisement advertisement = new Advertisement();
         advertisement.setEid(eid);
         advertisement.setTitle(request.getParameter("title"));
         advertisement.setContact(request.getParameter("contact"));
         advertisement.setFeature(request.getParameter("feature"));
         advertisement.setSalary((request.getParameter("salary")));
         advertisement.setWorkplace(request.getParameter("workplace"));

        String Path = new String("src/main/resources/static/logo/");  //前边不要加 /不然会变成从整个系统的根目录,相对目录更好操控
        File fileDir = new File(Path);
        if (!fileDir.exists()) {
            System.out.println("文件夹不存在");
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        System.out.println(fileDir.getAbsolutePath());
        // 先获取到要上传的文件目录
        //String path = request.getSession().getServletContext().getRealPath("/uploads");
        File file = new File(fileDir.getAbsolutePath());
        // 获取到上传文件的名称
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        // 把文件的名称唯一化
        filename = uuid + "_" + filename;
        String fplace = "/logo/"+filename;
        advertisement.setImgpath(fplace);
        advertisement.setContent(request.getParameter("content"));
        String enddate = request.getParameter("enddate");
        System.out.println(enddate);
        advertisement.setEnddate(enddate);

        // 上传文件
        upload.transferTo(new File(file, filename));
        advertisementMapper.addAdvertisement(advertisement);
        return "enterprise/enterpriseIndex";
    }




    @RequestMapping("/listAdvertisement")
    public void listAdvertisement(ModelMap modelMap, HttpServletResponse response) throws IOException {
        Enterprise enterprise = (Enterprise) modelMap.get("enterprise");
        Integer eid = enterprise.getEid();
        List<Advertisement> advertisementList= advertisementMapper.queryAdvertisementByEid(eid);

        ResultUtil.feedBack(response,"",advertisementList,true);
    }



    @RequestMapping("/myAdvertisement")
    public String showadvertisements(@Param("aid")Integer aid, Model model){

        model.addAttribute("aid",aid);
        System.out.println(aid);
        return "enterprise/enterpriseAdvertisement";
    }

    @RequestMapping("/showAdvertisement")
    public void  showAdvertisement(ModelMap modelMap,HttpServletResponse response) throws IOException {

        System.out.println("jinqvhgjkhjkhj");
       Integer aid = (Integer) modelMap.get("aid");
        System.out.println(aid);
      Advertisement advertisement = advertisementMapper.queryAdvertisementByaid(aid);
        System.out.println(advertisement);
        ResultUtil.feedBack(response,"",advertisement,true);


    }
/*

    @RequestMapping("/listFiles")
    public void findFiles(ModelMap modelMap, HttpServletResponse response) throws IOException {
        Student student = (Student) modelMap.get("student");
        Integer sid = student.getSid();
        List<PDFFile> fileList= fileMapper.queryFileBySid(sid);

        ResultUtil.feedBack(response,"",fileList,true);
    }


    @RequestMapping("/showpdfByFid")
    public String test2(HttpServletResponse response, HttpServletRequest request,@Param("fid")Integer fid) {

        PDFFile pdf = fileMapper.queryFileByFid(fid);
        if (pdf == null) {
            return null;

        } else {

            String fplace = pdf.getFplace();


            response.reset();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;fid=" + fid);
            System.out.println(fid);
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            OutputStream os = null;

            try {


                String newpath = fplace;  // 项目中用相对路径，但是相对路径千万不能加/开头，不然还是从盘符！
                File pdf2 = new File(newpath);
                System.out.println("请求PDF路径：" + pdf2.getAbsolutePath());
                os = response.getOutputStream();
                //获得PDF文件流
                InputStream is = new FileInputStream(pdf2);
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


    //直接访问/showpdf 可以实现下载功能，返回给pdfjs 可以获取在线阅读
    @RequestMapping("/showpdf")
    public String test3(HttpServletResponse response, HttpServletRequest request,@Param("fplace")String fplace) {

        String urlPath = "";
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fplace);
        System.out.println(fplace);

        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;

        try {


            //下面几行说明从绝对路径可以

  *//*          String Path = new String("PDFFile/");

            File pdf2 = new File(urlPath);
            System.out.println(pdf2.getAbsolutePath());
            String newpath = pdf2.getAbsolutePath()+"/PDFFile//test.pdf";*//*


            String newpath = fplace;  // 项目中用相对路径，但是相对路径千万不能加/开头，不然还是从盘符！
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


    @RequestMapping("/deletepdf")
    public String deletepdf(@Param("fid")Integer fid){

        fileMapper.deleteFile(fid);


        return "student/studentFiles";
    }*/
}
