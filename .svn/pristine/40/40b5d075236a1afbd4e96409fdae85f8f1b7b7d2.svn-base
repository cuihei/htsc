$(function(){
	form_edit.init();
})

var form_edit = {
	/**
	 * 初始化
	 */
	init : function(){
		form_edit.bindPageEvent();
	},
	
	/**
	 * 修改或新增成功
	 * */
	editSuccess : function(result){
		var formId = $("#formId").val();
		 if (result.code == 0) {
				if(result.value !="" && result.value != null){
					layer.msg(result.value);
					return;
				}else{
					layer.msg("保存失败！");
					return;
				}
		  }
		if(formId != null){
			layer.msg('修改成功!',{icon:1,time:6000});
			common.toPage("../form/index");
		}else{
			layer.msg('新增成功!',{icon:1,time:6000});
			common.toPage("../form/index");
		}
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		$("#submit").on("click",function(){
			 var form = {};
			 form.id = $("#formId").val();
			 // 获取所属基础数据id
			 form.baseDataId = $("#baseDataId").val();
			 // 获取表单名称
			 form.name = $("#name").val();
			 // 获取表单地址
			 form.url = $("#url").val();
			 var formJson = JSON.stringify(form);
			 var param = common.add_param("form",formJson);
			 common.init("../form/add","POST",form_edit.editSuccess);
			 common.do_submit(param);
		});
		
		/** 绑定取消按钮的click事件*/
		 $("#back").on("click",function(){
			//调回用户列表显示页面
			 window.history.go(-1);
			//执行刷新用户列表按钮
			 parent.$("#refresh").click();
		 })
	}
}