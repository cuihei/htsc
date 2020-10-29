$(function(){
	formValue_img.init();
})

var formValue_img = {
	/**
	 * 初始化
	 */
	init : function(){
		formValue_img.bindPageEvent();
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定返回按钮的click事件*/
		 $("#back").on("click",function(){
			//调回用户列表显示页面
			history.back();
		 })
		 
		 $("#formImg").error(function(){
			    $(this).hide();
			    $("#content").html("<h1>很抱歉，您查看的图片不存在...<h1>");
		 });
	}
}