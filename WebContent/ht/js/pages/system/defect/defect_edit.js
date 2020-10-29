$(function(){
	defect_edit.init();

})

var defect_edit = {
	/**
	 * 初始化
	 */
	init : function(){
		$("#score").attr("onkeyup","value=(parseInt((value=value.replace(/[^\\d]/g,''))==''?'0':value,10))");
		$("#score").attr("onafterpaste","value=(parseInt((value=value.replace(/[^\\d]/g,''))==''?'0':value,10))");
		defect_edit.bindPageEvent();
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
		 common.toPage("../defect/index");
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
	
	//项目选择成功后调用函数
	changeItemSuccess : function(result){
		var data = result.value;
		$("#defectitem").empty();
		var options = '<option value="" selected>--请选择--</option>';
		if(data == undefined){
			return;
		}
		if(data.length>0){
			$.each(data,function(index,obj){
				options+='<option value="'+obj.id+'">'+obj.item+'</option>';
			}); 
		}
		$("#defectitem").html(options);
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定提交按钮的click事件*/
		$("#submit").on("click",function(){
			var defect = {};
			var charttype = {};
			var defecttype = {};
			var defectitem = {};
			defect.id = $("#defectId").val();
			charttype.id = $("#charttype").val();
			if(charttype.id == null || charttype.id == "" ||(charttype.id).indexOf("请选择")> 0){
				layer.msg("请选择海图类型！");
				return;
			}else{
				defect.charttype = charttype;
			}
			defecttype.id = $("#defecttype").val();
			if(defecttype.id == null || defecttype.id == "" || (defecttype.id).indexOf("请选择")>0){
				layer.msg("请选择类别！");
				return;
			}else{
				defect.type = defecttype;
			}
			defectitem.id = $("#defectitem").val();
			if(defectitem.id == null || defectitem.id == "" || (defectitem.id).indexOf("请选择")>0){
				layer.msg("请选择项目类型！");
				return;
			}else{
				defect.item = defectitem;
			}
			var content = $("#discription").val();
			if(content != null && content != ""){
				defect.discription = content;
			}
			var deep = $("#deep").val();
			if(deep == null || deep == "" ||deep.indexOf("请选择")>0){
				layer.msg("请选择缺陷类别！");
				return;
			}else{
				defect.deep = deep;
			}
			defect.score = $("#score").val();
			if(defect.score == null || defect.score == ""){
				layer.msg("请填写扣分！");
				return;
			}else{
				reg = /^[0-9]*$/;
				if(!reg.test(defect.score)){
					layer.msg("只能填写数字！");
					return;
				}
			}
			var defectJson = JSON.stringify(defect);
			var param = common.add_param("defect", defectJson);
			common.init("../defect/edit","POST",defect_edit.editSuccess);
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
			common.init("../defecttype/type?charttypeId="+charttypeId,"POST",defect_edit.changeSuccess);
			common.do_submit();
		 });
		 
		 /** 绑定缺陷类型select框的change事件*/
		 $("#defecttype").change(function(){
			 var defecttypeId = $("#defecttype").val();
			 if(defecttypeId == "" || defecttypeId == null || defecttypeId.indexOf("请选择")>-1){
				   $("#defectitem").empty();
				   var options = '<option value=" ">--请选择--</option>';
				   $("#defectitem").html(options);
				   return;
				}
			common.init("../defectitem/item?defecttypeId="+defecttypeId,"POST",defect_edit.changeItemSuccess);
			common.do_submit();
		 });
	}
}