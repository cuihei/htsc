$(function(){
	editauditrole.init();
})

var editauditrole = {
	/**
	 * 初始化
	 */
	init : function(){
		editauditrole.bindPageEvent();
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		$("#submit").on("click",function(){
			 var id=$("#id").val();
			 var name=$("#name").val();
			 var auditorl={};
			 auditorl.id=id;
			 auditorl.name=name;
			 var params = JSON.stringify(auditorl);
			 var data = common.add_param("name",params);
			 common.init("../auditrole/add","POST",function(result){
				//调回用户列表显示页面
				 common.toPage("../auditrole/index");
			 });
			 common.do_submit(data);
		});
		
		/** 绑定取消按钮的click事件*/
		 $("#back").on("click",function(){
			//调回用户列表显示页面
			 common.toPage("../auditrole/index");
		 })
	}
}