package com.dongdong.internship.Controller;
import com.dongdong.internship.bean.Advertisement;
import com.dongdong.internship.bean.Apply;
import com.dongdong.internship.bean.Enterprise;
import com.dongdong.internship.bean.Student;
import com.dongdong.internship.mapper.AdvertisementMapper;
import com.dongdong.internship.mapper.ApplyMapper;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: ShengdongYan
 * @Date: 2019-08-03
 * @Version 1.0
 */
@Controller
@SessionAttributes(value= {"enterprise","eid","aid","student"},types=
        {com.dongdong.internship.bean.Enterprise.class,Integer.class,Integer.class, Student.class})
public class AdvertisementConbtroller {
    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Autowired
    private ApplyMapper applyMapper;

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

        String Path = new String("src/main/resources/logo/");  //前边不要加 /不然会变成从整个系统的根目录,相对目录更好操控
        File fileDir = new File(Path);
        if (!fileDir.exists()) {
            System.out.println("文件夹不存在");
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        System.out.println(fileDir.getAbsolutePath());




        // 获取到上传文件的名称
        String filename = upload.getOriginalFilename();

        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        // 把文件的名称唯一化
        filename = uuid + "_" + filename;
        File file = new File(fileDir.getAbsolutePath(),filename);
        String fplace = "/logo/"+filename;
        advertisement.setImgpath(fplace);
        advertisement.setContent(request.getParameter("content"));
        advertisement.setCompanyDescription(request.getParameter("companyDescription"));
        String enddate = request.getParameter("enddate");
        System.out.println(enddate);

        advertisement.setEnddate(enddate);

        // 上传文件
     /*   upload.transferTo(file);*/


        InputStream input = null;
        FileOutputStream fos = null;
        try {
            input = upload.getInputStream();
            fos = new FileOutputStream(file);

            byte[] car = new byte[2048];
            int len = 0; //一次真实的读几到了多少byte
            while (-1 != (len = input.read(car))) {  //当返回的长度是-1，表示没有数据了。

                fos.write(car, 0, len);
                fos.flush();

            }
        }
        catch (Exception e){
            e.printStackTrace();

            }

      finally {
            fos.flush();
            fos.close();
            input.close();

        }


        advertisementMapper.addAdvertisement(advertisement);
        return "redirect:/enterprise/index";
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

        return "enterprise/enterpriseAdvertisement";
    }


    @RequestMapping("/studentAdvertisement")
    public String showadvertisements2(@Param("aid")Integer aid, Model model){

        model.addAttribute("aid",aid);

        return "student/studentAdvertisement";
    }


    @RequestMapping("/chooseFile")
    public  String chooseFile(){
        return "student/studentChooseFile";
    }


    @RequestMapping("/apply")
    public String applyAdvertisement(@Param("fid")Integer fid ,ModelMap modelMap){

       Integer aid  =(Integer) modelMap.get("aid");
       Student student = (Student)modelMap.get("student");

        String sname = student.getSname();
        Apply apply = new Apply();
        apply.setAid(aid);
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date nowDate= new Date();
        String applydate= sdf.format(nowDate);

        apply.setApplydate(applydate);
        apply.setSname(sname);
        apply.setFid(fid);
        applyMapper.addApply(apply);


        return  "student/studentIndex";
    }


    @RequestMapping("/showAdvertisement")
    public void  showAdvertisement(ModelMap modelMap,HttpServletResponse response) throws IOException {

        Integer aid = (Integer) modelMap.get("aid");
        System.out.println(aid);
        Advertisement advertisement = advertisementMapper.queryAdvertisementByaid(aid);
        System.out.println(advertisement);
        ResultUtil.feedBack(response,"",advertisement,true);


    }

    @RequestMapping("/deleteAdvertisement")
    public  String deleteAdvertisement(@Param("fid")Integer aid){
        advertisementMapper.deleteAdvertisement(aid);

        return "enterprise/enterpriseIndex";


    }


@RequestMapping("/showHistory")
public void  showHistory(ModelMap modelMap,HttpServletResponse response) throws IOException {
    Integer aid = (Integer) modelMap.get("aid");
    Advertisement advertisement = advertisementMapper.queryAdvertisementByaid(aid);


}




}
