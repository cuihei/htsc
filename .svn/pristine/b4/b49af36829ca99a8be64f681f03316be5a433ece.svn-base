$(function(){
	edit_notice.init();
})

var edit_notice = {
	
	/**
	 * 初始化
	 */
	init : function(){
		edit_notice.bindPageEvent();
		
	},
    
	/**
	 * 绑定页面按钮事件
	 */
	bindPageEvent : function(){
		/**
		 * 确定按钮的点击事件
		 */
		$("#submit").on("click",function(){
			var correctionNoticeBook = {};
			var source = $("#source").val();
			if(source == null || source == ""|| source == "--请选择--"){
				layer.msg("请选择资料来源!");
				return false;
			}
			correctionNoticeBook.source = source;
			// 设置Id
			correctionNoticeBook.id = $("#id").val();
			// 设置状态
			correctionNoticeBook.state = $("#state").val();
			
			var titanic = $("#titanic").val();
			titanic=titanic.substring(titanic.lastIndexOf("（")+1,titanic.lastIndexOf("）")).trim();
			if(titanic != null && titanic != ""){
				correctionNoticeBook.titanic = $("#titanic").val();
			}else {
				layer.msg("请输入文号!");
				return false;
			}
			
			var receiveDate = $("#receiveDate").val();
			if(receiveDate != null && receiveDate != ""){
				correctionNoticeBook.receiveDate = receiveDate;
			}else {
				layer.msg("请输入收到日期!");
				return false;
			}
			
			var content = $("#content").val();
			if(content != null && content != ""){
				correctionNoticeBook.content = content;
			}else {
				correctionNoticeBook.content = "";
			}
			correctionNoticeBook.remark = $("#remark").val();
			
			// 转成JSON
			var correctionNoticeBookJson = JSON.stringify(correctionNoticeBook);
			var param = common.add_param("correctionNoticeBooks",correctionNoticeBookJson);
			
			common.init("../cnba/add","POST",function(result){
				 if (result.code == 0) {
						if(result.value !="" && result.value != null){
							layer.msg(result.value);
							return;
						}else{
							layer.msg("保存失败！");
							return;
						}
				 }
				layer.msg("操作成功!");
				
				// 返回图书资料列表页面
				common.toPage("../cnba/index");
				// 执行刷新按钮的点击事件
				parent.$("#refresh").click();
			});
			common.do_submit(param);
		});
		
		/**
		 * 返回按钮的点击事件
		 */
		$("#back").on("click",function(){
			// 返回图书资料列表页面
			common.toPage("../cnba/index");
			// 执行刷新按钮的点击事件
			parent.$("#refresh").click();
		});
	}
}