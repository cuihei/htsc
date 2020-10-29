$(function() {
	$("#opinion").attr("placeholder","若满分，请在此处填写：查无问题！")　
	//存在问题及处理意见 失去焦点触发 自动选择
	$("#opinion").blur(function(){
      var opvalue=$("#opinion").val();
  
   
        if(opvalue.indexOf("查无问")>-1||opvalue.indexOf("无问题")>-1) {
      	    $("#defecttype").kendoDropDownList({optionLabel : "查无问题"});
			$("#defectitem").kendoDropDownList({optionLabel : "查无问题"
				});
    		$("#number").val("0");
    		$("#score").val("0");
    		$("#remarks").val("无");
    	}else if(opvalue==null || opvalue=="" || opvalue.length == 0||opvalue==undefined){    	
    		
    		$("#number").val(""); 
    		$("#score").val("");
    		$("#remarks").val("");
    		defect_form.init();
	
			$("#defectitem").kendoDropDownList({optionLabel : "请选择......"});
			
    	}
    }) 
	
	

	var coefficient = $("#cff").val();
	/*if(coefficient == null || coefficient == ""){
		layer.open({
			  title: '提示'
			  ,content: '该类型图号下调整系数不存在，请联系管理员！'
			  ,yes: function(index, layero){
				  window.history.back();
			  }
			  ,cancel: function(){ 
				  window.history.back();
			  }
		});
	}*/
	$("#coefficient").val(coefficient);
	$("#splitter").kendoSplitter({
		orientation : "horizontal",
		panes : [ {
			size : "40%"
		}, {} ]
	});
	$("#right").kendoSplitter({
		orientation : "vertical",
		panes : [ {
			size : "75%"
		}, {} ]
	});
	//累计扣分
	aggregate = 0;
	//实际扣分
	point = 0;
	defect_form.init();
})

/** 绑定删除按钮的click事件*/
function removePage(obj) {
	var tr = $(obj).parent().parent();
	defect_form.remove(tr);
}

var defect_form = {
	/**
	 * 初始化
	 */
	init : function() {
		grid.init("bacthAdd");
		loading.init();
		try {
			defect_form.createGrid();
			defect_form.requestData();
			defect_form.bindPageEvent();
			defect_form.initMultiselect();
			$("#number").keyup(function(){
				var value = $("#number").val();
				if(isNaN(Number(value))){
					layer.msg("不能输入非数字，请重新输入！");
					$("#number").val(1);
				}else{
					if(Number(value)<0||Number(value)>100){
						layer.msg("请输入100以内的正整数！");
						$("#number").val(100);
					}
				}
			});
			
			$("#score").keyup(function(){
				var value = $("#score").val();
				if(isNaN(Number(value))){
					layer.msg("不能输入非数字，请重新输入！");
					$("#score").val(1);
				}else{
					if(Number(value)<0||Number(value)>100){
						layer.msg("请输入100以内的正整数！");
						$("#score").val(100);
					}
				}
			});
			//$("#number").attr("onkeyup","value=(parseInt((value=value.replace(/[^\\d]/g,''))==''?'0':value,10))");
			//$("#number").attr("onafterpaste","value=(parseInt((value=value.replace(/[^\\d]/g,''))==''?'0':value,10))");
		} catch (err) {
			loading.close();
		}
		var charttypeId = $("#charttypeId").val();
		common.init("../defecttype/type?charttypeId=" + charttypeId, "POST",
				function(result) {
					/**
					 * 初始化类别下拉列表
					 */
					$("#defecttype").kendoDropDownList({
						optionLabel : "请选择......",
						dataTextField : "typeName",
						dataValueField : "id",
						dataSource : result.value,
						filter : "contains",
						change : onChange,
						suggest : true
					});

					/**
					 * 类别的change事件
					 */
					function onChange() {
						var charttypeId = $("#charttypeId").val();
						//获取选中的类别
						var defecttypeId = $("#defecttype").val();
						common.init("../defectitem/item?defecttypeId="
								+ defecttypeId, "POST", function(result) {
							//重新绑定数据源
							var dataSource = new kendo.data.DataSource({
								data : result.value
							});
							var dropdownlist = $("#defectitem").data(
									"kendoDropDownList");
							dropdownlist.setDataSource(dataSource);
						});
						common.do_submit();
					}
					;
				});
		common.do_submit();

		/**
		 * 初始化项目下拉列表
		 */
		var items = {};
		var charttype = {};
		charttype.id = $("#charttypeId").val();
		items.charttype = charttype;
		var itemsJson = JSON.stringify(items);
		var param = common.add_param("di", itemsJson);
		common.init("../defectitem/list", "POST", function(result) {
			$("#defectitem").kendoDropDownList({
				optionLabel : "请选择......",
				dataTextField : "item",
				dataValueField : "id",
				dataSource : result.value,
				filter : "contains",
				change : onChangeItem,
				suggest : false
			});
		});
		common.do_submit(param);
		/**
		 * 绑定选中项目的change事件
		 */
		function onChangeItem(e) {
			var charttypeId = $("#charttypeId").val();
			var defecttypeId = $("#defecttype").val();
			//获取选中的项目id
			var defectitemId = $("#defectitem").val();
			common.init("../defectitem/byid?id=" + defectitemId, "POST",
					function(result) {
						var obj = result.value;
						if (obj == undefined) {
							return;
						}
						var dropdownlist = $("#defecttype").data(
								"kendoDropDownList");
						var items = dropdownlist.options.dataSource;
						for (var i = 0; i < items.length; i++) {
							if (obj.type.id == items[i].id) {
								dropdownlist.select(i + 1);
								break;
							}
						}
					});
			common.do_submit();
			common.init("../defect/content?charttypeId=" + charttypeId
					+ "&defecttypeId=" + defecttypeId + "&defectitemId="
					+ defectitemId, "POST", function(result) {
				var data = result.value;
				if (data == undefined) {
					return;
				}
				if (data != null && data != "" && data != undefined
						&& data.length > 0) {
					var obj = data[0];
				}
								
			   /// 如果查无问题给 NUMBER赋0 备注和存在问题赋值
				var defecttypecolor = $("#defecttype").data("kendoDropDownList");
				var defecttype = defecttypecolor.text();
			
				var defectitemcolor = $("#defectitem").data("kendoDropDownList");
				var defectitem = defectitemcolor.text();
				//alert(defectitem+"    "+defecttype)
				if(defectitem.indexOf("无问题")>-1||defecttype.indexOf("无问题")>-1 ){
					$("#number").val(0);
				    $("#opinion").val("查无问题");			
		    		$("#score").val("0");
		    		$("#remarks").val("无");
					}else  if(defecttype.indexOf("说明")==0){
			        	$("#number").val("0");
			    	}else{	
					$("#number").val(1);
					$("#remarks").val("");
				   // $("#opinion").val("");	
					}
				
				
				$("#score").val(obj.score);
				point = obj.score;
				var num = $("#number").val();
				if (num == null || num == "") {
					$("#score").val(obj.score);
				} else {
					var sum = num * (point);
					$("#score").val(sum);
				}
			});
			common.do_submit();
		}
		;
	},

	/**
	 * 删除缺陷
	 */
	remove : function(tr) {
		/*删除*/
		layer.confirm('确认要删除吗？', function(index) {
			var rowData = grid.getSelectRowDataByRow(tr);
			// 添加参数 @param 参数key；参数value
			var processDefId = $("#processDefId").val();
			var taskDefId = $("#taskDefId").val();
			common.add_param("processDefId", processDefId);
			common.add_param("taskDefId", taskDefId);
			common.add_param("taskInstId", $("#taskInstId").val());
			common.add_param("processInstId", $("#processInstId").val());
			var param = common.add_param("id", rowData.id);
			common.init("../defect/remove_form", "POST",
					defect_form.removeSuccess);
			// 执行提交操作
			common.do_submit(param);
		});
	},

	/**
	 * 删除成功
	 * */
	removeSuccess : function(result) {
		grid.init("bacthAdd");
		layer.close(1);
		if (result.code != 1) {
			layer.msg("删除失败！");
			return;
		}
		layer.msg('删除成功');
		defect_form.requestData();
	},

	/**
	 * 初始化多选控件
	 */
	initMultiselect : function() {
		// 图层多选
		$("#layer").kendoMultiSelect({
			placeholder : "请选择图层",
			dataTextField : "value",
			dataValueField : "id",
			autoBind : false
		});
	},

	/**
	 * 创建缺陷列表
	 */
	createGrid : function() {
		var columns = defect_form.createColumns();
		grid.createGrid(columns);
	},

	/**
	 * 构建缺陷列集合
	 */
	createColumns : function() {
		grid.init("bacthAdd");
		grid.resetColumn;
		grid.addColumn("200px", "opinion", "存在问题及处理意见");
		grid.addColumn("100px", "defecttype", "检查内容");
		grid.addColumn("100px", "defectitem", "扣分项目");
		grid.addColumn("100px", "number", "缺陷个数");
		grid.addColumn("100px", "score", "扣分");
		grid.addColumn("200px", "remarks", "备注");
		return grid.addColumn("50px", "handle", "操作", kendo.template($(
				"#removeTemplate").html()));
	},

	/**
	 * 发送数据请求
	 */
	requestData : function() {
		var splitId = $("#splitId").val();
		var taskInstId = $("#taskInstId").val();
		var processInstId = $("#processInstId").val();
		var processDefId = $("#processDefId").val();
		var taskDefId = $("#taskDefId").val();
		common.init("../defect/formlist?taskDefId="+taskDefId+"&processDefId="+processDefId+"&splitId=" + splitId + "&taskInstId="
				+ taskInstId + "&processInstId=" + processInstId, "POST",
				defect_form.bindGrid);
		common.do_submit();
	},

	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindGrid : function(result) {
		grid.init("bacthAdd");
		grid.bindData(result);
		var gridInst = $("#bacthAdd").data("kendoGrid");
		var height = $(document.body).height() - ($(document.body).height())
				* 0.3;
		gridInst.setOptions({
			height : height,
			filterable : false,
			sortable : false,
			pageable : {
				pageSizes : true,
				buttonCount : 5,
				pageSize : 200
			},
		});
		var pager = $("#bacthAdd").find("div[data-role='pager']");
		$(pager).hide();
		var data = result.value;
		if (data == undefined || data == null || data.length <= 0) {
			return;
		} else {
			if (data[0].total != null && data[0].total != "") {
				aggregate = data[0].total;
			}
			$("#total").val(aggregate);
			$("#actualTotal").val(data[0].actualTotal);
			$("#grading").val(data[0].grading);
		}
	},

	/**
	 * 保存后的回调函数
	 */
	addSuccess : function(result) {
		if (result.code != 1) {
			if (result.value != "" || result.value != null) {
				layer.msg(result.value);
				return;
			}
			layer.msg("保存失败！");
			return;
		}
		layer.msg("保存成功！");
		$("#left :input").each(function() {
			$(this).val("");
		});
		$("#defecttype").data("kendoDropDownList").value("请选择......");
		$("#defectitem").data("kendoDropDownList").value("请选择......");
		defect_form.requestData();
	},

	/**
	 * 保存后的回调函数
	 */
	updateSuccess : function(result) {
		if (result.code != 1) {
			if (result.value != "" || result.value != null) {
				layer.msg(result.value);
				return;
			}
			layer.msg("保存失败！");
			return;
		}
		layer.msg("保存成功！");
		setTimeout('window.history.back()', 500);
	},

	/**
	 * 计算扣分
	 */
	calculate : function() {
		var num = $("#number").val();
		if(defect_form.chkHalf($("#number").val())){
			num = 0;
		}
		var reg1=/[a-zA-Z]+(?=\d+)|\d+(?=[a-zA-Z]+)/g;
		var reg2= /^[A-Za-z]+$/;
		if (reg1.test(num)||reg2.test(num)){//判断是否符合正则表达式
			num = 0;
		}
		if (Number(num) < 0) {
			layer.msg("缺陷个数不能填负数！");
		}
		if(Number(num)>100){
			num = 100;
		}
		var coe = $("#cff").val();
		if (coe == null || coe == "") {
			coe = 1;
			$("#coefficient").val(coe);
		}
		var sum = point;
		var actual = point / coe;
		if (num != null && num != "") {
			sum = num * point;
			actual = sum;
		}
		$("#score").val(sum);
		/*var zong = (parseFloat(aggregate)+parseFloat(sum)).toFixed(1);
		$("#total").val(parseFloat(zong));
		$("#actual").val(parseFloat(actual).toFixed(1));
		//实际扣分
		var actualTotal = (zong/coe).toFixed(1);
		$("#actualTotal").val(parseFloat(actualTotal));
		$("#grading").val(100-actualTotal);*/
	},

	/**
	 * 计算扣分
	 */
	calculate1 : function() {
		var num = $("#number").val();
		if(defect_form.chkHalf($("#number").val())){
			num = 0;
		}
		var coe = $("#cff").val();
		if (coe == null || coe == "") {
			coe = 1;
			$("#coefficient").val(coe);
		}
		var sum = $("#score").val();
		if (sum == null || sum == "") {
			sum = 0;
		}
		var actualSum = (sum / coe).toFixed(1);
		/*$("#actual").val(parseFloat(actualSum).toFixed(1));
		//获取累计得分
		var zong = (parseFloat(aggregate)+parseFloat(sum)).toFixed(1);
		$("#total").val(parseFloat(zong));
		//实际扣分
		var actualTotal = (zong/coe).toFixed(1);
		$("#actualTotal").val(parseFloat(actualTotal));
		$("#grading").val(100-actualTotal);*/
	},
	
	//全角转换为半角函数   
	ToCDB :function (str)   
	{   
	    var tmp = "";   
	    for(var i=0;i<str.length;i++)   
	    {   
	        if(str.charCodeAt(i)>65248&&str.charCodeAt(i)<65375){   
	            tmp += String.fromCharCode(str.charCodeAt(i)-65248);   
	        }else {   
	            tmp += String.fromCharCode(str.charCodeAt(i));   
	        }   
	    }   
	    return tmp; 
	},
	
	chkHalf: function (str){  
		 for (var i = 0; i < str.length; i++) {
			strCode = str.charCodeAt(i);
			if ((strCode > 65248) || (strCode == 12288)) {
				return true;
			}
		}
		 return false;
	} ,
	
	createPlanGrid : function(){
		var columns = defect_form.createPlanColumns();
		grid.createGrid(columns);
	},
	
	createPlanColumns : function(){
		grid.init("plan");
		grid.resetColumn();
		var taskDefId = $("#taskDefId").val();
		if(taskDefId.indexOf("zhijian")!=-1){
			return grid.addColumn("200px","remark","质检记录");
		}else if(taskDefId.indexOf("shending")!=-1){
			return grid.addColumn("200px","remark","审定记录");
		}
		
	},
	
	requestPlanData : function(){
		var taskDefId = $("#taskDefId").val();
		if(taskDefId.indexOf("zhijian")!=-1){
			taskDefId = "u_task_plan_zhijian";
		}else if(taskDefId.indexOf("shending")!=-1){
			taskDefId = "u_task_plan_shending";
		}
		common.add_param("taskDefId",taskDefId);
		var param = common.add_param("parentProcessInstId",$("#parentProcessInstId").val());
		common.init("../formValue/getByProcess","POST",defect_form.bindPlanGrid);
		common.do_submit(param);
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindPlanGrid : function(result){
		grid.init("plan");
		grid.bindData(result);
		var gridInst = $("#plan").data("kendoGrid");
		gridInst.setOptions({
			height : 300,
		});
		var pager = $("#plan").find("div[data-role='pager']");
		$(pager).hide();
	},

	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function() {

		/**
		 * 绑定缺陷个数输入完之后的事件
		 */
		$("#number").bind("input propertychange", function() {
			defect_form.calculate();
		});
		
		$("#processplan").on("click",function(){
			grid.init("plan");
			loading.init();
			try{
				defect_form.createPlanGrid();
				defect_form.requestPlanData();
			}
			catch(err){
				loading.close();
			}
			loading.close();
			$("#myModal").modal('show');
		});

		/**
		 * 绑定扣分输入后的事件
		 */
		/* $("#score").bind("input propertychange", function() {
			defect_form.calculate1();
		 }); */

		/** 绑定返回按钮的click事件*/
		$("#back").on(
				"click",
				function() {
					var defect = {};
					var grading = $("#grading").val();
					/*if(Number(grading)<=0||Number(grading)>=100){
						return layer.msg("质量评分必须在0-100之间");
					}*/
					// 选中图层
					defect.splitId = $("#splitId").val();
					defect.grading = $("#grading").val();
					defect.taskInstId = $("#taskInstId").val();
					defect.processInstId = $("#processInstId").val();
					var processDefId = $("#processDefId").val();
					var taskDefId = $("#taskDefId").val();
					common.add_param("processDefId", processDefId);
					common.add_param("processInstId", $("#processInstId").val());
					common.add_param("taskDefId", taskDefId);
					common.add_param("taskInstId", $("#taskInstId").val());
					var defectJson = JSON.stringify(defect);
					var param = common.add_param("defect", defectJson);
					common.init("../defect/updategrading", "POST",
							defect_form.updateSuccess);
					common.do_submit(param);
				});

		/** 绑定提交按钮的点击事件*/
		$("#submit").on("click", function() {
			var defect = {};
			
			/*
		rid.addColumn("200px", "opinion", "存在问题及处理意见");
		grid.addColumn("100px", "defecttype", "检查内容");
		grid.addColumn("100px", "defectitem", "扣分项目");
		grid.addColumn("100px", "number", "缺陷个数");
		grid.addColumn("100px", "score", "扣分");
		grid.addColumn("200px", "remarks", "备注");
		
			 * */
						
			defect.splitId = $("#splitId").val();
			defect.taskInstId = $("#taskInstId").val();
			defect.processInstId = $("#processInstId").val();
			defect.charttype = $("#charttype").val();
			defect.opinion = $("#opinion").val();
			defect.number = $("#number").val();
			defect.score = $("#score").val();
			
			
			if (defect.opinion == null || defect.opinion == "") {
				return layer.msg("请填写存在问题及处理意见！");
			}
			
			if (defecttype == "请选择......") {
				return layer.msg("检查内容不可为空，请返回选择！");
		}
			
			if (defectitem == "请选择......") {
				return layer.msg("扣分项目不可为空，请返回选择！");
			}
			if (defect.number == null || defect.number == "") {
				return layer.msg("请填写缺陷个数！");
			}
			
			if (defect.score == null || defect.score == "") {
				return layer.msg("请填写扣分！");
			}

			var defecttypecolor = $("#defecttype").data("kendoDropDownList");
			var defecttype = defecttypecolor.text();
			
			defect.defecttype = defecttype;
			var defectitemcolor = $("#defectitem").data("kendoDropDownList");
			var defectitem = defectitemcolor.text();
			
			defect.defectitem = defectitem;
			defect.number = $("#number").val();
			defect.deep = $("#deep").val();
			var score = $("#score").val();
			defect.score = score;
			var remarks = $("#remarks").val();
			if (remarks.length > 50) {
				layer.msg("您输入的字符过长，请重新输入！");
				return;
			}
			defect.remarks = remarks;
			// 表单ID
			defect.formId = $("#formId").val();
			//难度系数
		//	var coefficient = $("#cff").val();
			var coefficient = $("#coefficient").val();
			defect.coefficient = coefficient;
			
			
			
			var defectJson = JSON.stringify(defect);
			var processDefId = $("#processDefId").val();
			var taskDefId = $("#taskDefId").val();
			var processInstId = $("#processInstId").val();
			common.add_param("processDefId", processDefId);
			common.add_param("taskDefId", taskDefId);
			common.add_param("processInstId", processInstId);
			var param = common.add_param("defect", defectJson);
			common.init("../defect/addform", "POST", defect_form.addSuccess);
			common.do_submit(param);
		});
	},
}