$(function(){
	plan.init();
})

function editPlan(obj) {
	var tr = $(obj).parent().parent();
	plan.toPlanEditPage(tr);
}



var plan = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("plan");
		loading.init();
		try{
			plan.createGrid($("#taskDefId").val());
			plan.requestData();
			plan.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
		loading.close();
	},
	
	tijiao: function(){
		 var processPlan = {};
		 
		 processPlan.id = $("#planId").val();
		 var taskDefId = $("#taskDefId").val();
		 if(taskDefId.indexOf("zhijian")!=-1){
			 processPlan.remark = $("#zj_remark").val();
		 }else if(taskDefId.indexOf("shending")!=-1){
			 processPlan.remark = $("#sd_remark").val();
		 }
		 processPlan.taskDefId = taskDefId;
		 processPlan.processInstId = $("#processInstId").val();
		 processPlan.taskId = $("#taskInstId").val();
		 processPlan.parentProcessInstId = $("#parentProcessInstId").val();
		 
		 var processPlanJson = JSON.stringify(processPlan);
		 var param = common.add_param("processPlan",processPlanJson);
		 common.init("../formValue/addProcessPlan","POST",plan.editSuccess);
		 common.do_submit(param);
	},
	
	/**
	 * 修改或新增成功
	 * */
	editSuccess : function(result){
		layer.msg('保存成功!',{icon:1,time:6000});
		$("#myModal").modal('hide');
		$("#zj_remark").val("");
		$("#sd_remark").val("");
		plan.requestData();
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(result){
		layer.close(1);
		layer.msg('删除成功');
		plan.requestData();
	},
	
	removePlans : function(){
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length<=0) {
			layer.msg('未选择任何行!', {icon:5,time:1000});
			return;
		}
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var processPlans = [];
			$.each(rowDatas,function(i,item){
				var id = $("#plan").data("kendoGrid").dataItem(item).id;
				var processPlan = {};
				processPlan.id = id;
				processPlans.push(processPlan);
			});
			var param = JSON.stringify(processPlans);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("processPlan",param);
			common.init("../formValue/removeProcessPlan","POST",plan.removeSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
	},
	
	/**
	 * 创建缺陷列表
	 */
	createGrid : function(taskDefId){
		var columns = plan.createColumns(taskDefId);
		grid.createGrid(columns);
	},
	
	createColumns : function(taskDefId){
		grid.resetColumn();
		var isRuTask = $("#isRuTask").val();
		if(taskDefId.indexOf("zhijian")!=-1){
			grid.addColumn("200px","remark","质检记录");
		}else if(taskDefId.indexOf("shending")!=-1){
			grid.addColumn("200px","remark","审定记录");
		}else{
			grid.addColumn("200px","remark","记录");
		}
		return grid.addColumn("5%","handle","操作",kendo.template($("#editTemplate").html()));
		
	},
	
	
	/**
	 * 发送数据请求
	 */
	requestData : function(){
		common.add_param("taskDefId",$("#taskDefId").val());
		var param = common.add_param("parentProcessInstId",$("#parentProcessInstId").val());
		common.init("../formValue/getByProcess","POST",plan.bindGrid);
		common.do_submit(param);
	},
	
	/**
	 * 发送数据请求
	 */
	ZhiJianPlans : function(){
		common.add_param("taskDefId","u_task_plan_zhijian");
		var param = common.add_param("parentProcessInstId",$("#parentProcessInstId").val());
		common.init("../formValue/getByProcess","POST",plan.bindGrid);
		common.do_submit(param);
	},
	
	/**
	 * 发送数据请求
	 */
	ShenDingPlans : function(){
		common.add_param("taskDefId","u_task_plan_shending");
		var param = common.add_param("parentProcessInstId",$("#parentProcessInstId").val());
		common.init("../formValue/getByProcess","POST",plan.bindGrid);
		common.do_submit(param);
	},
	
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 跳转到用户编辑页面
	 */
	toPlanEditPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		$("#planId").val(rowData.id);
		var taskDefId = $("#taskDefId").val();
		if(taskDefId.indexOf("zhijian")!=-1){
			$("#zj_remark").val(rowData.remark);
		}else if(taskDefId.indexOf("shending")!=-1){
			$("#sd_remark").val(rowData.remark);
		}
		$("#myModal").modal('show');
	},
	
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		$("#add").on("click",function(){
			$("#myModal").modal('show');
		});
		
		$("#remove").on("click",function(){
			plan.removePlans();
		});
		
		$("#zhijian").on("click",function(){
			plan.createGrid("u_task_plan_zhijian");
			plan.ZhiJianPlans();
		});
		
		$("#shending").on("click",function(){
			plan.createGrid("u_task_plan_shending");
			plan.ShenDingPlans();
		});
		
		$("#back").on("click",function(){
			history.back(-1);
		});
		
		$("#refresh").on("click",function(){
			window.location.reload();
		});
	},
}

