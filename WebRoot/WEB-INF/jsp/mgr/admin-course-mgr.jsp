<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>科多在线教育网站--课程管理界面</title>
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
 	$.getJSON("/oe/mgr/loadCourse?r="+Math.random(),{"pageNo":pageNo},function(jsonData){
 		var courseArr = jsonData["courseList"];
 		var paging = jsonData["paging"];
 		trs ='<tr><td width="100">课程名称</td>';
 		trs += '<td width="60">分类</td>';
 		trs += '<td width="60">收藏次数</td>';
 		trs += '<td width="60">价格</td>';
 		trs += '<td width="60">评分</td>';
 		trs += '<td width="60">状态</td>';
 		trs += '<td width="80">操作</td></tr>';
 		for(var i in courseArr){
 			var c = courseArr[i];
 			trs +='<tr><td>'+c.title+'</td>';
 			trs +='<td>'+c.category.name+'</td>';
 			trs +='<td>'+c.collect+'</td>';
 			trs +='<td>'+c.price+'</td>';
 			trs +='<td>'+c.point+'</td>';
 			if(c.status){
 				trs +='<td>可用</td>';
 				trs +='<td><a id="a-status-'+c.id+'" href="javascript://" valId="'+c.id+'" pageNo="'+paging.pageNo+'" newStatus="false">禁用</a></td></tr>';
 			}else{
 				trs +='<td>禁用</td>';
 				trs +='<td><a id="a-status-'+c.id+'" href="javascript://" valId="'+c.id+'" pageNo="'+paging.pageNo+'" newStatus="true">启用</a></td></tr>';
 			}
 		} 
 		$("#user-list").html(trs);
 		$("#a-paging-f").attr("val","1");
 		$("#a-paging-p").attr("val",paging.pagePrev);
 		$("#a-paging-n").attr("val",paging.pageNext);
 		$("#a-paging-t").attr("val",paging.pageTotal);
 		$("#span-pageNo").html(paging.pageNo);
 		$("#span-pageTotal").html(paging.pageTotal);
 		$("#span-countTotal").html(paging.countTotal);
 		$("a[id^='a-status-']").unbind();
 		$("a[id^='a-status-']").on("click",function(){
 			modifyCourseStatus($(this).attr("valId"),$(this).attr("newStatus"),$(this).attr("pageNo"));
 		});
 	});
}
function modifyCourseStatus(courseId,newStatus,pageNo){
	$.get("/oe/mgr/modifyCourseStatus?r="+Math.random(),{"courseId":courseId,"pageNo":pageNo,"newStatus":newStatus},function(data){
		loadCourse(data);
	});
	
	
}
    </script>
</head>
<body>
<div class="content">
    <%@ include file = "/WEB-INF/jsp/mgr/inc/header.jsp" %>
    <div class="container">
         <h2>用户状态管理</h2>
        <table id="user-list">
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