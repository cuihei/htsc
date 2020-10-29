<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@ include file="../../TagLibs.jsp"%>
<jsp:include page="../../Main.jsp"></jsp:include>
<script type="text/javascript" src="${SCRIPTS_PAGES_PATH}/datum/productupdsourcedata/productupdsourcedata_edit.js"></script>

<script>
	function mapNoChange() {
		var drawNum = $("#draw").prev().find('li').length;
		if(drawNum >= 1){
			var draw = $("#draw").prev().find('li:eq(0)').find('span:eq(0)').html();
			$.ajax({
				url:"../product/getPersonByMapNo",
				type:"post",
				data:{draw:draw},
				dataType:"json",
				success:function(data){
					$("#figure_caption").val(data.figure_caption);
					$("#compiler").val(data.compiler);
					$("#quality_inspector").val(data.quality_inspector);
					$("#authorized_member").val(data.authorized_member);
				}
			})
		}else {
			$("#figure_caption").val("");
			$("#compiler").val("");
			$("#quality_inspector").val("");
			$("#authorized_member").val("");
		}
		
	}
</script>