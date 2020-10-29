$(function(){
	books_borrowing.init();
})

var books_borrowing = {
	/**
	 * 初始化
	 */
	init : function(){
		books_borrowing.bindPageEvent();
	},
	
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定确定按钮的click事件 */
		$("#submit").on("click",function(){
			var borrowing = {};
			var ids = $("#bookId").val();
			// 获取可借数量
			var canBorrow = $("#canBorrowNo").val();
			// 获取借阅数量
			var borrowNo = $("#borrowNo").val();
			// 如果存在borrowNo为空，则不能提交
			if(borrowNo == null || borrowNo == ""){
				layer.msg("请确认填写好借阅数量后再提交！");
				return;
			}
			if(Number(borrowNo) > Number(canBorrow)){
				layer.msg("借阅数量不能大于可借数量！");
				return false;
			}
			// 获取是否涉密
			var secretInvolved = $("#isSecrecy").val();
			if(secretInvolved == null || secretInvolved == "" || secretInvolved.indexOf("请选择")>-1){
				layer.msg("请选择资料是否涉密！");
				return;
			}
			common.add_param("ids",ids);
			common.add_param("secretInvolved",secretInvolved);
			var param = common.add_param("borrowNos",borrowNo);
			common.init("../workflow/publishbooks","POST",function(result){
				if (result.code == 0) {
					layer.msg(result.value);
					return;
				}
				layer.msg("提交成功！",{icon:6,time:2000});
				window.history.go(-1);
			});
			common.do_submit(param);
		});

		/** 绑定取消按钮的click事件*/
		$("#back").on("click",function(){
			//调回资料列表显示页面
			common.toPage("../books/index");
			//执行刷新资料列表按钮
			parent.$("#refresh").click();
		});
		
		
	}
}