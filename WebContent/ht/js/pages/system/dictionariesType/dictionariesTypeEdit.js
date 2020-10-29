$(function(){
	var id = $("#id").val();
	
	 $("#submit").on("click",function(){
		 
		 
	 if(id==null||id==''){
		 var dictionariesType = {};
		 dictionariesType.name = $("#name").val();
		 dictionariesType.creator = $("#creator").val();
		 dictionariesType.modifier = $("#modifier").val();
		 // 转化为JSON对象
		 var dictionariesTypeJson = JSON.stringify(dictionariesType);
		 var param = common.add_param("dictionariesType",dictionariesTypeJson);
		 common.init("../dictionariesType/add","POST",function(result){
			//调回资源列表显示页面
			 if (result.code == 0) {
					if(result.value !="" && result.value != null){
						layer.msg(result.value);
						return;
					}else{
						layer.msg("保存失败！请联系管理员");
						return;
					}
			  }else if(result.code == 1){
				  layer.msg(result.value.msg);
				  if(result.value.flag){
					 
				  }else{
					  return;
				  }
			  }
			 common.toPage("../dictionariesType/index");
			 //执行刷新资源列表按钮
			 parent.$("#refresh").click();
		 });
		 common.do_submit(param);
	 }else{
		 var dictionariesType = {};
		 dictionariesType.id = id;
		 dictionariesType.name = $("#name").val();
		 dictionariesType.creator = $("#creator").val();
		 dictionariesType.modifier = $("#modifier").val();
		 // 转化为JSON对象
		 var dictionariesTypeJson = JSON.stringify(dictionariesType);
		 var param = common.add_param("dictionariesType",dictionariesTypeJson);
		 common.init("../dictionariesType/edit","POST",function(result){
			//调回资源列表显示页面
			 if (result.code == 0) {
					if(result.value !="" && result.value != null){
						layer.msg(result.value);
						return;
					}else{
						layer.msg("保存失败！");
						return;
					}
			  }
			 common.toPage("../dictionariesType/index");
			 //执行刷新资源列表按钮
			 parent.$("#refresh").click();
		 });
		 common.do_submit(param);
	 }	 
	 
   });
	 
		/** 绑定取消按钮的click事件*/
	 $("#back").on("click",function(){
		//调回资源列表显示页面
		 common.toPage("../dictionariesType/index");
		//执行刷新资源列表按钮
		 parent.$("#refresh").click();
	 })
 
})