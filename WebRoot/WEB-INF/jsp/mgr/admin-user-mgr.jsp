<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>科多在线教育网站--用户管理界面</title>
    <link rel="stylesheet" href="/oe/css/mgr-css1.css" type="text/css">
    <script type="text/javascript" src="/oe/js/jquery-1.7.min.js"></script>
    <script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
    <script type="text/javascript" src="/oe/js/messages_zh.js"></script>
    <script type="text/javascript">
$(function(){
	loadUser(1);
	$("a[id^='a-paging-']").on("click",function(){
 		loadUser($(this).attr("val"));
 	});
 	
 	
});
function loadUser(pageNo){
	var trs="";
 	$.getJSON("/oe/mgr/loadAvailableUser?r="+Math.random(),{"pageNo":pageNo},function(jsonData){
 		var userArr = jsonData["userList"];
 		var paging = jsonData["paging"];
 		trs ='<tr><td width="100">用户名</td>';
 		trs += '<td width="120">昵称</td>';
 		trs += '<td width="100">金币数</td>';
 		trs += '<td width="80">类别</td>';
 		trs += '<td width="60">状态</td>';
 		trs += '<td width="90">操作</td></tr>';
 		for(var i in userArr){
 			var u = userArr[i];
 			trs +='<tr><td>'+u.loginId+'</td>';
 			trs +='<td>'+u.name+'</td>';
 			trs +='<td>'+u.gold+'</td>';
 			if(u.type == 0){
 				trs +='<td>老师</td>';
 			}else{
 				trs +='<td>学生</td>';
 			}
 			if(u.status){
 				trs +='<td>可用</td>';
 				trs +='<td><a id="a-modifyUserStatus-'+u.loginId+'" href="javascript://" valLoginId="'+u.loginId+'" pageNo="'+paging.pageNo+'">禁用</a></td></tr>';
 			}else{
 				trs +='<td>禁用</td>';
 				trs +='<td><a id="a-modifyUserStatus-'+u.loginId+'" href="javascript://" valLoginId="'+u.loginId+'" pageNo="'+paging.pageNo+'">启用</a></td></tr>';
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
 		$("a[id^='a-modifyUserStatus-']").unbind();
 		$("a[id^='a-modifyUserStatus-']").on("click",function(){
 			modifyUserStatus($(this).attr("valLoginId"),$(this).attr("pageNo"));
 		});
 	});
}
function modifyUserStatus(loginId,pageNo){
	$.get("/oe/mgr/modifyUserStatus?r="+Math.random(),{"loginId":loginId,"pageNo":pageNo},function(data){
		loadUser(data);
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