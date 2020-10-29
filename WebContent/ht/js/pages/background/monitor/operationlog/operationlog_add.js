$(function(){
	//提交按钮绑定事件
	$("#submit").on("click",function(){
		 var syslogOperation = {};
		 // 获取日志id参数
		 syslogOperation.id = $("#id").val();
		// 获取操作者id参数
		 syslogOperation.operatorId = $("#operatorId").val();
		// 获取操作者ip参数
		 syslogOperation.operatorIp = $("#operatorIp").val();
		// 获取操作行为参数
		 syslogOperation.operationBehavior = $("#operationBehavior").val();
		// 获取操作结果参数
		 syslogOperation.operationResult = $("#operationResult").val();
		// 获取操作对象参数
		 syslogOperation.operationObject = $("#operationObject").val();
		// 获取操作时间参数
		 var ot = $("#operationTime").val();
		 syslogOperation.operationTime = new Date(ot.replace(/-/,"/"));
		//将数组转化成JSON数据
		 var syslogOperationJson = JSON.stringify(syslogOperation);
		 var param = common.add_param("syslogOperation",syslogOperationJson);
		 common.init("../so/add","POST",function(result){
			 layer.msg(result.value);
			 //调回用户列表显示页面
			 common.toPage("../so/index");
			 //执行刷新用户列表按钮
			 parent.$("#refresh").click();
		 });
		 common.do_submit(param);
	 })
	 
	 //取消按钮绑定事件
	 $("#back").on("click",function(){
		 //调回用户列表显示页面
		 common.toPage("../so/index");
		 //执行刷新用户列表按钮
		 parent.$("#refresh").click();
	 })
})