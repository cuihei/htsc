$(function(){
	view_folder.init();
})

var view_folder = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("folder");
		loading.init();
		try{
			//创建文件列表
			view_folder.createFolderGrid();
			//请求文件列表数据
			view_folder.requestFolderData();
			//绑定页面事件
			view_folder.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建文件列表列集合
	 */
	createfolderColumns : function(){
		grid.resetColumn();
		grid.addColumn("20%","fileName","电子文件名称");
		grid.addColumn("20%","fileType","文件类型");
		grid.addColumn("20%","suffixName","后缀名");
		grid.addColumn("20%","spaceSize","文件大小");
		grid.addColumn("25%","filePath","路径");
		grid.addColumn("20%","entityFileName","实体文件名称");
		return grid.addColumn("20%","entityFileNum","实体文件数量");
	},
	
	/**
	 * 创建文件列表
	 */
	createFolderGrid : function(){
		var columns = view_folder.createfolderColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestFolderData : function(){
		var categoryId = $("#categoryId").val();
		
		common.init("../datumFile/file_list?categoryId="+categoryId,"POST",view_folder.bindFolderGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindFolderGrid : function(result){
		grid.bindData(result);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定返回按钮的click事件*/
		$("#back").on("click",function(){
			//调回用户列表显示页面
			common.toPage("../datumFile/index");
			//执行刷新用户列表按钮
			parent.$("#refresh").click();
		});
	}
}