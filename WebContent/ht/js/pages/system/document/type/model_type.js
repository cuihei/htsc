$(function(){
	modeltype.init();
})

/** 绑定编辑用户窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	modeltype.toModelTypeEditPage(tr);
}

var modeltype = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("modeltype");
		loading.init();
		try{
			modeltype.createModelTypeGrid();
			modeltype.requestModelTypeData();
			modeltype.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建模板类型集合
	 */
	createModeltypeColumns : function(){
		grid.resetColumn();
		grid.addColumn("15%","name","模板类型名称");
		return grid.addColumn("10%","handle","操作",kendo.template($("#editTemplate").html()));
	},
	
	/**
	 * 创建模板类型列表
	 */
	createModelTypeGrid : function(){
		var columns = modeltype.createModeltypeColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestModelTypeData : function(){
		common.init("../mte/list","POST",modeltype.bindModelTypeGrid);
		common.do_submit();
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(){
		grid.init("modeltype");
		layer.close(1);
		layer.msg('删除成功');
		common.init("../mte/list","POST",modeltype.bindModelTypeGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindModelTypeGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 跳转到模板增加页面
	 */
	toModelTypeAddPage : function(){
		common.toPage("../mte/edit_init");
	},
	
	/**
	 * 删除模板
	 */
	removeModelTypes : function(){
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length <= 0) {
			layer.msg('未选择任何行!', {icon:5,time:1500});
			return;
		}
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var modeltypes = [];
			$.each(rowDatas,function(i,item){
				var id = $("#modeltype").data("kendoGrid").dataItem(item).id;
				var modeltype = {};
				modeltype.id = id;
				modeltypes.push(modeltype);
			});
			var param = JSON.stringify(modeltypes);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("modeltype",param);
			common.init("../mte/remove","POST",modeltype.removeSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
	},
	
	/**
	 * 跳转到模板编辑页面
	 */
	toModelTypeEditPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取用户ID
		var id = rowData.id;
		// 跳转到用户编辑页面
		common.toPage("../mte/edit_init?id="+id);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加用户窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			modeltype.toModelTypeAddPage();
		});
		
		/** 
		 * 绑定删除用户窗口按钮的click事件
		 */
		$("#remove").on("click",function(){
			modeltype.removeModelTypes();
		});
		
		/** 绑定刷新用户列表窗口按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
		
		/** 绑定导出模板类型列表窗口按钮的click事件*/
		$("#export").on("click",function(){
			var rowDatas = grid.getSelectRowsData();
			// 判断是否选中行
			if (rowDatas.length<=0) {
				layer.msg('未选择任何行!', {icon:5,time:1000});
				return false;
			}
			var modeltypes = [];
			$.each(rowDatas,function(i,item){
				var id = $("#modeltype").data("kendoGrid").dataItem(item).id;
				var modeltype = {};
				modeltype.id = id;
				modeltypes.push(modeltype);
			});
			// 转为JOSN数据
			var param = JSON.stringify(modeltypes);
			window.location.href="../mte/export?modeltype="+param;
		});
	}
}