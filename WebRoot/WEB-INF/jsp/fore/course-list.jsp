<%@page import="java.util.Random"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>科多在线教育</title>
<link rel="shortcut icon" href="/oe/resource/favicon.ico" />
<link href="/oe/css/bootstrap.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/oe/js/jquery-1.7.min.js"></script>
<link rel="stylesheet" type="text/css" href="/oe/css/import-res.css" />
<link rel="stylesheet" type="text/css" href="/oe/css/home.css" />
<script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
<script type="text/javascript" src="/oe/js/remove-cate.js"></script>
<link rel="stylesheet" type="text/css" href="/oe/css/notelist.css" />
<style>
	img { margin-top: 30px; }
</style>
<script type="text/javascript">
	
	$(function(){
		if($("#session-superCate-id").html()==""){
			$("#sp-id-all").css({
				"background-color":"#E66062"
			});		
		}
		if($("#session-subCate-id").html()==""){
			$("#sb-id-all").	css({
				"background-color":"#E66062"
			});		
		}
		
		$("a[id^='sp-id-']").each(function(){
			if($(this).attr("id").substring(6)==$("#session-superCate-id").html()){
				$(this).css({
					"background-color":"#E66062"
				});
			}
		});
		$("a[id^='sb-id-']").each(function(){
			if($(this).attr("id").substring(6)==$("#session-subCate-id").html()){
				$(this).css({
					"background-color":"#E66062"
				});
			}
		});
		
		
	});
	

</script>
</head>
<body>
	<div id="main">
		<%@include file="inc/header.jsp"%>
		<div class="container">
			<span id="session-superCate-id" class="hide">${sessionScope.superCategoryId }</span>
			<span id="session-subCate-id" class="hide">${sessionScope.subCategoryId }</span>
			
			<div class="row clearfix">
				<div class="col-md-9 column">
					<div class="row clearfix" style="margin-top: 20px">
						<div class="col-md-12 column">
							<div class="row clearfix">
								<div class="col-md-12 column" style="margin-bottom: 20px">
									<button type="button" class="btn btn-warning btn-lg disabled">分
										类:</button>
									&nbsp;
									<a href="/oe/course/courseList?superCategoryId=0"
											class="btn btn-default active" id="sp-id-all">全部</a>
									<c:forEach items="${ superCategories }" var="s">
										<a href="/oe/course/courseList?superCategoryId=${ s.id }"
											class="btn btn-default active" id="sp-id-${s.id }">${ s.name }</a>
									</c:forEach>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-12 column">
									<button type="button" class="btn btn-warning btn-lg disabled">内
										容:</button>
									&nbsp;
									<a href="/oe/course/courseList?subCategoryId=0"
											class="btn btn-default active" style="margin-top: 5px" id="sb-id-all">全部</a>
									<c:forEach items="${subCategories }" var="s">
										<a href="/oe/course/courseList?subCategoryId=${ s.id }"
											class="btn btn-default active" style="margin-top: 5px" id="sb-id-${s.id }">${ s.name }</a>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
					<div class="row clearfix" style="margin-top: 40px">
						<div class="col-md-12 column">
							<nav class="navbar navbar-default" role="navigation">
								<div class="collapse navbar-collapse"
									id="bs-example-navbar-collapse-1">
									<ul class="nav navbar-nav">
										<li><a rel="nofollow" href="/oe/course/courseList?order=3">价格</a></li>
										<li><a rel="nofollow" href="/oe/course/courseList?order=2">评分</a></li>
										<li><a rel="nofollow" href="/oe/course/courseList?order=5">收藏</a></li>
										<li><a rel="nofollow" href="/oe/course/courseList?order=4">时间</a></li>
									</ul>
								</div>
							</nav>
						</div>
					</div>
					<div class="row clearfix" style="margin-top: 0px">
						<div class="col-md-12 column">
							<div class="row clearfix" id="courses">
							<c:forEach items="${courses }" var="c">
								<div class='col-md-4 column'>
									<a href='/oe/course/details?id=${c.id }'>
									<img style='height: 150px' src='/oe/resource/photo/course/${c.photo }' class='img-responsive' /></a>
									<h4 style='height: 40px'>${c.title}</h4>
									<p><label style='font-size: 10px;height: 20px'>收藏人数</label>${c.collect }人</p>
									<p><label style='font-size: 10px;height: 20px'>综合评分</label>${c.point }</p>
									<a type='button' class='btn btn-success' disabled>价格 ${c.price }</a>
								</div>
							</c:forEach>
							</div>
							<!-- 分页标签 -->
							<div class="row clearfix">
								<div class="col-md-12 column text-center"
									style="margin-top: 50px">
									<ul class="pagination">
										<li><a id="a-paging-f" href="/oe/course/courseList?pageNO=1" >首页</a></li>
										<li><a id="a-paging-p" href="/oe/course/courseList?pageNO=${paging.pagePrev }" >上一页</a></li>
										<li><a id="a-paging-n" href="/oe/course/courseList?pageNO=${paging.pageNext }" >下一页</a></li>
										<li><a id="a-paging-t" href="/oe/course/courseList?pageNO=${paging.pageTotal }" >尾页</a></li>
									</ul>
								</div>
							</div>
							<!-- 统计页数 -->
							<div class="row clearfix">
								<div class="col-md-12 column text-center">
									<span id="span-pageNO">${paging.pageNo }</span>/
									<span id="span-pageTotal">${paging.pageTotal }</span>页&nbsp;&nbsp;共
									<span id="span-countTotal">${paging.countTotal }</span>条
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 column" style="padding-left: 20px">
					<div class="row clearfix">
						<div class="col-md-12 column">
							<img alt="140x140" src="/oe/resource/photo/course/img8.jpg"
								class="img-responsive" />
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column">
							<img alt="140x140" src="/oe/resource/photo/course/img8.jpg"
								class="img-responsive" />
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column">
							<img alt="140x140" src="/oe/resource/photo/course/img8.jpg"
								class="img-responsive" />
						</div>
					</div>
				</div>
			</div>
		</div>

		<%@include file="inc/footer.jsp"%>
	</div>

	<script src="/oe/js/jquery-3.1.1.js"></script>
	<script src="/oe/js/bootstrap.js"></script>
</body>
</html>



