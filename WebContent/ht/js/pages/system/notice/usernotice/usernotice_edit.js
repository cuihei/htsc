$(function(){
	usernotice_edit.init();
})

var usernotice_edit = {
	/**
	 * 初始化
	 */
	init : function(){
		usernotice_edit.bindPageEvent();
	},
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		$("#submit").on("click",function(){
			 var notice = {};
			 notice.id = $("#id").val();
			 // 获取通知标题
			 notice.title = $("#user_id").val();
			 // 获取通知描述
			 notice.description = $("#notice_id").val();
			 // 获取是否已读
			 var noticeJson = JSON.stringify(notice);
			 var param = common.add_param("notice",noticeJson);
			 common.init("../notice/add","POST",function(result){
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
				 common.toPage("../notice/index");
				 //执行刷新用户列表按钮
				 parent.$("#refresh").click();
			 });
			 common.do_submit(param);
	});

		/** 绑定取消按钮的click事件*/
		 $("#back").on("click",function(){
			//调回用户列表显示页面
			 common.toPage("../notice/index");
			//执行刷新用户列表按钮
			 parent.$("#refresh").click();
		 })
	}
}