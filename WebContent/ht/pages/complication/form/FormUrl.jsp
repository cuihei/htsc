<jsp:include page="../../_Include.jsp"></jsp:include>
<input type="hidden" id="url" value="${url}">
<script type="text/javascript">
$(function(){
	var url = $("#url").val();
	if(url != null && url != ""){
		common.toPage("../"+url);
	}
})
</script>