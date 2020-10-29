$(function(){
	type_edit.init();
})

var type_edit = {
	/**
	 * 初始化
	 */
	init : function(){
		type_edit.bindPageEvent();
	},
	
	editSuccess : function(result){
		if(result.code != 1){
			if(result.value != "" || result.value != null){
				layer.msg(result.value);
				return;
			}
			layer.msg("保存失败！");
			return;
		}
		layer.msg("保存成功！");
		 //返回目录主页面
		common.toPage("../defecttype/index");
		 //执行刷新用户列表按钮
		 parent.$("#refresh").click();
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定提交按钮的click事件*/
		$("#submit").on("click",function(){
			var type = {};
			var charttype = {};
			var typetype = {};
			type.id = $("#typeId").val();
			charttype.id = $("#charttype").val();
			if(charttype.id == null || charttype.id == "" ||(charttype.id).indexOf("请选择")>-1){
				layer.msg("请选择海图类型！");
				return;
			}
			type.charttype = charttype;
			type.typeName = $("#typeName").val();
			if(type.typeName  == null || type.typeName  == ""){
				layer.msg("请填写类别,如果没有请选择--！");
				return;
			}
			var typeJson = JSON.stringify(type);
			var param = common.add_param("type", typeJson);
			common.init("../defecttype/edit","POST",type_edit.editSuccess);
			common.do_submit(param);
		});
		
		/** 绑定返回按钮的click事件*/
		 $("#back").on("click",function(){
			 window.history.go(-1);
		 })
		 
	}
}