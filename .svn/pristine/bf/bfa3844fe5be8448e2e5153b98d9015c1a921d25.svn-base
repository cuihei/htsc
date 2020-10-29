$(function(){
	allotaudit.init();
})

var allotaudit = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("auditrole");
		loading.init();
		try{
			//创建角色列表
			allotaudit.createUserGrid();
			//请求角色列表数据
			allotaudit.requestUserData();
			//绑定页面事件
			allotaudit.bindPageEvent();
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
		grid.addColumn("45%","email","登陆账号");
		return grid.addColumn("45%","firstName","用户名称");
	},
	
	/**
	 * 创建用户列表
	 */
	createUserGrid : function(){
		var columns = allotaudit.createUserColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestUserData : function(){
		var groupId = $("#GroupId").val();
		if(groupId == ""){
			layer.msg('非法请求!', {icon:5,time:1000});
			//调回用户列表显示页面
			common.toPage("../auditrole/index");
		}
		var data = common.add_param("GroupId",groupId);
		common.init("../auditrole/alluser","POST",allotaudit.bindUserGrid);
		common.do_submit(data);
	},
	
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindUserGrid : function(result){
		if (result.code == 0) {
			layer.msg('非法请求!', {icon:5,time:1000});
			common.toPage("../auditrole/index");
		}
		var grid1 = $("#auditrole").data("kendoGrid");
		var height = $(document.body).height()-50;
		grid1.setOptions({
			height:height,
			dataSource: result.value,
			groupable: false,
			pageable:false
		});
		
		// 用户对象
		var userMap = result.value;
		var all = userMap.all;
		var select = userMap.select;
		var selects=[]; //存放选中UID 以便下边两个each过滤
		$.each(select,function(i,item){ 
			selects.push(item.id);
		});
		
		//循环置顶
		var onelist=[]; //存放置顶
		var twolist=[]; //存放不指定
		$.each(all,function(i,item){
			var ids=item.id;
    		if ($.inArray(ids,selects) != -1) {
    			onelist.push(item);
    		}else{
    			twolist.push(item);
    		}
		});
		
		//合并
		onelist=onelist.concat(twolist);
		var obj={};
		obj.code=1;
		obj.value=onelist;
		grid.bindData(obj);
		
		//判断是否选中
		var rows = common.get_all_rows($("#auditrole"));
		$.each(rows,function(i,item){
			var ids=$("#auditrole").data("kendoGrid").dataItem(item).id;
    		if ($.inArray(ids,selects) != -1) {
    			common.select_row($("#auditrole"),item);
    			var checkbox = $(item).find("input:checkbox");
    			checkbox.prop("checked",true);
			}
    	});
		//grid.setEvents();
	},
	
	/**
	 * 分配用户
	 */
	BindUsers : function(){
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length<=0) {
			layer.msg('未选择任何行!', {icon:5,time:1000});
			return;
		}
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var groupId=$("#GroupId").val();
			if(groupId == ""){
				layer.msg('非法请求!', {icon:5,time:1000});
				//调回用户列表显示页面
				common.toPage("../auditrole/index");
			}
			var users = [];
			$.each(rowDatas,function(i,item){
				var id = $("#auditrole").data("kendoGrid").dataItem(item).id;
				var user = {};
				user.groupid = groupId;
				user.uid = id;
				users.push(user);
			});
			var param = JSON.stringify(users);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("grouanduser",param);
			common.init("../auditrole/binguser","POST",function(result){
				//调回用户列表显示页面
				common.toPage("../auditrole/index");
			 });
			// 执行提交操作
			common.do_submit(data);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加用户窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			allotaudit.BindUsers();
		});
		
		$("#back").on("click",function(){
			common.toPage("../auditrole/index");
		});
	}
}