$(function(){
	pusd_edit.init();
})

var pusd_edit = {
	/**
	 * 初始化
	 */
	init : function(){
		$("#sourcedata_problem").attr('placeholder','请输入源数据问题');
		pusd_edit.initMultiselect();
		pusd_edit.setMultiSeleteData();
		pusd_edit.bindPageEvent();
	},
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		// 判断是否只选了一个图号
		$("#submit").on("click",function(){
			
			var drawNum = $("#draw").prev().find('li').length;
			if(drawNum > 1){
				layer.msg("最多只能选择一个图号");
			}else if(drawNum == 0){
				layer.msg("请选择一个图号");
			}else {
				layer.confirm('该操作会根据图号挂起流程，确定执行吗？',function(index){
					var productupdsourcedata = {};
					// 图号
					var draw = "";
					var dataItem = $("#draw").data("kendoMultiSelect").dataItems();
					$.each(dataItem,function(i,item){
						draw += ','+item.id;
					});
					// 获取图名
					productupdsourcedata.figure_caption = $("#figure_caption").val();
					// 获取编绘员
					productupdsourcedata.compiler = $("#compiler").val();
					// 获取质检员
					productupdsourcedata.quality_inspector = $("#quality_inspector").val();
					// 获取审定员
					productupdsourcedata.authorized_member = $("#authorized_member").val();
					// 源数据问题
					if($("#sourcedata_problem").val() == "请输入问题"){
						productupdsourcedata.sourcedata_problem = "";
					}else {
						productupdsourcedata.sourcedata_problem = $("#sourcedata_problem").val();
					}
					
					var pusdJson = JSON.stringify(productupdsourcedata);
					var param = common.add_param("productupdsourcedata",pusdJson);
					common.add_param("draw",draw.substring(1));
					common.init("../product/add","POST",function(result){
						//调回列表显示页面
						if (result.code == 0) {
							if(result.value !="" && result.value != null){
								layer.msg(result.value);
								return;
							}else{
								layer.msg("保存失败！");
								return;
							}
						}
						common.toPage("../product/index");
						//执行刷新列表按钮
						parent.$("#refresh").click();
					});
					common.do_submit(param);
				});
			}
			
		});

		/** 绑定取消按钮的click事件*/
		$("#back").on("click",function(){
			//调回用户列表显示页面
			common.toPage("../product/index");
			//执行刷新用户列表按钮
			parent.$("#refresh").click();
		});
	},
	
	/**
	 * 初始化多选控件
	 */
	initMultiselect : function(){
		// 图层多选
		$("#draw").kendoMultiSelect({
	        placeholder: "请选择图号",
	        dataTextField: "value",
	        dataValueField: "id",
	        autoBind: false,
	    });
	},
	
	/**
	 * 设置多选框的数据，并刷新
	 */
	setMultiSeleteData : function(){
		var draws = $("#draws").val();
		draw = [];
		for (var int = 0; int < draws.split(",").length; int++) {
			draw.push(draws.split(",")[int]);
		}
		var multiselect = $("#draw").data("kendoMultiSelect");
		multiselect.value(draw);
		multiselect.refresh();
	}
	
}