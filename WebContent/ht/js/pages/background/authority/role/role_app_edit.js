$(function(){
	role_application.init();
});

var role_application = {
		/**
		 * 初始化
		 */
		init : function(){
			loading.init();
			try{
				role_application.createaApplicatonGrid();
				role_application.requestApplicatonData();
				role_application.bindPageEvent();
			}
			catch(err){
				loading.close();
			}
		},
		
		createaApplicatonGrid:function(){
			// 绑定数据 @param 绑定的grid对象；显示列集合对象
			var tree = $("#roleApp").kendoTreeList({
    			resizable : true,
    			reorderable : true,
    			filterable: true,
                selectable: "multiple row",
                columns: [
                    { field: "appCode",expandable: true, title: "资源编码",attributes : {
    					"style" : "text-align: left;"
    				}, template:$("#tmplt_tree_checkbox").html()},
                    { field: "appName",expandable: false, title: "资源名称",attributes : {
    					"style" : "text-align: left;"
    				}},
                    { field: "appUrl", expandable: false, title: "资料类别编码",attributes : {
    					"style" : "text-align: left;"
    				}},
                    { field: "appType",expandable: false, title: "资料类别名称",attributes : {
    					"style" : "text-align: left;"
    				}},
                    { field: "appContent",expandable: false, title: "资源内容",attributes : {
    					"style" : "text-align: left;"
    				}}
                   /* { field: "write",expandable: false, title: "写",attributes : {
    					"style" : "text-align: left;"
    				},template:$("#write_select").html()},*/
                ]
            });
			
			tree.on("click", ".k-grid-content tr", function(e){
				if (e.target.type=="checkbox") {
					return;
				}
				var child = e.toElement;
				var row = $(child).parent();
				$("#roleApp").data("kendoTreeList").clearSelection();
				common.tree_select_checked_row(row);
			});
		},
		
		/**
		 * 加载树列表数据
		 */
		requestApplicatonData:function(){
			 var role = {};
			 role.roleId = $("#roleId").val();
			 var roleJson = JSON.stringify(role);
			 common.init("../role/roleapps","POST",function(result){
				 var dataSource = new kendo.data.TreeListDataSource({
						data : result.value,
		                schema: {
		                    model: {
		                        id: "id",
		                        fields: {
		                      	   id: { type: "string", nullable: false,resizable : true},
		                      	 parentId: { field: "appParentId", nullable: true }
		                        },
		                        expanded: true
		                    }
		                }
		            });
					var tree = $("#roleApp").data("kendoTreeList");
					tree.setDataSource(dataSource);
					// 默认选中事件
					var rows = common.tree_get_all_rows($("#roleApp"));
		        	$.each(rows,function(i,item){
		        		if ($("#roleApp").data("kendoTreeList").dataItem(item).roleId==$("#roleId").val()) {
		        			common.tree_select_row($("#roleApp"),$(item));
		        			var checkbox = $(item).find("input:checkbox");
		        			checkbox.prop("checked",true);
						}
		        		var select = $(item).find("select");
	        			select.val($("#roleApp").data("kendoTreeList").dataItem(item).write);
		        	});
			 });
			 var data = common.add_param("role",roleJson);
			 common.do_submit(data);
			 loading.close(1);
		},
		
		/**
		 * 绑定页面事件
		 */
		bindPageEvent : function(){
			$("#submit").on("click",function(){
				var selectRows = common.tree_get_select_rows($("#roleApp"));
				var apps = [];
				$.each(selectRows,function(i,item){
					var id = $("#roleApp").data("kendoTreeList").dataItem(item).id;
					var select = $(item).find("select");
					var app = {};
					app.id = id;
					app.write = select.val();
					apps.push(app);
				});
				common.add_param("roleId",$("#roleId").val());
				var data = common.add_param("app",JSON.stringify(apps));
				// 初始化common对象 @param 发送地址；访问方式  
				common.init("../role/addapps","POST",function(result){
					 layer.msg('资源分配成功！');
					 //调回角色列表显示页面
					 setTimeout(function(){
						 common.toPage("../role/index");
					 },500);
				 });
				 common.do_submit(data);
			});
			
			/** 
			 * 绑定取消窗口按钮的click事件
			 */
			$("#back").on("click",function(){
				history.back();
			});
		},
}