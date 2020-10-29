$(function(){
	$("#submit").on("click",function(){
		 var syslog = {};
		 // 获取日志id参数
		 syslog.id = $("#id").val();
	 	 // 获取访问者id参数
		 syslog.handleId = $("#handleId").val();
		 // 获取访问者ip参数
		 syslog.handleIp = $("#handleIp").val();
		 // 获取操作行为参数
		 syslog.handleBehavior = $("#handleBehavior").val();
		 // 获取操作结果参数
		 syslog.handleResult = $("#handleResult").val();
		 // 获取操作时间参数
		 var ht = $("#handleTime").val();
		 syslog.handleTime = new Date(ht.replace(/-/,"/"));
		 //将数组转化成JSON数据
		 var syslogJson = JSON.stringify(syslog);
		 var param = common.add_param("syslog",syslogJson);
		 common.init("../syslog/add","POST",function(result){
			 layer.msg("添加成功！");
			 //调回用户列表显示页面
			 common.toPage("../syslog/index");
			 //执行刷新用户列表按钮
			 parent.$("#refresh").click();
		 });
		 common.do_submit(param);
	 })
	 
	 $("#back").on("click",function(){
		 //调回用户列表显示页面
		 common.toPage("../syslog/index");
		 //执行刷新用户列表按钮
		 parent.$("#refresh").click();
	 })
})