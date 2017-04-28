<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>科多在线教育网站--审核新注册的老师的界面</title>
    <link rel="stylesheet" href="/oe/css/mgr-css1.css" type="text/css">
    <script type="text/javascript" src="/oe/js/jquery-1.7.min.js"></script>
    <script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
    <script type="text/javascript" src="/oe/js/messages_zh.js"></script>
    <script type="text/javascript">
$(function(){
	loadTeacher(1);
	$("a[id^='a-paging-']").on("click",function(){
 		loadTeacher($(this).attr("val"));
 	});
 	
 	
});
function loadTeacher(pageNo){
	var trs="";
 	$.getJSON("/oe/mgr/loadCheckTeacher?r="+Math.random(),{"pageNo":pageNo},function(jsonData){
 		var teacherArr = jsonData["teacherList"];
 		var paging = jsonData["paging"];
 		trs ='<tr><td width="100">用户名</td>';
 		trs += '<td width="120">昵称</td>';
 		trs += '<td width="40">性别</td>';
 		trs += '<td width="300">简介</td>';
 		trs += '<td width="100">电子邮箱</td>';
 		trs += '<td width="90">操作</td></tr>';
 		for(var i in teacherArr){
 			var t = teacherArr[i];
 			trs +='<tr><td>'+t.loginId+'</td>';
 			trs +='<td>'+t.name+'</td>';
 			if(t.sex){
 				trs +='<td>男</td>';
 			}else{
 				trs +='<td>女</td>';
 			}
 			trs +='<td>'+t.introduction+'</td>';
 			trs +='<td>'+t.email+'</td>';
 			trs +='<td><a id="a-pass-'+t.id+'" href="javascript://" valLoginId="'+t.loginId+'" pageNo="'+paging.pageNo+'">通过</a>&nbsp;&nbsp;<a id="a-refuse-'+t.id+'" href="javascript://" valLoginId="'+t.loginId+'" pageNo="'+paging.pageNo+'">拒绝</a></td></tr>'
 		} 
 		$("#user-list").html(trs);
 		$("#a-paging-f").attr("val","1");
 		$("#a-paging-p").attr("val",paging.pagePrev);
 		$("#a-paging-n").attr("val",paging.pageNext);
 		$("#a-paging-t").attr("val",paging.pageTotal);
 		$("#span-pageNo").html(paging.pageNo);
 		$("#span-pageTotal").html(paging.pageTotal);
 		$("#span-countTotal").html(paging.countTotal);
 		$("a[id^='a-pass-']").unbind();
 		$("a[id^='a-pass-']").on("click",function(){
 			passTeacher($(this).attr("valLoginId"),$(this).attr("pageNo"));
 		});
 		$("a[id^='a-refuse-']").unbind();
 		$("a[id^='a-refuse-']").on("click",function(){
 			refuseTeacher($(this).attr("valLoginId"),$(this).attr("pageNo"));
 		});
 	});
}
function passTeacher(loginId,pageNo){
	$.get("/oe/mgr/passTeacher?r="+Math.random(),{"loginId":loginId,"pageNo":pageNo},function(data){
		loadTeacher(data);
	});
}
function refuseTeacher(loginId,pageNo){
	$.get("/oe/mgr/refuseTeacher?r="+Math.random(),{"loginId":loginId,"pageNo":pageNo},function(data){
		loadTeacher(data);
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