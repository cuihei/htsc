$(function(){
	usernoticeview.init();
})

var usernoticeview = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("usernoticeview");
		loading.init();
		try{
			usernoticeview.createUserNoticeViewGrid();
			usernoticeview.requestUserNoticeViewData();
			usernoticeview.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建人员通知关系集合
	 */
	createUserNoticeViewColumns : function(){
		grid.resetColumn();
		grid.addColumn("25%","userName","通知接收人员");
		grid.addColumn("25%","title","通知标题");
		grid.addColumn("25%","description","通知描述");
		grid.addColumn("25%","isRead","是否已读");
		return grid.addColumn("25%","releaseTime","发布时间"); 
	},
	
	/**
	 * 创建人员通知关系列表
	 */
	createUserNoticeViewGrid : function(){
		var columns = usernoticeview.createUserNoticeViewColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
 	 */
	requestUserNoticeViewData : function(){
		var flag = $("#flag").val();
		common.init("../unv/list?flag="+flag,"POST",usernoticeview.bindUserNoticeViewGrid);
		common.do_submit();
	},
		
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindUserNoticeViewGrid : function(result){
		grid.bindData(result);
		/** 绑定编辑用户窗口按钮的click事件*/
		var editBtns = $("button[name='editUserNoticeView']");
		$.each(editBtns,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				employee.toUserEditPage(tr);
			});
		});
		
		/** 绑定取消按钮的click事件*/
		$("#back").on("click",function(){
			//调回用户列表显示页面
			common.toPage("../notice/index");
			//执行刷新用户列表按钮
			parent.$("#refresh").click();
		});
	},
}