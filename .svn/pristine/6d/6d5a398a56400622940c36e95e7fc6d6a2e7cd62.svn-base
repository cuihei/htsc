$(function(){
	formPd.init();
})

var formPd = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("formPd");
		loading.init();
		try{
			formPd.createUserGrid();
			formPd.requestUserData();
			formPd.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建资源列表列集合
	 */
	createUserColumns : function(){
		grid.resetColumn();
		grid.addColumn("30%","code","基础数据编码");
		grid.addColumn("30%","value","基础数据名称");
		return grid.addColumn("30%","typeName","类别");
	},
	
	/**
	 * 创建资源列表
	 */
	createUserGrid : function(){
		var columns = formPd.createUserColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestUserData : function(){
		common.init("../formProp/baseDataList","POST",formPd.bindUserGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindUserGrid : function(result){
		grid.bindData(result);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定取消窗口按钮的click事件
		 */
		$("#back").on("click",function(){
			history.back();
		});
	}
	
}