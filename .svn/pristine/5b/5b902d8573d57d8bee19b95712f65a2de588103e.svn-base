$(function(){
	seeTaskbill.init();
})

var seeTaskbill = {
	/**
	 * 初始化
	 */
	init : function(){
		seeTaskbill.bindPageEvent();
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		 /** 绑定取消按钮的click事件*/
		 $("#back").on("click",function(){
			//调回用户列表显示页面
			 window.history.go(-1);
			//执行刷新用户列表按钮
			 parent.$("#refresh").click();
		 })
	}
}
