var soursmallcorstatus = {
	/**
	 * 初始化
	 */
	init : function(process_inst_id,task_id){
		grid.init("sourSmallCorStatus");
		loading.init();
		try{
			soursmallcorstatus.createSourSmallCorStatusGrid();
			soursmallcorstatus.requestSourSmallCorStatusData(process_inst_id,task_id);
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建区域管理列表列集合
	 */
	createSourSmallCorStatusColumns : function(){
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
	requestSourSmallCorStatusData : function(process_inst_id,task_id){
		common.init("soursmallcor/soursmallcorstatus?process_Inst_Id="+process_inst_id+"&task_Id="+task_id,"POST", function(result){
			grid.bindData(result);
		});
		common.do_submit();
	},
	
	/**
	 * 创建区域管理列表
	 */
	createSourSmallCorStatusGrid : function(){
		var columns = soursmallcorstatus.createSourSmallCorStatusColumns();
		grid.createGrid(columns);
	}
}
