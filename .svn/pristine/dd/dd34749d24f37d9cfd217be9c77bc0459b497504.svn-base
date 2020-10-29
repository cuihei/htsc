$(function(){
	role.init();
})

/** 绑定编辑角色窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	role.toRoleEditPage(tr);
}

var role = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("role");
		loading.init();
		try{
			//创建角色列表
			role.createRoleGrid();
			//请求角色列表数据
			role.requestRoleData();
			//绑定页面事件
			role.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建角色列表列集合
	 */
	createRoleColumns : function(){
		grid.resetColumn();
		grid.addColumn("20%","roleName","角色名称");
		grid.addColumn("25%","creationDate","创建时间","#= creationDate ? kendo.toString(new Date(creationDate), 'yyyy-MM-dd') : '' #" );
		return grid.addColumn("15%","handle","操作",kendo.template($("#editTemplate").html()));
	},
	
	/**
	 * 创建角色列表
	 */
	createRoleGrid : function(){
		var columns = role.createRoleColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestRoleData : function(){
		common.init("../role/list","POST",role.bindRoleGrid);
		common.do_submit();
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(result){
		layer.close(1);
		layer.msg('删除成功');
		common.init("../role/list","POST",role.bindRoleGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindRoleGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 跳转到角色增加页面
	 */
	toRoleAddPage : function(){
		common.toPage("../role/edit_init");
	},
	
	/**
	 * 删除角色
	 */
	removeRoles : function(){
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length<=0) {
			layer.msg('未选择任何行!', {icon:5,time:1000});
			return;
		}
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var roles = [];
			$.each(rowDatas,function(i,item){
				var id = $("#role").data("kendoGrid").dataItem(item).id;
				var role = {};
				role.id = id;
				roles.push(role);
			});
			var param = JSON.stringify(roles);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("role",param);
			common.init("../role/remove","POST",role.removeSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
	},
	
	/**
	 * 跳转到用户编辑页面
	 */
	toRoleEditPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取用户ID
		var id = rowData.id;
		// 跳转到角色编辑页面
		common.toPage("../role/edit_init?id="+id);
	},
	
	/**
	 * 跳转到角色人员分配页面
	 */
	allocUserPage : function(){
		// 获取选中的行数据
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length != 1) {
			layer.msg('请选择一行进行人员分配!', {icon:5,time:1000});
			return;
		}
		$.each(rowDatas,function(i,item){
			var id = $("#role").data("kendoGrid").dataItem(item).id;
			// 跳转到角色人员分配编辑页面
			common.toPage("../role/alloc_user?id="+id);
		});
	},
	
	/**
	 * 跳转到角色资源分配页面
	 */
	allocAppPage : function(){
		// 获取选中的行数据
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length != 1) {
			layer.msg('请选择一行进行资源分配!', {icon:5,time:1000});
			return;
		}
		$.each(rowDatas,function(i,item){
			var id = $("#role").data("kendoGrid").dataItem(item).id;
			// 跳转到角色资源分配编辑页面
			common.toPage("../role/alloc_app?id="+id);
		});
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加用户窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			role.toRoleAddPage();
		});
		
		/** 
		 * 绑定删除用户窗口按钮的click事件
		 */
		$("#remove").on("click",function(){
			role.removeRoles();
		});
		
		/** 绑定刷新用户列表窗口按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
		
		/** 绑定角色人员分配列表窗口按钮的click事件*/
		$("#alloc_user").on("click",function(){
			role.allocUserPage();
		});
		
		/** 绑定角色资源分配列表窗口按钮的click事件*/
		$("#alloc_app").on("click",function(){
			role.allocAppPage();
		});	
	}
}