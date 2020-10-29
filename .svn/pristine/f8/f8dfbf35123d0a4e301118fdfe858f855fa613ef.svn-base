$(function() {
	allotDelegateUser.init();
})

var allotDelegateUser = {
	/**
	 * 初始化
	 */
	init : function() {
		grid.init("delegateUser");
		loading.init();
		try {
			// 创建角色列表
			allotDelegateUser.createUserGrid();
			// 请求角色列表数据
			allotDelegateUser.requestUserData();
			// 绑定页面事件
			allotDelegateUser.bindPageEvent();
		} catch (err) {
			loading.close();
		}
	},
	/**
	 * 构建用户列表列集合
	 */
	createUserColumns : function() {
		grid.resetColumn();
		return grid.addColumn("45%", "firstName", "用户名称");
	},

	/**
	 * 创建用户列表
	 */
	createUserGrid : function() {
		var columns = allotDelegateUser.createUserColumns();
		grid.createGrid(columns);
	},

	/**
	 * 发送数据请求
	 */
	requestUserData : function() {
		var groupId = $("#groupId").val();
		var data = common.add_param("groupId", groupId);
		common.init("../auditrole/allot_delegate_user_list", "POST",
				allotDelegateUser.bindUserGrid);
		common.do_submit(data);
	},

	/**
	 * 接收服务器响应数据,绑定表格 这是一个回调函数，不用手动调用
	 */
	bindUserGrid : function(result) {
		if (result.code == 0) {
			layer.msg(result.value, {
				icon : 5,
				time : 1000
			});
			return;
		}
		// 用户对象
		var userMap = result.value;
		var all = userMap.all;
		var select = userMap.select;
		var selects = []; // 存放选中UID 以便下边两个each过滤
		$.each(select, function(i, item) {
			selects.push(item.userId);
		});

		// 循环置顶
		var onelist = []; // 存放置顶
		var twolist = []; // 存放不指定
		$.each(all, function(i, item) {
			var ids = item.id;
			if ($.inArray(ids, selects) != -1) {
				onelist.push(item);
			} else {
				twolist.push(item);
			}
		});

		// 合并
		onelist = onelist.concat(twolist);
		var obj = {};
		obj.code = 1;
		obj.value = onelist;
		grid.bindData(obj);

		// 判断是否选中
		var rows = common.get_all_rows($("#delegateUser"));
		$.each(rows, function(i, item) {
			var ids = $("#delegateUser").data("kendoGrid").dataItem(item).id;
			if ($.inArray(ids, selects) != -1) {
				common.select_row($("#delegateUser"), item);
				var checkbox = $(item).find("input:checkbox");
				checkbox.prop("checked", true);
			}
		});
	},

	/**
	 * 分配用户
	 */
	bindUsers : function() {
		// 获取Grid的选中行
		var rowDatas = grid.getSelectRowsData();
		var groupId = $("#groupId").val();
		var userIds = [];
		$.each(rowDatas, function(i, item) {
			var id = $("#delegateUser").data("kendoGrid").dataItem(item).id;
			userIds.push(id);
		});
		// 添加参数 @param 参数key；参数value
		common.add_param("groupId", groupId);
		var data = common.add_param("userIds", JSON.stringify(userIds));
		common.init("../auditrole/allot_delegate_user", "POST",
				function(result) {
					if (result.code==0) {
						parent.layer.msg(result.value);
						return;
					}
					// 调回用户列表显示页面
					common.toPage("../auditrole/index");
					parent.layer.msg("分配成功");
				});
		// 执行提交操作
		common.do_submit(data);
	},

	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function() {
		/**
		 * 绑定增加用户窗口按钮的click事件
		 */
		$("#submit").one("click", function() {
			allotDelegateUser.bindUsers();
		});

		$("#back").one("click", function() {
			common.toPage("../auditrole/index");
		});
	}
}