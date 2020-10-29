$(function(){
	delegate_task.init();
})
var delegate_task = {
		/**
		 * 初始化
		 */
		init : function(){
			// 初始化表格对象
			grid.init("dv_delegate_task");
			// 显示加载层
			loading.init();
			try{
				// 创建表格
				delegate_task.createTaskGrid();
				// 发送数据请求绑定表格数据
				delegate_task.requestTaskData();
			}
			catch(err){
				loading.close();
			}
		},
		
		/**
		 * 创建任务表格
		 */
		createTaskGrid : function(){
			// 流程列
			grid.addColumn("100px","mapName","图名");
			grid.addColumn("100px","mapNo","图号");
			grid.addColumn("100px","scale","比例尺");
			grid.addColumn("100px","processDefName","任务类型");
			grid.addColumn("100px","taskName","任务名称");
			grid.addColumn("100px","performer","执行人");
			grid.addColumn("100px","createTime","发布时间","#= createTime ? kendo.toString(new Date(createTime), 'yyyy-MM-dd HH:mm:ss') : '' #" );
			var columns = grid.addColumn("100px","delegate","指派",kendo.template($("#templeteDelegate").html()));
			grid.createGrid(columns);
		},
		
		/**
		 * 发送获取任务列表请求
		 */
		requestTaskData : function(){
			common.init("../workflow/delegate_task_list","POST",delegate_task.bindTaskGrid);
			common.do_submit();
		},
		
		/**
		 * 绑定任务数据
		 */
		bindTaskGrid : function(result){
			// 綁定数据
			grid.bindData(result);
			// 提示加载成功信息
			layer.msg(msg.LOAD_SUCCESS);
		},

		/**
		 * 委托
		 */
		delegate : function(obj){
			var tr = $(obj).parent().parent();
			var rowData = grid.getSelectRowDataByRow(tr);
			var processInstId = rowData.processInstId;
			var taskId = rowData.taskInstId;
			var taskDefId = rowData.taskDefId;
			var suspendState = rowData.suspendState;
			if (suspendState == 0) {
				layer.msg("当前流程处于冻结状态,无法进行委托操作");
				return;
			}
			/** 绑定意见提交按钮的click事件*/
			$("#delegate").on("click",function(){
				layer.confirm("确定进行委托操作吗？",function(){
					common.init("../workflow/delegate","POST",function(result){
						if (result.code == 0) {
							layer.msg(result.value);
							return;
						}
						layer.msg("委托成功！");
						delegate_task.requestTaskData();
						$("#delegateCancel").click();
					});
					var userId = $("#delegateUser").val();
					common.reset_param();
					common.add_param("taskId",taskId);
					common.add_param("processInstId",processInstId);
					common.add_param("taskDefId",taskDefId);
					var data = common.add_param("userId",userId);
					common.do_submit(data);
				});
			});
			$("#myModalDelegateUser").modal('show');
		},
		
}