var url = null;
var type = null;
var columns = [];
var ajax_data = {};
var success_hanlder = {};
var common = {
	init : function(url, type, success_hanlder) {
		this.url = url;
		this.type = type;
		if(this.success_hanlder == null){
			this.success_hanlder = {};
		}
		this.success_hanlder[url] = success_hanlder;
	},

	add_checkbox : function(template, id, onclick) {
		var col = {};
		col.field = "selected";
		col.width = "27px";
		col.navigatable = false;
		col.scrollable = false;
		col.groupable = false;
		col.sortable = false;
		col.filterable = false;
		col.reorderable = false;
		col.resizable = false;
		col.title = "<input type='checkbox' id='" + id + "' onclick='"
				+ onclick + "'>";
		col.template = kendo.template(template.html());
		col.attributes = {
			"style" : "text-align: center;"
		};
		return col;
	},
	add_column : function(width, field, title, template, footerTemplate,
			format, attributes) {
		if (columns.length == 0) {
			var col = common.add_checkbox($("#tmplt_name_checkbox"),
					"check_all", "common.bind_select_all_row(this)");
			columns.push(col);
		}
		var col = {};
		var filterable = {};
		var cell = {};
		cell.showOperators = false;
		cell.operator = "contains";
		cell.width = "100%";
		filterable.cell = cell;
		if (template != null) {
			col.template = template;
			col.width = width;
			col.field = field;
			col.title = title;
			filterable = false;
		} else {
			col.width = width;
			col.field = field;
			col.title = title;
		}
		col.filterable = filterable;
		if (footerTemplate != null) {
			col.footerTemplate = footerTemplate;
		}
		col.format = format;
		if (attributes != null) {
			col.attributes = attributes;
		}
		columns.push(col);
		return columns;
	},

	reset_column : function() {
		columns = [];
	},

	add_param : function(key, value) {
		ajax_data[key] = value;
		return ajax_data;
	},

	reset_param : function() {
		ajax_data = {};
	},

	init_grid : function(dv_grid, columns, filterable, showRowNoAble) {
		if (showRowNoAble) {
			var col = {};
			col.template = "<span class='row-number'></span>";
			col.field = "rowNumber";
			col.title = "序号";
			col.width = "40px";
			col.filterable = false;
			col.sortable = false;
			col.headerAttributes = {
					 style: "margin:auto auto auto auto;font-size: 12px;font-weight:bold;font-family:Microsoft YaHei"
			};
			if (columns[0].field == 'selected') {
				columns.splice(1, 0, col);
			}
			else{
				columns.splice(0, 0, col);
			}
		}
		var gridIns = dv_grid.kendoGrid({
			columnResizeHandleWidth: 6,
			selectable : "multiple row",
			navigatable : true,
			scrollable : true,
			groupable : false,
			sortable : true,
			allowCopy : true,
			filterable : {
				extra: false,
				mode : filterable,
				messages : {
					info: "按以下条件过滤: ",
					filter: "筛选",//过滤按钮
					clear: "清除",//清空按钮
				},
				operators : {
					//字符串型
					string : {
						contains : "包  含",
						eq : "等  于",
						neq : "不等于",
					},
					//数值型
					number : {
						eq : "等于",
						neq : "不等于",
						gt : "大于",//大于
						lt : "小于"//小于
					},
					//日期型
					date : {
						gt : "大于",
						lt : "小于",
						eq : "等于",
						neq : "不等于",
					},
					//枚举型，
					enums : {
						eq : "等于",
						neq : "不等于"
					}
				},
			},
			reorderable : true,
			resizable : true,
			columnMenu : false,
			pageable : {
				pageSizes : true,
				buttonCount : 5,
				pageSize : 50
			},
			dataBound : function() {
				// 重新设置事件
				grid.resetEvents();
				// 增加序号
				if (!showRowNoAble) {
					return;
				}
				if (this.pager != null) {
					var rows = this.items();
					var page = this.pager.page() - 1;
					var pagesize = this.pager.pageSize();
					$(rows).each(function() {
						var index = $(this).index() + 1 + page * pagesize;
						var rowLabel = $(this).find(".row-number");
						$(rowLabel).html(index);
					});
				} else {
					this.hideColumn("rowNumber");
				}
			},
			columns : columns
		});

		gridIns.on("click", ".k-grid-content tr", function(e) {
			if (e.target.type == "checkbox") {
				return;
			}
			var child = e.toElement;
			var row = $(child).parent();
			var input = $(row).find("input");
			if (!$(input).prop("checked")) {
				$(input).prop("checked", true);
			} else {
				$(input).prop("checked", false);
			}
			dv_grid.data("kendoGrid").clearSelection();
			common.select_checked_row(row);
		});
	},
	
	hasLoading : false,
	
	/**
	 * 执行提交
	 */
	do_submit : function(data,isAsync) {
		if (common.hasLoading == false) {
			loading.init();
			common.hasLoading = true;
		}
		if (isAsync != false) {
			isAsync = true;
		}
		var success_hanlder = this.success_hanlder[this.url];
		$.ajax({
			type : this.type,
			url : this.url,
			async : isAsync,
			dataType : "json",
			data : data,
			success : function(result){
				success_hanlder.apply(this,[result]);
				loading.close();
				common.hasLoading = false;
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				common.reset_column();
				common.reset_param();
				loading.close();
				common.hasLoading = false;
			}
		});
		common.reset_param();
	},

	/**
	 * 获取选择行的数据对象
	 */
	get_select_row_data : function(dv_grid) {
		var grid = dv_grid.data("kendoGrid");
		var selectedRows = grid.select();
		if (selectedRows != null) {
			var rowData = grid.dataItem(selectedRows[0]);
			return rowData;
		}
		return null;
	},

	/**
	 * 获取所有行的数据对象
	 */
	get_all_row_data : function(dv_grid) {
		try {
			var grid = dv_grid.data("kendoGrid");
			var rows = grid._data;
			return rows;
		} catch (e) {
			return null;
		}
	},

	/**
	 * 获取所有行对象
	 */
	get_all_rows : function(dv_grid) {
		var grid = dv_grid.data("kendoGrid");
		return grid.tbody.find(">tr:not(.k-grouping-row)");
	},

	/**
	 * 获取选择行的数据对象
	 */
	get_select_rows_data : function(dv_grid) {
		var grid = dv_grid.data("kendoGrid");
		var selectedRows = grid.select();
		return selectedRows;
	},

	/** 绑定数据表双击事件*/
	dbclick : function(dv_grid, dbclick_success) {
		var grid = dv_grid.data("kendoGrid");
		grid.table.on("dblclick", "tr", dbclick_success);
	},

	/** 选择行*/
	select_row : function(grid, row) {
		var kendoGrid = grid.data("kendoGrid");
		kendoGrid.select(row);
	},

	/** 取消选择*/
	remove_row_select : function(row) {
		var dataUid = $(row).attr("data-uid");
		var rows = $(".bg-grid-container").find("tr[data-uid='"+dataUid+"']");
		for (var int = 0; int < rows.length; int++) {
			$(rows[int]).removeClass("k-state-selected");
			$(rows[int]).attr("aria-selected", false);
		}
	},

	/** 选择checkbox 选择当前行*/
	check_box_click : function(obj) {
		var checked = $(obj).prop("checked");
		if (checked == true) {
			var row = $(obj).parent().parent();
			var grid = $(obj).parents(".bg-grid-container")[0];
			var kendoGrid = $(grid);
			common.select_row(kendoGrid, row);
		} else {
			var row = $(obj).parent().parent();
			common.remove_row_select(row);
		}
	},

	/** 根据当前点击的行查询全部选中选择按钮的行 并且进行select*/
	select_checked_row : function(row) {
		var grid = row.parents(".bg-grid-container")[0];
		var checkboxs = $(grid).find("input:checkbox:checked");
		$.each(checkboxs, function(i, item) {
			var row = $(item).parent().parent();
			common.select_row($(grid), row);
		});
	},

	bind_select_all_row : function(obj) {
		var grid = $(obj).parents(".bg-grid-container")[0];
		var checkboxs = $(grid).find("input:checkbox");
		if ($(obj).prop("checked")) {
			$.each(checkboxs, function(i, item) {
				$(item).prop("checked", true);
				var row = $(item).parent().parent();
				common.select_row($(grid), row);
			});
		} else {
			checkboxs.prop("checked", false);
			$(grid).data("kendoGrid").clearSelection();
		}
	},

	/**
	 * 只能选择一行的校验方法
	 */
	row_num_valid : function(grid) {
		var rowDatas = common.get_select_rows(grid);
		if (rowDatas == null) {
			layer.msg('未选择行！', {
				icon : 2,
				time : 1000
			});
			return false;
		} else {
			if (rowDatas.length == 0) {
				layer.msg('未选择行！', {
					icon : 2,
					time : 1000
				});
				return false;
			} else if (rowDatas.length > 1) {
				layer.msg('最多选择一行进行操作！', {
					icon : 2,
					time : 1000
				});
				return false;
			} else {
				return true;
			}
		}
	},

	remove_row : function(grid, row) {
		var kendoGrid = grid.data("kendoGrid");
		kendoGrid.removeRow(row);
	},

	/** tree============================================*/

	tree_get_all_rows : function(tree) {
		var treelist = tree.data("kendoTreeList");
		var rows = treelist.content.find("tr");
		return rows;
	},

	tree_select_row : function(tree, row) {
		var treeList = tree.data("kendoTreeList");
		treeList.select(row);
	},

	/** 根据当前点击的行查询全部选中选择按钮的行 并且进行select*/
	tree_select_checked_row : function(row) {
		var tree = row.parents(".bg-grid-container")[0];
		var checkboxs = $(tree).find("input:checkbox:checked");
		$.each(checkboxs, function(i, item) {
			var row = $(item).parent().parent();
			common.tree_select_row($(tree), row);
		});
	},

	/** 取消选择*/
	tree_remove_row_select : function(row) {
		row.removeClass("k-state-selected");
		row.attr("aria-selected", false);
	},

	/** 选择checkbox 选择当前行*/
	tree_check_box_click : function(obj) {
		var checked = $(obj).prop("checked");
		if (checked == true) {
			var row = $(obj).parent().parent();
			var tree = $(obj).parents(".bg-grid-container")[0];
			var kendoTree = $(tree);
			common.tree_select_row(kendoTree, row);
		} else {
			var row = $(obj).parent().parent();
			common.tree_remove_row_select(row);
		}
	},

	/** 选择孩子节点*/
	tree_check_children : function(obj) {
		var checked = $(obj).prop("checked");
		// 获取到当前行
		var row = $(obj).parent().parent();
		var tree = $(obj).parents(".bg-grid-container")[0];
		var navId = $(tree).data("kendoTreeList").dataItem($(row)).id;
		var navFid = $(tree).data("kendoTreeList").dataItem($(row)).parentId;
		common.tree_check_box_click(obj);
		// 获取当前行的子节点
		var rows = $(row).nextAll();
		$.each(rows,
				function(i, item) {
					var itemNavFid = $(tree).data("kendoTreeList").dataItem(
							$(item)).parentId;
					if (navId == itemNavFid) {
						if (checked) {
							common.tree_select_row($(tree), $(item));
							var checkbox = $(item).find("input:checkbox");
							$(checkbox).prop("checked", true);
						} else {
							common.tree_remove_row_select($(item));
							var checkbox = $(item).find("input:checkbox");
							$(checkbox).prop("checked", false);
						}
						common.tree_check_children(checkbox);
					}
				});
	},

	/** 选择父亲节点*/
	tree_check_parent : function(obj) {
		var checked = $(obj).prop("checked");
		// 获取到当前行
		var row = $(obj).parent().parent();
		var tree = $(obj).parents(".bg-grid-container")[0];
		var navFid = $(tree).data("kendoTreeList").dataItem($(row)).parentId;
		common.tree_check_box_click(obj);
		// 获取当前行的子节点
		var rows = $(row).prevAll();
		$.each(rows, function(i, item) {
			var itemNavId = $(tree).data("kendoTreeList").dataItem($(item)).id;
			if (navFid == itemNavId) {
				if (checked) {
					common.tree_select_row($(tree), $(item));
					var checkbox = $(item).find("input:checkbox");
					$(checkbox).prop("checked", true);
				}
				common.tree_check_parent(checkbox);
			}
		});
	},

	/** 获取树选择行*/
	tree_get_select_rows : function(dv_tree) {
		var tree = dv_tree.data("kendoTreeList");
		var selectedRows = tree.select();
		return selectedRows;
	},

	/** 获取树选择一行*/
	tree_get_row_data : function(dv_tree) {
		var tree = dv_tree.data("kendoTreeList");
		var selectedRows = tree.select();
		if (selectedRows != null) {
			var rowData = tree.dataItem(selectedRows[0]);
			return rowData;
		}
		return null;
	},

	/** 将金额格式的,替换为空 */
	convertSimpleMoney : function(money) {
		if (money != null && money != "") {
			money = money.replace(/,/g, "");
		}
		return money;
	},

	/** 格式化金额 */
	formatMoney : function(s, type) {
		if (/[^0-9\.]/.test(s))
			return "0";
		if (s == null || s == "")
			return "0";
		s = s.toString().replace(/^(\d*)$/, "$1.");
		s = (s + "00").replace(/(\d*\.\d\d)\d*/, "$1");
		s = s.replace(".", ",");
		var re = /(\d)(\d{3},)/;
		while (re.test(s))
			s = s.replace(re, "$1,$2");
		s = s.replace(/,(\d\d)$/, ".$1");
		if (type == 0) {// 不带小数位(默认是有小数位)  
			var a = s.split(".");
			if (a[1] == "00") {
				s = a[0];
			}
		}
		return s;
	},
	
	/**
	 * 跳转
	 */
	toPage:function(href){
		var parentPage = parent.$(".page.in.active");
		if (parentPage == null||parentPage.length==0) {
			parentPage = parent.$("#page");
		}
		if (parentPage == null||parentPage.length==0) {
			window.location.href = href;
		}
		else{
			parentPage.prop("src",href);
		}
	}
}
