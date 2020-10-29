var flowchart = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("flowchart");
		loading.init();
		try{
			flowchart.createProcessGrid();
			flowchart.requestProcessData();
			flowchart.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建资源列表列集合
	 */
	createProcessColumns : function(){
		grid.resetColumn();
		grid.addColumn("10%","version","流程版本");
		grid.addColumn("35%","name","流程名称");
		grid.addColumn("15%","flowImg","查看流程图",kendo.template($("#editTemplate").html()));
		return grid.addColumn("15%","taskConfig","任务配置",kendo.template($("#taskConfig").html()));
	},
	
	/**
	 * 创建资源列表
	 */
	createProcessGrid : function(){
		var columns = flowchart.createProcessColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestProcessData : function(){
		common.init("../workflow/list","POST",flowchart.bindProcessGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindProcessGrid : function(result){
		grid.bindData(result);
	},
	
	/** 绑定查看流程图按钮的click事件*/
	editFlowChart : function(){
		var tr = $(this).parent().parent();
		flowchart.toFlowImgPage(tr);
	},
	
	/** 绑定任务配置按钮的click事件*/
	taskConfig : function(){
		var tr = $(this).parent().parent();
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取流程ID
		var id = rowData.id;
		// 到任务配置页面
		flowchart.toTaskConfigPage(id);
	},
	
	/**
	 * 跳转到增加流程页面
	 */
	toProcessAddPage : function(){
		common.toPage("../workflow/process_init");  //跳转到流程创建页面
 	},
 	
 	/**
	 * 跳转到查看流程圖页面
	 */
	toFlowImgPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取用户ID
		var id = rowData.id;
		// 跳转到查看流程圖页面
		common.toPage("../workflow/flowImage?id="+id);
	},
	
	
	/**
	 * 跳转到分配用户页面
	 */
	toAllocUserPage : function(param){
		common.toPage("../workflow/distributeUserList?flowchart="+param);
	},
	
	/**
	 * 跳转到任务配置页面
	 */
	toTaskConfigPage : function(processDefId){
		common.toPage("../workflow/task/init?processDefId="+processDefId);
	},
	
	/**
	 * 流程发布
	 */
	publish : function(){
		var tr = $(this).parent().parent();
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取流程ID
		var id = rowData.id;
		// 获取流程key
		var key = rowData.key;
		common.init("../workflow/publish","POST",function(result){
			if (result.code == 0) {
				layer.msg(result.value);
				return;
			}
			layer.msg("发布成功");
		});
		common.add_param("id",id);
		var data = common.add_param("key",key);
		common.do_submit(data);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加流程按钮的click事件
		 */
		$("#add").on("click",function(){
			flowchart.toProcessAddPage();
		});
		
		$("#edit").on("click",function(){
			var rowDatas = grid.getSelectRowsData();
			if (rowDatas.length<=0) {
				layer.msg('未选择任何行!', {icon:5,time:1000});
				return;
			}
			var flows=[];
			$.each(rowDatas,function(i,item){
				var id = $("#flowchart").data("kendoGrid").dataItem(item).id;
				var flow = {};
				flow.id = id;
				flows.push(flow);
			});
			var param = JSON.stringify(flows);
			// 添加参数 @param 参数key；参数value
			flowchart.toAllocUserPage(param);
		});
	}
}