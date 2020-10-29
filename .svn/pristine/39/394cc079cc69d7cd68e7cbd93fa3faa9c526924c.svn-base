$(function(){
	soursmallcor.init();
})

var soursmallcor = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("sourSmallCor");
		loading.init();
		try{
			soursmallcor.createSourSmallCorGrid();
			soursmallcor.requestSourSmallCorData();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建区域管理列表列集合
	 */
	createSourSmallCorColumns : function(){
		grid.resetColumn();
		grid.addColumn("120px","id","任务名称");
		grid.addColumn("120px","baseData.value","通告期号");
		grid.addColumn("120px","areaName","工程名称");
		grid.addColumn("120px","id","通告项号");
		grid.addColumn("120px","baseData.value","生成类型");
		grid.addColumn("120px","mapNo","其他改正");
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
	createSourSmallCorGrid : function(){
		var columns = soursmallcor.createSourSmallCorColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestSourSmallCorData : function(){
		common.init("../soursmallcor/soursmallcorlist","POST",soursmallcor.bindSourSmallCorGrid);
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
		common.toPage("../soursmallcor/initstatus?process_Inst_Id="+process_Inst_Id+"&task_Id"+task_Id);
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
		common.toPage("../soursmallcor/initdetails?id="+id);
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindSourSmallCorGrid : function(result){
		grid.bindData(result);
		/** 绑定流转状态按钮的click事件*/
		var statusBtns = $("button[name='status']");
		$.each(statusBtns,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				soursmallcor.toStatusPage(tr);
			});
		});
		/** 绑定详情按钮的click事件*/
		var detailsBtns = $("button[name='details']");
		$.each(detailsBtns,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				soursmallcor.toDetailsPage(tr);
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
