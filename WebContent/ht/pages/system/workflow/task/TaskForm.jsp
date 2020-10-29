<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="../../../TagLibs.jsp"%>

<jsp:include page="../../../Main.jsp"></jsp:include>
<input type="hidden" id="processInstId" value="${processInstId}">
<input type="hidden" id="processDefId" value="${processDefId}">
<input type="hidden" id="taskId" value="${taskId}">
<input type="hidden" id="taskDefId" value="${taskDefId}">
<input type="hidden" id="parentProcessInstId"	value="${parentProcessInstId}">
<input type="hidden"  id="planHisName" />
<script type="text/javascript"
	src="${SCRIPTS_PAGES_PATH}/system/workflow/task/taskform.js"></script>

<div class='row' id="jhfpr" 	style="display: none; margin-top: 10px; margin-left: 0px; width: 100%">


	<div class=' col-sm-3 col-md-3 col-xs-2' >
<p style=" font-size: 14px;">该任务的计划中,当前节点的分配人。</p>
		<div class='input-group'>
			<span class='input-group-addon'>计划分配人：</span> <select  id='usersfpr' class='form-control'>
		
			</select>
		</div>
	
	</div>
	<p style=" height: 50px;width: 100%"></p>
<hr  style=" border-bottom: 2px  #ccc dotted; ">
<p style=" height: 50px;width: 100%"></p>
</div>


