var messageHelper = {
	
	readMsgByTypeAndCode : function(type, isEn,code) {
		var msg = null;
		var contextPath = window.location.href;
		var schame = contextPath.split("//")[0];
		contextPath = contextPath.split("//")[1];
		var rootPath = schame + "//" + contextPath.split("/")[0]+"/ht";
		var xmlUrl = rootPath+"/ht/xml/msg.xml";
		$.ajax({
			url : xmlUrl,
			dataType : 'xml',
			type : 'GET',
			async:false,
			timeout : 2000,
			error : function(xml) {
				layer.msg("加载XML文件出错！");
			},
			success : function(xml) {
				$(xml).find("type").each(function(i) {
					var typeX = $(this).attr("type");
					if (typeX == type) {
						$($(xml).find("type")[i]).find("msg").each(function(i) {
							var codeX = $(this).attr("code");
							if (codeX == code) {
								if (isEn) {
									msg = $(this).attr("msgEn");
								}
								else{
									msg = $(this).attr("msgZh");
								}
								return;
							}
						});
					}
					if (msg != null) {
						return;
					}
				});
			}
		});
		return msg;
	}
}

/**
 * 一些常用消息
 */
var msg = {
	/*
	 * 加载成功
	 * 用于刷新列表、进入页面、加载请求等
	 */
	LOAD_SUCCESS : messageHelper.readMsgByTypeAndCode("load",false,"1"),
	
	/*
	 * 加载失败
	 * 用于刷新列表、进入页面、加载请求等
	 */
	LOAD_FAIL : messageHelper.readMsgByTypeAndCode("load",false,"0"),
	
	/*
	 * 添加成功
	 * 用于添加成功后
	 */
	ADD_SUCCESS : messageHelper.readMsgByTypeAndCode("add",false,"1"),
	
	/*
	 * 添加失败
	 * 用于添加失败后
	 */
	ADD_FAIL : messageHelper.readMsgByTypeAndCode("add",false,"0"),
	
	/*
	 * 编辑成功
	 * 用于编辑成功后
	 */
	EDIT_SUCCESS : messageHelper.readMsgByTypeAndCode("edit",false,"1"),
	
	/*
	 * 编辑成功
	 * 用于编辑失败后
	 */
	EDIT_FAIL : messageHelper.readMsgByTypeAndCode("edit",false,"0"),
	
	/*
	 * 删除成功
	 * 用于删除成功后
	 */
	REMOVE_SUCCESS : messageHelper.readMsgByTypeAndCode("remove",false,"1"),
	
	/*
	 * 删除失败
	 * 用于删除失败后
	 */
	REMOVE_FAIL : messageHelper.readMsgByTypeAndCode("remove",false,"0"),
}