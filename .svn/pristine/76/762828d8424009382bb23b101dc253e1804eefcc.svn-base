$(function(){
	booksarea.init();
})

/** 绑定编辑资源窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	booksarea.toUserEditPage(tr);
}

var booksarea = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("booksArea");
		loading.init();
		try{
			booksarea.createUserGrid();
			booksarea.requestUserData();
			booksarea.bindPageEvent();
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
		grid.addColumn("130px","code","海区编码");
		grid.addColumn("140px","value","海区名称");
		grid.addColumn("130px","typeName","类别");
		return grid.addColumn("60px","handle","操作",kendo.template($("#editTemplate").html()));
	},
	
	/**
	 * 创建资源列表
	 */
	createUserGrid : function(){
		var columns = booksarea.createUserColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestUserData : function(){
		var flag =$("#flag").val();
		var param = common.add_param("flag",flag);
		common.init("../dictionaries/booksarea","POST",booksarea.bindUserGrid);
		common.do_submit(param);
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(){
		grid.init("booksArea");
		layer.close(1);
		layer.msg('删除成功');
		var flag =$("#flag").val();
		common.init("../dictionaries/booksarea?flag="+flag,"POST",booksarea.bindUserGrid);
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
		var flag =$("#flag").val();
		common.toPage("../dictionaries/edit_books?flag="+flag);
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
				var id = $("#booksArea").data("kendoGrid").dataItem(item).id;
				var app = {};
				app.id = id;
				apps.push(app);
			});
			var param = JSON.stringify(apps);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("id",param);
			common.init("../dictionaries/remove","POST",booksarea.removeSuccess);
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
		var flag =$("#flag").val();
		// 跳转到资源编辑页面
		common.toPage("../dictionaries/edit_books?id="+id+"&flag="+flag);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加资源窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			booksarea.toUserAddPage();
		});
		
		/** 
		 * 绑定删除资源窗口按钮的click事件
		 */
		$("#remove").on("click",function(){
			booksarea.removeUsers();
		});
		
		/** 绑定刷新资源列表窗口按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
		
		
		/** 绑定导出模板类型列表窗口按钮的click事件*/
		$("#export").on("click",function(){
			var flag =$("#flag").val();
			window.location.href="../dictionaries/exportbooks?flag="+flag;
		});
	}
}