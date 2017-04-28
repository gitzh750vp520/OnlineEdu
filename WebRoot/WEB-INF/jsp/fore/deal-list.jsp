<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>科多在线教育</title>
<%@include file="inc/import-res.jsp"%>
<link rel="stylesheet" type="text/css" href="/oe/css/home.css" />
<script type="text/javascript">
	$(function() {
		loadDeals(1);

	});

	function loadDeals(pageNO) {
		$(".deal-item").remove();
		$("#deal-paging").remove();
		$
				.getJSON(
						"/oe/deal/showDeals",
						{
							pageNOStr : pageNO
						},
						function(data) {
							var deals = data['deals'];
							var paging = data['paging'];
							var dealDates = data['dealDates'];
							for(var d in deals){
								var item='<tr class="deal-item"><td>';
								if(deals[d].type){
									item+='消费';
								}else{
									item+='收入';
								}
								item+='</td><td >'+deals[d].gold+'</td><td >'+deals[d].content+'</td><td >'+dealDates[deals[d].id]+'</td></tr>';
								$("#deal-list")
								.append(item);
							}
							var pageDiv='<div id="deal-paging">';
								pageDiv+='<a href="javascript://" id="d-p-1">首页</a>';
								pageDiv+='<a href="javascript://" id="d-p-'+paging['pagePrev']+'">上一页</a>';
								pageDiv+='<a href="javascript://" id="d-p-'+paging['pageNext']+'">下一页</a>';
								pageDiv+='<a href="javascript://" id="d-p-'+paging['pageTotal']+'">尾页</a>';
								pageDiv+='<span>当前 '+paging['pageNo']+'/'+paging['pageTotal']+'</span>';
							pageDiv+='</div>';
							$("#deal-list").after(pageDiv);
							$("#deal-paging a").on("click",function(){
								var p = $(this).attr("id").substring(4);
								loadDeals(p);
							});
						});
	}
</script>
</head>
<body>
	<div id="main">
		<%@include file="inc/header.jsp"%>
		<div id="container">
			<div >
				<h3>充值记录</h3>
				<table id="deal-list" cellspacing="0">
					<tr>
						<td width="60">类型</td>
						<td width="120">金额</td>
						<td width="300">详情</td>
						<td width="200">时间</td>
					</tr>
				</table>
			</div>
		</div>

		<%@include file="inc/footer.jsp"%>
	</div>
</body>
</html>



