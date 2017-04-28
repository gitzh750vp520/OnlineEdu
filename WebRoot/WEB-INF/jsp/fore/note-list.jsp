<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>科多在线教育</title>
<link rel="shortcut icon" href="/oe/resource/favicon.ico" />
<script type="text/javascript" src="/oe/js/jquery-1.7.min.js"></script>
<link rel="stylesheet" type="text/css" href="/oe/css/import-res.css" />
<link rel="stylesheet" type="text/css" href="/oe/css/home.css" />
<script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
<script type="text/javascript" src="/oe/js/notelist.js"></script>
<link rel="stylesheet" type="text/css" href="/oe/css/notelist.css" />
</head>
<body>
	<div id="main">
		<%@include file="inc/header.jsp"%>
		<div id="container">
			<div id="super-cate-list">
				<ul>
					<li><a href="javascript://" class="note-cate-sp">全部</a>
						<ul id="sb-list-all" class=" note-cate-sb" >
							<c:forEach items="${list}" var="b">
										<li><a href="javascript://" class="note-cate-sb-a" id="sub-${b.id }">${b.name }</a></li>
							</c:forEach>
						</ul>
					</li>
					<c:forEach items="${superList }" var="s">
						<li>
							<a href="javascript://" class="note-cate-sp">${s.name }</a>
							<ul id="sb-list-${s.id }"   class=" note-cate-sb">
								<c:forEach items="${list}" var="b">
									<c:if test="${b.superCategory.id eq s.id }">
										<li><a href="javascript://" class="note-cate-sb-a" id="sub-${b.id }">${b.name }</a></li>
									</c:if>
								</c:forEach>
							</ul>
						</li>	
					</c:forEach>
				</ul>
			</div>
			<div id="notes-list">
				<div id="notes-list-title"><h3>我的笔记</h3></div>
				
			</div>
		</div>

		<%@include file="inc/footer.jsp"%>
		</div>
</body>
</html>



