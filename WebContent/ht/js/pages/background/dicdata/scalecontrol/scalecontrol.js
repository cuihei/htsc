$(function(){
	scaleControl.init();
})


/** 绑定编辑用户窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	scaleControl.editScaleControlPage(tr);
}

var scaleControl = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("scaleControl");
		loading.init();
		try{
			scaleControl.createScaleControlGrid();
			scaleControl.requestScaleControlData();
			scaleControl.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 创建比例尺列表
	 */
	createScaleControlGrid : function(){
		var columns = scaleControl.createscaleControlColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 构建比例尺集合
	 */
	createscaleControlColumns : function(){
		grid.resetColumn();
		grid.addColumn("240px","oldScale","原比例尺");
		grid.addColumn("240px","standardScale","标准比例尺");
		grid.addColumn("200px","creator","创建者");
		grid.addColumn("340px","creationDate","创建时间","#= creationDate ? kendo.toString(new Date(creationDate), 'yyyy-MM-dd') : '' #" );
//		grid.addColumn("100px","modifier","更新者");
//		grid.addColumn("150px","modifyDate","更新时间");
		return grid.addColumn("150px","handle","操作",kendo.template($("#editTemplate").html()));
	},
	
	/**
	 * 发送数据请求
	 */
	requestScaleControlData : function(){
		common.init("../scaleControl/list","POST",scaleControl.bindScaleControlGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindScaleControlGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 跳转到编辑页面
	 */
	editScaleControlPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取资料ID
		var id = rowData.id;
		common.toPage("../scaleControl/editinit?id="+id);
	},
	
	/**
	 * 跳转到新增页面
	 */
	addScaleControlPage : function(tr){
		common.toPage("../scaleControl/editinit");
	},
	
	/**
	 * 删除
	 */
	removeScaleControl : function(){
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length <= 0) {
			layer.msg('未选择任何行!', {icon:5,time:1500});
			return false;
		}
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var scaleControls = [];
			$.each(rowDatas,function(i,item){
				var id = $("#scaleControl").data("kendoGrid").dataItem(item).id;
				var scaleControl = {};
				scaleControl.id = id;
				scaleControls.push(scaleControl);
			});
			var param = JSON.stringify(scaleControls);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("scaleControl",param);
			common.init("../scaleControl/remove","POST",scaleControl.removeSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
	},
	
	/**
	 * 删除成功
	 **/
	removeSuccess : function(result){
		grid.init("scaleControl");
		layer.msg('已删除');
		scaleControl.requestScaleControlData();
		/*// 执行刷新按钮的点击事件
		$("#refresh").click();*/
	},
	
	/**
	 * 绑定页面点击事件
	 */
	bindPageEvent : function(){
		
		/**
		 * 新建按钮点击事件
		 */
		$("#add").on("click",function(){
			scaleControl.addScaleControlPage();
		});
		
		/** 
		 * 绑定删除按钮的click事件
		 */
		$("#remove").on("click",function(){
			scaleControl.removeScaleControl();
		});
		
		/**
		 * 刷新按钮点击事件
		 */
		$("#refresh").on("click",function(){
			window.location.reload();
		});
		
		/** 绑定导出通知按钮的click事件*/
		$("#export").on("click",function(){
			window.location.href = "../scaleControl/export";
		});
	},
}