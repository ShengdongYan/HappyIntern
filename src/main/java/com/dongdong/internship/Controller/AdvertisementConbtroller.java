package com.dongdong.internship.Controller;


import com.dongdong.internship.bean.*;
import com.dongdong.internship.mapper.AdvertisementMapper;
import com.dongdong.internship.mapper.ApplyMapper;
import com.dongdong.internship.mapper.FileMapper;
import com.dongdong.internship.mapper.StudentMapper;
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
import java.util.ArrayList;
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

    @Autowired
    private  StudentMapper studentMapper;

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
        List<AdvertisementWithNum> advertisementWithNums = new ArrayList<AdvertisementWithNum>();
        for(int i = 0 ; i < advertisementList.size();i++){
            Advertisement advertisement = advertisementList.get(i);
            Integer number = applyMapper.countByAid(advertisement.getAid());
            AdvertisementWithNum advertisementWithNum = new AdvertisementWithNum();
            advertisementWithNum.setAdvertisement(advertisement);
            advertisementWithNum.setNumber(number);
            advertisementWithNums.add(advertisementWithNum);
        }

        ResultUtil.feedBack(response,"",advertisementWithNums,true);
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
        apply.setStatus("waiting process");
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
    public  String deleteAdvertisement(ModelMap modelMap){
        Integer aid = (Integer) modelMap.get("aid");
        advertisementMapper.deleteAdvertisement(aid);
        return "enterprise/enterpriseIndex";

    }

@RequestMapping("/showHistory")
public void  showHistory(ModelMap modelMap,HttpServletResponse response) throws IOException {
    Integer aid = (Integer) modelMap.get("aid");
    Advertisement advertisement = advertisementMapper.queryAdvertisementByaid(aid);
}

@RequestMapping("/viewApply")
public String  showApply(@Param("aid")Integer aid,  Model model,HttpServletResponse response) throws IOException {
      model.addAttribute("aid",aid);
      return "enterprise/enterpriseApplyHistory";
    }

 @RequestMapping("/showApplyier")
 public  void  appliers(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) throws IOException {
       Integer aid = (Integer) modelMap.get("aid");

     List<Apply> applies =  applyMapper.queryApplyByAid(aid);

     List<ApplyHistory>  applyHistories = new ArrayList<>();

     for(int i =0; i< applies.size(); i++){
         Apply apply = applies.get(i);
         ApplyHistory applyHistory = new ApplyHistory();
         applyHistory.setApply(apply);
         Integer fid = apply.getFid();
         PDFFile pdfFile = fileMapper.queryFileByFid(fid);
         applyHistory.setFile(pdfFile);
         Student student2 = studentMapper.queryStudentByName(apply.getSname());
         applyHistory.setStudent(student2);
         applyHistory.setAdvertisement( advertisementMapper.queryAdvertisementByaid(apply.getAid()));
         applyHistories.add(applyHistory);
     }

     System.out.println(applyHistories);
     ResultUtil.feedBack(response, "返回数据", applyHistories, false);
 }




    @Autowired
private FileMapper fileMapper;

@RequestMapping("/showFeedback")
    public  void showFeedback(@Param("applyid")Integer applyid, HttpServletResponse response) throws IOException {
    String feedback = applyMapper.queryFeedback(applyid);
    ResultUtil.feedBack(response,"",feedback,true);

    }

@RequestMapping("/applyHistory")
    public  void  applyHistory(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) throws IOException {
      Student student = (Student)modelMap.get("student");
      String sname = student.getSname();

       List<Apply> applies =  applyMapper.queryApplyBySname(sname);

       List<ApplyHistory>  applyHistories = new ArrayList<>();

       for(int i =0; i< applies.size(); i++){
           Apply apply = applies.get(i);
           ApplyHistory applyHistory = new ApplyHistory();
           applyHistory.setApply(apply);
           Integer fid = apply.getFid();
           PDFFile pdfFile = fileMapper.queryFileByFid(fid);
           applyHistory.setFile(pdfFile);
            applyHistory.setStudent(student);
          applyHistory.setAdvertisement( advertisementMapper.queryAdvertisementByaid(apply.getAid()));

         applyHistories.add(applyHistory);
       }
        System.out.println(applyHistories);
        ResultUtil.feedBack(response, "返回数据", applyHistories, false);
}

@RequestMapping("/changeStatus")
    public String   changeStatuss(@Param("applyid")Integer applyid,@Param("status")String status ){
    applyMapper.changeStatus(applyid,status);
    return "/enterprise/enterpriseIndex";
}

    @RequestMapping("/changeFeedback")
     public String   changeFeedback(@Param("applyid")Integer applyid,@Param("feedback")String feedback ){
        applyMapper.changeFeedback(applyid,feedback);
        return "/enterprise/enterpriseIndex";

    }

}
