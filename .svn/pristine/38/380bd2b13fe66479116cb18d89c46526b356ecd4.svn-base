$(function(){
	usernotice.init();
})

var usernotice = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("usernotice");
		loading.init();
		try{
			usernotice.createUserNoticeGrid();
			usernotice.requestUserNoticeData();
			usernotice.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建用户通知关系集合
	 */
	createUserNoticeColumns : function(){
		grid.resetColumn();
		grid.addColumn("10%","id","模板类型编号");
		grid.addColumn("25%","user_name","用户名称");
		return grid.addColumn("30%","notice_id","通知名称");
	},
	
	/**
	 * 创建用户通知关系列表
	 */
	createUserNoticeGrid : function(){
		var columns = usernotice.createUserNoticeColumns();
		grid.createGrid(columns);
	},
	
	/**
 	 * 发送数据请求
	 */
	requestUserNoticeData : function(){
		common.init("../une/list","POST",usernotice.bindUserNoticeGrid);
		common.do_submit();
	},
	
	bindUserNoticeGrid : function(result){
		grid.bindData(result);
	}
}