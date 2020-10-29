$(function(){
	task.init();
})

/** 绑定表单窗口按钮的click事件*/
function editConfig(obj) {
	var tr = $(obj).parent().parent();
	task.toFormPage(tr);
}

function editStatus(obj) {
	var tr = $(obj).parent().parent();
	task.toStatusPage(tr);
}

function editAssociated(obj) {
	var tr = $(obj).parent().parent();
	task.toAssociatedPage(tr);
}


var task = {
		
		/**
		 * 初始化
		 */
		init : function(){
			// 初始化表格对象
			grid.init("dv_task");
			// 显示加载层
			loading.init();
			try{
				// 创建表格
				task.createTaskGrid();
				// 发送数据请求绑定表格数据
				task.requestTaskData();
			}
			catch(err){
				loading.close();
			}
		},
		
		/**
		 * 创建任务表格
		 */
		createTaskGrid : function(){
			grid.resetColumn();
			grid.addColumn("200px","processDefName","流程名称");
			grid.addColumn("200px","taskDefId","任务ID");
			grid.addColumn("200px","taskDefName","任务名称");
			var columns = grid.addColumn("500px","handle","操作",kendo.template($("#operation").html()));
			grid.createGrid(columns);
		},
		
		/**
		 * 发送获取任务列表请求
		 */
		requestTaskData : function(){
			common.init("../task/list","POST",task.bindTaskGrid);
			var processDefId = $("#processDefId").val();
			var data = common.add_param("processDefId",processDefId);
			common.do_submit(data);
		},
		
		/**
		 * 绑定任务数据
		 */
		bindTaskGrid : function(result){
			// 綁定数据
			grid.bindData(result);
			// 提示加载成功信息
			layer.msg(msg.LOAD_SUCCESS);
			// 绑定页面事件
			task.bindPageEvent();
		},
		
		bindPageEvent : function(){
			$("#back").on("click",function(){
				// 跳转到流程页面
				common.toPage("../workflow/init");
			});
		},
		
		/**
		 * 跳转到表单页面
		 */
		toFormPage : function(tr){
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			console.log(rowData);
			// 获取资源ID
			var processDefKey = rowData.processDefKey;
			var processDefId = rowData.processDefId;
			var taskDefId = rowData.taskDefId;
			var taskName = rowData.taskDefName;
			// 跳转到资源编辑页面
			common.toPage("../workflow/task/form?processDefId="+processDefId+"&taskDefId="+taskDefId+"&processDefKey="+processDefKey+"&taskName="+taskName);
		},
		
		toStatusPage : function(tr){
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			console.log(rowData);
			// 获取资源ID
			var processDefId = rowData.processDefId;
			var processDefKey = rowData.processDefKey;
			var taskDefId = rowData.taskDefId;
			// 跳转到资源编辑页面
			common.toPage("../workflow/task/status?processDefId="+processDefId+"&taskDefId="+taskDefId+"&processDefKey="+processDefKey);
		},
		
		toAssociatedPage : function(tr){
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			console.log(rowData);
			// 获取资源ID
			var processDefId = rowData.processDefId;
			var processDefKey = rowData.processDefKey;
			var taskDefId = rowData.taskDefId;
			// 跳转到资源编辑页面
			common.toPage("../workflow/task/associated?processDefId="+processDefId+"&taskDefId="+taskDefId+"&processDefKey="+processDefKey);
		}
}