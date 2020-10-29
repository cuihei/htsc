$(function(){
	dynamic.init();
})

var dynamic = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("dynamic_table");
		loading.init();
		try{
			dynamic.createDynamicGrid();
			dynamic.requestDynamicData();
			dynamic.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
		$("#year").datepicker({
			startView: 2, 
			maxViewMode: 2,
			minViewMode:2,
			format: 'yyyy',
			autoclose: true
		}).on('changeDate',changeYearMonth);
		function changeYearMonth(){
			var date = $("#year").val();
			loading.init();
			common.init("../dynamic/list?year="+date,"POST",dynamic.bindDynamicGrid);
    		common.do_submit();
		}
	},
	
	/**
	 * 创建资源列表
	 */
	createDynamicGrid : function(){
		var columns = dynamic.createDynamicColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 构建资源列表列集合
	 */
	createDynamicColumns : function(){
		grid.resetColumn();
		grid.addColumn("100px","mapNo","图号");
		grid.addColumn("160px","mapName","图名");
		grid.addColumn("150px","area","海区");
		grid.addColumn("120px","scale","比例尺 1:");
		
	//	grid.addColumn("120px","taskType","编绘任务类型");
		grid.addColumn("120px","processDefName","状态");
	//	grid.addColumn("120px","status","完成状态");
	//	grid.addColumn("120px","taskBookNo","任务书编号");
		
		//grid.addColumn("100px","month","月份");
		//grid.addColumn("100px","year","年份");
		grid.addColumn("100px","property","性质");
		grid.addColumn("150px","planExchangeTime","计划汇交时间","#= planExchangeTime ? kendo.toString(new Date(planExchangeTime), 'yyyy-MM-dd') : '' #" );
		grid.addColumn("150px","actualExchangeTime","实际汇交时间","#= actualExchangeTime ? kendo.toString(new Date(actualExchangeTime), 'yyyy-MM-dd') : '' #" );
		grid.addColumn("150px","taskReleaseTime","任务下达时间","#= taskReleaseTime ? kendo.toString(new Date(taskReleaseTime), 'yyyy-MM-dd') : '' #" );
		grid.addColumn("150px","planCompleteTime","计划完成时间","#= planCompleteTime ? kendo.toString(new Date(planCompleteTime), 'yyyy-MM-dd') : '' #" );
		return grid.addColumn("100px","remarks","动态");
	},
	
	/**
	 * 发送数据请求
	 */
	requestDynamicData : function(){
		var date = $("#year").val();
		common.init("../dynamic/list?year="+date,"POST",dynamic.bindDynamicGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindDynamicGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 绑定页面按钮点击事件
	 */
	bindPageEvent : function(){
		/** 绑定导出通知按钮的click事件*/
		$("#export").on("click",function(){
			var rowDatas = grid.getSelectRowsData();
			// 判断是否选中行
			if (rowDatas.length<=0) {
				layer.msg('未选择任何行!', {icon:5,time:1000});
				return false;
			}
			var dynamicTables = [];
			$.each(rowDatas,function(i,item){
				var id = $("#dynamic_table").data("kendoGrid").dataItem(item).id;
				var dynamicTable = {};
				dynamicTable.id = id;
				dynamicTables.push(dynamicTable);
			});
			var param = JSON.stringify(dynamicTables);
			var date1 = $("#date1").val();
			var date2 = $("#date2").val();
			window.location.href = "../dynamic/export?dynamicTables="+param;
		});
		
		/**
		 * 查询按钮点击事件
		 */
		$("#search").on("click",function(){
			// 获取出版日期
			var date1 = $("#date1").val();
			if(date1 == null || date1 ==""){
				layer.msg('未选择开始时间!', {icon:5,time:1000});
				return false;
			}
			var date2 = $("#date2").val();
			if(date2 == null || date2 ==""){
				layer.msg('未选择结束时间!', {icon:5,time:1000});
				return false;
			}
			dynamic.search(date1,date2);
		});
	},
	/**
	 * 模糊查询查询
	 */
	search : function(date1,date2){
		// 创建grid
		dynamic.createDynamicGrid();
		common.init("../dynamic/list?startTime="+date1+"&endTime="+date2,"POST",dynamic.bindDynamicGrid);
		common.do_submit();
	}
	
}