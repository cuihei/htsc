$(function(){
	var id=$("#taskBookId").val();
	tbedit.init();
	if(null!=id&&""!=id){
		taskShow.editInit();
	}
   
});
var tbedit={
		/**
		 * 初始化操作 1.将提交按钮改为保存按钮 2.删除日期框的图标 3.绑定按钮事件
		 */
		init:function(){
			$("#submit").val("保存");
			$("#submit").text("保存");
			$("#executeTime").prev().children().remove();
			$("#endTime").prev().children().remove();
			$("#taskSelect").append("<option value="+""+">"+"请选择"+"</option>");
			$("#taskSelect").append("<option value="+"year"+">"+"指令性任务"+"</option>");
			$("#taskSelect").append("<option value="+"month"+">"+"月度任务"+"</option>");
			/*$("#taskSelect").append("<option value="+"temp"+">"+"临时性任务"+"</option>");*/
			var id=$("#taskBookId").val();
			tbedit.bindPageEvent();
			$("#typeId").attr("disabled","disabled");  
			// 初始化多选控件
			tbedit.initMultiselect();
			tbedit.setMultiSeleteData();
		},
		
		/**
		 * 初始化多选控件
		 */
		initMultiselect : function(){
			// 图层多选
			$("#executeDeptId").kendoMultiSelect({
		        placeholder: "请选择执行部门",
		        dataTextField: "orgName",
		        dataValueField: "id",
		    });
		},
		
		/**
		 * 设置多选框的数据，并刷新
		 */
		setMultiSeleteData : function(){
			var deptId = $("#deptId").val();
			groupIds = [];
			for (var int = 0; int < deptId.split(",").length; int++) {
				groupIds.push(deptId.split(",")[int]);
			}
			var multiselect = $("#executeDeptId").data("kendoMultiSelect");
			multiselect.value(groupIds);
			multiselect.refresh();
		},
		
		/**
		 * 绑定页面事件
		 */
		bindPageEvent : function(){
			// 绑定保存事件
			$("#submit").on("click",function(){
				// 计划信息
				var datas = common.get_all_row_data($("#taskShow"));
				var planId="";
				var revision = "";
				for (var int = 0; int < datas.length; int++) {
					if(datas[int].id!=null){
						if(planId!=""){
							planId=planId+","+datas[int].id;
							if(datas[int].revision!=null){
								revision=revision+","+datas[int].revision;
							}else{
								revision=revision+","+"1";
							}
						}else{
							planId=datas[int].id;
							if(datas[int].revision!=null){
								revision=datas[int].revision;
							}else{
								revision="1";
							}
						}
					}
				}
				//选择执行部门
				groupIds = "";
				groupNames = "";
				var dataItem = $("#executeDeptId").data("kendoMultiSelect").dataItems();
				$.each(dataItem,function(i,item){
					groupIds += ','+item.id;
					groupNames += ','+item.orgName;
					//groupIds.push(item.id);
					//groupNames.push(item.orgName);
				});
				var taskbookName=$("#taskbookName").val();
				var taskFrom=$("#taskFrom").val();
				var executeDeptId=groupIds.substring(1);
				var executeTime=$("#executeTime").val();
				var endTime=$("#endTime").val();
				var technologyStandard=$("#technologyStandard").val();
				var technologyDemand=$("#technologyDemand").val();
				var otherDemand=$("#otherDemand").val();
				var taskbookNo=$("#taskbookNo").text();
				var taskBookId=$("#taskBookId").val();
				var taskBookType=$("#taskBookType").val();
				var currentItemNo=$("#currentItemNo").val();
				var projectName=$("#projectName").val();
				var noticeNo=$("#noticeNo").val();
				var correctNo=$("#correctNo").val();
				var correctItemNo=$("#correctItemNo").val();
				var correctNoticeNo=$("#correctNoticeNo").val();
				var itemName=$("#itemName").val();
				var processInstId = $("#processInstId").val();
				var taskId = $("#taskId").val();
				if(planId!=null&&planId!=""){
				if(taskbookName!=null&&taskbookName!=""){
					if(taskFrom!=null&&taskFrom!=""){
						if(executeDeptId!=null&&executeDeptId!=""){
							if(executeTime!=null&&executeTime!=""){
								$.ajax({
									type :"POST",
									url :"../yeartaskbook/edit",
									dataType : "json",
									data : {
										"taskBook.taskbookName":taskbookName,
										"taskBook.taskFrom":taskFrom,
										"taskBook.executeDeptId":executeDeptId,
										"taskBook.technologyStandard":technologyStandard,
										"taskBook.executeTime":executeTime,
										"taskBook.endTime":endTime,
										"taskBook.technologyDemand":technologyDemand,
										"taskBook.otherDemand":otherDemand,
										"taskBook.taskbookNo":taskbookNo,
										"taskBook.id":taskBookId,
										"taskBook.taskBookType":taskBookType,
										"taskBook.currentItemNo":currentItemNo,
										"taskBook.projectName":projectName,
										"taskBook.noticeNo":noticeNo,
										"taskBook.correctItemNo":correctItemNo,
										"taskBook.correctNo":correctNo,
										"taskBook.correctNoticeNo":correctNoticeNo,
										"taskBook.itemName":itemName,
										"planId":planId,
										"revision":revision,
										"processInstId" : processInstId,
										taskId : taskId
									},
									success : function(data){
										if(data.code == 0){
											layer.msg("保存失败,原因为："+data.value);
										}
										else{
											layer.msg("保存成功");
										}
									},
									error : function(data){
										layer.msg("保存失败");
									}
								});
							}else{
								layer.msg("请选择执行日期");
							}
						}else{
							layer.msg("请选择执行部门");
						}
					}else{
						layer.msg("任务来源");
					}
				}else{
					layer.msg("任务名称不可为空");
				}
				}else{
					layer.msg("请选择任务");
				}
			});
			// 绑定返回事件
			$("#backPage").unbind("click").click(function(){
				history.go(-1);
			});
			$("#taskFrom").unbind("blur").blur(function(){
				if(tbedit.isHyh()){
					$("#taskbookNo").text($("#hyhNo").val());
				}else{
					$("#taskbookNo").text($("#hylNo").val());
				}
			});
			$("#chooseTask").unbind("click").click(function(){
				taskDiv.init();
				$("#taskSelect").val("");
				$("#taskDiv").empty();
				$("#myModal").show();
			});
		},
		isHyh:function(){
			var str=$("#taskFrom").val();
			if(str.indexOf("东保")>=0||str.indexOf("中心")>=0||str.indexOf("部局")>=0){
				return true;
			}else{
				return false;
			}
		}	
}

var taskDiv={
		/**
		 * 初始化
		 */
		init : function(){
			grid.init("taskDiv");
			loading.init();
			try{
				taskDiv.createtaskDivGrid();
				// taskDiv.requesttaskDivData();
				taskDiv.bindPageEvent();
				var grid1 = $("#taskDiv").data("kendoGrid");
				grid1.setOptions({
					pageable:false,
				});
			}catch(err){
				loading.close();
			}
		},
		/**
		 * 构建改正通知公告表格。
		 */
		createtaskDivColumns : function(){
			grid.resetColumn();
			grid.addColumn("300px","areaName","海区");
			grid.addColumn("150px","mapNo","图号");
			grid.addColumn("150px","mapName","图名");
			grid.addColumn("150px","scale","比例尺 1:");
			grid.addColumn("150px","type","类别");
			grid.addColumn("150px","workload","测绘工作量");
			grid.addColumn("150px","printQuantity","印刷数量");
			grid.addColumn("150px","deliverTime","资料汇交时间");
			grid.addColumn("150px","measurementPeriod","基测周期");
			return grid.addColumn("150px","discription","说明");
		},
		/**
		 * 创建改正通知公告列表
		 */
		createtaskDivGrid : function(){
			var columns = taskDiv.createtaskDivColumns();
			grid.createGrid(columns);
			
		},
		/**
		 * 发送数据请求
		 */
		requesttaskDivData : function(){
			common.init("../yeartaskbook/list","POST",taskDiv.bindtaskDivGrid);
			common.do_submit();
		},
		/**
		 * 接收服务器响应数据,绑定表格 这是一个回调函数，不用手动调用
		 */
		bindtaskDivGrid : function(result){
			var data = result.value;
			if(data != null){
				for(var i=0;i<data.length;i++){
					// 区域判断
					var area = data[i].area;
					if(area != null &&　area.areaName != null){
						data[i]["areaName"] = area.areaName;
					}
				}
			}
			grid.bindData(result);
			// 设置高度
			var grid1 = $("#taskDiv").data("kendoGrid");
			var height =$(document.body).height()-($("#otherDemand").height()*2);
			grid1.setOptions({
				height:height,
				pageable:false,
			});
		},
		/**
		 * 绑定页面事件
		 */
		bindPageEvent : function(){
			$("#addTaskBook").click(function(){
				common.toPage("../yeartaskbook/edit_page");
			});
			$("#taskSelect").unbind("change").change(function(){
				$("#taskDiv").empty();
				taskDiv.init();
				loading.init();
				try{
					var option=$("#taskSelect").val();
					var url="";
					if(option=="year"){
						var url="../plan/list?type=1&categoryId=10250957411500057";	
					}else if(option=="month"){
						var url="../plan/list?type=2&categoryId=10250957569190070";	
					}else if(option=="temp"){
						var url="../plan/list?type=3&categoryId=10250958275470083";
					}
					common.init(url,"POST",taskDiv.bindtaskDivGrid);
					common.do_submit();
				}catch(err){
					loading.close();
				}
			});
			$("#taskSubmit").unbind("click").click(function(){
				var tr = grid.getSelectRowsData();
				var dataObj = taskDiv.getPlan(tr);
				if(dataObj.length == 0){
					layer.msg("请选择任务");
					return false;
				}else{
					var dataSource = new kendo.data.DataSource({
						  data: dataObj
					});
					taskShow.init();
					$("#taskShow").data("kendoGrid").setDataSource(dataSource);
					$("#myModal").hide();
				}
			});
			$("#hiddenModal").unbind("click").click(function(){
				$("#myModal").hide();
			});
			$("#closeModel").on("click",function(){
				$("#myModal").hide();
			});
			loading.close();	
		},
		
		getPlan : function(rows){
			var datas=[];
			for ( var int = 0; int < rows.length; int++) {
				datas.push(grid.getSelectRowDataByRow(rows[int]));
			}
			if(rows.length>0){
				if(rows.length==1){
					var mapName = datas[0].mapName;
					$("#taskbookName").val(mapName+"编绘任务书");
				}else{
					var mapName = datas[0].mapName;
					$("#taskbookName").val(mapName+"等"+rows.length+"幅图的编绘任务书");
				}
			}
			
			var dataObj=[];
			for ( var int = 0; int < datas.length; int++) {
				dataObj.push({id:datas[int].id,revision:datas[int].revision,mapNo:datas[int].mapNo,mapName:datas[int].mapName,scale:datas[int].scale});
			}
			
			return dataObj;
		}
}

var taskShow={
	/**
	 * 初始化
	 */
		init:function(){
			grid.init("taskShow");
			taskShow.createtaskShowColumns();
			var columns = taskShow.createtaskShowColumns();
			grid.createGrid(columns);
			var grid1 = $("#taskShow").data("kendoGrid");
			grid1.setOptions({
				pageable:false,
			});
		},
		/**
		 * 当进入编辑页面时，初始化;
		 */
		editInit:function(){
			grid.init("taskShow");
			taskShow.createtaskShowColumns();
			var columns = taskShow.createtaskShowColumns();
			grid.createGrid(columns);
			var taskBookId=$("#taskBookId").val();
			var data={
					"taskBookId":taskBookId
			}
			taskShow.requesttaskShowData(data);
			var grid1 = $("#taskShow").data("kendoGrid");
			grid1.setOptions({
				pageable:false,
			});
		},
	/**
	 * 创建表格
	 */
		createtaskShowColumns : function(){
			grid.resetColumn();
			grid.addColumn("150px","mapName","图名");
			grid.addColumn("150px","mapNo","图号");
			grid.addColumn("300px","scale","比例尺 1:");
			grid.addColumn("100px","revision","版次");
			return	grid.addColumn("100px","edit","修改",kendo.template("<a style='cursor:pointer;color:black' onclick='taskShow.revisionEdit.call(this)'>修改</a>"));
		},
		/**
		 * 发送数据请求
		 */
		requesttaskShowData : function(data){
			common.init("../yeartaskbook/planlist","POST",taskShow.bindtaskShowGrid);
			common.do_submit(data);
		},
		/**
		 * 接收服务器响应数据,绑定表格 这是一个回调函数，不用手动调用
		 */
		bindtaskShowGrid : function(result){
			grid.bindData(result);
			// 设置高度
			var grid1 = $("#taskShow").data("kendoGrid");
			var height =$(document.body).height()-($("#otherDemand").height()*2);
			grid1.setOptions({
				height:height,
				pageable:false,
			});
		},
		
		revisionEdit : function(obj){
			var td = $(this).closest("tr").find("td:eq(5)");
			var gridInst = grid.getGrid().data("kendoGrid");
			if (!this.modify) {
				this.modify = true;
				$(this).text("保存");
	    		gridInst.editCell(td);
	    		gridInst.table.off("click", "tr");
			} else {
				this.modify = false;
				$(this).text("修改");
				gridInst.saveChanges();
				gridInst.closeCell();
			}
		}
}
