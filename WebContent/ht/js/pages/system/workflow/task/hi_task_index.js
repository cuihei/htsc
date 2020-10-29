$(function(){
	hi_task.init();
	$("#goback").show();
})

function plan(obj,isRuTask) {
	var tr = $(obj).parent().parent();
	var rowData = grid.getSelectRowDataByRow(tr);
	var taskDefId = rowData.taskDefId;
	var procInstId = rowData.processInstId;
	var taskInstId = rowData.taskInstId;
	var parentProcessInstId = rowData.parentProcessInstId;
	common.toPage("../formValue/plan?taskDefId="+taskDefId+"&taskInstId="+taskInstId+"&processInstId="+procInstId+"&parentProcessInstId="+parentProcessInstId+"&isRuTask="+isRuTask);
}

var hi_task = {
		/**
		 * 初始化
		 */
		init : function(){
			// 初始化表格对象
			grid.init("dv_ru_task");
			// 显示加载层
			loading.init();
			try{
				// 创建表格
				hi_task.createRuTaskGrid();
				// 发送数据请求绑定表格数据
				hi_task.requestRuTaskData();
				// 绑定返回按钮事件
				$("#goback").on("click",function(){
					history.back();
				})
			}
			catch(err){
				loading.close();
			}
		},
		
		/**
		 * 创建任务表格
		 */
		createRuTaskGrid : function(){
			var processApprove  = ["CATALOG_MANAGEMENT","TASK_BOOK","DATA_INPUT","DATA_BORROWING","DATA_RETURN"];
			var taskDef = "# var processSubmitApprove = ['u_task_plan_zhijian_shenhe','u_task_ztkz_queren','u_task_ztkz_shenhe'];" +
			"if($.inArray(taskDefId,processSubmitApprove)<0){#";
			var approveHTML= $('#operation').html();
			approveHTML =  taskDef + approveHTML;
			approveHTML = approveHTML + "#} else{#";
			approveHTML = approveHTML + $('#operationDisable').html()+"# }#";
			var planHTML= $('#plan').html();
			grid.resetColumn();
			// 显示业务列
			var columns = $("#columns").val();
			var colArray = JSON.parse(columns);
			for (var int = 0; int < colArray.length; int++) {
				var column = colArray[int];
				grid.addColumn(column.width,column.prop,column.value);
			}
			// 流程列
			grid.addColumn("100px","processDefName","任务类型");
			grid.addColumn("100px","taskName","任务名称");
			grid.addColumn("100px","createTime","发布时间","#= createTime ? kendo.toString(new Date(createTime), 'yyyy-MM-dd HH:mm:ss') : '' #" );
			grid.addColumn("100px","endTime","完成时间","#= endTime ? kendo.toString(new Date(endTime), 'yyyy-MM-dd HH:mm:ss') : '' #" );
			grid.addColumn("100px","processEndTime","结束时间","#= processEndTime ? kendo.toString(new Date(processEndTime), 'yyyy-MM-dd HH:mm:ss') : '' #" );
			grid.addColumn("100px","plan","编绘计划问题",kendo.template(planHTML),null,null,false);
			if($.inArray($("#processDefKey").val(),processApprove) < 0){
				grid.addColumn("100px","operation","表单操作",kendo.template(approveHTML),null,null,false);
			}
			var columns = grid.addColumn("40px","flow","流转",kendo.template($("#flow").html()));
			grid.createGrid(columns);
		},
		
		/**
		 * 发送获取任务列表请求
		 */
		requestRuTaskData : function(){
			common.init("../workflow/hi_task_list","POST",hi_task.bindRuTaskGrid);
			var processDefKey = $("#processDefKey").val();
			var processInstId = $("#processInstId").val();
			var parentProcessInstId = $("#parentProcessInstId").val();
			common.add_param("processInstId",processInstId);
			common.add_param("parentProcessInstId",parentProcessInstId);
			var data = common.add_param("processDefKey",processDefKey);
			common.do_submit(data);
		},
		
		/**
		 * 绑定任务数据
		 */
		bindRuTaskGrid : function(result){
			// 綁定数据
			grid.bindData(result);
//			$("button[name='operation']").each(function(){
//				$(this).hide();
//			});
			// 提示加载成功信息
			layer.msg(msg.LOAD_SUCCESS);
		},
		
		/** 绑定表单查看的click事件*/
		formValue : function(obj){
			var tr = $(obj).parent().parent();
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			var processDefId = rowData.processDefId;
			var procInstId = rowData.processInstId;
			var taskInstId = rowData.taskInstId;
			var taskDefId = rowData.taskDefId;
			var param = "processDefId="+processDefId+"&taskDefId="+taskDefId+"&taskInstId="+taskInstId+"&processInstId="+procInstId;
			common.toPage("../formValue/process_forms_init?"+param);
		//alert("2")	
		},
		
		/**
		 * 导出经历簿
		 */
		exportBook : function(obj){
			var tr = $(obj).parent().parent();
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			var parentProcessInstId = rowData.parentProcessInstId;
			var processDefKey = rowData.processDefId.split(":")[0];
			var param = "parentProcessInstId="+parentProcessInstId+"&processDefKey="+processDefKey;
			window.location.href = "../workflow/export_book?"+param;
		},
		
		/**
		 * 流转
		 */
		flow : function(obj){
			var tr = $(obj).parent().parent();
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			var procInstId = rowData.processInstId;
			var taskInstId = rowData.taskInstId;
			common.toPage("../workflow/flow_init?taskInstId="+taskInstId+"&processInstId="+procInstId);
		}
}