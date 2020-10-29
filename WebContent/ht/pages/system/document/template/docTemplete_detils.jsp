<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@ include file="../../../TagLibs.jsp"%>
<jsp:include page="../../../Main.jsp"></jsp:include>
<script type="text/javascript">
$("#back").on("click",function(){
	window.history.back();
});
$("#downLoad").on("click",function(){
	var downLoad="${downLoad}";
	if(downLoad=="true"){
		window.location.href="..\\upload\\docTempleteDetils\\"+${downLoad_formId}+".doc";
	}else{
		alert("请先上传模板");
	}
});
</script>
<iframe id="doctemplete_show" src="${doctempleteUrl}" width="100%" height="80%" frameborder="0"></iframe>
