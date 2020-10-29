$(function(){
	batch_book_info_return.init();
})

var batch_book_info_return = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("batchReturn");
		loading.init();
		try{
			batch_book_info_return.createGrid();
			batch_book_info_return.requestData();
			batch_book_info_return.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 创建列表
	 */
	createGrid : function(){
		var columns = batch_book_info_return.createColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 构建列集合
	 */
	createColumns : function(){
		grid.resetColumn;
		grid.addColumn("100px","borrowCode","图书编号");
		grid.addColumn("200px","borrowBookName","图名/资料名");
		grid.addColumn("130px","type","资料类型");
		grid.addColumn("120px","shouldReturn","应归还数量");
		return	grid.addColumn("100px","returnNo","归还数量","1");
	//	return grid.addColumn("100px","edit","填写归还数量",kendo.template("<a style='cursor:pointer;color:black' onclick='batch_book_info_return.bookinfoEdit.call(this)'>修改</a>"));
	},
		
	/**
	 * 发送数据请求
	 */
	requestData : function(){
		var ids = $("#ids").val();
		common.init("../bookinfo/return_list?ids="+ids,"POST",batch_book_info_return.bindGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindGrid : function(result){
		grid.bindData(result);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		
		$("#back").on("click",function(){
			window.history.go(-1);
		})
		
		/**
		 * 提交申请表
		 */
		$("#submit").on("click",function(){
			//获取选中行数据对象
			var Datas = grid.getAllRowsData();
			var ids = "";
			var returnNo = "";
			var shouldReturn="";
			$.each(Datas,function(i,item){
				ids +=","+item.id;
				//returnNo += ","+item.returnNo;
				returnNo += ","+1;
				shouldReturn+= ","+item.shouldReturn;
			});
			ids = ids.substring(1);
			returnNo = returnNo.substring(1);
			shouldReturn = shouldReturn.substring(1);
			
			// 如果存在borrowNo为空，则不能提交
			/*	var array = returnNo.split(",");
			var array1 = shouldReturn.split(",");
	     	for (var int = 0; int < array.length; int++) {
				if(array[int] == 'undefined'){
					layer.msg("请确认填写好归还数量后再提交！");
					return;
				}
				if(Number(array[int])>0){
					var size=Number(array[int]);
					if(size>Number(array1[int])){
						layer.msg("归还数量超过应归还数量，请重新输入！");
						return;
					}
				    if(!Number.isInteger(size)){
						layer.msg("归还数量应为整数，请重新输入！");
						return;
					}
				}else{
					layer.msg("归还数量不能小于0，请重新输入！");
					return;
				}
			}
*/
			common.add_param("ids",ids);
			var param = common.add_param("returnNos",returnNo);
			common.init("../workflow/publishreturnbooks","POST",function(result){
				if (result.code == 0) {
					layer.msg(result.value);
					return;
				}
				layer.msg("提交成功");
				window.history.go(-1);
			});
			common.do_submit(param);
		})
	},
	
	bookinfoEdit : function(obj){
		var td = $(this).closest("tr").find("td:eq(6)");
		var gridInst = grid.getGrid().data("kendoGrid");
		if (!this.modify) {
			
			this.modify = true;
			$(this).text("保存");
    		gridInst.editCell(td);
    		gridInst.table.off("click", "tr");
		} else {
			var row = $(this).parent().parent();
			var rowData = gridInst.dataItem(row);
			var returnNo = rowData.returnNo;// 归还数量
			var shouldReturn = rowData.shouldReturn;// 应归还数量
			if(Number(returnNo)>0){
				var size=Number(returnNo);
			    if(!Number.isInteger(size)){
					layer.msg("归还数量应为整数，请重新输入！");
					return;
				}
				if(Number(returnNo)>Number(shouldReturn)){
					layer.msg("归还数量超过应归还数量，请重新输入！");
					return;
				}else{
					this.modify = false;
					$(this).text("修改");
					gridInst.saveChanges();
					gridInst.closeCell();
				}
					
				
			}else{
				layer.msg("归还数量应大于0，请重新输入！");
				return;
			}
		}
	}
	
}