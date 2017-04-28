$(function() {
	/*
	 * <li class="cate-bar"><a href="#" id="supe-cate-0"
	 * class="cate-bar-click">web前端</a><span class="hide">
	 * web前端,html/css,html5,css3</span> </li>
	 */
	$("#search-btn").on("click",function(){
		var temp = $("#search-input").val();
		location.href ="/oe/course/courseList?title="+temp;
	});
	var sbt = '';
	$.getJSON("/oe/category/loadAllCategories", function(data) {
		var i =0;
		for(var s in data){
			sbt+='<li class="cate-bar"><a href="/oe/course/courseList?superCategoryId='+data[s]['id']+'" id="supe-cate-'+(i++)+'"';
			sbt+='class="cate-bar-click">'+data[s]['name'];
			sbt+='</a><span class="hide">';
			var temp='';
			if(data[s]['subCategories'] != 'null'){
				for(var c in data[s]['subCategories']){
					temp+=data[s]['subCategories'][c]['name']+"#"+data[s]['subCategories'][c]['id']+',';
				}
			}
			sbt+=temp+'</span> </li>';
			
		}
		$("#search-bar ul").html(sbt);
		$(".cate-bar-click")
		.mouseover(
				function() {
					var top = parseInt($(this).attr("id").substring(10)) * 60 + 150;
					$("#sub-cate").remove();
					$("#header").append(
							"<div id='sub-cate' style='top:" + top
									+ "px;left:303px'></>");
					var subcates = $.trim($(this).next(".hide").html());
					var subarr = subcates.split(",");
					var ss = '';
					for ( var s in subarr) {
						var sbName = subarr[s].split("#")[0];
						var sbId = subarr[s].split("#")[1];
						
						if(sbName !='' && sbName != 'null'){
							ss += "<a class='sub-cate-ele' href='/oe/course/courseList?subCategoryId="+sbId+"'>"
								+ sbName + "</a>";
						}
					}
					$("#sub-cate").append(ss);
					$("#sub-cate").mouseleave(function() {
						$("#sub-cate").remove();
					});
				});
		
	});

	
	
});
