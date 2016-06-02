package com.mvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mvc.model.StudentModel;
import com.mvc.service.UsetService;

@Controller
public class UserController {

    @Autowired
    UsetService userService = null;

    @RequestMapping(value = "/userAdd")
    public String userAdd() {
        return "userAdd";
    }

    @RequestMapping(value = "/userAddInit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> userAddInit(@RequestBody StudentModel studentModel) {
        System.out.println("hahah");

        userService.addStudent(studentModel);

        return bulidReturnMap("ok", null);

    }

    // 参考url：http://blog.csdn.net/cheung1021/article/details/7084673
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String fileUpload(StudentModel studentModel, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {
        System.out.println(file.getOriginalFilename());
        System.out.println(file);
        System.out.println(file.getContentType());
        System.out.println(file.getSize());

        // 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
        String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
        // 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
        /*
         * try { FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, file.getOriginalFilename()));
         * } catch (IOException e) { // TODO 自動生成された catch ブロック e.printStackTrace(); }
         */

        String fileName = file.getOriginalFilename();
        File targetFile = new File("C:/Users/wang_changyuan/git/wangcyTest/WebContent/WEB-INF/views/upload", fileName);
        if (targetFile.exists()) {
            targetFile.delete();
        }

        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        // 保存
        try {
            file.transferTo(targetFile);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("fileName", fileName);
        System.out.println(request.getContextPath() + "upload/" + fileName);
        System.out.println(realPath);

        return "result";

    }
   /* 文件下载到 C:\\Users\\wang_changyuan\\upload\\*/
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> download(@RequestBody StudentModel studentModel, HttpServletResponse response, HttpServletRequest request) throws IOException {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        String path = "C:/Users/wang_changyuan/git/wangcyTest/WebContent/WEB-INF/views/upload/";
        String downLoadPath = path + studentModel.getFileName();
        System.out.println(downLoadPath);
        // OutputStream os= new FileOutputStream();

        /*
         * InputStream is = new FileInputStream(downLoadPath); OutputStream os = response.getOutputStream();
         * System.out.println(os); // 写文件 int b = is.read(); while (b != -1) { os.write(b); } is.close(); os.close();
         */
        String aaa = request.getSession().getServletContext().getRealPath("/WEB-INF/views/upload");
        String aaPath = aaa + studentModel.getFileName();
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(studentModel.getFileName(), "UTF-8"));
        InputStream reader = null;
        // OutputStream out = null;
        byte[] bytes = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            // 读取文件
            reader = new FileInputStream(aaPath);
            // 写入浏览器的输出流
            fileOutputStream = new FileOutputStream("C:\\Users\\wang_changyuan\\upload\\"+studentModel.getFileName());
            // fileOutputStream = response.getOutputStream();
            while ((len = reader.read(bytes)) > 0) {
                fileOutputStream.write(bytes, 0, len);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();

            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
        System.out.println("123");
        return bulidReturnMap("ok", null);
    }

    public Map<String, Object> bulidReturnMap(String code, Object result) {

        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("code", code);
        returnMap.put("result", result);

        return returnMap;

    }


    @RequestMapping(value = "/sayHello", method = RequestMethod.POST)
    @ResponseBody
    public String   sayHello(Model model) {
        //ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("hello", "hello");
        System.out.println("hahah");


        return "ssq";
    }

}
