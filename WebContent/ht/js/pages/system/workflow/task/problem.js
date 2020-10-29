$(function(){
	problem.init();
})

function downWtfjPage(obj) {
	var tr = $(obj).parent().parent();
	// 获取选中行数据对象
	var rowData = grid.getSelectRowDataByRow(tr);
	//获取附件地址
	var url=rowData.WTFJ;
	if(url == null || url == ""){
		layer.msg("这个问题链接没有上传文件！");
		return;
	}else{
		// 下载
		common.init("../formValue/fileisexist?url="+url,"POST",function(result){
			if (result.code == 0) {
				layer.msg(result.value);
				return;
			}else{
				if(result.value == 'exist'){
					window.location.href = "../formValue/form_file?url="+url;
				}else{
					layer.msg("该问题链接文件不存在！");
					return;
				}
			}
		});
		common.do_submit();
	}
}

function downFhPage(obj) {
	var tr = $(obj).parent().parent();
	// 获取选中行数据对象
	var rowData = grid.getSelectRowDataByRow(tr);
	//获取附件地址
	var url=rowData.FH;
	if(url == null || url == ""){
		layer.msg("这个发函没有上传文件！");
		return;
	}else{
		// 下载
		common.init("../formValue/fileisexist?url="+url,"POST",function(result){
			if (result.code == 0) {
				layer.msg(result.value);
				return;
			}else{
				if(result.value == 'exist'){
					window.location.href = "../formValue/form_file?url="+url;
				}else{
					layer.msg("该发函文件不存在！");
					return;
				}
			}
		});
		common.do_submit();
	}
}

function downHhPage(obj) {
	var tr = $(obj).parent().parent();
	// 获取选中行数据对象
	var rowData = grid.getSelectRowDataByRow(tr);
	//获取附件地址
	var url=rowData.HH;
	if(url == null || url == ""){
		layer.msg("这个回函没有上传文件！");
		return;
	}else{
		// 下载
		common.init("../formValue/fileisexist?url="+url,"POST",function(result){
			if (result.code == 0) {
				layer.msg(result.value);
				return;
			}else{
				if(result.value == 'exist'){
					window.location.href = "../formValue/form_file?url="+url;
				}else{
					layer.msg("该回函文件不存在！");
					return;
				}
			}
		});
		common.do_submit();
	}
}


var problem = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("problem");
		loading.init();
		try{
			//创建用户列表
			problem.createProblemGrid();
			//请求用户列表数据
			problem.requestProblemData();
			//绑定页面事件
			problem.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建列表列集合
	 */
	createProblemColumns : function(){
		grid.resetColumn();
		grid.addColumn("80px","TM","图名");
		grid.addColumn("80px","TH","图号");
		grid.addColumn("80px","HQ","海区");
		grid.addColumn("100px","WTLX","问题类型");
		grid.addColumn("100px","WTMS","问题描述");
		grid.addColumn("250px","WTFJ","问题链接","<a style='color:blue;cursor:pointer;' onclick='downWtfjPage(this)'> #= WTFJ?WTFJ.substring(WTFJ.lastIndexOf('\\\\')+1):''# </a>");
		grid.addColumn("100px","FKR","反馈人");
		grid.addColumn("100px","ZJY","质检");
		grid.addColumn("100px","SDY","审定");
		grid.addColumn("100px","FKRQ","反馈日期");
		grid.addColumn("100px","JSR","接收人");
		grid.addColumn("100px","JSRQ","接受日期");
		grid.addColumn("200px","FH","发函件链接","<a style='color:blue;cursor:pointer;' onclick='downFhPage(this)'> #= FH?FH.substring(FH.lastIndexOf('\\\\')+1):''# </a>");
		grid.addColumn("100px","FHSJ","发函时间");
		grid.addColumn("200px","HH","回函件链接","<a style='color:blue;cursor:pointer;' onclick='downHhPage(this)'> #= HH?HH.substring(HH.lastIndexOf('\\\\')+1):''# </a>");
		grid.addColumn("100px","HHSJ","回函时间");
		grid.addColumn("150px","ZZCLFF","最终处理方法");
		return grid.addColumn("100px","BZ","备注");
	},
	
	/**
	 * 创建用户列表
	 */
	createProblemGrid : function(){
		var columns = problem.createProblemColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestProblemData : function(){
		common.init("../problem/list","POST",problem.bindProblemGrid);
		common.do_submit();
	},
	
	bindProblemGrid : function(result){
		grid.bindData(result);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加用户窗口按钮的click事件
		 */
		$("#importSubmit").on("click",function(){
			var quality = $("#quality").val();
			var approval = $("#approval").val();
			common.add_param("quality",quality);
			common.add_param("approval",approval);
			var param = common.add_param("key","PROBLEM_SUBMIT");
			loading.init();
			common.init("../workflow/publish_problem","POST",function(result){
				if (result.code == 0) {
					layer.msg(result.value);
					return;
				}
				layer.msg("提交成功");
				var val = result.value;
				var processDefId = val.processDefId;
				var procInstId = val.processInstId;
				var taskInstId =  val.taskInstId;
				var taskDefId = val.taskDefId;
				var processDefKey = "PROBLEM_SUBMIT";
				loading.close();
				common.toPage("../taskform/process?from=1&taskDefId="+taskDefId+"&processDefId="+processDefId+"&taskInstId="+taskInstId+"&processInstId="+procInstId+"&processDefKey="+processDefKey);
			});
			common.do_submit(param);
		});
		
	}
}