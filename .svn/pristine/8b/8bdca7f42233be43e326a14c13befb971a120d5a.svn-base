$(function(){
	maps.init();
})

/** 绑定编辑目录窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	maps.toEditPage(tr);
}

/** 绑定查看图片按钮的click事件*/
function viewPage(obj) {
	var tr = $(obj).parent().parent();
	maps.toViewImg(tr);
}

var maps = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("maps");
		loading.init();
		try{
			maps.createGrid();
			maps.requestData();
			maps.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建资源列表列集合
	 */
	createColumns : function(){
		grid.resetColumn();
		grid.addColumn("40%","mapNo","图号",null,null,null,false);
		grid.addColumn("40%","handle","图片",kendo.template($("#viewTemplate").html()),null,null,false)
		return grid.addColumn("20%","handle","操作",kendo.template($("#editTemplate").html()));
	},
	
	/**
	 * 创建资源列表
	 */
	createGrid : function(){
		var columns = maps.createColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestData : function(){
		common.init("../maps/list","POST",maps.bindGrid);
		common.do_submit();
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(){
		grid.init("maps");
		layer.close(1);
		layer.msg('删除成功');
		common.init("../maps/list","POST",maps.bindGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindGrid : function(result){
		grid.bindData(result);
	},
	
	/**
	 * 跳转到增加页面
	 */
	toAddPage : function(){
		common.toPage("../maps/edit_init");
	},
	
	/**
	 * 删除资源
	 */
	remove : function(){
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length<=0) {
			layer.msg('未选择任何行!', {icon:5,time:1000});
			return;
		}
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var apps = [];
			$.each(rowDatas,function(i,item){
				var id = $("#maps").data("kendoGrid").dataItem(item).id;
				var app = {};
				app.id = id;
				apps.push(app);
			});
			var param = JSON.stringify(apps);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("maps",param);
			common.init("../maps/remove","POST",maps.removeSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
	},
	
	/**
	 * 跳转到编辑页面
	 */
	toEditPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取ID
		var id = rowData.id;
		// 跳转到目录编辑页面
		common.toPage("../maps/edit_init?id="+id);
	},
	
	/**
	 * 跳转到图片查看页面
	 */
	toViewImg : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取ID
		var id = rowData.id;
		// 跳转到区域图片查看页面
		common.toPage("../maps/init_view?id="+id)
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加资源窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			maps.toAddPage();
		});
		
		/** 
		 * 绑定删除资源窗口按钮的click事件
		 */
		$("#remove").on("click",function(){
			maps.remove();
		});
		
		/** 绑定刷新资源列表窗口按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
	}
}