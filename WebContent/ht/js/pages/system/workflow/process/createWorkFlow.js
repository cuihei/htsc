$(function(){
	//创建工作流初始化
	createworkflow.init();
})

var createworkflow = {
	// 当前选择行
	_currentRowData : null,
	
	/**
	 * 初始化
	 */
	init : function(){
		// 加载初始化
		loading.init();
		// 隐藏增加任务子流程
		$("#flow").parent().hide();
		try{
			// 创建任务列表
			createworkflow.createTaskGrid();
			// 初始化多选控件
			createworkflow.initMultiselect();
			// 绑定页面事件  
			createworkflow.bindPageEvent();
			
			loading.close();
		}
		catch(err){
			loading.close();
		}
		
	},
	
	/**
	 * 初始化多选控件
	 */
	initMultiselect : function(){
		// 审批角色多选
		$("#approvalRoleSelete").kendoMultiSelect({
	        placeholder: "选择审批角色",
	        dataTextField: "name",
	        dataValueField: "id",
	        dataSource: []
	    });
		// 同意步骤多选
		$("#successNextStep").kendoMultiSelect({
	        placeholder: "选择同意步骤",
	        dataTextField: "text",
	        dataValueField: "value",
	        dataSource: []
	    });
		// 退回步骤多选
		$("#failNextStep").kendoMultiSelect({
	        placeholder: "选择退回步骤",
	        dataTextField: "text",
	        dataValueField: "value",
	        dataSource: []
	    });
	},
	/**
	 * 创建任务表格组件
	 */
	createTaskGrid : function(){
		// 表格对象初始化
		grid.init("createworkflow");
		var columns = createworkflow.createGridColumns();
		grid.createGrid(columns);
		grid.getGrid().data("kendoGrid").setOptions({
			pageable:false,
			filterable:false
		});
		// 创建开始行
		// 添加一行任务数据
		var rowData = {};
		// 任务ID
		rowData.id = "ru_task_start";
		// 任务名称
		rowData.name = "开始";
		// 任务类型
		rowData.type = "0";
		// 任务受理组编号
		rowData.groupNo = null;
		// 同意流向任务ID
		rowData.sourceNo = null;
		// 同意流向任务名称
		rowData.source = null;
		// 退回流向任务ID
		rowData.targetNo = null;
		// 退回流向任务名称
		rowData.target = null;
		// 增加到Grid
		grid.getGrid().data("kendoGrid").dataSource.add(rowData);
		createworkflow.refresh();
	},
	
	/**
	 * 构建用户列表列集合
	 */
	createGridColumns : function(){
		grid.resetColumn();
		grid.addColumn("24%","name","任务名称");
		grid.addColumn("10%","groupName","受理组名称");
		grid.addColumn("10%","source","同意");
		grid.addColumn("10%","target","退回");
		var approvalBtn = "<button class='btn btn-success bk-margin-5'  name='setApprovalRole'><i class='fa fa-plus' ></i>设置审批角色</button>";
		grid.addColumn("16%","setApprovalRole","设置审批角色",kendo.template(approvalBtn));
		var drictBtn = "<button class='btn btn-warning bk-margin-5'  name='setDrict'><i class='fa fa-edit' ></i>设置流向</button>";
		grid.addColumn("16%","setDrict","设置流向",kendo.template(drictBtn));
		var deleteBtn = "<button class='btn btn-danger bk-margin-5'  name='remove'><i class='fa fa-times' ></i>删除</button>";
		return grid.addColumn("10%","delete","删除",kendo.template(deleteBtn));
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindTaskGrid : function(){
		/** 
		 * 绑定设置审批角色按钮的click事件
		 */
		var setApprovalRole = $("button[name='setApprovalRole']");
		$.each(setApprovalRole,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				createworkflow._currentRowData = grid.getGrid().data("kendoGrid").dataItem(tr);
				createworkflow.setApprovalRole();
			});
		});

		/**
		 * 绑定设置流向按钮的click事件
		 */
		var setDrict = $("button[name='setDrict']");
		$.each(setDrict,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				createworkflow._currentRowData = grid.getGrid().data("kendoGrid").dataItem(tr);
				createworkflow.setDrict();
			});
		});
		
		/** 
		 * 绑定删除任务按钮的click事件
		 */
		var remove = $("button[name='remove']");
		$.each(remove,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				createworkflow._currentRowData = grid.getGrid().data("kendoGrid").dataItem(tr);
				createworkflow.removeTasks();
			});
		});
	},
	
	/** 
	 * 设置审批角色Dialog
	 */
	setApprovalRole : function(){
		// 当前选中行
		var rowData = createworkflow._currentRowData;
		// 任务类型
		var taskType = rowData.type;
		if(taskType == "1"){
			layer.msg("调用子流程任务不用设置角色！");
			return;
		}
		if (rowData.id == "ru_task_start") {
			layer.msg("开始任务不用设置角色！");
			return;
		}
		if (rowData.id == "ru_task_end") {
			layer.msg("结束不用设置角色！");
			return;
		}
		//调用后台数据
		common.init("../auditrole/list","POST",function(result){
			//前次选择的数据
			var groupNo = rowData.groupNo;
			//重设多选框的数据
			createworkflow.setMultiSeleteData("approvalRoleSelete",result.value,groupNo);
			$('#approvalSet').modal('show');
		});
		common.do_submit();
	},
	
	/**
	 * 设置角色操作
	 */
	addApproalGroup : function(){
		// 行数据
		var rowData = createworkflow._currentRowData;
		//选择的审批组
		approvalGroupIds = [];
		approvalGroupNames = [];
		var dataItem = $("#approvalRoleSelete").data("kendoMultiSelect").dataItems();
		$.each(dataItem,function(i,item){
			approvalGroupIds.push(item.id);
			approvalGroupNames.push(item.name);
		});
		var groupName = "";
		for (var int = 0; int < approvalGroupNames.length; int++) {
			if (int == approvalGroupNames.length-1) {
				groupName += approvalGroupNames[int];
			}
			else{
				groupName += approvalGroupNames[int] + " ";
			}
		}
		// 设置行数据的角色编号
		rowData.groupNo = approvalGroupIds;
		// 设置行数据的组名称
		rowData.groupName = groupName;
		// 刷新grid
		createworkflow.refresh();
		// 关闭模式窗口
		$('#approvalSet').modal('hide');
		// 提示成功信息
		layer.msg(messageHelper.readMsgByTypeAndCode("process",false,"0"));
	},

	/**
	 * 添加任务到Grid中
	 */
	addTask : function(){
		// 定义绑定在Grid数据中的任务名称
		var gridTaskName = null;
		// 子流程Key
		var caKey = null;
		// 校验
		var taskType = $("#taskType").val();
		if (taskType == "0") {
			var taskName = $("#taskName").val();
			if(taskName == null || taskName == ""){
				layer.msg("任务名称不能为空");
				return;
			}
			gridTaskName = taskName;
		}
		else{
			var flowKey = $("#flow").val();
			if (flowKey == null || flowKey == "") {
				layer.msg("请选择子流程");
				return;
			}
			caKey = flowKey;
			gridTaskName = "调用子流程-"+$("#flow").find("option:selected").text();
		}
		// 关闭模态窗口
		$('#createTask').modal('hide');
		/**
		 * 设置数据
		 */
		// 设置任务名称等于空
		$("#taskName").val("");
		// 添加一行任务数据
		var rowData = {};
		// 任务ID
		rowData.id = createworkflow.getUUID();
		// 任务名称
		rowData.name = gridTaskName;
		// 任务类型
		rowData.type = taskType;
		// 子流程Key
		rowData.caKey = caKey;
		// 任务受理组编号
		rowData.groupNo = null;
		// 同意流向任务ID
		rowData.sourceNo = null;
		// 同意流向任务名称
		rowData.source = null;
		// 退回流向任务ID
		rowData.targetNo = null;
		// 退回流向任务名称
		rowData.target = null;
		// 增加到Grid
		grid.getGrid().data("kendoGrid").dataSource.add(rowData);
		// 刷新grid
		createworkflow.refresh();
	},
	
	/**
	 * 设置流向Dialog
	 */
	setDrict : function(){
		// 当前行数据
		var rowData = createworkflow._currentRowData;
		// 所有任务行
		var gridRows = grid.getAllRowsData();
		// 如果是开始行 那么隐藏退回选择框
		if (rowData.id == "ru_task_start") {
			$("#failNextStep").parent().parent().hide();
		}
		else if(rowData.id == "ru_task_end"){
			layer.msg("结束事件不需要设置流向");
			return;
		}
		else{
			$("#failNextStep").parent().parent().show();
		}
		datas = [];
		$.each(gridRows,function(i,item){
			// 排除自身
			if(rowData.id != item.id){
				if (item.id != "ru_task_start") {
					// 回选来源
					var data = {};
					data.value = item.id;
					data.text = item.name;
					datas.push(data);
				}
			}
			createworkflow.setMultiSeleteData("successNextStep",datas,rowData.sourceNo);
			createworkflow.setMultiSeleteData("failNextStep",datas,rowData.targetNo);
		});
		//显示Dialog
		$('#directionSet').modal('show');
	},
	
	/**
	 * 设置任务任务流向
	 */
	addDirection : function(){
		// 同意步骤编号
		var sourceNos = [];
		// 同意步骤名称
		var sources = [];
		var dataItems = $("#successNextStep").data("kendoMultiSelect").dataItems();
		$.each(dataItems,function(i,item){
			sourceNos.push(item.value);
			sources.push(item.text);
		});
		// 同意步骤编号
		var targetNos = [];
		// 同意步骤名称
		var targets = [];
		var dataItems = $("#failNextStep").data("kendoMultiSelect").dataItems();
		$.each(dataItems,function(i,item){
			targetNos.push(item.value);
			targets.push(item.text);
		});
		// 同意内容不能为空
		if(sourceNos.length == 0){
			layer.msg(messageHelper.readMsgByTypeAndCode("process",false,"1"));
			return;
		}
		// 同意步骤名称
		var sourceName = "";
		for (var int = 0; int < sources.length; int++) {
			if (int == sources.length-1) {
				sourceName += sources[int];
			}
			else{
				sourceName += sources[int] + " ";
			}
		}
		// 退回步骤名称
		var targetName = "";
		for (var int = 0; int < targets.length; int++) {
			if (int == sources.length-1) {
				targetName += targets[int];
			}
			else{
				targetName += targets[int] + " ";
			}
		}
		// 当前行数据
		var rowData = createworkflow._currentRowData;
		// 设置来源编码
		rowData.sourceNo = sourceNos;
		// 设置去向
		rowData.targetNo = targetNos;
		// 设置来源名称
		rowData.source = sourceName;
		// 设置去向名称
		rowData.target = targetName;
		// 刷新
		createworkflow.refresh();
		// 关闭模式窗口
		$('#directionSet').modal('hide');
		// 提示成功信息
		layer.msg(messageHelper.readMsgByTypeAndCode("process",false,"2"));
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/**
		 * 绑定添加任务时 选择任务类型的事件
		 */
		$("#taskType").on("change",function(){
			// 当前选择任务类型
			var taskType = $("#taskType").val();
			// 如果是任务
			if (taskType == "0") {
				// 隐藏流程选择
				$("#flow").parent().hide();
				// 显示任务名称
				$("#taskName").parent().show();
			}
			// 如果是子流程
			else{
				// 显示流程选择
				$("#flow").parent().show();
				// 隐藏任务名称
				$("#taskName").parent().hide();
			}
		})
		
		
		/** 绑定添加任务按钮的click事件*/
		$("#add").on("click",function(){
			// 隐藏流程选择
			$("#flow").parent().hide();
			$("#flow").val("");
			$("#taskType").val("0");
			// 显示任务名称
			$("#taskName").val("");
			$("#taskName").parent().show();
			$('#createTask').modal('show');
		});
		
		/** 绑定确认添加任务按钮的click事件*/
		$("#commitTask").on("click",function(){
			createworkflow.addTask();
		});
		
		/** 绑定提交按钮的click事件*/
		$("#submit").on("click",function(){
			createworkflow.createProcess();
		});
		
		/** 绑定返回按钮的click事件*/
		$("#back").on("click",function(){
			createworkflow.toPreviousPage();
		});
		
		/** 创建任务流向按钮的click事件**/
		$("#commitDirection").on("click",function(){
			createworkflow.addDirection();
		});
		
		/** 创建任务审批角色按钮的click事件**/
		$("#commitApproalGroup").on("click",function(){
			createworkflow.addApproalGroup();
		});
		
		$("#image").on("click",function(){
			// 流程名称不能为空
			var workFlowName = $("#name").val().trim();
			if(workFlowName == "" || workFlowName == null){
				layer.msg(messageHelper.readMsgByTypeAndCode("process",false,"3"));
				return;
			}
			var taskList = grid.getAllRowsData();
			// 任务个数不能为空
			if(taskList.length-1 == 0){
				layer.msg(messageHelper.readMsgByTypeAndCode("process",false,"4"));
				return;
			}
			/**
			 * 任务明细验证
			 * 1、任务组不能为空
			 * 2、任务步骤【同意】流向不能为空
			 * 3、任务不能独立
			 */
			$.each(taskList,function(i,item){
				// 审批组
				var groupNo = item.groupNo;
				if (item.id != "ru_task_start" && item.id != "ru_task_end" && item.type != "1") {
					if (groupNo == null) {
						layer.msg("任务："+item.name+"，未选择审批组");
						return;
					}
				}
				// 同意步骤流向
				var source = item.sourceNo;
				if (item.id != "ru_task_end") {
					if(source == null){
						layer.msg("任务："+item.name+"，未设置【同意】流向");
						return;
					}
				}
			});
			
			//数据合法，组织数据提交后台
			var tasks = JSON.stringify(taskList);
			common.add_param("processName",workFlowName);
			common.add_param("processKey","P123");
			var data = common.add_param("taskList",tasks);
			common.init("../workflow/ProcessLayout","POST",function(result){
				if (result.code == 0) {
					layer.msg(result.value);
					loading.close();
					return;
				}
				layer.open({
					  type: 2,
					  area: ['700px', '530px'],
					  fix: true, //不固定
					  maxmin: true,
					  content: '../workflow/flowLayout?path='+result.value
					});
				loading.close();
			});
			// 加载
			loading.init();
			common.do_submit(data);
		});
		
		$("#addEndTask").on("click",function(){
			// 添加一行任务数据
			var rowData = {};
			// 任务ID
			rowData.id = "ru_task_end";
			// 任务名称
			rowData.name = "结束";
			// 任务类型
			rowData.type = "0";
			// 任务受理组编号
			rowData.groupNo = null;
			// 同意流向任务ID
			rowData.sourceNo = null;
			// 同意流向任务名称
			rowData.source = null;
			// 退回流向任务ID
			rowData.targetNo = null;
			// 退回流向任务名称
			rowData.target = null;
			// 增加到Grid
			grid.getGrid().data("kendoGrid").dataSource.add(rowData);
			// 刷新
			createworkflow.refresh();
		});
	},


	/**
	 * 设置多选框的数据，并刷新
	 * controleName 组件ID
	 * data         多选框数据
	 * defaultValue 多选框默认值
	 */
	setMultiSeleteData : function(controleName,data,defaultValue){
		var dataSource = new kendo.data.DataSource({
			data:data
		});
		var multiselect = $("#"+controleName).data("kendoMultiSelect");
		multiselect.setDataSource(dataSource);
		multiselect.value(defaultValue);
		multiselect.refresh();
	},
	
	/**
	 * 临时任务ID
	 */
	getUUID : function(){
	    return "u_task_" + 'xxxxxxxxxxxx4xxxyxxxxxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
	        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
	        return v.toString(16);
	    });
	},
	

	/**
	 * 删除任务
	 */
	removeTasks : function(){
		//判断是否对设置流向有影响
		//删除行的ID
		var rowData = createworkflow._currentRowData;
		var taskId = rowData.id;
		var taskName = rowData.name;
		var isError = false;
		var tipMessage = "确认要删除吗？";
		// 所有行
		var gridRows = grid.getAllRowsData();
		// 遍历所有行
		$.each(gridRows,function(i,item){
			// 行的来源
			var sourceNo = item.sourceNo;
			// 行的去向
			var targetNo = item.targetNo;
			// 行名称
			var name = item.name;
			// 如果选择删除的行被这行使用
			if ($.inArray(taskId,sourceNo) > -1 || $.inArray(taskId,targetNo) > -1) {
				isError = true;
				tipMessage = "任务“"+taskName+"”被"+"任务“"+name+"”使用,不允许删除；请修改前置任务的流向后再进行删除！";
			}
		});
		if(isError){
			layer.msg(tipMessage);
			return;
		}
		//删除
		layer.confirm(tipMessage,function(index){
			// 删除数据源
			grid.getGrid().data("kendoGrid").dataSource.remove(createworkflow._currentRowData);
			// 刷新
			createworkflow.refresh();
			// 提示成功信息
			layer.msg(msg.REMOVE_SUCCESS);
		});
	},
	
	/**
	 * 跳转到上一页面
	 */
	toPreviousPage : function(){
		common.toPage("../workflow/init");
	},
	
	/**
	 * 创建工作流列表
	 */
	createProcess : function(){
		// 流程名称不能为空
		var workFlowId = $("#processType").val();
		var workFlowName = $("#processType").find("option:selected").text();
		if(workFlowId == "" || workFlowId == null){
			layer.msg(messageHelper.readMsgByTypeAndCode("process",false,"3"));
			return;
		}
		var taskList = grid.getAllRowsData();
		// 任务个数不能为空
		if(taskList.length-1 == 0){
			layer.msg(messageHelper.readMsgByTypeAndCode("process",false,"4"));
			return;
		}
		/**
		 * 任务明细验证
		 * 1、任务组不能为空
		 * 2、任务步骤【同意】流向不能为空
		 * 3、任务不能独立
		 */
		$.each(taskList,function(i,item){
			// 审批组
			var groupNo = item.groupNo;
			if (item.id != "ru_task_start" && item.id != "ru_task_end" && item.type != "1") {
				if (groupNo == null) {
					layer.msg("任务："+item.name+"，未选择审批组");
					return;
				}
			}
			// 同意步骤流向
			var source = item.sourceNo;
			if (item.id != "ru_task_end") {
				if(source == null){
					layer.msg("任务："+item.name+"，未设置【同意】流向");
					return;
				}
			}
		});
		
		//数据合法，组织数据提交后台
		var tasks = JSON.stringify(taskList);
		common.add_param("processName",workFlowName);
		common.add_param("processKey",workFlowId);
		common.add_param("xml",$("#submit").val());
		var data = common.add_param("taskList",tasks);
		common.init("../workflow/create_process","POST",function(result){
			if (result.code == 0) {
				layer.msg(msg.ADD_FAIL);
				loading.close();
				return;
			}
			layer.msg(msg.ADD_SUCCESS);
			loading.close();
			//调回用户列表显示页面
			createworkflow.toPreviousPage();
			loading.close();
		});
		// 加载
		loading.init();
		common.do_submit(data);
	},	
	
	/**
	 * 刷新 重新绑定事件
	 */
	refresh : function(){
		grid.refresh();
		createworkflow.bindTaskGrid();
	}
}