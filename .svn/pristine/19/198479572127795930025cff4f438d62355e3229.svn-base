$(function(){
	taskBooks.init();
	$("#uploadFile").css("width","300px");
})
var taskBooks ={
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("tables1");
		loading.init();
		try{
			taskBooks.createtaskBooksGrid();
			taskBooks.requesttaskBooksData();
			taskBooks.bindPageEvent();
		}catch(err){
			loading.close();
		}
	},
	/**
	 * 构建改正通知公告表格。
	 */
	createtaskBooksColumns : function(){
		grid.resetColumn();
		grid.addColumn("150px","taskbookNo","序号");
		grid.addColumn("300px","taskbookName","编绘任务书名称");
		grid.addColumn("150px","history_version","历史版本",kendo.template($("#hisTemplate").html()));
		grid.addColumn("150px","upload_options","上传附件",kendo.template($("#uploadTemplate").html()));
		return grid.addColumn("150px","handle","操作",kendo.template($("#editTemplate").html()));
	},
	/**
	 *
	 */
	createtaskBooksGrid : function(){
		var columns = taskBooks.createtaskBooksColumns();
		grid.createGrid(columns);
	},
	/**
	 * 发送数据请求
	 */
	requesttaskBooksData : function(){
		var taskBookType=$("#taskBookType").val();
		common.init("../taskbook/list?taskBookType="+taskBookType,"POST",taskBooks.bindtaskBooksGrid);
		common.do_submit();
	},
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindtaskBooksGrid : function(result){
		grid.bindData(result);
		//设置高度
		var grid1 = $("#taskBooks").data("kendoGrid");
		var height =$(document.body).height()-($("#addTaskBook").parent().parent().height()*6);
		grid1.setOptions({
			height:height,
		});
		var hstoryBtns = $("button[name='histroy']");
		$.each(hstoryBtns,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				taskBooks.toHistoryPage(tr);
			});
		});
		var editBtns = $("button[name='edit']");
		$.each(editBtns,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				taskBooks.toEditPage(tr);
			});
		});
		var editBtns = $("button[name='upload']");
		$.each(editBtns,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				taskBooks.showModel(tr);
			});
		});
		//grid.setEvents();
	},
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		$("#addTaskBook").click(function(){
			var taskBookType=$("#taskBookType").val();
			common.toPage("../taskbook/edit_page?taskBookType="+taskBookType);
		});
		$("#taskSubmit").click(function(){
			$("#importForm").submit();
		});
		
	},
	
	/**
	 * 跳转到历史版本页面
	 */
	toHistoryPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取任务书ID
		var id = rowData.id;
		//获取任务书类型
		var taskBookType=$("#taskBookType").val();
		// 跳转到历史版本页面
		common.toPage("../historyTaskBook/index?taskBookId="+id+"&taskBookType="+taskBookType);
	},
	/**
	 * 跳转到编辑页面
	 */
	toEditPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取任务书ID
		var id = rowData.id;
		//获取任务书类型
		var taskBookType=$("#taskBookType").val();
		// 跳转到用户编辑页面
		common.toPage("../taskbook/edit_page?id="+id+"&taskBookType="+taskBookType);
	},
	/**
	 * 跳转到编辑页面
	 */
	showModel : function(tr){
		$("#myModal").modal('show');
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取任务书ID
		var id = rowData.id;
		$("#taskBookId").val(id);
	}
}
