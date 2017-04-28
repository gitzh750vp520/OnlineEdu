<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>科多在线教育</title>
<%@include file="inc/import-res.jsp"%>
<link rel="stylesheet" type="text/css" href="/oe/css/home.css" />
<script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
<script type="text/javascript" >
	$(function(){
		$("#recharge-form").validate({
			errorClass : "error-msg",
			rules : {
				money:{
					required:true,
					digits:true
				}
			},
			messages : {
				money:{
					required:"金额必填",
					digits:"金额必须为数字"
				}
			}
		});
		
	});

</script>
</head>
<body>
	<div id="main">
		<%@include file="inc/header.jsp"%>
		<div id="container">
			<div id="recharge-div">
			<h3 id="recharge-title">充值</h3>
			<form action="/oe/recharge/comfirm" method="post" id="recharge-form">
					<table>
						<tr>
							<td><span class='input-title'>请输入充值金额：</span></td>
							<td><input type="text" name="money" class='mine-input'/></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="确认充值" class="mine-btn"/></td>
						</tr>
						<tr>
							<td></td>
							<td><span class="error-msg">${errorMsg }</span></td>
						</tr>
					</table>
			</form>
			</div>
		</div>

		<%@include file="inc/footer.jsp"%>
	</div>
</body>
</html>



