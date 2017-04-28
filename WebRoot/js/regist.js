$(function(){
	$("#regist-stu").css({
		"background-color":"#F05656"
	});
	$("#regist-stu").on("click",function(){
		$("#regist-stu").css({
			"background-color":"#F05656"
		});
		$("#regist-teac").css({
			"background-color":"#F8B5B4"
		});
		$("#regist-form input[name='type']").val(1);
		$("#regist-div").css({
			"background-color":"#D7D8D4"
		});
		
		$("#regist-form input[type='submit']").val("注册学生");
	});
	$("#regist-teac").on("click",function(){
		$("#regist-stu").css({
			"background-color":"#F8B5B4"
		});
		$("#regist-teac").css({
			"background-color":"#F05656"
		});
		$("#regist-form input[name='type']").val(0);
		$("#regist-div").css({
			"background-color":"#85A5AD"
		});
		$("#regist-form input[type='submit']").val("注册老师");
	});
	
	$("#regist-form").validate({
		errorClass:"error-msg",
		rules: {
			loginId:{
				required:true,
				minlength:6,
				maxlength:20
			},loginPsw:{
				required:true,
				minlength:6,
				maxlength:20
			},
			reloginPsw:{
				required: true,
				equalTo: "#loginPsw"
			},
			name: "required",
			email: {
				required: true,
				email: true
			},
			introduction:"required",
		},
		messages: {
			loginId:{
				required:"账号不能为空",
				minlength:"账号长度只能在6-20位之间",
				maxlength:"账号长度只能在6-20位之间"
			},loginPsw:{
				required:"密码不能为空",
				minlength:"密码长度只能在6-20位之间",
				maxlength:"密码长度只能在6-20位之间"
			},
			reloginPsw:{
				required: "重复密码不能为空",
				equalTo: "重复密码不一致"
			},
			name: "姓名不能为空",
			email: {
				required: "邮箱不能为空",
				email: "邮箱格式不正确"
			},
			introduction:"个人简介不能为空",
		}
		
		
	});
});