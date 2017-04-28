package com.online.edu.web.controller;


import java.io.File;
import java.io.IOException;
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

import com.online.edu.entity.Catalog;
import com.online.edu.entity.Course;
import com.online.edu.service.CatalogService;
@Controller
@RequestMapping("/catalog")
public class CatalogController extends BaseController{
	@Autowired
	private CatalogService catalogService;
	@RequestMapping("/watch")
	public String watch(Integer catalogId,Model model){
		//能获得这个课程目录（视频）的id 如1
//		if(catalogId == null){
//			catalogId = 1;
//		}
		Catalog catalog = catalogService.watch(catalogId);
		model.addAttribute("catalog", catalog);
		return "fore/play-video";
	}
	@RequestMapping("/showCatalogs")
	public String showCatalogs(){
		return null;
	}
	@RequestMapping("/uploadForm")
	public String uploadForm(Integer courseId,Model model){
		Integer maxSequence = catalogService.getMaxSequenceByCourseId(courseId);
		Integer sequence = 0;
		if(maxSequence == null){
			sequence = 1;
		}else{
			sequence = maxSequence + 1;
		}
		model.addAttribute("courseId", courseId);
		model.addAttribute("sequence", sequence);
		return "fore/upload-catalog";
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
            Integer maxSequence = catalogService.getMaxSequenceByCourseId(courseId);
            Integer i = 0;
    		if(maxSequence == null){
    			i = 1;
    		}else{
    			i = maxSequence + 1;
    		}
            while(iter.hasNext()){  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){
                    	Catalog c = new Catalog();
                    	c.setCourse(new Course());
                    	c.getCourse().setId(courseId);
                    	String title = (String) request.getParameter("title"+i);
                    	c.setTitle(title);
                    	c.setSequence(i);
                    	i += 1;
                        String path = request.getSession().getServletContext().getRealPath("resource/video");
            			String suffix = myFileName.substring(myFileName.lastIndexOf("."));
            			String fileName = System.currentTimeMillis()+suffix;
            			c.setVideoName(fileName);
                        File localFile = new File(path,fileName);  
                        file.transferTo(localFile);  
                        catalogService.addNewCatalog(c);
                    }
                }  
            }  
        }  
		return "redirect:/user/personalCenter";
	}
}
