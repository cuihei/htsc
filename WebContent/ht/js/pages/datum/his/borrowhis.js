$(function(){
	borrowing.init();
})

/** 绑定归还按钮的click事件*/
function returnPage(obj) {
	var tr = $(obj).parent().parent();
	var rowData = grid.getSelectRowDataByRow(tr);
	var id = rowData.borrowId;
	layer.confirm('确认要归还这一种图书吗？',function(index){
		// 添加参数 @param 参数key；参数value
		common.toPage("../bookinfo/batch_return_init?ids="+id);
	});
}

var borrowing = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("borrowHis");
		loading.init();
		try{
			borrowing.createborrowingGrid();
			borrowing.requestborrowingData();
			borrowing.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 创建借阅记录列表
	 */
	createborrowingGrid : function(){
		var columns = borrowing.createborrowingColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 构建借阅记录集合
	 */
	createborrowingColumns : function(){
		grid.resetColumn();
		grid.addColumn("120px","borrowBookName","图书名称");
		grid.addColumn("100px","borrowCode","图号");
		grid.addColumn("100px","concurrentTime","出版年月");

		grid.addColumn("100px","type","资料类型");
	//	grid.addColumn("100px","concurrentTime","汇交时间","#= concurrentTime ? kendo.toString(new Date(concurrentTime), 'yyyy-MM-dd') : '' #");
		
		grid.addColumn("100px","borrowPerson","借阅人");
		grid.addColumn("80px","borrowNo","借阅数量");
		grid.addColumn("120px","borrowDate","借阅时间");
		grid.addColumn("120px","jystatus","借阅状态");
		grid.addColumn("120px","returnPerson","归还人");
		grid.addColumn("80px","returnNo","已归还数量");
		//grid.addColumn("80px","shouldReturn","剩余归还数量");
		grid.addColumn("120px","returnDate","归还时间");
		return grid.addColumn("120px","ghstatus","归还状态");
		var type = $("#type").val();
		/*if(type == '1'){
			grid.addColumn("120px","ghstatus","归还状态");
			return grid.addColumn("80px","handle","归还",kendo.template($("#qzTemplate").html()));
		}else{
			return grid.addColumn("120px","ghstatus","归还状态");
		}*/
		
	},
	
	/**
	 * 发送数据请求
	 */
	requestborrowingData : function(){
		var type = $("#type").val();
		var param = common.add_param("type",type);
		common.init("../borrowing/list","POST",borrowing.bindborrowingGrid);
		common.do_submit(param);
	},
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindborrowingGrid : function(result){
		grid.bindData(result);
		
		/** 判断列归还按钮是否显示*/
		var returnBtns = $("button[name='return']");
		$.each(returnBtns,function(i,item){
			var tr = $(item).parent().parent();
			var rowData = grid.getSelectRowDataByRow(tr);
			
			if(rowData.ghstatus == '审核通过'){
				if(rowData.borrowNo == rowData.returnNo){
					$(item).attr('disabled',true); 
				}
			}else if(rowData.ghstatus == '待审核'){
				$(item).attr('disabled',true); 
			}
			if(rowData.shouldReturn == '0'){
				$(item).attr('disabled',true); 
			}
			if(rowData.jystatus != '审核通过'){
				$(item).attr('disabled',true); 
			}
		});
	},
	/**
	 * 绑定页面点击事件
	 */
	bindPageEvent : function(){
		
		/**
		 * 点击归还
		 */
		$("#batchreturn").on("click",function(){
			//获取选中行数据对象
			var rowDatas = grid.getSelectRowsData();
			if (rowDatas.length<=0) {
				layer.msg('未选择任何行!', {icon:5,time:1000});
				return;
			}
			var borrowHiss = [];
			var ids = "";
			$.each(rowDatas,function(i,item){
				var borrowId = $("#borrowHis").data("kendoGrid").dataItem(item).borrowId;
				var shouldReturn = $("#borrowHis").data("kendoGrid").dataItem(item).shouldReturn;
				var borrowHis = {};
				borrowHis.borrowId = borrowId;
				ids += borrowId+",";
				borrowHis.shouldReturn = shouldReturn;
				borrowHiss.push(borrowHis);
			});
			for (var i = 0; i< borrowHiss.length; i++) {
				if(borrowHiss[i].shouldReturn == '0' || borrowHiss[i].shouldReturn == null){
					layer.msg("借阅资料已经归还,请重新选择！");
					return;
				}
			}
			layer.confirm('确认要归还这'+borrowHiss.length+'种图书吗？',function(index){
				// 添加参数 @param 参数key；参数value
				var param = common.add_param("ids",ids);
				common.toPage("../bookinfo/batch_return_init?ids="+ids);
			});
		});
		
		$("#export").on("click",function(){
			var grid = $("#borrowHis").data("kendoGrid");
			grid.setOptions({
				excel:{
					//是否导出所有页中的数据
				    allPages:true,
				    fileName: "借阅归还记录.xlsx"
				},
				excelExport: function (e) {
			        $.each(e.data, function (i, item) {
			            var value=item.Code;
		                e.workbook.sheets[0].rows[i + 1].cells[1].value = i+1;
			        	e.workbook.sheets[0].rows[i].cells.splice(0,1)
			       }); 
				},
			});
			grid.saveAsExcel();
		})
	}
	
}