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
		$("#source").attr("readOnly",true);
		$("#source").attr("onfocus","this.defaultIndex=this.selectedIndex");
		$("#source").attr("onchange","this.selectedIndex=this.defaultIndex");
		$("#titanic").attr("readOnly",true);
		$("#titanic").attr("onfocus","this.defaultIndex=this.selectedIndex");
		$("#titanic").attr("onchange","this.selectedIndex = this.defaultIndex");
		$("#receiveDate").attr("readOnly",true);
		$("#receiveDate").attr("onfocus","this.defaultIndex=this.selectedIndex");
		$("#receiveDate").attr("onchange","this.selectedIndex = this.defaultIndex");
		$("#content").attr("readOnly",true);
		$("#content").attr("onfocus","this.defaultIndex=this.selectedIndex");
		$("#content").attr("onchange","this.selectedIndex = this.defaultIndex");
		$("#remark").attr("readOnly",true);
		$("#remark").attr("onfocus","this.defaultIndex=this.selectedIndex");
		$("#remark").attr("onchange","this.selectedIndex=this.defaultIndex");
		booksfile.bindPageEvent();
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
			window.history.go(-1);
		});
	}
	
}