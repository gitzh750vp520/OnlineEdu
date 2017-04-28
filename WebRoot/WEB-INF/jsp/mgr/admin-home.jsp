<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>科多在线教育网站--管理员主页</title>
    <link rel="stylesheet" href="/oe/css/mgr-css1.css" type="text/css">
    <script type="text/javascript" src="/oe/js/jquery-1.7.min.js"></script>
    <script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
    <script type="text/javascript" src="/oe/js/messages_zh.js"></script>
    <script type="text/javascript">
$(function(){
  	$("#addAdminForm").validate({
	errorClass:"fail",
	rules:{
		loginId:"required",
		loginPsw:"required",
		name:"required"
	},
	messages:{
		loginId:"用户名不能为空",
		loginPsw:"密码不能为空",
		name:"姓名不能为空"
	}
	});
	$("a[id^='a-paging-']").on("click",function(){
 		loadAdmins($(this).attr("val"));
 	});
 	$("a[id^='a-modify-status-']").on("click",function(){
 		modifyAdminStatus($(this).attr("valLoginId"),$(this).attr("pageNo"));
 	});
 	$("a[id^='a-delete-admin-']").on("click",function(){
 		deleteAdmin($(this).attr("valId"),$(this).attr("pageNo"));
 	});
 	
 	$("#a-modifyLoginPsw").on("click",function(){
 		$("#div-modeifyLoginPsw").css("display","block");
        $("#div-modifyAdminInfo").css("display","none");
        $("#modifyLoginPswForm").validate({
		errorClass:"fail",
		rules:{
			oldLoginPsw:"required",
			newLoginPsw:"required"
		},
		messages:{
			oldLoginPsw:"旧密码不能为空",
			newLoginPsw:"新密码不能为空"
		}
		});
 	});
 	$("#a-modifyAdminInfo").on("click",function(){
 		$("#div-modeifyLoginPsw").css("display","none");
        $("#div-modifyAdminInfo").css("display","block");
        $("#modifyAdminInfoForm").validate({
		errorClass:"fail",
		rules:{
			newName:"required"
		},
		messages:{
			newName:"姓名不能为空"
		}
		});
 	});
});
function modifyAdminStatus(loginId,pageNo){
	$.get("/oe/mgr/modifyAdminStatus?r="+Math.random(),{"loginId":loginId,"pageNo":pageNo},function(data){
		loadAdmins(data);
	});
}
function deleteAdmin(adminId,pageNo){
	$.get("/oe/mgr/deleteAdmin?r="+Math.random(),{"adminId":adminId,"pageNo":pageNo},function(data){
		loadAdmins(data);
	});
}

function loadAdmins(pageNo){
	var trs="";
 	$.getJSON("/oe/mgr/loadAdmins?r="+Math.random(),{"pageNo":pageNo},function(jsonData){
 		var adminArr = jsonData["adminList"];
 		var paging = jsonData["paging"];
 		trs ='<tr><td width="110">用户名</td>';
 		trs += '<td width="70">姓名</td>';
 		trs += '<td width="100">类别</td>';
 		trs += '<td width="60">状态</td>';
 		trs += '<td width="90">操作</td></tr>';
 		for(var i in adminArr){
 			var a = adminArr[i];
 			trs +='<tr><td>'+a.loginId+'</td>';
 			trs +='<td>'+a.name+'</td>';
 			trs +='<td>普通管理员</td>';
 			if(a.status){
 				trs +='<td>可用</td>';
 				trs +='<td><a id="a-modify-status-'+a.loginId+'" href="javascript://" valLoginId="'+a.loginId+'" pageNo="'+paging.pageNo+'">禁用</a>&nbsp;&nbsp;<a id="a-delete-admin-'+a.id+'" valId="'+a.id+'" href="javascript://" pageNo="'+paging.pageNo+'">删除</a></td></tr>';
 			}else{
 				trs +='<td>禁用</td>';
 				trs +='<td><a id="a-modify-status-'+a.loginId+'" href="javascript://" valLoginId="'+a.loginId+'" pageNo="'+paging.pageNo+'">启用</a>&nbsp;&nbsp;<a id="a-delete-admin-'+a.id+'" valId="'+a.id+'" href="javascript://" pageNo="'+paging.pageNo+'">删除</a></td></tr>';
 			}
 		} 
 		$("#admin-list").html(trs);
 		$("#a-paging-f").attr("val","1");
 		$("#a-paging-p").attr("val",paging.pagePrev);
 		$("#a-paging-n").attr("val",paging.pageNext);
 		$("#a-paging-t").attr("val",paging.pageTotal);
 		$("#span-pageNo").html(paging.pageNo);
 		$("#span-pageTotal").html(paging.pageTotal);
 		$("#span-countTotal").html(paging.countTotal);
 		$("a[id^='a-modify-status-']").unbind();
 		$("a[id^='a-modify-status-']").on("click",function(){
 			modifyAdminStatus($(this).attr("valLoginId"),$(this).attr("pageNo"));
 		});
 		$("a[id^='a-delete-admin-']").unbind();
 		$("a[id^='a-delete-admin-']").on("click",function(){
 			deleteAdmin($(this).attr("valId"),$(this).attr("pageNo"));
 		});
 	});
}
    </script>
</head>
<body>
<div class="content">
    <%@ include file = "/WEB-INF/jsp/mgr/inc/header.jsp" %>
    <div class="container">
        <div id="left-div">
            <h2>管理员个人信息</h2>
            <table>
                <tr>
                    <td>管理员姓名</td>
                    <td>${currAdmin.name}</td>
                </tr>
                <tr>
                    <td>管理员级别</td>
                    <c:choose>
					   <c:when test="${currAdmin.type}">  
					         <td>普通管理员</td>    
					   </c:when>
					   <c:otherwise> 
					     <td>超级管理员</td>
					   </c:otherwise>
					</c:choose>
                </tr>
                <tr>
                    <td><a id="a-modifyLoginPsw" href="javascript://">修改密码</a></td>
                    <td><a id="a-modifyAdminInfo" href="javascript://">修改个人信息</a></td>
                </tr>
            </table>
            <div id="div-modeifyLoginPsw">
            	<p>修改个人密码</p>
            	<form action="modifyPassword" method="post" id="modifyLoginPswForm">
            		<p>请输入旧密码：<input type="password" name="oldLoginPsw"/></p>
            		<p>请输入新密码：<input type="password" name="newLoginPsw"/></p>
            		<p><input type="submit" value="修改" /></p>
            	</form>
            </div>
            <p>${modifyLoginPswMsg}</p>
            <div id="div-modifyAdminInfo">
            	<p>修改个人信息</p>
            	<form action="modifyAdminInfo" method="post" id="modifyAdminInfoForm">
            		<p>请输入新姓名：<input type="text" name="newName"/></p>
            		<p><input type="submit" value="修改" /></p>
            	</form>
            </div>
        </div>
        <div id="right-div">
        	<c:if test="${currAdmin.loginId eq 'admin'}">
        	<h2>普通管理员列表</h2>
            <table id="admin-list">
                <tr>
                    <td width="110">用户名</td>
                    <td width="70">姓名</td>
                    <td width="100">类别</td>
                    <td width="60">状态</td>
                    <td width="90">操作</td>
                </tr>
                <c:forEach items="${adminList}" var="a">
					<tr>
						<td>${ a.loginId }</td>
						<td>${ a.name}</td>
						<td>普通管理员</td>
						<c:choose>
						   <c:when test="${a.status}">  
						   	<td>可用</td>    
						   </c:when>
						   <c:otherwise> 
						     <td>禁用</td>
						   </c:otherwise>
						</c:choose>
						<td>
						<a id="a-modify-status-${ a.loginId }" href = "javascript://" valLoginId ="${ a.loginId }" pageNo="${paging.pageNo}" >${ a.status eq true?'禁用':'启用'}</a>&nbsp;&nbsp;<a id="a-delete-admin-${ a.id }" href = "javascript://" valId ="${ a.id }" pageNo="${paging.pageNo}">删除</a>
						</td>
					</tr>
					</c:forEach>
            </table>
            <div style="height:40px;line-height:40px;font-size:12px">
                <a href="javascript://" id="a-paging-f" val="1">首页</a>&nbsp;&nbsp;
                <a href="javascript://" id="a-paging-p" val="1" >上一页</a>&nbsp;&nbsp;
                <a href="javascript://" id="a-paging-n" val="2">下一页</a>&nbsp;&nbsp;
                <a href="javascript://" id="a-paging-t" val="${paging.pageTotal }">尾页</a>&nbsp;&nbsp;
                <span id="span-pageNo">${paging.pageNo}</span>/<span id="span-pageTotal">${paging.pageTotal}</span>页  共计<span id="span-countTotal">${paging.countTotal }</span>条
            </div>
            <h3>添加新用户</h3>
            <div id="add-admin">
                <form action="/oe/mgr/addAdmin" method="post" id="addAdminForm">
                    <table>
                        <tr>
                            <td>用户名</td>
                            <td><input type="text" name="loginId"/></td>
                        </tr>
                        <tr>
                            <td>密码</td>
                            <td><input type="password" name="loginPsw"/></td>
                        </tr>
                        <tr>
                            <td>姓名</td>
                            <td><input type="text" name="name"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="添加"/></td>
                        </tr>
                    </table>
                </form>
                <p>${ addAdminMsg }</p>
            </div>
        	</c:if>
        </div>
    </div>
    <%@ include file = "/WEB-INF/jsp/mgr/inc/footer.jsp" %>
</div>
</body>
</html>