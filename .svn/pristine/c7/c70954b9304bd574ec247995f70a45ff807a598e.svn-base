$(function(){
	catalogtype.init();
})

/** 绑定编辑类型窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	catalogtype.toTypeEditPage(tr);
}

var catalogtype = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("type");
		loading.init();
		try{
			catalogtype.createTypeGrid();
			catalogtype.requestTypeData();
			catalogtype.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建目录类型列表列集合
	 */
	createTypeColumns : function(){
		grid.resetColumn();
		grid.addColumn("20%","value","类别名称");
		grid.addColumn("20%","creator","创建人");
		grid.addColumn("25%","creationDate","创建时间");
		return grid.addColumn("100px","handle","操作",kendo.template($("#editTemplate").html()));
	},
	
	/**
	 * 创建目录列表
	 */
	createTypeGrid : function(){
		var columns = catalogtype.createTypeColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestTypeData : function(){
		common.init("../cta/list","POST",catalogtype.bindTypeGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindTypeGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 跳转到类型增加页面
	 */
	toTypeAddPage : function(){
		common.toPage("../cta/initedit");
	},
	
	/**
	 * 删除类型
	 */
	removeType :function(){
		var idss="";
		var rowDatas = grid.getSelectRowsData();
         if (rowDatas.length<1) {
             layer.msg('请选择一行!');
             return;
         }
		/*删除*/
		layer.confirm('确认要删除吗？',function(){
			var types = [];
			var value = null;
			$.each(rowDatas,function(i,item){
				value = $("#type").data("kendoGrid").dataItem(item).value;
				var id = $("#type").data("kendoGrid").dataItem(item).id;
				var type = {};
				type.id = id;
				type.value = value;
				types.push(type);
			});
				var param = JSON.stringify(types);
				// 添加参数 @param 参数key；参数value
				var data = common.add_param("type",param);
				common.init("../cta/remove","POST",function(result){
					//删除报错
					if (result.code != 1) {
						//删除报错
						var msg="该目录类别包含对应目录区域，请先删除目录区域！";
						layer.msg(msg);
						return;
					};
					//删除成功
					layer.msg("删除成功");
					catalogtype.requestTypeData();
					catalogtype.bindTypeGrid();
				});
				// 执行提交操作
				common.do_submit(data);
			});
		},
	
	/**
	 * 跳转到类型编辑页面
	 */
	toTypeEditPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取类型ID
		var id = rowData.id;
		// 跳转到类型编辑页面
		common.toPage("../cta/initedit?id="+id);
	},
	
	/**
	 * 导出成功
	 * */
	exportSuccess : function(){
		layer.msg('导出成功');
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加类型窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			catalogtype.toTypeAddPage();
		});
		
		/** 
		 * 绑定删除类型窗口按钮的click事件
		 */
		$("#remove").on("click",function(){
			catalogtype.removeType();
		});
		
		/** 绑定刷新类型列表窗口按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
		
		/** 
		 * 绑定导出区域管理窗口按钮的click事件
		 */
		$("#export").on("click",function(){
			window.location.href = "../cta/export";  
			layer.msg("导出成功");
		});
	}
}