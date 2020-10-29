$(function(){
	booksfile.init();
})

var booksfile = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("booksfile");
		loading.init();
		try{
			booksfile.createbooksGrid();
			booksfile.requestbooksData();
			booksfile.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 创建文件列表
	 */
	createbooksGrid : function(){
		var columns = booksfile.createbooksColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 构建文件集合
	 */
	createbooksColumns : function(){
		grid.resetColumn();
		grid.addColumn("30%","bookName","图书名称");
		grid.addColumn("30%","fileName","文件名称");
		grid.addColumn("30%","filePath","文件路径");
		grid.addColumn("30%","suffixName","文件后缀");
		grid.addColumn("20%","spaceSize","文件大小");
		return grid.addColumn("30%","handle","操作",kendo.template($("#editTemplate").html()));
	},
	
	/**
	 * 发送数据请求
	 */
	requestbooksData : function(){
		var bookFile = {};
		bookFile.bookId = $("#booksId").val();
		 var bookFileJson = JSON.stringify(bookFile);
		 var param = common.add_param("bookFile",bookFileJson);
		common.init("../books/booksfile","POST",booksfile.bindbooksGrid);
		common.do_submit(param);
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindbooksGrid : function(result){
		grid.bindData(result);
		// 绑定下载点击事件
    	var booksBtns = $("button[name='downloadFile']");
		$.each(booksBtns,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				booksfile.downloadFile(tr);
			});
		});
		//grid.setEvents();
	},
	
	/**
	 * 文件下载
	 */
	downloadFile : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取附件ID
		var id = rowData.id;
		window.location.href = "../datumFileDownload/filedownload?bookfileId="+id;
	},
	
	/**
	 * 绑定按钮事件
	 */
	bindPageEvent : function(){
		/**
		 * 返回按钮点击事件
		 */
		$("#back").on("click",function(){
			common.toPage("../books/index");
		});
	}
}