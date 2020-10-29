$(function(){
	monthly_plan.init();
})

var monthly_plan = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("monthly_plan");
		loading.init();
		try{
			monthly_plan.createmonthly_planGrid();
			monthly_plan.requestmonthly_planData();
			monthly_plan.bindPageEvent();
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
			common.init("../month/list?year="+date,"POST",monthly_plan.bindmonthly_planGrid);
			common.do_submit();
		}
		
	},
	
	/**
	 * 创建资源列表
	 */
	createmonthly_planGrid : function(){
		var columns = monthly_plan.createmonthly_planColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 构建资源列表列集合
	 */
	createmonthly_planColumns : function(){
		grid.resetColumn();
		grid.addColumn("150px","seaArea","海区",null,null,null,false);
		grid.addColumn("100px","mapNo","图号",null,null,null,false);
		grid.addColumn("150px","mapName","图名",null,null,null,false);
		grid.addColumn("150px","scale","比例尺 1:",null,null,null,false);
		grid.addColumn("150px","property","性质",null,null,null,false);
		grid.addColumn("150px","planExchangeTime","汇交时间","#= planExchangeTime ? kendo.toString(new Date(planExchangeTime), 'yyyy-MM-dd') : '' #");
		return grid.addColumn("200px","remarks","备注",null,null,null,false);
	},
	
	/**
	 * 发送数据请求
	 */
	requestmonthly_planData : function(){
		var date = $("#year").val();
		common.init("../month/list?year="+date,"POST",monthly_plan.bindmonthly_planGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindmonthly_planGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 绑定页面按钮点击事件
	 */
	bindPageEvent : function(){
		/** 绑定导出通知按钮的click事件*/
		$("#export").on("click",function(){
			var date = $("#year").val();
			var rowDatas = grid.getSelectRowsData();
			// 判断是否选中行
			if (rowDatas.length<=0) {
				layer.msg('未选择任何行!', {icon:5,time:1000});
				return false;
			}
			var monthly_plans = [];
			$.each(rowDatas,function(i,item){
				var id = $("#monthly_plan").data("kendoGrid").dataItem(item).id;
				var monthly_plan = {};
				monthly_plan.id = id;
				monthly_plans.push(monthly_plan);
			});
			var param = JSON.stringify(monthly_plans);
			window.location.href = "../month/export?monthPlans="+param+"&year="+date;
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
			monthly_plan.search(date1,date2);
		});
	},
	/**
	 * 模糊查询查询
	 */
	search : function(date1,date2){
		// 创建grid
		monthly_plan.createmonthly_planGrid();
		common.init("../month/list?startTime="+date1+"&endTime="+date2,"POST",monthly_plan.bindmonthly_planGrid);
		common.do_submit();
	}
	
	
}