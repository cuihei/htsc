<%@ include file="../../TagLibs.jsp"%>
<html>
<head><jsp:include page="../../_Include.jsp"></jsp:include></head>
<body style="overflow: hidden">
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
					btn: ["BACK"]
				}, 
				function(){
					history.back();
			});
		}
	})
</script>
<script type="text/javascript" src="${SCRIPTS_PAGES_PATH}/system/defect/defect_form_source.js"></script>
