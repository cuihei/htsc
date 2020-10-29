$(function(){
	role_user.init();
})

var role_user = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("roleUser");
		loading.init();
		try{
			role_user.createUserGrid();
			role_user.requestUserData();
			role_user.bindPageEvent();
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
		grid.addColumn("30%","userNo","登陆账号");
		return grid.addColumn("30%","userName","用户名称");
	},
	
	/**
	 * 创建用户列表
	 */
	createUserGrid : function(){
		var columns = role_user.createUserColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestUserData : function(){
		 var role = {};
		 role.roleId = $("#roleId").val();
		 var roleJson = JSON.stringify(role);
		 var param = common.add_param("role",roleJson);
		 common.init("../role/userlist","POST",role_user.bindUserGrid);
		 common.do_submit(param);
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindUserGrid : function(result){
		if (result.code != 1) {
			layer.msg(result.value);
			loading.close();
			return;
		}
		var grid = $("#roleUser").data("kendoGrid");
		var height = $(document.body).height()-50;
		grid.setOptions({
			height:height,
			dataSource: result.value,
			groupable: false,
			pageable:false
		});

		loading.close();
		var rows = common.get_all_rows($("#roleUser"));
		// 循环判断是否是该角色的人员
    	$.each(rows,function(i,item){
    		if ($("#roleUser").data("kendoGrid").dataItem(item).roleId==$("#roleId").val()) {
    			common.select_row($("#roleUser"),item);
    			var checkbox = $(item).find("input:checkbox");
    			checkbox.prop("checked",true);
			}
    	});
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		$("#submit").on("click",function(){
			 var users = [];
			 var rowDatas = grid.getSelectRowsData();
				$.each(rowDatas,function(i,item){
					var id = $("#roleUser").data("kendoGrid").dataItem(item).id;
					var user = {};
					user.id = id;
					users.push(user);
				});
			 var userJson = JSON.stringify(users);
			 common.add_param("roleId",$("#roleId").val());
			 var param = common.add_param("users",userJson);
			 common.init("../role/addusers","POST",function(result){
				 layer.msg('用户分配成功！');
				 //调回角色列表显示页面
				 common.toPage("../role/index");
			 });
			 common.do_submit(param);
		});
		
		/** 
		 * 绑定取消窗口按钮的click事件
		 */
		$("#back").on("click",function(){
			history.back();
		});
	}
}