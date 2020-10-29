var sourcestatus = {
	/**
	 * 初始化
	 */
	init : function(process_inst_id,task_id){
		grid.init("sourceStatus");
		loading.init();
		try{
			sourcestatus.createSourceStatusGrid();
			sourcestatus.requestSourceStatusData(process_inst_id,task_id);
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建区域管理列表列集合
	 */
	createSourceStatusColumns : function(){
		grid.resetColumn();
		grid.addNoCheckColumn("5%","id","任务名称");
		grid.addNoCheckColumn("5%","user_Name","执行人员");
		grid.addNoCheckColumn("5%","start_Time","开始时间");
		grid.addNoCheckColumn("5%","end_Time","结束时间");
		return grid.addColumn("5%","advice","意见");
	},
	
	/**
	 * 发送数据请求
	 */
	requestSourceStatusData : function(process_inst_id,task_id){
		common.init("source/sourcestatus?process_Inst_Id="+process_inst_id+"&task_Id="+task_id,"POST", function(result){
			grid.bindData(result);
		});
		common.do_submit();
	},
	
	/**
	 * 创建区域管理列表
	 */
	createSourceStatusGrid : function(){
		var columns = sourcestatus.createSourceStatusColumns();
		grid.createGrid(columns);
	}
}
