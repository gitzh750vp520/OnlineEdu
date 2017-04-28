$(function(){
	$("#modify-psw-form").validate({
		errorClass:"error-msg",
		rules: {
			'original-psw':{
				required:true,
				minlength:6,
				maxlength:20
			},'new-psw':{
				required:true,
				minlength:6,
				maxlength:20
			},
			'repeat-psw':{
				required: true,
				equalTo: "#new-psw"
			}
		},
		messages: {
			'original-psw':{
				required:"密码不能为空",
				minlength:"密码长度只能在6-20位之间",
				maxlength:"密码长度只能在6-20位之间"
			},
			'new-psw':{
				required:"密码不能为空",
				minlength:"密码长度只能在6-20位之间",
				maxlength:"密码长度只能在6-20位之间"
			},'repeat-psw':{
				required:"重复密码不能为空",
				equalTo:"重复密码不正确",
			}
		}
	});
});