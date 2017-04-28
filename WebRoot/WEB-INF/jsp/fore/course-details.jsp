<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>科多在线教育</title>
<link rel="stylesheet" type="text/css" href="/oe/css/import-res.css" />
<link rel="stylesheet" type="text/css" href="/oe/css/home.css" />
<link rel="stylesheet" type="text/css" href="/oe/css/notelist.css" />
<link href="/oe/css/bootstrap.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/oe/js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="/oe/js/jquery.validate.js"></script>
<script type="text/javascript" src="/oe/js/remove-cate.js"></script>
<style>
.container li {
	margin-top: 20px;
}
</style>
<script type="text/javascript">
	$(function(){
		loadCatelog(1);
		$("a[id^='a-paging-']").on("click",function(){
 			loadCatelog($(this).attr("val"));
 		}); 
	});
	function loadCatelog(pageNO){
		var trs="";
 		$.getJSON("/oe/course/loadCatalog?r="+Math.random(),{"pageNO":pageNO},function(jsonData){
			var catalogs = jsonData["catalogs"];
			var paging = jsonData["paging"];
			for(var i in catalogs){
 				var c = catalogs[i];
 				trs += "<li><a href='/oe/catalog/watch?catalogId=" + c.id + "'";
				trs += "class='btn btn-success'>点击播放</a>&nbsp;&nbsp;&nbsp;&nbsp;" + c.title + "</li>";
 			} 
			$("#catalog").html(trs);
			$("#a-paging-f").attr("val","1");
 			$("#a-paging-p").attr("val",paging.pagePrev);
 			$("#a-paging-n").attr("val",paging.pageNext);
 			$("#a-paging-t").attr("val",paging.pageTotal);
 			$("#span-pageNO").html(paging.pageNo);
 			$("#span-pageTotal").html(paging.pageTotal);
 			$("#span-countTotal").html(paging.countTotal);
 		});
	}
</script>
</head>
<body>
	<div id="main">
		<%@include file="inc/header.jsp"%>
		<div class="container">
			<div class="row clearfix" style="margin-top: 20px">
				<div class="col-md-12 column">
					<div class="row clearfix"
						style="margin-bottom: 30px;margin-top: 30px">
						<div class="col-md-12 column">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="media">
										<a rel="nofollow" href="/oe/catalog/watch?catalogId=1"
											class="pull-left"> <img
											src="/oe/resource/photo/course/${ course.photo }"
											class="media-object img-responsive" alt='' /> </a>
									</div>
								</div>
								<div class="col-md-6 column">
									<div class="row">
										<div class="col-md-12 column">
											<h3>${ course.title }</h3>
										</div>
									</div>
									<div class="row" style="margin-top: 20px">
										<div class="col-md-12 column">
											<span class="label label-primary" style="font-size: 14px">好评度
												${ course.point }</span>&nbsp;&nbsp; <span
												class="label label-success" style="font-size: 14px">收藏人数
												${ course.collect }</span>
										</div>
									</div>
									<div class="row" style="margin-top: 60px">
										<div class="col-md-12 column">
											<span class="label label-danger" style="font-size: 20px">价格:${
												course.price }</span>&nbsp;&nbsp; <span class="label label-default"
												style="font-size: 14px">${ course.discount }折!</span>
										</div>
									</div>
									<div class="row" style="margin-top: 60px">
										<div class="col-md-6 column">

											<c:if test="${empty currUser }">
												<a href="/oe/user/loginForm" type="button"
													class="btn btn-success">登录报名</a>
											&nbsp;&nbsp;
										<a href="/oe/user/loginForm" type="button"
													class="btn btn-warning">登录收藏</a>
											</c:if>
											<c:if test="${not empty currUser }">
												<a href="/oe/course/buy?id=${ course.id }" type="button"
													class="btn btn-success">立即报名</a>
											&nbsp;&nbsp;
										<a href="/oe/course/collect?id=${ course.id }" type="button"
													class="btn btn-warning">收藏</a>
											</c:if>
											&nbsp;&nbsp;&nbsp;${ collMessage }${ buyMessage }
										</div>
										<div class="col-md-6 column">
											<div class="btn-group">
												<button class="btn btn-default">课程评价</button>
												<button data-toggle="dropdown"
													class="btn btn-default dropdown-toggle">
													<span class="caret"></span>
												</button>

												<ul class="dropdown-menu">

													<c:if test="${empty currUser }">
														<li><a rel="nofollow" href="/oe/user/loginForm">登录即可评分</a>
														</li>
													</c:if>
													<c:if test="${ not empty currUser }">
														<li><a rel="nofollow"
															href="/oe/evaluate/addEvaluate?id=${ course.id }&point=1">1 星</a></li>
														<li><a rel="nofollow"
															href="/oe/evaluate/addEvaluate?id=${ course.id }&point=2">2 星</a></li>
														<li><a rel="nofollow"
															href="/oe/evaluate/addEvaluate?id=${ course.id }&point=3">3 星</a></li>
														<li><a rel="nofollow"
															href="/oe/evaluate/addEvaluate?id=${ course.id }&point=4">4 星</a></li>
														<li><a rel="nofollow"
															href="/oe/evaluate/addEvaluate?id=${ course.id }&point=5">5 星</a></li>
													</c:if>
												</ul>
												&nbsp;&nbsp;&nbsp;${ evaMsg }
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-9 column" style="padding-right: 50px">
							<div class="row clearfix">
								<div class="col-md-12 column">
									<div class="tabbable" id="tabs-846784">
										<ul class="nav nav-tabs">
											<li class="active"><a rel="nofollow" href="#panel-362364" data-toggle="tab">课程概述</a></li>
											<li><a rel="nofollow" href="#panel-994255" data-toggle="tab">课程目录</a></li>
											<li><a rel="nofollow" href="#panel-994277" data-toggle="tab">课件下载</a></li>
										</ul>
										<div class="tab-content">
											<div class="tab-pane active" id="panel-362364">
												<!-- 课程概述 -->
												<!-- 以下为数据库读取信息 -->
												<h2 style="margin-top: 20px">${ course.target }</h2>
												<br />
												<h3 style="margin-top: 20px">${ course.suitable }</h3>
												<br />
												<h4 style="margin-top: 20px">${ course.demand }</h4>
											</div>
											<div class="tab-pane" id="panel-994255">
												<div class="col-md-12 column">
													<ol id="catalog">
														<!-- AJAX生成 -->
													</ol>
												</div>
												<!-- 分页标签 -->
												<div class="row clearfix">
													<div class="col-md-12 column text-center"
														style="margin-top: 50px">
														<ul class="pagination">
															<li><a id="a-paging-f" href="javascript://" val="">首页</a></li>
															<li><a id="a-paging-p" href="javascript://" val="">上一页</a></li>
															<li><a id="a-paging-n" href="javascript://" val="">下一页</a></li>
															<li><a id="a-paging-t" href="javascript://" val="">尾页</a></li>
														</ul>
													</div>
												</div>
											</div>
											<div class="tab-pane" id="panel-994277">
												<c:forEach items="${ coursewares }" var="c">
													<p><a href="/oe/courseware/download?coursewareId=${ c.id }">课件名称  ----> ${c.name}</a></p>
												</c:forEach>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-3 column">
							<div class="row clearfix">
								<div class="col-md-12 column">
									<div class="panel panel-default" style="margin-top: 60px">
										<!-- 类似广告页面,看情况实现 -->
										<div class="panel-heading">
											<h3 class="panel-title">热 门 课 程</h3>
										</div>
										<div class="panel-body">WEB前端</div>
										<div class="panel-body">Linux</div>
										<div class="panel-body">大数据</div>
										<div class="panel-footer">科多大数据</div>
									</div>
								</div>
							</div>
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



