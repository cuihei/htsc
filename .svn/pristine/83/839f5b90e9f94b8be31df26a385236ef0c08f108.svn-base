$(function(){
	syslogOperation.init();
})

var syslogOperation = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("syslogOperation");
		loading.init();
		try{
			syslogOperation.createSyslogOperationGrid();
			syslogOperation.requestSyslogOperationData();
			syslogOperation.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 创建资源列表
	 */
	createSyslogOperationGrid : function(){
		var columns = syslogOperation.createSyslogOperationColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 构建资源列表列集合
	 */
	createSyslogOperationColumns : function(){
		grid.resetColumn();
		grid.addColumn("30%","id","日志编号");
		grid.addColumn("20%","operatorId","操作者");
		grid.addColumn("30%","operatorIp","操作者ip");
		grid.addColumn("30%","operationBehavior","操作行为");
		grid.addColumn("30%","operationResult","操作结果");
		grid.addColumn("30%","operationObject","操作对象");
		return grid.addColumn("30%","operationTime","操作时间");
	},
	
	/**
	 * 发送数据请求
	 */
	requestSyslogOperationData : function(){
		common.init("../so/list","POST",syslogOperation.bindSyslogOperationGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindSyslogOperationGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 删除
	 */
	removeSyslogOperation : function(){
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length <= 0) {
			layer.msg('未选择任何行!', {icon:5,time:1500});
			return false;
		}
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var syslogOperations = [];
			$.each(rowDatas,function(i,item){
				var id = $("#syslogOperation").data("kendoGrid").dataItem(item).id;
				var syslogOperation = {};
				syslogOperation.id = id;
				syslogOperations.push(syslogOperation);
			});
			var param = JSON.stringify(syslogOperations);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("syslogOperation",param);
			common.init("../so/remove","POST",syslogOperation.removeSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(result){
		grid.init("syslogOperation");
		layer.msg('已删除');
		syslogOperation.requestSyslogOperationData();
	},
	
	/**
	 * 绑定页面按钮点击事件
	 */
	bindPageEvent : function(){
		/** 绑定刷新日志列表窗口按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
		
		/** 绑定删除日志窗口按钮的click事件*/
		$("#remove").on("click",function(){
			syslogOperation.removeSyslogOperation();
		});

		/** 绑定导出按钮的click事件*/
		$("#export").on("click",function(){
			var rowDatas = grid.getSelectRowsData();
			// 判断是否选中行
			if (rowDatas.length<=0) {
				layer.msg('未选择任何行!', {icon:5,time:1000});
				return false;
			}
			var syslogOperations = [];
			$.each(rowDatas,function(i,item){
				var id = $("#syslogOperation").data("kendoGrid").dataItem(item).id;
				var syslogOperation = {};
				syslogOperation.id = id;
				syslogOperations.push(syslogOperation);
			});
			var param = JSON.stringify(syslogOperations);
			window.location.href = "../so/export?so="+param;
		});
	}
}