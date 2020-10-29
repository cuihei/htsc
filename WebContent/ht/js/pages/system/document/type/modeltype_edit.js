$(function(){
	modeltype_edit.init();
})

var modeltype_edit = {
	/**
	 * 初始化
	 */
	init : function(){
		modeltype_edit.bindPageEvent();
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
	 $("#submit").on("click",function(){
		 var modeltype = {};
		 // 获取模板类别Id
		 modeltype.id = $("#modeltypeId").val();
		 // 获取模板类别名称
		 modeltype.name = $("#name").val();
		 var modeltypeJson = JSON.stringify(modeltype);
		 var param = common.add_param("modeltype",modeltypeJson);
		 common.init("../mte/add","POST",function(result){
			//调回用户列表显示页面
			 if (result.code == 0) {
					if(result.value !="" && result.value != null){
						layer.msg(result.value);
						return;
					}else{
						layer.msg("保存失败！");
						return;
					}
				}
			 common.toPage("../mte/index");
			 //执行刷新用户列表按钮
			 parent.$("#refresh").click();
		 });
		 
		 common.do_submit(param);
	 });
	 
	 	// 绑定返回按钮事件
	   	 $("#back").on("click",function(){
	   		 //调回模板类型列表显示页面
	   		common.toPage("../mte/index");
	   		//执行刷新模板类型列表按钮
			 parent.$("#refresh").click();
   	 	})
	}
}