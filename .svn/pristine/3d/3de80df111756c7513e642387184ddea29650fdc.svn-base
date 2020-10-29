$(function(){
	catalog_view.init();
})

var catalog_view = {
	/**
	 * 初始化
	 */
	init : function(){
		catalog_view.bindPageEvent();
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定返回按钮的click事件*/
		 $("#back").on("click",function(){
			 var type = $("#type").val();
			 var year= $("year").val();
			 var categoryId = $("#categoryId").val();
			//调回用户列表显示页面
			 common.toPage("../detail/index?type="+type+"&categoryId="+categoryId+"&year="+year);
			//执行刷新用户列表按钮
			 parent.$("#refresh").click();
		 })
		 
		 $("img").error(function(){
			    $(this).hide();
			    $("#content").html("<h1>很抱歉，您查看的图片不存在...<h1>");
			});

	}
}
