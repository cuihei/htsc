$(function(){
	chartCompletion.init();

})
       //获取当前年 
		var mydate = new Date();
		var t=mydate.getFullYear();

var chartCompletion = {
	init : function(){
		// 初始化表格对象
		grid.init("chartCompletion");
		// 显示加载层
		//loading.init();
		try{
			var type = $("#type").val();
			if(type == "distribution"){
				
				// 创建表格
		//		$("#year").hide();
		//		$("#year").siblings().hide();
				$("#compType").parent().parent().attr("style","display:none");
				chartCompletion.createDistributionChartCompletionGrid();
			}else{
				// 创建表格
				var key = $("#key").val();
				if(key!=null && key!='' && key!=undefined){
					$("#compType").parent().parent().attr("style","display:none");
				}
				chartCompletion.createChartCompletionGrid();
			}
			// 发送数据请求绑定表格数据
			//chartCompletion.requestChartCompletionData();
		}
		catch(err){
			//loading.close();
		}
		//$("#export").hide();
		$("#export").on("click",function(){
			
			chartCompletion.exportExcel();
		});
		
		$("#compType").on("change",function(){
			var type = $("#type").val();
			var year = $("#year").val();
			var compType = $("#compType").val();
					grid.bindServerData("../chartCompletion2/all?year="+year+"&compType="+compType+"&type="+type,null);

	
		});
		
		$("#year").on("change",function(){
			
			
			var type = $("#type").val();
			var year = $("#year").val();
		
			var compType = $("#compType").val();
			//alert("comptype=>" +compType+"    year=>"+year);
					grid.bindServerData("../chartCompletion2/all?year="+year+"&compType="+compType+"&type="+type,null);
	
		});
	},
	
	/**
	 * 创建分配任务表格
	 */
	createDistributionChartCompletionGrid : function(){
		grid.resetColumn();
		// 显示业务列
		var isComplete = $("#isComplete").val();
		var year = $("#year").val();
		// 流程列
		grid.addColumn("200px","taskbookNo","任务书编号");
		grid.addColumn("100px","mapNo","图号");
		grid.addColumn("200px","mapName","图名");
		grid.addColumn("100px","scale","比例尺 1:");
		grid.addColumn1("100px","processDefName","任务类型");
		grid.addColumn("100px","status","状态");
		grid.addColumn("200","taskName","节点名称");
		grid.addColumn("100px","compilationGroup","编绘组");
		grid.addColumn("100px","performer","当前处理人");
		grid.addColumn("100px","creationDate","处理时间","#=creationDate ? kendo.toString(new Date(creationDate), 'yyyy-MM-dd') : '' #" );
		var columns = grid.addColumn("100px","except","操作","<button class='btn btn-info' onclick='chartCompletion.exportBook(this)'>导出</button>");
		var compType = $("#compType").val();
		var type = $("#type").val();
		grid.createGrid(columns);
		
		grid.bindServerData("../chartCompletion2/all?year="+t+"&compType="+compType+"&type="+type,columns);
	
	},
	
	/**
	 * 创建任务表格
	 */
	createChartCompletionGrid : function(){
		grid.resetColumn();
		// 显示业务列
		var isComplete = $("#isComplete").val();
		var compType = $("#compType").val();
		var year = $("#year").val();
		
		
	    grid.addLockedColumn("200px","taskbookNo","任务书编号");
		grid.addLockedColumn("100px","mapNo","图号");
		grid.addLockedColumn("300px","mapName","图名");
		grid.addLockedColumn("100px","scale","比例尺 1:");
		grid.addLockedColumn("100px","processDefName","任务类型");
		grid.addLockedColumn("100px","taskName","当前节点");
		grid.addLockedColumn("100px","status","状态");
		grid.addLockedColumn("100px","currUser","当前操作人","#=='制图事业部科长确认' ? kendo.toString('制图科长') : '' #" );
		grid.addColumn("100px","delivertime","计划汇交","#= delivertime ? kendo.toString(new Date(delivertime), 'yyyy-MM-dd') : '' #");
		grid.addColumn("100px","sjhjtime","实际汇交");
		grid.addColumn("100px","yjtime","预警");
		grid.addColumn("100px","type","制图性质");
        grid.addColumn("100px","taskBookTime","任务书创建时间");
		grid.addColumn("100px","compilationGroup","编绘组");
		grid.addColumn("100px","compilationPerson","编绘员");
		grid.addColumn("100px","compilationWorkdays","编绘工天")
		grid.addColumn("100px","compilationWorkdays","倒计时天（天）");
		
		grid.addColumn("100px","compilationStartTime","编绘开始时间");
		grid.addColumn("100px","compilationEndTime","编绘结束时间");
		grid.addColumn("100px","qualityPerson","质检人");
		grid.addColumn("100px","qualityStartTime","质检开始时间")
		grid.addColumn("100px","qualityEndTime","质检结束时间")
		grid.addColumn("100px","qualityScore","质检评分");
		grid.addColumn("100px","checkWorkdays","质检工天");
		grid.addColumn("140px","ztTime","制图事业部科长审核时间");
		grid.addColumn("100px","apprvoePerson","审定人");
		grid.addColumn("100px","apprvoeStartTime","审定开始时间");
		grid.addColumn("100px","apprvoeEndTime","审定结束时间");
		grid.addColumn("100px","examineWorkdays","审定工天");
		grid.addColumn("100px","score","审定评分");
		grid.addColumn("150px","zlTime","质量检验科科长审核时间");
		var columns = grid.addColumn("100px","except","操作","<button class='btn btn-info' onclick='chartCompletion.exportBook(this)'>导出</button>");
		var type = $("#type").val();
		grid.createGrid(columns);
		var key = $("#key").val();
		//默认显示当前年的未完成的任务  0全部任务 1 已完成  2 未完成  2018.8.29
        //alert("comptype=>" +compType+"    year=>"+year+"     type=>"+type+"     key=>"+key);

		
		if(key==null || key=='' || key==undefined){
		///进入页面 显示绘任务成情况

		grid.bindServerData("../chartCompletion2/all?compType="+compType+"&year="+year+"&type="+type,columns);
	    }else{
			grid.bindServerData("../chartCompletion2/listByKey?processDefKey="+key+"&isComplete="+isComplete+"&type="+type,columns);
		}
		
		var gridInst = grid.getGrid().data("kendoGrid");
		gridInst.lockColumn("selected");
		gridInst.lockColumn("rowNumber");
/*		gridInst.reorderColumn(0, gridInst.columns[8]);
		gridInst.reorderColumn(1, gridInst.columns[9]);*/
		
		gridInst.reorderColumn(0, gridInst.columns[8]);
		gridInst.reorderColumn(1, gridInst.columns[9]);
		
	},
	
	/**
	 * 发送获取任务列表请求
	 */
	requestChartCompletionData : function(){
		var key = $("#key").val();
		var isComplete = $("#isComplete").val();
		var type = $("#type").val();
		if(key==null || key=='' || key==undefined){
		
			common.init("../chartCompletion2/all?year="+t+"&type="+type,"POST",chartCompletion.bindChartCompletionGrid);
		
		}else{
	
			common.init("../chartCompletion2/listByKey?processDefKey="+key+"&isComplete="+isComplete,"POST",chartCompletion.bindChartCompletionGrid);
			
		}
		common.do_submit();
	},
	
	/**
	 * 绑定任务数据
	 */
	bindChartCompletionGrid : function(result){
		
		// 綁定数据
		grid.bindData(result);
		var gridInst = $("#chartCompletion").data("kendoGrid");
		gridInst.setOptions({
			sortable: true,
			lockedable: false,
			autoFitColumnable: false,
			columnMenu: true,
			pageable : {
				pageSizes : true,
				buttonCount : 5,
				pageSize : 50
			},
		});
		// 提示加载成功信息
		layer.msg(msg.LOAD_SUCCESS);
	},
	
	/**
	 * 流转
	 */
	flow : function(obj){
		var tr = $(obj).parent().parent();
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		var procInstId = rowData.processInstId;
		var taskInstId = rowData.taskInstId;
		common.toPage("../workflow/flow_init?taskInstId="+taskInstId+"&processInstId="+procInstId);
	},
	
	formatDateTime : function(inputTime){
		var date = new Date(inputTime);  
	    var y = date.getFullYear();    
	    var m = date.getMonth() + 1;    
	    m = m < 10 ? ('0' + m) : m;    
	    var d = date.getDate();    
	    d = d < 10 ? ('0' + d) : d; 
	    return y + '-' + m + '-' + d;
	},
	
	process: function(obj){
		var tr = $(obj).parent().parent();
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取用户ID
		var flowId = rowData.processDefId;
		var excutionId = rowData.excutionId;
		// 跳转到查看流程圖页面
		common.toPage("../workflow/task_flow_page?flowId="+flowId+"&excutionId="+excutionId);
	},
	/**
	 * 导出经历簿
	 */
	exportBook : function(obj){

		var tr = $(obj).parent().parent();
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		var parentProcessInstId = rowData.parentProcessInstId;
		var processDefKey = rowData.processDefId.split(":")[0];
		var processInstId = rowData.processInstId;
		var param = "parentProcessInstId="+parentProcessInstId+"&processDefKey="+processDefKey+"&processInstId="+processInstId;
		window.location.href = "../workflow/export_book?"+param;
	},
	
	exportExcel :function(){
	
		//获取SELECT选中的值
		var cType=$("#compType   option:selected").text();
		var cYear=$("#year   option:selected").text();
		var gridInst = grid.getGrid().data("kendoGrid");
		gridInst.bind("excelExport", function(e) {
		    e.workbook.fileName =cYear+"年 编绘任务 "+cType + "情况统计表.xlsx";
		    $.each(e.data, function (i, item) {//逐行处理数据
		    	
		    	e.workbook.sheets[0].rows[i + 1].cells[1].value =i+1;
		    	
/*		    	if(item.taskBookTime != '' && item.taskBookTime != null){
		    		e.workbook.sheets[0].rows[i + 1].cells[9].value = kendo.toString(new Date(item.taskBookTime), 'yyyy-MM-dd');
		    	}
		    	if(item.compilationExpectedTime != '' && item.compilationExpectedTime != null){
		    		e.workbook.sheets[0].rows[i + 1].cells[12].value = kendo.toString(new Date(item.compilationExpectedTime), 'yyyy-MM-dd');
		    	}
		    	if(item.compilationStartTime != '' && item.compilationStartTime != null){
		    		e.workbook.sheets[0].rows[i + 1].cells[14].value = kendo.toString(new Date(item.compilationStartTime), 'yyyy-MM-dd');
		    	}
		    	if(item.compilationEndTime != '' && item.compilationEndTime != null){
		    		e.workbook.sheets[0].rows[i + 1].cells[15].value = kendo.toString(new Date(item.compilationEndTime), 'yyyy-MM-dd');
		    	}
		    	if(item.qualityExpectedTime != '' && item.qualityExpectedTime != null){
		    		e.workbook.sheets[0].rows[i + 1].cells[17].value = kendo.toString(new Date(item.qualityExpectedTime), 'yyyy-MM-dd');
		    	}
		    	if(item.qualityStartTime != '' && item.qualityStartTime != null){
		    		e.workbook.sheets[0].rows[i + 1].cells[18].value = kendo.toString(new Date(item.qualityStartTime), 'yyyy-MM-dd');
		    	}
		    	if(item.qualityEndTime != '' && item.qualityEndTime != null){
		    		e.workbook.sheets[0].rows[i + 1].cells[19].value = kendo.toString(new Date(item.qualityEndTime), 'yyyy-MM-dd');
		    	}
		    	if(item.ztTime != '' && item.ztTime != null){
		    		e.workbook.sheets[0].rows[i + 1].cells[22].value = kendo.toString(new Date(item.ztTime), 'yyyy-MM-dd');
		    	}
		    	if(item.apprvoeExpectedTime != '' && item.apprvoeExpectedTime != null){
		    		e.workbook.sheets[0].rows[i + 1].cells[24].value = kendo.toString(new Date(item.apprvoeExpectedTime), 'yyyy-MM-dd');
		    	}
		    	if(item.apprvoeStartTime != '' && item.apprvoeStartTime != null){
		    		e.workbook.sheets[0].rows[i + 1].cells[25].value = kendo.toString(new Date(item.apprvoeStartTime), 'yyyy-MM-dd');
		    	}
		    	if(item.apprvoeEndTime != '' && item.apprvoeEndTime != null){
		    		e.workbook.sheets[0].rows[i + 1].cells[26].value = kendo.toString(new Date(item.apprvoeEndTime), 'yyyy-MM-dd');
		    	}
		    	if(item.zlTime != '' && item.zlTime != null){
		    		e.workbook.sheets[0].rows[i + 1].cells[29].value = kendo.toString(new Date(item.zlTime), 'yyyy-MM-dd');
		    	}*/
                //e.workbook.sheets[0].rows[i + 1].cells[1].value = value;
			});
		});
		
		var excel={};
		$.extend(excel,gridInst);//此时excel就是grid的副本，拥有和grid一样的属性和函数,对grid的操作可以转移到excel上来进行
		var type = $("#type").val();
		if(type == "distribution"){
			$.each(excel._data,function (i,item) {//逐行处理数据
				item.rowNumber = i+1;
			});
		}else{
			/*$.each(excel._data,function (i,item) {//逐行处理数据
				item.rowNumber = i+1-(excel._data.length/2);
				if(item.taskBookTime != '' && item.taskBookTime != null){
					item.taskBookTime = kendo.toString(new Date(item.taskBookTime), 'yyyy-MM-dd');
				}
			});*/
			
			 
			
		}
		
		
		excel.saveAsExcel();
		
/*		//先隐藏掉不需要导出的列
		excel.hideColumn("selected");
		excel.hideColumn("except");
		//导出
		
		//重新设置为显示
		excel.showColumn("selected");
		excel.showColumn("except");*/
	}
}