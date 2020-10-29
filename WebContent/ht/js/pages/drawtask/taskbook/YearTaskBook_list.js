$(function(){
	taskBooks.init();
	$("#uploadFile").css("width","300px");
})

/** 绑定编辑用户窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	taskBooks.toEditPage(tr);
}

/** 绑定编辑用户窗口按钮的click事件*/
function downPage(obj) {
	var tr = $(obj).parent().parent();
	// 获取选中行数据对象
	var rowData = grid.getSelectRowDataByRow(tr);
	//获取附件地址
	var enclosure=rowData.enclosure;
	if(enclosure == null || enclosure == ""){
		layer.msg("这个改正通告任务书没有上传文件！");
		return;
	}else{
		// 下载
		window.location.href = "../formValue/form_file?url="+enclosure;
	}
}

/** 绑定编辑用户窗口按钮的click事件*/
function uploadPage(obj) {
	var tr = $(obj).parent().parent();
	taskBooks.showModel(tr);
}

/** 绑定编辑用户窗口按钮的click事件*/
function histroyPage(obj) {
	var tr = $(obj).parent().parent();
	taskBooks.toHistoryPage(tr);
}

var taskBooks ={
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("taskBooks");
		loading.init();
		try{
			taskBooks.createtaskBooksGrid();
			taskBooks.requesttaskBooksData();
			taskBooks.bindPageEvent();
			$("#typeId").attr("style","width:200px;");  
		}catch(err){
			loading.close();
		}
		/*//初始化时间控件
		$("#year").datepicker({
			startView: 2, 
			maxViewMode: 2,
			minViewMode:2,
			 format: 'yyyy',
			 autoclose: true
		}).on('changeDate',gotoDate);
		function gotoDate(){
			var year = $("#year").val();
			loading.init();
			common.init("../yeartaskbook/list?year="+year,"POST",taskBooks.bindtaskBooksGrid);
			common.do_submit();
		}*/
	},
	/**
	 * 构建改正通知公告表格。
	 */
	createtaskBooksColumns : function(){
		grid.resetColumn();
		grid.addColumn("130px","taskbookNo","编号");
		grid.addColumn("220px","taskbookName","名称");
		grid.addColumn("140px","value","类型");
		grid.addColumn("70px","noticeNo","通告期号");
		grid.addColumn("150px","history_version","历史版本",kendo.template($("#hisTemplate").html()));
		grid.addColumn("150px","upload_options","上传附件",kendo.template($("#uploadTemplate").html()));
		grid.addColumn("150px","download_options","下载附件",kendo.template($("#downloadTemplate").html()));
		grid.addColumn("100px","creationDate","创建时间");
		return grid.addColumn("150px","handle","操作",kendo.template($("#editTemplate").html()));
	},
	/**
	 *
	 */
	createtaskBooksGrid : function(){
		var columns = taskBooks.createtaskBooksColumns();
		grid.createGrid(columns);
	},
	/**
	 * 发送数据请求
	 */
	requesttaskBooksData : function(){
		common.init("../yeartaskbook/list","POST",taskBooks.bindtaskBooksGrid);
		common.do_submit();
	},
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindtaskBooksGrid : function(result){
		grid.bindData(result);
		//设置高度
		var grid1 = $("#taskBooks").data("kendoGrid");
		var height =$(document.body).height()-($("#submit").parent().parent().height()*4);
		grid1.setOptions({
			height:height,
		});
		//grid.setEvents();
	},
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		$("#submit").click(function(){
			var count = $("#count").val();
			layer.confirm('这是本年度的第'+count+'期，确认要发布吗？',function(index){
				common.init("../workflow/publishcnp","POST",function(result){
					if (result.code == 0) {
						layer.msg(result.value);
						return;
					}
					layer.msg("发布成功");
					taskBooks.requesttaskBooksData();
				});
				common.do_submit();
			});
		});
		$("#taskSubmit").click(function(){
			$("#importForm").ajaxSubmit({  
	            type: 'post',  
	            url: "../yeartaskbook/upload_file" ,  
	            beforeSubmit: function() { 
	            	return true;
	            } ,  
	            success: function(result){
	            	layer.msg("上传成功"); 
	            	setTimeout(function() {
	            		common.toPage("../yeartaskbook/index");
					},1500); 
	            },  
	            error: function(XmlHttpRequest, textStatus, errorThrown){
	            	layer.msg("上传失败");  
	            }  
	        });  
		});
		
	},
	
	/**
	 * 跳转到历史版本页面
	 */
	toHistoryPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取任务书ID
		var id = rowData.id;
		//获取任务书类型
		var taskBookType= rowData.taskBookType;
		// 跳转到历史版本页面
		common.toPage("../historyTaskBook/index?taskBookId="+id+"&taskBookType="+taskBookType);
	},
	/**
	 * 跳转到编辑页面
	 */
	toEditPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取任务书ID
		var id = rowData.id;
		//获取任务书类型
		var taskBookType = rowData.taskBookType;
		// 跳转到用户编辑页面
		common.toPage("../yeartaskbook/edit_page?id="+id+"&taskBookType="+taskBookType);
	},
	/**
	 * 跳转到编辑页面
	 */
	showModel : function(tr){
		$("#myModal").modal('show');
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取任务书ID
		var id = rowData.id;
		$("#taskBookId").val(id);
	}
}
