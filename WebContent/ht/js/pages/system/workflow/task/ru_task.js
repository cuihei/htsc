
$(function() {
	

    var img1="<img  id='uploadImg1' src='../ht/upload/images/uploadpic_s.png' onclick='onclic1()' style='cursor:pointer;width:80px;height:72px;'><img id='myImg1' onclick='onclic1()' style='cursor:pointer;'><input type='file' name='myfiles1' id='myfiles1' onchange='changeImg1()' style='display:none'>"
    var img2="<img id='uploadImg2' src='../ht/upload/images/uploadpic_s.png' onclick='onclic2()' style='cursor:pointer;width:80px;height:72px;'><img id='myImg2' onclick='onclic2()' style='cursor:pointer;'><input type='file' name='myfiles2' id='myfiles2' onchange='changeImg2()' style='display:none'>"
    var img3="<img id='uploadImg3' src='../ht/upload/images/uploadpic_s.png' onclick='onclic3()' style='cursor:pointer;width:80px;height:72px;'><img id='myImg3' onclick='onclic3()' style='cursor:pointer;'><input type='file' name='myfiles3' id='myfiles3' onchange='changeImg3()' style='display:none'>"   
	var remark_r="<div id='div_r'></div>";
	    $("#div1").html(img1);	  $("#div2").html(img2);	  $("#div3").html(img3);$("#div4").html(remark_r);
        $("#errtxt1").css({"padding":"1px","width":"100%","height":"100%","font-size":"12px","font-weight":"normal","color":"#171717"});
		$("#errtxt2").css({"padding":"1px","width":"100%","height":"100%","font-size":"12px","font-weight":"normal","color":"#171717"});
		$("#errtxt3").css({"padding":"1px","width":"100%","height":"100%","font-size":"12px","font-weight":"normal","color":"#171717"});
})

function onclic1(){	$("#myfiles1").click();};
function onclic2(){	$("#myfiles2").click();};
function onclic3(){	$("#myfiles3").click();};
function changeImg1() {for (var i = 0; i < event.target.files.length; i++) {var file = event.target.files.item(i);if (!(/^image\/.*$/i.test(file.type))) {continue;};var freader = new FileReader();freader.readAsDataURL(file);freader.onload = function() {$("#myImg1").css({"width":"80px","height":"72px"});$("#myImg1").attr("src", event.target.result);$("#uploadImg1").attr("style","display:none");}}};
function changeImg2() {for (var i = 0; i < event.target.files.length; i++) {var file = event.target.files.item(i);if (!(/^image\/.*$/i.test(file.type))) {continue;};var freader = new FileReader();freader.readAsDataURL(file);freader.onload = function() {$("#myImg2").css({"width":"80px","height":"72px"});$("#myImg2").attr("src", event.target.result);$("#uploadImg2").attr("style","display:none");}}};
function changeImg3() {for (var i = 0; i < event.target.files.length; i++) {var file = event.target.files.item(i);if (!(/^image\/.*$/i.test(file.type))) {continue;};var freader = new FileReader();freader.readAsDataURL(file);freader.onload = function() {$("#myImg3").css({"width":"80px","height":"72px"});$("#myImg3").attr("src", event.target.result);$("#uploadImg3").attr("style","display:none");}}};



///////////////////////////////////////////////



function plan(obj,isRuTask) {
	var tr = $(obj).parent().parent();
	var rowData = grid.getSelectRowDataByRow(tr);
	var taskDefId = rowData.taskDefId;
	var procInstId = rowData.processInstId;
	var taskInstId = rowData.taskInstId;
	var parentProcessInstId = rowData.parentProcessInstId;
	common.toPage("../formValue/plan?taskDefId="+taskDefId+"&taskInstId="+taskInstId+"&processInstId="+procInstId+"&parentProcessInstId="+parentProcessInstId+"&isRuTask="+isRuTask);
}

var ru_task = {
		
		// 审核流程集合
		processApprove : ["CATALOG_MANAGEMENT","TASK_BOOK","DATA_INPUT","DATA_BORROWING","DATA_RETURN"],
		
		taskDefId:null,
		processDefId:null,
		taskId:null,
		processInstId:null,
		parentProcessInstId:null,
		buttonValueDefault : $("#submitOpinion").text(),
		mapName:null,
		taskName:null,
		/**
		 * 初始化
		 */
		init : function(){
			// 初始化表格对象
			grid.init("dv_ru_task");
			// 显示加载层
			loading.init();
			
			$("#year1").datepicker({
				startView: 2, 
				maxViewMode: 2,
				minViewMode:2,
				format: 'yyyy',
				autoclose: true
			});
			
			$("#year2").datepicker({
				startView: 2, 
				maxViewMode: 2,
				minViewMode:2,
				format: 'yyyy',
				autoclose: true
			});
			
			$("#batchApprove").on("click",function(){
				var rowDatas = grid.getSelectRowsData();
				if (rowDatas.length<=0) {
					layer.msg('未选择任何行!', {icon:5,time:1000});
					return;
				}
				var status = "";
				var flag = "false";
				$.each(rowDatas,function(i,item){
					status  = $("#dv_ru_task").data("kendoGrid").dataItem(item).status;
					if(status == '待分配'){
						flag = "true";
					}
				});
				if(flag == "true"){
					layer.msg('请选择可以审核的任务!', {icon:5,time:1000});
					return false;
				}else{
					$("#myModalBatch").modal('show');
				}
				
			});
			try{
				// 创建表格
				 ru_task.createRuTaskGrid();
				// 发送数据请求绑定表格数据
				ru_task.requestRuTaskData();
			}
			catch(err){
				loading.close();
			}
			$("#year").datepicker({
				startView: 2, 
				maxViewMode: 2,
				minViewMode:2,
				format: 'yyyy',
				autoclose: true
			}).on('changeDate',changeYearMonth);
			function changeYearMonth(){
				var date = $("#year").val();
				loading.init();
				common.init("../workflow/ru_task_list","POST",ru_task.bindRuTaskGrid);
				var processDefKey = $("#processDefKey").val();
				common.add_param("year",date);
				var data = common.add_param("processDefKey",processDefKey);
				common.do_submit(data);
			}
		},
		
		/**
		 * 创建任务表格
		 */
		createRuTaskGrid : function(){
			var taskDef = "# var processSubmitApprove = ['u_task_distribution_worker','u_task_distribution_group','u_task_fenpei_bjy','u_task_fenpei_shending','u_task_distribution_zhijian','u_task_distribution_shending','u_task_plan_distribution_zhijian','u_task_plan_distribution_shending'];" +
			"if($.inArray(taskDefId,processSubmitApprove)<0){#";
			var approveHTML= $('#approve').html();
			approveHTML =  taskDef + approveHTML;
			approveHTML = approveHTML + "#} else{#";
			approveHTML = approveHTML + $('#approveDisable').html()+"# }#";
			try {
				processSubmitApprove= $('#processSubmitApprove').val();
				processSubmitApprove=processSubmitApprove.replace("[","");
				processSubmitApprove=processSubmitApprove.replace("]","");
				var taskDef1 = "# if(processSubmitApprove.indexOf(taskDefId)!=-1){#";
				var operationHTML= $('#operation').html();
				operationHTML =  taskDef1 + operationHTML;
				operationHTML = operationHTML + "#} else{#";
				operationHTML = operationHTML + $('#operationDisable').html()+"# }#";
			} catch (e) {
				console.log(e);
			}
			
			var taskDefPlan = "# var processSubmitPlan = ['u_task_plan_zhijian','u_task_plan_shending'];" +
			"if($.inArray(taskDefId,processSubmitPlan)>=0){#";
			var planHTML= $('#plan').html();
			planHTML =  taskDefPlan + planHTML;
			planHTML = planHTML + "#} else{#";
			planHTML = planHTML + $('#planDisable').html()+"# }#";
			
			grid.resetColumn();
			// 显示业务列
			var columns = $("#columns").val();
			var colArray = JSON.parse(columns);
			for (var int = 0; int < colArray.length; int++) {
				var column = colArray[int];
				if(column.value != '附件名称'){
					grid.addColumn(column.width,column.prop,column.value);
				}else{
					grid.addColumn(column.width,column.prop,column.value,"<a style='color:blue;cursor: pointer;' onclick='ru_task.downPage(this)'>#:"+column.prop+"#</a>");
				}
			}
			// 流程列
			var processDefKey = $("#processDefKey").val();
			grid.addColumn("100px","processDefName","任务类型");
			grid.addColumn("150px","remark","备注");
			grid.addColumn("200px","taskName","当前任务的节点");
			if(		processDefKey.indexOf("DATA_BORROWING") != -1 || processDefKey.indexOf("DATA_RETURN") != -1  || processDefKey.indexOf("DATA_INPUT") != -1 
			){
			grid.addColumn("100px","createTime","发布时间","#= createTime ? kendo.toString(new Date(createTime), 'yyyy-MM-dd HH:mm:ss') : '' #" );
			}else{
			grid.addColumn("100px","issueTime","发布时间","#= issueTime ? kendo.toString(new Date(issueTime), 'yyyy-MM-dd HH:mm:ss') : '' #" );
			};
			if($.inArray(processDefKey,ru_task.processApprove)>-1){
				grid.addColumn("80px","operation","查看",kendo.template($("#operation").html()));
				var columns = grid.addColumn("80px","approve","审核",kendo.template($("#approve").html()),null,null,false);
			}
			else{
				grid.addColumn("80px","flow","流转",kendo.template($("#flow").html()));
				if(processDefKey == 'PROBLEM_SUBMIT'){
					var columns =grid.addColumn("180px","operation","流程操作",kendo.template(operationHTML),null,null,false);
				}else{
					grid.addColumn("100px","plan","编绘计划问题",kendo.template(planHTML),null,null,false);
					grid.addColumn("180px","operation","流程操作",kendo.template(operationHTML),null,null,false);
					var columns = grid.addColumn("100px","approve","任务处理",kendo.template(approveHTML),null,null,false);
				}
			}
// grid.addColumn("18%","suspendState","流程状态","#= suspendState == true ? '挂起':
// '激活' #" );
// var columns =
// grid.addColumn("15%","control","流程操作",kendo.template($("#control").html()));
			grid.createGrid(columns);
		},
		
		/**
		 * 发送获取任务列表请求
		 */
		requestRuTaskData : function(){
			common.init("../workflow/ru_task_list","POST",ru_task.bindRuTaskGrid);
			var processDefKey = $("#processDefKey").val();
			var date = $("#year").val();
			common.add_param("year",date);
			var data = common.add_param("processDefKey",processDefKey);
			common.do_submit(data);
		},
		
		/**
		 * 绑定任务数据
		 */
		bindRuTaskGrid : function(result){
			// 綁定数据
			grid.bindData(result);
			// 提示加载成功信息
			layer.msg(msg.LOAD_SUCCESS);
		},
		
		/** 绑定表单按钮的click事件 */
		operation : function(obj){
			var tr = $(obj).parent().parent();
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			var processDefId = rowData.processDefId;
			var procInstId = rowData.processInstId;
			var taskInstId = rowData.taskInstId;
			var taskDefId = rowData.taskDefId;
			var parentProcessInstId =rowData.parentProcessInstId;
			var processDefKey = $("#processDefKey").val();
			var from = $("#from").val();
			common.toPage("../taskform/process?taskDefId="+taskDefId+"&processDefId="+processDefId+"&taskInstId="+taskInstId+"&processInstId="+procInstId+"&from="+from+"&processDefKey="+processDefKey+"&parentProcessInstId="+parentProcessInstId);
		},
		
		downPage :function(obj) {
			var tr = $(obj).parent().parent();
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			// 获取附件地址
			var enclosure=rowData.enclosure;
			if(enclosure == null || enclosure == ""){
				layer.msg("这个任务书没有上传文件！");
				return;
			}else{
				// 下载
				window.location.href = "../formValue/form_file?url="+enclosure;
			}
		},
		
		/** 绑定表单查看的click事件 */
		formValue : function(obj){
			var tr = $(obj).parent().parent();
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			var processDefId = rowData.processDefId;
			var procInstId = rowData.processInstId;
			var taskInstId = rowData.taskInstId;
			var taskDefId = rowData.taskDefId;
			var param = "processDefId="+processDefId+"&taskDefId="+taskDefId+"&taskInstId="+taskInstId+"&processInstId="+procInstId;
			common.toPage("../formValue/process_forms_init?"+param);
		},
		
		plan : function(obj){
			var tr = $(obj).parent().parent();
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			var taskDefId = rowData.taskDefId;
			var procInstId = rowData.processInstId;
			var taskInstId = rowData.taskInstId;
			var parentProcessInstId = rowData.parentProcessInstId;
			common.toPage("../formValue/plan?taskDefId="+taskDefId+"&taskInstId="+taskInstId+"&processInstId="+procInstId+"&parentProcessInstId="+parentProcessInstId);
		},
		
		catalogDetail : function(obj){
			var tr = $(obj).parent().parent();
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			var processDefKey = $("#processDefKey").val();
			if(processDefKey == "CATALOG_MANAGEMENT"){
				var id = rowData.id;
				var categoryId = rowData.type.id;
				common.toPage("../detail/init_edit?flag=1&id="+id+"&categoryId="+categoryId);
			}else{
				var type = rowData.type;
				var id = rowData.id;
				if(type == "图书资料"){
					common.toPage("../bookinfo/bookfile_init?mark=1&id="+id);
				}else if(type == "海图资料"){
					common.toPage("../books/booksfile_init?mark=1&id="+id);
				}else if(type == "外业汇交资料"){
					// 外业汇交资料标识
					var flag = "filedData";
					common.toPage("../filedData/file_init?mark=1&id="+id+"&flag="+flag);
				}
			}
		},
		
		/**
		 * 导出经历簿
		 */
		exportBook : function(obj){
			var tr = $(obj).parent().parent();
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			//当前节点名称
			var taskName=rowData.taskName;
			var parentProcessInstId = rowData.parentProcessInstId;
			var processInstId = rowData.processInstId;
			var processDefKey = rowData.processDefId.split(":")[0];
			var param = "parentProcessInstId="+parentProcessInstId+"&processDefKey="+processDefKey+"&processInstId="+processInstId;
			window.location.href = "../workflow/export_book?"+param;
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
		
		/**
		 * 审批
		 */
		approve : function(obj){
			
			
			var tr = $(obj).parent().parent();
			var rowData = grid.getSelectRowDataByRow(tr);
			var taskId = rowData.taskInstId;
			var processInstId = rowData.processInstId;
			var parentProcessInstId = rowData.parentProcessInstId;
			var taskDefId = rowData.taskDefId;
			var processDefId = rowData.processDefId;
			var suspendState = rowData.suspendState;
			var mapName = rowData.mapName;
		    var taskName=rowData.taskName;
			
			ru_task.taskDefId = taskDefId;
			ru_task.processDefId = processDefId;
			ru_task.taskId = taskId;
			ru_task.processInstId = processInstId;
			ru_task.parentProcessInstId = parentProcessInstId;
			ru_task.mapName = mapName;
			ru_task.taskName = taskName;

				   //给改正项号绑定事件  限制只允许输入数字
				　$('#opinion1').attr("onchange","var patrn = /^[0-9]*(\.[0-9]{1,2})?$/; if (!patrn.test(this.value) || this.value == '') { layer.alert('改正项号只允许为数字，请返回重写！'); this.value = ''; }");
			
			if (suspendState == true) {
				layer.msg("当前流程处于冻结状态,无法进行提交"); 
				
				return;
			}
			// 显示意见栏
			common.init("../workflow/task_outflow_count","POST",function(result){
				if (result.code == 0) {
					return;
				}

				var curRoleId=$("#curRoleId").val();
				// 是否存在改正通告
				var hasNotice = result.value.notice;


			if (hasNotice == "true") {
			
			    	$(".modal-content-r").attr("style","display:none");
					$(".modal-content").attr("style","width:450px;margin:0 auto");
					$("#dv_notice1").show();
					$("#dv_notice2").show();
					$(".input-group").show();
					$("#back").css("float","right");
					$("#back").css("margin-right","10px");
				 }else if(curRoleId=="true"&&taskName.indexOf("最小比例尺")<0&&taskName.indexOf("质检员填写")<0&& taskName.indexOf("审定员填写")<0){ 
			//alert(taskName.indexOf("质检员填写")<0)
				$(".modal-content-r").show();
				 $(".modal-content-r").attr("style","width:450px; height:500px;margin:-50px auto;background-color:#fff;");
		    	 $(".modal-content").attr("style","display:none");
                  $("#back").insertAfter("#div_r");
 				  $("#backOpinion").insertAfter("#div_r");
				  $("#submitOpinion").insertAfter("#div_r");
				  $("#remark").insertAfter("#div_r");
				  $('#back').css("float", "right");
				  $("#back").css("margin-right","10px");
				 
	
				}else{
			
					$(".modal-content").show();
                    $(".modal-content-r").attr("style","display:none");
                    $("#back").insertAfter("#dv_remark");
 				    $("#backOpinion").insertAfter("#dv_remark");
				    $("#submitOpinion").insertAfter("#dv_remark");
					$("#remark").insertAfter("#dv_remark");
					
					$(".modal-content").attr("style","width:360px");

					$("#dv_notice1").hide();
					$("#dv_notice2").hide();
					$(".input-group").hide();
					$('#back').css("float", "right");
					$("#back").css("margin-right","10px");
				}

				var count = result.value.buttonCount;
				// 如果下一步没有退回 隐藏退回按钮
				if (count > 1) {
					$("#backOpinion").show();					
				}
				else{
					$("#backOpinion").hide();
				}
				// 提交按钮显示值
				var buttonValue = result.value.buttonValue;
				if (buttonValue != null) {
					$("#submitOpinion").text(buttonValue);

				}
				else{
					$("#submitOpinion").text(ru_task.buttonValueDefault);
				}
				
				$("#opinion").val("");
				$("#myModal").modal('show');
				$("#opinion1").val("");// /2018.4.24 清空弹窗 项号的值
				$("#opinion2").val("");// /2018.4.24 清空弹窗 项号的值
			});
			common.reset_param();
			common.add_param("processDefId",processDefId);
			var data = common.add_param("taskDefId",taskDefId);
			common.do_submit(data);
			$("#opinion1").val("");// /2018.4.24 清空弹窗 项号的值
			$("#opinion2").val("");// /2018.4.24 清空弹窗 项号的值

		
		},
		
		/**
		 * 委托
		 */
		delegate : function(obj){
			var tr = $(obj).parent().parent();
			var rowData = grid.getSelectRowDataByRow(tr);
			var processInstId = rowData.processInstId;
			var taskId = rowData.taskInstId;
			var suspendState = rowData.suspendState;
			if (suspendState == true) {
				layer.msg("当前流程处于冻结状态,无法进行委托操作");
				return;
			}
			/** 绑定意见提交按钮的click事件 */
			$("#delegate").on("click",function(){
				layer.confirm("确定进行委托操作吗？",function(){
					common.init("../workflow/delegate","POST",function(result){
						if (result.code == 0) {
							layer.msg(result.value);
							return;
						}
						layer.msg("委托成功！");
						ru_task.requestRuTaskData();
						$("#delegateCancel").click();
					});
					var userId = $("#delegateUser").val();
					common.reset_param();
					common.add_param("taskId",taskId);
					common.add_param("processInstId",processInstId);
					var data = common.add_param("userId",userId);
					common.do_submit(data);
				});
			});
			$("#myModalDelegateUser").modal('show');
		},
		
		/**
		 * 挂起
		 */
		suspend : function(obj){
			var tr = $(obj).parent().parent();
			var rowData = grid.getSelectRowDataByRow(tr);
			var processInstId = rowData.processInstId;
			var suspendState = rowData.suspendState;
			if (suspendState == true) {
				layer.msg("当前流程已挂起");
				return;
			}
			/** 绑定原因提交按钮的click事件 */
			$("#suspendOrActivate").on("click",function(){
				ru_task.suspendOrActivate(processInstId,true);
			});
			$("#reason").val("");
			$("#myModalSuspendReason").modal('show');
		},
		
		/**
		 * 激活
		 */
		activate : function(obj){
			var tr = $(obj).parent().parent();
			var rowData = grid.getSelectRowDataByRow(tr);
			var processInstId = rowData.processInstId;
			var suspendState = rowData.suspendState;
			if (suspendState == false) {
				layer.msg("当前流程已经是激活状态");  
				return;
			}
			/** 绑定原因提交按钮的click事件 */
			$("#suspendOrActivate").on("click",function(){
				ru_task.suspendOrActivate(processInstId,false);
				
			});
			$("#reason").val("");
			$("#myModalSuspendReason").modal('show');
		},
		
		
		

		/** 绑定意见退回按钮的click事件 */
		backOpinion : function(){
			ru_task.performTask(ru_task.taskDefId,ru_task.processDefId,ru_task.taskId,ru_task.processInstId,ru_task.parentProcessInstId,"0",ru_task.mapName,ru_task.taskName);
		},
		
	
		
		 submitOpinion1 : function(){
						////判断改正项号
						var opinion1=$("#opinion1").val();
						var year1=$("#year1").val();
						if(opinion1!=""||opinion1!=null){
			            $.ajax({ 
			            type:'POST',
						url:'../workflow/task_notice',
						dataType:'json',
						async:false,
						data:{'taskDefId':ru_task.taskDefId, 'processInstId':ru_task.processInstId,'opinion1':$("#opinion1").val(), 'year1':$("#year1").val(), },  	 
						 success: function(result){
			              if (result.code == 0) {
			                        ///////////////////
			                        //询问框
			                layer.confirm(result.value, {
			                    btn: ['确认','返回'] //按钮
			                 }, function(){
			                        ru_task.submitOpinion1;
			                      }, function(){ return; });
			                        /////////////////////
			                        }else{
			                        ru_task.submitOpinion1;
			                        }}
			            });           
			            }
			 },   
		
		/** 绑定意见提交按钮的click事件 */
		submitOpinion : function(){
				ru_task.performTask(ru_task.taskDefId,ru_task.processDefId,ru_task.taskId,ru_task.processInstId,ru_task.parentProcessInstId,"1",ru_task.mapName,ru_task.taskName)
		},
		
performTask : function(taskDefId,processDefId,taskId,processInstId,parentProcessInstId,agreeValue,mapName,taskName){
      loading.init();
			common.reset_param();
			common.add_param("myfiles1",$("#myfiles1").val());
			common.add_param("errtxt1",$("#errtxt1").val());
			common.add_param("myfiles2",$("#myfiles2").val());
			common.add_param("errtxt2",$("#errtxt2").val());
			common.add_param("myfiles3",$("#myfiles3").val());
			common.add_param("errtxt3",$("#errtxt3").val());
			
			common.add_param("taskId",taskId);
			common.add_param("processInstId",processInstId);
			common.add_param("taskDefId",taskDefId);
			common.add_param("processDefId",processDefId);
			common.add_param("parentProcessInstId",parentProcessInstId);
			if (agreeValue != null) {	common.add_param("agreeValue",agreeValue);	}
			common.add_param("remark",$("#remark").val());
			common.add_param("mapName",mapName);
			common.add_param("taskName",taskName);
			
			var advice = "";		if($("#dv_notice1").is(":hidden")){	advice = "";	}
			common.add_param("year1",$("#year1").val());
			common.add_param("opinion1",$("#opinion1").val());
			common.add_param("year2",$("#year2").val());
			var data = common.add_param("opinion2",$("#opinion2").val());
	        common.init("../workflow/task_perform","POST",function(result){
				var strvalue=result.value;
			
			/*	　layer.msg('操作成功！',{icon: 6});
				layer.alert(strvalue,9);*/
				
			
				if (result.code == "0" &&strvalue.indexOf("先前项号")>0  ) {
			 	var strprice = parseInt(strvalue.replace(/[^0-9]/ig,""));//截取改正通告数字
					 layer.confirm('<font style="color:red">填写的项号已小于先前项号，先前项号<strong>'+strprice+'</strong>!<br/>【提交】  确认继续提交<br/>【取消】  返回重新修改项号</font>',  {
						  btn: ['提交','取消'] //按钮
						}, function(){
                            ////////////////////////
							   common.init("../workflow/task_perform1","POST",function(){
									layer.msg("提交任务成功！");
									$("#remark").val("");
									ru_task.requestRuTaskData();
									$("#back").click();
									loading.close();
								});
							   
								common.do_submit(data);
							//////////////////////////						
					
						}, function(){
							return;
						});
				
					return;
					
					
				} else	if (result.code == "0"){
				
				layer.alert(strvalue);
				
					 return;

								
				}		
			
		    
				layer.msg("提交任务成功！");
				$("#remark").val("");
				ru_task.requestRuTaskData();
			    $("#back").click();		    
			    loading.close();
	        
			});
	     	common.do_submit(data);
		},
		

	
		/**
		 * 激活/挂起
		 * 
		 * @processInstId 流程实例ID
		 * @advice 挂起/激活原因
		 */
		suspendOrActivate : function(processInstId,isSuspend){
			layer.confirm("确定要执行此操作吗？",function(){
				var _func = function(data){
					if (data.code == 0) {
						layer.msg(data.value);
						return;
					}
					var msg = isSuspend == true ? "挂起成功！" : "激活成功！";
					layer.msg(msg);
					$("#suspendOrActivateCancel").click();
					ru_task.requestRuTaskData();
				}
				if (isSuspend == true) {
					common.init("../workflow/suspend","POST",_func);
				}else{
					common.init("../workflow/activate","POST",_func);
				}
				var advice = $("#reason").val();
				common.add_param("processInstId",processInstId);
				var data = common.add_param("advice",advice);
				common.do_submit(data);
			});
		},
		
		/**
		 * 批量通过
		 */
		submitBatchOpinion : function(){
			loading.init();
			common.reset_param();
			var rowDatas = grid.getSelectRowsData();
			if (rowDatas.length<=0) {
				layer.msg('未选择任何行!', {icon:5,time:1000});
				return;
			}
			var taskIds = "";
			var processInstIds = "";
			var taskDefIds = "";
			var processDefIds = "";
			var parentProcessInstIds = "";
			var mapName = "";
			var taskName="";
			
			$.each(rowDatas,function(i,item){
				taskIds += ','+ $("#dv_ru_task").data("kendoGrid").dataItem(item).taskInstId;
				processInstIds += ',' + $("#dv_ru_task").data("kendoGrid").dataItem(item).processInstId;
				taskDefIds += ',' + $("#dv_ru_task").data("kendoGrid").dataItem(item).taskDefId;
				processDefIds += ',' + $("#dv_ru_task").data("kendoGrid").dataItem(item).processDefId;
				var parentProcessInstId = $("#dv_ru_task").data("kendoGrid").dataItem(item).parentProcessInstId
				parentProcessInstIds += ',' +parentProcessInstId;
				mapName += ',' + $("#dv_ru_task").data("kendoGrid").dataItem(item).mapName;
				taskName += ',' + $("#dv_ru_task").data("kendoGrid").dataItem(item).taskName;
			});
			taskIds = taskIds.substring(1);
			processInstIds = processInstIds.substring(1);
			taskDefIds = taskDefIds.substring(1);
			processDefIds = processDefIds.substring(1);
			parentProcessInstIds = parentProcessInstIds.substring(1);
			taskName = taskName.substring(1);
			mapName = mapName.substring(1);
			common.add_param("taskId",taskIds);
			common.add_param("processInstId",processInstIds);
			common.add_param("taskDefId",taskDefIds);
			common.add_param("processDefId",processDefIds);
			common.add_param("parentProcessInstId",parentProcessInstIds);
			common.add_param("mapName",mapName);
			common.add_param("taskName",taskName);
			var data = common.add_param("agreeValue","1");
			common.init("../workflow/task_batchperform","POST",function(result){
				if (result.code == 0) {
					layer.msg(result.value);
					loading.close();
					return;
				}
				layer.msg("提交任务成功！");
				ru_task.requestRuTaskData();
				$("#backBatch").click();
				loading.close();
			});
			common.do_submit(data);
		},
		
		/**
		 * 批量退回
		 */
		backBatchOpinion : function(){
			loading.init();
			common.reset_param();
			var rowDatas = grid.getSelectRowsData();
			if (rowDatas.length<=0) {
				layer.msg('未选择任何行!', {icon:5,time:1000});
				return;
			}
			var taskIds = "";
			var processInstIds = "";
			var taskDefIds = "";
			var processDefIds = "";
			var parentProcessInstIds = "";
			var mapName = "";
			var taskName = "";
			
			$.each(rowDatas,function(i,item){
				taskIds += ','+ $("#dv_ru_task").data("kendoGrid").dataItem(item).taskInstId;
				processInstIds += ',' + $("#dv_ru_task").data("kendoGrid").dataItem(item).processInstId;
				taskDefIds += ',' + $("#dv_ru_task").data("kendoGrid").dataItem(item).taskDefId;
				processDefIds += ',' + $("#dv_ru_task").data("kendoGrid").dataItem(item).processDefId;
				var parentProcessInstId = $("#dv_ru_task").data("kendoGrid").dataItem(item).parentProcessInstId
				parentProcessInstIds += ',' +parentProcessInstId;
				mapName += ',' + $("#dv_ru_task").data("kendoGrid").dataItem(item).mapName;
				taskName += ',' + $("#dv_ru_task").data("kendoGrid").dataItem(item).taskName;
			});
			taskIds = taskIds.substring(1);
			processInstIds = processInstIds.substring(1);
			taskDefIds = taskDefIds.substring(1);
			processDefIds = processDefIds.substring(1);
			parentProcessInstIds = parentProcessInstIds.substring(1);
			mapName = mapName.substring(1);
			taskName = taskName.substring(1);
			common.add_param("taskId",taskIds);
			common.add_param("processInstId",processInstIds);
			common.add_param("taskDefId",taskDefIds);
			common.add_param("processDefId",processDefIds);
			common.add_param("parentProcessInstId",parentProcessInstIds);
			common.add_param("mapName",mapName);
			common.add_param("taskName",taskName);
			var data = common.add_param("agreeValue","0");
			common.init("../workflow/task_batchperform","POST",function(result){
				if (result.code == 0) {
					layer.msg(result.value);
					loading.close();
					return;
				}
				layer.msg("退回任务成功！");
				ru_task.requestRuTaskData();
				$("#backBatch").click();
				loading.close();
			});
			common.do_submit(data);
		}
}