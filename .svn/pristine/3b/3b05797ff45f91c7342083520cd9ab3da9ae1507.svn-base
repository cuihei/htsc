$(function(){
	  associated.init();
})


var associated = {
		/**
		 * 初始化
		 */
		init : function(){
			// 初始化多选控件
			associated.initMultiselect();
			associated.setMultiSeleteData();
			
			
			 var processDefId = $("#processDefId").val();
			 var processDefKey = $("#processDefKey").val();
			 var taskDefId = $("#taskDefId").val();
				
			 $("#submit").on("click",function(){
				 var processTaskRelation = {};
				 processTaskRelation.processDefId=processDefId;
				 processTaskRelation.processDefKey=processDefKey;
				 processTaskRelation.taskDefId=taskDefId;
				 relationIds = "";
				 var dataItem = $("#name").data("kendoMultiSelect").dataItems();
				 $.each(dataItem,function(i,item){
					 relationIds += ','+item.taskDefId;
				 });
				 relationIds = relationIds.substring(1);
				 processTaskRelation.relationId = relationIds;
				 // 转化为JSON对象
				 var processTaskRelationJson = JSON.stringify(processTaskRelation);
				 var param = common.add_param("processTaskRelation",processTaskRelationJson);
				 common.init("../task/associatedEdit","POST",function(result){
					 //调回资源列表显示页面
					 common.toPage("../workflow/task/init?processDefId="+processDefId);
					 //执行刷新资源列表按钮
					 parent.$("#refresh").click();
				 });
				 common.do_submit(param);
		   });
			 
			/** 绑定取消按钮的click事件*/
			 $("#back").on("click",function(){
				//调回资源列表显示页面
				 common.toPage("../workflow/task/init?processDefId="+processDefId);
				//执行刷新资源列表按钮
				 parent.$("#refresh").click();
			 });
		},
		
		/**
		 * 初始化多选控件
		 */
		initMultiselect : function(){
			// 图层多选
			$("#name").kendoMultiSelect({
		        placeholder: "请选择关联任务名称",
		        dataTextField: "taskDefName",
		        dataValueField: "taskDefId",
		        autoBind: false
		    });
		},
		
		/**
		 * 设置多选框的数据，并刷新
		 */
		setMultiSeleteData : function(){
			var relationId = $("#relationId").val();//括号替换为空
			relationIds = [];
			for (var int = 0; int < relationId.split(",").length; int++) {
				relationIds.push(relationId.split(",")[int]);
			}
			var multiselect = $("#name").data("kendoMultiSelect");
			multiselect.value(relationIds);
			multiselect.refresh();
		}
		
	}