$(function(){
	$("#search-btn").on("click",function(){
		var temp = $("#search-input").val();
		location.href ="/oe/course/courseList?title="+temp;
	});
	$("#search-bar").remove();
	
	$(".note-cate-sp").next("ul").hide();
	$(".note-cate-sp").on("click",function(){
		var spId = $(this).next("ul").attr("id").substring(8);
		if(spId=='all'){
			loadNotes({'superCategoryId':0});
		}else{
			loadNotes({'superCategoryId':spId});
		}
		$(this).next("ul").slideToggle();
		
	});
	
	$(".note-cate-sb-a").on("click",function(){
		var sbId = $(this).attr("id").substring(4);
		loadNotes({'subCategoryId':sbId});
	});
	loadNotes({'superCategoryId':0});
});
function loadNotes(params){
	$(".note-content").remove();
	$("#note-paging").remove();
	$.getJSON("/oe/note/loadUserNotesSpecfic",params,function(data){
		var paging = data['paging'];
		var notes = data['notes'];
		/*<div class="note-content"><p></p></div>*/
		for(n in notes){
			$("#notes-list").append('<div class="note-content"><h4>'+notes[n].catalog.title+':</h4><p>&nbsp;&nbsp;'+notes[n].content+'</p></div>');
		}
		var pagingDiv='<div id="note-paging">';
			pagingDiv+='<a href="javascript://" id="n-1">首页</a>';
			pagingDiv+='<a href="javascript://" id="n-'+paging['pagePrev']+'">上一页</a>';
			pagingDiv+='<a href="javascript://" id="n-'+paging['pageNext']+'">下一页</a>';
			pagingDiv+='<a href="javascript://" id="n-'+paging['pageTotal']+'">尾页</a>';
			pagingDiv+='<span>当前 '+paging['pageNo']+'/'+paging['pageTotal']+'</span>';
			pagingDiv+='</div>';
		$("#notes-list").append(pagingDiv);
		
		$("#note-paging a").on("click",function(){
			var pageNO = $(this).attr("id").substring(2);
			loadNotes({'pageNoStr':pageNO});
		});
	});
}
