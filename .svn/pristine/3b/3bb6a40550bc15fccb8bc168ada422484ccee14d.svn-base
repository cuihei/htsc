$(function(){
	papersmallcor.init();
})

var papersmallcor = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("paperSmallCor");
		loading.init();
		try{
			papersmallcor.createPaperSmallCorGrid();
			papersmallcor.requestPaperSmallCorData();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建区域管理列表列集合
	 */
	createPaperSmallCorColumns : function(){
		grid.resetColumn();
		grid.addColumn("120px","id","任务名称");
		grid.addColumn("120px","baseData.value","改正期号");
		grid.addColumn("120px","areaName","制图范围");
		grid.addColumn("180px","id","涉及图幅变更信息");
		grid.addColumn("100px","areaName","编绘员");
		grid.addColumn("100px","areaName","质检员");
		grid.addColumn("100px","areaName","审定员");
		grid.addColumn("100px","baseData.value","状态");
		grid.addColumn("120px","handle","流转状态",kendo.template($("#status").html()));
		grid.addColumn("100px","handle","详情",kendo.template($("#details").html()));
		return grid.addColumn("130px","handle","导出经历薄",kendo.template($("#export").html()));
	},
	
	/**
	 * 创建区域管理列表
	 */
	createPaperSmallCorGrid : function(){
		var columns = papersmallcor.createPaperSmallCorColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestPaperSmallCorData : function(){
		common.init("../papersmallcor/papersmallcorlist","POST",papersmallcor.bindPaperSmallCorGrid);
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
		common.toPage("../papersmallcor/initstatus?process_Inst_Id="+process_Inst_Id+"&task_Id"+task_Id);
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
		//common.init("../area/export","POST",catalogarea.exportSuccess())
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindPaperSmallCorGrid : function(result){
		grid.bindData(result);
		/** 绑定流转状态按钮的click事件*/
		var statusBtns = $("button[name='status']");
		$.each(statusBtns,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				taskbill.toBillStatusPage(tr);
			});
		});
		/** 绑定详情按钮的click事件*/
		var detailsBtns = $("button[name='details']");
		$.each(detailsBtns,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				//taskbill.toBillEditPage(tr);
			});
		});
		/** 绑定导出按钮的click事件*/
		var exportBtns = $("button[name='export']");
		$.each(exportBtns,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				//taskbill.toBillEditPage(tr);
			});
		});
		//grid.setEvents();
	},

}
