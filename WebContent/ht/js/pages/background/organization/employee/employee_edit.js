$(function(){
	employee_edit.init();
})
function onclic(){
	$("#myfiles").click();
};

function changeImg() {
	for (var i = 0; i < event.target.files.length; i++) {
		var file = event.target.files.item(i);
		if (!(/^image\/.*$/i.test(file.type))) {
			//不是图片 就跳出这一次循环
			continue;  
		}
		//实例化FileReader API  
		var freader = new FileReader();
		freader.readAsDataURL(file);
		freader.onload = function() {
			$("#myImg").attr("src", event.target.result);
			$("#myImg").attr("height", "130px");
			$("#myImg").attr("width", "300px");
			$("#uploadImg").attr("style","display:none");
		}
	}
};



var employee_edit = {
	/**
	 * 初始化
	 */
	init : function(){
		try{
			//创建用户列表
			employee_edit.initMultiselect();
			employee_edit.setMultiSeleteData();
			//绑定页面事件
			employee_edit.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	checkPassword : function(password){
		var regUpper = /[A-Z]/;
		var regLower = /[a-z]/;
		var regNum = /[0-9]/;
		var regStr = /[^A-Za-z0-9]/;
		var complex = 0;
		if (regLower.test()) {
			++complex;
		}
		if (regUpper.test(password)) {
			++complex;
		}
		if (regNum.test(password)) {
			++complex;
		}
		if (regStr.test(password)) {
			++complex;
		}
		if (complex < 3 || password.length < 8) {
			return false;
		}
		return true;
	},
	
	checkUserName : function(userName) {
	    var re = /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/gi;//只能输入汉字和英文字母和数字
	    if (re.test(userName)) {
	        return true;
	    } else {
	        return false;
	    }
	},
	
	/**
	 * 初始化多选控件
	 */
	initMultiselect : function(){
		// 图层多选
		$("#roleId").kendoMultiSelect({
	        placeholder: "请选择所属角色",
	        dataTextField: "roleName",
	        dataValueField: "id",
	        autoBind: false,
	    });
	},
	
	/**
	 * 设置多选框的数据，并刷新
	 */
	setMultiSeleteData : function(){
		var roles = $("#roles").val();
		groupIds = [];
		for (var int = 0; int < roles.split(",").length; int++) {
			groupIds.push(roles.split(",")[int]);
		}
		var multiselect = $("#roleId").data("kendoMultiSelect");
		multiselect.value(groupIds);
		multiselect.refresh();
	},
	
	chkHalf: function (str){  
		 for (var i = 0; i < str.length; i++) {
			strCode = str.charCodeAt(i);
			if ((strCode > 65248) || (strCode == 12288)) {
				return true;
			}
		}
		 return false;
	} ,
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定确定添加按钮的click事件*/
		$("#submit").on("click",function(){
			 // 获取登陆账号参数
			 var user = {};
			 user.id = $("#userId").val();
			 var userNo = $("#userNo").val()
			 if(employee_edit.chkHalf(userNo)){
				 layer.msg("不能输入全角符号！");
				 return;
			 }
			 // 获取登陆账号参数
			 user.userNo = $("#userNo").val();
			 if($("#userNo").val() == ""){
				 layer.msg("请输入用户登录账号！");
				 return;
			 }
			 // 获取用户名称
			 user.userName = $("#userName").val();
			 if($("#userName").val() == ""){
				 layer.msg("请输入用户名称！");
				 return;
			 }
			 // 校验登陆账号
			 if(employee_edit.checkUserName($("#userNo").val()) == false){
				 layer.msg("登录账号不允许输入特殊符号，只可输入汉字,英文字母和数字");
				 return;
			 }
			 // 校验用户名
			 if(employee_edit.checkUserName($("#userName").val()) == false){
				 layer.msg("用户名称不允许输入特殊符号，只可输入汉字,英文字母和数字");
				 return;
			 }
			 // 获取用户密码
			 user.password = $("#password").val();
			 var password = $("#password").val();
			 var newPassword = $("#newpassword").val();
			 if (password == "") {
					layer.msg("请输入用户密码！");
					return;
			}
			if (newPassword == "") {
				layer.msg("请确认密码！");
				return;
			}
			if (password != newPassword) {
				layer.msg("两次密码不一致！");
				return;
			}
			/*if(employee_edit.checkPassword(password) == false){
				layer.msg("密码长度至少有 8 个字符；至少包含以下四类字符中的三类：大写字母、小写字母、数字、以及特殊符号（如 !、@、#）");
				return;
			}*/
			 // 所属组织
			 user.orgId = $("#orgId").val();
			 // 所属角色
			 var roleId = "";
			 var dataItem = $("#roleId").data("kendoMultiSelect").dataItems();
				$.each(dataItem,function(i,item){
					roleId += ','+item.id;
				});
			 var executeRoleId=roleId.substring(1);
			 var userJson = JSON.stringify(user);
			 var param = common.add_param("user",userJson);
			 common.add_param("roleId",executeRoleId);
			 var ajax_option={
				 type:"post",
				 url:"../areaImgUpload/uploaduser",
				 data:param,
				 success:function(result){
					 result = eval("("+result+")");
					 if (result.code == 0) {
							if(result.value !="" && result.value != null){
								layer.msg(result.value);
								return;
							}else{
								layer.msg("用户已存在，请重写！");
								return;
							}
					  }else{
						  if(result.value =="userNo"){
								layer.msg("您输入的登录名过长，请重新输入");
								return;
							}else if(result.value =="userName"){
								layer.msg("您输入的用户名过长，请重新输入");
								return;
							}else if(result.value =="exit"){
								layer.msg("用户已存在，请重写");
								return;
							}
					  }
					 layer.msg("操作成功");
					 common.toPage("../user/index");
				 } 
			};
			$('#importForm').ajaxSubmit(ajax_option);
			
		 })
		 /** 绑定返回按钮的click事件*/
		 $("#back").on("click",function(){
			 common.toPage("../user/index");
			 parent.$("#refresh").click();
		 })
	}
}