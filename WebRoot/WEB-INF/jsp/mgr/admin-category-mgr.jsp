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
	loadCategory(1);
	$("a[id^='a-paging-']").on("click",function(){
 		loadCategory($(this).attr("val"));
 	});
});
function loadCategory(pageNo){
	var trs="";
 	$.getJSON("/oe/mgr/loadCategory?r="+Math.random(),{"pageNo":pageNo},function(jsonData){
 		var categoryArr = jsonData["categoryList"];
 		var paging = jsonData["paging"];
 		trs ='<tr><td width="110">分类名</td>';
 		trs += '<td width="110">父分类名</td></tr>';
 		for(var i in categoryArr){
 			var c = categoryArr[i];
 			trs +='<tr><td>'+c.name+'</td>';
 			trs +='<td>'+c.superCategory.name+'</td></tr>';
 		} 
 		$("#category-list").html(trs);
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
</script>
</head>
<body>
<div class="content">
    <%@ include file = "/WEB-INF/jsp/mgr/inc/header.jsp" %>
    <div class="container">
    <div id="left-div">
    	<h2>添加新分类</h2>
    	<form action="/oe/mgr/addCategeory" method="post">
    		<p>分类名：<input type="text" name="name" /></p>
    		<p>所属父分类：
    			<select name="supcategoryId">
    				<c:forEach items="${supList }" var="s">
    					<option value="${s.id}">${s.name}</option>
    				</c:forEach>
    			</select>
    		</p>
    		<p><input type="submit" value="添加" /></p>
    	</form>
    	<P>${ addCategoryMsg }</P>
    	<h2>添加父分类</h2>
    	<form action="/oe/mgr/addSuperCategory" method="post">
    		<p>分类名：<input type="text" name="name" /></p>
    		<p><input type="submit" value="添加" /></p>
    	</form>
    	<P>${ addSuperCategoryMsg }</P>
    </div>
    <div id="right-div">
    	<div id="show-category">
    		<h2>所有分类</h2>
        	<table id="category-list">
       		 </table>
        	<div style="height:40px;line-height:40px;font-size:12px">
            <a href="javascript://" id="a-paging-f" val="">首页</a>&nbsp;&nbsp;
            <a href="javascript://" id="a-paging-p" val="" >上一页</a>&nbsp;&nbsp;
            <a href="javascript://" id="a-paging-n" val="">下一页</a>&nbsp;&nbsp;
            <a href="javascript://" id="a-paging-t" val="">尾页</a>&nbsp;&nbsp;
            <span id="span-pageNo"></span>/<span id="span-pageTotal"></span>页  共计<span id="span-countTotal"></span>条
        	</div>
    	</div>
    	<div id="modify-category-name">
    		<h2>修改父分类名</h2>
    		<form action="/oe/mgr/modifySuperCategory" method="post">
    		<p>请输入要修改的父分类名：</p>
    		<p><input type="text" name="oldSupName" /></p>
    		<p>请输入修改后的父分类名：</p>
    		<p><input type="text" name="newSupName" /></p>
    		<p><input type="submit" value="修改" />${ modifySupNameMsg }</p>
    		</form>
    		<h2>修改子分类名</h2>
    		<form action="/oe/mgr/modifyCategory" method="post">
    		<p>请输入要修改的子分类名：</p>
    		<p><input type="text" name="oldSubName" /></p>
    		<p>请输入修改后的子分类名：</p>
    		<p><input type="text" name="newSubName" /></p>
    		<p><input type="submit" value="修改" />${ modifySubNameMsg }</p>
    		</form>
    	</div>
    </div>
    </div>
    <%@ include file = "/WEB-INF/jsp/mgr/inc/footer.jsp" %>
</div>
</body>
</html>