function loadCollectedCourses(pageNO) {

	$.getJSON("/oe/course/collectedCourses", {
		'pageNO' : pageNO
	}, function(data) {
		parseData(data);
	});

}
function loadPurchasedCourses(pageNO) {

	$.getJSON("/oe/course/purchasedCourses", {
		'pageNO' : pageNO
	}, function(data) {
		parseData(data);
	});

}
function loadUploadedCourses(pageNO) {
	$.getJSON("/oe/course/uploadedCourses", {
		'pageNO' : pageNO
	}, function(data) {
		parseData(data);
	});

}
function parseData(d) {
	$("#pageInfo").remove();
	$("#courses-content .course-div ").remove();
	var cds = '';
	var courses = d['courses'];
	var paging = d['paging'];
	for ( var c in courses) {
		cds += '<div class="course-div">';
		cds += '<label> <img width="230px" height="150px" ';
		cds += ' src="/oe/resource/photo/course/' + courses[c].photo
				+ '" /><a ';
		cds += ' class="course-link" href="';
		var href = "javascript://";
		if ($("#choose").html()==3 ) {
			if(courses[c].handle == 101){
				href = '/oe/catalog/uploadForm?courseId=' + courses[c].id;
			}
		} else {
			href = '/oe/course/details?id=' + courses[c].id;
		}
		cds += href + '">';
		if ($("#choose").html() == 3) {
			if (courses[c].handle == 100) {
				cds += "<span class='handle-status'>该课程正在审核中<span>";
			} else {
				cds += "<span class='handle-status-pass'>课程审核通过，请上传视频<span>";
			}
		}
		cds+=  '</a> <span class="course-title">' + courses[c].title;
		cds += '</span> <span class="need-gold-txt">所需金币：</span><span ';
		cds += ' class="need-gold-num">' + Math.floor(courses[c].price
				* courses[c].discount) + '</span> <span ';
		cds += ' class="course-teacher">' + courses[c].user.name
				+ '</span> <img';
		cds += ' class="gold-icon" width="30" height="30" ';
		cds += ' src="/oe/resource/goldIcon.jpg" /> </label>';
		if ($("#choose").html() == 3) {
			cds+='<span class="edit-course" id="'+courses[c].id+'">修改课程</span>';
			if (courses[c].handle == 101) {
				cds += "<a href='/oe/courseware/uploadForm?courseId="+courses[c].id+"' class='link-upload-cw'>上传课程课件<a>";
			}
		}
		cds += '</div>';
	}
	$("#courses-content").prepend(cds);
	$(".edit-course").on("click",function(){
		$("#c-id").val($(this).attr("id"));
		$("#sb-s option").remove();
		$.getJSON("/oe/course/loadCourseInfo",{courseId:$(this).attr("id")},function(data){
			var course = data['course'];
			var categories = data['categories'];
			$("#sb-s").append("<option value='-1'>请选择所在分类</option>");
			for(var c in categories){
				var ch = "<option value='"+categories[c].id+"'>"+categories[c].name+"</option>";
				if(course.category.id==categories[c].id){
					ch="<option selected value='"+categories[c].id+"'>"+categories[c].name+"</option>";
				}
				$("#sb-s").append(ch);
			}
			
			$("#dia-modify-course-form input[name='title']").val(course.title);
			$("#dia-modify-course-form input[name='price']").val(course.price);
			$("#dia-modify-course-form input[name='discount']").val(course.discount);
			$("#dia-modify-course-form textarea[name='target']").val(course.target);
			$("#dia-modify-course-form textarea[name='suitable']").val(course.suitable);
			$("#dia-modify-course-form textarea[name='demand']").val(course.demand);
			$("#dia-modify-course-form img").attr("src","/oe/resource/photo/course/"+course.photo);
		});
		
		$("#dia-modify-course").dialog("open");
	});
	$(".paging-div a[id^='c-p-f-']").attr("id", "c-p-f-1");
	$(".paging-div a[id^='c-p-p-']").attr("id", "c-p-p-" + paging['pagePrev']);
	$(".paging-div a[id^='c-p-n-']").attr("id", "c-p-n-" + paging['pageNext']);
	$(".paging-div a[id^='c-p-l-']").attr("id", "c-p-l-" + paging['pageTotal']);
	$(".paging-div").append(
			"<span id='pageInfo'>当前 " + paging['pageNo'] + "/"
					+ paging['pageTotal'] + "</span>");

}
$(function() {
	loadCollectedCourses(1);
	$(".paging-div a[id^='c-p-']").on("click", function() {
		var pageNO = $(this).attr("id").substring(6);
		if ($("#choose").html() == "1") {
			loadCollectedCourses(pageNO);
		} else if ($("#choose").html() == "2") {
			loadPurchasedCourses(pageNO);
		} else if ($("#choose").html() == "3") {
			loadUploadedCourses(pageNO);
		}
	});
	$("#c1").css({
		"background-color" : "#EDECE8"
	});
	$("#c2,#c3").css({
		"background-color" : "#F7F7F7"
	});
	$("#c1").on("click", function() {
		$("#choose").html(1);
		$("#c1").css({
			"background-color" : "#EDECE8"
		});
		$("#c2,#c3").css({
			"background-color" : "#F7F7F7"
		});
		loadCollectedCourses(1);
	});
	$("#c2").on("click", function() {
		$("#choose").html(2);
		$("#c2").css({
			"background-color" : "#EDECE8"
		});
		$("#c1,#c3").css({
			"background-color" : "#F7F7F7"
		});
		loadPurchasedCourses(1);

	});
	$("#c3").on("click", function() {
		$("#choose").html(3);
		$("#c3").css({
			"background-color" : "#EDECE8"
		});
		$("#c1,#c2").css({
			"background-color" : "#F7F7F7"
		});
		loadUploadedCourses(1);

	});

	$("#dia-modi-user-info-form").validate({
		errorClass : "error-msg",
		rules : {
			name : "required",
			email : {
				required : true,
				email : true
			},
			introduction : "required",
		},
		messages : {
			name : "姓名不能为空",
			email : {
				required : "邮箱不能为空",
				email : "邮箱格式不正确"
			},
			introduction : "个人简介不能为空",
		},
		submitHandler : function() {
			modifyInfo();
		}
	});
	$("#dia-user-info").dialog({
		autoOpen : false,
		width : 500,
		height : 400,
		resizable : false,
		modal : true
	});
	$("#dia-modify-course").dialog({
		autoOpen : false,
		width : 630,
		height : 660,
		resizable : false,
		modal : true
	});
	
	$("#modi-user-info").on("click", function() {
		$("#dia-user-info").dialog("open");
	});
	
	
	$("#dia-modify-course-form").validate({
		submitHandler:function(){
 			modifyCourseInfo();
 		},
 		errorClass:"error-msg",
 		rules:{
 			title:{
 				required:true,
 				maxlength:20
 			},discount:{
 				required:true,
 				number:true,
 				range:[0,1]
 			},
 			categoryId:{
 				min:0
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
 			},discount:{
 				required:"必须输入0-1小数",
 				number:"必须输入0-1小数",
 				range:"必须输入0-1小数"
 			},
 			categoryId:{
 				min:"课程分类必选"
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

function modifyCourseInfo(){
	var formData = new FormData($("#dia-modify-course-form")[0]);
	 $.ajax({  
         url: '/oe/course/modifyCourseInfo' ,  
         type: 'POST',  
         data: formData,  
         async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (returndata) {  
        	var data = JSON.parse(returndata);
        	for(d in data){
        		if(d=='success'){
        			$("#mc-msg").css({
        				"color":"green"
        			});
        		}else{
        			$("#mc-msg").css({
        				"color":"red"
        			});
        		}
        		$("#mc-msg").html(data[d]);
        	}
         }, 
         error: function (data) {  
        	
         }  
    }); 
}

function modifyInfo() {
			$.getJSON(
					"/oe/user/modifyUserInfo",
					{
						"name" : $("#dia-user-info input[name='name']").val(),
						"sex" : $("#dia-user-info input[name='sex']:checked")
								.val(),
						"email" : $("#dia-user-info input[name='email']").val(),
						"introduction" : $(
								"#dia-user-info textarea[name='introduction']")
								.val()
					},
					function(data) {
						if (data == '1') {
							$("span:contains('欢迎登录')").html(
									$("#dia-user-info input[name='name']")
											.val()
											+ "，欢迎登录");
							$("#user-info td:contains('姓名')").next("td").html(
									$("#dia-user-info input[name='name']")
											.val());
							$("#user-info td:contains('性别')")
									.next("td")
									.html(
											$(
													"#dia-user-info input[name='sex']:checked")
													.val() == "true" ? "男"
													: "女");
							$("#user-info td:contains('邮箱')").next("td").html(
									$("#dia-user-info input[name='email']")
											.val());
							$("#user-info td:contains('个人简介')")
									.next("td")
									.html(
											$(
													"#dia-user-info textarea[name='introduction']")
													.val());
							$("#dia-user-info").dialog("close");
						}
					});
}