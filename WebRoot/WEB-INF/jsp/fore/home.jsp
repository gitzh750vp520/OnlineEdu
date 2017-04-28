<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
<title>科多在线教育</title>
<%@include file="inc/import-res.jsp" %>
<link rel="stylesheet" type="text/css" href="/oe/css/home.css" />
<link rel="stylesheet" type="text/css" href="/oe/css/datouwang.css" />
<script type="text/javascript" src="/oe/js/koala.min.1.5.js"></script>
<script type="text/javascript" src="/oe/js/home.js"></script>
</head>
<body>
	<div id="main">
		<%@include file="inc/header.jsp"%>
		<div id="container">
			<div id="imgs-carousel">
				<div id="fsD1" class="focus" style="margin-left:40px">
					<div id="D1pic1" class="fPic">
						<c:forEach items="${coursesRecommended }" var="cr">
						
						<div class="fcon" style="display: none;">
							<a href="/oe/course/details?id=${cr.id }"><img
								src="/oe/resource/photo/course/${cr.photo }" style="opacity: 1; ">
							</a> <span class="shadow"><a href="/oe/course/details?id=${cr.id }">${cr.title }</a>
							</span>
						</div>
						</c:forEach>
						</div>
					<div class="fbg">
						<div class="D1fBt" id="D1fBt">
							<c:forEach begin="1" end="${fn:length(coursesRecommended) }">
							<a href="javascript:void(0)" hidefocus="true" target="_self"
								class=""><i>1</i>
							</a> 
							</c:forEach>
							</div>
					</div>
					<span class="prev"></span> <span class="next"></span>
				</div>



			</div>
			<h3 class="courses-title">热门课程</h3>
			<div id="most-pop">
				<c:forEach items="${mostPopCourses }" var="m">
					<div class="course-div">
						<label> <img width="230px" height="150px"
							src="/oe/resource/photo/course/${m.photo }" /> <a class="course-link" href="/oe/course/details?id=${m.id }"></a>
							<span class="course-title">${m.title }</span> <span
							class="need-gold-txt">所需金币：</span><span class="need-gold-num">${m.price*m.discount
								}</span> <span class="course-teacher">${m.user.name }</span> <img
							class="gold-icon" width="30" height="30"
							src="/oe/resource/goldIcon.jpg" /> </label>
					</div>
				</c:forEach>
				<a href="/oe/course/courseList" class="link-more">更多...</a>
			</div>
			<h3 class="courses-title">精品课程</h3>
			<div id="high-point">
				<c:forEach items="${bestPointCourses }" var="b">
					<div class="course-div">
						<label> <img width="230px" height="150px"
							src="/oe/resource/photo/course/${b.photo }" /> <a class="course-link" href="/oe/course/details?id=${b.id }"></a>
							<span class="course-title">${b.title }</span> <span
							class="need-gold-txt">所需金币：</span><span class="need-gold-num">${b.price*b.discount
								}</span> <span class="course-teacher">${b.user.name}</span> <img
							class="gold-icon" width="30" height="30"
							src="/oe/resource/goldIcon.jpg" /> </label>
					</div>
				</c:forEach>
				<a href="/oe/course/courseList" class="link-more">更多...</a>
			</div>
		</div>
		<div class="clear-float"></div>
		<%@include file="inc/footer.jsp"%>
	</div>
</body>
</html>

