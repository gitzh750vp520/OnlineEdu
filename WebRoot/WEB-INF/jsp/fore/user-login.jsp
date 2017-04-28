<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>科多在线教育</title>
	<%@include file="inc/import-res.jsp" %>
	<link rel="stylesheet" type="text/css" href="/oe/css/login.css" />
	<script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
	<script type="text/javascript" src="/oe/js/login-form.js"></script>
</head>
<body>
<div id="main">
	<%@include file="inc/header.jsp" %>
	<div id="container">
		<div id="login-div">
		
		<div id="ld-head">	<span id="login-title-student">学生登录</span>
			<span id="login-title-teacher">老师登录</span> </div>
			<f:form action="/oe/user/login" modelAttribute="loginUser" method="post">
				<input type="hidden" name="type" value="1"/>
				<table >
					<tr>
						<td>登录账号:</td>
						<td><f:input path="loginId" cssClass="nor-input"/></td>
						<td><f:errors path="loginId"/></td>
					</tr>
					<tr>
						<td>登录密码:</td>
						<td><f:password path="loginPsw" cssClass="nor-input"/></td>
						<td><f:errors path="loginPsw"/></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="学生登录" class="submit-btn" /></td>
						<td><f:errors path="*"/>${errorMsg }</td>
					</tr>
							
				</table>
				<span class="hide" id="login-tip">${loginTip }</span>				
			</f:form>
			
		</div>
	</div>
	
	<%@include file="inc/footer.jsp" %>
</div>
</body>
</html>


