$(function(){
	 var processDefId = $("#processDefId").val();
	 var processDefKey = $("#processDefKey").val();
	 var taskDefId = $("#taskDefId").val();
	
	 $("#submit").on("click",function(){
		 var processStatus = {};
		 processStatus.processDefId=processDefKey;
		 processStatus.taskDefId=taskDefId;
		 processStatus.status = $("#status option:selected").val();
		 // 转化为JSON对象
		 var processStatus = JSON.stringify(processStatus);
		 var param = common.add_param("processStatus",processStatus);
		 common.init("../task/statusEdit","POST",function(result){
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
	 
	 /** 绑定状态按钮的click事件*/
	 $("#statusType").on("change",function(){
		 var statusTypeId = $("#statusType").val();
		 $.ajax({
			 url:'../task/getChildeStatus?typeId='+statusTypeId,
			 type:'POST',
			 success:function(data){
				 var length = data.length;
				 if(length!=0){
					 $("#status").empty();
					 $("#status").append("<option value=''>--请选择--</option>");
					 for(var i = 0;i<length;i++	){
						 $("#status").append("<option value="+data[i]['id']+">"+data[i]['value']+"</option>");
					 }
				 }
			 },
			 error:function(){
			 }
		 });
	 });
})