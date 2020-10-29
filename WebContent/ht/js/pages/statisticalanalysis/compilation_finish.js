$(function(){
	chartCompletion.init();
})

var chartCompletion = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("chartCompletion");
		loading.init();
		try{
			chartCompletion.createSubmissionSummaryGrid();
			chartCompletion.requestSubmissionSummaryData();
			chartCompletion.bindPageEvent();
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
		}).on('changeDate',gotoDate);
		function gotoDate(){
			var date = $("#year").val();
			common.init("../chartCompletion2/finish?year="+date,"POST",chartCompletion.bindSubmissionSummaryGrid);
			common.do_submit();
		}
	},
	
	/**
	 * 创建资源列表
	 */
	createSubmissionSummaryGrid : function(){
		var columns = chartCompletion.createSubmissionSummaryColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 构建资源列表列集合
	 */
	createSubmissionSummaryColumns : function(){
		grid.resetColumn();
		grid.addColumn("100px","processDefName","类型");
		grid.addColumn("160px","mapName","图名");
		grid.addColumn("100px","mapNo","图号");
		grid.addColumn("150px","scale","比例尺（1：）");
		grid.addColumn("150px","type","制图性质");
		grid.addColumn("150px","sjhjtime","资料汇交日期"); //实际汇交时间
		grid.addColumn("150px","compilationStartTime","编绘开始日期");//编绘开始时间
		grid.addColumn("150px","apprvoeEndTime","编绘完成日期");//审定完成时间
		grid.addColumn("150px","","印刷完成日期");
		grid.addColumn("150px","score","质量评定"); 
		grid.addColumn("150px","","版次、印次信息（版本）");
		grid.addColumn("150px","","调整系数");
		grid.addColumn("150px","","计划印数");
		grid.addColumn("150px","","实际印数");
		grid.addColumn("150px","","上海入库数");
		grid.addColumn("150px","","天津入库数");
		grid.addColumn("150px","","广东入库数");
	return 	grid.addColumn("150px","","备注");
	},
	
	/**
	 * 发送数据请求
	 */
	requestSubmissionSummaryData : function(){
		var date = $("#year").val();
	    common.init("../chartCompletion2/finish?year="+date,"POST",chartCompletion.bindSubmissionSummaryGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindSubmissionSummaryGrid : function(result){
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
			var chartCompletions = [];
			$.each(rowDatas,function(i,item){
				var id = $("#chartCompletion").data("kendoGrid").dataItem(item).id;
				var chartCompletion = {};
				chartCompletion.id = id;
				chartCompletions.push(chartCompletion);
			});
			var param = JSON.stringify(chartCompletions);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("chartCompletions",param);
			window.location.href = "../chartCompletion2/export?chartCompletions="+param;
	//		window.location.href = "../chartCompletion2/finish?chartCompletions="+param;
		});
		
		
		$("#export1").on("click",function(){
			/*var rowDatas = grid.getSelectRowsData();
			// 判断是否选中行
			if (rowDatas.length<=0) {
				layer.msg('未选择任何行!', {icon:5,time:1000});
				return false;
			}*/
			/*var chartCompletions = [];
			$.each(rowDatas,function(i,item){
				var id = $("#chartCompletion").data("kendoGrid").dataItem(item).id;
				var chartCompletion = {};
				chartCompletion.id = id;
				chartCompletions.push(chartCompletion);
			});
			var param = JSON.stringify(chartCompletions);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("chartCompletions",param);*/
			window.location.href = "../chartCompletion2/exportsum";
	//		window.location.href = "../chartCompletion2/finish?chartCompletions="+param;
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
			var year = $("#year").val();
			if(date1.indexOf(year) > -1){
				
			}else{
				layer.msg('开始时间的年份和当前选择的年份不一致!', {icon:5,time:3000});
				return false;
			}
			var date2 = $("#date2").val();
			if(date2 == null || date2 ==""){
				layer.msg('未选择结束时间!', {icon:5,time:1000});
				return false;
			}
			if(date2.indexOf(year) > -1){
				
			}else{
				layer.msg('结束时间的年份和当前选择的年份不一致!', {icon:5,time:3000});
				return false;
			}
			chartCompletion.search(date1,date2);
		});
	},
	/**
	 * 模糊查询查询
	 */
	search : function(date1,date2){
		// 创建grid
		chartCompletion.createSubmissionSummaryGrid();
		common.init("../chartCompletion2/finish?startTime="+date1+"&endTime="+date2,"POST",chartCompletion.bindSubmissionSummaryGrid);
		common.do_submit();
	}
	
	
}