<%@ include file="../../../TagLibs.jsp"%>
<jsp:include page="../../../Main.jsp"></jsp:include>
<input type="hidden" id="task_id" name="task_id" value="${task_Id}"/>
<input type="hidden" id="process_inst_id" name="process_inst_id" value="${process_Inst_Id}"/>
<script type="text/javascript" src="${SCRIPTS_PAGES_PATH}/complication/seamap/paper/paperstatus.js"></script>
<script type="text/javascript">
	$(function(){
		paperstatus.init($("#process_inst_id").val(),$("#task_id").val());
	})
</script>