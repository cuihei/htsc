$(function(){
	item_edit.init();
})

var item_edit = {
	/**
	 * 初始化
	 */
	init : function(){
		item_edit.bindPageEvent();
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
		common.toPage("../defectitem/index");
		 //执行刷新用户列表按钮
		 parent.$("#refresh").click();
	},
	
	changeSuccess : function(result){
		var data = result.value;
		$("#defecttype").empty();
		var options = '<option value="" selected>--请选择--</option>';
		if(data != undefined){
			if(data.length>0){
				$.each(data,function(index,obj){
					options+='<option value="'+obj.id+'">'+obj.typeName+'</option>';
				}); 
			}
		}
		$("#defecttype").html(options);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定提交按钮的click事件*/
		$("#submit").on("click",function(){
			var items = {};
			var charttype = {};
			var type = {};
			items.id = $("#itemId").val();
			charttype.id = $("#charttype").val();
			if(charttype.id != null && charttype.id != "" && (charttype.id).indexOf("请选择")<= -1){
				items.charttype = charttype;
			}else{
				layer.msg("请选择海图类型！");
				return;
			}
			type.id = $("#defecttype").val();
			if(type.id != null && type.id != "" && (type.id).indexOf("请选择")<= -1){
				items.type = type;
			}else{
				layer.msg("请选择缺陷类型！");
				return;
			}
			items.item = $("#item").val();
			if(items.item == null || items.item == ""){
				layer.msg("请填写项目！");
				return;
			}
			var itemJson = JSON.stringify(items);
			var param = common.add_param("item", itemJson);
			common.init("../defectitem/edit","POST",item_edit.editSuccess);
			common.do_submit(param);
		});
		
		/** 绑定返回按钮的click事件*/
		 $("#back").on("click",function(){
			 window.history.go(-1);
		 });
		 
		 /** 绑定海图类型select框的change事件*/
		 $("#charttype").change(function(){
			 var charttypeId = $("#charttype").val();
			 if(charttypeId == "" || charttypeId == null || charttypeId.indexOf("请选择")>-1){
				   $("#defecttype").empty();
				   var options = '<option value="">--请选择--</option>';
				   $("#defecttype").html(options);
				   return;
				}
			common.init("../defecttype/type?charttypeId="+charttypeId,"POST",item_edit.changeSuccess);
			common.do_submit();
		 });
		 
	}
}