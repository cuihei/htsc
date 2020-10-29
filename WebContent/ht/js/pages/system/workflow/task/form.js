$(function() {
	var processDefId = $("#processDefId").val();
	var processDefKey = $("#processDefKey").val();
	var taskDefId = $("#taskDefId").val();
	var taskDefName = $("#taskDefName").val();
	if($('#formTypeId option:selected').text()!='--请选择--'){
		$("#formTypeId").prepend(
		"<option value='0'>--请选择--</option>");
	};//选中的值
	$("#submit").on(
			"click",
			function() {
				var processForm = {};
				processForm.processDefId = processDefKey;
				processForm.taskDefId = taskDefId;
				processForm.formId = $("#formId option:selected").val();
				processForm.orderNo = $("#orderNo").val();
				processForm.delegate = $("#delegate").val();
				// 转化为JSON对象
				var processForm = JSON.stringify(processForm);
				var param = common.add_param("processForm", processForm);
				common.init("../task/formEdit", "POST", function(result) {
					// 调回资源列表显示页面
					common.toPage("../workflow/task/init?processDefId="
							+ processDefId);
					// 执行刷新资源列表按钮
					parent.$("#refresh").click();
				});
				common.do_submit(param);
			});
	/** 绑定取消按钮的click事件 */
	$("#back").on("click", function() {
		// 调回资源列表显示页面
		common.toPage("../workflow/task/init?processDefId=" + processDefId);
		// 执行刷新资源列表按钮
		parent.$("#refresh").click();
	});
	/** 绑定表单按钮的click事件 */
	$("#formTypeId").on(
			"change",
			function() {
				var formTypeId = $("#formTypeId").val();
				if(formTypeId=='0'){
					$("#formId").empty();
					$("#formId").prepend(
					"<option value='0'>--请选择--</option>");
				}
				$.ajax({
					url : '../task/getChildeForm?formId=' + formTypeId,
					type : 'POST',
					success : function(data) {
						var length = data.length;
						if (length != 0) {
							$("#formId").empty();
							$("#formId").append(
									"<option value=''>--请选择--</option>");
							for (var i = 0; i < length; i++) {
								$("#formId")
										.append(
												"<option value="
														+ data[i]['id'] + ">"
														+ data[i]['name']
														+ "</option>");
							}
						}
					},
					error : function() {
					}
				});
			});
	
		$("#prop").on("click", function() {
			// 调回资源列表显示页面
			common.toPage("../workflow/task/process_form_prop_init?processDefKey=" + processDefKey + "&taskDefId=" +taskDefId+"&taskDefName="+taskDefName);
		});
})

	