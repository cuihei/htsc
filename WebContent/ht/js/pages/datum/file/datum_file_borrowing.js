$(function(){
	datum_file_borrowing.init();
})

var datum_file_borrowing = {
	/**
	 * 初始化
	 */
	init : function(){
		//将资料类别元素设置为readonly
		datum_file_borrowing.bindPageEvent();
	},
	
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定确定按钮的click事件 */
		$("#submit").on("click",function(){
			var borrowing = {};
			// 获取图书编号
			var borrowBookNo = $("#borrowBookNo").val()
			// 获取借阅数量
			var borrowNo = $("#borrowNo").val();
			// 获取归还数量
			var returnNo = $("#returnNo").val();
			if(returnNo == "0"){
				// 设置图书编号
				borrowing.borrowBookNo = borrowBookNo;
				// 设置文件夹Id
				borrowing.folderId = $("#folderId").val();
				// 获取图书名称
				borrowing.borrowBookName = $("#borrowBookName").val();
				// 获取借阅者
				borrowing.borrowPerson = $("#borrowPerson").val();
				// 获取借阅时间
				borrowing.borrowDate = $("#borrowDate").val();
				// 获取借阅数量
				borrowing.borrowNo = borrowNo;
				// 获取借阅天数
				borrowing.borrowDays = $("#borrowDays").val();
			}else {
				// 判断归还数量是否大于借阅数量
				if(Number(returnNo) > Number(borrowNo)){
					layer.msg("归还数量不能大于借阅数量！",{icon:2,time:1500});
					return false;
				}else {
					borrowing.id = $("#id").val();
					// 设置图书编号
					borrowing.borrowBookNo = borrowBookNo;
					// 设置文件夹Id
					borrowing.folderId = $("#folderId").val();
					// 获取图书名称
					borrowing.borrowBookName = $("#borrowBookName").val();
					// 获取借阅者
					borrowing.borrowPerson = $("#borrowPerson").val();
					// 获取借阅时间
					borrowing.borrowDate = $("#borrowDate").val();
					// 获取借阅天数
					borrowing.borrowDays = $("#borrowDays").val();
					// 计算剩余数量
					var surplus = (borrowNo-returnNo).toString();
					borrowing.borrowNo = surplus;
				}
			}
			//将数组转换成JOSN数据
			var borrowingJson = JSON.stringify(borrowing);
			var param = common.add_param("borrowing",borrowingJson);

			common.init("../datumFile/add_borrowing?borrowBookNo="+borrowBookNo+"&returnNo="+returnNo,"POST",function(result){
				//调回资料类别列表显示页面
				common.toPage("../datumFile/index");
				//执行刷新资料类别列表按钮
				parent.$("#refresh").click();
			});
			common.do_submit(param);
		});

		/** 绑定取消按钮的click事件*/
		$("#back").on("click",function(){
			//调回资料列表显示页面
			common.toPage("../datumFile/index");
			//执行刷新资料列表按钮
			parent.$("#refresh").click();
		});
		
		
	}
}