$(function(){
	source.init();
})

var source = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("source");
		loading.init();
		try{
			source.createSourceGrid();
			source.requestSourceData();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建区域管理列表列集合
	 */
	createSourceColumns : function(){
		grid.resetColumn();
		grid.addColumn("150px","taskBill.taskBook.taskbookNo","任务书序号");
		grid.addColumn("160px","taskBill.taskBook.taskbookName","编绘任务书名称");
		grid.addColumn("120px","taskBill.plan.category.value","计划类别");
		grid.addColumn("120px","taskBill.plan.mapNo","图号");
		grid.addColumn("120px","taskBill.plan.mapName","图名");
		grid.addColumn("120px","taskBill.plan.scale","比例尺 1:");
		grid.addColumn("120px","taskBill.plan.type","类别");
		grid.addColumn("120px","taskBill.plan.revision","版次");
		grid.addColumn("120px","taskName","当前任务");
		//grid.addColumn("120px","createTime","发布时间","#= createTime ? kendo.toString(new Date(createTime), 'yyyy-MM-dd HH:mm:ss') : '' #" );
		grid.addColumn("120px","flow","流转",kendo.template($("#flow").html()));
		grid.addColumn("120px","operation","操作",kendo.template($("#operation").html()));
		return grid.addColumn("120px","approve","审批",kendo.template($("#approve").html()));
	},
	
	/**
	 * 创建区域管理列表
	 */
	createSourceGrid : function(){
		var columns = source.createSourceColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestSourceData : function(){
		common.init("../source/sourcelist?flag=source","POST",source.bindSourceGrid);
		common.do_submit();
	},
	
	/**
	 * 跳转到状态页面
	 */
	toStatusPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取流程实例id
		var process_Inst_Id = '';   //留空需要修改
		// 获取任务ID
		var task_Id='';   //修空需要传值
		// 跳转到区域管理编辑页面
		common.toPage("../source/initstatus?process_Inst_Id="+process_Inst_Id+"&task_Id"+task_Id);
	},

	/**
	 * 跳转到详情页面
	 */
	toDetailsPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取区域管理ID
		var id = rowData.id;
		// 跳转到区域管理编辑页面
		common.toPage("../source/initdetails?id="+id);
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindSourceGrid : function(result){
		grid.bindData(result);
		/** 绑定表单按钮的click事件*/
		var operations = $("button[name='operation']");
		$.each(operations,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				// 获取选中行数据对象
				var rowData = grid.getSelectRowDataByRow(tr);
				var processDefId = rowData.processDefId;
				var procInstId = rowData.processInstId;
				var taskInstId = rowData.taskInstId;
				var taskDefId = rowData.taskDefId;
				common.toPage("../taskform/content?taskDefId="+taskDefId+"&processDefId="+processDefId+"&taskInstId="+taskInstId+"&processInstId="+procInstId);
			});
		});
		/** 绑定审批按钮的click事件*/
		var approveBtns = $("button[name='approve']");
		$.each(approveBtns,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				var rowData = grid.getSelectRowDataByRow(tr);
				var taskId = rowData.taskInstId;
				var processInstId = rowData.processInstId;
				var taskDefId = rowData.taskDefId;
				var processDefId = rowData.processDefId;
				/** 绑定意见提交按钮的click事件*/
				$("#submitOpinion").on("click",function(){
					ru_task.performTask(taskDefId,processDefId,taskId,processInstId,"1");
				});
				
				/** 绑定意见退回按钮的click事件*/
				$("#backOpinion").on("click",function(){
					ru_task.performTask(taskDefId,processDefId,taskId,processInstId,"0");
				});
				$("#opinion").val("");
				$("#hiddenButton").click();
			});
		})
		/** 绑定表单按钮的click事件*/
		var flows = $("button[name='flow']");
		$.each(flows,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				// 获取选中行数据对象
				var rowData = grid.getSelectRowDataByRow(tr);
				var procInstId = rowData.processInstId;
				var taskInstId = rowData.taskInstId;
				common.toPage("../workflow/flow_init?taskInstId="+taskInstId+"&processInstId="+procInstId);
			});
		});
		//grid.setEvents();
	},
	
}
