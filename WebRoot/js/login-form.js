$(function() {
	if($("#login-tip").html()){
		alert($("#login-tip").html());
		$("#login-tip").html("");
	}
	$("#login-title-teacher").on("click",function(){
		$(this).css({
			"background-color":"salmon"
				
		});
		$("#login-div .submit-btn").attr("value","老师登录");
		$("#login-div input[name='type']").attr("value","0");
		$("#login-title-student").css({
			"background-color":"sandybrown"
		});
		$("#login-div").css({
			"background-color":"#B98679"
		});
	});
	$("#login-title-student").on("click",function(){
		$(this).css({
			"background-color":"salmon"
		});
		$("#login-div .submit-btn").attr("value","学生登录");
		$("#login-div input[name='type']").attr("value","1");
		$("#login-title-teacher").css({
			"background-color":"sandybrown"
		});
		$("#login-div").css({
			"background-color":"#E5EBEA"
		});
	});
	
	$("#loginUser").validate({
		errorClass:"error-msg",
 		rules:{
 			loginId:{
 				required:true,
 				minlength:6,
 				maxlength:20
 			},loginPsw:{
 				required:true,
 				minlength:6,
 				maxlength:20
 			}
 		},
 		messages:{
 			loginId:{
 				required:"请先输入登录账号",
 				minlength:"账号必须在6-20位之间",
 				maxlength:"账号必须在6-20位之间"
 			},loginPsw:{
 				required:"请先输入登录密码",
 				minlength:"密码必须在6-20位之间",
 				maxlength:"密码必须在6-20位之间"
 			}
 			
 		}
		
		
	});
});
