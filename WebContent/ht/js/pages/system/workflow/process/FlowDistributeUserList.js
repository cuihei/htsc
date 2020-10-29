var flowUser = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("user");
		loading.init();
		try{
			//创建用户列表
			flowUser.createUserGrid();
			//请求用户列表数据
			flowUser.requestUserData();
			//绑定页面事件
			flowUser.bindPageEvent(flowIds);
			//选中选择的数据
			flowUser.selectUserData(flowIds);
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建用户列表列集合
	 */
	createUserColumns : function(){
		grid.resetColumn();
		grid.addColumn("20%","userNo","登陆账号");
		grid.addColumn("20%","userName","用户名称");
		grid.addColumn("25%","mail","邮箱");
		return grid.addColumn("25%","phone","电话");
	},
	
	/**
	 * 创建用户列表
	 */
	createUserGrid : function(){
		var columns = flowUser.createUserColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestUserData : function(){
		common.init("../user/list","POST",flowUser.bindUserGrid);
		common.do_submit();
	},
	
	
	/**
	 * 选中数据
	 */
	selectUserData : function(flowIds){
		var flowIds = $("#flowIds").val();
		// 添加参数 @param 参数key；参数value
		var data = common.add_param("flowIds",flowIds);
		common.init("../workflow/selectUser","POST",flowUser.selectUserGrid);
		common.do_submit(data);
	},
	
	/**
	 * 选中数据
	 */
	selectUserGrid : function(result){
		var users=[]; 
		var rows = common.get_all_rows($("#user"));
		$.each(result,function(i,item){
			var us=item;
			$.each(us,function(i,item){
				var user=item;
				$.each(rows,function(i,item){
					var id = $("#user").data("kendoGrid").dataItem(item).id;
					if(user==id){
						var checkbox = $(item).find("input:checkbox");
						checkbox.prop("checked",true);
						common.select_row($("#user"),item);
					}
				});
			});
    	});
	},
	
	
	/**
	 * 发送返回上一页的请求
	 */
	toFlowChartList : function(){
		//调回用户列表显示页面
		common.toPage("../workflow/init");
		//执行刷新用户列表按钮
		 parent.$("#refresh").click();
	},
	
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindUserGrid : function(result){
		grid.bindData(result);
	},
	
	/**
	 * 绑定任务用户
	 * */
	submitSuccess : function(result){
		if (result.code==0) {
			layer.msg(result.value);
			return;
		}
		layer.msg(messageHelper.readMsgByTypeAndCode("alloc",false,"1"));
	},

	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(flowIds){
		//提交事件绑定
		$("#submit").on("click",function(){
			var rowDatas = grid.getSelectRowsData();
			if (rowDatas.length<=0) {
				layer.msg('未选择任何行!', {icon:5,time:1000});
				return;
			}
			var users=[];
			for (var int = 0; int < rowDatas.length; int++) {
				var user = {};
				user.id = $("#user").data("kendoGrid").dataItem(rowDatas[int]).id;
				users.push(user);
			}
			var flowIds = $("#flowIds").val();
			var userParam = JSON.stringify(users);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("user",userParam);
			data = common.add_param("flowIds",flowIds);
			common.init("../workflow/addUser","POST",flowUser.submitSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
		
		//返回事件绑定
		$("#back").on("click",function(){
			flowUser.toFlowChartList();
		});
		
	}
}