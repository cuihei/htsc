$(function(){
	edit_book_info.init();
})

var edit_book_info = {
	
	/**
	 * 初始化
	 */
	init : function(){
		edit_book_info.bindPageEvent();
		$("#total").attr("onkeyup","value=(parseInt((value=value.replace(/[^\\d]/g,''))==''?'0':value,10))");
		$("#total").attr("onafterpaste","value=(parseInt((value=value.replace(/[^\\d]/g,''))==''?'0':value,10))");
		
		// 初始化图书资料编辑页面，获取一级子类Id
		/*var oneSubClassId = $("#oneSubClass").val();
		// 根据一级子类设置二级子类的值
		if(oneSubClassId == "--请选择--"){
			$("#twoSubClass").val("--请选择--");	
		}else {
			edit_book_info.twoSubClassSelect(oneSubClassId);
		}*/
		
		/**
		 * 点击一级子类改变二级子类的值
		 */
		$("#oneSubClass").on("change",function(){
			// 获取一级子类Id
			var oneSubClassId = $("#oneSubClass").val();
			// 设置二级子类的值
			edit_book_info.twoSubClassSelect(oneSubClassId);
		});
		
	},
	
    /**
     * 请求二级子类下拉框数据
     */
    twoSubClassSelect : function(oneSubClassId){
    	// 添加参数 @param 参数key；参数value
		var data = common.add_param();
		// 初始化common对象 @param 发送地址；访问方式；回调函数
		common.init("../bookinfo/twoSubClass?oneSubClassId="+oneSubClassId,"POST",edit_book_info.select_twoSubClass_success);
		// 执行提交操作
		common.do_submit(data,false);
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
    
	/**
	 * 绑定页面按钮事件
	 */
	bindPageEvent : function(){
		/**
		 * 确定按钮的点击事件
		 */
		$("#submit").on("click",function(){
			var bookInfo = {};
			// 设置Id
			bookInfo.id = $("#bookInfoId").val();

			//编码
			var code = $("#code").val();
			if(code == null || code == ""){
				layer.msg("请输入图书编号!");
				return false;
			}
			// 设置图书名称
			var bookName = $("#bookName").val();
			if(bookName != null && bookName != ""){
				bookInfo.bookName = bookName;
			}else {
				layer.msg("请输入图书名称!");
				return false;
			}
			// 设置一级子类
			var oneSubClass = $("#oneSubClass").val();
			if(oneSubClass != "--请选择--"){
				bookInfo.oneSubClass = oneSubClass;
			}else {
				layer.msg("请选择一级子类!");
				return false;
			}
			
			// 设置二级子类
			var twoSubClass = $("#twoSubClass").val();
			if(twoSubClass != "--请选择--"){
				bookInfo.twoSubClass = twoSubClass;
			}else {
				layer.msg("请选择二级子类!");
				return false;
			}
			
			// 设置出版年月
			if($("#publishDate").val()!=""){
				bookInfo.publishDate = $("#publishDate").val()+"-01";
			}else{
				bookInfo.publishDate = "";
			}
			
			// 设置版本号
			bookInfo.version = $("#version").val();
			// 设置库存数量
			var total = $("#total").val();
			if(total != null && total != ""){
				bookInfo.total = total;
			}else{
				layer.msg("请输入库存数量!");
				return false;
			}
			if(bookInfo.id == null || bookInfo.id == ""){
				// 创建库存总数和在库、可用数量是一致的
				bookInfo.inventoryNum = total;
				bookInfo.canBorrowing = total;
			}else{
				// 编辑库存则有多种情况
				var  sum = $("#sum").val();
				if(Number(total)>=Number(sum)){
					// 总数增加了
					var plus = Number(total)-Number(sum);
					// 在库也加上增加量
					bookInfo.inventoryNum = Number($("#inventoryNum").val())+plus;
					// 可用数量也加上增加量
					bookInfo.canBorrowing = Number($("#canBorrowing").val())+plus;
				}else{
					// 总数减少了 
					var reduce = Number(sum)-Number(total); 
					// 外借的数量
					var borrowNum = Number(sum)-Number($("#inventoryNum").val());
					// 没有外借
					if(Number(borrowNum)==0){
						bookInfo.inventoryNum = Number($("#inventoryNum").val())-Number(reduce);
						var canborrowNum = Number($("#canBorrowing").val())-Number(reduce);
						if(Number(canborrowNum)<=0){
							bookInfo.canBorrowing = 0;	
						}else{
							bookInfo.canBorrowing = canborrowNum;
						}
					}else{ // 有外借
						if(Number(reduce)>Number($("#inventoryNum").val())){
							layer.msg("库存减少数量不能大于在库数量！");
							return false;
						}
						var inventoryNum = Number($("#inventoryNum").val())-Number(reduce);
						bookInfo.inventoryNum = inventoryNum;
						// 可借数量
						var canBorrowNum = Number($("#canBorrowing").val())-Number(reduce);
						if(Number(canBorrowNum)<=0){
							bookInfo.canBorrowing = 0;
						}else{
							bookInfo.canBorrowing = canBorrowNum;
						}
						
					}
				}
				
			}
			// 设置出版单位
			var publishUnit = $("#publishUnit").val();
			bookInfo.publishUnit = publishUnit;
			bookInfo.code = $("#code").val();
			bookInfo.author = $("#author").val();
			if($("#printDate").val()!=""){
				bookInfo.printDate = $("#printDate").val()+"-01";
			}else{
				bookInfo.printDate = "";
			}
			bookInfo.bookNo = $("#bookNo").val();
			bookInfo.price = $("#price").val();
			bookInfo.savePlace = $("#savePlace").val();
			bookInfo.fileStatus = $("#fileStatus").val();
			bookInfo.downPermission = $("#downPermission").val();
			bookInfo.remarks = $("#remarks").val();
			bookInfo.flowSuggestion = $("#flowSuggestion").val();
			
			// 转成JSON
			var bookInfoJson = JSON.stringify(bookInfo);
			var param = common.add_param("bookInfo",bookInfoJson);
			
			common.init("../bookinfo/add","POST",function(result){
				// 判断图书是否重复
				if (result.code == 0) {
					if(result.value !="" && result.value != null){
						layer.msg(result.value);
						return;
					}else{
						layer.msg("图书编号已存在，请重新填写！");
						return;
					}	
					
				}
				layer.msg("操作成功");
				// 返回图书资料列表页面
				common.toPage("../bookinfo/index");
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
			common.toPage("../bookinfo/index");
			// 执行刷新按钮的点击事件
			parent.$("#refresh").click();
		});
		
		 $("#code").blur(function(){
			var param = common.add_param("code",$("#code").val());
			common.init("../bookinfo/getByCode","POST",function(result){
					if (result.value == null) {
						layer.msg("图书编号不存在，没有数据！");
						return;
					}
					$("#bookName").val(result.value.bookName);
					$("#author").val(result.value.author);
					$("#oneSubClass option").each(function(){
						if($(this).val()==result.value.oneSubClass){
		     				$(this).attr("selected","selected");
		     			}
					});
					edit_book_info.twoSubClassSelect(result.value.oneSubClass);
					$("#twoSubClass option").each(function(){
     					if($(this).val()==result.value.twoSubClass){
		     				$(this).attr("selected",true);
     					}
     				});
					$("#bookNo").val(result.value.bookNo);
					$("#publishUnit").val(result.value.publishUnit);
					var date = new Date(result.value.publishDate);
					var Y = date.getFullYear() + '-';
					var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1);
					$("#publishDate").val(Y+M);
					$("#version").val(result.value.version);
					var date1 = new Date(result.value.printDate);
					var Y1 = date1.getFullYear() + '-';
					var M1 = (date1.getMonth()+1 < 10 ? '0'+(date1.getMonth()+1) : date1.getMonth()+1);
					$("#printDate").val(Y1+M1);
					$("#price").val(result.value.price);
					$("#total").val(result.value.total);
					$("#savePlace").val(result.value.savePlace);
					$("#fileStatus").val(result.value.fileStatus);
					$("#downPermission").val(result.value.downPermission);
					$("#remarks").val(result.value.remarks);
					$("#flowSuggestion").val(result.value.flowSuggestion);
					
					layer.msg("加载成功");

				});
				common.do_submit(param);
		 });
	}
}