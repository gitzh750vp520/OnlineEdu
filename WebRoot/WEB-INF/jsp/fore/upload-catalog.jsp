<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>科多在线教育--上传视频界面</title>
<script type="text/javascript" src="/oe/js/jquery-1.7.min.js"></script>
<link rel="stylesheet" type="text/css" href="/oe/css/import-res.css" />
<link rel="stylesheet" type="text/css" href="/oe/css/home.css" />
<script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
<script type="text/javascript" src="/oe/js/remove-cate.js"></script>
<link rel="stylesheet" type="text/css" href="/oe/css/notelist.css" />
<link rel="stylesheet" type="text/css" href="/oe/css/upload-catalog.css" />
    <script type="text/javascript">
    $(function(){
    	$("#add-new-file").on("click",function(){
    		add_new_file();
    	});
    	i = parseInt($("#div-upload-catalog-first").attr("valSequence")) + 1;
    });
   var i;
    function add_new_file(){
    	$("#div-upload-catalog-first").attr("valSequence")
    	var trs = "";
    	trs += "<div class='div-upload-catalog'>";
    	trs += "<p>视频顺序"+i+"</p>";
    	trs += "<p>视频的名称:<input type='text' name='title"+i+"'/></p>";
    	trs += "<p>上传视频:<input type='file' name='file"+i+"' /></p>";
    	trs += "</div>";
    	$("#containsUploadCatalog").append(trs);
    	i = i + 1;
    }
    </script>
  </head>
<body>
<div id="main">
	<%@include file="inc/header.jsp"%>
	<div class="container">
	<div class="style-upload">
	<form id="uploadVideoForm" action="/oe/catalog/upload?courseId=${courseId}" method="post" enctype="multipart/form-data">
        <div id="containsUploadCatalog">
        <div id="div-upload-catalog-first" class="div-upload-catalog" valSequence="${ sequence }">
        <p>视频顺序${ sequence }</p>
        <p>视频的名称:<input type="text" name="title${ sequence }"/></p>
        <p>上传视频:<input type="file" name="file1" /></p>
        </div>
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
