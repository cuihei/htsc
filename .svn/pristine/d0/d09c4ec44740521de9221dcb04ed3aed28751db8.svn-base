$(function(){
	defect.init();
})

/** 绑定编辑目录窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	defect.toEditPage(tr);
}

var defect = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("defect");
		loading.init();
		try{
			defect.createGrid();
			defect.requestData();
			defect.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 创建缺陷列表
	 */
	createGrid : function(){
		var columns = defect.createColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 构建缺陷列集合
	 */
	createColumns : function(){
		grid.resetColumn;
		grid.addColumn("100px","chartName","海图类型");
		grid.addColumn("100px","typeName","缺陷类别");
		grid.addColumn("100px","itemName","项目");
		grid.addColumn("100px","discription","缺陷内容");
		grid.addColumn("100px","deep","缺陷级别");
		grid.addColumn("100px","score","扣分");
		return grid.addColumn("100px","handle","操作",kendo.template($("#editTemplate").html()));
	},
		
	/**
	 * 发送数据请求
	 */
	requestData : function(){
		common.init("../defect/defect_list","POST",defect.bindGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindGrid : function(result){
		/*var data = result.value;
		if(data != null){
			for(var i=0;i<data.length;i++){
				// 海图类型
				var charttype = data[i].charttype;
				if(charttype != null &&　charttype.value != null){
					data[i]["charttypeName"] = charttype.value;
				}
				// 缺陷类别
				var defecttype = data[i].type;
				if(defecttype != null &&　defecttype.typeName != null){
					data[i]["typeName"] = defecttype.typeName;
				}
				// 项目
				var defectitem = data[i].item;
				if(defectitem != null && defectitem.item != null){
					 data[i]["itemName"] = defectitem.item;
				}
			}
		}*/
		grid.bindData(result);
		var gridInst = $("#defect").data("kendoGrid");
		
		 
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
			var defects = [];
			$.each(rowDatas,function(i,item){
				var id = $("#defect").data("kendoGrid").dataItem(item).id;
				var defect = {};
				defect.id = id;
				defects.push(defect);
			});
			var paramJson = JSON.stringify(defects);
			// 添加参数 @param 参数key；参数value
			var param = common.add_param("ids",paramJson);
			common.init("../defect/remove","POST",defect.removeSuccess);
			// 执行提交操作
			common.do_submit(param);
		});
	},
	
	/**
	 * 跳转到添加页面
	 */
	toAddPage : function(){
		common.toPage("../defect/init_edit");
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(result){
		grid.init("defect");
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
		defect.requestData();
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
		common.toPage("../defect/init_edit?id="+id);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加目录窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			defect.toAddPage();
		});
		
		/** 
		 * 绑定删除目录窗口按钮的click事件
		 */
		$("#remove").on("click",function(){
			defect.remove();
		});
		
		/** 绑定刷新目录列表窗口按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
	}
}