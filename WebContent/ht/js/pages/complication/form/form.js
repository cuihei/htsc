$(function(){
	form.init();
})

/** 绑定编辑窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	form.toFormEditPage(tr);
}

/** 绑定查看窗口按钮的click事件*/
function seePage(obj) {
	var tr = $(obj).parent().parent();
	form.toFormPropPage(tr);
}

/** 绑定生成窗口按钮的click事件*/
function goPage(obj) {
	var tr = $(obj).parent().parent();
	form.toFormGoPage(tr);
}

/** 绑定导出按钮的click事件*/
function goPoiPage(obj) {
	var tr = $(obj).parent().parent();
	form.toFormGoPoi(tr);
}

var form = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("form");
		loading.init();
		try{
			//创建列表
			form.createFormGrid();
			//请求列表数据
			form.requestFormData();
			//绑定页面事件
			form.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建表单列表列集合
	 */
	createFormColumns : function(){
		grid.resetColumn();
		grid.addColumn("20%","value","所属基础数据表单名称");
		grid.addColumn("25%","name","表单名称");
		grid.addColumn("25%","url","表单地址");
		return grid.addColumn("25%","handle","操作",kendo.template($("#editTemplate").html()),null,null,true);
	},
	
	/**
	 * 创建列表
	 */
	createFormGrid : function(){
		var columns = form.createFormColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestFormData : function(){
		common.init("../form/list","POST",form.bindFormGrid);
		common.do_submit();
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(result){
		layer.close(1);
		layer.msg('删除成功');
		common.init("../form/list","POST",form.bindFormGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindFormGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 跳转到增加页面
	 */
	toFormAddPage : function(){
		common.toPage("../form/edit_init");
	},
	
	/**
	 * 删除
	 */
	removeForms : function(){
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length<=0) {
			layer.msg('未选择任何行!', {icon:5,time:1000});
			return;
		}
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var forms = [];
			$.each(rowDatas,function(i,item){
				var id = $("#form").data("kendoGrid").dataItem(item).id;
				var form = {};
				form.id = id;
				forms.push(form);
			});
			var param = JSON.stringify(forms);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("form",param);
			common.init("../form/remove","POST",form.removeSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
	},
	
	/**
	 * 跳转到编辑页面
	 */
	toFormEditPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取用户ID
		var id = rowData.id;
		// 跳转到用户编辑页面
		common.toPage("../form/edit_init?id="+id);
	},
	
	/**
	 * 跳转到导出页面
	 */
	toFormGoPoi : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取用户ID
		var id = rowData.id;
		// 跳转到导出页面
		common.toPage("../docTempleteAction/index?formId="+id);
	},
	
	/**
	 * 跳转到表单属性页面
	 */
	toFormPropPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取用户ID
		var id = rowData.id;
		// 跳转到用户编辑页面
		common.toPage("../formProp/index?form_id="+id);
	},
	
	/**
	 * 生成页面
	 */
	toFormGoPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取用户ID
		var id = rowData.id;
		// 跳转到用户编辑页面
		common.toPage("../formValue/index?formId="+id);
	},
	
	/**
	 * 绑定全部生成按钮
	 */
	toFormAddAllPage : function(){
		common.init("../form/add_all","POST",form.addAllSuccess);
		common.do_submit();
	},
	
	/**
	 * 一键生成，回调初始化列表页面
	 */
	addAllSuccess : function(result){
		layer.close(1);
		layer.msg('全部生成完成!',{icon:1,time:1000});
		common.init("../form/list","POST",form.bindFormGrid);
		common.do_submit();
	},
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			form.toFormAddPage();
		});
		
		/** 
		 * 绑定删除窗口按钮的click事件
		 */
		$("#remove").on("click",function(){
			form.removeForms();
		});
		
		/** 绑定刷新列表窗口按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
		
		/** 
		 * 绑定全部生成窗口按钮的click事件
		 */
		$("#addall").on("click",function(){
			form.toFormAddAllPage();
		});
	}
}