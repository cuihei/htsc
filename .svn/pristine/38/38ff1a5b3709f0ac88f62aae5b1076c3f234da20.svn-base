$(function(){
	catalog_edit.init();
})

var catalog_edit = {
	/**
	 * 初始化
	 */
	init : function(){
		catalog_edit.bindPageEvent();
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		$("#submit").on("click",function(){
			 var type = {};
			 type.id = typeId.value;
			 // 获取类型名称参数
			 type.value = $("#value").val();
			 type.code = $("#code").val();
			 if(type.code==""){
				 layer.msg("请输入目录编码");
				 return;
			 }
			 if(type.value==""){
				 layer.msg("请输入目录名称");
				 return;
			 }
			 var typeJson = JSON.stringify(type);
			 var param = common.add_param("type",typeJson);
			 common.init("../cta/edit","POST",function(result){
				 if(result.code != 1){
					 if(result.value !="" && result.value != null){
							layer.msg(result.value);
							return;
					 }else{
						 layer.msg("数据已存在！");
						 return;
					 }
				 }
				 layer.msg("操作成功!");
				//调回用户列表显示页面
				 common.toPage("../cta/index");
			 });
			 common.do_submit(param);
		});
		
		/** 绑定取消按钮的click事件*/
		 $("#back").on("click",function(){
			//调回用户列表显示页面
			 common.toPage("../cta/index");
			//执行刷新用户列表按钮
			 parent.$("#refresh").click();
		 })
	}
}