$(function(){
	$("#search-btn").on("click",function(){
		var temp = $("#search-input").val();
		location.href ="/oe/course/courseList?title="+temp;
	});
	$("#search-bar").remove();
});
