<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>科多在线教育</title>
<link rel="shortcut icon" href="/oe/resource/favicon.ico" />
<script type="text/javascript" src="/oe/js/jquery-1.7.min.js"></script>
<link rel="stylesheet" type="text/css" href="/oe/css/import-res.css" />
<link rel="stylesheet" type="text/css" href="/oe/css/home.css" />
<script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
<script type="text/javascript" src="/oe/js/remove-cate.js"></script>
<link rel="stylesheet" type="text/css" href="/oe/css/notelist.css" />
<link rel="stylesheet" type="text/css" href="/oe/css/upload-course.css" />
 <script type="text/javascript">
 $(function(){
    	$("#form-upload-course").validate({
		submitHandler:function(){
 			addNote();
 		},
 		errorClass:"fail",
 		rules:{
 			title:{
 				required:true,
 				maxlength:20
 			},
 			price:{
 				required:true,
 				digits:true
 			},
 			target:"required",
 			suitable:"required",
 			demand:"required"
 		},
 		messages:{
 			title:{
 				required:"课程名称不能为空！",
 				maxlength:"课程名称长度不能超过20！"
 			},
 			price:{
 				required:"价格不能为空！",
 				digits:"价格必须是整数"
 			},
 			target:"学习目标不能为空！",
 			suitable:"适用人群不能为空！",
 			demand:"学习需求不能为空！"
 		}
		});
});
 </script>
</head>
<body>
<div id="main">
	<%@include file="inc/header.jsp"%>
		<div class="container">
		<div class="style-upload">
		<form id="form-upload-course" action="/oe/course/upload" method="post" enctype="multipart/form-data">
        <p>课程的名称:<input type="text" name="title" value="${ requestScope.course.title }"/></p>
        <p>课程分类：
        <select name="categoryId">
        	<c:forEach items="${categoryList }" var="c" >
        		<option value="${c.id}">${c.name }</option>
        	</c:forEach>
        </select>
        </p>
        <p>课程的价格:<input type="text" name="price" value="${ requestScope.course.price }"/></p>
        <p>课程的目标:<input type="text" name="target" value="${ requestScope.course.target }" /></p>
        <p>课程的适用人群:<input type="text" name="suitable" value="${ requestScope.course.suitable }" /></p>
        <p>课程的需求:<input type="text" name="demand" value="${ requestScope.course.demand }"/></p>
        <p>课程的展示图片:<input type="file" name="photo" /></p>
        <p><input type="submit" value="上传" /></p>
    	</form>
    	<p>${ loadMsg }</p>
		</div>
    	</div>
		<%@include file="inc/footer.jsp"%>
	</div>
</body>
</html>
