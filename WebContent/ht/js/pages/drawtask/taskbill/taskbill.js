var ifPlanNull="";
$(function(){
	taskbill.init();
})

var taskbill = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("taskBill");
		loading.init();
		try{
			taskbill.createGrid();
			taskbill.requestData();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建区域管理列表列集合
	 */
	createColumns : function(){
		grid.resetColumn();
		grid.addColumn("150px","taskBook.taskbookNo","任务书序号");
		grid.addColumn("160px","taskBook.taskbookName","编绘任务书名称");
		grid.addColumn("120px","plan.category.value","计划类别");
		grid.addLockedColumn("120px","plan.mapNo","图号");
		grid.addLockedColumn("120px","plan.mapName","图名");
		grid.addColumn("120px","plan.scale","比例尺 1:");
		grid.addColumn("120px","plan.type","类别");
		grid.addColumn("140px","plan.workload","测绘工作量");
		grid.addColumn("150px","plan.deliverTime","资料汇交时间","#= plan.deliverTime ? kendo.toString(new Date(plan.deliverTime), 'yyyy-MM-dd') : '' #");
		grid.addColumn("150px","plan.planMonth","所属月份");
		grid.addColumn("120px","plan.measurementPeriod","基测周期");
		//grid.addColumn("120px","plan.testIng","检测周期");
		grid.addColumn("120px","plan.revision","版次");
		grid.addColumn("120px","plan.printQuantity","印刷数量");
		return grid.addColumn("100px","taskBook.taskbookName","说明");
	},
	
	/**
	 * 创建区域管理列表
	 */
	createGrid : function(){
		var columns = taskbill.createColumns();
		grid.createGrid(columns);
		var gridInst = grid.getGrid().data("kendoGrid");
		gridInst.lockColumn("selected");
		gridInst.lockColumn("rowNumber");
		gridInst.reorderColumn(0, gridInst.columns[2]);
		gridInst.reorderColumn(1, gridInst.columns[3]);
	},
	
	/**
	 * 发送数据请求
	 */
	requestData : function(){
		common.init("../taskbill/list?flag="+flag.value,"POST",taskbill.bindGrid);
		common.do_submit();
	},
	
	bindGrid : function(result){
		grid.bindData(result);
		var gridInst = grid.getGrid().data("kendoGrid");
		var options = gridInst.getOptions();
		var height = options.height + 20;
		gridInst.setOptions({
			height:height
		});
	}
}
