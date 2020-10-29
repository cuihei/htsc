$(function(){
	
	 /** 绑定返回按钮的click事件*/
	 $("#submit").on("click",function(){
		history.back();
	 })
	
	 /** 绑定返回按钮的click事件*/
	 $("#back").on("click",function(){
		history.back();
	 })
})
	function onclic(obj){
		//$("#formImgUpload").click();
		var i =  obj.id.substring(obj.id.length-1);
		$("#formImgUpload"+i).click();
	};
	
	function changeImg(obj) {
		var j =  obj.id.substring(obj.id.length-1);
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
				$("#myImg"+j).attr("src", event.target.result);
				$("#myImg"+j).attr("height", "130px");
				$("#myImg"+j).attr("width", "300px");
				$("#upload"+j).attr("style","display:none");
			}
		}
	};