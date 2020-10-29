$(function(){
	employee_img.init();
})

var employee_img = {
	/**
	 * 初始化
	 */
	init : function(){
		employee_img.bindPageEvent();
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定返回按钮的click事件*/
		 $("#back").on("click",function(){
			//调回用户列表显示页面
			 common.toPage("../user/index");
			//执行刷新用户列表按钮
			 parent.$("#refresh").click();
		 })
		 
		 $("userImg").error(function(){
			    $(this).hide();
			    $("#content").html("<h1>很抱歉，您查看的图片不存在...<h1>");
			});
	}
}