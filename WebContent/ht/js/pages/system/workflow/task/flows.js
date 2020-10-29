$(function(){
	flows.init();
})

var flows = {
	/**
	 * 初始化
	 */
	init : function(process_inst_id,task_id){
		grid.init("dv_flows");
		loading.init();
		try{
			flows.createFlowsGrid();
			flows.requestFlowsData();
			// 绑定返回按钮事件
			$("#back").on("click",function(){
				history.back();
			})
		}
		catch(err){
			loading.close();
		}
	},

	/**
	 * 创建流转状态列表
	 */
	createFlowsGrid : function(){
		try {

			var columns = flows.createFlowsColumns();
			grid.createGrid(columns);
		} catch (e) {
			loading.close();
		}
	},
	
	/**
	 * 创建流转状态列表列集合
	 */
	createFlowsColumns : function(){
		grid.resetColumn();
		grid.addColumn("16%","processName","流程名称");
		grid.addColumn("16%","taskName","任务名称");
		grid.addColumn("10%","taskResult","任务结果");
		grid.addColumn("10%","userName","执行人员");
		grid.addColumn("14%","startTime","开始时间","#= startTime ? kendo.toString(new Date(startTime), 'yyyy-MM-dd HH:mm:ss') : '' #");
		grid.addColumn("14%","endTime","结束时间","#= endTime ? kendo.toString(new Date(endTime), 'yyyy-MM-dd HH:mm:ss') : '' #");
		grid.addColumn("18%","advice","通告改正至");
		var columns =  grid.addColumn("18%","remark","备注");
		return columns;
	},
	
	/**
	 * 发送数据请求
	 */
	requestFlowsData : function(){
		common.reset_param();
		var data = common.add_param("processInstId",$("#processInstId").val());
		common.init("workflow/flow_list","POST", function(result){
			grid.bindData(result);
		});
		common.do_submit(data);
	}
}
