$(function(){
	htaskBooks.init();
})
var htaskBooks ={
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("hTaskBooks");
		loading.init();
		try{
			htaskBooks.createhtaskBooksGrid();
			htaskBooks.requesthtaskBooksData();
			htaskBooks.bindPageEvent();
		}catch(err){
			loading.close();
		}
	},
	/**
	 * 历史列表表格。
	 */
	createhtaskBooksColumns : function(){
		grid.resetColumn();
		grid.addColumn("150px","taskbookNo","序号");
		grid.addColumn("150px","taskbookName","历史任务书名称");
		grid.addColumn("150px","download_options","下载附件",kendo.template($("#downloadTemplate").html()));
		grid.addColumn("150px","issueTime","下发时间","#=issueTime?kendo.toString(new Date(issueTime),'yyyy-MM-dd'):'' #");
		return grid.addColumn("300px","handle","操作",kendo.template($("#editTemplate").html()));
	},
	/**
	 * 创建改正通知公告列表
	 */
	createhtaskBooksGrid : function(){
		var columns = htaskBooks.createhtaskBooksColumns();
		grid.createGrid(columns);
		
	},
	/**
	 * 发送数据请求
	 */
	requesthtaskBooksData : function(){
		var taskBookId=$("#taskBookId").val();
		var data={"taskBookId":taskBookId};
		common.init("../historyTaskBook/list_bytaskbookid","POST",htaskBooks.bindhtaskBooksGrid);
		common.do_submit(data);
	},
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindhtaskBooksGrid : function(result){
		grid.bindData(result);
		//设置高度
		var grid1 = $("#hTaskBooks").data("kendoGrid");
		var height =$(document.body).height()-($("#submit").parent().parent().height()*2);
		grid1.setOptions({
			height:height,
		});
		var editBtns = $("button[name='info']");
		$.each(editBtns,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				htaskBooks.toDetailsPage(tr);
			});
		});
		var downBtns = $("div[name='download']");
		$.each(downBtns,function(i,item){
			var tr = $(item).parent().parent();
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			//获取id
			var id=rowData.id;
			//获取附件地址
			var enclosure=rowData.enclosure;
			//拆分附件地址，获取附件名称
			if(enclosure!=null&enclosure!=""){
				enclosure=enclosure.split("\\");
				enclosure=enclosure[enclosure.length-1];
				//如果有附件，则设置下载附件按钮
				if(enclosure!=null&enclosure!=""){
					$(item).append("<a class='download' target='_blank'  href=../historyTaskBook/down_load?id="+id+">"+enclosure+"</a>");
				}
			}
		});
		//grid.setEvents();
	},
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		$("#backPage").unbind("click").click(function(){
			var taskBookType=$("#taskBookType").val()
			common.toPage("../taskbook/index?taskBookType="+taskBookType);
		});
	},
	/**
	 * 跳转到详情页面
	 */
	toDetailsPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取任务书ID
		var id = rowData.id;
		// 跳转到用户编辑页面
		common.toPage("../historyTaskBook/detail?id="+id);
	}
	
}
