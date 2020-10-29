$(function(){
	datumfile.init();
});

var datumfile = {
		
		/**
		 * 初始化
		 */
		init : function(){
			// 显示加载层
			grid.init("datumfile");
			loading.init();
			try{
				datumfile.createDatumFileGrid();
				datumfile.requestDatumFileData();
				datumfile.bindPageEvent();
			}
			catch(err){
				loading.close();
			}
		},
		
		/**
		 * 创建grid树
		 */
		createDatumFileGrid:function(){
			// 绑定数据 @param 绑定的grid对象；显示列集合对象
			$("#datumfile").kendoTreeList({
    			resizable : true,
    			reorderable : true,
                selectable : true,
    			filterable : {
    				mode : "row"
    			},
                columns: [
                    { field: "fileType", title: "类型"},
                    { field: "fileName", title: "名称"},
                    { field: "look", title: "查看",template: kendo.template($("#lookTemplate").html())},
                    { field: "handle", title: "操作",template: kendo.template($("#editTemplate").html())}
                ]
            });
		},
		
		/**
		 * 加载树列表数据
		 */
		requestDatumFileData:function(){
			 common.init("../datumFile/tree","POST",function(result){
				 var dataSource = new kendo.data.TreeListDataSource({
						data : result.value,
		                schema: {
		                    model: {
		                        id: "id",
		                        fields: {
		                       	   id: { type: "string", nullable: false,resizable : true},
		                       	 parentId: { field: "categoryId", nullable: true }
		                         },
		                        expanded: true
		                    }
		                }
		            });
				 var tree = $("#datumfile").data("kendoTreeList");
					tree.setDataSource(dataSource);
					// 隐藏按钮
					var rows = common.tree_get_all_rows($("#datumfile"));
		        	$.each(rows,function(i,item){
		        		if ($("#datumfile").data("kendoTreeList").dataItem(item).fileType=='根目录') {
		        			$(item).find("button[name='folderDatumFile']").hide();
		        			$(item).find("button[name='bookDatumFile']").hide();
		        			$(item).find("button[name='uploadDatumFile']").hide();
		        			$(item).find("button[name='downloadDatumFile']").hide();
		        			$(item).find("button[name='borrowDatumFile']").hide();
		        			$(item).find("button[name='returnDatumFile']").hide();
		        			$(item).find("button[name='deleteDatumFile']").hide();
						}else if($("#datumfile").data("kendoTreeList").dataItem(item).fileType=='文件夹'){
							$(item).find("button[name='bookDatumFile']").hide();
							$(item).find("button[name='downloadDatumFile']").hide();
		        			$(item).find("button[name='borrowDatumFile']").hide();
		        			$(item).find("button[name='returnDatumFile']").hide();
		        			$(item).find("button[name='deleteDatumFile']").hide();
		        			$(item).children('td').eq(1).html($("#datumfile").data("kendoTreeList").dataItem(item).fileName);
						}else if($("#datumfile").data("kendoTreeList").dataItem(item).fileType=='电子文档'){
							$(item).find("button[name='folderDatumFile']").hide();
		        			$(item).find("button[name='uploadDatumFile']").hide();
		        			$(item).find("button[name='borrowDatumFile']").hide();
		        			$(item).find("button[name='returnDatumFile']").hide();
		        			$(item).children('td').eq(1).html($("#datumfile").data("kendoTreeList").dataItem(item).fileName);
						}else if($("#datumfile").data("kendoTreeList").dataItem(item).fileType=='实体文档'){
							$(item).find("button[name='folderDatumFile']").hide();
		        			$(item).find("button[name='uploadDatumFile']").hide();
		        			$(item).find("button[name='downloadDatumFile']").hide();
		        			$(item).children('td').eq(1).html($("#datumfile").data("kendoTreeList").dataItem(item).fileName);
						}
		        	});
		        	
		        	// 按钮事件
		        	// 查看文件夹点击事件
		        	var folderBtns = $("button[name='folderDatumFile']");
		    		$.each(folderBtns,function(i,item){
		    			$(item).on("click",function(){
		    				var tr = $(item).parent().parent();
		    				datumfile.viewfolder(tr);
		    			});
		    		})
		    		
		    		// 查看文件属性点击事件
		        	var bookBtns = $("button[name='bookDatumFile']");
		    		$.each(bookBtns,function(i,item){
		    			$(item).on("click",function(){
		    				var tr = $(item).parent().parent();
		    				datumfile.fileAttr(tr);
		    			});
		    		})
		    		
		    		// 文件下载点击事件
		    		var downloadBtns = $("button[name='downloadDatumFile']");
		    		$.each(downloadBtns,function(i,item){
		    			$(item).on("click",function(){
		    				var tr = $(item).parent().parent();
		    				datumfile.downloadFile(tr);
		    			});
		    		})
		    		
		    		// 文件上传点击事件
		    		var uploadBtns = $("button[name='uploadDatumFile']");
	    			$.each(uploadBtns,function(i,item){
	    				$(item).on("click",function(){
	    					var tr = $(item).parent().parent();
	    					// 显示模态框
	    					$('#myModal').modal('show');
	    					// 获取选中行数据对象
	    					var rowData = grid.getTreeSelectRowDataByRow(tr);
	    					// 获取资料ID
	    					var categoryId = rowData.id;
	    					$("#categoryId").val(categoryId);
	    				});
		    		})
		    		
		    		// 文件借阅点击事件
		    		var borrowBtns = $("button[name='borrowDatumFile']");
		    		$.each(borrowBtns,function(i,item){
		    			$(item).on("click",function(){
		    				var tr = $(item).parent().parent();
		    				datumfile.borrowingDatumFile(tr);
		    			});
		    		})
		    		
		    		// 文件归还点击事件
		    		var returnBtns = $("button[name='returnDatumFile']");
		    		$.each(returnBtns,function(i,item){
		    			$(item).on("click",function(){
		    				var tr = $(item).parent().parent();
		    				datumfile.returnDatumFile(tr);
		    			});
		    		})
		    		
		    		// 文件删除点击事件
		    		var deleteBtns = $("button[name='deleteDatumFile']");
		    		$.each(deleteBtns,function(i,item){
		    			$(item).on("click",function(){
		    				var tr = $(item).parent().parent();
		    				datumfile.removeDatumFile(tr);
		    			});
		    		});
			 });
			 common.do_submit();
			 loading.close(1);
		},
		
		/**
		 * 删除成功回调
		 * */
		removeSuccess : function(){
			layer.msg('已删除',{icon:2,time:1500});
			datumfile.requestDatumFileData();
			layer.close(1);
		},
		
		/**
		 * 接收服务器响应数据,绑定表格
		 * 这是一个回调函数，不用手动调用
		 */
		bindDatumFileGrid : function(result){
			grid.bindData(result);
		},
		
		/**
		 * 跳转到资料增加页面
		 */
		toDatumFileAddPage : function(){
			common.toPage("../datumFile/edit_init");
		},
		
		/**
		 * 删除资料
		 */
		removeDatumFile : function(tr){
			/*删除*/
			layer.confirm('确认要删除吗？',function(index){
				// 获取Grid的选中行
			    var rowData = grid.getTreeSelectRowDataByRow(tr);
				// 获取资料ID
				var id = rowData.id;
				var data = common.add_param("datumfileId",id);
				common.init("../datumFile/remove","POST",datumfile.removeSuccess);
				// 执行提交操作
				common.do_submit(data);
			});
		},
		
		/**
		 * 跳转到借阅资料页面
		 */
		borrowingDatumFile : function(tr){
			// 获取选中行数据对象
			var rowData = grid.getTreeSelectRowDataByRow(tr);
			// 获取资料ID
			var id = rowData.id;
			// 文件夹ID
			var categoryId = rowData.parentId;
			// 跳转到借阅资料页面
			common.toPage("../datumFile/borrowing_init?id="+id+"&datumCategoryId="+categoryId);
		},

		/**
		 * 跳转到归还资料页面
		 */
		returnDatumFile : function(tr){
			// 获取选中行数据对象
			var rowData = grid.getTreeSelectRowDataByRow(tr);
			// 获取资料ID
			var id = rowData.id;
			// 文件夹ID
			var categoryId = rowData.parentId;
			// 获取资料名称
			var borrowBookName = rowData.fileName;
			// 跳转到借阅资料页面
			common.toPage("../datumFile/borrowing_init?id="+id+"&datumCategoryId="+categoryId+"&borrowBookName="+borrowBookName+"&flag=return");
		},
		
		/**
		 * 查看文件夹
		 */
		viewfolder : function(tr){
			// 获取选中行数据对象
			var rowData = grid.getTreeSelectRowDataByRow(tr);
			// 获取资料ID
			var id = rowData.id;
			// 跳转到借阅资料页面
			common.toPage("../datumFile/view_folder_init?id="+id);
		},
		
		/**
		 * 上传
		 */
		uploadFile : function(){
			var categoryId = $("#categoryId").val();
			// 判空
			var data = $('#uploadFile').val();
			if(data==""){
				layer.msg('请选择文件！');
				return;
			}
			loading.init();
			$("#importForm").ajaxSubmit({  
	            type: 'post',  
	            url: "../datumFileUpload/uploadFile?categoryId="+categoryId,  
	            beforeSubmit: function() { 
	            	return true;
	            } ,  
	            success: function(result){
	                loading.close();
	                layer.msg("上传成功！",{icon:6,time:10000});
	                var categoryId = $("#categoryId").val();
	                window.location.reload();
	            },  
	            error: function(XmlHttpRequest, textStatus, errorThrown){
	            	loading.close();
	            	layer.msg("系统错误，请联系管理员！");  
	            }  
	        });  
		},
		
		/**
		 * 文件下载
		 */
		downloadFile : function(tr){
			// 获取选中行数据对象
			var rowData = grid.getTreeSelectRowDataByRow(tr);
			// 获取资料ID
			var id = rowData.id;
			window.location.href = "../datumFileDownload/download?datumfileId="+id;
		},
		
		/**
		 * 文件属性
		 */
		fileAttr : function(tr){
			// 获取选中行数据对象
			var rowData = grid.getTreeSelectRowDataByRow(tr);
			// 获取资料ID
			var id = rowData.id;
			common.toPage("../datumFile/fileAttr_init?datumfileId="+id);
		},
		
		/**
		 * 绑定页面事件
		 */
		bindPageEvent : function(){
			/** 
			 * 绑定增加资料按钮的click事件
			 */
			$("#add").on("click",function(){
				datumfile.toDatumFileAddPage();
			});
			
			/** 绑定刷新按钮的click事件*/
			$("#refresh").on("click",function(){
				window.location.reload();
			});
			
			/** 导入按钮点击事件 */
			$("#importSubmit").on("click",function(){
				datumfile.uploadFile();
			});
		}
	
}