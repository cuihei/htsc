$(function(){
	datumcategory.init();
});

var datumcategory = {
		
		/**
		 * 初始化
		 */
		init : function(){
			// 显示加载层
			grid.init("datumcategory");
			loading.init();
			try{
				datumcategory.createDatumCategoryGrid();
				datumcategory.requestDatumCategoryData();
				datumcategory.bindPageEvent();
			}
			catch(err){
				loading.close();
			}
		},
		
		createDatumCategoryGrid:function(){
		
			// 绑定数据 @param 绑定的grid对象；显示列集合对象
			$("#datumcategory").kendoTreeList({
    			resizable : true,
    			reorderable : true,
                selectable : true,
    			filterable : {
    				mode : "row"
    			},
                columns: [
                    { field: "categoryName",expandable: true, title: "名称"},
                    { field: "handle", title: "操作",template: kendo.template($("#editTemplate").html())}
                ]
            });
		},
		
		requestDatumCategoryData:function(){
			 common.init("../datumCategory/list","POST",function(result){
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
				    var tree = $("#datumcategory").data("kendoTreeList");
					tree.setDataSource(dataSource);
					var rows = common.tree_get_all_rows($("#datumcategory"));
		        	$.each(rows,function(i,item){
		        		if ($("#datumcategory").data("kendoTreeList").dataItem(item).categoryName=='root') {
		        			$(item).find("button[name='editDatumCategory']").hide();
						}
		        	});
		        	
		        	// 按钮事件
		        	var editBtns = $("button[name='editDatumCategory']");
		    		$.each(editBtns,function(i,item){
		    			$(item).on("click",function(){
		    				var tr = $(item).parent().parent();
		    				datumcategory.toDatumCategoryEditPage(tr);
		    			});
		    		})
			 });
			 common.do_submit();
			 loading.close(1);
		},
		
		/**
		 * 删除成功回调
		 * */
		removeSuccess : function(){
			layer.msg('已删除');
			datumcategory.requestDatumCategoryData();
			layer.close(1);
		},
		
		/**
		 * 接收服务器响应数据,绑定表格
		 * 这是一个回调函数，不用手动调用
		 */
		bindDatumCategoryGrid : function(result){
			grid.bindData(result);
		},
		
		/**
		 * 跳转到资料类别增加页面
		 */
		toDatumCategoryAddPage : function(){
			common.toPage("../datumCategory/edit_init");
		},
		
		/**
		 * 删除校验
		 */
		removeCheck : function(){
			// 获取grid选中行
			var rowData = common.tree_get_row_data($("#datumcategory"));
			// 如果没有选中行
			if(rowData == null) {
				layer.msg('未选择行！', {icon:2,time:1000});
				return false;
			}else {
				// 获取选中行Id
				var datumCategoryId = rowData.id;
				var datumCategoryParentId = rowData.parentId;
				var data = common.add_param("datumCategoryId",datumCategoryId);
				data = common.add_param("datumCategoryParentId",datumCategoryParentId);
				// 添加参数 @param 参数key；参数value
				common.init("../datumCategory/isFile","POST",datumcategory.removeDatumCategorys);
				// 执行提交
				common.do_submit(data);
			}
		},
		
		/**
		 * 删除校验回调
		 */
		removeDatumCategorys : function(result){
			// 获取Grid的选中行
			var rowData = common.tree_get_row_data($("#datumcategory"));
			var datumCategoryId = rowData.id;
			var parentId = rowData.parentId;
			if(result.value == 0){
				/*删除*/
				layer.confirm('确认要删除吗？',function(index){
					// 添加参数 @param 参数key；参数value
					var data = common.add_param("datumCategoryId",datumCategoryId);
					common.init("../datumCategory/remove","POST",datumcategory.removeSuccess);
					// 执行提交操作
					common.do_submit(data);
				});
			}else if(result.value == "root"){
				layer.msg("资料根路径，不能删除！",{icon:2,time:1500});
			}else if(result.value != 0 && parentId!="201610301150"){
				layer.msg("当前资料类别下有文件，不能删除！",{icon:2,time:1500});
			}else if(result.value != 0 && parentId =="201610301150"){
				layer.msg("当前资料类别下有子类别，不能删除！",{icon:2,time:1500});
			}
		},
		
		/**
		 * 跳转到资料类别编辑页面
		 */
		toDatumCategoryEditPage : function(tr){
			// 获取选中行数据对象
			var rowData = grid.getTreeSelectRowDataByRow(tr);
			// 获取资料类别ID
			var id = rowData.id;
			// 跳转到资料类别编辑页面
			common.toPage("../datumCategory/edit_init?id="+id);
		},
		
		/**
		 * 绑定页面事件
		 */
		bindPageEvent : function(){
			/** 
			 * 绑定增加资料类别窗口按钮的click事件
			 */
			$("#add").on("click",function(){
				datumcategory.toDatumCategoryAddPage();
			});
			
			/** 
			 * 绑定删除资料类别窗口按钮的click事件
			 */
			$("#remove").on("click",function(){
				datumcategory.removeCheck();
			});
			
			/** 绑定刷新资料类别列表窗口按钮的click事件*/
			$("#refresh").on("click",function(){
				window.location.reload();
			});
		}
	
}