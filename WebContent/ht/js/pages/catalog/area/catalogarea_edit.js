$(function(){
	var id = $("#id").val();
	/** 绑定确定添加按钮的click事件*/
	$("#submit").on("click",function(){
		 // 获取登陆账号参数
		 var catalogArea = {};
		 var baseData = {};
		 catalogArea.areaName = $("#areaName").val();
		 catalogArea.id = id;
		 baseData.id = $("#typeId").val();
		 if(baseData.id=="--请选择--"){
			 layer.msg("请选择目录类型");
				return;
		 }
		 if(catalogArea.areaName==""){
			 layer.msg("请输入目录区域名称");
				return;
		 }
		 catalogArea.baseData=baseData;
		 var catalogAreaJson = JSON.stringify(catalogArea);
		 var param = common.add_param("catalogArea",catalogAreaJson);
		 var ajax_option={
			 type:"post",
			 url:"../../areaImgUpload/upload",
			 data:param,
			 success:function(result){
				 result = eval("("+result+")");
				 if (result.code != 1) {
					 	layer.msg(result.value);
						return;
					}
				 layer.msg("操作成功");
				 common.toPage("../catalog/area/index");
			 } 
		};
		$('#importForm').ajaxSubmit(ajax_option);
		
	 })
	 /** 绑定返回按钮的click事件*/
	 $("#back").on("click",function(){
		 common.toPage("../catalog/area/index");
		 parent.$("#refresh").click();
	 })
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