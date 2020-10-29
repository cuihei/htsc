$(function(){
	var id = $("#id").val();
	/*var mapsNo = $("#mapsNo").val();
	if(id!=""){
		var options='<option value="'+mapsNo+'">'+mapsNo+'</option>';
		$("#mapNo").html(options);
	}
	$("#mapNo").change(function(){
		var mapNo = $("#mapNo").val();
		var catalogTypeId = $("#catalogTypeId").val();
		if(mapNo == "" || mapNo == null || mapNo.indexOf("请选择")>-1){
			   $("#scale").val("");
			   $("#mapName").val("");
			   return;
			}
		common.init("../maps/changeMapNo?mapNo="+mapNo+"&catalogTypeId="+catalogTypeId,"POST",function(result){
			var data = result.value;
			if(data!=undefined){
				 $("#scale").val(data.scale);
				 $("#mapName").val(data.mapName);
			}
		});
		common.do_submit();
	})
	$("#catalogAreaId").change(function(){
		var catalogAreaId = $("#catalogAreaId").val();
		var catalogTypeId = $("#catalogTypeId").val();
		if(catalogAreaId == "" || catalogAreaId == null || catalogAreaId.indexOf("请选择")>-1){
			   var options = '<option value="">--请选择--</option>';
			   $("#mapNo").html(options);
			   $("#scale").val("");
			   $("#mapName").val("");
			   return;
			}
		common.init("../maps/changeCatalogAreaId?catalogAreaId="+catalogAreaId+"&catalogTypeId="+catalogTypeId,"POST",function(result){
			var data = result.value;
			$("#mapNo").empty();
			$("#scale").val("");
			$("#mapName").val("");
			var options = '<option value="">--请选择--</option>'
			if(data!=undefined){
				if(data.length>0){
					$.each(data,function(index,element){
						options+='<option value="'+element.mapNo+'">'+element.mapNo+'</option>';
					});
					$("#mapNo").html(options);
				}
			}
		})
		common.do_submit();
	});
	$("#catalogTypeId").change(function(){
		 var catalogTypeId = $("#catalogTypeId").val();
		 if(catalogTypeId == "" || catalogTypeId == null || catalogTypeId.indexOf("请选择")>-1){
			   $("#catalogAreaId").empty();
			   var options = '<option value="">--请选择--</option>';
			   $("#catalogAreaId").html(options);
			   $("#mapNo").html(options);
			   $("#scale").val("");
			   $("#mapName").val("");
			   return;
			}
		common.init("../maps/changeCatalogType?catalogTypeId="+catalogTypeId,"POST",function(result){
				var data = result.value;
				$("#catalogAreaId").empty();
				$("#mapNo").empty();
				$("#scale").val("");
				$("#mapName").val("");
				var options = '<option value="" selected>--请选择--</option>';
				if(data != undefined){
					if(data.length>0){
						$.each(data,function(index,element){
							options+='<option value="'+element.id+'">'+element.areaName+'</option>';
						}); 
					}
				}
				$("#catalogAreaId").html(options);
				$("#mapNo").html('<option value="" selected>--请选择--</option>');
		});
		common.do_submit();
	 });
	*/
	/** 绑定确定添加按钮的click事件*/
	$("#submit").on("click",function(){
		 // 获取登陆账号参数
		 var mapNo=$("#mapNo").val();
		 var reg = /[0-9A-Za-z]/;
		 if(!reg.test(mapNo)){
			 layer.msg("请输入字母和数字！");
			 return;
		 }
		 var ajax_option={
			 type:"post",
			 url:"../mapsImgUpload/upload?mapNo="+mapNo+"&id="+id,
			 success:function(result){
				 result = eval("("+result+")");
				 if (result.code != 1) {
					 	layer.msg(result.value);
						return;
					}
				 layer.msg("操作成功");
				 common.toPage("../maps/index");
			 } 
		};
		$('#importForm').ajaxSubmit(ajax_option);
		
	 });
	 /** 绑定返回按钮的click事件*/
	 $("#back").on("click",function(){
		 common.toPage("../maps/index");
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
				$("#myImg").attr("height", "130px");
				$("#myImg").attr("width", "300px");
				$("#uploadImg").attr("style","display:none");
			}
		}
	};
	
