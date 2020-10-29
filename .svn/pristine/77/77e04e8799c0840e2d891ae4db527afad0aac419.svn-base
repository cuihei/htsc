$(function(){
	completion_status.init();
})

var completion_status = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("completion_status");
		loading.init();
		try{
			completion_status.createCompletionGrid();
			completion_status.requestCompletionData();
			completion_status.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
		//初始化时间控件
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
			common.init("../statistic/list?year="+date,"POST",completion_status.bindCompletionGrid);
    		common.do_submit();
		}
	},
	
	/**
	 * 创建港口航道图完成情况列表
	 */
	createCompletionGrid : function(){
		var columns = completion_status.createCompletionColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 构建港口航道图完成情况列表列集合
	 */
	createCompletionColumns : function(){
		grid.resetColumn();
		//grid.addColumn("160px","area","海区");
		grid.addColumn("100px","mapNo","图号");
		grid.addColumn("100px","mapName","图名");
		grid.addColumn("130px","scale","比例尺 1:");
		grid.addColumn1("150px","content","编绘内容");
		grid.addColumn("160px","completeDate","完成日期（审定）");
		//grid.addColumn("250px","completeNumber","完成数量 月/累计（海图数量）");
		return grid.addColumn("150px","remarks","备注",null,null,null,false);
	},
	
	/**
	 * 发送数据请求
	 */
	requestCompletionData : function(){
		var date = $("#year").val();
		common.init("../statistic/list?year="+date,"POST",completion_status.bindCompletionGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindCompletionGrid : function(result){
		grid.bindData(result);
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
			var completionStatus = [];
			$.each(rowDatas,function(i,item){
				var id = $("#completion_status").data("kendoGrid").dataItem(item).id;
				var completionStatu = {};
				completionStatu.id = id;
				completionStatus.push(completionStatu);
			});
			var param = JSON.stringify(completionStatus);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("completionStatus",param);
			window.location.href = "../statistic/export?completionStatus="+param;
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
			completion_status.search(date1,date2);
		});
	},
	/**
	 * 模糊查询查询
	 */
	search : function(date1,date2){
		// 创建grid
		completion_status.createCompletionGrid();
		common.init("../statistic/list?startTime="+date1+"&endTime="+date2,"POST",completion_status.bindCompletionGrid);
		common.do_submit();
	}
	
	
}