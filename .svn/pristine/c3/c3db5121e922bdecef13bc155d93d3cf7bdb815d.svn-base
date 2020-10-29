$(function(){
	formProp.init();
})

/** 绑定编辑窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	formProp.toFormPropEditPage(tr);
}

var formProp = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("formProp");
		loading.init();
		try{
			//创建列表
			formProp.createFormPropGrid();
			//请求列表数据
			formProp.requestFormPropData();
			//绑定页面事件
			formProp.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建表单属性列表列集合
	 */
	createFormPropColumns : function(){
		grid.resetColumn();
		grid.addColumn("20%","formName","所属表单名称");
		grid.addColumn("25%","propKey","属性键");
		grid.addColumn("25%","propName","属性名称");
		grid.addColumn("25%","propType","属性类型");
		grid.addColumn("25%","required","是否必填项");
		grid.addColumn("25%","propDefaultValue","属性默认值");
		grid.addColumn("25%","selectData","下拉列表值");
		grid.addColumn("15%","num","排序值");
		return grid.addColumn("15%","handle","操作",kendo.template($("#editTemplate").html()));
	},
	
	/**
	 * 创建列表
	 */
	createFormPropGrid : function(){
		var columns = formProp.createFormPropColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestFormPropData : function(){
		var formId = $("#form_id").val();
		common.init("../formProp/list?formId="+formId,"POST",formProp.bindFormPropGrid);
		common.do_submit();
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(result){
		layer.close(1);
		layer.msg('删除成功');
		var formId = $("#form_id").val();
		common.init("../formProp/list?formId="+formId,"POST",formProp.bindFormPropGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindFormPropGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 跳转到增加页面
	 */
	toFormPropAddPage : function(){
		// 获取formID
		var formId = $("#form_id").val();
		common.toPage("../formProp/edit_init?form_id="+formId);
	},
	
	/**
	 * 删除
	 */
	removeFormProps : function(){
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length<=0) {
			layer.msg('未选择任何行!', {icon:5,time:1000});
			return;
		}
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var formProps = [];
			$.each(rowDatas,function(i,item){
				var id = $("#formProp").data("kendoGrid").dataItem(item).id;
				var formProp = {};
				formProp.id = id;
				formProps.push(formProp);
			});
			var param = JSON.stringify(formProps);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("formProp",param);
			common.init("../formProp/remove","POST",formProp.removeSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
	},
	
	/**
	 * 跳转到编辑页面
	 */
	toFormPropEditPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取用户ID
		var id = rowData.id;
		// 获取formID
		var formId = $("#form_id").val();
		// 跳转到用户编辑页面
		common.toPage("../formProp/edit_init?id="+id+"&form_id="+formId);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			formProp.toFormPropAddPage();
		});
		
		/** 
		 * 绑定删除窗口按钮的click事件
		 */
		$("#remove").on("click",function(){
			formProp.removeFormProps();
		});
		
		/** 绑定刷新列表窗口按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
		
		/** 绑定返回窗口按钮的click事件*/
		$("#back").on("click",function(){













			
			common.toPage("../form/index");
		});
	}
}