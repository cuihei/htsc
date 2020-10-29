$(function(){
	defect_item.init();
})

/** 绑定编辑目录窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	defect_item.toEditPage(tr);
}

var defect_item = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("defectitem");
		loading.init();
		try{
			defect_item.createGrid();
			defect_item.requestData();
			defect_item.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 创建缺陷列表
	 */
	createGrid : function(){
		var columns = defect_item.createColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 构建缺陷列集合
	 */
	createColumns : function(){
		grid.resetColumn;
		grid.addColumn("30%","charttypeName","海图类型",null,null,null,false);
		grid.addColumn("30%","typeName","类别",null,null,null,false);
		grid.addColumn("30%","item","项目",null,null,null,false);
		return grid.addColumn("10%","handle","操作",kendo.template($("#editTemplate").html()));
	},
		
	/**
	 * 发送数据请求
	 */
	requestData : function(){
		common.init("../defectitem/list","POST",defect_item.bindGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindGrid : function(result){
		var data = result.value;
		if(data != null){
			for(var i=0;i<data.length;i++){
				// 海图类型
				var charttype = data[i].charttype;
				if(charttype != null &&　charttype.value != null){
					data[i]["charttypeName"] = charttype.value;
				}
				// 缺陷类别
				var itemtype = data[i].type;
				if(itemtype != null &&　itemtype.typeName != null){
					data[i]["typeName"] = itemtype.typeName;
				}
			}
		}
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 删除缺陷
	 */
	remove : function(){
		//获取选中行数据对象
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length<=0) {
			layer.msg('未选择任何行!', {icon:5,time:1000});
			return;
		}
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var defectitems = [];
			$.each(rowDatas,function(i,item){
				var id = $("#defectitem").data("kendoGrid").dataItem(item).id;
				var defectitem = {};
				defectitem.id = id;
				defectitems.push(defectitem);
			});
			var paramJson = JSON.stringify(defectitems);
			// 添加参数 @param 参数key；参数value
			var param = common.add_param("ids",paramJson);
			common.init("../defectitem/remove","POST",defect_item.removeSuccess);
			// 执行提交操作
			common.do_submit(param);
		});
	},
	
	/**
	 * 跳转到添加页面
	 */
	toAddPage : function(){
		common.toPage("../defectitem/init_edit");
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(result){
		grid.init("defectitem");
		layer.close(1);
		if(result.code != 1){
			layer.msg("删除失败！");
			return;
		}
		if(result.value == "" || result.value == null){
			layer.msg('删除成功');
		}else{
			layer.msg(result.value);
		}
		common.init("../defectitem/list","POST",defect_item.bindGrid);
		common.do_submit();
	},
	
	/**
	 * 跳转到缺陷编辑页面
	 */
	toEditPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取缺陷ID
		var id = rowData.id;
		// 跳转到目录编辑页面
		common.toPage("../defectitem/init_edit?id="+id);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加目录窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			defect_item.toAddPage();
		});
		
		/** 
		 * 绑定删除目录窗口按钮的click事件
		 */
		$("#remove").on("click",function(){
			defect_item.remove();
		});
		
		/** 绑定刷新目录列表窗口按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
	}
}