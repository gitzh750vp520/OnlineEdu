<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>科多在线教育</title>
<%@include file="inc/import-res.jsp"%>
<link rel="stylesheet" type="text/css" href="/oe/css/home.css" />
<script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
<script type="text/javascript" src="/oe/js/modiPsw.js"></script>
</head>
<body>
	<div id="main">
		<%@include file="inc/header.jsp"%>
		<div id="container">
			<div id="modi-psw-div">
			<h3 id="modi-psw-title">修改密码</h3>
			<form action="/oe/user/modifyPassword" method="post" id="modify-psw-form">
					<table>
						<tr>
							<td><span class='input-title'>原密码：</span></td>
							<td><input type="password" name="original-psw" class='mine-input'/></td>
						</tr>
						<tr>
							<td><span class='input-title'>新密码：</span></td>
							<td><input type="password" id='new-psw'  name="new-psw"  class='mine-input'/></td>
						</tr>
						<tr>
							<td><span class='input-title'>重复密码：</span></td>
							<td><input type="password"  name="repeat-psw" class='mine-input'/></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="修改密码" class="mine-btn"/></td>
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



