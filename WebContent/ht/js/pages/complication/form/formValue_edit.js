$(function(){
	/** 绑定确定添加按钮的click事件*/
	$("#submit").on("click",function(){
		var props;
		var taskInstId = $("#taskInstId").val();
		var processInstId = $("#processInstId").val();
		var jsonProp = $("#props").val();
		common.add_param("taskInstId",taskInstId);
		common.add_param("processInstId",processInstId);
		if(typeof jsonProp == 'object'){
		    props = jsonProp;
		}
		else{
			props = eval("("+jsonProp+")");
		}
		for(var item in props){
			if(props[item].required == "是"){
				
			}
		}
		var param = common.add_param("props",props);
		var ajax_option={
			 type:"post",
			 url:"../areaImgUpload/UploadForm",
			 data:param,
			 success:function(data){
				if (data.code == 0) {
					layer.msg(data.value);
					return;
				}
				layer.msg("保存成功");
				history.back()
			 }
		};
		$('#importForm').ajaxSubmit(ajax_option);
		return false; 
	 });
	 
	/** 绑定确定多次添加按钮的click事件*/
	$("#add").on("click",function(){
		var props;
		var taskInstId = $("#taskInstId").val();
		var processInstId = $("#processInstId").val();
		var jsonProp = $("#props").val();
		common.add_param("taskInstId",taskInstId);
		common.add_param("processInstId",processInstId);
		if(typeof jsonProp == 'object'){
		    props = jsonProp;
		}
		else{
			props = eval("("+jsonProp+")");
		}
		for(var item in props){
			var propKey = props[item].propKey;
			var prop = $('#propKey').val();
			if( prop == ""){
		 		layer.msg("请输入"+props[item].propName);
		 		return;
			}
		}
		var param = common.add_param("props",props);
		var ajax_option={
			 type:"post",
			 url:"../areaImgUpload/UploadForm",
			 data:param,
			 success:function(data){
				if (data.code == 0) {
					layer.msg(data.value);
					return;
				}
				layer.msg("保存成功");
			 }
		};
		$('#importForm').ajaxSubmit(ajax_option);
		return false; 
	 });
	
	 /** 绑定返回按钮的click事件*/
	 $("#back").on("click",function(){
		history.back();
	 })
})
	function onclic(){
		$("#formImgUpload").click();
	};
	
	function changeImg() {
		for (var i = 0; i < event.target.files.length; i++) {

			//alert(event.target.files.length)
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
				$("#upload").attr("style","display:none");
			}
		}
	};