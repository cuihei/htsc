$(function(){
	filed_data_return.init();
})

var filed_data_return = {
	/**
	 * 初始化
	 */
	init : function(){
		filed_data_return.bindPageEvent();
	},
	
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定确定按钮的click事件 */
		$("#submit").on("click",function(){
			var bookReturn = {};
			// 获取图书编号
			var picNo = $("#picNo").val();
			bookReturn.bookNo = picNo;
			// 获取项目名称
			bookReturn.bookName = $("#projectName").val();
			// 获取归还人
			bookReturn.returnPerson = $("#returnPerson").val();
			// 获取归还日期
			bookReturn.returnDate = $("#returnDate").val();
			// 获取归还数量
			var returnNo = $("#returnNo").val();
			bookReturn.returnNo = returnNo;
			// 获取借阅天数
			bookReturn.borrowDays = $("#borrowDays").val();
			// 将数组转换成JOSN数据
			var bookReturnJson = JSON.stringify(bookReturn);
			var param = common.add_param("bookReturn",bookReturnJson);

			common.init("../filedData/return_filed_data?picNo="+picNo+"&returnNo="+returnNo,"POST",function(result){
				//返回图书资料列表显示页面
				common.toPage("../filedData/index");
				//执行刷新列表按钮
				parent.$("#refresh").click();
			});
			common.do_submit(param);
		});

		/** 绑定取消按钮的click事件*/
		$("#back").on("click",function(){
			//调回资料列表显示页面
			common.toPage("../filedData/index");
			//执行刷新资料列表按钮
			parent.$("#refresh").click();
		});
		
		
	}
}