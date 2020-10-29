$(function(){
	org_user.init();
})

var org_user = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("orgUser");
		loading.init();
		try{
			org_user.createUserGrid();
			org_user.requestUserData();
			org_user.bindPageEvent();
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
		return grid.addColumn("25%","orgName","所属组织机构");
	},
	
	/**
	 * 创建用户列表
	 */
	createUserGrid : function(){
		var columns = org_user.createUserColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestUserData : function(){
		 orgId = $("#orgId").val();
		 var param = common.add_param("org",orgId);
		 common.init("../org/userlist","POST",org_user.bindUserGrid);
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
		var grid = $("#orgUser").data("kendoGrid");
		var height = $(document.body).height()-50;
		grid.setOptions({
			height:height,
			dataSource: result.value,
			groupable: false,
			pageable:false
		});

		loading.close();
		var rows = common.get_all_rows($("#orgUser"));
		// 循环判断是否是该组织的人员
    	$.each(rows,function(i,item){
    		if ($("#orgUser").data("kendoGrid").dataItem(item).orgId==$("#orgId").val()) {
    			common.select_row($("#orgUser"),item);
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
					var id = $("#orgUser").data("kendoGrid").dataItem(item).id;
					var user = {};
					user.id = id;
					users.push(user);
				});
			 var userJson = JSON.stringify(users);
			 common.add_param("orgId",$("#orgId").val());
			 var param = common.add_param("users",userJson);
			 common.init("../org/addusers","POST",function(result){
				 layer.msg('用户分配成功！');
				 //调回组织列表显示页面
				 common.toPage("../org/index");
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