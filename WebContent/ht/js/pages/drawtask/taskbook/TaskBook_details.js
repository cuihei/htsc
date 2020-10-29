$(function(){
	tbedit.init();
	taskShow.editInit();
});
var tbedit={
		/**
		 * 初始化操作
		 * 1.将提交按钮改为保存按钮
		 * 2.删除日期框的图标
		 * 3.绑定按钮事件
		 */
		init:function(){
			$("#executeTime").prev().children().remove();
			tbedit.bindPageEvent();
		},
		/**
		 * 绑定页面事件
		 */
		bindPageEvent : function(){
			//绑定返回事件
			$("#backPage").click(function(){
				history.go(-1);
			});
		}
}

var taskShow={
	/**
	 * 初始化
	 */
		init:function(){
			grid.init("taskShow");
			taskShow.createtaskShowColumns();
			var columns = taskShow.createtaskShowColumns();
			grid.createGrid(columns);
		},
		/**
		 * 当进入编辑页面时，初始化;
		 */
		editInit:function(){
			grid.init("taskShow");
			taskShow.createtaskShowColumns();
			var columns = taskShow.createtaskShowColumns();
			grid.createGrid(columns);
			var planId=$("#planId").val();
			var data={
					"planId":planId
			}
			taskShow.requesttaskShowData(data);
		},
	/** 
	 * 创建表格
	 */
		createtaskShowColumns : function(){
			grid.resetColumn();
			grid.addColumn("150px","mapName","图名");
			grid.addColumn("150px","mapNo","图号");
			return	grid.addColumn("150px","scale","比例尺 1:");
		},
		/**
		 * 发送数据请求
		 */
		requesttaskShowData : function(data){
			common.init("../taskbook/planlist","POST",taskShow.bindtaskShowGrid);
			common.do_submit(data);
		},
		/**
		 * 接收服务器响应数据,绑定表格
		 * 这是一个回调函数，不用手动调用
		 */
		bindtaskShowGrid : function(result){
			grid.bindData(result);
			//设置高度
			var grid1 = $("#taskShow").data("kendoGrid");
			var height =$(document.body).height()-($("#otherDemand").height()*2);
			grid1.setOptions({
				height:height,
				pageable:false,
			});
		}
	
		
}
