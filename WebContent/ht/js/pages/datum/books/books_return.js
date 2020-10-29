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
			var bookReturn = {};
			// 获取图书编号
			var bookNo = $("#BookNo").val();
			bookReturn.bookNo = bookNo;
			// 获取图书名称
			bookReturn.bookName = $("#BookName").val();
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

			common.init("../books/return_book?bookNo="+bookNo+"&returnNo="+returnNo,"POST",function(result){
				//返回图书资料列表显示页面
				common.toPage("../books/index");
				//执行刷新列表按钮
				parent.$("#refresh").click();
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