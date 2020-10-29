$(function(){
  var userAgent = navigator.userAgent;
	    var isOpera = userAgent.indexOf("Opera") > -1;
	    var isMaxthon = userAgent.indexOf("Maxthon") > -1 ;
	    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera ;
	    var isFF = userAgent.indexOf("Firefox") > -1 ;
	    var isSafari = userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") < 1 ;
	    var isChrome = userAgent.indexOf("Chrome") > -1 ;
	    var isIE5 = isIE55 = isIE6 = isIE7 = isIE8 = false;
	     
	   // if(isFF){document.write("当前浏览器是  Firefox，请用谷歌浏览器，登陆系统。");}
	    //if(isMaxthon){document.write("当前浏览器是 傲游(webkit核心)，请用谷歌浏览器，登陆系统。");}
	    if(isOpera){document.write("当前浏览器是  Opera，请用谷歌浏览器，登陆系统。");}
	    if(isSafari){document.write("当前浏览器是  Safari，请用谷歌浏览器，登陆系统。");}
	    //if(isChrome){document.write("当前浏览器是  Chrome");}
	    if(isIE){document.write("当前浏览器是  IE，请用谷歌浏览器，登陆系统。");}

	
	login.index();
	
	
})

var login = {
	
	index : function(){
		//为keyListener方法注册按键事件
		document.onkeydown=keyListener;
		function keyListener(e){
		 // 当按下回车键，执行我们的代码
		 if(e.keyCode == 13){
			 $('#login').click();
		 }
		}
			
		$("#login").on("click",function(){
			var userNo = $("#userNo").val();
			var password = $("#pwd").val();
			if (userNo == null || userNo == "") {
				layer.msg(messageHelper.readMsgByTypeAndCode("login",false,"2"));
				return;
			}
			if (password == null || password == "") {
				layer.msg(messageHelper.readMsgByTypeAndCode("login",false,"3"));
				return;
			}
			common.init("../index/validation","POST",function(result){
				if (result.code == 0) {
					layer.msg(result.value);
					return;
				}
				layer.msg(messageHelper.readMsgByTypeAndCode("login",false,"1"));
				common.toPage("../index/init");
			});
			common.add_param("userNo",userNo);
			var data = common.add_param("password",password);
			common.do_submit(data);
		});
	},
}