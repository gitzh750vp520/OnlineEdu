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
	loadNote(1);
	$("a[id^='a-paging-']").on("click",function(){
 		loadNote($(this).attr("val"));
 	});
 	
 	
});
function loadNote(pageNo){
	var trs="";
 	$.getJSON("/oe/mgr/loadPendingNote?r="+Math.random(),{"pageNo":pageNo},function(jsonData){
 		var noteArr = jsonData["noteList"];
 		var paging = jsonData["paging"];
 		trs ='<tr><td width="100">用户昵称</td>';
 		trs += '<td width="260">笔记所属视频名</td>';
 		trs += '<td width="400">笔记内容</td>';
 		trs += '<td width="130">给金币</td>';
 		trs += '<td width="80">不给金币</td></tr>';
 		for(var i in noteArr){
 			var n = noteArr[i];
 			trs +='<tr><td>'+n.user.name+'</td>';
 			trs +='<td>'+n.catalog.title+'</td>';
 			trs +='<td>'+n.content+'</td>';
 			trs +='<td><form><select id="select-gold-'+n.id+'" name="gold">';
 			trs +='<option value="1">1个金币</option><option value="2">2个金币</option><option value="3">3个金币</option>';
            trs +='</select><input id="input-give-gold-'+n.id+'" type="button" value="给金币" valId="'+n.id+'" pageNo="'+paging.pageNo+'" valUserId="'+n.user.id+'"/></form></td>';
          	trs +='<td><a id="a-refuse-'+n.id+'" href="javascript://" valId="'+n.id+'" pageNo="'+paging.pageNo+'">拒绝</a></td></tr>';       
 		}
 		$("#note-list").html(trs);
 		$("#a-paging-f").attr("val","1");
 		$("#a-paging-p").attr("val",paging.pagePrev);
 		$("#a-paging-n").attr("val",paging.pageNext);
 		$("#a-paging-t").attr("val",paging.pageTotal);
 		$("#span-pageNo").html(paging.pageNo);
 		$("#span-pageTotal").html(paging.pageTotal);
 		$("#span-countTotal").html(paging.countTotal);
 		$("input[id^='input-give-gold-']").unbind();
 		$("input[id^='input-give-gold-']").on("click",function(){
 			giveNoteGold($(this).attr("valId"),$(this).attr("valUserId"),$(this).attr("pageNo"));
 		});
 		$("a[id^='a-refuse-']").unbind();
 		$("a[id^='a-refuse-']").on("click",function(){
 			refuseNote($(this).attr("valId"),$(this).attr("pageNo"));
 		});
 	});
}
function refuseNote(noteId,pageNo){
	$.get("/oe/mgr/refuseNote?r="+Math.random(),{"noteId":noteId,"pageNo":pageNo},function(data){
		loadNote(data);
	});
}
function giveNoteGold(noteId,userId,pageNo){
	var gold = $("#select-gold-"+noteId).val();
	$.get("/oe/mgr/giveNoteGold?r="+Math.random(),{"noteId":noteId,"userId":userId,"pageNo":pageNo,"gold":gold},function(data){
		loadNote(data);
	});
	
}
    </script>
</head>
<body>
<div class="content">
    <%@ include file = "/WEB-INF/jsp/mgr/inc/header.jsp" %>
    <div class="container">
         <h2>用户提交的笔记的管理</h2>
        <table id="note-list">
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