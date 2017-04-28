<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>科多在线教育</title>
<link rel="stylesheet" type="text/css" href="/oe/css/import-res.css" />
<script type="text/javascript" src="/oe/js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="/oe/js/import-res.js"></script>
 <link rel="shortcut icon" href="/oe/resource/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="/oe/js/jquery-ui/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="/oe/css/home.css" />
<script type="text/javascript" src="/oe/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
<script type="text/javascript" src="/oe/js/personal-center.js"></script>
</head>
<body>
	<div id="main">
		<%@include file="inc/header.jsp"%>
		<div id="container">
			<div id="user-info">
				<img width="80" height="100"
					src="/oe/resource/photo/user/${currUser.photo }" />

				<div id="modi-user-photo">
					<h4>修改头像：</h4>
					<form action="/oe/user/modifyUserPhoto" method="post"
						enctype="multipart/form-data">
							<input type="file" name="newPhoto" id="file-input" />
							<input type="submit" value="保存" id="to-modify-photo" >
							<span class="error-msg">${modiPhotoError }</span>
						</form>
				</div>
				<table>
					<tr>
						<td>姓名：</td>
						<td>${currUser.name }</td>
					</tr>
					<tr>
						<td>性别：</td>
						<td>${currUser.sex?'男':'女' }</td>
					</tr>

					<tr>
						<td>邮箱：</td>
						<td>${currUser.email }</td>
					</tr>
					<tr>
						<td>个人简介：</td>
						<td>${currUser.introduction }</td>
					</tr>
					<tr>
						<td>金币：</td>
						<td>${currUser.gold }</td>
					</tr>
				</table>
				<a id="link-mod-psw" href="/oe/user/modifyPasswordForm"
					class="mine-link">修改密码</a>
				<button id="modi-user-info" class="mine-btn">修改个人信息</button>
			</div>
			<div id="choose-courses">
				<ul>
					<li id="c1">收藏的课程</li>
					<li id="c2">购买的课程</li>
					<c:if test="${currUser.type==0 }">
						<li id="c3">已上传课程</li>
					</c:if>
				</ul>
			</div>

			<div id="courses-content">
				<div class="paging-div">
					<a href="javascript://" id="c-p-f-">首页</a> <a href="javascript://"
						id="c-p-p-">上一页</a> <a href="javascript://" id="c-p-n-">下一页</a> <a
						href="javascript://" id="c-p-l-">尾页</a> <span id="choose"
						class="hide">1</span>
				</div>

			</div>
		</div>
			<div id="dia-user-info" title="修改个人信息">
				<form id="dia-modi-user-info-form"  >
					<table>
						<tr>
							<td>姓名：</td>
							<td><input type="text" name="name" class="mine-input"
								value="${currUser.name }" />
							</td>
						</tr>
						<tr>
							<td>性别：</td>
							<td><input type="radio"
								${currUser.sex?'checked':'' } name="sex" value="true" />男 <input
								type="radio" ${currUser.sex?'':'checked' } name="sex"
								value="false" />女</td>
						</tr>

						<tr>
							<td>邮箱：</td>
							<td><input type="text" name="email" class="mine-input"
								value="${currUser.email }" />
							</td>
						</tr>
						<tr>
							<td>个人简介：</td>
							<td><textarea name="introduction" class="textarea-mine">${currUser.introduction }</textarea>
							</td>
						</tr>

						<tr>
							<td></td>
							<td><input type="submit" value="保存修改" class="mine-btn" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div id="dia-modify-course" title="修改课程信息" >
				
				<form id="dia-modify-course-form" enctype="multipart/form-data">
				<input id="c-id" type="hidden" name="id" />
				<table >
					<tr>
						<td>课程名称</td>
						<td>课程分类</td>
					</tr>
					<tr>
						<td>
						<input type="text" name="title" class="mine-input"/></td>
						<td>
							<select id="sb-s" name="categoryId">
							</select>
						</td>
					</tr>
					<tr>
						<td>课程价格& &nbsp;课程折扣(0-1小数)</td>
						<td>课程目标</td>
					</tr>
					<tr>
						<td>
						<input type="text" name="price" class="mine-input-half"/>
						<input type="text" name="discount" class="mine-input-half"/>
						</td>
						<td><textarea  name="target" class="textarea-mine"></textarea></td>
						
					</tr>
					<tr>
						<td>适用人群</td>
						<td>原来课程展示所需图片</td>
					</tr>
					<tr>
						<td><textarea  name="suitable" class="textarea-mine"></textarea></td>
						<td><img src="" width="200" height="140"/></td>
						
					</tr>
					<tr>
						<td>课程要求</td>
						<td>课程展示所需图片:</td>
						
					</tr>
					<tr>
						<td><textarea  name="demand" class="textarea-mine"></textarea></td>
						<td><input type="file" name="photo"/></td>
					</tr>
					<tr>
						<td><span id="mc-msg"></span></td>
						<td><input type="submit" value="修改" class="mine-btn-2"/></td>
					</tr>
				
				</table>
				</form>
			</div>
		<%@include file="inc/footer.jsp"%>
	</div>
</body>
</html>



