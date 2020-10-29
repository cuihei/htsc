$(function(){
	notice_edit.init();
})

var notice_edit = {
	/**
	 * 初始化
	 */
	init : function(){
		notice_edit.bindPageEvent();
		$("#NoticeType option").each(function(){
			if($(this).text()=="其他通知"){
 				$(this).attr("selected","selected");
 			}
         });
		$("#NoticeType").attr("disabled","disabled");
	},
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		$("#submit").on("click",function(){
			var notice = {};
			notice.id = $("#noticeId").val();
			// 获取通知类型
			$("#NoticeType").removeAttr("disabled");
			notice.notice_Type = $("#NoticeType").val();
			// 获取通知标题
			notice.title = $("#title").val();
			// 获取通知描述
			notice.description = $("#description").val();
			// 获取人员id
			notice.user_id = $("#user_id").val();
			// 获取通知名称
			notice.user_name = $("#user_name").val();
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