$(function(){
	plan.init();

})

/** 绑定编辑目录窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	plan.toEditPage(tr);
}

var plan = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("plan");
		loading.init();
		try{
			plan.createGrid();
			plan.requestData();
			plan.bindPageEvent();
		//	loading.close();
		}
		catch(err){
			loading.close();
		}
		//初始化时间控件
		$("#year").datepicker({
			startView: 2, 
			maxViewMode: 2,
			minViewMode:2,
			 format: 'yyyy',
			 autoclose: true
		}).on('changeDate',gotoDate);
		function gotoDate(){
			var year = $("#year").val();
			var categoryId = $("#categoryId").val();
			loading.init();
			common.init("../plan/list?categoryId="+categoryId+"&year="+year,"POST",plan.bindGrid);
    		common.do_submit();
		}
		//月计划时间change
		$("#yearMonth").on('changeDate',changeYearMonth);
		function changeYearMonth(){
			var date = $("#yearMonth").val();
			if($("#yearMonth").val()!=null&&$("#yearMonth").val()!=""){
				date=$("#yearMonth").val()+'-01';
			}
			var categoryId = $("#categoryId").val();
			loading.init();
			common.init("../plan/monthPlanList?categoryId="+categoryId+"&date="+date,"POST",plan.bindGrid);
			common.do_submit();
		}
	},
	
	/**
	 * 创建计划列表
	 */
/*	createGrid : function(){
		var type = $("#type").val();
		var columns = plan.createColumns(type);
		grid.createGrid(columns);
		var gridInst = grid.getGrid().data("kendoGrid");
		gridInst.lockColumn("selected");
		gridInst.lockColumn("rowNumber");
		gridInst.reorderColumn(0, gridInst.columns[2]);
		gridInst.reorderColumn(1, gridInst.columns[3]);
		gridInst.hideColumn("year");
		gridInst.hideColumn("measurementPeriod");
		gridInst.hideColumn("nature");
		gridInst.hideColumn("lastYear");
		gridInst.setOptions({
			columnMenu: true
	    });
	},
	*/
	createGrid : function(){
		var type = $("#type").val();
		var columns = plan.createColumns(type);
		grid.createGrid(columns);
		var gridInst = grid.getGrid().data("kendoGrid");
		gridInst.hideColumn("year");
		gridInst.hideColumn("measurementPeriod");
		gridInst.hideColumn("testIng");
		gridInst.hideColumn("nature");
		gridInst.hideColumn("lastYear");

	},
	
	
	
	/**
	 * 构建月度计划列集合
	 */
	createColumns : function(type){
		grid.resetColumn();

		grid.addColumn("120px","mapNo","图号");
		if(type == "3"){
			grid.addColumn("120px","mapName","任务名");
		}else{
			grid.addColumn("120px","mapName","图名");
		}
		grid.addColumn("130px","area","海区");
		grid.addColumn("150px","scale","比例尺 1:");
		grid.addColumn("120px","type","类别");
		grid.addColumn("150px","deliverTime","计划汇交日期","#= deliverTime ? kendo.toString(new Date(deliverTime), 'yyyy-MM-dd') : '' #");
		grid.addColumn("150px","year","年份");
		if(type != "1"){
			grid.addColumn("150px","planMonth","所属月份");
		}
		grid.addColumn("150px","measurementPeriod","基测周期");
		grid.addColumn("150px","testIng","基测周期");
		grid.addColumn("150px","taskSource","任务来源");
		grid.addColumn("150px","nature","上次测量/编绘性质");
		grid.addColumn("150px","lastYear","上次测量年份");
		if(type == '2'){
			grid.addColumn("150px","taskType","任务类型");
		}
		if($("#jurisdiction").val()=="true"){
			grid.addColumn("200px","discription","说明",null,null,null,false);
			return grid.addColumn("100px","handle","操作",kendo.template($("#editTemplate").html()),null,null,null,false);
		}else{
			return	grid.addColumn("200px","discription","说明",null,null,null,false);
			
		}
	},
		
	/**
	 * 发送数据请求
	 */
	requestData : function(){
		var type = $("#type").val()
		var categoryId = $("#categoryId").val();
		var yearMonth="";
		if(type=='2'){
			//月计划
			if($("#yearMonth").val()!=''){
				var date = $("#yearMonth").val()+'-01';
			}else{
				var date = kendo.toString(new Date(), 'yyyy-MM-dd');
			}
			common.init("../plan/monthPlanList?categoryId="+categoryId+"&date="+date,"POST",plan.bindGrid);
		}else{
			var year = $("#year").val();
			common.init("../plan/list?categoryId="+categoryId+"&year="+year,"POST",plan.bindGrid);
		}
		
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindGrid : function(result){
		var data = result.value;
		if(data != null && data != undefined){
			for(var i=0;i<data.length;i++){
				//区域判断
				var area = data[i].area;
				if(area != null &&　area.areaName != null){
					data[i]["areaName"] = area.areaName;
				}
			}
		}
		grid.bindData(result,true);
	},
	
	/**
	 * 删除月度计划
	 */
	removePlan : function(){
		//获取选中行数据对象
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length<=0) {
			layer.msg('未选择任何行!', {icon:5,time:1000});
			return;
		}
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var plans = [];
			$.each(rowDatas,function(i,item){
				var id = $("#plan").data("kendoGrid").dataItem(item).id;
				var plan = {};
				plan.id = id;
				plans.push(plan);
			});
			//删除关联plan
			$.each(rowDatas,function(i,item){
				var relationId = $("#plan").data("kendoGrid").dataItem(item).relationId;
				var plan = {};
				plan.id = relationId;
				plans.push(plan);
			});
			var res = [plans[0]];
			for (var i = 1; i < plans.length; i++) {
                var repeat = false;
                for (var j = 0; j < res.length; j++) {
                    if (plans[i].id== res[j].id) {
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                	res.push(plans[i]);
                }
            }
			var paramJson = JSON.stringify(res);
			// 添加参数 @param 参数key；参数value
			var param = common.add_param("ids",paramJson);
			common.init("../plan/remove","POST",plan.removeSuccess);
			// 执行提交操作
			common.do_submit(param);
		});
	},
	
	/**
	 * 跳转到添加页面
	 */
	toAddPage : function(){
		var type = $("#type").val();
		var categoryId = $("#categoryId").val();
		common.toPage("../plan/init_edit?type="+type+"&categoryId="+categoryId);
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(result){
		grid.init("plan");
		layer.close(1);
		if(result.code != 1){
			layer.msg("删除失败！");
			return;
		}
		layer.msg(result.value);
		plan.requestData();
	},
	
	/**
	 * 跳转到目录编辑页面
	 */
	toEditPage : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取目录ID
		var id = rowData.id;
		var type = $("#type").val();
		var categoryId = $("#categoryId").val();
		// 跳转到目录编辑页面
		common.toPage("../plan/init_edit?id="+id+"&type="+type+"&categoryId="+categoryId);
	},
	
	/**
	 * 上传文件
	 */
	importExcel : function(){
		// 判空
		var data = $('#uploadFile').val();
		if(data==""){
			layer.msg('请选择文件！');
			return;
		}
		var fileType=data.substr(data.lastIndexOf(".")).toLowerCase();
	    if(fileType != '.xls'&&fileType != '.xlsx'){
	    	layer.msg('请导入xls或xlsx格式的文件');
			return;
	    }
		loading.init();
		$("#importForm").ajaxSubmit({  
            type: 'post',  
            url: "../plan/upload" ,  
            beforeSubmit: function() { 
            	return true;
            } ,  
            success: function(result){
            	result = eval("("+result+")");
                if(result.code != 1 ){
                	loading.close();
                	layer.msg("导入失败，请检查数据无误后再导入！");
                	return;
                }
                loading.close();
                layer.msg(result.value,{icon:1,time:6000});
                plan.requestData();
            },  
            error: function(XmlHttpRequest, textStatus, errorThrown){
            	loading.close();
            	layer.msg("系统错误，请联系管理员！");  
            }  
        });  
	},	
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加目录窗口按钮的click事件
		 */
		$("#add").on("click",function(){
			plan.toAddPage();
		});
		
		/** 
		 * 绑定删除目录窗口按钮的click事件
		 */
		$("#remove").on("click",function(){
			plan.removePlan();
		});
		
		/** 绑定刷新目录列表窗口按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
		
		/** 绑定上传文件的click事件*/
		$("#importSubmit").on("click",function(){
			plan.importExcel();
		});
		
		/** 绑定导出文件模板的click事件*/
		$("#exportTemplate").on("click",function(){
			$("#importForm").attr("action", "../plandownload/template");
			$("#importForm").submit();
		});
		
		/** 绑定导出文件的click事件*/
		$("#export").on("click",function(){
			var year  = $("#year").val();
			var type = $("#type").val();
			if(type == '2'){
				year = $("#yearMonth").val()
			}
			var categoryId = $("#categoryId").val();
			window.location.href="../plandownload/excel?type="+type+"&categoryId="+categoryId+"&year="+year;
		});
		
		/** 年度布局计划的click事件*/
		$("#planAttachment").on("click",function(){
			var year  = $("#year").val();
			common.toPage("../plan/planAttachmentInit?year="+year);
		});
	}
}