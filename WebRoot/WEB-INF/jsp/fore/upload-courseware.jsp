<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>科多在线教育--上传课件界面</title>
    <link rel="shortcut icon" href="/oe/resource/favicon.ico" />
    <script type="text/javascript" src="/oe/js/jquery-1.7.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/oe/css/import-res.css" />
	<link rel="stylesheet" type="text/css" href="/oe/css/home.css" />
	<script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
	<script type="text/javascript" src="/oe/js/remove-cate.js"></script>
	<link rel="stylesheet" type="text/css" href="/oe/css/notelist.css" />
	<link rel="stylesheet" type="text/css" href="/oe/css/upload-courseware.css" />
    <script type="text/javascript">
    $(function(){
    	$("#add-new-file").on("click",function(){
    		add_new_file();
    	});
    });
    i = 2;
    function add_new_file(){
    	var trs = "";
    	trs += "<div class='div-upload-courseware'>";
    	trs += "<p>课件的名称:<input type='text' name='name"+i+"'/></p>";
    	trs += "<p>上传课件:<input type='file' name='file"+i+"' /></p>";
    	trs += "</div>";
    	$("#uploadCoursewareFirst").after(trs);
    	i = i + 1;
    }
    </script>
  </head>
<body>
<div id="main">
	<%@include file="inc/header.jsp"%>
	<div class="container">
	<div class="style-upload">
	<form id="uploadCoursewareForm" action="/oe/courseware/upload?courseId=${courseId}" method="post" enctype="multipart/form-data">
        <div id="uploadCoursewareFirst" class="div-upload-courseware">
        <p>课件的名称:<input type="text" name="name1"/></p>
        <p>上传课件:<input type="file" name="file1" /></p>
        </div>
        <p><input type="submit" value="上传"/></p>
    </form>
    <p><input type="button" id="add-new-file" value="继续上传" /></p>
	</div>
    </div>
	<%@include file="inc/footer.jsp"%>
</div>
</body>
</html>
