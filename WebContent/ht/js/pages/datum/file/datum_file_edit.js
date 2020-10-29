$(function(){
	common.init("../datumCategory/tree", "POST", function(result) {
		treeView.init("select", function(e) {
			if (e.node != null) {
				var data = treeView.getData(e.node);
				var categoryName = data.name;
				$("#dCataParentName").val(categoryName);
			}
			
		}, result.value);
		treeView.bindTree();
	});
	common.do_submit();
	
	
	//将资料类别元素设置为readonly
	$("#parentId").attr("disabled",true);
	// 文件类别change事件
	$("#FileTypeId").on("change",function(){
		// 判断文件类别是否是电子文件，显示不同的输入框
		if($("#FileTypeId").val() == "电子文档"){
			// 获取最外层父节点
			var uploadFile = $("#uploadFile").parent().parent().attr("class");
			
			$("."+uploadFile).eq(0).css("display","");
			$("."+uploadFile).eq(1).css("display","");
			$("."+uploadFile).eq(2).css("display","");
			$("."+uploadFile).eq(3).css("display","");
			$("."+uploadFile).eq(4).css("display","");
			$("."+uploadFile).eq(5).css("display","none");
			$("."+uploadFile).eq(6).css("display","none");
			$("."+uploadFile).eq(7).css("display","");
		}else {
			var uploadFile = $("#uploadFile").parent().parent().attr("class");
			
			$("."+uploadFile).eq(0).css("display","");
			$("."+uploadFile).eq(1).css("display","");
			$("."+uploadFile).eq(2).css("display","");
			$("."+uploadFile).eq(3).css("display","none");
			$("."+uploadFile).eq(4).css("display","none");
			$("."+uploadFile).eq(5).css("display","");
			$("."+uploadFile).eq(6).css("display","");
			$("."+uploadFile).eq(7).css("display","");
		}
	});
	
	 /** 绑定选择父资料类别确定按钮的click事件*/
	$("#importSubmit").on("click",function(){
		$("#parentId option").each(function(){
			if($(this).text() == $("#dCataParentName").val()){
				$(this).attr("selected","selected");
			}
		});
		$("#myModal").hide();
	});
	
	
	/** 绑定确定添加按钮的click事件*/
	$("#submit").on("click",function(){
		 var datumFile = {};
		 // 获取文件夹Id
		 datumFile.categoryId = $("#parentId").val();
		 // 获取文档类型
		 var fileType = $("#FileTypeId").val();
		 if(fileType == "--请选择--") {
			 layer.msg("请选择文档类型！",{icon:2,time:1500});
			 return false;
		 }else {
			 datumFile.fileType = fileType;
		 }
		 // 获取文件名称
		 datumFile.fileName = $("#fileName").val();
		 // 获取备注信息
		 datumFile.remarks = $("#remarks").val();
		 // 获取实体文件名称
		 datumFile.entityFileName = $("#entityFileName").val();
		 // 获取实体数量
		 datumFile.entityFileNum = $("#entityFileNum").val();
		 var datumFileJson = JSON.stringify(datumFile);
		 var param = common.add_param("datum",datumFileJson);
		 var ajax_option={
				 type:"post",
				 url:"../areaImgUpload/uploadfile",
				 data:param,
				 success:function(result){
					 layer.msg("操作成功",{icon:6,time:10000});
					 common.toPage("../datumFile/index");
				 } 
			};
			$('#importForm').ajaxSubmit(ajax_option);
		
	 })
	 
	 /** 绑定返回按钮的click事件*/
	 $("#back").on("click",function(){
		 window.history.go(-1);
	 })
})