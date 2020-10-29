var propaperstatus = {
	/**
	 * 初始化
	 */
	init : function(process_inst_id,task_id){
		grid.init("corNoticeStatus");
		loading.init();
		try{
			propaperstatus.createProPaperStatusGrid();
			propaperstatus.requestProPaperStatusData(process_inst_id,task_id);
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建区域管理列表列集合
	 */
	createProPaperStatusColumns : function(){
		grid.resetColumn();
		grid.addNoCheckColumn("5%","id","任务名称");
		grid.addNoCheckColumn("5%","user_Name","执行人员");
		grid.addNoCheckColumn("5%","start_Time","开始时间");
		grid.addNoCheckColumn("5%","end_time","结束时间");
		return grid.addColumn("5%","advice","意见");
	},
	
	/**
	 * 发送数据请求
	 */
	requestProPaperStatusData : function(){
		common.init("propaper/propaperstatus?process_Inst_Id="+process_inst_id+"&task_Id="+task_id,"POST", function(result){
			grid.bindData(result);
		});
		common.do_submit();
	},
	
	/**
	 * 创建区域管理列表
	 */
	createProPaperStatusGrid : function(){
		var columns = propaperstatus.createProPaperStatusColumns();
		grid.createGrid(columns);
	}
}
