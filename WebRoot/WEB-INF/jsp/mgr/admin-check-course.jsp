<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>科多在线教育网站--审核老师刚上传的课程的界面</title>
    <link rel="stylesheet" href="/oe/css/mgr-css1.css" type="text/css">
    <script type="text/javascript" src="/oe/js/jquery-1.7.min.js"></script>
    <script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
    <script type="text/javascript" src="/oe/js/messages_zh.js"></script>
    <script type="text/javascript">
$(function(){
	loadCourse(1);
	$("a[id^='a-paging-']").on("click",function(){
 		loadCourse($(this).attr("val"));
 	});
 	
 	
});
function loadCourse(pageNo){
	var trs="";
 	$.getJSON("/oe/mgr/loadCheckCourse?r="+Math.random(),{"pageNo":pageNo},function(jsonData){
 		var courseArr = jsonData["courseList"];
 		var paging = jsonData["paging"];
 		trs ='<tr><td width="130">课程名</td>';
 		trs += '<td width="80">分类</td>';
 		trs += '<td width="50">价格</td>';
 		trs += '<td width="240">课程目标</td>';
 		trs += '<td width="190">课程适用人群</td>';
 		trs += '<td width="190">课程学习需求</td>';
 		trs += '<td width="80">处理</td></tr>';
 		for(var i in courseArr){
 			var c = courseArr[i];
 			trs +='<tr><td>'+c.title+'</td>';
 			trs +='<td>'+c.category.name+'</td>';
 			trs +='<td>'+c.price+'</td>';
 			trs +='<td>'+c.target+'</td>';
 			trs +='<td>'+c.suitable+'</td>';
 			trs +='<td>'+c.demand+'</td>';
 			trs +='<td><a id="a-pass-'+c.id+'" href="javascript://" valId="'+c.id+'" pageNo="'+paging.pageNo+'">通过</a>&nbsp;&nbsp;<a id="a-refuse-'+c.id+'" href="javascript://" valId="'+c.id+'" pageNo="'+paging.pageNo+'">拒绝</a></td></tr>'
 		} 
 		$("#course-list").html(trs);
 		$("#a-paging-f").attr("val","1");
 		$("#a-paging-p").attr("val",paging.pagePrev);
 		$("#a-paging-n").attr("val",paging.pageNext);
 		$("#a-paging-t").attr("val",paging.pageTotal);
 		$("#span-pageNo").html(paging.pageNo);
 		$("#span-pageTotal").html(paging.pageTotal);
 		$("#span-countTotal").html(paging.countTotal);
 		$("a[id^='a-pass-']").unbind();
 		$("a[id^='a-pass-']").on("click",function(){
 			passCourse($(this).attr("valId"),$(this).attr("pageNo"));
 		});
 		$("a[id^='a-refuse-']").unbind();
 		$("a[id^='a-refuse-']").on("click",function(){
 			refuseCourse($(this).attr("valId"),$(this).attr("pageNo"));
 		});
 	});
}
function passCourse(id,pageNo){
	$.get("/oe/mgr/passCourse?r="+Math.random(),{"courseId":id,"pageNo":pageNo},function(data){
		loadCourse(data);
	});
}
function refuseCourse(id,pageNo){
	$.get("/oe/mgr/refuseCourse?r="+Math.random(),{"courseId":id,"pageNo":pageNo},function(data){
		loadCourse(data);
	});
}
    </script>
</head>
<body>
<div class="content">
    <%@ include file = "/WEB-INF/jsp/mgr/inc/header.jsp" %>
    <div class="container">
         <h2>用户刚上传的课程的管理</h2>
        <table id="course-list">
        </table>
        <div style="height:40px;line-height:40px;font-size:12px">
            <a href="javascript://" id="a-paging-f" val="">首页</a>&nbsp;&nbsp;
            <a href="javascript://" id="a-paging-p" val="" >上一页</a>&nbsp;&nbsp;
            <a href="javascript://" id="a-paging-n" val="">下一页</a>&nbsp;&nbsp;
            <a href="javascript://" id="a-paging-t" val="">尾页</a>&nbsp;&nbsp;
            <span id="span-pageNo"></span>/<span id="span-pageTotal"></span>页  共计<span id="span-countTotal"></span>条
        </div>
    </div>
    <%@ include file = "/WEB-INF/jsp/mgr/inc/footer.jsp" %>
</div>
</body>
</html>