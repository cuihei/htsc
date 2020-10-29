$(function(){
	syslog.init();
})

var syslog = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("syslog");
		loading.init();
		try{
			syslog.createSyslogGrid();
			syslog.requestSyslogData();
			syslog.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 创建资源列表
	 */
	createSyslogGrid : function(){
		var columns = syslog.createSyslogColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 构建资源列表列集合
	 */
	createSyslogColumns : function(){
		grid.resetColumn();
		grid.addColumn("20%","handleId","访问者");
		grid.addColumn("30%","handleIp","访问者ip");
		grid.addColumn("30%","handleBehavior","操作行为");
		grid.addColumn("30%","handleResult","操作结果");
		return grid.addColumn("30%","handleTime","访问时间");
	},
	
	/**
	 * 发送数据请求
	 */
	requestSyslogData : function(){
		common.init("../syslog/list","POST",syslog.bindSyslogGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindSyslogGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 删除
	 */
	removeSyslog : function(){
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length <= 0) {
			layer.msg('未选择任何行!', {icon:5,time:1500});
			return false;
		}
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var syslogs = [];
			$.each(rowDatas,function(i,item){
				var id = $("#syslog").data("kendoGrid").dataItem(item).id;
				var syslog = {};
				syslog.id = id;
				syslogs.push(syslog);
			});
			var param = JSON.stringify(syslogs);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("syslog",param);
			common.init("../syslog/remove","POST",syslog.removeSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(result){
		layer.msg('已删除');
		syslog.requestSyslogData();
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
			syslog.removeSyslog();
		});

		/** 绑定导出按钮的click事件*/
		$("#export").on("click",function(){
			var rowDatas = grid.getSelectRowsData();
			// 判断是否选中行
			if (rowDatas.length<=0) {
				layer.msg('未选择任何行!', {icon:5,time:1000});
				return false;
			}
			var syslogs = [];
			$.each(rowDatas,function(i,item){
				var id = $("#syslog").data("kendoGrid").dataItem(item).id;
				var syslog = {};
				syslog.id = id;
				syslogs.push(syslog);
			});
			var param = JSON.stringify(syslogs);
			window.location.href = "../syslog/export?syslog="+param;
		});
	}
}