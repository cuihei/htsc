$(function(){
	coe_edit.init();
	$("#scale").attr("onkeyup","value=(parseInt((value=value.replace(/[^\\d]/g,''))==''?'0':value,10))");
	$("#scale").attr("onafterpaste","value=(parseInt((value=value.replace(/[^\\d]/g,''))==''?'0':value,10))");
 var coid=$("#coeId").val();
if(coid.length<1){
	//清空类型选择框
	$("#type").empty();
	$("#type").append("<option value=''>--禁止选择类型--</option>"); 
}

///查询是否存在该图号
$("#mapNo").on('blur',function(){
var	mapno=$(this).val();
	$.ajax({
		url : "../coefficient/mapNoexi", //请求路径
		type : "post", //提交方式
		data : {       //参数以json格式拼接在url后
			"mapno":mapno, 
		},
		dateType : "json", //预计返回的数据类型
		success : function(dataObj) {
			
	    	$("#zhtfirstEdition").removeAttr("readonly");$("#zhtreprint").removeAttr("readonly");
			$("#zhtfirstEdition").attr("placeholder","请输入纸海图 首版");$("#zhtreprint").attr("placeholder","请输入纸海图 首版");
			$("#dzhtfirstEdition").removeAttr("readonly");$("#dzhtreprint").removeAttr("readonly");
			$("#dzhtfirstEdition").attr("placeholder","请输入电子海图 首版");$("#dzhtreprint").attr("placeholder","请输入电子海图 首版");
			$("#ysjfirstEdition").removeAttr("readonly");$("#ysjreprint").removeAttr("readonly");
			$("#ysjfirstEdition").attr("placeholder","请输入源数据 首版");$("#ysjreprint").attr("placeholder","请输入源数据 首版");

			var result=eval("("+dataObj+")");//转换为json对象 
			var val=result.value;
			// val:1106193540120002 纸海图   11061936168310003 电子海图  11061935001990001 源数据
			if(result.code==1){
				if(val.indexOf("1106193540120002") >= 0 ) { 
		
					$("#zhtfirstEdition").attr("readonly","readonly");$("#zhtreprint").attr("readonly","readonly");
					$("#zhtfirstEdition").attr("placeholder","已存在");$("#zhtreprint").attr("placeholder","已存在");
				}
				if(val.indexOf("11061936168310003") >= 0 ) { 
					$("#dzhtfirstEdition").attr("readonly","readonly");$("#dzhtreprint").attr("readonly","readonly");
					$("#dzhtfirstEdition").attr("placeholder","已存在");$("#dzhtreprint").attr("placeholder","已存在");
				}
				if(val.indexOf("11061935001990001") >= 0 ) { 
					$("#ysjfirstEdition").attr("readonly","readonly");$("#ysjreprint").attr("readonly","readonly");
					$("#ysjfirstEdition").attr("placeholder","已存在");$("#ysjreprint").attr("placeholder","已存在");
				}
			
				
				
			}
			
	
		},

	});
});







})

var coe_edit = {
	/**
	 * 初始化
	 */
	init : function(){
		coe_edit.bindPageEvent();
	},
	
	editSuccess : function(result){
		if(result.code != 1){
			if(result.value != "" || result.value != null){
				layer.msg(result.value);
				return;
			}
			layer.msg("保存失败！");
			return;
		}
		 layer.msg("保存成功！");
		 //返回目录主页面
		 common.toPage("../coefficient/init");
		 //执行刷新用户列表按钮
		 parent.$("#refresh").click();
	},
	addSuccess : function(result){
		if(result.code == 1){
				
					if(result.value==1){
						 layer.msg("保存成功！");
						setTimeout(function () {common.toPage("../coefficient/init"); }, 2000)
						}
		

		}else if(result.value==0){
			 layer.msg("保存失败，请联系管理员！");
				return;
					}

	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定提交按钮的click事件*/
		$("#submit").on("click",function(){

			 var coe = {};
			 var type = {};
			 var type_id = ["1106193540120002", "11061936168310003","11061935001990001"];
			 coe.id = $("#coeId").val();
			 coe.mapNo = $("#mapNo").val();
			 coe.mapName = $("#mapName").val();
			 coe.scale = $("#scale").val();
			
			 if(coe.id.length>0){
				 layer.msg("修改！");
				 //纸海图
				 coe.id = $("#zhtcoeId").val();
				 type.id = $("#zhttype").val();
				 coe.type = type;
				 coe.firstEdition = $("#zhtfirstEditionalter").val();
				 coe.reprint = $("#zhtreprintalter").val();
				 var coeJson = JSON.stringify(coe);
				 var param = common.add_param("coe",coeJson);
				 common.init("../coefficient/add","POST",coe_edit.editSuccess); 
				 common.do_submit(param);
				 //源数据
				 coe.id = $("#ysjcoeId").val();
				 type.id = $("#ysjtype").val();
				 coe.type = type;
				 coe.firstEdition = $("#ysjfirstEditionalter").val();
				 coe.reprint = $("#ysjreprintalter").val();
				 var coeJson = JSON.stringify(coe);
				 var param = common.add_param("coe",coeJson);
				 common.init("../coefficient/add","POST",coe_edit.editSuccess); 
				 common.do_submit(param);
				 //电子海图
				 coe.id = $("#dzhtcoeId").val();
				 type.id = $("#dzhttype").val();
				 coe.type = type;
				 coe.firstEdition = $("#dzhtfirstEditionalter").val();
				 coe.reprint = $("#dzhtreprintalter").val();
				 var coeJson = JSON.stringify(coe);
				 var param = common.add_param("coe",coeJson);
				 common.init("../coefficient/add","POST",coe_edit.editSuccess); 
				 common.do_submit(param);
				 
			 } else{
				
				 if($("#mapNo").val().length<1){layer.msg("图号 不可为空！"); return;}	
				 if($("#mapName").val().length<1){layer.msg("图名 不可为空！"); return;}	
				 if($("#scale").val().length<1){layer.msg("比例尺不可为空！"); return;}	
						 var zht1=$("#zhtfirstEdition").val().length;var zht2=$("#zhtreprint").val().length; var zhta=$("#zhtfirstEdition").attr("placeholder");var zhtb=$("#zhtreprint").attr("placeholder");
						 var dzht1=$("#dzhtfirstEdition").val().length;var dzht2=$("#dzhtreprint").val().length; var dzhta=$("#dzhtfirstEdition").attr("placeholder");var dzhtb=$("#dzhtreprint").attr("placeholder");
						 var ysj1=$("#ysjfirstEdition").val().length;var ysj2=$("#ysjreprint").val().length; var ysja=$("#ysjfirstEdition").attr("placeholder");var ysjb=$("#ysjreprint").attr("placeholder");
						  if(zht1<1&&zht2>1){layer.msg("纸海图首版不可为空！");return;}				    if(zht1>1&&zht2<1){layer.msg("纸海图再版不可为空！");return;}
						  if(dzht1<1&&dzht2>1){layer.msg("电子纸海图首版不可为空！");return;}		if(dzht1>1&&dzht2<1){layer.msg("电子纸海图再版不可为空！");return;}
						  if(ysj1<1&&ysj2>1){layer.msg("源数据首版不可为空！"); return;}					if(ysj1>1&&ysj2<1){layer.msg("源数据再版不可为空！");return;}
					//	  if(zht1<1&&zht2<1&&dzht1<1&&dzht2<1&&ysj1<1&&ysj2<1){layer.msg("难度系数不可为空！"); return;}		
						  
						  
						  
							if(zht1<1&&zht2<1&&zhta=="已存在"&&zhtb=="已存在"){
							}else{
							 coe.firstEdition =$("#zhtfirstEdition").val();
							 coe.reprint =$("#zhtreprint").val();	
							 type.id="1106193540120002";
							  coe.type = type;
							 var coeJson = JSON.stringify(coe);
							 var param = common.add_param("coe",coeJson);
							 common.init("../coefficient/add","POST",coe_edit.addSuccess); 	
							 common.do_submit(param);
							}
								
										if(dzht1<1&&dzht2<1&&dzhta=="已存在"&&dzhtb=="已存在"){
								}else{
							 coe.firstEdition =$("#dzhtfirstEdition").val();
							 coe.reprint =$("#dzhtreprint").val();
							 type.id= "11061936168310003";
							  coe.type = type;
							 var coeJson = JSON.stringify(coe);
							 var param = common.add_param("coe",coeJson);
							 common.init("../coefficient/add","POST",coe_edit.addSuccess); 	
							 common.do_submit(param);
								} 
				
							

								if(ysj1<1&&ysj2<1&&ysja=="已存在"&&ysjb=="已存在"){
								}else{
							 coe.firstEdition =$("#ysjfirstEdition").val();
							 coe.reprint =$("#ysjreprint").val();		
						   	type.id="11061935001990001";
							  coe.type = type;
							 var coeJson = JSON.stringify(coe);
							 var param = common.add_param("coe",coeJson);
							 common.init("../coefficient/add","POST",coe_edit.addSuccess); 	
							 common.do_submit(param);
						 };				 
			 }
					 
			
		});
		
		/** 绑定返回按钮的click事件*/
		 $("#back").on("click",function(){
			 window.history.go(-1);
		 });
	}
}