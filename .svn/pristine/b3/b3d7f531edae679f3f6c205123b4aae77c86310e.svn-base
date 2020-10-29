$(function(){
	returnhis.init();
})

var returnhis = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("returnHis");
		loading.init();
		try{
			returnhis.createreturnhisGrid();
			returnhis.requestreturnhisData();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 创建借阅记录列表
	 */
	createreturnhisGrid : function(){
		var columns = returnhis.createreturnhisColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 构建借阅记录集合
	 */
	createreturnhisColumns : function(){
		grid.resetColumn();
		grid.addColumn("30%","bookName","图书名称");
		grid.addColumn("30%","returnPerson","借阅人员");
		grid.addColumn("30%","returnNo","归还数量");
		return grid.addColumn("20%","returnDate","归还时间");
	},
	
	/**
	 * 发送数据请求
	 */
	requestreturnhisData : function(){
		common.init("../returnbook/list","POST",returnhis.bindreturnhisGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindreturnhisGrid : function(result){
		grid.bindData(result);
	}
	
}