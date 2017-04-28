<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>科多在线教育</title>
<%@include file="inc/import-res.jsp"%>
<link rel="stylesheet" type="text/css" href="/oe/css/home.css" />
<script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
<script type="text/javascript" src="/oe/js/regist.js"></script>
</head>
<body>
	<div id="main">
		<%@include file="inc/header.jsp"%>
		<div id="container">
			<div id="regist-div">
				<button id="regist-stu" class="mine-btn">注册学生</button>
				<button id="regist-teac" class="mine-btn">注册老师</button>
				<form id="regist-form" action="/oe/user/regist" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="type" value="1" />
					<table>
						<tr>
							<td>账号:</td>
							<td><input type="text" class="mine-input" name="loginId" />
							</td>
						</tr>
						<tr>
							<td>密码:</td>
							<td><input type="password"  class="mine-input"  id="loginPsw" name="loginPsw" />
							</td>
						</tr>
						<tr>
							<td>重复密码:</td>
							<td><input type="password"  class="mine-input" name="reloginPsw" />
							</td>
						</tr>
						<tr>
							<td>姓名:</td>
							<td><input type="text" class="mine-input" name="name" />
							</td>
						</tr>
						<tr>
							<td>性别:</td>
							<td><input type="radio" checked name="sex" value="true" />男
								<input type="radio" name="sex" value="false" />女</td>
						</tr>
						<tr>
							<td>邮箱:</td>
							<td><input type="text"  class="mine-input" name="email" />
							</td>
						</tr>
						<tr>
							<td>个人简介:</td>
							<td><textarea class="textarea-mine" name="introduction"></textarea>
							</td>
						</tr>
						<tr>
							<td>头像：</td>
							<td><input type="file" name="userPhoto" />
							</td>
						</tr>
						<tr>
							<td></td> 
							<td><input type="submit" value="注册学生" class="mine-btn" />
							</td>
						</tr>
						<tr>
							<td></td>
							<td>${errorMsg }</td>
						</tr>
					</table>
				</form>
			</div>


		</div>

		<%@include file="inc/footer.jsp"%>
	</div>
</body>
</html>


