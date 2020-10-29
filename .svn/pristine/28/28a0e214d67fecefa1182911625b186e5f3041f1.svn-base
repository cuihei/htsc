$(function(){
	qualityAnalysisStatistics.init();
})

var qualityAnalysisStatistics = {
	init : function(){
		// 初始化表格对象
		grid.init("qualityAnalysisStatistics");
		// 显示加载层
		loading.init();
		try{
			// 创建表格
			qualityAnalysisStatistics.createqualityAnalysisStatisticsGrid();
			// 发送数据请求绑定表格数据
			qualityAnalysisStatistics.requestqualityAnalysisStatisticsData();
		}
		catch(err){
			loading.close();
		}
		$("#export").on("click",function(){
			qualityAnalysisStatistics.exportExcel();
		});
		$("#search").on("click",function(){
			qualityAnalysisStatistics.search();
		});
		
	},
	
	/**
	 * 创建任务表格
	 */
	createqualityAnalysisStatisticsGrid : function(){
		grid.resetColumn();
		// 流程列
		var key = $("#key").val();
		grid.addLockedColumn("100px","mapNo","图号",false);
		grid.addLockedColumn("150px","mapName","图名");
		grid.addLockedColumn("100px","scale","比例尺 1:");
		grid.addLockedColumn("100px","processDefName","任务类型");
		grid.addLockedColumn("100px","status","状态");
		grid.addColumn("100px","compilationPerson","编绘员");
		grid.addColumn("100px","qualityPerson","质检人");
		grid.addColumn("100px","apprvoePerson","审定人");
		grid.addColumn("100px","dataFrom","来源");
		grid.addColumn("100px","opinion","存在问题及处理意见");
		grid.addColumn("100px","defecttype","类别");
		grid.addColumn("100px","defectitem","检查项目");
		if("SEA_MAP_COMPILATION_PAPER"!=key){
			grid.addColumn("100px","discription","缺陷内容");
			grid.addColumn("100px","deep","缺陷类别");
		}
		grid.addColumn("100px","number","缺陷个数");
		grid.addColumn("100px","score","扣分");
		var grad="成图分数";
		if("SEA_MAP_COMPILATION_SOURCE_DATA"==key){
			grad="分数";
		}
		var columns = grid.addColumn("100px","grading",grad);
		grid.createGrid(columns);
		var gridInst = grid.getGrid().data("kendoGrid");
		gridInst.lockColumn("selected");
		gridInst.lockColumn("rowNumber");
		gridInst.reorderColumn(0, gridInst.columns[5]);
		gridInst.reorderColumn(1, gridInst.columns[6]);
	},
	
	/**
	 * 发送获取任务列表请求
	 */
	requestqualityAnalysisStatisticsData : function(){
		var key = $("#key").val();
		common.init("../qualityAnalysisStatistics/all?processDefKey="+key,"POST",qualityAnalysisStatistics.bindqualityAnalysisStatisticsGrid);
		common.do_submit();
	},
	
	/**
	 * 绑定任务数据
	 */
	bindqualityAnalysisStatisticsGrid : function(result){
		// 綁定数据
		grid.bindData(result,true);
		var gridInst = $("#qualityAnalysisStatistics").data("kendoGrid");
		gridInst.setOptions({
			sortable: true,
			lockedable: false,
			autoFitColumnable: false,
			columnMenu: true
		});
		// 提示加载成功信息
		layer.msg(msg.LOAD_SUCCESS);
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
	},
	
	process: function(obj){
		var tr = $(obj).parent().parent();
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取用户ID
		var flowId = rowData.processDefId;
		var excutionId = rowData.excutionId;
		// 跳转到查看流程圖页面
		common.toPage("../workflow/task_flow_page?flowId="+flowId+"&excutionId="+excutionId);
	},
	
	exportExcel :function(){
		var key = $("#key").val();
		var gridInst = grid.getGrid().data("kendoGrid");
		gridInst.bind("excelExport", function(e) {
			var name="质量统计分析";
			if("SEA_MAP_COMPILATION_SOURCE_DATA"==key){
				name="源数据质量分析统计表";
			}
			if("SEA_MAP_COMPILATION_PAPER"==key){
				name="纸海图质量分析统计表 ";
			}
			if("SEA_MAP_COMPILATION_ELECTRONIC"==key){
				name="电子海图质量分析统计表 ";
			}
		    e.workbook.fileName = name+".xlsx";
		});
		gridInst.saveAsExcel();
	},
	search:function(){
		var key = $("#key").val();
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		if(startTime==null||startTime==undefined||startTime==""){
			layer.msg('请选择开始时间!', {icon:5,time:1000});
			return;
		}
		if(endTime==null||endTime==undefined||endTime==""){
			layer.msg('请选择结束时间!', {icon:5,time:1000});
			return;
		}
		common.init("../qualityAnalysisStatistics/search?processDefKey="+key+"&startTime="+startTime+"&endTime="+endTime,"POST",qualityAnalysisStatistics.bindqualityAnalysisStatisticsGrid);
		common.do_submit();
	}
}