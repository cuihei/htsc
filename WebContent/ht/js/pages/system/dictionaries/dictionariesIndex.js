$(function(){
	dictionaries.init();
})

/** 绑定编辑资源窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	dictionaries.toUserEditPage(tr);
}

var dictionaries = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("dictionaries");
		loading.init();
		try{
			dictionaries.createUserGrid();
			dictionaries.requestUserData();
			dictionaries.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建资源列表列集合
	 */
	createUserColumns : function(){
		grid.resetColumn();
		grid.addColumn("130px","code","基础数据编码");
		grid.addColumn("140px","value","基础数据名称");
		grid.addColumn("130px","typeName","类别");
		grid.addColumn("100px","creator","创建者");
		grid.addColumn("120px","creationDate","创建时间","#= creationDate ? kendo.toString(new Date(creationDate), 'yyyy-MM-dd') : '' #" );
		grid.addColumn("100px","modifier","更新者");
		grid.addColumn("120px","modifyDate","更新时间","#= modifyDate ? kendo.toString(new Date(modifyDate), 'yyyy-MM-dd') : '' #" );
		return grid.addColumn("60px","handle","操作",kendo.template($("#editTemplate").html()));
	},
	
	/**
	 * 创建资源列表
	 */
	createUserGrid : function(){
		var columns = dictionaries.createUserColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestUserData : function(){
		common.init("../dictionaries/list","POST",dictionaries.bindUserGrid);
		common.do_submit();
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(){
		grid.init("dictionaries");
		layer.close(1);
		layer.msg('删除成功');
		common.init("../dictionaries/list","POST",dictionaries.bindUserGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindUserGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 跳转到资源增加页面
	 */
	toUserAddPage : function(){
		common.toPage("../dictionaries/edit_init");
	},
	
	/**
	 * 删除资源
	 */
	removeUsers : function(){
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
				var id = $("#dictionaries").data("kendoGrid").dataItem(item).id;
				var app = {};
				app.id = id;
				apps.push(app);
			});
			var param = JSON.stringify(apps);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("id",param);
			common.init("../dictionaries/remove","POST",dictionaries.removeSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
	},
	
	/**
	 * 跳转到资源编辑页面
	 */
	toUserEditPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取资源ID
		var id = rowData.id;
		// 跳转到资源编辑页面
		common.toPage("../dictionaries/edit_init?id="+id);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加资源窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			dictionaries.toUserAddPage();
		});
		
		/** 
		 * 绑定删除资源窗口按钮的click事件
		 */
		$("#remove").on("click",function(){
			dictionaries.removeUsers();
		});
		
		/** 绑定刷新资源列表窗口按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
		
		
		
		
		/** 绑定导出模板类型列表窗口按钮的click事件*/
		$("#export").on("click",function(){
			window.location.href="../dictionaries/export";
		});
	}
}