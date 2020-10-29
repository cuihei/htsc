$(function(){
	employee.init();
})

/** 绑定编辑用户窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	employee.toUserEditPage(tr);
}

/** 绑定查看签章窗口按钮的click事件*/
function signaturePage(obj) {
	var tr = $(obj).parent().parent();
	employee.toUserSignaturePage(tr);
}


var employee = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("user");
		loading.init();
		try{
			//创建用户列表
			employee.createUserGrid();
			//请求用户列表数据
			employee.requestUserData();
			//绑定页面事件
			employee.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建用户列表列集合
	 */
	createUserColumns : function(){
		grid.resetColumn();
		grid.addColumn("20%","userNo","登陆账号");
		grid.addColumn("20%","userName","用户名称");
		grid.addColumn("25%","orgName","所属组织机构");
		grid.addColumn("25%","roleId","所属角色");
		grid.addColumn("15%","signature","签章",kendo.template($("#qzTemplate").html()));
		return grid.addColumn("15%","handle","操作",kendo.template($("#editTemplate").html()));
	},
	
	/**
	 * 创建用户列表
	 */
	createUserGrid : function(){
		var columns = employee.createUserColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestUserData : function(){
		common.init("../user/list","POST",employee.bindUserGrid);
		common.do_submit();
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(result){
		if(result.value == 'success'){
			layer.close(1);
			layer.msg('删除成功');
			common.init("../user/list","POST",employee.bindUserGrid);
			common.do_submit();
		}else if(result.value == 'org'){
			layer.msg('该人员已经被组织使用，不能删除！');
		}else if(result.value == 'role'){
			layer.msg('该人员已经被流程角色使用，不能删除！');
		}else if(result.value == 'workflow'){
			layer.msg('该人员已经被流程角色使用，不能删除！');
		}
		
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindUserGrid : function(result){
		grid.bindData(result);
	},
	
	/**
	 * 跳转到用户增加页面
	 */
	toUserAddPage : function(){
		common.toPage("../user/edit_init");
	},
	
	/**
	 * 删除用户
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
			var users = [];
			$.each(rowDatas,function(i,item){
				var id = $("#user").data("kendoGrid").dataItem(item).id;
				var user = {};
				user.id = id;
				users.push(user);
			});
			var param = JSON.stringify(users);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("user",param);
			common.init("../user/remove","POST",employee.removeSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
	},
	
	/**
	 * 跳转到签章查看页面
	 */
	toUserSignaturePage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取用户ID
		var id = rowData.id;
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取目录ID
		var id = rowData.id;
		//判断是否有图片
		if(rowData.userImg != null && rowData.userImg != ""){
			// 跳转到用户编辑页面
			common.toPage("../user/signature_init?id="+id);
		}else{
			layer.msg("该用户还没有上传签章图片！");
			return;
		}
		
	},
	
	/**
	 * 跳转到用户编辑页面
	 */
	toUserEditPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取用户ID
		var id = rowData.id;
		// 跳转到用户编辑页面
		common.toPage("../user/edit_init?id="+id);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加用户窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			employee.toUserAddPage();
		});
		
		/** 
		 * 绑定删除用户窗口按钮的click事件
		 */
		$("#remove").on("click",function(){
			employee.removeUsers();
		});
		
		/** 绑定刷新用户列表窗口按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
		
		/** 绑定导出用户窗口按钮的click事件*/
		$("#export").on("click",function(){
			window.location.href="../detaildownload/exceluser";
		});
	}
}