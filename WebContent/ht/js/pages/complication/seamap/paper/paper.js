$(function(){
	paper.init();
})

/** 绑定流转状态按钮的click事件*/
function statusPage(obj) {
	var tr = $(obj).parent().parent();
	paper.toStatusPage(tr);
}

/** 绑定详情按钮的click事件*/
function detailsPage(obj) {
	var tr = $(obj).parent().parent();
	paper.toDetailsPage(tr);
}

/** 绑定导出按钮的click事件*/
function exportPage(obj) {
	var tr = $(obj).parent().parent();
	//paper.toBillEditPage(tr);
}

var paper = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("paper");
		loading.init();
		try{
			paper.createPaperGrid();
			paper.requestPaperData();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建区域管理列表列集合
	 */
	createPaperColumns : function(){
		grid.resetColumn();
		grid.addColumn("120px","areaName","任务名称");
		grid.addColumn("100px","areaName","图号");
		grid.addColumn("100px","areaName","图名");
		grid.addColumn("120px","areaName","比例尺 1:");
		grid.addColumn("120px","areaName","任务类型");
		grid.addColumn("100px","areaName","状态");
		grid.addColumn("110px","areaName","流转节点");
		grid.addColumn("100px","areaName","编绘员");
		grid.addColumn("100px","areaName","质检员");
		grid.addColumn("100px","areaName","审定员");
		grid.addColumn("130px","areaName","当前操作人");
		grid.addColumn("100px","areaName","版次");
		grid.addColumn("120px","handle","流转状态",kendo.template($("#status").html()));
		grid.addColumn("100px","handle","详情",kendo.template($("#details").html()));
		return grid.addColumn("130px","handle","导出经历薄",kendo.template($("#export").html()));
	},
	
	/**
	 * 创建区域管理列表
	 */
	createPaperGrid : function(){
		var columns = paper.createPaperColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestPaperData : function(){
		common.init("../paper/paperlist","POST",paper.bindPaperGrid);
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
		common.toPage("../paper/initstatus?process_Inst_Id="+process_Inst_Id+"&task_Id"+task_Id);
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
		common.toPage("../paper/initdetails?id="+id);
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindPaperGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
}
