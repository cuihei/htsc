$(function(){
	role_edit.init();
})

var role_edit = {
	/**
	 * 初始化
	 */
	init : function(){
		role_edit.bindPageEvent();
		var roleId = $("#roleId").val();
		if(roleId == '11031901469110099'){
			$("#back").text("取消");
		}
	},
	
	/**
	 * 修改或新增成功
	 * */
	editSuccess : function(result){
		 if(result.code == 0){
			 layer.msg(result.value);
			 return;
		 }else{
			 var roleId = $("#roleId").val();
			 if(roleId != null){
				 layer.msg('修改成功!',{icon:1,time:6000});
				 common.toPage("../role/index");
			 }else{
				 layer.msg('新增成功!',{icon:1,time:6000});
				 common.toPage("../role/index");
			 }
		 }
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		$("#submit").on("click",function(){
			 var role = {};
			 role.id = $("#roleId").val();
			 // 获取登陆账号参数
			 role.roleNo = $("#roleNo").val();
			 
			 // 获取用户名称
			 role.roleName = $("#roleName").val();
			 var roleJson = JSON.stringify(role);
			 var param = common.add_param("role",roleJson);
			 common.init("../role/edit","POST",role_edit.editSuccess);
			 common.do_submit(param);
		});
		
		/** 绑定取消按钮的click事件*/
		 $("#back").on("click",function(){
			//调回用户列表显示页面
			 common.toPage("../role/index");
			//执行刷新用户列表按钮
			 parent.$("#refresh").click();
		 })
	}
}