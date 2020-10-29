$(function() {
	formProp_edit.init();

})

var formProp_edit = {
	/**
	 * 初始化
	 */
	init : function() {
		$("#selectData").attr("disabled","disabled")//将元素设置为readonly
		// 文件类别change事件
		$("#propType").on("click", function() {
			// 判断文件类别是否是电子文件，显示不同的输入框
			if ($("#propType").val() == "select"||$("#propType").val() == "kendoselect"||$("#propType").val() == "multiselect") {
				// 获取最外层父节点
				$("#hiddenDiv").css("display", "");
			} else {
				$("#hiddenDiv").css("display", "none");
			}
		});
		formProp_edit.bindPageEvent();
	},

	/**
	 * 修改或新增成功
	 */
	editSuccess : function(result) {
		 if (result.code == 0) {
				if(result.value !="" && result.value != null){
					layer.msg(result.value);
					return;
				}else{
					layer.msg("保存失败！");
					return;
				}
		  }
		var formPropId = $("#formPropId").val();
		var formId = $("#form_id").val();
		if (formPropId != null) {
			layer.msg('修改成功!', {
				icon : 1,
				time : 6000
			});
			common.toPage("../formProp/index?form_id=" + formId);
		} else {
			layer.msg('新增成功!', {
				icon : 1,
				time : 6000
			});
			common.toPage("../formProp/index?form_id=" + formId);
		}
	},

	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function() {
		$("#submit").on("click", function() {
			var formProp = {};
			formProp.id = $("#formPropId").val();
			// 获取所属表单id
			formProp.formId = $("#form_id").val();
			// 获取表单属性键
			formProp.propKey = $("#propKey").val();
			// 获取表单属性名称
			formProp.propName = $("#propName").val();
			// 获取表单属性类型
			formProp.required = $("#required").val();
			// 获取表单属性类型
			formProp.num = $("#num").val();
			// 获取表单属性类型
			formProp.propType = $("#propType").val();
			formProp.selectData = $("#selectData").val();
			formProp.selectDic = $("#selectDic").val();
			// 获取表单属性默认值
			formProp.propDefaultValue = $("#propDefaultValue").val();
			var formPropJson = JSON.stringify(formProp);
			var param = common.add_param("formProp", formPropJson);
			common.init("../formProp/add", "POST", formProp_edit.editSuccess);
			common.do_submit(param);
		});

		/** 绑定取消按钮的click事件 */
		$("#back").on(
				"click",
				function() {
					var formId = $("#form_id").val();
					// 调回用户列表显示页面
					common.toPage("../formProp/index?form_id=" + formId);
					// 执行刷新用户列表按钮
					parent.$("#refresh").click();
				})

		/** 绑定选择下拉菜单确定按钮的click事件 */
		 $("#importSubmit").on("click",function(){
			var selectKey = $("#selectKey").val();
			var selectType = $("#selectType").val();
			if((selectKey == null || selectKey =="" )&&(selectType == null || selectType =="" )&&(selectType =="--请选择--" )){
				layer.msg("请输入列表的值或选择字典类型");
				return false;
			}
			if(selectType != null && selectType !="" &&selectType !="--请选择--" ){
				$("#selectDic").val(selectType);
			}else if(selectKey != null && selectKey !="" ){
				$("#selectData").val(selectKey);
			}else{
				layer.msg("请输入列表的值或选择字典类型");
				return false;
			}
			
			
		 });		

		/** 发布通知按钮点击事件 */
		 $("#addBaseData").on("click",function(){
				$('#myModal').modal('show');
		 });
	}

}