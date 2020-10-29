var splitId="";
var taskBookId="";
$(function(){
	tasksplit.init();
})

/** 绑定编辑用户窗口按钮的click事件*/
function splitPage(obj) {
	var tr = $(obj).parent().parent();
	// 如果不在流程内 不允许拆分
	var processInstId = $("#processInstId").val().trim();
	if (processInstId == null || processInstId == "") {
		layer.msg("请您到待办任务里进行拆分操作！");
		return;
	}
	var rowData = grid.getSelectRowDataByRow(tr);
	splitId = rowData.id;
	taskBookId = rowData.taskBook.id;
	// 获取拆分类型
	var type = $("#flag").val();
	// 如果是改正通告类型则自动拆分为三条
	if("TASK_BOOK_CORRECTION_NOTICE" == type){
		tasksplit.cornoticeSubmit();
	}
	// 是其他类型则进行选择拆分
	else{
		$("#hiddenButton").click();
	}
}

var tasksplit = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("taskSplit");
		loading.init();
		try{
			tasksplit.createGrid();
			tasksplit.requestData();
			tasksplit.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建区域管理列表列集合
	 */
	createColumns : function(){
		grid.resetColumn();
		grid.addColumn("150px","taskBook.taskbookNo","任务书序号");
		grid.addColumn("160px","taskBook.taskbookName","编绘任务书名称");
		grid.addLockedColumn("120px","plan.mapNo","图号");
		grid.addLockedColumn("120px","plan.mapName","图名");
		grid.addColumn("120px","plan.scale","比例尺 1:");
		grid.addColumn("150px","plan.deliverTime","计划汇交日期","#= plan.deliverTime ? kendo.toString(new Date(plan.deliverTime), 'yyyy-MM-dd') : '' #");
		grid.addColumn("150px","plan.planMonth","所属月份");
		grid.addColumn("120px","plan.measurementPeriod","基测周期");
		grid.addColumn("120px","plan.revision","版次");
		grid.addColumn("100px","plan.discription","说明");
		return grid.addColumn("100px","handle","拆分",kendo.template($("#splitTemplate").html()));
	},
	
	/**
	 * 创建区域管理列表
	 */
	createGrid : function(){
		var columns = tasksplit.createColumns();
		grid.createGrid(columns);
		var gridInst = grid.getGrid().data("kendoGrid");
		gridInst.lockColumn("selected");
		gridInst.lockColumn("rowNumber");
		gridInst.reorderColumn(0, gridInst.columns[2]);
		gridInst.reorderColumn(1, gridInst.columns[3]);
	},
	
	/**
	 * 发送数据请求
	 */
	requestData : function(){
		common.add_param("flag",flag.value);
		var param = common.add_param("taskBookId",$("#taskBookId").val());
		common.init("../tasksplit/list","POST",tasksplit.bindGrid);
		common.do_submit(param);
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindGrid : function(result){
		grid.bindData(result);
		/** 绑定拆分按钮的click事件*/
		var splitBtns = $("button[name='split']");
		$.each(splitBtns,function(i,item){
			$(item).on("click",function(){
				
			});
		});
	},
	
	/**
	 * 改正通告自动拆分
	 */
	cornoticeSubmit : function(){
		layer.confirm("改正通告将自动拆分成三条，确定拆分并提交任务吗？",function(){
			 var taskSplit = {};
			 taskSplit.taskType = "CORRECTION_NOTICE_TEMPLATE,CORRECTION_NOTICE_TEMPLATE_EDIT,CORRECTION_NOTICE_SOURCE_DATA_SMALL_CORRECTION,";
			 taskSplit.typeValue= "源数据小改正,改正通告编辑,改正通告模板编辑,";
			 taskSplit.taskBookPlanId = splitId;
			 taskSplit.taskBookId = taskBookId;
			 var splitJson = JSON.stringify(taskSplit);
			 common.add_param("split",splitJson);
			 common.add_param("processInstId",$("#processInstId").val());
			 var param = common.add_param("taskId",$("#taskId").val());
			 common.init("../tasksplit/split","POST",function(result){
				 if (result.code == 0) {
						layer.msg(result.value);
						return;
				 }
				 layer.msg("拆分成功");
				 history.back();
			 });
			 common.do_submit(param);
		})
	},
	
	/**
	 * 拆分确定按钮
	 */
	toSubmit : function(){
		var taskType="";
		var typeValue="";
		var checks = document.getElementsByName("splitType");
		for(var i in checks){
			if(checks[i].checked){
				taskType+=checks[i].id+",";
				typeValue+=checks[i].value+",";
			}
		}
		 var taskSplit = {};
		 taskSplit.taskType = taskType;
		 taskSplit.taskBookPlanId =splitId;
		 taskSplit.typeValue= typeValue;
		 taskSplit.taskBookId = taskBookId;
		 if( taskType==""){
			 layer.msg("请选择拆分类型");
			 return;
		 }
		 var splitJson = JSON.stringify(taskSplit);
		 common.add_param("split",splitJson);
		 var param = common.add_param("processInstId",$("#processInstId").val());
		 var param = common.add_param("taskId",$("#taskId").val());
		 common.init("../tasksplit/split","POST",function(result){
			 if (result.code != 1) {
					layer.msg(result.value);
					return;
				}
			 layer.msg("拆分成功");
			 $("#windowClose").click();
			 history.back();
		 });
		 common.do_submit(param);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定click事件
		 */
		$("#submit").on("click",function(){
			tasksplit.toSubmit();
		});
		
		$("#back").on("click",function(){
			$("#windowClose").click();
		});
	}
}
