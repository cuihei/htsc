$(function() {
	application.init();
});

var application = {

	/**
	 * 初始化
	 */

	init : function() {
		loading.init();
		try {
			application.createaApplicatonGrid();
			application.requestApplicatonData();
			application.bindPageEvent();
		} catch (err) {
			loading.close();
		}
	},

	createaApplicatonGrid : function() {

		// 绑定数据 @param 绑定的grid对象；显示列集合对象
		$("#application").kendoTreeList({
			height : $(document.body).height()-120,
			resizable : true,
			reorderable : true,
			selectable : true,
			pageable : true,
			sortable : true,
			filterable : {
				mode : "row"
			},
			columns : [ {
				field : "appCode",
				expandable : true,
				title : "资源编码",
				attributes : {
					"style" : "text-align: left;"
				}
			}, {
				field : "appName",
				expandable : false,
				title : "资源名称",
				attributes : {
					"style" : "text-align: left;"
				}
			}, {
				field : "appUrl",
				expandable : false,
				title : "资源URl",
				attributes : {
					"style" : "text-align: left;"
				}
			}, {
				field : "appType",
				expandable : false,
				title : "资源类别名称",
				attributes : {
					"style" : "text-align: left;"
				}
			}, {
				field : "appContent",
				expandable : false,
				title : "资源内容",
				attributes : {
					"style" : "text-align: left;"
				}
			}, ]
		});
	},

	requestApplicatonData : function() {
		var dataSource = new kendo.data.TreeListDataSource({
			transport : {
				read : {
					url : "../app/list",
					dataType : "json"
				}
			},
			schema : {
				model : {
					id : "id",
					fields : {
						id : {
							type : "string",
							nullable : false,
							resizable : true
						},
						parentId : {
							field : "appParentId",
							nullable : true
						}
					},
					expanded : true
				}
			}
		});
		var tree = $("#application").data("kendoTreeList");
		tree.setDataSource(dataSource);
		var gridInst = $("#application").data("kendoTreeList");
		var height = $(document.body).height()-($(document.body).height())*0.27;
		gridInst.setOptions({
			height:height,
		});
		loading.close(1);
	},

	/**
	 * 删除成功
	 */
	removeSuccess : function(result) {
		if (result.value == 'success') {
			layer.close(1);
			layer.msg('删除成功', {
				icon : 1,
				time : 1000
			});
			layer.close(1);
			application.requestApplicatonData();
		} else if (result.value == 'del') {
			layer.msg('资源已经分配，不可删除！');
		}
	},

	/**
	 * 接收服务器响应数据,绑定表格 这是一个回调函数，不用手动调用
	 */
	bindapplicatonGrid : function(result) {
		grid.bindData(result);
	},

	/**
	 * 跳转到资料类别增加页面
	 */
	toApplicatonAddPage : function() {
		common.toPage("../app/edit_init");
	},

	/**
	 * 删除资料类别
	 */
	removeApplicatons : function() {
		// 获取grid选中行
		var rowData = common.tree_get_row_data($("#application"));
		if (rowData == null) {
			layer.msg('未选择行！', {
				icon : 2,
				time : 1000
			});
			return false;
		}
		/* 删除 */
		layer.confirm('确认要删除吗？', function(index) {
			// 获取Grid的选中行
			var rowData = common.tree_get_row_data($("#application"));
			var app = {};
			app.id = rowData.id;
			// 添加参数 @param 参数key；参数value
			var appJson = JSON.stringify(app);
			var param = common.add_param("application", appJson);
			common.init("../app/remove", "POST", application.removeSuccess);
			// 执行提交操作
			common.do_submit(param);
		});
	},

	/**
	 * 跳转到资料类别编辑页面
	 */
	toApplicatonEditPage : function(tr) {
		// 获取grid选中行
		var rowData = common.tree_get_row_data($("#application"));
		if (rowData == null) {
			layer.msg('未选择行！', {
				icon : 2,
				time : 500
			});
			return false;
		}
		// 获取选中行数据对象
		var rowData = common.tree_get_row_data($("#application"));
		// 获取资料类别ID
		var id = rowData.id;
		// 跳转到资料类别编辑页面
		common.toPage("../app/edit_init?id=" + id);
	},

	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function() {
		/**
		 * 绑定增加资料类别窗口按钮的click事件
		 */
		$("#add").on("click", function() {
			application.toApplicatonAddPage();
		});

		/**
		 * 绑定编辑组织机构窗口按钮的click事件
		 */
		$("#edit").on("click", function() {
			application.toApplicatonEditPage();
		});

		/**
		 * 绑定删除资料类别窗口按钮的click事件
		 */
		$("#remove").on("click", function() {
			application.removeApplicatons();
		});

		/** 绑定刷新资料类别列表窗口按钮的click事件 */
		$("#refresh").on("click", function() {
			window.location.reload();
		});

		/** 绑定导出按钮的额click事件 */
		$("#export").on("click", function() {
			window.location.href = "../app/export";
			layer.msg("导出成功");
		});

	},
}