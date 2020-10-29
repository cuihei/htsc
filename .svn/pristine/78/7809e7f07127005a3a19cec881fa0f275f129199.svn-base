$(function(){
	ftp.init();
	$("#submit").on("click",function(){
		$('#importForm').submit();
	 })
});
	

var ftp = {
	/**
	 * 初始化
	 */
	init : function(){
		/** 上传文件点击事件 */
	    $("#fjs").kendoUpload({
	    	async: {
	            saveUrl: "save",
	            removeUrl: "remove",
	            autoUpload: true
	        }
	    });
	}
}