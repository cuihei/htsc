$(function(){
	var id = $("#id").val();
	
	 $("#submit").on("click",function(){
		 
		 
	 if(id==null||id==''){
		 var dictionaries = {};
		 dictionaries.code = $("#code").val();
		 dictionaries.value = $("#value").val();
		 dictionaries.typeId = $("#type option:selected").val();
		 dictionaries.typeName = $("#type option:selected").text();
		 dictionaries.creator = $("#creator").val();
		 //dictionaries.creationDate = $("#creationDate").val();
		 //dictionaries.creationDate = "2016-12-12";
		 dictionaries.modifier = $("#modifier").val();
		 //dictionaries.modifyDate = $("#modifyDate").val();
		 //dictionaries.modifyDate = "2016-12-12";
		 // 转化为JSON对象
		 var dictionariesJson = JSON.stringify(dictionaries);
		 var param = common.add_param("dictionaries",dictionariesJson);
		 common.init("../dictionaries/add","POST",function(result){
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
			 common.toPage("../dictionaries/index");
			 //执行刷新资源列表按钮
			 parent.$("#refresh").click();
		 });
		 common.do_submit(param);
	 }else{
		 var dictionaries = {};
		 dictionaries.id = id;
		 dictionaries.code = $("#code").val();
		 dictionaries.value = $("#value").val();
		 dictionaries.typeId = $("#type option:selected").val();
		 dictionaries.typeName = $("#type option:selected").text();
		 dictionaries.creator = $("#creator").val();
		 dictionaries.modifier = $("#modifier").val();
		 // 转化为JSON对象
		 var dictionariesJson = JSON.stringify(dictionaries);
		 var param = common.add_param("dictionaries",dictionariesJson);
		 common.init("../dictionaries/edit","POST",function(result){
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
			 common.toPage("../dictionaries/index");
			 //执行刷新资源列表按钮
			 parent.$("#refresh").click();
		 });
		 common.do_submit(param);
	 }	 
	 
	 
	 
	 
	 
   });
	 
		/** 绑定取消按钮的click事件*/
	 $("#back").on("click",function(){
		//调回资源列表显示页面
		 common.toPage("../dictionaries/index");
		//执行刷新资源列表按钮
		 parent.$("#refresh").click();
	 })
 
})