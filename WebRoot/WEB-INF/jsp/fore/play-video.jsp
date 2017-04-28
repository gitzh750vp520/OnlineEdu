<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>科多在线教育--视频播放界面</title>
    <link rel="stylesheet" href="/oe/css/css1.css" type="text/css"/>
    <script type="text/javascript" src="/oe/js/jquery-1.7.min.js"></script>
<link rel="stylesheet" type="text/css" href="/oe/css/import-res.css" />
<link rel="stylesheet" type="text/css" href="/oe/css/home.css" />
<script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
<script type="text/javascript" src="/oe/js/notelist.js"></script>
<link rel="stylesheet" type="text/css" href="/oe/css/notelist.css" />
<link rel="stylesheet" type="text/css" href="/oe/css/upload-course.css" />
    <script type="text/javascript">
    $(function(){
    	$("#addNewNoteForm").validate({
		submitHandler:function(){
 			addNote();
 		},
 		errorClass:"fail",
 		rules:{
 			noteContent:"required"
 		},
 		messages:{
 			noteContent:"不能提交空笔记！"
 		}
		});
    		$("#a-note").on("click",function(){
                $("#note").css("display","block");
                $("#comment").css("display","none");
            })
            $("#a-comment").on("click",function(){
                $("#note").css("display","none");
                $("#comment").css("display","block");
                $("#addNewCommentForm").validate({
				submitHandler:function(){
		 			addComment();
		 		},
		 		errorClass:"fail",
		 		rules:{
		 			commentContent:"required"
		 		},
		 		messages:{
		 			commentContent:"不能提交空评论！"
		 		}
				});
				loadComments(1);
            })
 			$("#otherNotes").on("click",function(){
 				loadOtherNotes(1);
 			});
 			$("#myNotes").on("click",function(){
 				loadMyNotes(1);
 			});
 			loadMyNotes(1);
        });

function loadMyNotes(pageNo){
	$("a[id^='a-note-paging-']").unbind();
	$("a[id^='a-note-paging-']").on("click",function(){
 		loadMyNotes($(this).attr("val"));
 	});
	var catalogId = $("#catalogId").val();
 	var trs="";
 	$.getJSON("/oe/note/loadUserNotes?r="+Math.random(),{"pageNo":pageNo,"catalogId":catalogId},function(jsonData){
 		var noteArr = jsonData["noteList"];
 		var paging = jsonData["paging"];
 		trs = '<ul>'
 		for(var i in noteArr){
 			var n = noteArr[i];
 			trs +='<li>'+n.content+'</li>';
 		} 
 		trs += '</ul>'
 		$("#note_content_ul").html(trs);
 		$("#a-note-paging-f").attr("val","1");
 		$("#a-note-paging-p").attr("val",paging.pagePrev);
 		$("#a-note-paging-n").attr("val",paging.pageNext);
 		$("#a-note-paging-t").attr("val",paging.pageTotal);
 		$("#span-pageNo").html(paging.pageNo);
 		$("#span-pageTotal").html(paging.pageTotal);
 		$("#span-countTotal").html(paging.countTotal);
 	});
 }
 function loadOtherNotes(pageNo){
 	$("a[id^='a-note-paging-']").unbind();
	$("a[id^='a-note-paging-']").on("click",function(){
 		loadOtherNotes($(this).attr("val"));
 	});
	var catalogId = $("#catalogId").val();
 	var trs="";
 	$.getJSON("/oe/note/loadOtherNotes?r="+Math.random(),{"pageNo":pageNo,"catalogId":catalogId},function(jsonData){
 		var noteArr = jsonData["noteList"];
 		var paging = jsonData["paging"];
 		trs = '<ul>'
 		for(var i in noteArr){
 			var n = noteArr[i];
 			trs +='<li>'+n.user.name+'说'+n.content+'</li>';
 		} 
 		trs += '</ul>'
 		$("#note_content_ul").html(trs);
 		$("#a-note-paging-f").attr("val","1");
 		$("#a-note-paging-p").attr("val",paging.pagePrev);
 		$("#a-note-paging-n").attr("val",paging.pageNext);
 		$("#a-note-paging-t").attr("val",paging.pageTotal);
 		$("#span-pageNo").html(paging.pageNo);
 		$("#span-pageTotal").html(paging.pageTotal);
 		$("#span-countTotal").html(paging.countTotal);
 	});
 }
 function loadComments(pageNo){
 	var catalogId = $("#catalogId").val();
 	$("a[id^='a-comment-paging-']").unbind();
	$("a[id^='a-comment-paging-']").on("click",function(){
 		loadComments($(this).attr("val"));
 	});
 	var trs="";
 	$.getJSON("/oe/comment/showComments?r="+Math.random(),{"pageNo":pageNo,"catalogId":catalogId},function(jsonData){
 		var commentArr = jsonData["commentList"];
 		var paging = jsonData["paging"];
 		trs = '<div>'
 		for(var i in commentArr){
 			var c = commentArr[i];
 			trs +='<div id="div-comment-'+c.id+'" class="div-comment"><span class="show-name">'+c.user.name+'</span>说'+c.content;
 			trs +='<a id="a-reply-'+ c.id +'" href="javascript://" val="'+c.id+'">回复</a>';
 			if(c.replies != null){
 				var rs = c.replies;
 				trs += '<div><ul>';
 				for(var j in rs){
 					var r = rs[j];
 					trs += '<li><span class="show-name">'+ r.sendUser.name + '</span>对<span class="show-name">' + r.receiveUser.name + '</span>说：' + r.content + '</li>'
 				}
 				trs += '</ul></div>';
 			}
 			trs += '<div id="show-reply-form-'+c.id+'" class="div-reply">';
 			trs +='<form id="form-reply-'+c.id+'"><textarea id="textarea-'+c.id+'" rows="3" cols="50" name="replyContent" pageNo="'+paging.pageNo+'"></textarea>';
 			trs +='<input type="submit" value="回复"/>'
 			trs +='</form>';
 			trs +='<a id="close-show-reply-form-'+c.id+'" hred="javascript://">收起</a>'
 			trs +='</div>';
 			trs += '</div>'
 		} 
 		trs += '</div>'
 		$("#comment_list").html(trs);
 		$("#a-comment-paging-f").attr("val","1");
 		$("#a-comment-paging-p").attr("val",paging.pagePrev);
 		$("#a-comment-paging-n").attr("val",paging.pageNext);
 		$("#a-comment-paging-t").attr("val",paging.pageTotal);
 		$("#span-comment-pageNo").html(paging.pageNo);
 		$("#span-comment-pageTotal").html(paging.pageTotal);
 		$("#span-comment-countTotal").html(paging.countTotal);
 		$("a[id^='a-reply-']").on("click",function(){
 			addNewReply($(this).attr("val"));
 		});
 	});
 }
 function addNote(){
 	var content = $("textarea[name='noteContent']").val();
 	var catalogId = $("#catalogId").val();
	$.post("/oe/note/addNote",{"content":content,"catalogId":catalogId},function(data){
		loadMyNotes(data);
	});
	$("textarea[name='noteContent']").val("");
 }
 function addComment(){
 	var content = $("textarea[name='commentContent']").val();
 	var catalogId = $("#catalogId").val();
 	$.post("/oe/comment/addComment",{"content":content,"catalogId":catalogId},function(data){
 		loadComments(data);
 	});
	$("textarea[name='commentContent']").val("");
 }
 function addNewReply(commentId){
 	$("#show-reply-form-"+commentId).css("display","block");
 	$("#close-show-reply-form-"+commentId).on("click",function(){
 		$("#show-reply-form-"+commentId).css("display","none");
 	});
 	$("#form-reply-"+commentId).validate({
		submitHandler:function(){
 			addReply(commentId);
 		},
 		errorClass:"fail",
 		rules:{
 			replyContent:"required"
 		},
 		messages:{
 			replyContent:"不能提交空回复！"
 		}
		});
 	
 }
 function addReply(commentId){
 	var content = $("#textarea-"+commentId).val();
 	var pageNo = $("#textarea-"+commentId).attr("pageNo");
 	$.post("/oe/reply/addReply",{"content":content,"commentId":commentId,"pageNo":pageNo},function(data){
 		loadComments(data);
 	});
 	$("#textarea-"+commentId).val("");
 }
    </script>
  </head>
  
  <body>
<div id="main">
<%@include file="inc/header.jsp"%>
  <div class="container">
  <h2>${ catalog.title }</h2>
  <input type="hidden" id="catalogId" value="${catalog.id}" />
 	 <div id="play_video">
	    <video src="/oe/resource/video/${ catalog.videoName }"  width="800" height="400" controls="controls">
			您的浏览器不支持视频标签。
		</video>
    </div>
    <div id="navigation">
    	<ul>
    		<li><a href="javascript://" id="a-note">笔记</a></li>
    		<li><a href="javascript://" id="a-comment">评论</a></li>
    		<li>播放次数：${catalog.clickTimes} 次</li>
    	</ul>
    </div>
    <div id="note">
    	<form id="addNewNoteForm">
    	<p>记笔记</p>
    		<textarea rows="5" cols="100" name="noteContent"></textarea>
    		<input type="submit" value="记录"/>
    	</form>
    	<ul>
    		<li><a href="javascript://" id="myNotes">我的笔记</a></li>
    		<li><a href="javascript://" id="otherNotes">同学们的笔记</a></li>
    	</ul>
    	<div id="note_content">
		   	<div id="note_content_ul">
		   	</div>
	    	<div style="height:40px;line-height:40px;font-size:12px">
	    		<a href="javascript://" id="a-note-paging-f" val="1">首页</a>&nbsp;&nbsp;
	    		<a href="javascript://" id="a-note-paging-p" val="">上一页</a>&nbsp;&nbsp;
	    		<a href="javascript://" id="a-note-paging-n" val="">下一页</a>&nbsp;&nbsp;
	    		<a href="javascript://" id="a-note-paging-t" val="">尾页</a>&nbsp;&nbsp;
	    		<span id="span-pageNo"></span>/<span id="span-pageTotal"></span>页  共计<span id="span-countTotal"></span>条
		    </div>
    	</div>
    </div>
    <div id="comment">
    	<form id="addNewCommentForm">
    		<p id="test-p">发表评论</p>
    		<textarea rows="5" cols="100" name="commentContent"></textarea>
    		<input type="submit" value="发表"/>
    	</form>
    	<div id="comment_list">
    	</div>
    	<div style="height:40px;line-height:40px;font-size:12px">
    		<a href="javascript://" id="a-comment-paging-f" val="1">首页</a>&nbsp;&nbsp;
    		<a href="javascript://" id="a-comment-paging-p" val="">上一页</a>&nbsp;&nbsp;
    		<a href="javascript://" id="a-comment-paging-n" val="">下一页</a>&nbsp;&nbsp;
    		<a href="javascript://" id="a-comment-paging-t" val="">尾页</a>&nbsp;&nbsp;
    		<span id="span-comment-pageNo"></span>/<span id="span-comment-pageTotal"></span>页  共计<span id="span-comment-countTotal"></span>条
		</div>
    </div>
  </div>
		<%@include file="inc/footer.jsp"%>
	</div>
  </body>
</html>
