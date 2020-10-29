$(function(){
	application_edit.init();
})

var application_edit = {
	/**
	 * 初始化
	 */
	init : function(){
		$("#appParentId").attr("disabled","disabled")//将元素设置为readonly
		application_edit.bindPageEvent();
		application_edit.bindTreeView();
	},
	
	/**
	 * 绑定树视图
	 */
	bindTreeView : function(){
		common.init("../app/appChilds", "POST", function(result) {
			treeView.init("select", function(e) {
				if (e.node != null) {
					var data = treeView.getData(e.node);
					var parentName = data.name;
					$("#appParentName").val(parentName);
				}
				
			}, result.value);
			treeView.bindTree();
		});
		common.do_submit();
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定提交增加资源按钮的click事件*/
		 $("#submit").on("click",function(){
			 var app = {};
			 app.id = $("#appId").val();
			// 获取父资源Id参数
			 app.appParentId = $("#appParentId").val();
			 if(app.appParentId == "--请选择--"){
				 app.appParentId = "";
			 }
			 // 获取资源编号参数
			 app.appCode = $("#appCode").val();
			 if(app.appCode == ""){
				 layer.msg("请输入资源编号");
				 return;
			 }
			 // 获取资源名称
			 app.appName = $("#appName").val();
			 if(app.appName == ""){
				 layer.msg("请输入资源名称");
				 return;
			 }
//			 // 获取资源编号参数
			 app.appUrl = $("#appUrl").val();
			 // 获取资源名称
			 app.appType = $("#appType").val();
			 if(app.appType == ""||app.appType== "--请选择--"){
				 layer.msg("请输入资源类型");
				 return;
			 }
			 app.appImg = $("#appImg").val();
			 app.appContent = $("#appContent").val();
			 //将数组转化成JSON数据
			 var appJson = JSON.stringify(app);
			 var param = common.add_param("application",appJson);
			 common.init("../app/edit","POST",function(result){
				 if(result.code == 0){
					 layer.msg(result.value);
					 return;
				 }else{
					 //调回资源列表显示页面
					 common.toPage("../app/index");
					 //执行刷新用户列表按钮
					 parent.$("#refresh").click();
				 }
				
			 });
			 common.do_submit(param);
		 });

		 /** 绑定取消按钮的click事件*/
		 $("#back").on("click",function(){
			//调回资源列表显示页面
			 common.toPage("../app/index");
			//执行刷新资源列表按钮
			 parent.$("#refresh").click();
		 });
		 
		 /** 绑定选择父资源确定按钮的click事件*/
		 $("#importSubmit").on("click",function(){
			 $("#appParentId option").each(function(){
				if($(this).text()==$("#appParentName").val()){
     				$(this).attr("selected","selected");
     			}else{
     				if($("#appParentName").val()==""){
				 		if($(this).text()=="根节点"){
				 			$(this).attr("selected","selected");
				 		}
  					}
     			}
	         });
			 $("#myModal").hide();
		 });
	}
}