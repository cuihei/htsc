$(function(){
	auditrole.init();
})

/** 绑定编辑用户窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	auditrole.toUserEditPageEdit(tr);
}

var auditrole = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("auditrole");
		loading.init();
		try{
			//创建角色列表
			auditrole.createUserGrid();
			//请求角色列表数据
			auditrole.requestUserData();
			//绑定页面事件
			auditrole.bindPageEvent();
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
		grid.addColumn("25%","name","组名称");
		var deleteBtn = "<button class='btn btn-danger bk-margin-5'  name='remove' title='删除'><i class='fa fa-times' ></i></button>";
		grid.addColumn("25%","handle","操作",kendo.template($("#editTemplate").html()+deleteBtn));
		return grid.addColumn("10%","allot","选择委托人",kendo.template($("#delegateTemplate").html()));
	},
	
	/**
	 * 创建用户列表
	 */
	createUserGrid : function(){
		var columns = auditrole.createUserColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestUserData : function(){
		common.init("../auditrole/list","POST",auditrole.bindUserGrid);
		common.do_submit();
	},
	
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindUserGrid : function(result){
		grid.bindData(result);
		/** 绑定编辑用户窗口按钮的click事件*/
		var editBtns = $("button[name='remove']");
		$.each(editBtns,function(i,item){
			$(item).on("click",function(){
				var tr = $(item).parent().parent();
				auditrole.toUserEditPage(tr);
			});
		});
		//grid.setEvents();
	},
	
	/**
	 * 跳转到用户增加页面
	 */
	toUserAddPage : function(){
		common.toPage("../auditrole/edit_init");
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(result){
		grid.init("auditrole");
		var list=result.value;
		layer.close();
		if(list.length == 0){
			layer.msg('删除成功');
		}else{
			for(var i=0;i<list.length;i++){
				var name = list[i].name;
				var taskname = list[i].taskname;
				layer.msg(name+'删除失败,被'+taskname+'占用!');
			}
		}
		
		common.init("../auditrole/list","POST",auditrole.bindUserGrid);
		common.do_submit();
	},
	
	/**
	 * 删除
	 */
	toUserEditPage : function(tr){
		layer.confirm('确认要删除吗？',function(index){
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			// 获取用户ID
			var id = rowData.id;
			var users = [];
			var auditroles={};
			auditroles.id=id;
			users.push(auditroles);
			var param = JSON.stringify(users);
			var data = common.add_param("auditrole",param);
			common.init("../auditrole/remove","POST",auditrole.removeSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
	},
	
	/**
	 * 修改
	 */
	toUserEditPageEdit : function(tr){
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			// 获取用户ID
			var id = rowData.id;
			common.toPage("../auditrole/edit_init?id="+id);
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
				var id = $("#auditrole").data("kendoGrid").dataItem(item).id;
				var user = {};
				user.id = id;
				users.push(user);
			});
			var param = JSON.stringify(users);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("auditrole",param);
			common.init("../auditrole/remove","POST",auditrole.removeSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
	},
	/**
	 * 分配用户
	 */
	allotUsers : function(){
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length<=0) {
			layer.msg('未选择任何行!', {icon:5,time:1000});
			return;
		}
		if (rowDatas.length!=1) {
			layer.msg('请选择一行!', {icon:5,time:1000});
			return;
		}
		var id = "";
		$.each(rowDatas,function(i,item){
			id = $("#auditrole").data("kendoGrid").dataItem(item).id;
		});
		if(id != ""){
			common.toPage("../auditrole/allotaudit?id="+id);
		}else{
			layer.msg('请选择一行!', {icon:5,time:1000});
		}
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加用户窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			auditrole.toUserAddPage();
		});
		/** 
		 * 绑定分配用户窗口按钮的click事件
		 */
		$("#edit").on("click",function(){
			auditrole.allotUsers();
		});
		/** 
		 * 绑定删除用户窗口按钮的click事件
		 */
		$("#remove").on("click",function(){
			auditrole.removeUsers();
		});
	},
	
	/**
	 * 选择委托人
	 */
	allotDelegateUser:function(obj){
		var tr = $(obj).parent().parent();
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取用户ID
		var groupId = rowData.id;
		var groupName = rowData.name;
		// 跳转到选择委托人页面
		common.toPage("../auditrole/allot_delegate_user_init?groupId="+groupId+"&groupName="+groupName);
	}
}