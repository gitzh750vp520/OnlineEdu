<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="header">

	<div id="search-form">
		<input type="text" id="search-input" value="${sessionScope.title }"/>
		<button id="search-btn">搜索</button>
	</div>
	<div id="right-link">
		<c:if test="${empty currUser }">
			<a href="/oe/user/loginForm">登录</a>
			<a href="/oe/user/registForm">注册</a>
		</c:if>
		<c:if test="${not empty currUser }">
			<span>${currUser.name }，欢迎登录</span>
			<a href="/oe/user/logout">注销</a>
			<a href="/oe/deal/history">消费记录</a>
			<a href="/oe/recharge/rechargeForm">充值</a>
			<a href="/oe/user/personalCenter">个人中心</a>

		</c:if>
	</div>
	<div id="top-logo">
		<a href="/oe/home/index">OnlineEdu</a>
	</div>
	<div id="search-bar">
		<ul>

		</ul>
	</div>
	<div id="header-bottom">
		<div id="header-nav">
			<ul>
				<li><a href="/oe/home/index">首页</a>
				</li>
				<li><a href="/oe/course/courseList">课程</a>
				</li>
				<c:if test="${not empty currUser}">
					<li><a href="/oe/note/noteList">个人笔记</a>
					</li>
				</c:if>
				<c:if test="${currUser.type eq 0 }">
					<li><a href="/oe/course/uploadForm">上传课程</a>
					</li>
				</c:if>
				<li><a href="#">帮助</a>
				</li>
			</ul>
		</div>
	</div>
</div>
