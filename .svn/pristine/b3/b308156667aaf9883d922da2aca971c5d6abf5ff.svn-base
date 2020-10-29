<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="TagLibs.jsp"%>
<jsp:include page="_Include.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${STYLES_PATH}/admin.css" />
<div class="tabbable">
	<ul class="nav nav-tabs">
		<c:forEach items="${tabItems}" var='tabItem' varStatus="status">
			<li><a href="#tab_${status.index}" data-toggle="tab">${tabItem}</a></li>
		</c:forEach>
	</ul>
	<div class="tab-content">
		<c:forEach items="${tabContents}" var='tabContent' varStatus="status">
			<iframe class="tab-pane fade page" id="tab_${status.index}"
				srcParam="..${tabContent}"> </iframe>
		</c:forEach>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		var activeLi = $(".nav.nav-tabs").children("li")[0];
		$(activeLi).addClass("active");
		var activeIframe = $(".tab-content").children("iframe")[0];
		$(activeIframe).addClass("in");
		$(activeIframe).addClass("active");
		var fullHeight = parent.$("#page").height();
		var tabHeight = $(".nav.nav-tabs").outerHeight();
		$(".tabbable").css("height", fullHeight - tabHeight);
		
		var lis = $(".nav.nav-tabs").children("li");
		for(var i = 0 ; i < lis.length; i++){
			var li = lis[i];
			var isActive = $(li).hasClass("active");
			var as = $(li).children("a");
			for(var j = 0 ; j < as.length; j++){
				if(isActive == true){
					var aHref =  $(as[0]).attr("href");
					var frameId = aHref.substring(1);
					var frame = $("#"+frameId);
					var url = frame.attr("srcParam");
					url = url.replace("*","&");
					$("#"+frameId).attr("src",url);
				}
				$(as[j]).on("click",function(){
					var aHref =  $(this).attr("href");
					var frameId = aHref.substring(1);
					var frame = $("#"+frameId);
					var url = frame.attr("srcParam");
					url = url.replace("*","&");
					// 是否重新加载
// 					if($("#"+frameId).attr("src") == null){
// 						$("#"+frameId).attr("src",url);
// 					}
					$("#"+frameId).attr("src",url);
				})
			}
		}
	})
</script>