$(function(){
	childTaskEdit.init();
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
			common.init("../createTask/changeTask?taskType="+taskType,"POST",function(result){var data = result.value;
				$("#childTaskType").empty();
				var options = '<option value="">--请选择--</option>'
				if(data!=undefined){
					if(data.length>0){
						$.each(data,function(index,element){
							options+='<option value="'+element.code+'">'+element.value+'</option>';
						});
						$("#childTaskType").html(options);
					}
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
		common.toPage("../createTask/child_task_init");
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
			var childTaskType = $("#childTaskType").val();
			if(childTaskType == "" || childTaskType == null || childTaskType.indexOf("请选择")>-1){
				layer.msg("请选择子类型！");
			   return;
			}
			var taskName = $("#taskName").val();
			if(taskName == null ||taskName == ""){
				layer.msg("请填写任务名！");
				return;
			}
			task.taskType = taskType;
			task.childTaskType = childTaskType;
			task.taskName = taskName;
			task.mapNo = $("#mapNo").val();
			task.scale = $("#scale").val();
			task.revision = $("#revision").val();
			task.id =  $("#id").val();
			//task.yearMonth = $("#yearMonth").val();
			var taskJson = JSON.stringify(task);
			var param = common.add_param("task", taskJson);
			common.init("../createTask/edit","POST",childTaskEdit.editSuccess);
			common.do_submit(param);
		});
		
		/** 绑定返回按钮的click事件*/
		 $("#back").on("click",function(){
			 common.toPage("../createTask/child_task_init");
		 })
	}
}
