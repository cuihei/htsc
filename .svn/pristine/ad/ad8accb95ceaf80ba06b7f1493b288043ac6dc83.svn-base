$(function(){
	edit_scaleControl.init();
})

var edit_scaleControl = {
	
	/**
	 * 初始化
	 */
	init : function(){
		edit_scaleControl.bindPageEvent();
		
	},
	
	/**
	 * 绑定页面按钮事件
	 */
	bindPageEvent : function(){
		/**
		 * 确定按钮的点击事件
		 */
		$("#submit").on("click",function(){
			var scaleControl = {};
			// 设置Id
			scaleControl.id = $("#id").val();
			// 设置旧比例尺
			scaleControl.oldScale = $("#oldScale").val();
			// 设置标准比例尺
			scaleControl.standardScale = $("#standardScale").val();
			// 转成JSON
			var scaleControlJson = JSON.stringify(scaleControl);
			var param = common.add_param("scaleControl",scaleControlJson);
			
			common.init("../scaleControl/add","POST",function(result){
				 if(result.code != 1){
					if(result.value != "" || result.value != null){
						layer.msg(result.value);
						return;
					}
					layer.msg("保存失败！");
					return;
				 }
				// 返回比例尺管理列表页面
				common.toPage("../scaleControl/index");
				// 执行刷新按钮的点击事件
				parent.$("#refresh").click();
			});
			common.do_submit(param);
		});
		
		/**
		 * 返回按钮的点击事件
		 */
		$("#back").on("click",function(){
			// 返回比例尺管理列表页面
			common.toPage("../scaleControl/index");
			// 执行刷新按钮的点击事件
			parent.$("#refresh").click();
		});
	}
}