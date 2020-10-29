<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="_Include.jsp"></jsp:include>
</head>
<body>
	<input type="hidden" value="${userNo}" id="_userNo">
	<input type="hidden" value="${error}" id="_error">
	<input type="hidden" value="${write}" id="_write">
	${html}
</body>
</html>
<script type="text/javascript">
	$(function(){
		var userNo = $("#_userNo").val();
		if (userNo == "" || userNo == null) {
			parent.window.location.href = "../index/login";
		}
		var error = $("#_error").val();
		if (error != "" && error != null) {
			layer.confirm(error, {
					btn: ["返回"]
				}, 
				function(){
					history.back();
			});
		}
	/* 	var write = $("#_write").val();
		var arr,reg=new RegExp("(^| )"+"WRITE"+"=([^;]*)(;|$)");
		   if(arr=document.cookie.match(reg)){
			   write = unescape(arr[2]); 
		}
 		if(write == "0"){
			var btns=document.getElementsByTagName('button');
			for(var i=0;i<btns.length;i++){
				btns[i].style.display='none'
			}
		} 
 */
	})
</script>