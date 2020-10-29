$(function(){
	workspace.init();
})
var workspace = {
	/**
	 * 初始化
	 */
	init : function(){
		workspace.workspaceRead();
	},
	
	/**
	 * 标记为已读点击事件
	 */
	workspaceRead : function(){
		var editBtns = $("a[name='read']");
		$.each(editBtns,function(i,item){
			$(item).on("click",function(){
				$(item).css("color","red");
				var tr = $(item).parent().parent();
				workspace.modefiyRead(tr);
			});
		});
	},
	
	/**
	 * 更新阅读状态为已读
	 */
	modefiyRead : function(tr){
		// 获取用户通知id
		var userNoticeId = tr.children("input").val();
		var userNotices = [];
		var userNotice = {};
		userNotice.id = userNoticeId;
		userNotices.push(userNotice);
		// 转成JSON
		var param = JSON.stringify(userNotices);
		common.init("../workSpace/modify?userNoticeId="+param,"POST",null);
		common.do_submit();
	}
}



