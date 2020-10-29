$(function(){
	formValue.init();
	$("#submit").text("查看");
})

var formValue = {
	
	_propKeys : [],
	
	_taskInstId : null,
	
	_processInstId : null,
	
	_formId : null,
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("formPropValue");
		loading.init();
		try{
			formValue._taskInstId = $("#taskInstId").val();
			formValue._processInstId = $("#processInstId").val();
			formValue._formId = $("#formId").val();
			//创建列表
			formValue.createformValueGrid();
			//请求列表数据
			formValue.requestformValueData();
			formValue.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建表单列表列集合
	 */
	createformValueColumns : function(){
		// 获取隐藏域的map集合值
		var mapHidden = $("#mapHidden").val();
		// 将JSON字符串转化为JSON对象
		formValue._propKeys = JSON.parse(mapHidden); 
		// 遍历JSON对象取得表头
		var colmuns = null;
		for (var int = 0; int < formValue._propKeys.length; int++) {
			var prop = formValue._propKeys[int];
			var key = prop.key;
			var name = prop.name;
			var type = prop.type;
			if (type == "img") {
				colmuns = grid.addColumn("150px",key,name,kendo.template($("#formImgTemplate").html()));
			}
			else if(type == "file"){
				colmuns = grid.addColumn("150px",key,name,kendo.template($("#formFileTemplate").html()));
			}
			else{
				colmuns = grid.addColumn("150px",key,name);
			}
		}
		return colmuns;
	},
	
	/**
	 * 创建列表
	 */
	createformValueGrid : function(){
		var columns = formValue.createformValueColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestformValueData : function(){
		common.init("../formValue/list","POST",formValue.bindformValueGrid);
		common.add_param("taskInstId",formValue._taskInstId);
		common.add_param("processInstId",formValue._processInstId);
		var data = common.add_param("formId",formValue._formId);
		common.do_submit(data);
	},
	

	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindformValueGrid : function(result){
		grid.bindData(result);
		/** 绑定查看签章窗口按钮的click事件*/
		var imgBtns = $("button[name='editImg']");
		$.each(imgBtns,function(i,item){
			$(item).on("click",function(){
				var td = $(item).parent();
				var tr = $(item).parent().parent();
				var propIndex = td.index()-2;
				if (propIndex >=0) {
					formValue.toFormValueFormImgPage(tr,propIndex);
				}
			});
		});
		
		/** 绑定查看文件窗口按钮的click事件*/
		var imgBtns = $("button[name='editFile']");
		$.each(imgBtns,function(i,item){
			$(item).on("click",function(){
				var td = $(item).parent();
				var tr = $(item).parent().parent();
				var propIndex = td.index()-2;
				if (propIndex >=0) {
					formValue.toFormValueFormFilePage(tr,propIndex);
				}
			});
		});
		//grid.setEvents();
	},

	/**
	 * 跳转到查看图片页面
	 */
	toFormValueFormImgPage : function(tr,propIndex){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取url的值
		var url = rowData[formValue._propKeys[propIndex].key];
		// 跳转到表单查看页面
		common.toPage("../formValue/form_img?url="+url);
	},
	
	/**
	 * 下载文件
	 */
	toFormValueFormFilePage : function(tr,propIndex){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取用户ID
		var url = rowData[formValue._propKeys[propIndex].key];
		// 下载
		common.toPage("../formValue/form_file?url="+url);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		$("#submit").one("click",function(){
			var formId = $("#formId").val();
			common.toPage("../docTempleteAction/detils?formId="+formValue._formId+"&processInstId="+formValue._processInstId+"&taskInstId="+formValue._taskInstId);
		})
		
		$("#back").one("click",function(){
			window.history.back();
		})
	}
}