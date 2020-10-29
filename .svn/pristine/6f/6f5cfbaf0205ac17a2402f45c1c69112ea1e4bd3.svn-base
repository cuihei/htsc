$(function(){
	var id = $("#id").val();
	
	 $("#submit").on("click",function(){
		 
	 var flag =$("#flag").val();
	 if(id==null||id==''){
		 var dictionaries = {};
		 dictionaries.code = $("#code").val();
		 dictionaries.value = $("#value").val();
		 
		 if(flag == '1'){
			 dictionaries.typeId = "02281125502140137";
			 dictionaries.typeName = "海图资料所属海区";
		 }else{
			 dictionaries.typeId = "02281555137100012";
			 dictionaries.typeName = "外业汇交资料所属海区";
		 }
		 // 转化为JSON对象
		 var dictionariesJson = JSON.stringify(dictionaries);
		 var param = common.add_param("dictionaries",dictionariesJson);
		 common.init("../dictionaries/add","POST",function(result){
			 if(result.code == 0){
				 layer.msg(result.value);
			 }else{
				//调回资源列表显示页面
				 var flag =$("#flag").val();
				 common.toPage("../dictionaries/books_index?flag="+flag);
				 //执行刷新资源列表按钮
				 parent.$("#refresh").click();
			 }
		 });
		 common.do_submit(param);
	 }else{
		 var dictionaries = {};
		 dictionaries.id = id;
		 dictionaries.code = $("#code").val();
		 dictionaries.value = $("#value").val();
		 if(flag == '1'){
			 dictionaries.typeId = "02281125502140137";
			 dictionaries.typeName = "海图资料所属海区";
		 }else{
			 dictionaries.typeId = "02281555137100012";
			 dictionaries.typeName = "外业汇交资料所属海区";
		 }
		 // 转化为JSON对象
		 var dictionariesJson = JSON.stringify(dictionaries);
		 var param = common.add_param("dictionaries",dictionariesJson);
		 common.init("../dictionaries/edit","POST",function(result){
			 if(result.code == 0){
				 layer.msg(result.value);
			 }else{
				 //调回资源列表显示页面
				 var flag =$("#flag").val();
				 common.toPage("../dictionaries/books_index?flag="+flag);
				 //执行刷新资源列表按钮
				 parent.$("#refresh").click();
			 }
		 });
		 common.do_submit(param);
	 }	 
   });
	 
		/** 绑定取消按钮的click事件*/
	 $("#back").on("click",function(){
		//调回资源列表显示页面
		 var flag =$("#flag").val();
		 common.toPage("../dictionaries/books_index?flag="+flag);
		//执行刷新资源列表按钮
		 parent.$("#refresh").click();
	 })
 
})