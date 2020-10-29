$(function(){
	var formId = $("#formId").val();
	$("#back").on("click",function(){
		var formId = $("#formId").val();
		common.toPage("../form/index");
	});
	$("#open").on("click",function(){
		$("#formID").val(formId);
		$("#myModal").modal('show');
	});
	$("#introduce").click(function(){
		common.toPage("../docTempleteAction/download");
	});
	
	$("#templeteSubmit").on("click",function(){
		$("#importForm").ajaxSubmit({  
            type: 'post',  
            url: "../docTempleteAction/save" ,  
            beforeSubmit: function() { 
            	return true;
            } ,  
            success: function(result){
            	layer.msg("上传成功"); 
            	$("#doctemplete_show").attr("src","..\\upload\\docTemplete\\"+formId+".pdf");
//            	setTimeout(function() {
//					window.location.href="../taskbook/index";
//				},1500); 
            },  
            error: function(XmlHttpRequest, textStatus, errorThrown){
            	layer.msg("上传失败");  
            }  
        });  
	});
	
	
})
