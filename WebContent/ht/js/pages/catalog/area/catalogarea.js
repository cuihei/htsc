$(function(){
	catalogarea.init();
})

/** 绑定编辑区域管理窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	catalogarea.toAreaEditPage(tr);
}

var catalogarea = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("catalogArea");
		loading.init();
		try{
			catalogarea.createAreaGrid();
			catalogarea.requestAreaData();
			catalogarea.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建区域管理列表列集合
	 */
	createAreaColumns : function(){
		grid.resetColumn();
		grid.addColumn("30%","baseData.value","所属目录类型");
		grid.addColumn("30%","areaName","目录区域名称");
		grid.addColumn("10%","creator","创建人");
		grid.addColumn("20%","creationDate","创建时间");
		return grid.addColumn("15%","handle","操作",kendo.template($("#editTemplate").html()));
	},
	
	/**
	 * 创建区域管理列表
	 */
	createAreaGrid : function(){
		var columns = catalogarea.createAreaColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestAreaData : function(){
		common.init("../area/list","POST",catalogarea.bindAreaGrid);
		common.do_submit();
	}, 
	
	/**
	 * 跳转到编辑页面
	 */
	toAreaEditPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取区域管理ID
		var id = rowData.id;
		// 跳转到区域管理编辑页面
		common.toPage("../catalog/area/initedit?id="+id);
	},
	
	/**
	 * 跳转到区域管理增加页面
	 */
	toAreaAddPage : function(){
		common.toPage("../catalog/area/initadd");
	},
	
	/**
	 * 删除区域管理
	 */
	removeAreas :function(){
		var idss="";
		var rowDatas = grid.getSelectRowsData();
         if (rowDatas.length<1) {
             layer.msg('请选择一行!');
             return;
         }
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			var catalogAreas = [];
			var value = null;
			$.each(rowDatas,function(i,item){
				var id = $("#catalogArea").data("kendoGrid").dataItem(item).id;
				areaName = $("#catalogArea").data("kendoGrid").dataItem(item).areaName;
				var catalogArea = {};
				catalogArea.id = id;
				catalogAreas.push(catalogArea);
			});
			var param = JSON.stringify(catalogAreas);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("catalogArea",param);
			common.init("../area/remove","POST",function(result){
				//删除报错
				if (result.code != 1) {
					//删除报错
					var msg="该目录区域包含对应目录明细，请先删除目录明细！";
					layer.msg(msg);
					return;
				};
				//删除成功
				layer.msg("删除成功");
				catalogarea.requestAreaData();
				catalogarea.bindAreaGrid();
			});
			// 执行提交操作
			common.do_submit(data);
		});
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindAreaGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加区域管理窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			catalogarea.toAreaAddPage();
		});
		/** 
		 * 绑定导出区域管理窗口按钮的click事件
		 */
		$("#export").on("click",function(){
			window.location.href = "../area/export";  
			layer.msg("导出成功");
		});
		
		/** 
		 * 绑定删除区域管理窗口按钮的click事件
		 */
		$("#remove").on("click",function(){
			catalogarea.removeAreas();
		});
		
		/** 绑定刷新区域管理列表窗口按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
		
	}
}
