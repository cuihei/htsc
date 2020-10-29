$(function(){
	datum_category_edit.init();
})

var datum_category_edit = {
	/**
	 * 初始化
	 */
	init : function(){
		//将资料类别元素设置为readonly
		$("#parentId").attr("disabled",true);
		datum_category_edit.bindPageEvent();
		datum_category_edit.bindTreeView();
	},
	
	/**
	 * 绑定树视图
	 */
	bindTreeView : function(){
		common.init("../datumCategory/tree?id=10182049", "POST", function(result) {
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
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 绑定确定按钮的click事件 */
		$("#submit").on("click",function(){
			var dcategory = {};
			// 获取资料类别编码
			dcategory.id = $("#datumcategoryId").val();
			//  获取上级资料类别Id
			dcategory.parentId = $("#parentId").val(); 
			// 获取资料类别名称
			dcategory.categoryName = $("#categoryName").val();
			//将数组转换成JOSN数据
			var dcategoryJson = JSON.stringify(dcategory);
			var param = common.add_param("dcategory",dcategoryJson);
			if(dcategory.categoryName==null||dcategory.categoryName==""){
				layer.msg("资料类别名称不能为空");
				return ;
			}
			common.init("../datumCategory/add","POST",function(result){
				//调回资料类别列表显示页面
				 if (result.code == 0) {
						if(result.value !="" && result.value != null){
							layer.msg(result.value);
							return;
						}else{
							layer.msg("保存失败！");
							return;
						}
				  }
				common.toPage("../datumCategory/index");
				//执行刷新资料类别列表按钮
				parent.$("#refresh").click();
			});
			common.do_submit(param);
		});

		/** 绑定取消按钮的click事件*/
		$("#back").on("click",function(){
			//调回用户列表显示页面
			common.toPage("../datumCategory/index");
			//执行刷新用户列表按钮
			parent.$("#refresh").click();
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
	}
}