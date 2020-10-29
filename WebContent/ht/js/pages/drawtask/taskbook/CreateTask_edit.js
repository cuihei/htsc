
$(function(){
	childTaskEdit.init();
	$("#scale").attr("onkeyup","value=(parseInt((value=value.replace(/[^\\d]/g,''))==''?'0':value,10))");
	$("#scale").attr("onafterpaste","value=(parseInt((value=value.replace(/[^\\d]/g,''))==''?'0':value,10))");
})

var childTaskEdit ={
	/**
	 * 初始化
	 */
	init : function(){
		$("#taskType").change(function(){
			var taskType = $("#taskType").val();
			if(taskType == "" || taskType == null || taskType.indexOf("请选择")>-1){
				   var options = '<option value="">--请选择--</option>';
				   $("#childTaskType").html(options);
				   $("#scale").parent().parent().parent().css("display","");
				   return;
			}
			if(taskType=="TASK_BOOK_CORRECTION_NOTICE"||taskType=="TASK_BOOK_SMALL_CORRECTION"){
				$("#scale").parent().parent().parent().css("display","none");
			}else{
				$("#scale").parent().parent().parent().css("display","");
			}
			common.init("../createTask/changeTask?taskType="+taskType,"POST",function(result){
				var data = result.value;
				var multiselect = $("#childTaskType").data("kendoMultiSelect");
				if(multiselect != null){
					multiselect.setDataSource(data);
				}else{
					childTaskEdit.initMultiselect(data);
				}
				
				
			})
			common.do_submit();
		});
		childTaskEdit.bindPageEvent();
	},
	
	/**
	 * 初始化多选控件
	 */
	initMultiselect : function(data){
	
		$("#childTaskType").kendoMultiSelect({
	        placeholder: "请选择子任务类型",
	        dataTextField: "value",
	        dataValueField: "code",
	        autoBind: false,
	        dataSource:data
	    });
	},
	
	//保存回调函数
	editSuccess : function(result){
		if(result.code != 1){
			if(result.value !="" && result.value != null){
				layer.msg(result.value);
				return;
			}
			layer.msg("保存失败！");
			return;
		}
		layer.msg("保存成功！");
		 //返回目录主页面
		common.toPage("../createTask/index");
	},
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定提交按钮的click事件*/
		$("#submit").on("click",function(){
			var task={};
			var taskType = $("#taskType").val();
			if(taskType == "" || taskType == null || taskType.indexOf("请选择")>-1){
				layer.msg("请选择任务类型！");
			   return;
			}
			
			//选择子任务类型
			childTaskTypeIds = "";
			childTaskTypeNames = "";
			var dataItem = $("#childTaskType").data("kendoMultiSelect").dataItems();
			$.each(dataItem,function(i,item){
				childTaskTypeIds += ','+item.code;
				childTaskTypeNames += ','+item.value;
			});
			childTaskTypeIds = childTaskTypeIds.substring(1);
			childTaskTypeNames = childTaskTypeNames.substring(1);
			
			if(childTaskTypeIds == "" || childTaskTypeIds == null ){
				layer.msg("请选择子类型！");
			   return;
			}
			var taskName = $("#taskName").val();
			if(taskName == null ||taskName == ""){
				layer.msg("请填写任务名！");
				return;
			}
			task.taskType = taskType;
			task.childTaskType = childTaskTypeIds;
			task.taskName = taskName;
			task.mapNo = $("#mapNo").val();
			task.scale = "1:"+$("#scale").val();
			task.revision = $("#revision").val();
			task.parentTaskbookId =  $("#id").val();
			//task.yearMonth = $("#yearMonth").val();
			var taskJson = JSON.stringify(task);
			var param = common.add_param("task", taskJson);
			common.init("../createTask/add","POST",childTaskEdit.editSuccess);
			common.do_submit(param);
		});
		
		/** 绑定返回按钮的click事件*/
		 $("#back").on("click",function(){
			 common.toPage("../createTask/index");
		 })
	}
}
