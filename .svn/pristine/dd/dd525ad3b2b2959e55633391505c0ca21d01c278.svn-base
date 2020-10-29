$(function(){
	booksfile.init();
})


/** 绑定下载click事件*/
function down(obj) {
	var id = obj.getAttribute("name");
	booksfile.downloadFile(id);
}

function remove(obj) {
	var id = obj.getAttribute("name");
	booksfile.removeFile(id);
}

var booksfile = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("booksfile");
		$("#oneSubClass").attr("readOnly",true);
		$("#oneSubClass").attr("onfocus","this.defaultIndex=this.selectedIndex");
		$("#oneSubClass").attr("onchange","this.selectedIndex=this.defaultIndex");
		$("#port").attr("readOnly",true);
		$("#port").attr("onfocus","this.defaultIndex=this.selectedIndex");
		$("#port").attr("onchange","this.selectedIndex = this.defaultIndex");
		$("#entry").attr("readOnly",true);
		$("#entry").attr("onfocus","this.defaultIndex=this.selectedIndex");
		$("#entry").attr("onchange","this.selectedIndex = this.defaultIndex");
		$("#auditor").attr("readOnly",true);
		$("#auditor").attr("onfocus","this.defaultIndex=this.selectedIndex");
		$("#auditor").attr("onchange","this.selectedIndex = this.defaultIndex");
		$("#twoSubClass").attr("readOnly",true);
		$("#twoSubClass").attr("onfocus","this.defaultIndex=this.selectedIndex");
		$("#twoSubClass").attr("onchange","this.selectedIndex=this.defaultIndex");
		try{
			booksfile.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindbooksGrid : function(result){
		grid.bindData(result);
	},
	
	/**
	 * 文件下载
	 */
	downloadFile : function(id){
		window.location.href = "../datumFileDownload/filedownload?bookfileId="+id;
	},
	
	removeFile : function(id){
		var param = common.add_param("id",id);
		common.init("../books/delete","POST",function(result){
			if (result.code == 0) {
				layer.msg(result.value);
				return;
			}
			layer.msg("删除成功！");
			window.location.reload() ;
		});
		common.do_submit(param);
	},
	
	/**
	 * 绑定按钮事件
	 */
	bindPageEvent : function(){
		/**
		 * 返回按钮点击事件
		 */
		$("#back").on("click",function(){
			var mark = $("#mark").val();
			if(mark == "1"){
				window.history.go(-1);
			}else{
				common.toPage("../books/index");
			}
		});
	},
	
	  /**
     * 请求二级子类下拉框数据
     */
    twoSubClassSelect : function(oneSubClassId){
    	// 添加参数 @param 参数key；参数value
		var data = common.add_param();
		// 初始化common对象 @param 发送地址；访问方式；回调函数
		common.init("../books/twoSubClass?oneSubClassId="+oneSubClassId,"POST",booksfile.select_twoSubClass_success);
		// 执行提交操作
		common.do_submit(data);
    },
    
    /**
	 * 获取二级子类数据后绑定下拉列表 
	 */
    select_twoSubClass_success:function(result) {
    	$("#twoSubClass").empty();
    	$.each(result, function(i, item) {
    		$("#twoSubClass").append("<option value='"+item.id+"'>"+item.categoryName+"</option>");
    	});
    },
}