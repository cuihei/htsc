$(function(){
	org.init();
});

/** 绑定编辑用户窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	org.toOrgEditPage(tr);
}

var org = {
			
		/**
		 * 初始化
		 */
		init : function(){
			grid.init("org");
			loading.init();
			try{
				org.createOrgGrid();
				org.requestOrgData();
				org.bindPageEvent();
			}
			catch(err){
				loading.close();
			}
		},
		
		createOrgGrid:function(){
		
			// 绑定数据 @param 绑定的grid对象；显示列集合对象
			$("#org").kendoTreeList({
				height : $(document.body).height()-120,
				resizable : true,
				reorderable : true,
				selectable : true,
				pageable : true,
				sortable : true,
				filterable : {
					mode : "row"
				},
	            columns: [
						{ field: "orgName", title: "组织机构名称", 
							attributes : {
							"style" : "text-align: left;"
						}},
						{ field: "handle", title: "操作",template: kendo.template($("#editTemplate").html()),
							}
	            ]
	        });
		},
		
		/**
		 * 请求组织机构列表数据，绑定数据
		 * */ 
		requestOrgData:function(){
			 common.init("../org/list","POST",function(result){
				    var dataSource = new kendo.data.TreeListDataSource({
						data : result,
		                schema: {
		                    model: {
		                    	 id: "id",
		 	                    fields: {
		 		                	 id: { type: "string", nullable: false,resizable : true},
		 		                     parentId: { field: "parentId", nullable: true }
		 	                    },
		                        expanded: true
		                    }
		                }
		            });
				    var tree = $("#org").data("kendoTreeList");
					tree.setDataSource(dataSource);
					var rows = common.tree_get_all_rows($("#org"));
					// 隐藏根目录和公共部门的编辑按钮
		        	$.each(rows,function(i,item){
		        		if ($("#org").data("kendoTreeList").dataItem(item).orgName=='上海海图中心'||$("#org").data("kendoTreeList").dataItem(item).orgName=='公共部门') {
		        			$(item).find("button[name='editOrg']").hide();
						}
		        	});
		        	$.each(rows,function(i,item){
		        		if ($("#org").data("kendoTreeList").dataItem(item).orgName=='公共部门') {
		        			$(item).hide();
						}
		        	});
			 });
			 common.do_submit();
			 loading.close(1);
		},
		
		/**
		 * 删除成功
		 * */
		removeSuccess : function(){
			layer.msg('已删除',{icon:2,time:1500});
			org.requestOrgData();
			layer.close(1);
		},
		
		
		/**
		 * 接收服务器响应数据,绑定表格
		 * 这是一个回调函数，不用手动调用
		 */
		bindOrgGrid : function(result){
			grid.bindData(result);
		},
		
		/**
		 * 跳转到组织机构增加页面
		 */
		toOrgAddPage : function(){
			common.toPage("../org/edit_init");
		},
		
		/**
		 * 删除组织机构
		 */
		removeOrgs : function(){
			// 获取grid选中行
			var rowData = common.tree_get_row_data($("#org"));
			if(rowData == null) {
				layer.msg('未选择行！', {icon:2,time:1000});
				return false;
			}
			if(rowData.orgName == "公共部门"){
				layer.msg('不能删除公共部门！', {icon:2,time:1000});
				return false;
			}
			/*删除*/
			layer.confirm('您将删除该机构下的所有子机构和人员，确认要删除吗？',function(index){
				// 获取Grid的选中行
				var rowData = common.tree_get_row_data($("#org"));
				var organization = {};
				organization.id = rowData.id;
				// 添加参数 @param 参数key；参数value
				var orgJson = JSON.stringify(organization);
				var param = common.add_param("org",orgJson);
				common.init("../org/remove","POST",org.removeSuccess);
				// 执行提交操作
				common.do_submit(param);
			});
		},
		
		/**
		 * 跳转到组织机构编辑页面
		 */
		toOrgEditPage : function(tr){
			// 获取选中行数据对象
			var rowData = grid.getTreeSelectRowDataByRow(tr);
			var id= rowData.id;
			// 跳转到组织机构编辑页面
			common.toPage("../org/edit_init?id="+id);
		},
		
		/**
		 * 跳转到组织机构人员分配页面
		 */
		allocUserPage : function(){
			// 获取选中的行数据
			var rowData = common.tree_get_row_data($("#org"));
			if(rowData.orgName == "公共部门"){
				layer.msg('公共部门不需进行人员分配！');
				return;
			}
			if(rowData == null){
				layer.msg('请选择一个部门进行人员分配！');
				return;
			}else{
				var id= rowData.id;
				// 跳转到组织机构编辑页面
				common.toPage("../org/alloc_user?id="+id);
			}
		},
		
		/**
		 * 绑定页面事件
		 */
		bindPageEvent : function(){
			/** 
			 * 绑定增加组织机构窗口按钮的click事件
			 */
			$("#add").on("click",function(){
				org.toOrgAddPage();
			});
			
			/** 
			 * 绑定删除组织机构窗口按钮的click事件
			 */
			$("#remove").on("click",function(){
				org.removeOrgs();
			});
			
			/** 绑定刷新组织机构列表窗口按钮的click事件*/
			$("#refresh").on("click",function(){
				window.location.reload();
			});
			
			/** 绑定组织机构人员分配列表窗口按钮的click事件*/
			$("#alloc_user").on("click",function(){
				org.allocUserPage();
			});			
		}
}