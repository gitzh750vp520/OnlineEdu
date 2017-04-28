package com.online.edu.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.online.edu.entity.Course;
import com.online.edu.entity.Courseware;
import com.online.edu.service.CoursewareService;

@Controller
@RequestMapping("/courseware")
public class CoursewareController extends BaseController{
	@Autowired
	private CoursewareService coursewareService;
	@RequestMapping("/download")
	public void down(Integer coursewareId,HttpServletRequest request,HttpServletResponse response) throws IOException { 
	     response.setContentType("application/vnd.ms-excel"); 
	     Courseware c= coursewareService.getCoursewareByCoursewareId(coursewareId);
	     String nowPath = request.getSession().getServletContext().getRealPath("resource/courseware")+File.separator+c.getFileName();
//	     name = new String(name.getBytes("iso-8859-1"),"UTF-8") + fileName.substring(fileName.lastIndexOf("."));
//	     String contextPath = request.getContextPath();
//	     String serveltPath = request.getServletPath();
	      File file = new File(nowPath); 
	     // 清空response 
	        response.reset(); 
	        // 设置response的Header 
	        String name = c.getName() + c.getFileName().substring(c.getFileName().lastIndexOf("."));
	        response.addHeader("Content-Disposition", "attachment;filename="+new String(name.getBytes("gbk"),"iso-8859-1"));  //转码之后下载的文件不会出现中文乱码
	        response.addHeader("Content-Length", "" + file.length()); 
	     try{ 
	         //以流的形式下载文件 
	         InputStream fis = new BufferedInputStream(new FileInputStream(nowPath)); 
	         byte[] buffer = new byte[fis.available()]; 
	         fis.read(buffer); 
	         fis.close(); 
	         OutputStream toClient = new BufferedOutputStream(response.getOutputStream()); 
	         toClient.write(buffer); 
	         toClient.flush(); 
	         toClient.close(); 
	     }catch(Exception e){ 
	         e.printStackTrace(); 
	     } 
	} 
	@RequestMapping("/uploadForm")
	public String uploadForm(Integer courseId,Model model){
		model.addAttribute("courseId", courseId);
		return "fore/upload-courseware";
	}
	@RequestMapping("/upload")
	public String upload(Integer courseId,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames(); 
            Integer i = 1;
            while(iter.hasNext()){  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){
                    	Courseware c = new Courseware();
                    	c.setCourse(new Course());
                    	c.getCourse().setId(courseId);
                    	String name = (String) request.getParameter("name"+i);
                    	c.setName(name);
                    	i += 1;
                        String path = request.getSession().getServletContext().getRealPath("resource/courseware");
            			String suffix = myFileName.substring(myFileName.lastIndexOf("."));
            			String fileName = System.currentTimeMillis()+suffix;
            			c.setFileName(fileName);
                        File localFile = new File(path,fileName);  
                        file.transferTo(localFile);  
                        coursewareService.addNewCourseware(c);
                    }
                }  
            }  
        }  
		return "redirect:/user/personalCenter";
	}
}
