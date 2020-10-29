$(function(){
	filedDataFile.init();
})

var filedDataFile = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("filedData");
		loading.init();
		try{
			filedDataFile.createfileddataGrid();
			filedDataFile.requestfileddataFileData();
			filedDataFile.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 创建通知类型列表
	 */
	createfileddataGrid : function(){
		var columns = filedDataFile.createfileddataFileColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 构建通知类型集合
	 */
	createfileddataFileColumns : function(){
		grid.resetColumn();
		grid.addColumn("30%","bookName","图书名称");
		grid.addColumn("30%","fileName","文件名称");
		grid.addColumn("30%","filePath","文件路径");
		grid.addColumn("30%","suffixName","文件后缀名");
		grid.addColumn("20%","spaceSize","文件大小");
		return grid.addColumn("30%","handle","操作",kendo.template($("#editTemplate").html()));
	},
	
	/**
	 * 发送数据请求
	 */
	requestfileddataFileData : function(){
		var bookFile = {};
		bookFile.bookId = $("#filedDataId").val();
		var bookFileJson = JSON.stringify(bookFile);
		var param = common.add_param("bookFile",bookFileJson);
		common.init("../filedData/filedDataFile","POST",filedDataFile.bindfileddataFileGrid);
		common.do_submit(param);
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindfileddataFileGrid : function(result){
		grid.bindData(result);
		// 编辑图书资料点击事件
    	var bookBtns = $("button[name='downloadFile']");
		$.each(bookBtns,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				filedDataFile.downloadFile(tr);
			});
		});
		var deleteBtns = $("button[name='removeFile']");
		$.each(deleteBtns,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				filedDataFile.deleteFile(tr);
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
	 * 文件删除
	 */
	deleteFile : function(tr){
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			// 获取附件ID
			var id = rowData.id;
			common.init("../books/delete?id="+id,"POST",filedDataFile.removeSuccess);
			// 执行提交操作
			common.do_submit();
		});
	},
	/**
	 * 删除成功
	 * */
	removeSuccess : function(result){
		layer.close(1);
		layer.msg("删除成功");
		setTimeout(function(){
			window.location.reload();
		},500);
	},
	
	/**
	 * 绑定按钮事件
	 */
	bindPageEvent : function(){
		/**
		 * 返回按钮点击事件
		 */
		$("#back").on("click",function(){
			var mark = $("#mark").val();
			if(mark == "1"){
				window.history.go(-1);
			}else{
				common.toPage("../filedData/index");
			}
		});
	}
}