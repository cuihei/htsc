$(function(){
	planAttachment.init();
})

/** 绑定编辑按钮的click事件*/
function down(obj) {
	var tr = $(obj).parent().parent();
	planAttachment.downloadFile(tr);
}

/** 绑定预览按钮的click事件*/
function preview(obj) {
	var tr = $(obj).parent().parent();
	planAttachment.previewFile(tr);
}

var planAttachment = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("planAttachment");
		loading.init();
		try{
			planAttachment.createGrid();
			planAttachment.requestFileData();
			planAttachment.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 创建通知类型列表
	 */
	createGrid : function(){
		var columns = planAttachment.createFileColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 构建通知类型集合
	 */
	createFileColumns : function(){
		grid.resetColumn();
		grid.addColumn("10%","planYear","年份","#= planYear ? kendo.toString(new Date(planYear), 'yyyy') : '' #");
		grid.addColumn("30%","fileName","文件名称");
		grid.addColumn("30%","filePath","文件路径");
		grid.addColumn("30%","suffixName","文件后缀名");
		grid.addColumn("20%","spaceSize","文件大小");
		return grid.addColumn("30%","handle","操作",kendo.template($("#downTemplate").html()));
	},
	
	/**
	 * 发送数据请求
	 */
	requestFileData : function(){
		var year = $("#year").val();
		if(year!=""){
			 year = $("#year").val()+"-01-01";
		}else{
			 year = kindo.toString(new Date(),"yyyy-MM-dd");
		}
		common.init("../plan/planAttachment?year="+year,"POST",planAttachment.bindFileGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindFileGrid : function(result){
		grid.bindData(result);
	},
	
	/**
	 * 上传
	 */
	upload : function(){
		var year = $("#year").val();
		if(year!=""){
			year = year+'-01-01';
		}else{
			year = kendo.toString(new Date(),'yyyy-MM-dd');
		}
		// 判空
		var data = $('#uploadPlanFile').val();
		if(data !=null && data!=""){
			var fileType=data.substr(data.lastIndexOf(".")).toLowerCase();
			if(fileType != '.gif' && fileType != '.png' && fileType != '.jpg' && fileType != '.jpeg'
			   && fileType != '.doc'&& fileType != '.docx'&& fileType != '.xls'&& fileType != '.xlsx'
			   && fileType != '.pdf'&& fileType != '.txt'){
				layer.msg('请上传office、图片、txt的文件！');
				$('#uploadPlanFile').val("");
				return;
			}
		}else{
			layer.msg('请选择文件！');
			return;
		}
		loading.init();
		$("#importForm").ajaxSubmit({  
            type: 'post',  
            url: "../plan/uploadfile?year="+year,  
            beforeSubmit: function() { 
            	return true;
            } ,  
            success: function(result){
                loading.close();
                layer.msg("上传成功！",{icon:6,time:10000});
                var booksId = $("#booksId").val();
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
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取附件ID
		var id = rowData.id;
		common.init("../plan/checkFile?id="+id,"POST",planAttachment.layerload);
		common.do_submit();
	},
	
	/**
	 * 文件预览
	 */
	previewFile : function(tr){
		var rowData = grid.getSelectRowDataByRow(tr);
		var id = rowData.id;
		common.init("../plan/getUrlById?id="+id,"POST",planAttachment.layerOpen);
		common.do_submit();
	},
	
	layerOpen : function(result){
		if(result!=null&&result.value!=""){
			layer.open({
				  type: 2,
				  title: "文件预览",
				  area: ['800px', '600px'],
				  skin: 'layui-layer-rim', //加上边框
				  content: result.value
				});
		}else{
			layer.msg('抱歉，您要预览的文件不存在!', {icon:5,time:1000});
		}
		
	},
	layerload : function(result){
		if(result!=null&&result.value!=""){
			window.location.href = "../plan/download?id="+result.value;
		}else{
			layer.msg('抱歉，您要下载的文件不存在!', {icon:5,time:1000});
		}
		
	},
	/**
	 * 删除
	 */
	remove : function(){
		//获取选中行数据对象
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length<=0) {
			layer.msg('未选择任何行!', {icon:5,time:1000});
			return;
		}
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var planFiles = [];
			$.each(rowDatas,function(i,item){
				var id = $("#planAttachment").data("kendoGrid").dataItem(item).id;
				var fileName = $("#planAttachment").data("kendoGrid").dataItem(item).fileName;
				var filePath = $("#planAttachment").data("kendoGrid").dataItem(item).filePath;
				var planFile = {};
				planFile.id = id;
				planFile.filePath = filePath;
				planFile.fileName = fileName;
				planFiles.push(planFile);
			});
			var paramJson = JSON.stringify(planFiles);
			// 添加参数 @param 参数key；参数value
			var param = common.add_param("ids",paramJson);
			common.init("../plan/planFilesRemove","POST",planAttachment.removeSuccess);
			// 执行提交操作
			common.do_submit(param);
		});
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(result){
		grid.init("planAttachment");
		layer.close(1);
		if(result.code != 1){
			layer.msg("删除失败！");
			return;
		}
		layer.msg('删除成功');
		planAttachment.requestFileData();
	},
	
	/**
	 * 绑定按钮事件
	 */
	bindPageEvent : function(){
		/**
		 * 返回按钮点击事件
		 */
		$("#back").on("click",function(){
			window.history.go(-1);
		});
		/**
		 * 文件上传
		 */
		$("#doUpload").on("click",function(){
			planAttachment.upload();
		});
		/** 
		 * 绑定删除按钮的click事件
		 */
		$("#remove").on("click",function(){
			planAttachment.remove();
		});
		
	}
}