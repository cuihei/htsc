$(function(){
	file_attr.init();
})

var file_attr = {
	/**
	 * 初始化
	 */
	init : function(){
		//将资料类别元素设置为readonly
		file_attr.bindPageEvent();
	},
	
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定取消按钮的click事件*/
		$("#back").on("click",function(){
			//调回资料列表显示页面
			common.toPage("../datumFile/index");
			//执行刷新资料列表按钮
			parent.$("#refresh").click();
		});
	}
}