$(function(){
	var id = $("#id").val();
	
	$("#revision").attr("onkeyup","value=(parseInt((value=value.replace(/[^\\d]/g,''))==''?'0':value,10))");
	$("#revision").attr("onafterpaste","value=(parseInt((value=value.replace(/[^\\d]/g,''))==''?'0':value,10))");
	
	$("#scale").attr("onkeyup","value=(parseInt((value=value.replace(/[^\\d]/g,''))==''?'0':value,10))");
	$("#scale").attr("onafterpaste","value=(parseInt((value=value.replace(/[^\\d]/g,''))==''?'0':value,10))");
	
	
	/** 绑定确定添加按钮的click事件*/
	$("#submit").on("click",function(){
		var workDays = {};
		var patrn=/^[0-9]+([.][0-9]+){0,1}$/;   
		 if($("#content").val()=="--请选择--"){
			layer.msg("请选择编绘类型");
			return;
		}
		 if($("#revision").val()== "--请选择--"){
			 layer.msg("请选择版次");
			 return;
		 }
		 if($("#mapNo").val()==""){
			 layer.msg("请输入图号");
				return;
		 }	
		 if($("#mapName").val()==""){
			 layer.msg("请输入图名");
				return;
		 }
		 if($("#scale").val()==""){
			 layer.msg("请输入比例尺");
				return;
		 }
		 if(!patrn.exec($("#compilationWorkDays").val())){
			 layer.msg("编绘工天只能输入数字");
				return;
		 }
		 if(!patrn.exec($("#checkWorkDays").val())){
			 layer.msg("质检工天只能输入数字");
				return;
		 }
		 if(!patrn.exec($("#examineWorkDays").val())){
			 layer.msg("审定工天只能输入数字");
				return;
		 }
		workDays.content = $("#content").val();
		workDays.revision = $("#revision").val();
		workDays.mapNo = $("#mapNo").val();
		workDays.mapName = $("#mapName").val();
		workDays.scale = $("#scale").val();
		workDays.compilationWorkDays = $("#compilationWorkDays").val();
		workDays.checkWorkDays = $("#checkWorkDays").val();
		workDays.examineWorkDays = $("#examineWorkDays").val();
		workDays.id = id;
		var workDaysJson = JSON.stringify(workDays);
		var param = common.add_param("workDays", workDaysJson);
		common.init("../workDays/save?flag="+flag.value,"POST",function(result){
			if(result.code != 1){
				if(result.value !="" && result.value != null){
					layer.msg(result.value);
					return;
				}
				layer.msg("保存失败！");
				return;
			}
			layer.msg("保存成功！");
			common.toPage("../workDays/index?flag="+flag.value);
		});
		common.do_submit(param);
	 });
	 /** 绑定返回按钮的click事件*/
	 $("#back").on("click",function(){
		 common.toPage("../workDays/index?flag="+flag.value);
		 parent.$("#refresh").click();
	 });

})
