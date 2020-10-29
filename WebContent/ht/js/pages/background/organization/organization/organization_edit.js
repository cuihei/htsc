$(function(){
	organization_edit.init();
})

var organization_edit = {
	/**
	 * 初始化
	 */
	init : function(){
		$("#parentId").attr("disabled","disabled")//将元素设置为readonly
		organization_edit.bindPageEvent();
		organization_edit.bindTreeView();
	},
	
	/**
	 * 绑定树视图
	 */
	bindTreeView : function(){
		common.init("../org/orgtree", "POST", function(result) {
			treeView.init("select", function(e) {
				if (e.node != null) {
					var data = treeView.getData(e.node);
					var parentName = data.name;
					$("#orgParentName").val(parentName);
				}
			}, result.value);
			treeView.bindTree();
		});
		common.do_submit();
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定提交增加组织机构按钮的click事件*/
		 $("#submit").on("click",function(){
			 var org = {};
			 org.id = $("#orgId").val();
			// 获取父机构Id参数
			 org.parentId = $("#parentId").val();
			 if(org.parentId == "--请选择--"){
				 org.parentId = "";
			 }
			 // 获取机构编号参数
			/* org.orgNo = $("#orgNo").val();
			 if(org.orgNo == ""){
				 layer.msg("请输入组织机构编号");
				 return;
			 }*/
			 // 获取机构名称
			 org.orgName = $("#orgName").val();
			 if(org.orgName == ""){
				 layer.msg("请输入组织机构名称");
				 return;
			 }
			 //将数组转化成JSON数据
			 var orgJson = JSON.stringify(org);
			 var param = common.add_param("org",orgJson);
			 common.init("../org/add","POST",function(result){
				 //调回机构列表显示页面
				 if (result.code == 0) {
						if(result.value !="" && result.value != null){
							layer.msg(result.value);
							return;
						}else{
							layer.msg("该组织机构已存在，请重写！");
							return;
						}
				  }
				 layer.msg("操作成功");
				 common.toPage("../org/index");
				 //执行刷新用户列表按钮
				 parent.$("#refresh").click();
			 });
			 common.do_submit(param);
		 });

		 /** 绑定取消按钮的click事件*/
		 $("#back").on("click",function(){
			//调回机构列表显示页面
			 common.toPage("../org/index");
			//执行刷新机构列表按钮
			 parent.$("#refresh").click();
		 });
		 
		 /** 绑定选择父机构确定按钮的click事件*/
		 $("#importSubmit").on("click",function(){
			 $("#parentId option").each(function(){
				 // 如果是公共部门，则不能添加子部门
				 if($("#orgParentName").val() == "公共部门"){
					 layer.msg("公共部门下不能添加子机构");
					 return;
				 }else if($("#orgParentName").val() == ""){
					 if($(this).text()=="组织机构"){
	        			 $(this).attr("selected","selected");
	        		}
				 }else{
					 if($(this).text()==$("#orgParentName").val()){
	        			 $(this).attr("selected","selected");
	        		}
				 }
	         });
			 $("#myModal").hide();
		 });
	}
}