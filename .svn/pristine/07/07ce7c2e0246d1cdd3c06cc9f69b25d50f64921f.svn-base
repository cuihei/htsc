$(function() {
	// 初始化表格对象
	grid.init("div_process_form_prop");
	// 显示加载层
	loading.init();
	try{
		// 创建表格
		process_form_prop.createGrid();
		// 发送数据请求绑定表格数据
		process_form_prop.requestData();
		// 绑定事件
		$("#submit").on("click",function(){
			var gridInst = $("#div_process_form_prop").data("kendoGrid");
			gridInst.saveChanges();
			var rowDatas = grid.getAllRowsData();
			var p = [];
			for (var int = 0; int < rowDatas.length; int++) {
				var rowData = rowDatas[int];
				var f = {};
				f.id = rowData.id;
				f.processFormId = rowData.processFormId;
				f.formPropId = rowData.formPropId;
				f.editAble = rowData.editAble;
				f.propKey = rowData.propKey;
				f.propName = rowData.propName;
				f.propType = rowData.propType;
				f.defaultValue = rowData.defaultValue;
				f.defaultDelegate = rowData.defaultDelegate;
				f.required = rowData.required;
				f.selectData = rowData.selectData;
				f.num = rowData.num;
				p.push(f);
			}
			var param = common.add_param("processFormProps",JSON.stringify(p));
			common.init("../task/process_form_prop_save","POST",function(result){
				if (result.code == 0) {
					layer.msg(result.value);
					return;
				}
				layer.msg("提交成功");
				process_form_prop.requestData();
			});
			common.do_submit(param);
		})
		
		$("#back").on("click",function(){
			history.back();
		})
	}
	catch(err){
		loading.close();
	}
})

var process_form_prop = {
	/**
	 * 创建任务表格
	 */
	createGrid : function(){
		grid.resetColumn();
		grid.addColumn("100px","propKey","属性键");
		grid.addColumn("100px","propName","属性名称");
		grid.addColumn("100px","propType","属性类型");
		grid.addColumn("100px","editAble","是否可编辑");
		grid.addColumn("100px","num","排序");
		grid.addColumn("100px","selectData","筛选框数据");
		grid.addColumn("100px","defaultValue","默认值");
		var columns = grid.addColumn("100px","defaultDelegate","默认委托");
		grid.createGrid(columns);
	},
	
	/**
	 * 发送获取任务列表请求
	 */
	requestData : function(){
		common.init("../task/process_form_prop_list","POST",function(result){
			if (result.code == 0) {
				layer.msg(result.value);
				return;
			}
			// 綁定数据
			grid.bindData(result);
			var gridInst = $("#div_process_form_prop").data("kendoGrid");
			gridInst.setOptions({
				editable:true
			});
			gridInst.table.on("click", "tr", function(e){
    			var td = $(e.target);
    			var index = gridInst.cellIndex(td)
    			if (index != 4&&index != 5&&index != 6&&index != 7&&index != 8&&index != 9) {
    				return false;
    			}
    		});
			// 提示加载成功信息
			layer.msg(msg.LOAD_SUCCESS);
		});
		var processDefKey = $("#processDefKey").val();
		var taskDefId = $("#taskDefId").val();
		common.add_param("taskDefId",taskDefId);
		var data = common.add_param("processDefKey",processDefKey);
		common.do_submit(data);
	}
}