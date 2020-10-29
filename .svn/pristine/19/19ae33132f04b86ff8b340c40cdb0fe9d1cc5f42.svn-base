$(function(){
	var id = $("#id").val();
	var code = $("#code").val();
	$("#code").blur(function(){
		code = $("#code").val();
		 $.ajax({
	 			type :"POST",
	 			url :"../symbol/checkCode",
	 			dataType : "json",
	 			async : false,
	 			data :  "code="+code,
	 			success : function(data){
	 				if(data.value=="false"){
	 					 layer.msg("特征码已存在");
	 					$("#code").val("");
	 				}
	 				
				},
	 			error : function(err){
					   console.log(err.responseText);
				}
	 		});
	});
	/** 绑定确定添加按钮的click事件*/
	$("#submit").on("click",function(){
		 // 获取登陆账号参数
		 var code = $("#code").val();
		 $.ajax({
	 			type :"POST",
	 			url :"../symbol/checkCode",
	 			dataType : "json",
	 			async : false,
	 			data :  "code="+code,
	 			success : function(data){
	 				if(data.value=="false"){
	 					 layer.msg("特征码已存在");
	 					$("#code").val("");
	 				}
	 				
				},
	 			error : function(err){
					   console.log(err.responseText);
				}
	 		});
		 code = encodeURIComponent(code);
		 if(code=="" || code==null){
			 layer.msg("填写特征码");
				return;
		 }
		 var fileType=document.getElementById("myfiles").value.substr(document.getElementById("myfiles").value.lastIndexOf(".")).toLowerCase();
		 if(document.getElementById("myfiles").value==""){
			 if(document.getElementById("uploadImg").src=="../ht/upload/images/uploadpic.png"){
				 layer.msg("请上传图片");
				 return;
			 }
		 }else if(fileType != '.gif' && fileType != '.png' && fileType != '.jpg' && fileType != '.jpeg'){
			 layer.msg("请上传图片");
			 return;
		 }
		 var ajax_option={
			 type:"post",
			 url:"../symbolImgUpload/upload?code="+code+"&id="+id,
			 success:function(result){
				 result = eval("("+result+")");
				 if (result.code != 1) {
					 	layer.msg(result.value);
						return;
					}
				 layer.msg("操作成功");
				 common.toPage("../symbol/index");
			 } 
		};
		$('#importForm').ajaxSubmit(ajax_option);
		
	 });
	 /** 绑定返回按钮的click事件*/
	 $("#back").on("click",function(){
		 common.toPage("../symbol/index");
		 parent.$("#refresh").click();
	 });

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
//				$("#myImg").attr("height", "100px");
//				$("#myImg").attr("width", "110px");
				$("#uploadImg").attr("style","display:none");
			}
		}
	};
	
